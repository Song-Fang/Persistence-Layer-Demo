package com.jpaDemo;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@NamedQuery(name="testNameQuery",query="SELECT c FROM Customer c WHERE c.id =15")
@Table(name="JPA_Customer")
@Entity
public class Customer {
	
	private int id;
	private String lastName;
	private String email;
	private int age;
	private Date creatTime;
	private Date birth;
	private Set<Order> order = new HashSet<>();
	
	@JoinColumn(name="CUSTOMER_ID")
	@OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.REMOVE})
	public Set<Order> getOrder() {
		return order;
	}

	public Customer(String lastName, int age) {
		super();
		this.lastName = lastName;
		this.age = age;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}

	public Date getCreatTime() {
		return creatTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	@Temporal(TemporalType.DATE)
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="last_name",nullable=false,length=50)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Basic
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Basic
	public int getAge() {
		return age;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", lastName=" + lastName + ", email=" + email + ", age=" + age + ", creatTime="
				+ creatTime + ", birth=" + birth + "]";
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Transient
	public String getInfo(){
		return "last_name"+lastName+",E-mail"+email;
	}
	
}
