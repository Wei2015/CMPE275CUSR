package com.cmpe275.cusr.model;

public class Segment {
	
	private long trainId;
	
	private java.util.Date departureTime;
	
	private java.util.Date arrivalTime;
	
	private Station departureStation;
	
	private Station arrivalStation;
	
	public long getTrainId() {
		return trainId;
	}
	public void setTrainId(long trainId) {
		this.trainId = trainId;
	}
	public java.util.Date getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(java.util.Date departureTime) {
		this.departureTime = departureTime;
	}
	public java.util.Date getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(java.util.Date arrivalTime) {
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
