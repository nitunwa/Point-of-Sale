package com.sale.app.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.sale.app.dao.SaleDao;
import com.sale.app.models.Inventory;
import com.sale.app.models.Sale;
import com.sale.app.models.SaleDetails;
import com.sale.app.models.Salesman;

@Repository
@Transactional
public class SaleDaoImpl implements SaleDao {
	@PersistenceContext
	private EntityManager entityManager;

	private double getGrandTotal(List<Inventory> inventoryList) {
		System.out.println("SaleDaoImpl.getGrandTotal() start");
		double sum = 0;
		for (Inventory inventory : inventoryList) {

			double totalAmount = inventory.getQuantity() * inventory.getPrice();
			sum = sum + totalAmount;
		}
		System.out.println("SaleDaoImpl.getGrandTotal() end");
		return sum;
	}

	
	public Inventory getInventory(Inventory inventory) {

		Inventory item = entityManager.createQuery("from Inventory where sku= :sku", Inventory.class)
				.setParameter("sku", inventory.getSku()).getSingleResult();
		return item;

	}

	@Override
	public Sale saveTranjection(List<Inventory> inventoryList, Salesman salesman) {
		System.out.println("SaleDaoImpl.saveTranjection() start");

		Salesman operator = entityManager.find(Salesman.class, salesman.getId());

		Sale sale = new Sale();
		sale.setSalesman(operator);
		double subTotal = getGrandTotal(inventoryList);
		double tax = subTotal * .1;
		double total = subTotal + tax;
		sale.setSubtTotal(subTotal);
		sale.setTax(tax);
		sale.setTotal(total);
		entityManager.persist(sale);

		SaleDetails salesDetails = new SaleDetails();
		Inventory item = new Inventory();
		for (Inventory inventory : inventoryList) {

			double totalAmount = inventory.getQuantity() * inventory.getPrice() + tax;
			salesDetails.setCurrentDate(new Date());
			salesDetails.setQuantity(inventory.getQuantity());
			item = getInventory(inventory);
			salesDetails.setInventory(item);

			salesDetails.setTotal(totalAmount);
			salesDetails.setSale(sale); 

			entityManager.persist(salesDetails);

		}
		System.out.println("SaleDaoImpl.saveTranjection() end");
		return sale;
	}

	public Sale getSale(Sale sale) {

		Sale obj = entityManager.find(Sale.class, sale.getSaleId());
		obj.getSalesList();
		obj.getSalesman();
		return obj;
	}
}
