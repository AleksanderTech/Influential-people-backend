package com.alek.influentialpeople.persistance.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	
	@Id
	private int id;
	
	private String name;

	@OneToMany(fetch = FetchType.LAZY)
	private Set<Comment> set;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(String name, Set<Comment> set) {
		super();
		this.name = name;
		this.set = set;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Comment> getSet() {
		return set;
	}

	public void setSet(Set<Comment> set) {
		this.set = set;
	}

}
