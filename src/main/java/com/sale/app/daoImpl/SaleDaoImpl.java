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

	private List<Inventory> getInventoryListDao(List<Inventory> inventoryList) {
		System.out.println("SaleDaoImpl.getInventoryListDao() statrt");
		List<String> skuList = new ArrayList<String>();
		for (Inventory inventory : inventoryList) {
			skuList.add(inventory.getSku());
		}
		String str = "from Inventory as t where t.sku IN :skus";
		
		List<Inventory> itemList = entityManager.createQuery(str,Inventory.class ).setParameter("skus",skuList)
				.getResultList();
		System.out.println("SaleDaoImpl.getInventoryListDao() end");
		return itemList;
	}

	@Override
	public Sale saveTranjection(List<Inventory> inventoryList, Salesman salesman) {
		System.out.println("SaleDaoImpl.saveTranjection() start");
		
		Salesman operator = entityManager.find(Salesman.class, salesman.getId());
		List<Inventory> inventoryListDao = getInventoryListDao(inventoryList);
		Sale sale = new Sale();
		sale.setSalesman(operator);
		sale.setTotal(getGrandTotal(inventoryListDao));
		entityManager.persist(sale);

		SaleDetails salesDetails = new SaleDetails();

		for (Inventory inventory : inventoryListDao) {
                double totalAmount = inventory.getQuantity() * inventory.getPrice();
				salesDetails.setCurrentDate(new Date());
				salesDetails.setQuantity(inventory.getQuantity());
				salesDetails.setInventory(inventory);
				salesDetails.setTax(inventory.getTax());
				salesDetails.setTotal(totalAmount);
		
		entityManager.persist(salesDetails);
		

	}
		System.out.println("SaleDaoImpl.saveTranjection() end");
		return sale;
	}
}
