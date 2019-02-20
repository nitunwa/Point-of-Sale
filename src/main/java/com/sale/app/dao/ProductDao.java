package com.sale.app.dao;

import java.util.List;

import com.sale.app.models.Product;

public interface ProductDao {
public void addProduct(Product product);
public List<Product> getAllProduct();
public void delete(Product product);
}
