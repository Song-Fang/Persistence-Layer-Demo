package com.jpaDemo;


import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Table(name="JPA_Customer")
@Entity
public class Customer {
	
	private int id;
	private String lastName;
	private String email;
	private int age;
	private Date creatTime;
	private Date birth;
	
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Transient
	public String getInfo(){
		return "last_name"+lastName+",E-mail"+email;
	}
	
}
