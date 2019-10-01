package com.jpaDemo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name="JPA_Items")
@Entity
public class Items {
	private int ID;
	private String itemsName;
	private Set<Category> category = new HashSet<Category>();
	
	@JoinTable(name = "Item_Category",joinColumns={@JoinColumn(name="Item_ID",referencedColumnName="ID")},
			inverseJoinColumns={@JoinColumn(name="Category_ID",referencedColumnName="ID")})
	@ManyToMany
	public Set<Category> getCategory() {
		return category;
	}
	public void setCategory(Set<Category> category) {
		this.category = category;
	}
	
	@GeneratedValue
	@Id
	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	@Column(name="Items_Name")
	public String getItemsName() {
		return itemsName;
	}
	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}
	

	
}
