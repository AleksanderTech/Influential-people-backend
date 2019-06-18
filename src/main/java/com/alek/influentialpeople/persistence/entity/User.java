package com.alek.influentialpeople.persistence.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private long id;
	@Column(unique = true, nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false, length = 20)
	private String role;
	@Column(columnDefinition = "int default '0'")
	private int activation;
	@Column(updatable = false, nullable = false)
	private Long created_at;
	@OneToMany(mappedBy="user")
	private List<Article> articles=new ArrayList<>();
	@OneToMany(mappedBy="user")
	private List<Comment> comments=new ArrayList<>();
	

	@PrePersist
	private void onCreate() {
		created_at = new Date().toInstant().getEpochSecond();
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(long id) {
		super();
		this.id = id;
	}

	public User(long id, String username, String password, String email, String role, int activation, Long created_at) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.activation = activation;
		this.created_at = created_at;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getActivation() {
		return activation;
	}

	public void setActivation(int activation) {
		this.activation = activation;
	}

	public Long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Long created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, username=%s, password=%s, email=%s, role=%s, activation=%s, created_at=%s]",
				id, username, password, email, role, activation, created_at);
	}

}
