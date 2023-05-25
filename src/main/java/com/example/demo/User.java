package com.example.demo;

public class User {
	private int id;
	private String name;
	private String surname;
	private String adress;
	private String phone;
	private String job;
	
	public User(int id, String  name, String surname, String adress, String phone, String job) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.adress = adress;

		this.phone = phone;
		this.job = job;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
}
