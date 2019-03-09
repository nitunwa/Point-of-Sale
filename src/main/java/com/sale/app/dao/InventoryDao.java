package com.sale.app.dao;

import java.util.List;

import com.sale.app.models.Inventory;

public interface InventoryDao {
	public void addInventory(Inventory inventory);
	public Inventory getInventoryPrice(String sku);
	public List<Inventory> getAllInventory();
}
