package com.alek.influentialpeople.persistence.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class QuoteComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private long id;
	@Column(nullable = false,columnDefinition="TEXT")
	private String content;
	@Column(updatable = false, nullable = false)
	private Long created_at;
	
	
}
