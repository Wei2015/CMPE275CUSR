package com.cmpe275.cusr.model;

import java.util.Date;
import java.util.List;

public class Booking {
	
	private Date departureDate;
	private List<Segment> departureTrip;
	private Date returnDate;
	private List<Segment> returnTrip;
	private int numOfSeats;
	private double price;
	
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public List<Segment> getDepartureTrip() {
		return departureTrip;
	}
	public void setDepartureTrip(List<Segment> departureTrip) {
		this.departureTrip = departureTrip;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
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
