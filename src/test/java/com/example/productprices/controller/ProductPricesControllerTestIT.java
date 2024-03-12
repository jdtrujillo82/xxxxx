package com.example.productprices.controller;

import com.example.productprices.TestUtils;
import com.example.productprices.dto.ProductPricesResponseDTO;
import com.example.productprices.mapper.PriceToProductPriceResponseDTOMapper;
import com.example.productprices.repository.PriceRepository;
import com.example.productprices.service.ProductPricesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductPricesControllerTestIT {


    @Autowired
    private PriceRepository priceRepository;
    private InditexProductPricesController productPricesController;



    @BeforeEach
    void init() {
        ProductPricesService productPricesService = new ProductPricesService(priceRepository);
        PriceToProductPriceResponseDTOMapper mapper = new PriceToProductPriceResponseDTOMapper();
        productPricesController = new InditexProductPricesController(productPricesService, mapper);

    }

    @ParameterizedTest
    @MethodSource("provideParamsForCurrentPrice")
    void getCurrentPriceOkTest(OffsetDateTime applicationDateTime, Long productId, Long brandId, ProductPricesResponseDTO expectedResponse)  {
        ResponseEntity<ProductPricesResponseDTO> response = productPricesController.getCurrentPrice(applicationDateTime, productId, brandId);
        
        assertNotNull(response.getBody());
        assertAll("response",
                () -> assertEquals(ProductPricesResponseDTO.class, response.getBody().getClass()),
                () -> assertEquals(expectedResponse.getBrandId(), response.getBody().getBrandId()),
                () -> assertEquals(expectedResponse.getStartDate(), response.getBody().getStartDate()),
                () -> assertEquals(expectedResponse.getEndDate(), response.getBody().getEndDate()),
                () -> assertEquals(expectedResponse.getPriceList(), response.getBody().getPriceList()),
                () -> assertEquals(expectedResponse.getPrice(), response.getBody().getPrice()),
                () -> assertEquals(expectedResponse.getCurr(), response.getBody().getCurr()),
                () -> assertEquals(expectedResponse.getProductId(), response.getBody().getProductId())
        );
    }

    private static Stream<Arguments> provideParamsForCurrentPrice() {
        //Generate arguments with params and expected reponse for parametrized test
        return Stream.of(
                Arguments.of(OffsetDateTime.parse("2020-06-14T10:00:00+02:00"), 35455L, 1L,
                        TestUtils.generateCustomProductPricesResponseDTO
                        (1L, OffsetDateTime.parse("2020-06-14T00:00:00+02:00"),OffsetDateTime.parse("2020-12-31T23:59:59+01:00"), 1L,
                                35455L,35.50, "EUR")),

                Arguments.of(OffsetDateTime.parse("2020-06-14T16:00:00+02:00"), 35455L, 1L,
                        TestUtils.generateCustomProductPricesResponseDTO
                                (1L, OffsetDateTime.parse("2020-06-14T15:00:00+02:00"),OffsetDateTime.parse("2020-06-14T18:30:00+02:00"), 2L,
                                        35455L,25.45, "EUR")),

                Arguments.of(OffsetDateTime.parse("2020-06-14T21:00:00+02:00"), 35455L, 1L,
                        TestUtils.generateCustomProductPricesResponseDTO
                                (1L, OffsetDateTime.parse("2020-06-14T00:00:00+02:00"),OffsetDateTime.parse("2020-12-31T23:59:59+01:00"), 1L,
                                        35455L,35.50, "EUR")),

                Arguments.of(OffsetDateTime.parse("2020-06-15T10:00:00+02:00"), 35455L, 1L,
                        TestUtils.generateCustomProductPricesResponseDTO
                                (1L, OffsetDateTime.parse("2020-06-15T00:00:00+02:00"),OffsetDateTime.parse("2020-06-15T11:00:00+02:00"), 3L,
                                        35455L,30.50, "EUR")),

                Arguments.of(OffsetDateTime.parse("2020-06-16T21:00:00+02:00"), 35455L, 1L,
                        TestUtils.generateCustomProductPricesResponseDTO
                                (1L, OffsetDateTime.parse("2020-06-15T16:00:00+02:00"),OffsetDateTime.parse("2020-12-31T23:59:59+01:00"), 4L,
                                        35455L,38.95, "EUR"))

        );
    }


}
