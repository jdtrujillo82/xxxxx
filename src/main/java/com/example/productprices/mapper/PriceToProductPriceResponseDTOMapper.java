package com.example.productprices.mapper;

import com.example.productprices.dto.ProductPricesResponseDTO;
import com.example.productprices.model.Price;
import org.springframework.stereotype.Component;

@Component
public class PriceToProductPriceResponseDTOMapper {

    public ProductPricesResponseDTO mapPriceToProductPriceResponseDTO(Price price){
       
    	ProductPricesResponseDTO productPricesResponseDTO = new ProductPricesResponseDTO();
    	productPricesResponseDTO.setProductId(price.getPriceId().getProductId());
    	productPricesResponseDTO.setBrandId(price.getPriceId().getBrand().getId());
    	productPricesResponseDTO.setPriceList(price.getPriceId().getPriceList());
    	productPricesResponseDTO.setStartDate(price.getPriceId().getStartDate());
    	productPricesResponseDTO.setEndDate(price.getPriceId().getEndDate());
    	productPricesResponseDTO.setPrice(price.getCurrentPrice());
    	productPricesResponseDTO.setCurr(price.getPriceId().getCurr());
    	
    	return productPricesResponseDTO;
    }
}
