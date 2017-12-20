package com.cmpe275.cusr.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.cusr.model.Station;
import com.cmpe275.cusr.model.Train;
import com.cmpe275.cusr.model.TrainStatus;
import com.cmpe275.cusr.repository.RequestRepository;
import com.cmpe275.cusr.repository.TrainRepository;
import com.cmpe275.cusr.repository.TrainStatusRepository;


@Service
public class SystemServiceImpl implements SystemService{
	
	@Autowired
	private TrainStatusRepository trainStatusRepo;
	
	@Autowired
	private TrainRepository trainRepo;
	
	@Autowired
	private RequestRepository requestRepo;
	
	public String getTrainReservationPercent(String bound, String date) {
		Train train = trainRepo.findByBound(bound);
		double percent = getReservationPercent(train,date);
		DecimalFormat df = new DecimalFormat("#%");
		String percentString = df.format(percent);
		return percentString;
	}
	
	private double getReservationPercent(Train train, String date) {
		
		TrainStatus status = trainStatusRepo.findByTrainAndDate(train, date);
		
		Map<Station, Integer> seats = status.getSeatStatus();
		int totalSeats = 0;
		for (Station s : seats.keySet()) {
			totalSeats+=seats.get(s);
		}
		double percent = (double) totalSeats/(Station.values().length-1);
		return percent;
	}
	
	public String getDailyPercent(List<Train> trains, String date) {
		double totalPercent = 0.0;
		for (Train train : trains) {
			totalPercent += getReservationPercent(train, date);
		}
		double dailyPercent = totalPercent/trains.size();
		DecimalFormat df = new DecimalFormat("#%");
		String percentString = df.format(dailyPercent);
		return percentString;
	}
	
	public int getTotalSearchNumber(String date) {
		return requestRepo.getTotalCount(date);
	}
	
	
	

}
