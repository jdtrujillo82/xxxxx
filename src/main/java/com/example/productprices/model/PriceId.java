package com.example.productprices.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class PriceId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name = "BRAND_ID")
    private Brand brand;
    @Column (name= "START_DATE")
    private OffsetDateTime startDate;
    @Column (name= "END_DATE")
    private OffsetDateTime endDate;
    @Column (name= "PRICE_LIST")
    private Long priceList;
    @Column (name= "PRODUCT_ID")
    private Long productId;
    @Column (name= "PRIORITY")
    private Integer priority;
    @Column (name= "CURR")
    private String curr;
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public OffsetDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(OffsetDateTime startDate) {
		this.startDate = startDate;
	}
	public OffsetDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(OffsetDateTime endDate) {
		this.endDate = endDate;
	}
	public Long getPriceList() {
		return priceList;
	}
	public void setPriceList(Long priceList) {
		this.priceList = priceList;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getCurr() {
		return curr;
	}
	public void setCurr(String curr) {
		this.curr = curr;
	}
}
