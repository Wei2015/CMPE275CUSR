package com.cmpe275.cusr.model;


public class SearchContent {
	
	private Station departureStation;
	private Station destinationStation;
	private String departureDate;
	private String departureTime;
	private boolean roundTrip = true;
	private boolean expressTrain = true;
	private String returnDate;
	private String returnTime;
	private int numberOfSeats;
	
	private Station[] allStations = Station.values();
	private final int[] seats = {1,2,3,4,5}; 
	
	
	public SearchContent() {
		super();
	}
	
	public Station[] getAllStations() {
		return allStations;
	}
	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public int[] getSeats() {
		return seats;
	}
	
	public Station getDepartureStation() {
		return departureStation;
	}
	public void setDepartureStation(Station departureStation) {
		this.departureStation = departureStation;
	}
	public Station getDestinationStation() {
		return destinationStation;
	}
	public void setDestinationStation(Station destinationStation) {
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
	public boolean isExpressTrain() {
			return expressTrain;
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
	//for testing purpose
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Departure Station: ");
		result.append(getDepartureStation());
		result.append("\nDestination Station: ");
		result.append(getDestinationStation());
		result.append("\nDeparture Date and Time: ");
		result.append(getDepartureDate());
		result.append(" ");
		result.append(getDepartureTime());
		if (isRoundTrip()) result.append("\nRound Trip \n");
		else result.append("\nOne Way Trip \n");
		if (isExpressTrain()) result.append("\nExpress Train Selected \n");
		else result.append("\nRegular Train Selected \n");
		return result.toString();
	}
	
}
