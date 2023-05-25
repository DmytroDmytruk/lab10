package com.example.database.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class LendingPlanId implements Serializable {
    @Column(name = "inventory_number")
    private Long inventoryNumber;

	public Long getInventoryNumber() {
		return inventoryNumber;
	}

	public void setInventoryNumber(Long inventoryNumber) {
		this.inventoryNumber = inventoryNumber;
	}
    
}
