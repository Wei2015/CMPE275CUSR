package com.cmpe275.cusr.model;

import java.util.List;

public class Booking {
	
	private java.util.Calendar departureDate;
	private List<Segment> departureTrip;
	private java.util.Calendar returnDate;
	private List<Segment> returnTrip;
	private int numOfSeats;
	private double price;
	
	public java.util.Calendar getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(java.util.Calendar departureDate) {
		this.departureDate = departureDate;
	}
	public List<Segment> getDepartureTrip() {
		return departureTrip;
	}
	public void setDepartureTrip(List<Segment> departureTrip) {
		this.departureTrip = departureTrip;
	}
	public java.util.Calendar getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(java.util.Calendar returnDate) {
		this.returnDate = returnDate;
	}
	public List<Segment> getReturnTrip() {
		return returnTrip;
	}
	public void setReturnTrip(List<Segment> returnTrip) {
		this.returnTrip = returnTrip;
	}
	public int getNumOfSeats() {
		return numOfSeats;
	}
	public void setNumOfSeats(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
