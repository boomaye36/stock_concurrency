package com.example.stock_concurrency.repository;

import com.example.stock_concurrency.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
