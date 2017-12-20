package com.cmpe275.cusr.model;

import java.sql.Date;

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
	
	@Column(name="TimeBeforeReq")
	private Date timeBeforeReq;
	
	@Column(name="TimeAfterReq")
	private Date timeAfterReq;
	
	@Column(name="TimeCost")
	private long timeCost;


	public Request() {
		super();
	}
	
	public void setTimeCost(long timeCost) {
		this.timeCost = timeCost;
	}

	public long getTimeCost() {
		return timeAfterReq.getTime()-timeBeforeReq.getTime();
	}

	public String getNumberOfConnections() {
		return numberOfConnections;
	}

	public void setNumberOfConnections(String numberOfConnections) {
		this.numberOfConnections = numberOfConnections;
	}

	public Date getTimeBeforeReq() {
		return timeBeforeReq;
	}

	public void setTimeBeforeReq(Date timeBeforeReq) {
		this.timeBeforeReq = timeBeforeReq;
	}

	public Date getTimeAfterReq() {
		return timeAfterReq;
	}

	public void setTimeAfterReq(Date timeAfterReq) {
		this.timeAfterReq = timeAfterReq;
	}
	
	
	
	
	
	

}
