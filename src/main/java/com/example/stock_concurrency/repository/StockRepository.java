package com.example.stock_concurrency.repository;

import com.example.stock_concurrency.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
