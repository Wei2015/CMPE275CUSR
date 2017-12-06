package com.cmpe275.cusr.model;

public class Segment implements Comparable<Segment>{
	
	private long trainId;
	private String bound;
	private String departureTime;
	private String arrivalTime;
	private Station departureStation;
	private Station arrivalStation;
	
	
	public Segment() {
		super();
	}
	
	public Segment(String bound, String departureTime, String arrivalTime, Station departureStation, Station arrivalStation) {
		super();
		this.bound = bound;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.departureStation = departureStation;
		this.arrivalStation = arrivalStation;
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

	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
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
	
	@Override
	public int compareTo(Segment other) {
		return this.getArrivalTime().compareTo(other.getArrivalTime());
	
	}
}
