package com.cmpe275.cusr.model;

import java.text.SimpleDateFormat;
import java.util.Date;



public class Segment {
	
	private String bound;
	private Date departureTime;
	private Date arrivalTime;
	private Station departureStation;
	private Station arrivalStation;
	
	private SimpleDateFormat convertTime = new SimpleDateFormat("HH:mm:ss");
	
	public Segment() {
		super();
	}
	
	public Segment(String bound, Date departureTime, Date arrivalTime, Station departureStation, Station arrivalStation) {
		super();
		this.bound = bound;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.departureStation = departureStation;
		this.arrivalStation = arrivalStation;
	}


	
	public String getBound() {
		return bound;
	}

	public void setBound(String bound) {
		this.bound = bound;
	}

	public String getDepartureTime() {
		return convertTime.format(departureTime);
	}
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalTime() {
		return convertTime.format(arrivalTime);
	}
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Station getDepartureStation() {
		return departureStation;
	}
	public void setDepartureStation(Station departureStation) {
		this.departureStation = departureStation;
	}
	public Station getArrivalStation() {
		return arrivalStation;
	}
	public void setArrivalStation(Station arrivalStation) {
		this.arrivalStation = arrivalStation;
	}
}
