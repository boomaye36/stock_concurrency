package com.example.stock_concurrency.facade;

import com.example.stock_concurrency.repository.RedisLockRepository;
import com.example.stock_concurrency.service.StockService;
import org.redisson.api.RLock;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LettuceLockStockFacade {
    private RedisLockRepository redisLockRepository;
    private StockService stockService;

    public LettuceLockStockFacade(RedisLockRepository redisLockRepository, StockService stockService) {
        this.redisLockRepository = redisLockRepository;
        this.stockService = stockService;
    }

    public void decrease(Long id, Long quantity) throws InterruptedException {
        while (!redisLockRepository.lock(id)){
            Thread.sleep(100);
        }

        try{
            stockService.decrease(id, quantity);
        }finally {
            redisLockRepository.unlock(id);
        }
    }
}
