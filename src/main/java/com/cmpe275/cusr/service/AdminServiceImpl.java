package com.cmpe275.cusr.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.cusr.model.Station;
import com.cmpe275.cusr.model.Train;
import com.cmpe275.cusr.model.TrainSchedule;
import com.cmpe275.cusr.model.TrainStatus;
import com.cmpe275.cusr.repository.ScheduleRepository;
import com.cmpe275.cusr.repository.TrainRepository;
import com.cmpe275.cusr.repository.TrainStatusRepository;


@Service
public class AdminServiceImpl implements AdminService {
	private static final String EXPRESS = "EXPRESS";
	private static final String REGULAR = "REGULAR";
	private static final int TIMEFRAME = 28;
	private static String datePattern = "yyyy-MM-dd";
	
	@Autowired
	private TrainRepository trainRepo;
	
	@Autowired
	private ScheduleRepository scheduleRepo;
	
	@Autowired
	private TrainStatusRepository trainStatusRepo;
	
	//populate train status information
	public void populateTrainStatus() {
		if (!(trainRepo.count()>0))
			populateTrainTable();
		
		trainStatusRepo.deleteAll();
		List<Train> allTrains = trainRepo.findAll();
		
		SimpleDateFormat format = new SimpleDateFormat(datePattern);
		
		for (Train t: allTrains) {
			for (int i = 0; i < TIMEFRAME; i++) {
				Date current = new Date();
				current.setTime(current.getTime()+1000*60*60*24*i);
				String currentDate= format.format(current);
				TrainStatus newStatus = new TrainStatus(t, currentDate, false);
				Map<Station, Integer> map = newStatus.getSeatStatus();
				Station[] stations = Station.values();
				if (t.getBound().endsWith("00")) {
					for (int j = 0; j < stations.length; j+=5) {
						map.put(stations[j], 0);
					}
				} else {
					for (int j= 0; j< stations.length; j++) {
						map.put(stations[j], 0);
					}
				}
				newStatus.setSeatStatus(map);
				trainStatusRepo.save(newStatus);
			}
		}
	}
	
	
	//populate train schedule information
	public void populateTrainTable() {
		
		trainRepo.deleteAll();
		scheduleRepo.deleteAll();
		
		//Adding Express Train Info
		addExpressTrain("SB");
		addExpressTrain("NB");

		//Adding Regular Train Info
		addRegularTrain("SB");
		addRegularTrain("NB");
	}
	
	private void addRegularTrain(String direction) {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		for (int i = 6; i < 21; i++) {
			for (int m = 15; m < 60; m+=15) {
				String bound;
				//generate bound content
				if (i<10 ) {
					bound = direction + "0" + String.valueOf(i) + String.valueOf(m);
				}else {
					bound = direction + String.valueOf(i) + String.valueOf(m);
				}
				
				//adding departure time at start station
				String departTimeString = String.valueOf(i) + ":" + String.valueOf(m) +":00";
				//add a new Train to TRAIN table
				Train regular = new Train(bound, departTimeString, REGULAR);
				trainRepo.save(regular);
				
				//generate time schedule at other stations
				Date departTime = new Date();
				try {
					departTime = timeFormat.parse(departTimeString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Date updatedTime = (Date)departTime.clone();
				Station[] stations = Station.values();
				if (direction.equals("SB")) {
					for (int j = 0; j<stations.length-1; j++) {
						updatedTime.setTime(departTime.getTime() + 8*60*1000*j);
						TrainSchedule oneSchedule = new TrainSchedule(stations[j], timeFormat.format(updatedTime), regular);
						updatedTime.setTime(updatedTime.getTime() - 3*60 *1000); //calculate arrival time
						oneSchedule.setArrivalTime(timeFormat.format(updatedTime)); //add arrival time to schedule
						scheduleRepo.save(oneSchedule);
					}
					TrainSchedule lastOne = new TrainSchedule(stations[stations.length-1], "", regular);
					updatedTime.setTime(updatedTime.getTime() + 8*60 *1000); //calculate arrival time
					lastOne.setArrivalTime(timeFormat.format(updatedTime));
					scheduleRepo.save(lastOne);
				} else {
					for (int j = stations.length-1; j>0 ; j--) {
						updatedTime.setTime(departTime.getTime() + 8*60*1000*(stations.length-1-j));
						TrainSchedule oneSchedule = new TrainSchedule(stations[j], timeFormat.format(updatedTime), regular);
						updatedTime.setTime(updatedTime.getTime() - 3*60 *1000); //calculate arrival time
						oneSchedule.setArrivalTime(timeFormat.format(updatedTime)); //add arrival time to schedule
						scheduleRepo.save(oneSchedule);
					}
					TrainSchedule lastOne = new TrainSchedule(stations[0], "", regular);
					updatedTime.setTime(updatedTime.getTime() + 8*60 *1000); //calculate arrival time
					lastOne.setArrivalTime(timeFormat.format(updatedTime));
					scheduleRepo.save(lastOne);
					
				}
			}
		}
	}

	private void addExpressTrain(String direction) {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		for (int i = 6; i <= 21; i++) {
			String bound;
			//adding bound
			if (i<10 ) {
				bound = direction + "0" + String.valueOf(i) + "00";
			}else {
				bound = direction + String.valueOf(i) + "00";
			}
			//adding station departure time
			String departTimeString = String.valueOf(i) +":00:00";
			Train express = new Train(bound, departTimeString, EXPRESS);
			trainRepo.save(express);
			
			Date departTime = new Date();
			try {
				departTime = timeFormat.parse(departTimeString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date updatedTime = (Date)departTime.clone();
			Station[] stations = Station.values();
			if (direction.equals("SB")) {
				for (int j = 0; j<stations.length-1; j+=5) {
					updatedTime.setTime(departTime.getTime() + 28*60*1000*(j/5));
					TrainSchedule oneSchedule = new TrainSchedule(stations[j], timeFormat.format(updatedTime), express);
					updatedTime.setTime(updatedTime.getTime() - 3*60 *1000); //calculate arrival time
					oneSchedule.setArrivalTime(timeFormat.format(updatedTime)); //add arrival time to schedule
					scheduleRepo.save(oneSchedule);
				}
				TrainSchedule lastOne = new TrainSchedule(stations[stations.length-1], "", express);
				updatedTime.setTime(updatedTime.getTime() + 28*60 *1000); //calculate arrival time
				lastOne.setArrivalTime(timeFormat.format(updatedTime));
				scheduleRepo.save(lastOne);
			} else {
				for (int j = stations.length-1; j>0 ; j-=5) {
					updatedTime.setTime(departTime.getTime() + 28*60*1000*((stations.length-1-j)/5));
					TrainSchedule oneSchedule = new TrainSchedule(stations[j], timeFormat.format(updatedTime), express);
					updatedTime.setTime(updatedTime.getTime() - 3*60 *1000); //calculate arrival time
					oneSchedule.setArrivalTime(timeFormat.format(updatedTime)); //add arrival time to schedule
					scheduleRepo.save(oneSchedule);
				}
				TrainSchedule lastOne = new TrainSchedule(stations[0], "", express);
				updatedTime.setTime(updatedTime.getTime() + 28*60 *1000); //calculate arrival time
				lastOne.setArrivalTime(timeFormat.format(updatedTime));
				scheduleRepo.save(lastOne);
			}
		}
	
	}
}
