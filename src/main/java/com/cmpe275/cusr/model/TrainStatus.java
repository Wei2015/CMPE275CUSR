package com.cmpe275.cusr.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.Table;

@Entity
@Table(name="TRAIN_STATUS")
public class TrainStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="STATUS_ID")
	private long statusId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn (name ="TRAIN_ID")
	private Train train;
	
	@Column (name = "DATE")
	private String date;
	
	@ElementCollection
	@CollectionTable(name="SEAT_STATUS")
	@MapKeyEnumerated(EnumType.STRING)
	@MapKeyColumn(name="STATION", nullable=false)
	@Column(name="USED_SEATS")
	private Map<Station, Integer> seatStatus;
	
	@Column (name="IS_CANCELLED")
	private boolean isCancelled;
	
	

	public TrainStatus() {
		super();
	}

	public TrainStatus(Train train, String date, boolean isCancelled) {
		super();
		this.train = train;
		this.date = date;
		this.seatStatus = new HashMap<Station, Integer>();
		this.isCancelled = isCancelled;
	}

	public long getStatusId() {
		return statusId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Map<Station, Integer> getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(Map<Station, Integer> seatStatus) {
		this.seatStatus = seatStatus;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
}