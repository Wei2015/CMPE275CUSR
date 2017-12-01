package com.cmpe275.cusr.model;

import java.util.Date;
import java.util.List;

public class OneWaySearchRes {
	
	private Date departureDate;
	private List<Segment> connections;
	private int numberOfSeats;
	private double ticketPrice;
	
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
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
