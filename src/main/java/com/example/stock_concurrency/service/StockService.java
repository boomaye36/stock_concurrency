package com.example.stock_concurrency.service;

import com.example.stock_concurrency.domain.Stock;
import com.example.stock_concurrency.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }
    @Transactional
    public void decrease(Long id, Long quantity){
        // stock 조회
        // 재고를 감소시킨 뒤
        // 갱신된 값을 저장하도록 한다.

        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.decrease(quantity);
        stockRepository.saveAndFlush(stock);
    }
}
