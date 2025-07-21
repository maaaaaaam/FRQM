package com.randomquotemachine.backend.repository;

import com.randomquotemachine.backend.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Query(
    value = "SELECT * FROM Quotes ORDER BY id OFFSET :offset ROWS FETCH NEXT 1 ROWS ONLY",
    nativeQuery = true
    )
    Quote findQuoteByOffset(@Param("offset") int offset);

}