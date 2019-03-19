package com.sale.app.dao;

import java.util.List;

import com.sale.app.models.Inventory;
import com.sale.app.models.Sale;
import com.sale.app.models.Salesman;

public interface SaleDao {
public Sale saveTranjection(List<Inventory> inventoryList, Salesman salesman );
}
