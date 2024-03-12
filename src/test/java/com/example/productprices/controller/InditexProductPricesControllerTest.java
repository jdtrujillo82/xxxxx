package com.example.productprices.controller;

import com.example.productprices.TestUtils;
import com.example.productprices.dto.ProductPricesResponseDTO;
import com.example.productprices.exception.NotFoundException;
import com.example.productprices.mapper.PriceToProductPriceResponseDTOMapper;
import com.example.productprices.model.Price;
import com.example.productprices.service.ProductPricesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InditexProductPricesControllerTest {

    @Mock
    private ProductPricesService productPricesService;
    private InditexProductPricesController productPricesController;
    private Price price;
    private ProductPricesResponseDTO productPricesResponseDTOExpectedOk;

    private OffsetDateTime now;


    @BeforeEach
    void init() {
        PriceToProductPriceResponseDTOMapper mapper = new PriceToProductPriceResponseDTOMapper();
        productPricesController = new InditexProductPricesController(productPricesService, mapper);
        price = TestUtils.generateCompleteOkPrice();
        productPricesResponseDTOExpectedOk = TestUtils.generateCompleteOkProductPricesResponseDTO();
        now = OffsetDateTime.now();

    }

    @Test
    void getCurrentPriceOkTest() throws NotFoundException {
        when(productPricesService.getCurrentPrice(any(Long.class), any(Long.class), any(OffsetDateTime.class))).thenReturn(price);
        ResponseEntity<ProductPricesResponseDTO> response = productPricesController.getCurrentPrice(now, TestUtils.PRODUCT_ID, TestUtils.BRAND_ID);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertAll("response", () -> assertNotNull(response.getBody().getPrice()), () -> assertNotNull(response.getBody().getProductId()), () -> assertNotNull(response.getBody().getPriceList()), () -> assertNotNull(response.getBody().getStartDate()), () -> assertNotNull(response.getBody().getEndDate()), () -> assertNotNull(response.getBody().getCurr()), () -> assertNotNull(response.getBody().getBrandId()));
        assertAll("response", () -> assertEquals(productPricesResponseDTOExpectedOk.getPrice(), response.getBody().getPrice()), () -> assertEquals(productPricesResponseDTOExpectedOk.getProductId(), response.getBody().getProductId()), () -> assertEquals(productPricesResponseDTOExpectedOk.getPriceList(), response.getBody().getPriceList()), () -> assertEquals(productPricesResponseDTOExpectedOk.getStartDate(), response.getBody().getStartDate()), () -> assertEquals(productPricesResponseDTOExpectedOk.getEndDate(), response.getBody().getEndDate()), () -> assertEquals(productPricesResponseDTOExpectedOk.getCurr(), response.getBody().getCurr()), () -> assertEquals(productPricesResponseDTOExpectedOk.getBrandId(), response.getBody().getBrandId()));
    }

    @Test
    void getCurrentPriceKoTest() throws NotFoundException {
        when(productPricesService.getCurrentPrice(any(Long.class), any(Long.class), any(OffsetDateTime.class))).thenThrow(new NotFoundException(HttpStatus.NOT_FOUND, "Error"));
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> productPricesController.getCurrentPrice(now, TestUtils.PRODUCT_ID, TestUtils.BRAND_ID));
        assertEquals("404 NOT_FOUND \"Error\"", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

}
