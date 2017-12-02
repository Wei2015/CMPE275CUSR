package com.cmpe275.cusr.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.cusr.model.Booking;
import com.cmpe275.cusr.model.Ticket;
import com.cmpe275.cusr.model.Train;
import com.cmpe275.cusr.repository.TicketRepository;
import com.cmpe275.cusr.repository.TrainRepository;
import com.cmpe275.cusr.repository.UserRepository;

@Service
public class TicketServiceImpl {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private TrainRepository trainRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public boolean purchase(long userId, Booking booking) {
		Ticket ticket = new Ticket();
		int numOfSeats = booking.getNumOfSeats();
		ticket.setNumOfSeats(numOfSeats);
		double price = booking.getPrice();
		ticket.setPrice(price);
		ticket.setUser(userRepository.findOne(userId));
		
		Date departureDate = booking.getDepartureDate();
		ticket.setDepartDate(departureDate);
		int departTripSize = booking.getDepartureTrip().size();		
		ticket.setDepartTime(booking.getDepartureTrip().get(0).getDepartureTime());
		if (departTripSize > 1) {
			ticket.setStop1Time(booking.getDepartureTrip().get(1).getDepartureTime());
		}
		if (departTripSize > 2) {
			ticket.setStop2Time(booking.getDepartureTrip().get(2).getDepartureTime());
		}
		ticket.setArrivalTime(booking.getDepartureTrip().get(2).getArrivalTime());
		ticket.setDepartStation(booking.getDepartureTrip().get(0).getDepartureStation());
		if (departTripSize > 1) {
			ticket.setStop1(booking.getDepartureTrip().get(1).getDepartureStation());
		}
		if (departTripSize > 2) {
			ticket.setStop2(booking.getDepartureTrip().get(2).getDepartureStation());
		}
		ticket.setArrivalStation(booking.getDepartureTrip().get(2).getArrivalStation());
		List<Train> departTrains = new ArrayList<>();
		for (int i = 0; i < departTripSize; i++) {
			Train train = trainRepository.findOne(booking.getDepartureTrip().get(i).getTrainId());
			departTrains.add(train);
			Map<Date, Integer> map = train.getTrainStatus();
			int updatedNumOfSeats = numOfSeats;
			if (map.containsKey(departureDate)) {
				updatedNumOfSeats += map.get(departureDate);
			}
			map.put(booking.getDepartureDate(), updatedNumOfSeats);
			if (updatedNumOfSeats > train.getCapacity()) {
				return false;
			}
		}
		ticket.setDepartTrains(departTrains);

		List<Train> returnTrains = new ArrayList<>();
		Date returnDate = booking.getReturnDate();
		if (returnDate != null) {
			ticket.setReturnDate(returnDate);
			int returnTripSize = booking.getReturnTrip().size();
			ticket.setReturnDepartTime(booking.getReturnTrip().get(0).getDepartureTime());
			if (returnTripSize > 1) {
				ticket.setReturnStop1Time(booking.getReturnTrip().get(1).getDepartureTime());
			}
			if (returnTripSize > 2) {
				ticket.setReturnStop2Time(booking.getReturnTrip().get(2).getDepartureTime());
			}
			ticket.setReturnArrivalTime(booking.getReturnTrip().get(2).getArrivalTime());
			if (returnTripSize > 1) {
				ticket.setReturnStop1(booking.getReturnTrip().get(1).getDepartureStation());
			}
			if (returnTripSize > 2) {
				ticket.setReturnStop2(booking.getReturnTrip().get(2).getDepartureStation());
			}
			for (int i = 0; i < returnTripSize; i++) {
				Train train = trainRepository.findOne(booking.getReturnTrip().get(i).getTrainId());
				departTrains.add(train);
				Map<Date, Integer> map = train.getTrainStatus();
				int updatedNumOfSeats = numOfSeats;
				if (map.containsKey(returnDate)) {
					updatedNumOfSeats += map.get(returnDate);
				}
				map.put(booking.getReturnDate(), updatedNumOfSeats);
				if (updatedNumOfSeats > train.getCapacity()) {
					return false;
				}
			}
			ticket.setReturnTrains(returnTrains);
		}
		departTrains.forEach(t -> trainRepository.save(t));
		returnTrains.forEach(t -> trainRepository.save(t));
		ticketRepository.save(ticket);
		return true;
	}

	public boolean cancel(long userId, Booking booking) {
		Date date = booking.getDepartureDate();
		Date time = booking.getDepartureTrip().get(0).getDepartureTime();

		return true;
	}

}
