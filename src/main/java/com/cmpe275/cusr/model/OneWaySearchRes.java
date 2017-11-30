package com.cmpe275.cusr.model;

import java.util.List;

public class OneWaySearchRes {
	
	private java.util.Calendar departureDate;
	private List<ConnectionSegment> connections;
	private Station arrivalStation;
	private java.util.Date arrivalTime;
	private int numberOfSeats;
	private double ticketPrice;
	
	public java.util.Calendar getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(java.util.Calendar departureDate) {
		this.departureDate = departureDate;
	}
	public List<ConnectionSegment> getConnections() {
		return connections;
	}
	public void setConnections(List<ConnectionSegment> connections) {
		this.connections = connections;
	}
	public Station getArrivalStation() {
		return arrivalStation;
	}
	public void setArrivalStation(Station arrivalStation) {
		this.arrivalStation = arrivalStation;
	}
	public java.util.Date getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(java.util.Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public int getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	public double getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

}
