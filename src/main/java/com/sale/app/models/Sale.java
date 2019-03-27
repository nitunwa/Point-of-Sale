package com.sale.app.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "sale")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "saleId")
public class Sale {

	public Sale(Long saleId) {
		super();
		this.saleId = saleId;
	}
	public Sale() {
		super();
		
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long saleId;
	
	@NotNull
	private Date currentDate = new Date();
	
	
	private Long customerId;
	
	@NotNull
	private double total;
	
	
	private String credit_card;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "salesman_Id")
	private Salesman salesman;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sale", fetch = FetchType.LAZY)
	List<SaleDetails> salesList;

	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCredit_card() {
		return credit_card;
	}

	public void setCredit_card(String credit_card) {
		this.credit_card = credit_card;
	}

	public Salesman getSalesman() {
		return salesman;
	}

	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}

	public List<SaleDetails> getSalesList() {
		return salesList;
	}

	public void setSalesList(List<SaleDetails> salesList) {
		this.salesList = salesList;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
	

}
