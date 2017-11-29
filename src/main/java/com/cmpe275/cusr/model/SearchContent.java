package com.cmpe275.cusr.model;

public class SearchContent {
	private Character departureStation;
	private Character destinationStation;
	private String departureDate;
	private String departureTime;
	private boolean roundTrip;
	private String returnDate;
	private String returnTime;

	public static final char[] ALL_STATIONS = 
		{ 'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	public SearchContent() {
		super();
	}
	
	public Character getDepartureStation() {
		return departureStation;
	}
	public void setDepartureStation(Character departureStation) {
		this.departureStation = departureStation;
	}
	public Character getDestinationStation() {
		return destinationStation;
	}
	public void setDestinationStation(Character destinationStation) {
		this.destinationStation = destinationStation;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public boolean isRoundTrip() {
		return roundTrip;
	}
	public void setRoundTrip(boolean roundTrip) {
		this.roundTrip = roundTrip;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	
}
