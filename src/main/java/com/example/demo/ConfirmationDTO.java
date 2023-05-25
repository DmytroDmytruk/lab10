package com.example.demo;

import java.util.Date;

public class ConfirmationDTO {
	private String title;
	private String author;
	private String name;
	private Date currDate;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCurrDate() {
		return currDate;
	}
	public void setCurrDate(Date currDate) {
		this.currDate = currDate;
	}
	public ConfirmationDTO(String title, String author, String name, Date currDate) {
		super();
		this.title = title;
		this.author = author;
		this.name = name;
		this.currDate = currDate;
	}
	
}
