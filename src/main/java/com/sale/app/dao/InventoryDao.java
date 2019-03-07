package com.sale.app.dao;

import com.sale.app.models.Inventory;

public interface InventoryDao {
	public void addInventory(Inventory inventory);
	public Inventory getInventoryPrice(String sku);
}
