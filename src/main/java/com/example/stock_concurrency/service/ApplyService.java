package com.example.stock_concurrency.service;

import com.example.stock_concurrency.domain.Coupon;
import com.example.stock_concurrency.repository.CouponCountRepository;
import com.example.stock_concurrency.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {
    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;
    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
    }

    public void apply(Long userId){

        long count = couponCountRepository.increment();

        if (count > 100){
            return;
        }
        couponRepository.save(new Coupon(userId));
    }
}
