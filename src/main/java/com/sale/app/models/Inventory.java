package com.sale.app.models;

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
@Table(name = "inventory")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private int quantity;

	@NotNull
	private double price;

	@NotNull
	private int batchNum;

	@NotNull
	private String sku;

	@NotNull
	private double tax;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "inventory", fetch = FetchType.LAZY)
	private List<SaleDetails> salesList;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_Id")
	private Supplier supplier;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "salesman_Id")
	private Salesman salesman;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_Id")
	private Product product;

	public Inventory() {
	}

	public Inventory(Long id) {
		super();
		this.id = id;
	}

	public Inventory(Product product, int quantity, double price, int batchNum, Supplier supplier, Salesman salesman,
			String sku) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.batchNum = batchNum;

		this.supplier = supplier;
		this.salesman = salesman;
		this.sku = sku;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(int batchNum) {
		this.batchNum = batchNum;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public List<SaleDetails> getSalesList() {
		return salesList;
	}

	public void setSalesList(List<SaleDetails> salesList) {
		this.salesList = salesList;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Salesman getSalesman() {
		return salesman;
	}

	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", quantity=" + quantity + ", price=" + price + ", batchNum=" + batchNum
				+ ", sku=" + sku + "]";
	}

}
