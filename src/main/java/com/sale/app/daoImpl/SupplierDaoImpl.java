package com.sale.app.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.sale.app.dao.SupplierDao;
import com.sale.app.models.Product;
import com.sale.app.models.Salesman;
import com.sale.app.models.Supplier;

@Repository
@Transactional
public class SupplierDaoImpl implements SupplierDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createSupplier(Supplier supplier) {

		entityManager.persist(supplier);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Supplier> getAllSupplier() {
		return entityManager.createQuery("from Supplier").getResultList();
	}

	@Override
	public void delete(Supplier supplier) {
		Supplier user =entityManager.find(Supplier.class, supplier.getId());
		entityManager.remove(user);
		

	}

	@Override
	public Supplier getsupplier(Long id) {
		Supplier supplierFmDb = entityManager.find(Supplier.class,id);
		return supplierFmDb;
	}

	

}
