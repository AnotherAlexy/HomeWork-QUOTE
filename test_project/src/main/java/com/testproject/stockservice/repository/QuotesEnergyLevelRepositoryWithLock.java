package com.testproject.stockservice.repository;

import com.testproject.stockservice.model.entity.QuoteElvl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface QuotesEnergyLevelRepositoryWithLock extends JpaRepository<QuoteElvl, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select q from QuoteElvl q where q.isin = :isin")
    Optional<QuoteElvl> findOneForUpdate(@Param("isin") String isin);
}
