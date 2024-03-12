package com.example.productprices.repository;

import com.example.productprices.model.Price;
import com.example.productprices.model.PriceId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.OffsetDateTime;

public interface PriceRepository extends PagingAndSortingRepository<Price, PriceId> {
    @Query("SELECT p " +
            "FROM Price p " +
            "WHERE p.priceId.brand.id = :brandId " +
            "AND p.priceId.productId = :productId " +
            "AND p.priceId.startDate <= :applicationDate " +
            "AND p.priceId.endDate >= :applicationDate " +
            "ORDER BY p.priceId.priority DESC")
    Page<Price> getCurrentPrice(Long brandId, Long productId, OffsetDateTime applicationDate, Pageable pageable);
}
