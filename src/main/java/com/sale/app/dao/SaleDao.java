package com.sale.app.dao;

import com.sale.app.models.Salesman;

public interface SaleDao {
public void saveTranjection(int quantity,String sku,Salesman salesman,double tax);
}
