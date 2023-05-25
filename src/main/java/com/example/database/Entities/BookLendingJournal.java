package com.example.database.Entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Entity
@Table(name = "book_lending_journal")
public class BookLendingJournal {

    @Id
    @Column(name = "inventory_number")
    private Long inventoryNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_card_number")
    private ReaderArchive readerArchive;

    @Column(name = "lending_date")
    private Date lendingDate;

	public Long getInventoryNumber() {
		return inventoryNumber;
	}

	public void setInventoryNumber(Long inventoryNumber) {
		this.inventoryNumber = inventoryNumber;
	}

	public ReaderArchive getReaderArchive() {
		return readerArchive;
	}

	public void setReaderArchive(ReaderArchive readerArchive) {
		this.readerArchive = readerArchive;
	}

	public Date getLendingDate() {
		return lendingDate;
	}

	public void setLendingDate(Date lendingDate) {
		this.lendingDate = lendingDate;
	}

	public BookLendingJournal(Long inventoryNumber, ReaderArchive readerArchive, Date lendingDate) {
		super();
		this.inventoryNumber = inventoryNumber;
		this.readerArchive = readerArchive;
		this.lendingDate = lendingDate;
	}
    
    
}
