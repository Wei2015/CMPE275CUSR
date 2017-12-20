package com.cmpe275.cusr.model;


public class SearchContent {
	
	private Station departureStation;
	private Station destinationStation;
	private String departureDate;
	private String departureTime;
	private boolean roundTrip;
	private boolean exactTime;
	private String trainType;
	private String returnDate;
	private String returnTime;
	private int numberOfSeats;
	private String numberOfConnections;
	
	private Station[] allStations = Station.values();
	private static final int[] seats = {1,2,3,4,5}; 
	public static final String[] connectionOptions= {"Any", "None", "One"};
	private static final String[] trainTypeOptions= {"Any", "Regular","Express"};
	

	
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
	

	public String getNumberOfConnections() {
		return numberOfConnections;
	}

	public void setNumberOfConnections(String numberOfConnections) {
		this.numberOfConnections = numberOfConnections;
	}

	public int[] getSeats() {
		return seats;
	}
	
	public String[] getConnectionOptions() {
		return connectionOptions;
	}
	
	public String[] getTrainTypeOptions() {
		return trainTypeOptions;
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

	public void setRoundTrip(boolean roundTrip) {
		this.roundTrip = roundTrip;
	}
	
	public boolean isExactTime() {
		return exactTime;
	}
	public void setExactTime(boolean exactTime) {
		this.exactTime = exactTime;
	}
	
	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
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
		if (isRoundTrip()) result.append("\nRound Trip");
		else result.append("\nOne Way Trip");
		result.append("\nTrain Type Selected: " + getTrainType());
		result.append("\nNumber of seats selected: " + getNumberOfSeats());
		result.append("\nNumber of Connection: " + getNumberOfConnections());
		return result.toString();
	}
	
	public SearchContent getReturnSearch() {
		SearchContent returnSearch = new SearchContent();
		returnSearch.setDepartureDate(this.returnDate);
		returnSearch.setDepartureStation(this.destinationStation);
		returnSearch.setDepartureTime(this.returnTime);
		returnSearch.setDestinationStation(this.departureStation);
		returnSearch.setExactTime(false);
		returnSearch.setNumberOfSeats(this.numberOfSeats);
		returnSearch.setRoundTrip(false);
		returnSearch.setNumberOfConnections(this.numberOfConnections);
		returnSearch.setTrainType(this.trainType);
		
		return returnSearch;
	}
	
	
}
