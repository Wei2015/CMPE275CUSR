package com.cmpe275.cusr.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.cusr.model.SearchContent;
import com.cmpe275.cusr.model.Station;
import com.cmpe275.cusr.model.SystemStat;
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
	
	private static DecimalFormat df = new DecimalFormat("#.##%");
	
	public String getTrainReservationPercent(String bound, String date) {
		Train train = trainRepo.findByBound(bound);
		double percent = getReservationPercent(train,date);
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
		double percent = (double) totalSeats/((Station.values().length-1)*train.getCapacity());
		return percent;
	}
	
	public String getDailyPercent(List<Train> trains, String date) {
		double totalPercent = 0.0;
		for (Train train : trains) {
			totalPercent += getReservationPercent(train, date);
		}
		double dailyPercent = totalPercent/trains.size();
		String percentString = df.format(dailyPercent);
		return percentString;
	}
	
	public int getTotalSearchNumber(String date) {
		int connectionOptions = SearchContent.connectionOptions.length;
		int totalCount = 0;
		for (int i = 0; i < connectionOptions; i++) {
			String connectionOption = SearchContent.connectionOptions[i];
			totalCount += requestRepo.getCountOnConnectionTypeAndDate(connectionOption, date);
		}
		return totalCount;
	}
	
	public SystemStat[] getRequestStat(String date) {
		int connectionOptions = SearchContent.connectionOptions.length;
		SystemStat[] result = new SystemStat[connectionOptions];
		
		//Calculate search percentage of each different number of connection 
		int totalSearch = getTotalSearchNumber(date);

		for (int i = 0; i < connectionOptions; i++) {
			String connectionOption = SearchContent.connectionOptions[i];
			int count = requestRepo.getCountOnConnectionTypeAndDate(connectionOption, date);
			double countD= (double)count/totalSearch;
			String countPercent = df.format(countD);
			long avgTime = 0;
			if (count != 0) {
				long eachTotalTime = requestRepo.getEachSearchTime(connectionOption, date);
				avgTime = eachTotalTime/count;
			}
			result[i] = new SystemStat(connectionOption, countPercent, avgTime);
		}
		return result;
	}
	
	
	

}
