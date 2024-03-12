package com.example.productprices.service;

import com.example.productprices.exception.NotFoundException;
import com.example.productprices.model.Price;
import com.example.productprices.repository.PriceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class ProductPricesService {

    private final PriceRepository priceRepository;

    public ProductPricesService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price getCurrentPrice(Long productId, Long brandId, OffsetDateTime applicationDate) throws NotFoundException {
        Page<Price> pagedPrices = priceRepository.getCurrentPrice(brandId, productId, applicationDate, PageRequest.of(0, 1));
        if (pagedPrices.isEmpty() || pagedPrices.getContent().isEmpty()) {
            throw new NotFoundException(HttpStatus.NOT_FOUND, "Unable to locate current price for brandId " + brandId +
                    " productId " + brandId + " and applicationDate " + applicationDate);
        }
        return pagedPrices.getContent().get(0);
    }
}


