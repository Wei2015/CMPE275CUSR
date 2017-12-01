package com.cmpe275.cusr.model;


import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.EnumType;




@Entity
@Table(name="TRAIN")
public class Train {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TRAIN_ID")
	private long trainId;
	
	@Column(name="BOUND", nullable=false)
	private String bound;
	
	@Column(name="DEPARTURE_TIME", nullable=false)
	@Temporal(TemporalType.TIME)
	private java.util.Date departureTime;
	
	@Column(name="TYPE", nullable=false)
	private String type;
	
	@Column(name="CAPACITY")
	private int capacity;
	
	@ElementCollection
	@CollectionTable(name="TRAIN_SCHEDULE")
	@MapKeyEnumerated(EnumType.STRING)
	@MapKeyColumn(name="STATION")
	@Column(name="DEPART_TIME_AT_STATION")
	@Temporal(TemporalType.TIME)
	private Map<Station, java.util.Date> trainTimeTable;
	
	@ElementCollection
	@CollectionTable(name="TRAIN_STATUS")
	@MapKeyJoinColumn(name="DATE", nullable=false)
	@Column(name="USED_SEATS")
	private Map<java.util.Calendar, Integer> trainStatus;
	
	public Train() {
		super();
	}

	public long getTrainId() {
		return trainId;
	}

	public String getBound() {
		return bound;
	}

	public void setBound(String bound) {
		this.bound = bound;
	}

	public java.util.Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(java.util.Date departureTime) {
		this.departureTime = departureTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Map<Station, java.util.Date> getTrainTimeTable() {
		return trainTimeTable;
	}

	public void setTrainTimeTable(Map<Station, java.util.Date> trainTimeTable) {
		this.trainTimeTable = trainTimeTable;
	}

	public Map<java.util.Calendar, Integer> getTrainStatus() {
		return trainStatus;
	}

	public void setTrainStatus(Map<java.util.Calendar, Integer> trainStatus) {
		this.trainStatus = trainStatus;
	}

}

