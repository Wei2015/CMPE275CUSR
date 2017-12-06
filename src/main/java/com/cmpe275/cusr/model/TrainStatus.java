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
	
	

}