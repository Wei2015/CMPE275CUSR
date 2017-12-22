package com.cmpe275.cusr.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmpe275.cusr.model.Booking;
import com.cmpe275.cusr.model.OneWayList;
import com.cmpe275.cusr.model.OneWayTrip;
import com.cmpe275.cusr.model.SearchContent;
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

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private TrainService trainService;
	


	// show train capacity
	public List<Train> showTrainCapacity() {
		return trainRepo.findAll();
	}

	// update train capacity
	public void updateTrainCapacity(int capacity) {
		trainRepo.updateCapacity(capacity);
	}

	// reset ticket table and status table;
	public void reset() {
		scheduleRepo.deleteAllInBatch();
		ticketRepository.deleteAllInBatch();
		List<TrainStatus> statusList = trainStatusRepo.findAll();
		for (TrainStatus t : statusList) {
			t.setSeatStatus(null);
		}
		trainStatusRepo.deleteAllInBatch();
		trainRepo.deleteAllInBatch();
	}

	// populate train status information
	public void populateTrainStatus() {
		if (!(trainRepo.count() > 0))
			populateTrainTable();

		trainStatusRepo.deleteAll();
		List<Train> allTrains = trainRepo.findAll();

		SimpleDateFormat format = new SimpleDateFormat(datePattern);

		for (Train t : allTrains) {
			for (int i = 0; i < TIMEFRAME; i++) {
				Date current = new Date();
				current.setTime(current.getTime() + 1000 * 60 * 60 * 24 * i);
				String currentDate = format.format(current);
				TrainStatus newStatus = new TrainStatus(t, currentDate, false);
				Map<Station, Integer> map = newStatus.getSeatStatus();
				Station[] stations = Station.values();
				for (int j = 0; j < stations.length; j++)
					map.put(stations[j], 0);
				newStatus.setSeatStatus(map);
				trainStatusRepo.save(newStatus);
			}
		}
	}

	// populate train schedule information
	public void populateTrainTable() {

		trainRepo.deleteAll();
		scheduleRepo.deleteAll();

		// Adding Express Train Info
		addExpressTrain("SB");
		addExpressTrain("NB");

		// Adding Regular Train Info
		addRegularTrain("SB");
		addRegularTrain("NB");
	}

	private void addRegularTrain(String direction) {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		for (int i = 6; i < 21; i++) {
			for (int m = 15; m < 60; m += 15) {
				String bound;
				// generate bound content
				if (i < 10) {
					bound = direction + "0" + String.valueOf(i) + String.valueOf(m);
				} else {
					bound = direction + String.valueOf(i) + String.valueOf(m);
				}

				// adding departure time at start station
				String departTimeString = String.valueOf(i) + ":" + String.valueOf(m) + ":00";
				// add a new Train to TRAIN table
				Train regular = new Train(bound, departTimeString, REGULAR);
				trainRepo.save(regular);

				// generate time schedule at other stations
				Date departTime = new Date();
				try {
					departTime = timeFormat.parse(departTimeString);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Date updatedTime = (Date) departTime.clone();
				Station[] stations = Station.values();
				if (direction.equals("SB")) {
					for (int j = 0; j < stations.length; j++) {
						updatedTime.setTime(departTime.getTime() + 8 * 60 * 1000 * j);
						TrainSchedule oneSchedule = new TrainSchedule(stations[j], timeFormat.format(updatedTime),
								regular);
						updatedTime.setTime(updatedTime.getTime() - 3 * 60 * 1000); // calculate arrival time
						oneSchedule.setArrivalTime(timeFormat.format(updatedTime)); // add arrival time to schedule
						adjustTimeFormat(oneSchedule);
						scheduleRepo.save(oneSchedule);
					}

				} else {
					for (int j = stations.length - 1; j >= 0; j--) {
						updatedTime.setTime(departTime.getTime() + 8 * 60 * 1000 * (stations.length - 1 - j));
						TrainSchedule oneSchedule = new TrainSchedule(stations[j], timeFormat.format(updatedTime),
								regular);
						updatedTime.setTime(updatedTime.getTime() - 3 * 60 * 1000); // calculate arrival time
						oneSchedule.setArrivalTime(timeFormat.format(updatedTime)); // add arrival time to schedule
						adjustTimeFormat(oneSchedule);
						scheduleRepo.save(oneSchedule);
					}

				}
			}
		}
	}

	private void addExpressTrain(String direction) {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		for (int i = 6; i <= 21; i++) {
			String bound;
			// adding bound
			if (i < 10) {
				bound = direction + "0" + String.valueOf(i) + "00";
			} else {
				bound = direction + String.valueOf(i) + "00";
			}
			// adding station departure time
			String departTimeString = String.valueOf(i) + ":00:00";
			Train express = new Train(bound, departTimeString, EXPRESS);
			trainRepo.save(express);

			Date departTime = new Date();
			try {
				departTime = timeFormat.parse(departTimeString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date updatedTime = (Date) departTime.clone();
			Station[] stations = Station.values();
			if (direction.equals("SB")) {
				for (int j = 0; j < stations.length; j += 5) {
					updatedTime.setTime(departTime.getTime() + 28 * 60 * 1000 * (j / 5));
					TrainSchedule oneSchedule = new TrainSchedule(stations[j], timeFormat.format(updatedTime), express);
					updatedTime.setTime(updatedTime.getTime() - 3 * 60 * 1000); // calculate arrival time
					oneSchedule.setArrivalTime(timeFormat.format(updatedTime)); // add arrival time to schedule
					adjustTimeFormat(oneSchedule);
					scheduleRepo.save(oneSchedule);
				}

			} else {
				for (int j = stations.length - 1; j >= 0; j -= 5) {
					updatedTime.setTime(departTime.getTime() + 28 * 60 * 1000 * ((stations.length - 1 - j) / 5));
					TrainSchedule oneSchedule = new TrainSchedule(stations[j], timeFormat.format(updatedTime), express);
					updatedTime.setTime(updatedTime.getTime() - 3 * 60 * 1000); // calculate arrival time
					oneSchedule.setArrivalTime(timeFormat.format(updatedTime)); // add arrival time to schedule
					adjustTimeFormat(oneSchedule);
					scheduleRepo.save(oneSchedule);
				}

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
	public void trainCancel(String bound, String date) {

		// check date is not more than 4 weeks from now. otherwise just return
		if (!verifyDate(date))
			return;

		Train train = trainRepo.findByBound(bound);

		// Check train departure time is not within 3 hours.
		String startTime = train.getDepartureTime();

		if (startTime.length() < 8) 
			startTime = "0" + startTime;
		
		if (!ticketService.timeCheck(date, startTime, 180))
			return;

		// Check if the bound and date is already cancelled.
		TrainStatus currentStatus = trainStatusRepo.findByTrainAndDate(train, date);
		if (currentStatus.isCancelled())
			return;

		// Update train status.
		currentStatus.setCancelled(true);
		trainStatusRepo.save(currentStatus);

		//find tickets contain the train, release seat status, and collect ticket info as a list.
		List<Ticket> cancelledTickets = cancelTickets(train, date);

		//search trip and purchase new ticket based on the information from cancelled tickets
		for (Ticket t : cancelledTickets) {
			Booking booking = searchTicket(t);
			User user = t.getUser();
			if (booking == null) {
				String msg = "Sorry, a train in your following booking has been cancelled, and there is no ticket meeting your original searching criteria.Please try your search again!";
				String cancelContent = emailService.ticketMailBuilder(t.getTicketId(), "emailTemplateCancel", msg);
				emailService.sendMail(user.getEmail(), "CUSR Train Cancellation", cancelContent);
			}
			ticketService.purchase(user, booking);
			String msg = "Your booking has been re-processed due to train cancellation. Here is the ticket details:";
			String purchaseContent = emailService.bookingMailBuilder(booking, "emailTemplateBook", msg);
			emailService.sendMail(user.getEmail(), "CUSR Ticket Re-Booking Confirmation", purchaseContent);
		}
	}

	//create a new Booking object from search
	private Booking searchTicket(Ticket t) {
		Booking newBook = new Booking();
		SearchContent newSearch = createNewSearch(t);
		OneWayList oneWay = new OneWayList();
		trainService.searchOneWay(newSearch, oneWay);
		OneWayTrip forwardTrip = oneWay.getFirstFive().get(0);
		newBook.setDepartureDate(forwardTrip.getDepartureDate());
		newBook.setDepartureTrip(forwardTrip.getConnections());
		newBook.setNumOfSeats(newSearch.getNumberOfSeats());
		newBook.setPassenger(t.getPassenger());
		newBook.setPrice(forwardTrip.getTicketPrice());
		if (newSearch.isRoundTrip()) {
			OneWayList returnWay = new OneWayList();
			trainService.searchOneWay(newSearch.getReturnSearch(), returnWay);
			OneWayTrip returnTrip = returnWay.getFirstFive().get(0);
			newBook.setReturnDate(returnTrip.getDepartureDate());
			newBook.setReturnTrip(returnTrip.getConnections());
			newBook.setPrice(newBook.getPrice()+returnTrip.getTicketPrice() + 1.0);
		}
		return newBook;
	}	
		
	//create a new SearchContent object based on one ticket information
	private SearchContent createNewSearch(Ticket t) {
		SearchContent newSearch = new SearchContent();
		newSearch.setDepartureDate(t.getDepartDate());
		newSearch.setDepartureStation(t.getDepartStation());
		newSearch.setDestinationStation(t.getArrivalStation());
		newSearch.setDepartureTime(t.getDepartSegment1DepartTime());
		if (t.getReturnDate() != null) {
			newSearch.setRoundTrip(true);
			newSearch.setReturnDate(t.getReturnDate());
			newSearch.setReturnTime(t.getReturnSegment1DepartTime());
		} else {
			newSearch.setRoundTrip(false);
		}
		newSearch.setExactTime(false);
		newSearch.setTrainType("Any");
		newSearch.setNumberOfSeats(t.getNumOfSeats());
		newSearch.setNumberOfConnections("Any");
		return newSearch;
	}
	
	// Find affected tickets, cancel tickets and update seat status.
	private List<Ticket> cancelTickets(Train train, String date) {
		List<Ticket> cancelledTickets = new ArrayList<>();
		List<Ticket> departTickets = ticketRepository.findByDepartDate(date);
		List<Ticket> returnTickets = ticketRepository.findByReturnDate(date);

		for (Ticket ticket : departTickets) {
			List<Train> departTrains = ticket.getDepartTrains();
			if (!departTrains.contains(train)) {
				continue;
			}
			if (ticket.isCancelled()) {
				continue;
			}
			cancelledTickets.add(ticket);
			ticketService.cancel(ticket.getTicketId());
		}
		for (Ticket ticket : returnTickets) {
			List<Train> returnTrains = ticket.getReturnTrains();
			if (!returnTrains.contains(train)) {
				continue;
			}
			if (ticket.isCancelled()) {
				continue;
			}
			cancelledTickets.add(ticket);
			ticketService.cancel(ticket.getTicketId());
		}
		return cancelledTickets;
	}

	//verify cancel train on the date not more than 4 weeks from now
	private boolean verifyDate(String date) {

		Date later = new Date();
		for (int i = 1; i < 5; i++)
			later.setTime(later.getTime() + 7 * 24 * 60 * 60 * 1000);
		Date userInput = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			userInput = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return userInput.before(later);
	}

}
