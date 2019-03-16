package com.sale.app.dao;

import java.util.List;

import com.sale.app.models.Inventory;
import com.sale.app.models.Product;


public interface InventoryDao {
	public void addInventory(Inventory inventory);
	public Inventory getInventoryPrice(String sku);
	public List<Inventory> getAllInventory();
	public void removeInventory(Inventory inventory);
	public Inventory getInventoryBySku(String sku);
}
