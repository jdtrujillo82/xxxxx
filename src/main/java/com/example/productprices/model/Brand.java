package com.example.productprices.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "BRANDS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name= "ID")
    private Long id;
    @Column(name= "NAME")
    private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
