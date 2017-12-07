package com.cmpe275.cusr.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.cusr.model.Booking;
import com.cmpe275.cusr.model.Station;
import com.cmpe275.cusr.model.Ticket;
import com.cmpe275.cusr.model.Train;
import com.cmpe275.cusr.model.TrainStatus;
import com.cmpe275.cusr.model.User;
import com.cmpe275.cusr.repository.TicketRepository;
import com.cmpe275.cusr.repository.TrainRepository;
import com.cmpe275.cusr.repository.TrainStatusRepository;

@Service
public class TicketServiceImpl {

	/*@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private TrainRepository trainRepository;
	
	@Autowired
	private TrainStatusRepository trainStatusRepository;
	
	@Autowired
	private EmailService emailService;

	@Transactional
	public boolean purchase(User user, Booking booking) {
		Ticket ticket = new Ticket();
		//General information.
		int numOfSeats = booking.getNumOfSeats();
		ticket.setNumOfSeats(numOfSeats);
		double price = booking.getPrice();
		ticket.setPrice(price);
		ticket.setUser(user);
		//Depart trip.
		//Date.
		String departureDate = booking.getDepartureDate();
		ticket.setDepartDate(departureDate);
		//Station and Time.
		int departTripSize = booking.getDepartureTrip().size();
		Station departStation = booking.getDepartureTrip().get(0).getDepartureStation();
		Station arrivalStation = booking.getDepartureTrip().get(0).getArrivalStation();
		Station stop1 = null;
		Station stop2 = null;
		if (departTripSize > 1) {
			stop1 = booking.getDepartureTrip().get(1).getDepartureStation();
			arrivalStation = booking.getDepartureTrip().get(1).getArrivalStation();
		}
		if (departTripSize > 2) {
			stop2 = booking.getDepartureTrip().get(2).getDepartureStation();
			arrivalStation = booking.getDepartureTrip().get(2).getArrivalStation();
		}		
		
		ticket.setDepartSegment1DepartTime(booking.getDepartureTrip().get(0).getDepartureTime());
		ticket.setDepartSegment1ArrivalTime(booking.getDepartureTrip().get(0).getArrivalTime());
		ticket.setDepartStation(departStation);
		ticket.setArrivalStation(arrivalStation);
		if (departTripSize > 1) {
			ticket.setDepartSegment2DepartTime(booking.getDepartureTrip().get(1).getDepartureTime());
			ticket.setDepartSegment2ArrivalTime(booking.getDepartureTrip().get(1).getArrivalTime());
			ticket.setStop1(stop1);
		}
		if (departTripSize > 2) {
			ticket.setDepartSegment3DepartTime(booking.getDepartureTrip().get(2).getDepartureTime());
			ticket.setDepartSegment3ArrivalTime(booking.getDepartureTrip().get(2).getArrivalTime());
			ticket.setStop2(stop2);
		}	
		//Train and TrainStatus.
		List<Train> departTrains = new ArrayList<>();
		List<TrainStatus> updatedTrainStatus = new ArrayList<>();
		for (int i = 0; i < departTripSize; i++) {
			Train train = trainRepository.findOne(booking.getDepartureTrip().get(i).getTrainId());
			if (numOfSeats > train.getCapacity()) {
				return false;
			}
			departTrains.add(train);
			List<TrainStatus> trainStatus = train.getTrainStatus();
			TrainStatus status = null;
			for (int j = 0; j < trainStatus.size(); j++) {
				status = trainStatus.get(j);
				if (departureDate == status.getDate()) {
					if (status.isCancelled()) {
						return false;
					} else {
						break;
					}
				}
			}
			Station s1;
			Station s2;
			switch (i) {
			case 0:
				s1 = departStation;
				s2 = departTripSize > 1 ? stop1 : arrivalStation;
				break;
			case 1:
				s1 = stop1;
				s2 = departTripSize > 2 ? stop2 : arrivalStation;
				break;
			case 2:
				s1 = stop2;
				s2 = arrivalStation;
				break;
			}
			if (status == null) {
				status = new TrainStatus();
				status.setDate(departureDate);
				status.setCancelled(false);
				Map<Station, Integer> map = new HashMap<>();
				for (int k = s1.getIndex(); k < s2.getIndex(); ++k) {					
					map.put(Station.values()[k], numOfSeats);
				}
				status.setSeatStatus(map);
			} else {
				//status.setDate(departureDate);
				status.setCancelled(false);
				Map<Station, Integer> map  = status.getSeatStatus();
				if (map == null) {
					map = new HashMap<>();
				}
				for (int k = s1.getIndex(); k < s2.getIndex(); ++k) {
					Station s = Station.values()[k];
					int updatedNumOfSeats = map.getOrDefault(s, 0) + numOfSeats;
					if (updatedNumOfSeats > train.getCapacity()) {
						return false;
					}
					map.put(s, updatedNumOfSeats);
					status.setSeatStatus(map);
				} 
			}
			updatedTrainStatus.add(status);
			trainStatus.add(status);			
			train.setTrainStatus(trainStatus);
		}
		ticket.setDepartTrains(departTrains);
		

		//Return trip.
		List<Train> returnTrains = new ArrayList<>();
		String returnDate = booking.getReturnDate();
		if (returnDate != null) {
			ticket.setReturnDate(returnDate);
			int returnTripSize = booking.getReturnTrip().size();
			ticket.setReturnSegment1DepartTime(booking.getReturnTrip().get(0).getDepartureTime());
			ticket.setReturnSegment1ArrivalTime(booking.getReturnTrip().get(0).getArrivalTime());
			if (returnTripSize > 1) {
				ticket.setReturnSegment2DepartTime(booking.getReturnTrip().get(1).getDepartureTime());
				ticket.setReturnSegment2ArrivalTime(booking.getReturnTrip().get(1).getArrivalTime());
			}
			if (returnTripSize > 2) {
				ticket.setReturnSegment3DepartTime(booking.getReturnTrip().get(2).getDepartureTime());
				ticket.setReturnSegment3ArrivalTime(booking.getReturnTrip().get(2).getArrivalTime());
			}
			if (returnTripSize > 1) {
				ticket.setReturnStop1(booking.getReturnTrip().get(1).getDepartureStation());
			}
			if (returnTripSize > 2) {
				ticket.setReturnStop2(booking.getReturnTrip().get(2).getDepartureStation());
			}
			for (int i = 0; i < returnTripSize; i++) {
				Train train = trainRepository.findOne(booking.getReturnTrip().get(i).getTrainId());
				returnTrains.add(train);
				List<TrainStatus> statusList = train.getTrainStatus();
				int updatedNumOfSeats = numOfSeats;
				for (int j = 0; j < statusList.size(); j++) {
					TrainStatus status = statusList.get(j);
					if ((status != null) && (status.isCancelled() != true) && (returnDate == status.getDate())) {
						updatedNumOfSeats += status.getUsedSeats();
					}
					status.setCancelled(false);
					status.setDate(returnDate);
					status.setUsedSeats(updatedNumOfSeats);
				}				
				/*Map<Date, Integer> map = train.getTrainStatus();
				int updatedNumOfSeats = numOfSeats;
				if (map.containsKey(returnDate)) {
					updatedNumOfSeats += map.get(returnDate);
				}
				map.put(booking.getReturnDate(), updatedNumOfSeats);*/
				/*if (updatedNumOfSeats > train.getCapacity()) {
					return false;
				}
			}
			ticket.setReturnTrains(returnTrains);
		}
		
		//Ticket status.
		ticket.setCancelled(false);
		
		//Store new ticket and train status information into database.
		ticketRepository.save(ticket);
		updatedTrainStatus.forEach(t -> trainStatusRepository.save(t));
		
		//Email service.
		emailService.sendMail(user.getEmail(),"CUSR Booking Confirmation","Thanks for your booking!");
		return true;
	}
	
	@Transactional
	public boolean cancel(User user, Booking booking) {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd'.LLLL yyyy");
		String dateStr = formatter.print(selectedDate);
		LocalTime currentTime = LocalTime.now();
		Format("HH:mm:ss")
		Date date = booking.getDepartureDate();
		Date time = booking.getDepartureTrip().get(0).getDepartureTime();
		Date now = new Date();
		return true;
	}*/

}
