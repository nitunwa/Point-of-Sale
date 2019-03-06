package com.sale.app.daoImpl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.sale.app.dao.SaleDao;
import com.sale.app.models.Inventory;
import com.sale.app.models.Sale;
import com.sale.app.models.Salesman;

@Repository
@Transactional
public class SaleDaoImpl implements SaleDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveTranjection(int quantity, String sku,Salesman salesman) {

		Inventory inventory = entityManager.createQuery("from Inventory where sku= :sku", Inventory.class)
				.setParameter("sku", sku).getSingleResult();
		Sale sale = new Sale();
		double totalPrice =inventory.getPrice() *quantity;
		sale.setCurrentDate(new Date());
		sale.setQuantity(quantity);
		sale.setPrice(inventory.getPrice());
		sale.setInventory(inventory);
		sale.setTotal(totalPrice);
		sale.setSalesman(salesman);
		entityManager.persist(sale);
	}

}
