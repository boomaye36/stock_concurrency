package com.example.stock_concurrency.service;

import com.example.stock_concurrency.domain.Coupon;
import com.example.stock_concurrency.repository.CouponRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ApplyServiceTest {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private CouponRepository couponRepository;
    @Test
    void apply() {
        applyService.apply(1L);

        long count = couponRepository.count();

        Assertions.assertEquals(count, 1);
    }
}