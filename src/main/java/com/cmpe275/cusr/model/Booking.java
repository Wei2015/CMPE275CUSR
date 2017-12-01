package com.cmpe275.cusr.model;

import java.util.List;

public class Booking {
	
	private java.util.Calendar departDate;
	private List<Long> trainId;
	private List<Station> station;
	private List<java.util.Date> departTime;
	private Station arrivalStation;
	private java.util.Date arrivalTime;
	private java.util.Calendar returnDate;
	private List<Long> returnTrainId;
	private List<Station> returnStation;
	private List<java.util.Date> returnDepartTime;
	private Station returnArrivalStation;
	private java.util.Date returnArrivalTime;
	private int numOfSeats;
	private double price;
	
	public java.util.Calendar getDepartDate() {
		return departDate;
	}
	public void setDepartDate(java.util.Calendar departDate) {
		this.departDate = departDate;
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
	public List<java.util.Date> getDepartTime() {
		return departTime;
	}
	public void setDepartTime(List<java.util.Date> departTime) {
		this.departTime = departTime;
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
	public List<java.util.Date> getReturnDepartTime() {
		return returnDepartTime;
	}
	public void setReturnDepartTime(List<java.util.Date> returnDepartTime) {
		this.returnDepartTime = returnDepartTime;
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
