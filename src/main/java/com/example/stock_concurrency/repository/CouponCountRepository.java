package com.example.stock_concurrency.repository;

import com.example.stock_concurrency.domain.Coupon;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CouponCountRepository {
    private final RedisTemplate<String, String> redisTemplate;
    private final CouponRepository couponRepository;

    public CouponCountRepository(RedisTemplate<String, String> redisTemplate,
                                 CouponRepository couponRepository) {
        this.redisTemplate = redisTemplate;
        this.couponRepository = couponRepository;
    }

    public Long increment(){
        return redisTemplate
                .opsForValue()
                .increment("coupon_count");
    }
}
