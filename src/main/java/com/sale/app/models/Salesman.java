package com.sale.app.models;

import java.util.ArrayList;
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

import org.springframework.transaction.annotation.EnableTransactionManagement;

@Entity
@Table(name = "salesman")
public class Salesman {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String salesmanName;
	@NotNull
	private String email;
	
	@NotNull
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "salesman", fetch = FetchType.LAZY)
	List<Sale> salesList = new ArrayList<>();
	
	


	public Salesman() {

	}

	public Salesman(Long id) {
		super();
		this.id = id;
	}

	public Salesman(String salesmanName,String email, String password) {
		super();
		this.salesmanName = salesmanName;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Sale> getSalesList() {
		return salesList;
	}

	public void setSalesList(List<Sale> salesList) {
		this.salesList = salesList;
	}

}
