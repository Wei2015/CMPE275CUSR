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
	public OneWayTrip(String departureDate, int numberOfSeats) {
		super();
		this.departureDate = departureDate;
		this.numberOfSeats = numberOfSeats;
		this.ticketPrice = 0.0;
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
		ticketPrice = 0.0;
		for (Segment s:connections) {
			ticketPrice += s.getPrice();
		}
		return ticketPrice * numberOfSeats;
	}
	
	public String getArrivalTime() {
		arrivalTime = connections.get(connections.size()-1).getArrivalTime();
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	@Override
	public int compareTo(OneWayTrip other) {
		int result = this.getArrivalTime().compareTo(other.getArrivalTime());
		if (result != 0) {
			return result;
		}else {
			return other.getConnections().get(0).getDepartureTime().compareTo(this.getConnections().get(0).getDepartureTime());
		}
	
	}

}
