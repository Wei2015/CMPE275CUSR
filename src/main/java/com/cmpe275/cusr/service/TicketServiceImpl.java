package com.cmpe275.cusr.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

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
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private TrainRepository trainRepository;
	
	@Autowired
	private TrainStatusRepository trainStatusRepository;
	
	@Transactional
	public boolean purchase(User user, Booking booking) {
		Ticket ticket = new Ticket();
		//General information.
		int numOfSeats = booking.getNumOfSeats();
		ticket.setNumOfSeats(numOfSeats);
		ticket.setPassenger((ArrayList<String>) booking.getPassenger());
		double price = booking.getPrice() + 1;
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
		Station s1 = null;
		Station s2 = null;
		for (int i = 0; i < departTripSize; i++) {
			Train train = trainRepository.findByBound(booking.getDepartureTrip().get(i).getBound());
			departTrains.add(train);
			TrainStatus status = trainStatusRepository.findByTrainAndDate(train, departureDate);
			if (status.isCancelled()) {
				return false;
			}
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
			status.setCancelled(false);
			int idx1 = s1.getIndex();
			int idx2 = s2.getIndex();
			Map<Station, Integer> map  = status.getSeatStatus();
			if (idx1 < idx2) {
				for (int k = idx1; k < idx2; ++k) {
					Station s = Station.values()[k];
					int updatedNumOfSeats = map.get(s) + numOfSeats;
					if (updatedNumOfSeats > train.getCapacity()) {
						return false;
					}
					map.put(s, updatedNumOfSeats);
					status.setSeatStatus(map);
				} 
			} else {
				for (int k = idx1; k > idx2; --k) {
					Station s = Station.values()[k];
					int updatedNumOfSeats = map.get(s) + numOfSeats;
					if (updatedNumOfSeats > train.getCapacity()) {
						return false;
					}
					map.put(s, updatedNumOfSeats);
					status.setSeatStatus(map);
				}
			}
			updatedTrainStatus.add(status);
			//trainStatus.add(status);			
			//train.setTrainStatus(trainStatus);
		}
		ticket.setDepartTrains(departTrains);

		//Return trip.
		List<Train> returnTrains = new ArrayList<>();
		String returnDate = booking.getReturnDate();
		if (returnDate != null) {
			//Date.
			ticket.setReturnDate(returnDate);
			//Station and Time.
			int returnTripSize = booking.getReturnTrip().size();
			Station returnStop1 = null;
			Station returnStop2 = null;
			if (returnTripSize > 1) {
				returnStop1 = booking.getReturnTrip().get(1).getDepartureStation();
			}
			if (returnTripSize > 2) {
				returnStop2 = booking.getReturnTrip().get(2).getDepartureStation();
			}		
			ticket.setReturnSegment1DepartTime(booking.getReturnTrip().get(0).getDepartureTime());
			ticket.setReturnSegment1ArrivalTime(booking.getReturnTrip().get(0).getArrivalTime());
			if (returnTripSize > 1) {
				ticket.setReturnSegment2DepartTime(booking.getReturnTrip().get(1).getDepartureTime());
				ticket.setReturnSegment2ArrivalTime(booking.getReturnTrip().get(1).getArrivalTime());
				ticket.setReturnStop1(returnStop1);
			}
			if (returnTripSize > 2) {
				ticket.setReturnSegment3DepartTime(booking.getReturnTrip().get(2).getDepartureTime());
				ticket.setReturnSegment3ArrivalTime(booking.getReturnTrip().get(2).getArrivalTime());
				ticket.setReturnStop2(returnStop2);
			}
			//Train and TrainStatus.
			for (int i = 0; i < returnTripSize; i++) {
				Train train = trainRepository.findByBound(booking.getReturnTrip().get(i).getBound());
				returnTrains.add(train);
				TrainStatus status = trainStatusRepository.findByTrainAndDate(train, returnDate);
				if (status.isCancelled()) {
					return false;
				}
				switch (i) {
				case 0:
					s1 = arrivalStation;
					s2 = returnTripSize > 1 ? returnStop1 : departStation;
					break;
				case 1:
					s1 = returnStop1;
					s2 = returnTripSize > 2 ? returnStop2 : departStation;
					break;
				case 2:
					s1 = returnStop2;
					s2 = departStation;
					break;
				}
				status.setCancelled(false);
				int idx1 = s1.getIndex();
				int idx2 = s2.getIndex();
				Map<Station, Integer> map  = status.getSeatStatus();
				if (idx1 < idx2) {
					for (int k = idx1; k < idx2; ++k) {
						Station s = Station.values()[k];
						int updatedNumOfSeats = map.get(s) + numOfSeats;
						if (updatedNumOfSeats > train.getCapacity()) {
							return false;
						}
						map.put(s, updatedNumOfSeats);
						status.setSeatStatus(map);
					} 
				} else {
					for (int k = idx1; k > idx2; --k) {
						Station s = Station.values()[k];
						int updatedNumOfSeats = map.get(s) + numOfSeats;
						if (updatedNumOfSeats > train.getCapacity()) {
							return false;
						}
						map.put(s, updatedNumOfSeats);
						status.setSeatStatus(map);
					}
				}
				updatedTrainStatus.add(status);
				//trainStatus.add(status);			
				//train.setTrainStatus(trainStatus);
			}
			ticket.setReturnTrains(returnTrains);
		}
		
		//Ticket status.
		ticket.setCancelled(false);
		
		//Store new ticket and train status information into database.
		ticketRepository.save(ticket);
		updatedTrainStatus.forEach(t -> trainStatusRepository.save(t));		
		return true;
	}
	
	@Transactional
	public boolean cancel(long ticketId) {
		Ticket ticket = ticketRepository.findOne(ticketId);
		String departDate = ticket.getDepartDate();
		
		//Check time.
		if (!timeCheck (departDate, ticket.getDepartSegment1DepartTime(), 60)) {
			return false;
		}

		//Common variables.
		int numOfSeats = ticket.getNumOfSeats();
		List<TrainStatus> updatedTrainStatus = new ArrayList<>();
		Station s1 = null;
		Station s2 = null;
		
		//Depart Trip.
		//Train and Station info.
		List<Train> departTrains = ticket.getDepartTrains();
		int departTripSize = departTrains.size();
		Station departStation = ticket.getDepartStation();
		Station arrivalStation = ticket.getArrivalStation();
		Station stop1 = null;
		Station stop2 = null;
		if (departTripSize > 1) {
			stop1 = ticket.getStop1();
		}
		if (departTripSize > 2) {
			stop2 = ticket.getStop2();
		}
		
		//Update train status.
		for (int i = 0; i < departTripSize; i++) {
			Train train = departTrains.get(i);
			TrainStatus status = trainStatusRepository.findByTrainAndDate(train, departDate);
			/*if (status.isCancelled()) {
				return false;
			}*/
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
			//trainStatus.add(status);			
			//train.setTrainStatus(trainStatus);
		}
		
		//Return trip.
		String returnDate = ticket.getReturnDate();
		if (returnDate != null) {
			List<Train> returnTrains = ticket.getReturnTrains();
			int returnTripSize = returnTrains.size();
			Station returnStop1 = null;
			Station returnStop2 = null;
			if (returnTripSize > 1) {
				returnStop1 = ticket.getReturnStop1();
			}
			if (returnTripSize > 2) {
				returnStop2 = ticket.getReturnStop2();
			}
			//Update train status.
			for (int i = 0; i < returnTripSize; i++) {
				Train train = returnTrains.get(i);
				TrainStatus status = trainStatusRepository.findByTrainAndDate(train, returnDate);
				/*if (status.isCancelled()) {
					return false;
				}*/
				switch (i) {
				case 0:
					s1 = arrivalStation;
					s2 = returnTripSize > 1 ? returnStop1 : departStation;
					break;
				case 1:
					s1 = returnStop1;
					s2 = returnTripSize > 2 ? returnStop2 : departStation;
					break;
				case 2:
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
						if (updatedNumOfSeats > train.getCapacity()) {
							return false;
						}
						map.put(s, updatedNumOfSeats);
						status.setSeatStatus(map);
					} 
				} else {
					for (int k = idx1; k > idx2; --k) {
						Station s = Station.values()[k];
						int updatedNumOfSeats = map.get(s) - numOfSeats;
						if (updatedNumOfSeats > train.getCapacity()) {
							return false;
						}
						map.put(s, updatedNumOfSeats);
						status.setSeatStatus(map);
					}
				}
				updatedTrainStatus.add(status);
				//trainStatus.add(status);			
				//train.setTrainStatus(trainStatus);
			}
		}
		//Update ticket status.
		ticket.setCancelled(true);
		
		//Store ticket information and train status information into database.
		ticketRepository.save(ticket);
		updatedTrainStatus.forEach(t -> trainStatusRepository.save(t));
		return true;
	}
	
	public boolean timeCheck (String date, String time, int diff) {		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(date, dateFormatter);
		LocalDate currentDate = LocalDate.now();
		
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime localTime = LocalTime.parse(time, timeFormatter);
		LocalTime currentTime = LocalTime.now();
		
		if (currentDate.isAfter(localDate)) {
			return false;
		} 
		if (currentDate.isEqual(localDate)) {
			if (Duration.between(currentTime, localTime).toMinutes() < diff) {
				return false;
			}
		}
		return true;
	}
	
}
