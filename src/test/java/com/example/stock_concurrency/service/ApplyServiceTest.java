package com.example.stock_concurrency.service;

import com.example.stock_concurrency.domain.Coupon;
import com.example.stock_concurrency.repository.CouponRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    @Test
    public void 여러명응모() throws InterruptedException {
        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++){
            long userId = i;
            executorService.submit(() -> {
                try{
                    applyService.apply(userId);
                }finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        Thread.sleep(10000); // thread sleep 이용하여 데이터 처리 도중에 쿠폰이 발급되지 않도록 조정
       long count =  couponRepository.count();
       Assertions.assertEquals(count, 100);
    }

    @Test
    public void 한명당_하나의_쿠폰() throws InterruptedException {
        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++){
            long userId = i;
            executorService.submit(() -> {
                try{
                    applyService.apply(1L);
                }finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        Thread.sleep(10000); // thread sleep 이용하여 데이터 처리 도중에 쿠폰이 발급되지 않도록 조정
        long count =  couponRepository.count();
        Assertions.assertEquals(count, 1);
    }
}