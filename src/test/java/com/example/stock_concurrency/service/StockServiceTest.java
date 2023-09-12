package com.example.stock_concurrency.service;

import com.example.stock_concurrency.domain.Stock;
import com.example.stock_concurrency.repository.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StockServiceTest {
    @Autowired
    private StockService stockService;
    @Autowired
    private StockRepository stockRepository;

    @BeforeEach
    public void Before(){
        stockRepository.saveAndFlush(new Stock(1L, 100L));
    }
    @AfterEach
    public void after(){
        stockRepository.deleteAll();
    }
    @Test
    void decrease() {
        stockService.decrease(1L, 1L);

        // 100 - 1 = 99
        Stock stock = stockRepository.findById(1L).orElseThrow();
        Assertions.assertEquals(99, stock.getQuantity());
    }

    @Test
    public void 동시에_100개의 요청(){

    }
}