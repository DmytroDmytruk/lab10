package com.example.database.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book_fund")
public class BookFund {

	public BookFund() {
		
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "inventory_number")
    private Integer inventoryNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_code")
    private Catalog catalog;

    @Column(name = "status")
    private String status;

	public BookFund(Catalog catalog, String status) {
		super();

		this.catalog = catalog;
		this.status = status;
	}

	public Integer getInventoryNumber() {
		return inventoryNumber;
	}

	public void setInventoryNumber(Integer inventoryNumber) {
		this.inventoryNumber = inventoryNumber;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}