package com.example.productprices.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRICES")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Price {

    @EmbeddedId
    private PriceId priceId;
    @Column (name= "PRICE")
    private Double currentPrice;
	public PriceId getPriceId() {
		return priceId;
	}
	public void setPriceId(PriceId priceId) {
		this.priceId = priceId;
	}
	public Double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}
    
}
