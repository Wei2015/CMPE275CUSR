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
import com.cmpe275.cusr.model.User;
import com.cmpe275.cusr.repository.TicketRepository;
import com.cmpe275.cusr.repository.TrainRepository;

import in.kiranreddy.EmailService;

@Service
public class TicketServiceImpl {

	/*@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private TrainRepository trainRepository;
	
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
		Date departureDate = booking.getDepartureDate();
		ticket.setDepartDate(departureDate);
		
		int departTripSize = booking.getDepartureTrip().size();
		ticket.setDepartSegment1DepartTime(booking.getDepartureTrip().get(0).getDepartureTime());
		ticket.setDepartSegment1ArrivalTime(booking.getDepartureTrip().get(0).getArrivalTime());
		if (departTripSize > 1) {
			ticket.setDepartSegment2DepartTime(booking.getDepartureTrip().get(1).getDepartureTime());
			ticket.setDepartSegment2ArrivalTime(booking.getDepartureTrip().get(1).getArrivalTime());
		}
		if (departTripSize > 2) {
			ticket.setDepartSegment3DepartTime(booking.getDepartureTrip().get(2).getDepartureTime());
			ticket.setDepartSegment3ArrivalTime(booking.getDepartureTrip().get(2).getArrivalTime());
		}
		
		ticket.setDepartStation(booking.getDepartureTrip().get(0).getDepartureStation());
		ticket.setArrivalStation(booking.getDepartureTrip().get(0).getArrivalStation());
		if (departTripSize > 1) {
			ticket.setStop1(booking.getDepartureTrip().get(1).getDepartureStation());
			ticket.setArrivalStation(booking.getDepartureTrip().get(1).getArrivalStation());
		}
		if (departTripSize > 2) {
			ticket.setStop2(booking.getDepartureTrip().get(2).getDepartureStation());
			ticket.setArrivalStation(booking.getDepartureTrip().get(2).getArrivalStation());
		}	
		
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

		//Return trip.
		List<Train> returnTrains = new ArrayList<>();
		Date returnDate = booking.getReturnDate();
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
		
		//Ticket status.
		ticket.setCancelled(false);
		
		//Store into database.
		departTrains.forEach(t -> trainRepository.save(t));
		returnTrains.forEach(t -> trainRepository.save(t));
		ticketRepository.save(ticket);
		
		//Email service.
		emailService.sendMail(user.getEmail(),"CUSR Booking confirmation","Thanks for your bokking!");
		return true;
	}
	
	@Transactional
	public boolean cancel(User user, Booking booking) {
		Date date = booking.getDepartureDate();
		Date time = booking.getDepartureTrip().get(0).getDepartureTime();
		Date now = new Date();
		return true;
	}*/

}
