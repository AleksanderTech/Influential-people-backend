package com.alek.influentialpeople.persistance.entity;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private long id;
	@NotNull
	@Column(unique = true, nullable = false)
	private String nickname;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(columnDefinition = "int default '0'")
	private int activation;
	@Column(nullable = false)
	private String role;
	@Column(updatable = false)
	private Long created_at;

	@PrePersist
	private void onCreate() {
		created_at = ZonedDateTime.now().toEpochSecond();
	}

	public User() {
	}

	public User(Long id, String nickname, String email, String password, int activation, String role, long created_at) {
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.activation = activation;
		this.role = role;
		this.created_at = created_at;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getActivation() {
		return activation;
	}

	public void setActivation(int activation) {
		this.activation = activation;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Long created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, nickname=%s, email=%s, password=%s, activation=%s, role=%s, created_at=%s]",
				id, nickname, email, password, activation, role, created_at);
	}

}
