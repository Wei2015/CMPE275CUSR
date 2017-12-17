package com.cmpe275.cusr.model;

import java.util.ArrayList;
import java.util.List;

public class AllTrainContent {
	
	private List<Train> trains;
	
	private List<String> trainBounds;


	public AllTrainContent() {
		super();
		trains = new ArrayList<Train>();
		this.trainBounds = new ArrayList<String>();
	}

	public AllTrainContent(List<Train> trains) {
		super();
		this.trains = trains;
		this.trainBounds = new ArrayList<String>();
		for (Train t : trains) {
			this.trainBounds.add(t.getBound());
		}
	}
	public List<Train> getTrains() {
		return trains;
	}

	public void setTrains(List<Train> trains) {
		this.trains = trains;
	}

	public List<String> getTrainBounds() {
		if (trainBounds.size()==0 && trains.size()>0) {
			for (Train t : trains) 
				this.trainBounds.add(t.getBound());
		}
		return trainBounds;
	}

	public void setTrainBounds(List<String> trainBounds) {
		this.trainBounds = trainBounds;
	}
	
	
	

}
