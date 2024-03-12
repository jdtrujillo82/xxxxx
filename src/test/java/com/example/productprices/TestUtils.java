package com.example.productprices;

import com.example.productprices.dto.ProductPricesResponseDTO;
import com.example.productprices.model.Brand;
import com.example.productprices.model.Price;
import com.example.productprices.model.PriceId;

import java.time.OffsetDateTime;

public class TestUtils {

    public static final Long BRAND_ID = 1L;
    public static final String BRAND_NAME = "BrandName";
    public static final OffsetDateTime START_DATE = OffsetDateTime.MIN;
    public static final OffsetDateTime END_DATE = OffsetDateTime.MAX;
    public static final Long PRICE_LIST = 2L;
    public static final Long PRODUCT_ID = 3L;
    public static final Integer PRIORITY = 10;
    public static final String CURR = "EUR";
    public static final Double PRICE = 50.5;

    public static Price generateCompleteOkPrice() {
    	
    	Brand brand = new Brand();
    	brand.setId(BRAND_ID);
    	brand.setName(BRAND_NAME);
  
        PriceId priceId = new PriceId();
        priceId.setBrand(brand);
        priceId.setStartDate(START_DATE);
        priceId.setEndDate(END_DATE);
        priceId.setPriceList(PRICE_LIST);
        priceId.setProductId(PRODUCT_ID);
        priceId.setPriority(PRIORITY);
        priceId.setCurr(CURR);
        
        Price price = new Price();
        price.setPriceId(priceId);
        price.setCurrentPrice(PRICE);
       
        return price;
    }

    public static ProductPricesResponseDTO generateCompleteOkProductPricesResponseDTO() {

    	ProductPricesResponseDTO productPricesResponseDTO = new ProductPricesResponseDTO();
    	
    	productPricesResponseDTO.setBrandId(BRAND_ID);
    	productPricesResponseDTO.setStartDate(START_DATE);
    	productPricesResponseDTO.setEndDate(END_DATE);
    	productPricesResponseDTO.setPriceList(PRICE_LIST);
    	productPricesResponseDTO.setProductId(PRODUCT_ID);
    	productPricesResponseDTO.setPrice(PRICE);
    	productPricesResponseDTO.setCurr(CURR);
        return productPricesResponseDTO;
    }

    public static ProductPricesResponseDTO generateCustomProductPricesResponseDTO
            (Long brandId, OffsetDateTime startDate,OffsetDateTime endDate, Long priceList, Long productI, Double price, String curr) {

    	ProductPricesResponseDTO productPricesResponseDTO = new ProductPricesResponseDTO();
    	
    	productPricesResponseDTO.setBrandId(brandId);
    	productPricesResponseDTO.setStartDate(startDate);
    	productPricesResponseDTO.setEndDate(endDate);
    	productPricesResponseDTO.setPriceList(priceList);
    	productPricesResponseDTO.setProductId(productI);
    	productPricesResponseDTO.setPrice(price);
    	productPricesResponseDTO.setCurr(curr);
    	
    	 return productPricesResponseDTO;
    
    }


}
