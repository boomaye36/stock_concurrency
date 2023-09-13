package com.example.stock_concurrency.service;

import com.example.stock_concurrency.domain.Coupon;
import com.example.stock_concurrency.producer.CouponCreateProducer;
import com.example.stock_concurrency.repository.CouponCountRepository;
import com.example.stock_concurrency.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {
    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;
    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
    }

    public void apply(Long userId){

        Long count = couponCountRepository.increment();
       // Long count = couponRepository.count();
        if (count > 100){
            return;
        }
        //couponRepository.save(new Coupon(userId));
        couponCreateProducer.create(userId);
    }
}
//docker exec -it kafka kafka-console-consumer.sh --topic coupon_create --bootstrap-server localhost:9092 --key-deserializer "org.apache.kafka.common.serialization.StringDeserializer" --value-deserializer "org.apache.kafka.common.serialization.LongDeserializer"