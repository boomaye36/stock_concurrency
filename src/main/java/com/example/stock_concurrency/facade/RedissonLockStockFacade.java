package com.example.stock_concurrency.facade;

import com.example.stock_concurrency.service.StockService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedissonLockStockFacade {
    private RedissonClient redissonClient;

    private StockService stockService;

    public RedissonLockStockFacade(RedissonClient redissonClient, StockService stockService) {
        this.redissonClient = redissonClient;
        this.stockService = stockService;
    }

    public void decrease(Long id, Long quantity){
        RLock lock = redissonClient.getLock(id.toString());

        try{
          boolean available = lock.tryLock(15, 1, TimeUnit.SECONDS);
            if (!available) {
                System.out.println("Lock 획득 실패");
                return;
            }
            stockService.decrease(id, quantity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }
}
