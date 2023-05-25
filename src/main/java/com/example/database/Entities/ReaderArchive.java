package com.example.database.Entities;

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
@Table(name = "reader_archive")
@SequenceGenerator(name = "reader_archive_seq", sequenceName = "reader_archive_seq", allocationSize = 1)
public class ReaderArchive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "reader_archive_seq")
    @Column(name = "reader_card_number")
    private Integer readerCardNumber;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "activity_type")
    private String activityType;

	public Integer getReaderCardNumber() {
		return readerCardNumber;
	}

	public void setReaderCardNumber(Integer readerCardNumber) {
		this.readerCardNumber = readerCardNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
}