package com.cmpe275.cusr.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class OneWayTrip {
	
	private Date departureDate;
	private List<Segment> connections;
	private int numberOfSeats;
	private double ticketPrice;
	private Date arrivalTime;
	
	private SimpleDateFormat convertDate = new SimpleDateFormat("MM/dd/yyyy");
	private SimpleDateFormat convertTime = new SimpleDateFormat("HH:mm:ss");

	public OneWayTrip() {
		super();
	}
	public OneWayTrip(Date departureDate, Date arrivalTime, int numberOfSeats, double ticketPrice) {
		super();
		this.departureDate = departureDate;
		this.arrivalTime = arrivalTime;
		this.numberOfSeats = numberOfSeats;
		this.ticketPrice = ticketPrice;
	}
	public String getDepartureDate() {
		return convertDate.format(departureDate);
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
	public String getArrivalTime() {
		return convertTime.format(arrivalTime);
	}
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

}
