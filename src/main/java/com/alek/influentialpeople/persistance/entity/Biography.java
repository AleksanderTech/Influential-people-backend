package com.alek.influentialpeople.persistance.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Biography {
	@Id
	private long id;
	private String content;
	@OneToOne
	private Person person;
	@OneToOne
	private User user;

	public Biography(long id, String content, Person person, User user) {
		this.id = id;
		this.content = content;
		this.person = person;
		this.user = user;
	}
//	private String getDateTime() {
//        SimpleDateFormat dateFormat = new SimpleDateFormat(
//                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//        Date date = new Date();
//        return dateFormat.format(date);
//}
	public Biography() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return String.format("Biography [id=%s, content=%s, person=%s, user=%s]", id, content, person, user);
	}
}
