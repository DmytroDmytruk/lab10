package com.example.database.Entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "catalog")
@SequenceGenerator(name = "catalog_seq", sequenceName = "catalog_seq", allocationSize = 1)
public class Catalog {

	public Catalog() {
		
	}

	   	    
    public Catalog(String author, String title, String publisher, Integer year, String type, Integer numberOfPages,
			String theme, Integer price) {
		this.author = author;
		this.title = title;
		this.publisher = publisher;
		this.year = year;
		this.type = type;
		this.numberOfPages = numberOfPages;
		this.theme = theme;
		this.price = price;
	}


	public int getLibraryCode() {
		return libraryCode;
	}

	public void setLibraryCode(int libraryCode) {
		this.libraryCode = libraryCode;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catalog_seq")
    @Column(name = "library_code")
    private int libraryCode;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getNumberOfPages() {
		return numberOfPages;
	}
	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	@Column(name = "publisher")
    private String publisher;

    @Column(name = "year")
    private Integer year;

    @Column(name = "type")
    private String type;

    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    @Column(name = "theme")
    private String theme;

    @Column(name = "price")
    private Integer price;
}