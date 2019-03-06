package com.sale.app.dao;

import java.util.List;

import com.sale.app.models.Salesman;
import com.sale.app.models.Supplier;

public interface SalesmanDao {
public void createSalesman(Salesman salesman);
public List<Salesman> getAllSalesman();
public void delete(Salesman salesman);
public Salesman getSalesman(Long id);
public Salesman getByEmail(String email);
}
