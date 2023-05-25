package com.example.demo;

import javax.persistence.Column;

public class Book {
	private int libraryCode;
	private String title;
	private String author;
	private String publisher;
	private String type;
	private String theme;
	private int price;
    private int year;
    private int numberOfPages;
	private int count;
	
	
	
	public Book(int libraryCode, String title, String author, int price, int count) {
		this.libraryCode = libraryCode;
		this.title = title;
		this.author = author;
		this.price = price;
		this.count = count;
	}
	
	public Book(int libraryCode, String title, String author, int price, int year, int numberOfPages) {
		this.libraryCode = libraryCode;
		this.title = title;
		this.author = author;
		this.price = price;
		this.year = year;
		this.numberOfPages = numberOfPages;
	}
	
	
	
	public Book(String author, String title, String publisher, int year, String type, int numberOfPages,String theme, int price,
			 int count) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.type = type;
		this.theme = theme;
		this.price = price;
		this.year = year;
		this.numberOfPages = numberOfPages;
		this.count = count;
	}
	


	public Book() {}
	
	public int getLibraryCode() {
		return libraryCode;
	}
	public void setLibraryCode(int libraryCode) {
		this.libraryCode = libraryCode;
	}
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getNumberOfPages() {
		return numberOfPages;
	}
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
}
