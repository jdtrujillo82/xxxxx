package com.example.productprices.repository;

import com.example.productprices.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandsRepository extends JpaRepository<Brand, Long> {
}
