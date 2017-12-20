package com.cmpe275.cusr.model;

public class SystemStat {
	
	private String numberOfConnections;
	
	private String rate;
	
	private long timeCost;

	public SystemStat(String numberOfConnections, String rate, long timeCost) {
		super();
		this.numberOfConnections = numberOfConnections;
		this.rate = rate;
		this.timeCost = timeCost;
	}

	public String getNumberOfConnections() {
		return numberOfConnections;
	}

	public void setNumberOfConnections(String numberOfConnections) {
		this.numberOfConnections = numberOfConnections;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public long getTimeCost() {
		return timeCost;
	}

	public void setTimeCost(long timeCost) {
		this.timeCost = timeCost;
	}
	
	

}
