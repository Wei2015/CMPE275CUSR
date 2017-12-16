package com.cmpe275.cusr.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.cusr.model.Booking;
import com.cmpe275.cusr.model.Station;
import com.cmpe275.cusr.model.Ticket;
import com.cmpe275.cusr.model.Train;
import com.cmpe275.cusr.model.TrainSchedule;
import com.cmpe275.cusr.model.TrainStatus;
import com.cmpe275.cusr.model.User;
import com.cmpe275.cusr.repository.ScheduleRepository;
import com.cmpe275.cusr.repository.TicketRepository;
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
	/*
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private TicketService ticketService;
	*/
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
					for (int j = 0; j<stations.length; j++) {
						updatedTime.setTime(departTime.getTime() + 8*60*1000*j);
						TrainSchedule oneSchedule = new TrainSchedule(stations[j], timeFormat.format(updatedTime), regular);
						updatedTime.setTime(updatedTime.getTime() - 3*60 *1000); //calculate arrival time
						oneSchedule.setArrivalTime(timeFormat.format(updatedTime)); //add arrival time to schedule
						adjustTimeFormat(oneSchedule);
						scheduleRepo.save(oneSchedule);
					}
					/*
					TrainSchedule lastOne = new TrainSchedule(stations[stations.length-1], "", regular);
					updatedTime.setTime(updatedTime.getTime() + 8*60 *1000); //calculate arrival time
					lastOne.setArrivalTime(timeFormat.format(updatedTime));
					scheduleRepo.save(lastOne);
					*/
				} else {
					for (int j = stations.length-1; j>=0 ; j--) {
						updatedTime.setTime(departTime.getTime() + 8*60*1000*(stations.length-1-j));
						TrainSchedule oneSchedule = new TrainSchedule(stations[j], timeFormat.format(updatedTime), regular);
						updatedTime.setTime(updatedTime.getTime() - 3*60 *1000); //calculate arrival time
						oneSchedule.setArrivalTime(timeFormat.format(updatedTime)); //add arrival time to schedule
						adjustTimeFormat(oneSchedule);
						scheduleRepo.save(oneSchedule);
					}
					/*
					TrainSchedule lastOne = new TrainSchedule(stations[0], "", regular);
					updatedTime.setTime(updatedTime.getTime() + 8*60 *1000); //calculate arrival time
					lastOne.setArrivalTime(timeFormat.format(updatedTime));
					scheduleRepo.save(lastOne);
					*/
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
				for (int j = 0; j<stations.length; j+=5) {
					updatedTime.setTime(departTime.getTime() + 28*60*1000*(j/5));
					TrainSchedule oneSchedule = new TrainSchedule(stations[j], timeFormat.format(updatedTime), express);
					updatedTime.setTime(updatedTime.getTime() - 3*60 *1000); //calculate arrival time
					oneSchedule.setArrivalTime(timeFormat.format(updatedTime)); //add arrival time to schedule
					adjustTimeFormat(oneSchedule);
					scheduleRepo.save(oneSchedule);
				}
				/*
				TrainSchedule lastOne = new TrainSchedule(stations[stations.length-1], "", express);
				updatedTime.setTime(updatedTime.getTime() + 28*60 *1000); //calculate arrival time
				lastOne.setArrivalTime(timeFormat.format(updatedTime));
				scheduleRepo.save(lastOne);
				*/
			} else {
				for (int j = stations.length-1; j>=0 ; j-=5) {
					updatedTime.setTime(departTime.getTime() + 28*60*1000*((stations.length-1-j)/5));
					TrainSchedule oneSchedule = new TrainSchedule(stations[j], timeFormat.format(updatedTime), express);
					updatedTime.setTime(updatedTime.getTime() - 3*60 *1000); //calculate arrival time
					oneSchedule.setArrivalTime(timeFormat.format(updatedTime)); //add arrival time to schedule
					adjustTimeFormat(oneSchedule);
					scheduleRepo.save(oneSchedule);
				}
				/*
				TrainSchedule lastOne = new TrainSchedule(stations[0], "", express);
				updatedTime.setTime(updatedTime.getTime() + 28*60 *1000); //calculate arrival time
				lastOne.setArrivalTime(timeFormat.format(updatedTime));
				scheduleRepo.save(lastOne);
				*/
			}
		}
	
	}
	
	
	private void adjustTimeFormat(TrainSchedule oneSchedule) {
		String departTime = oneSchedule.getDepartTime();
		String arrivalTime = oneSchedule.getArrivalTime();
		if (departTime.startsWith("00"))
			oneSchedule.setDepartTime(departTime.replaceFirst("00", "24"));
		if (arrivalTime.startsWith("00"))
			oneSchedule.setArrivalTime(arrivalTime.replaceFirst("00", "24"));
	}
	
	@Transactional
	public void trainCancel (String trainName, String date) {
		//Check time.
		/*Train train = trainRepo.findByBound(trainName);
		String startTime = train.getDepartureTime();
		if (ticketService.timeCheck (date, startTime, 180)) {
			return;
		}
		
		//Update train status.
		TrainStatus status = trainStatusRepo.findByTrainAndDate(train, date);
		status.setCancelled(true);
		trainStatusRepo.save(status);
		
		//Find affected tickets.
		List<Ticket> cancelledTickets = new ArrayList<>();
		List<Ticket> departTickets = ticketRepository.findByDepartDate(date);
		List<Ticket> returnTickets = ticketRepository.findByReturnDate(date);
		List<TrainStatus> updatedTrainStatus = new ArrayList<>();
		Station s1 = null;
		Station s2 = null;
		
		for (Ticket ticket : departTickets) {
			List<Train> trains = ticket.getDepartTrains();
			if (!trains.contains(train)) {
				continue;
			}
			cancelledTickets.add(ticket);
			ticket.setCancelled(true);
			//Update seat status.
			int numOfSeats = ticket.getNumOfSeats();
			int departTripSize = trains.size();
			Station departStation = ticket.getDepartStation();
			Station arrivalStation = ticket.getArrivalStation();
			Station stop1 = ticket.getStop1();
			Station stop2 = ticket.getStop2();
			switch (departTripSize) {
			case 1:
				s1 = departStation;
				s2 = departTripSize > 1 ? stop1 : arrivalStation;
				break;
			case 2:
				s1 = stop1;
				s2 = departTripSize > 2 ? stop2 : arrivalStation;
				break;
			case 3:
				s1 = stop2;
				s2 = arrivalStation;
				break;
			}
			int idx1 = s1.getIndex();
			int idx2 = s2.getIndex();
			Map<Station, Integer> map  = status.getSeatStatus();
			if (idx1 < idx2) {
				for (int k = idx1; k < idx2; ++k) {
					Station s = Station.values()[k];
					int updatedNumOfSeats = map.get(s) - numOfSeats;
					map.put(s, updatedNumOfSeats);
					status.setSeatStatus(map);
				} 
			} else {
				for (int k = idx1; k > idx2; --k) {
					Station s = Station.values()[k];
					int updatedNumOfSeats = map.get(s) - numOfSeats;
					map.put(s, updatedNumOfSeats);
					status.setSeatStatus(map);
				}
			}
			updatedTrainStatus.add(status);
		}
		for (Ticket ticket : returnTickets) {
			List<Train> trains = ticket.getReturnTrains();
			if (!trains.contains(train)) {
				continue;
			}
			cancelledTickets.add(ticket);
			ticket.setCancelled(true);
			//Update seat status.
			int numOfSeats = ticket.getNumOfSeats();
			int returnTripSize = trains.size();
			Station departStation = ticket.getArrivalStation();
			Station arrivalStation = ticket.getDepartStation();
			Station returnStop1 = ticket.getReturnStop1();
			Station returnStop2 = ticket.getReturnStop2();
			switch (returnTripSize) {
			case 1:
				s1 = arrivalStation;
				s2 = returnTripSize > 1 ? returnStop1 : departStation;
				break;
			case 2:
				s1 = returnStop1;
				s2 = returnTripSize > 2 ? returnStop2 : departStation;
				break;
			case 3:
				s1 = returnStop2;
				s2 = departStation;
				break;
			}
			int idx1 = s1.getIndex();
			int idx2 = s2.getIndex();
			Map<Station, Integer> map  = status.getSeatStatus();
			if (idx1 < idx2) {
				for (int k = idx1; k < idx2; ++k) {
					Station s = Station.values()[k];
					int updatedNumOfSeats = map.get(s) - numOfSeats;
					map.put(s, updatedNumOfSeats);
					status.setSeatStatus(map);
				} 
			} else {
				for (int k = idx1; k > idx2; --k) {
					Station s = Station.values()[k];
					int updatedNumOfSeats = map.get(s) - numOfSeats;
					map.put(s, updatedNumOfSeats);
					status.setSeatStatus(map);
				}
			}
			updatedTrainStatus.add(status);
		}
		updatedTrainStatus.forEach(t -> trainStatusRepo.save(t));
			
			
		//New search.
		for (Ticket ticket : cancelledTickets) {
		  // search ticket
		  Booking booking = searchTicket(ticket);
		  //Email.
		  User user = ticket.getUser();
		  emailService.sendMail(user.getEmail(),"CUSR Train Cancellation", "The Train has been cancelled");
		 //Re-book.
		  ticketService.purchase(user, booking);
		}*/
	}
	
}
