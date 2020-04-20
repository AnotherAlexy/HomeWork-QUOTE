package com.testproject.stockservice.repository;


import com.testproject.stockservice.model.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotesHistoryRepository extends JpaRepository<Quote, Long> {
}
