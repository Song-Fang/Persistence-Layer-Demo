package com.jpaTest;

import static org.junit.Assert.*;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jpaDemo.Customer;

public class JPATest {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory("JPA-HelloWorld");
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
		transaction.begin();
	}

	@After
	public void destroy() {
		
		//Before I submit, the transaction is not closed and roll back can be done
		//transaction.rollback();
		//When I commit, the transaction is not active
		transaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void testFind() {
		Customer customer = entityManager.find(Customer.class, 1);
		System.out.println("-------------------");
		System.out.println(customer);
	}

	@Test
	public void getReference() {
		Customer customer = entityManager.getReference(Customer.class, 1);

		// 获取的是customer的代理对象，在实际使用的时候才打印customer
		// 如果将entityManager提前关闭，会导致懒加载异常
		System.out.println(customer.getClass().getName());
		System.out.println("-------------------");
		System.out.println(customer);

	}

	// 如果对象有id，则会抛出异常，但hibernate的save方法不会抛异常
	@Test
	public void testPersistence() {
		Customer customer = new Customer();
		customer.setAge(27);
		customer.setBirth(new Date());
		customer.setCreatTime(new Date());
		customer.setEmail("lynn@163.com");
		customer.setLastName("Lynn");
		//customer.setId(5);
		entityManager.persist(customer);
		System.out.println(customer.getId());
	}

	// 不能移除游离对象，只能移除持久化对象，但hibernate可以
	@Test
	public void testRemove() {
		// Customer customer = new Customer();
		// customer.setId(2);
		// entityManager.remove(customer);
		Customer customer = entityManager.find(Customer.class, 7);
		//It will show the operation is failed.
		//clear() method make the customer object become detached state
		// it will not be found in session part
		entityManager.clear();
		entityManager.remove(customer);
	}

	//若对象是transient object, 
	//Merge方法会创建一个新的对象，并对新的对象进行持久化操作，即insert操作
	//No selection, just insert your record
	@Test
	public void testMerger1() {
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatTime(new Date());
		customer.setEmail("Peter@163.com");
		customer.setLastName("Peter");
		Customer customer2 = entityManager.merge(customer);
		System.out.println("customer#id: " + customer.getId());
		System.out.println("customer#id: " + customer2.getId());

	}

	// 若传入的是一个游离对象，即有OID，在EntityManager缓存中没有对象
	// 若在数据库中也没有对应的记录
	// 创建一个新的对象
	// 执行insert操作
	@Test
	public void testMerger2() {
		Customer customer = new Customer();
		customer.setAge(19);
		customer.setBirth(new Date());
		customer.setCreatTime(new Date());
		customer.setEmail("Kathy@163.com");
		customer.setLastName("Kathy");
		customer.setId(8);
		//execute select and insert operation 
		Customer customer2 = entityManager.merge(customer);
		System.out.println("customer#id: " + customer.getId());
		System.out.println("customer#id: " + customer2.getId());

	}

	// 若传入的是一个游离对象，即有OID，在EntityManager缓存中没有对象
	// 若在数据库中有对应的记录，加载对应记录
	// JPA执行查询操作，返回一个新的对象
	// 执行update操作
	@Test
	public void testMerger3() {
		Customer customer = new Customer();
		customer.setAge(19);
		customer.setBirth(new Date());
		customer.setCreatTime(new Date());
		customer.setEmail("Peter@163.com");
		customer.setLastName("Peter");
		// 执行查询操作
		customer.setId(6);
		System.out.println("------------");
		//merge方法会去执行查询
		//execute select and update operation
		Customer customer2 = entityManager.merge(customer);
		System.out.println(customer == customer2);

	}

	// 有OID，在EntityManager缓存中有对象
	// JPA创建一个新的对象，将游离对象属性复制到新创建的对象中
	// EntityManager执行update操作
	@Test
	public void testMerger4() {
		Customer customer = new Customer();
		customer.setAge(25);
		customer.setBirth(new Date());
		customer.setCreatTime(new Date());
		customer.setEmail("Jack@163.com");
		customer.setLastName("Jack");
		// 执行查询操作
		customer.setId(8);
		//手动查询，因为内存里有对应对象，merger方法不执行查询
		Customer customer2 = entityManager.find(Customer.class,6);
		System.out.println("------------");
		//不执行查询操作，直接更新
		entityManager.merge(customer);
		System.out.println(customer == customer2);

	}
	
	@Test
	public void testFlush(){
		Customer customer = entityManager.find(Customer.class, 1);
		System.out.println(customer);
		
		customer.setLastName("Francis");
		//force to send the update request
		entityManager.flush();
	}
	
	@Test
	public void testRefresh(){
		Customer customer = entityManager.find(Customer.class, 1);
		System.out.println(customer);
		
		
		entityManager.refresh(customer);
		System.out.println(customer);
		
		
	}
	
	@Test
	public void testClear(){
		Customer customer = entityManager.find(Customer.class, 1);
		System.out.println(customer);
		
		customer.setLastName("Bob");
		//The record in the database has nothing changed.
		//Clear the persistence context in the JPA
		entityManager.clear();
		System.out.println(customer);
	}

}
