package com.cmpe275.cusr.model;


import java.util.ArrayList;
import java.util.List;


public class OneWayTrip implements Comparable<OneWayTrip>{
	
	private String departureDate;
	private List<Segment> connections;
	private int numberOfSeats;
	private double ticketPrice;
	private String arrivalTime;
	
	public OneWayTrip() {
		super();
	}
	public OneWayTrip(String departureDate, String arrivalTime, int numberOfSeats, double ticketPrice) {
		super();
		this.departureDate = departureDate;
		this.arrivalTime = arrivalTime;
		this.numberOfSeats = numberOfSeats;
		this.ticketPrice = ticketPrice;
		this.connections = new ArrayList<Segment>();
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
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
		//calculate ticketPrice based on segment info
		return ticketPrice;
	}
	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	@Override
	public int compareTo(OneWayTrip other) {
		return this.getArrivalTime().compareTo(other.getArrivalTime());
	
	}

}
