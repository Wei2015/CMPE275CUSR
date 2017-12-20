package com.cmpe275.cusr.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmpe275.cusr.model.OneWayList;
import com.cmpe275.cusr.model.OneWayTrip;
import com.cmpe275.cusr.model.SearchContent;
import com.cmpe275.cusr.model.Segment;
import com.cmpe275.cusr.model.Station;
import com.cmpe275.cusr.model.Train;
import com.cmpe275.cusr.model.TrainSchedule;
import com.cmpe275.cusr.model.TrainStatus;
import com.cmpe275.cusr.repository.ScheduleRepository;
import com.cmpe275.cusr.repository.TrainStatusRepository;


@Service
public class TrainServiceImpl implements TrainService {
	
	@Autowired
	private ScheduleRepository scheduleRepo;
	
	@Autowired 
	private TrainStatusRepository trainStatusRepo;
	
	
	private static int NUMBER_OF_TRIP_RETURNED = 5;
	private static String fullPattern = "yyyy-MM-dd HH:mm:ss";
	private static String timePattern = "HH:mm:ss";
	private static String datePattern = "yyyy-MM-dd";
	
	
public boolean verfiyDateAndTime(SearchContent content, OneWayList result) {
	
	String departureDate = content.getDepartureDate();
	String departureTime = content.getDepartureTime(); 
	if (departureTime.length() == 4 && !departureTime.startsWith("0")) 
		departureTime = "0" + departureTime;
	departureTime += ":00";
	
	String returnDate;
	String returnTime; 
	
	//verify departure time no less than 5 minutes from now and date is within 4 weeks from now
	boolean inputTimeOK= verifyDepartureTime(departureDate, departureTime);
	boolean returnDateOK = true;
	
	//verify return date is within 7 days from departure date
	if (content.isRoundTrip()) {
		returnDate = content.getReturnDate();
		returnTime = content.getDepartureTime(); 
		if (returnTime.length() == 4 && !returnTime.startsWith("0")) 
			returnTime = "0" + returnTime;
		returnTime += ":00";
		returnDateOK = verifyReturnDate(departureDate, returnDate);
	}
	
	if (!inputTimeOK)
		result.setMessage("Input Error: \n Departure time should be set more than 5 minutes from now. \n Departure date should be within 4 weeks");
	else if (!returnDateOK)
		result.setMessage("Input Error: \n Return date should be within 7 days from departure date");
	
	return inputTimeOK && returnDateOK;
}
	
	
	
public void searchOneWay(SearchContent content, OneWayList result) {
		
		//get input parameters
		String departureDate = content.getDepartureDate();
		String departureTime = content.getDepartureTime(); 
		if (departureTime.length() == 4 && !departureTime.startsWith("0")) 
			departureTime = "0" + departureTime;
		departureTime += ":00";
		
		Station departure = content.getDepartureStation();
		Station destination = content.getDestinationStation();
		
		int numberOfSeats = content.getNumberOfSeats();
		String trainType = content.getTrainType();
		String numberOfConnections = content.getNumberOfConnections();
		boolean isExactTime = content.isExactTime();
		
		Station[] allStations = Station.values();
		
	
		//find direct trip, if isExactTime == true, search only departure time is exact.
		List<Segment> directTrips= getDirectTripSorted(departure, destination, departureDate, departureTime, isExactTime, numberOfSeats);
		for (Segment s: directTrips) {
			if (trainType.equals("Express") && !s.getBound().endsWith("00"))
				continue;
			else if (trainType.equals("Regular") && s.getBound().endsWith("00"))
				continue;
			OneWayTrip oneTrip = new OneWayTrip(departureDate, numberOfSeats);
			List<Segment> connection = new ArrayList<>();
			connection.add(s);
			oneTrip.setConnections(connection);
			result.getFirstFive().add(oneTrip);
		}
		
		//return result if number of connections is None
		if (numberOfConnections.equals("None")) {
			sorting(result);
			return;
		}
		
		//adjust SB and NB iteration index
		int start = departure.getIndex();
		int end = destination.getIndex();
		if ((destination.getIndex()-departure.getIndex()) <0) {
			start = -start;
			end = -end;
		}
		
		//find two segments trip. Check trainType requirement is met. 
		for (int i = start+1; i < end; i++) {
			Station oneStop = allStations[Math.abs(i)];
			List<Segment> firstTrip = getDirectTripSorted(departure, oneStop, departureDate, departureTime, isExactTime, numberOfSeats);
			List<Segment> lastTrip = getDirectTripSorted(oneStop, destination, departureDate, departureTime, false, numberOfSeats);
			List<List<Segment>> oneStopTrips= getOneStopTrip(firstTrip, lastTrip);
			for (List<Segment> l: oneStopTrips) {
				boolean isTypeRight = checkTrainType(trainType, l);
				if (isTypeRight) {
					OneWayTrip oneTrip = new OneWayTrip(departureDate, numberOfSeats);
					oneTrip.setConnections(l);
					result.getFirstFive().add(oneTrip);
				}
			}
		}
		
		//return result if number of connections is One
		if (numberOfConnections.equals("One")) {
			sorting(result);
			return;
		}
		
		//find three segments trip
		outer: for (int i=start+1; i < end-1; i++) {
			Station firstStop = allStations[Math.abs(i)];
			for (int j=i+1; j < destination.getIndex(); j++) {
				Station secondStop = allStations[Math.abs(j)];
				List<Segment> firstTrip = getDirectTripSorted(departure, firstStop, departureDate, departureTime, isExactTime, numberOfSeats);
				List<Segment> secondTrip = getDirectTripSorted(firstStop, secondStop, departureDate, departureTime, false, numberOfSeats);
				List<Segment> lastTrip = getDirectTripSorted(secondStop, destination, departureDate, departureTime, false, numberOfSeats);
				List<List<Segment>> twoStopTrips= getTwoStopTrip(firstTrip, secondTrip, lastTrip);
				for (List<Segment> l: twoStopTrips) {
					boolean isTypeRight = checkTrainType(trainType, l);
					if (isTypeRight) {
						OneWayTrip oneTrip = new OneWayTrip(departureDate, numberOfSeats);
						oneTrip.setConnections(l);
						result.getFirstFive().add(oneTrip);
						if (result.getFirstFive().size() >= NUMBER_OF_TRIP_RETURNED) 
							break outer;
					}
				}
			}
		}
		//sort all trips and return result
		sorting(result);

	}
	//Search for two stops trip, verify connection time is within 2 hours
	private List<List<Segment>> getTwoStopTrip (List<Segment> firstPart, List<Segment> secondPart, List<Segment> lastPart){
		List<List<Segment>> result = new ArrayList<>();
		if (firstPart.size()==0 || secondPart.size()==0 || lastPart.size() ==0) return result;
		for (Segment last:lastPart) {
			if (last.getArrivalTime()==null) continue;
			for (Segment second: secondPart) {
				if (second.getArrivalTime() == null) continue;
				for (Segment first:firstPart) {
					if (first.getArrivalTime()==null) continue;
					if (checkConnectionTime(first.getArrivalTime(), second.getDepartureTime())
							&& !first.getBound().equals(second.getBound())
							&& checkConnectionTime(second.getArrivalTime(), last.getDepartureTime()) 
							&& !second.getBound().equals(last.getBound())) {
									List<Segment> foundOne = new ArrayList<>();
									foundOne.add(first);
									foundOne.add(second);
									foundOne.add(last);
									result.add(foundOne);
						
					}
				}
			}
		}
		return result;
	}
	
	
	//Search for arrival time at stop not later than departure time, verify connection is within 2 hours.
	private List<List<Segment>> getOneStopTrip (List<Segment> firstPart, List<Segment> secondPart) {
		List<List<Segment>> result = new ArrayList<>();
		if (firstPart.size()==0 || secondPart.size()==0) return result;
		
		for (Segment first : firstPart) {
			if (first.getArrivalTime()==null) continue;
			for (Segment second: secondPart) {
				if (second.getArrivalTime()==null) continue;
				boolean within2hour = checkConnectionTime(first.getArrivalTime(), second.getDepartureTime());
				if (within2hour && !first.getBound().equals(second.getBound())) {
					List<Segment> foundOne = new ArrayList<>();
					foundOne.add(first);
					foundOne.add(second);
					result.add(foundOne);
				}
			}
		}
		return result;
	}
	
	//search for direct trip
	private List<Segment> getDirectTripSorted (Station departure, Station destination, String departureDate, String departureTime, 
											boolean isExactTime, int numberOfSeats) {
		//Get direction for "NB" or "SB" 
		String direction = (destination.getIndex()-departure.getIndex()) >0? "SB" : "NB";
		//Obtain list of train based on departure time and station
		List<TrainSchedule> departGroup;
		if (isExactTime) {
			departGroup = scheduleRepo.findByStopAndDepartTime(departure, departureTime);
		} else {
			departGroup = scheduleRepo.findByStopAndDepartTimeGreaterThanEqual(departure, departureTime);
		}
	
		List<Segment> directGroup = new ArrayList<>();
		
		for (TrainSchedule t : departGroup) {
			if (t.getTrain().getBound().contains(direction)) {
				String departTime = t.getDepartTime();
				String bound = t.getTrain().getBound();
				Train train = t.getTrain();
				
				//check is seat on this train at this station is available
				TrainStatus status = trainStatusRepo.findByTrainAndDate(train, departureDate);
				if ((status.getSeatStatus().get(departure) + numberOfSeats) > train.getCapacity() || status.isCancelled()) 
					continue;
				
				String arrivalTime = null;
				TrainSchedule arrival = scheduleRepo.findByStopAndTrain(destination,train);
				if (arrival != null) {
					arrivalTime = arrival.getArrivalTime();
					directGroup.add(new Segment(bound, departTime, arrivalTime, departure, destination));
				}
			}
		}
		if (directGroup.size() >0) Collections.sort(directGroup);

		return directGroup;
	}
	
	//verify departureTime vs current time
	private boolean verifyDepartureTime(String departureDate, String departureTime) {
		
		Date current = new Date();
		Date later = new Date();
		for (int i = 1; i < 5; i++)
			later.setTime(later.getTime()+7*24*60*60*1000);
		Date userInput = null;
		SimpleDateFormat format = new SimpleDateFormat(fullPattern);
		String updatedDepartureTime = departureDate+" "+departureTime;
		try {
			userInput = format.parse(updatedDepartureTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		userInput.setTime(userInput.getTime() - 1000*(60*5-1));
		return current.before(userInput) && userInput.before(later);
	}
	
	//verify return date is 7 days within departure date
	private boolean verifyReturnDate(String departureDate, String returnDate) {
		SimpleDateFormat format = new SimpleDateFormat(datePattern);
		Date departD = null;
		Date returnD = null;
		try {
			departD = format.parse(departureDate);
			returnD = format.parse(returnDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		departD.setTime(departD.getTime() + (7*24*60*60+1)*1000);
		return returnD.before(departD);
	}
	
	
	//verify connection is within 2 hour
	private boolean checkConnectionTime(String arrivalTime, String departureTime) {
		SimpleDateFormat format = new SimpleDateFormat(timePattern);
		Date arrival = new Date();
		Date nextDepart = new Date();
		try {
			arrival = format.parse(arrivalTime);
			nextDepart = format.parse(departureTime);
		}catch (ParseException e) {
			e.printStackTrace();
		}
		if (arrival.before(nextDepart)) {
			arrival.setTime(arrival.getTime() + 1000*60*60*2);
			if (nextDepart.before(arrival)) return true;
		}
		return false;
	}
	
	//verify train type is correct for trips with connections
	private boolean checkTrainType(String type, List<Segment> segmentList) {
		List<String> bounds = new ArrayList<>();
		for (Segment s: segmentList) {
			if (s.getBound().endsWith("00"))
				bounds.add("Express");
			else 
				bounds.add("Regular");
		}
		if (type.equals("Express") &&!bounds.contains("Express"))
			return false;
		if (type.equals("Regular") && bounds.contains("Express"))
			return false;
		return true;
	}
	//sort based on arrival time for the trips.
	private void sorting(OneWayList result) {
		if (result.getFirstFive().size() != 0)  {
			Collections.sort(result.getFirstFive());
			//get first five trips
			if (result.getFirstFive().size() > NUMBER_OF_TRIP_RETURNED)
				result.setFirstFive(result.getFirstFive().subList(0, NUMBER_OF_TRIP_RETURNED));
		}else {
			result.setMessage("No bookable trip found!");
		}
	}
	
}
