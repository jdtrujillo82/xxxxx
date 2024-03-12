package com.example.productprices.controller;

import com.example.productprices.dto.ProductPricesResponseDTO;
import com.example.productprices.exception.NotFoundException;
import com.example.productprices.mapper.PriceToProductPriceResponseDTOMapper;
import com.example.productprices.model.Price;
import com.example.productprices.service.ProductPricesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/inditex")
public class InditexProductPricesController {

    private final ProductPricesService productPricesService;
    private final PriceToProductPriceResponseDTOMapper mapper;

    public InditexProductPricesController(ProductPricesService productPricesService, PriceToProductPriceResponseDTOMapper mapper) {
        this.productPricesService = productPricesService;
        this.mapper = mapper;
    }

    @GetMapping("/productPrices")
    public ResponseEntity<ProductPricesResponseDTO> getCurrentPrice(
            @RequestParam("dateTime") OffsetDateTime aplicationDateTime,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {
        try {

        	
            Price price = productPricesService.getCurrentPrice(productId, brandId, aplicationDateTime);
            ProductPricesResponseDTO productPricesResponseDTO = mapper.mapPriceToProductPriceResponseDTO(price);

            return new ResponseEntity<>(productPricesResponseDTO, HttpStatus.OK);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
