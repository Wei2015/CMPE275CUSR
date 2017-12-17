package com.cmpe275.cusr.model;

import java.util.List;

public class AllTrainContent {
	
	private List<Train> trains;


	public AllTrainContent() {
		super();
	}

	public AllTrainContent(List<Train> trains) {
		super();
		this.trains = trains;
	}
	public List<Train> getTrains() {
		return trains;
	}

	public void setTrains(List<Train> trains) {
		this.trains = trains;
	}
	
	

}
