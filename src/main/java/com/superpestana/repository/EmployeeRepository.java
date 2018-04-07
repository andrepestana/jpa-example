package com.superpestana.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.superpestana.model.Employee;

public class EmployeeRepository {

	private static final String PERSISTENCE_UNIT_NAME = "JpaExample";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	private EntityManager em;

	public EmployeeRepository() {
		super();
		em = factory.createEntityManager();
	}

	public void save(Employee employee) {
		em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> findAll(){
		Query q = em.createQuery("select e from Employee e", Employee.class);
		return q.getResultList();
	}
	
	public Employee findById(long id) {
		Query q = em.createQuery("select e from Employee e where e.id = :id", Employee.class);
		q.setParameter("id", id);
		return (Employee) q.getSingleResult();
	}
}
