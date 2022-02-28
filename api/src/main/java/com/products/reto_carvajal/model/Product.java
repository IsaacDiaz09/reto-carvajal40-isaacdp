package com.products.reto_carvajal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3439026397482656390L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idProd;

	private String name;

	private Double price;

	@Column(length = 512)
	private String description;

	private Integer stock;

	private Boolean isAvailable;

	private String image;

	public Product(Long idProduct, String name, String description, Integer stock, Double price, Boolean isAvailable,
			String image) {
		this.idProd = idProduct;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.isAvailable = isAvailable;
		this.image = image;
	}

	public Product() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getIdProd() {
		return idProd;
	}

}
