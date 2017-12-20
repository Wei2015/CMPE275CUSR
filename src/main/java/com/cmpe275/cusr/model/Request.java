package com.cmpe275.cusr.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REQUEST")
public class Request {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="REQUEST_ID")
	private long requestId;
	
	@Column(name="numberOfConnections")
	private String numberOfConnections;
	
	@Column(name="Date")
	private String date;
	
	
	@Column(name="TimeCost")
	private long timeCost;


	public Request() {
		super();
	}
	
	public Request(String numberOfConnections, String date, long timeCost) {
		super();
		this.numberOfConnections = numberOfConnections;
		this.date = date;
		this.timeCost = timeCost;
	}

	public void setTimeCost(long timeCost) {
		this.timeCost = timeCost;
	}

	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getTimeCost() {
		return timeCost;
	}

	public String getNumberOfConnections() {
		return numberOfConnections;
	}

	public void setNumberOfConnections(String numberOfConnections) {
		this.numberOfConnections = numberOfConnections;
	}


	
	
	
	
	
	

}
