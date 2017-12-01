package com.cmpe275.cusr.model;

import java.util.List;

public class OneWaySearchRes {
	
	private java.util.Calendar departureDate;
	private List<Segment> connections;
	private int numberOfSeats;
	private double ticketPrice;
	
	public java.util.Calendar getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(java.util.Calendar departureDate) {
		this.departureDate = departureDate;
	}
	public List<Segment> getConnections() {
		return connections;
	}
	public void setConnections(List<Segment> connections) {
		this.connections = connections;
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
