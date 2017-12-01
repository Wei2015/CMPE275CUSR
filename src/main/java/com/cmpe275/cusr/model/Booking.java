package com.cmpe275.cusr.model;

import java.util.List;

public class Booking {
	
	private java.util.Calendar departureDate;
	private List<Long> trainId;
	private List<Station> station;
	private List<java.util.Date> departureTime;
	private Station arrivalStation;
	private java.util.Date arrivalTime;
	private java.util.Calendar returnDate;
	private List<Long> returnTrainId;
	private List<Station> returnStation;
	private List<java.util.Date> returnDepartureTime;
	private Station returnArrivalStation;
	private java.util.Date returnArrivalTime;
	private int numOfSeats;
	private double price;
	
	public java.util.Calendar getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(java.util.Calendar departureDate) {
		this.departureDate = departureDate;
	}
	public List<Long> getTrainId() {
		return trainId;
	}
	public void setTrainId(List<Long> trainId) {
		this.trainId = trainId;
	}
	public List<Station> getStation() {
		return station;
	}
	public void setStation(List<Station> station) {
		this.station = station;
	}
	public List<java.util.Date> getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(List<java.util.Date> departureTime) {
		this.departureTime = departureTime;
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
	public java.util.Calendar getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(java.util.Calendar returnDate) {
		this.returnDate = returnDate;
	}
	public List<Long> getReturnTrainId() {
		return returnTrainId;
	}
	public void setReturnTrainId(List<Long> returnTrainId) {
		this.returnTrainId = returnTrainId;
	}
	public List<Station> getReturnStation() {
		return returnStation;
	}
	public void setReturnStation(List<Station> returnStation) {
		this.returnStation = returnStation;
	}
	public List<java.util.Date> getReturnDepartureTime() {
		return returnDepartureTime;
	}
	public void setReturnDepartureTime(List<java.util.Date> returnDepartureTime) {
		this.returnDepartureTime = returnDepartureTime;
	}
	public Station getReturnArrivalStation() {
		return returnArrivalStation;
	}
	public void setReturnArrivalStation(Station returnArrivalStation) {
		this.returnArrivalStation = returnArrivalStation;
	}
	public java.util.Date getReturnArrivalTime() {
		return returnArrivalTime;
	}
	public void setReturnArrivalTime(java.util.Date returnArrivalTime) {
		this.returnArrivalTime = returnArrivalTime;
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
