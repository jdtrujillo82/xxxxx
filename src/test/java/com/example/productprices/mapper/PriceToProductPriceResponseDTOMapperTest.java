package com.example.productprices.mapper;

import com.example.productprices.TestUtils;
import com.example.productprices.dto.ProductPricesResponseDTO;
import com.example.productprices.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceToProductPriceResponseDTOMapperTest {

    private PriceToProductPriceResponseDTOMapper mapper;

    private Price price;
    private ProductPricesResponseDTO productPricesResponseDTOExpectedOk;

    @BeforeEach
    void init() {
        mapper = new PriceToProductPriceResponseDTOMapper();
        price = TestUtils.generateCompleteOkPrice();
        productPricesResponseDTOExpectedOk = TestUtils.generateCompleteOkProductPricesResponseDTO();
    }

    @Test
    void mapPrice2ProductPriceResponseDTOOkTest() {
        ProductPricesResponseDTO response = mapper.mapPriceToProductPriceResponseDTO(price);

        assertAll("response",
                () -> assertEquals(productPricesResponseDTOExpectedOk.getPrice(), response.getPrice()),
                () -> assertEquals(productPricesResponseDTOExpectedOk.getProductId(), response.getProductId()),
                () -> assertEquals(productPricesResponseDTOExpectedOk.getPriceList(), response.getPriceList())
        );
    }

}
