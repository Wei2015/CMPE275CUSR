package com.cmpe275.cusr.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.cusr.model.Station;
import com.cmpe275.cusr.model.Train;
import com.cmpe275.cusr.repository.TrainRepository;



@Service
public class AdminServiceImpl implements AdminService{
	
	private static final String EXPRESS = "EXPRESS";
	private static final String REGULAR = "REGULAR";
	
	@Autowired
	private TrainRepository trainRepo;
	
	public void populateTrainTable() {
		//Adding Express Train Info
		addExpressTrain("SB");
		addExpressTrain("NB");

		//Adding Regular Train Info
		addRegularTrain("SB");
		addRegularTrain("NB");
	}
	
	private void addRegularTrain(String direction) {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		for (int i = 6; i <= 21; i++) {
			for (int m = 15; m < 60; m+=15) {
				String bound;
				Date departTime = new Date();
				//adding bound
				if (i<10 ) {
					bound = direction + "0" + String.valueOf(i) + String.valueOf(m);
				}else {
					bound = direction + String.valueOf(i) + String.valueOf(m);
				}
				//adding station departure time
				Map<Station, Date> trainTimeTable = new HashMap<>();
				String departTimeString = String.valueOf(i) + ":" + String.valueOf(m) +":00";
				try {
					departTime = timeFormat.parse(departTimeString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			
				Station[] stations = Station.values();
				if (direction.equals("SB")) {
					for (int j = 0; j<stations.length-1; j++) {
						Date updatedTime = (Date)departTime.clone();
						updatedTime.setTime(departTime.getTime() + 8*60*1000*j);
						trainTimeTable.put(stations[j], updatedTime);
					}
				} else {
					for (int j = stations.length-1; j>0 ; j--) {
						Date updatedTime = (Date)departTime.clone();
						updatedTime.setTime(departTime.getTime() + 8*60*1000*(stations.length-1-j));
						trainTimeTable.put(stations[j], updatedTime);
					}
				}
			
				Train regular = new Train(bound, departTime, REGULAR, trainTimeTable);
				trainRepo.save(regular);
			
			}
		}
	}

	private void addExpressTrain(String direction) {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		for (int i = 6; i <= 21; i++) {
			String bound;
			Date departTime = new Date();
			//adding bound
			if (i<10 ) {
				bound = direction + "0" + String.valueOf(i) + "00";
			}else {
				bound = direction + String.valueOf(i) + "00";
			}
			//adding station departure time
			Map<Station, Date> trainTimeTable = new HashMap<>();
			String departTimeString = String.valueOf(i) +":00:00";
			try {
				departTime = timeFormat.parse(departTimeString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			Station[] stations = Station.values();
			if (direction.equals("SB")) {
				for (int j = 0; j<stations.length-1; j+=5) {
					Date updatedTime = (Date)departTime.clone();
					updatedTime.setTime(departTime.getTime() + 28*60*1000*(j/5));
					trainTimeTable.put(stations[j], updatedTime);
				}
			} else {
				for (int j = stations.length-1; j>0 ; j-=5) {
					Date updatedTime = (Date)departTime.clone();
					updatedTime.setTime(departTime.getTime() + 28*60*1000*((stations.length-1-j)/5));
					trainTimeTable.put(stations[j], updatedTime);
				}
			}
			
			Train express = new Train(bound, departTime, EXPRESS, trainTimeTable);
			trainRepo.save(express);
			
		}
	
	}
}
