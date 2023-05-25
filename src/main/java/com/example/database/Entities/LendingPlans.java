package com.example.database.Entities;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "lending_plans")
@SequenceGenerator(name = "lending_plans_seq", sequenceName = "lending_plans_seq", allocationSize = 1)
public class LendingPlans {

    @EmbeddedId
    private LendingPlanId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("inventoryNumber")
    @JoinColumn(name = "inventory_number")
    private BookLendingJournal bookLendingJournal;

    @Column(name = "planned_return_date")
    private Date plannedReturnDate;

	public LendingPlanId getId() {
		return id;
	}
	public void setId(LendingPlanId id) {
		this.id = id;
	}
	public BookLendingJournal getBookLendingJournal() {
		return bookLendingJournal;
	}
	public void setBookLendingJournal(BookLendingJournal bookLendingJournal) {
		this.bookLendingJournal = bookLendingJournal;
	}
	public Date getPlannedReturnDate() {
		return plannedReturnDate;
	}
	public void setPlannedReturnDate(Date plannedReturnDate) {
		this.plannedReturnDate = plannedReturnDate;
	} 
}