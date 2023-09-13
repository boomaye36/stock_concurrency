package com.example.stock_concurrency.repository;

import com.example.stock_concurrency.domain.FailedEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailedEventRepository extends JpaRepository<FailedEvent, Long> {
}
