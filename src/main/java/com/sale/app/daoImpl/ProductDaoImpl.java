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
	private EntityManager entityManager;

	@Override
	public void addProduct(Product product) {
		System.out.println("Adding product");
		entityManager.persist(product);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProduct() {
		return entityManager.createQuery("from Product").getResultList();
	}

	@Override
	public void delete(Product product) {
		Product productfromDb = entityManager.find(Product.class, product.getProductId());
		entityManager.remove(productfromDb);
	}

	@Override
	public Product getProduct(Long productId) {
		Product productfrDb = entityManager.find(Product.class, productId);
		return productfrDb;
	}

}
