package com.jpaDemo;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
	public static void main(String[] args) {
		//1. Create EntitymanagerFactory
		String persistenceUnitName = "JPA-HelloWorld";
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
		
		//2.Create EntityManager
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//3.Initialize Transaction
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		//4.Persistence operation
		Customer customer = new Customer();
		customer.setAge(27);
		customer.setEmail("sof10@pitt.edu");
		customer.setLastName("Francis");
		customer.setBirth(new Date());
		customer.setCreatTime(new Date());
		
		entityManager.persist(customer);
		
		//5.Commit transaction
		transaction.commit();
		
		//6.close EntityManager
		entityManager.close();
	}
}
