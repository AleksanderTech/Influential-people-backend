package com.alek.influentialpeople.persistance.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class Hero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private int id;
	@Column(unique = true, nullable = false)
	private String fullName;
	@Column(updatable = false, nullable = false)
	private Long created_at;

	@PrePersist
	private void onCreate() {
		created_at = new Date().toInstant().getEpochSecond();
	}

	public Hero() {
	}

	public Hero(int id, String fullName, Long created_at) {
		this.id = id;
		this.fullName = fullName;
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return String.format("Hero [id=%s, fullName=%s, created_at=%s]", id, fullName, created_at);
	}

}