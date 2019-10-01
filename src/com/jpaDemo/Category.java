package com.jpaDemo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name="JPA_Category")
@Entity
public class Category {
	private int ID;
	private String categoryName;
	private Set<Items> items = new HashSet<Items>();
	
	@ManyToMany(mappedBy="category")
	public Set<Items> getItems() {
		return items;
	}
	public void setItems(Set<Items> items) {
		this.items = items;
	}
	
	@GeneratedValue
	@Id
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	@Column(name="Category_Name")
	public String getCategoryName() {
		return categoryName;
	}
	
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
