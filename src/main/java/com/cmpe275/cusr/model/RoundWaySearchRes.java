package com.cmpe275.cusr.model;

public class RoundWaySearchRes {

	private OneWaySearchRes firstTrip;
	private OneWaySearchRes returnTrip;
	
	
	public double getTicketPrice() {
		return firstTrip.getTicketPrice() + returnTrip.getTicketPrice();
	}

	public int getNumberOfSeats() {
		return firstTrip.getNumberOfSeats();
	}
	
	
}
