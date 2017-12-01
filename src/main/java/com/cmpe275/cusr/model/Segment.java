package com.cmpe275.cusr.model;

public class Segment {
	
	private long trainId;
	private java.util.Date departureTime;
	private java.util.Date arrivalTime;
	private Station departureStop;
	private Station arrivalStop;
	
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
	public Station getDepartureStop() {
		return departureStop;
	}
	public void setDepartureStop(Station departureStop) {
		this.departureStop = departureStop;
	}
	public Station getArrivalStop() {
		return arrivalStop;
	}
	public void setArrivalStop(Station arrivalStop) {
		this.arrivalStop = arrivalStop;
	}
}
