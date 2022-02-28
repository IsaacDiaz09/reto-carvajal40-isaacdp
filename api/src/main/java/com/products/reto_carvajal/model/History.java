package com.products.reto_carvajal.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "history")
public class History implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1582999888989105067L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idHistory;

	@OneToOne
	private Product prod;

	private Date at = new Date();

	public History() {
	}

	public History(Product prod) {
		this.prod = prod;
	}

	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}

	public Long getIdHistory() {
		return idHistory;
	}

	public Date getAt() {
		return at;
	}

}
