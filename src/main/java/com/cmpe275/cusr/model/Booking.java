package com.cmpe275.cusr.model;

import java.util.List;

public class Booking {
	
	private String departureDate;
	private List<Segment> departureTrip;
	private String returnDate;
	private List<Segment> returnTrip;
	private int numOfSeats;
	private List<String> passenger;
	private double price;
	
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public List<Segment> getDepartureTrip() {
		return departureTrip;
	}
	public void setDepartureTrip(List<Segment> departureTrip) {
		this.departureTrip = departureTrip;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
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
	
	public List<String> getPassenger() {
		return passenger;
	}
	public void setPassenger(List<String> passenger) {
		this.passenger = passenger;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
