package com.sale.app.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


import org.springframework.stereotype.Repository;

import com.sale.app.dao.InventoryDao;
import com.sale.app.models.Inventory;
import com.sale.app.models.Product;

@Repository
@Transactional
public class InventoryDaoImpl implements InventoryDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addInventory(Inventory inventory) {
		entityManager.persist(inventory);
	}

	@Override
	public Inventory getInventoryPrice(String sku) {
		Inventory inventory = entityManager.createQuery("from Inventory where sku= :sku", Inventory.class)
				.setParameter("sku", sku).getSingleResult();
		return inventory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Inventory> getAllInventory() {
		
		return entityManager.createQuery("from Inventory").getResultList();
	}

	@Override
	public void removeInventory(Inventory inventory) {
		Inventory inventoryPro= entityManager.find(Inventory.class, inventory.getId());
	 entityManager.remove(inventoryPro);
	}

	@Override
	public Inventory getInventoryBySku(String sku) {
		String sql = "SELECT inv FROM Inventory inv " + "JOIN FETCH inv.product p  where inv.sku= :sku";
		Inventory inventory = entityManager.createQuery(sql, Inventory.class)
				.setParameter("sku", sku).getSingleResult();
		return inventory;
		
		
	}

}
