package com.cmpe275.cusr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TRAIN_STATUS")
public class TrainStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="STATUS_ID")
	private long statusId;
	
	@Column (name = "DATE")
	private String date;
	
	@Column (name = "USED_SEATS")
	private int usedSeats;
	
	@Column (name="IS_CANCELLED")
	private boolean isCancelled;

	public long getStatusId() {
		return statusId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getUsedSeats() {
		return usedSeats;
	}

	public void setUsedSeats(int usedSeats) {
		this.usedSeats = usedSeats;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}	
}