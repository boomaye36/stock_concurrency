package com.example.stock_concurrency.service;

import com.example.stock_concurrency.producer.CouponCreateProducer;
import com.example.stock_concurrency.repository.AppliedUserRepository;
import com.example.stock_concurrency.repository.CouponCountRepository;
import com.example.stock_concurrency.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {
    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;
    private final AppliedUserRepository appliedUserRepository;
    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer, AppliedUserRepository appliedUserRepository) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
        this.appliedUserRepository = appliedUserRepository;
    }

    public void apply(Long userId){
       Long apply = appliedUserRepository.add(userId);

       if (apply != 1){
           return;
       } // 쿠폰을 발급 받으면 쿠폰을 발급 받지 못함

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