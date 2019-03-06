package com.sale.app.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.sale.app.dao.SalesmanDao;
import com.sale.app.models.Salesman;

@Repository
@Transactional
public class SalesmanDaoImpl implements SalesmanDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createSalesman(Salesman salesman) {
		System.out.println(" create a salesman");
		entityManager.persist(salesman);
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Salesman> getAllSalesman() {
		return entityManager.createQuery("from Salesman").getResultList();
		
	}

	@Override
	public void delete(Salesman salesman) {
		
		Salesman user =entityManager.find(Salesman.class, salesman.getId());
		entityManager.remove(user);
		System.out.println("delete a salesman from dao");
	}

	@Override
	public Salesman getSalesman(Long id) {
		Salesman salesman = entityManager.find(Salesman.class, id);
		return salesman;
	}

	@Override
	public Salesman getByEmail(String email) {
		Salesman salesmanDb = (Salesman) entityManager.createQuery("from Salesman where email= :Email").setParameter("Email", email).getSingleResult();
		return salesmanDb;
	}

	

}
