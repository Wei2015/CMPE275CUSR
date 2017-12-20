package com.cmpe275.cusr.service;

import java.util.List;

import com.cmpe275.cusr.model.Train;

public interface AdminService {
	
	public void populateTrainTable();
	
	public void populateTrainStatus();
	
	public void trainCancel (String trainName, String date);
	
	public void updateTrainCapacity(int capacity);
	
	public void reset();
	
	public List<Train> showTrainCapacity();
	
	
}
