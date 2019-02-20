package com.sale.app.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.sale.app.dao.ProductDao;
import com.sale.app.models.Product;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {
	@PersistenceContext
	private EntityManager eEntityManager;

	@Override
	public void addProduct(Product product) {
		System.out.println("Adding product");
		eEntityManager.persist(product);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProduct() {
		return eEntityManager.createQuery("from Product").getResultList();
	}

	
	@Override
	public void delete(Product product) {
		Product productfromDb =eEntityManager.find(Product.class, product.getProductId());
		eEntityManager.remove(productfromDb);
	}

}
