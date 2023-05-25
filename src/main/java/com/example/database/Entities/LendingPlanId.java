package com.example.database.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class LendingPlanId implements Serializable {
    @Column(name = "inventory_number")
    private Integer inventoryNumber;

	public Integer getInventoryNumber() {
		return inventoryNumber;
	}

	public void setInventoryNumber(Integer inventoryNumber) {
		this.inventoryNumber = inventoryNumber;
	}
    
}
