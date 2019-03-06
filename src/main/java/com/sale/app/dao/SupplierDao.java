package com.sale.app.dao;

import java.util.List;

import com.sale.app.models.Product;
import com.sale.app.models.Supplier;



public interface SupplierDao {
	public void createSupplier(Supplier supplier);
	public List<Supplier> getAllSupplier();
	public void delete(Supplier supplier);
	public Supplier getsupplier(Long id);

}
