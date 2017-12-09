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
import com.cmpe275.cusr.repository.ScheduleRepository;


@Service
public class TrainServiceImpl implements TrainService {
	
	@Autowired
	private ScheduleRepository scheduleRepo;
	
	private static int NUMBER_OF_TRIP_RETURNED = 5;

	private static String fullPattern = "yyyy-MM-dd HH:mm:ss";
	private static String timePattern = "HH:mm:ss";
	
	public OneWayList searchOneWay(SearchContent content) {
		
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
		boolean isRoundTrip = content.isRoundTrip();
		boolean isExactTime = content.isExactTime();
		
		Station[] allStations = Station.values();
		
		//result container
		OneWayList result = new OneWayList();
		
		//verify departure date and time no less than 5 minutes 
		boolean inputTimeOK= verifyDepartureTime(departureDate, departureTime);
		if (!inputTimeOK) {
			result.setMessage("Departure time is within 5 minutes from now!");
			return result;
		}
		
		//find direct trip, if isExactTime == true, search only departure time is exact.
		List<Segment> directTrips= getDirectTripSorted(departure,destination,departureTime, isExactTime);
		for (Segment s: directTrips) {
			OneWayTrip oneTrip = new OneWayTrip(departureDate, numberOfSeats);
			List<Segment> connection = new ArrayList<>();
			connection.add(s);
			oneTrip.setConnections(connection);
			result.getFirstFive().add(oneTrip);
		}
		
		//adjust SB and NB iteration index
		int start = departure.getIndex();
		int end = destination.getIndex();
		if ((destination.getIndex()-departure.getIndex()) <0) {
			start = -start;
			end = -end;
		}
		
		//find one change trip, some bug exits. NB need to be i--
		for (int i = start+1; i < end; i++) {
			Station oneStop = allStations[Math.abs(i)];
			List<Segment> firstTrip = getDirectTripSorted(departure, oneStop, departureTime, isExactTime);
			List<Segment> lastTrip = getDirectTripSorted(oneStop, destination, departureTime, false);
			List<List<Segment>> oneStopTrips= getOneStopTrip(firstTrip, lastTrip);
			for (List<Segment> l: oneStopTrips) {
				OneWayTrip oneTrip = new OneWayTrip(departureDate, numberOfSeats);
				oneTrip.setConnections(l);
				result.getFirstFive().add(oneTrip);
			}
		}
		
		//find two change trip
		for (int i=start+1; i < end-1; i++) {
			Station firstStop = allStations[Math.abs(i)];
			for (int j=i+1; j < destination.getIndex(); j++) {
				Station secondStop = allStations[Math.abs(j)];
				List<Segment> firstTrip = getDirectTripSorted(departure, firstStop, departureTime, isExactTime);
				List<Segment> secondTrip = getDirectTripSorted(firstStop, secondStop, departureTime, false);
				List<Segment> lastTrip = getDirectTripSorted(secondStop, destination, departureTime, false);
				List<List<Segment>> twoStopTrips= getTwoStopTrip(firstTrip, secondTrip, lastTrip);
				for (List<Segment> l: twoStopTrips) {
					OneWayTrip oneTrip = new OneWayTrip(departureDate, numberOfSeats);
					oneTrip.setConnections(l);
					result.getFirstFive().add(oneTrip);
				}
			}
		}
		
		//sorting based on arrival time
		if (result.getFirstFive().size() != 0)  {
			Collections.sort(result.getFirstFive());
			//get first five trips
			if (result.getFirstFive().size() > NUMBER_OF_TRIP_RETURNED)
				result.setFirstFive(result.getFirstFive().subList(0, NUMBER_OF_TRIP_RETURNED));
		} 
		return result;

	}
	//Search for two stops trip
	private List<List<Segment>> getTwoStopTrip (List<Segment> firstPart, List<Segment> secondPart, List<Segment> lastPart){
		List<List<Segment>> result = new ArrayList<>();
		if (firstPart.size()==0 || secondPart.size()==0 || lastPart.size() ==0) return result;
		for (Segment last:lastPart) {
			if (last.getArrivalTime()==null) continue;
			for (Segment second: secondPart) {
				if (second.getArrivalTime() == null) continue;
				for (Segment first:firstPart) {
					if (first.getArrivalTime()==null) continue;
					if (first.getArrivalTime().compareTo(second.getDepartureTime())<0 
							&& !first.getBound().equals(second.getBound())
							&& second.getArrivalTime().compareTo(last.getDepartureTime())<0 
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
	
	
	//Search for arrival time at stop not later than departure time 
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
	private List<Segment> getDirectTripSorted (Station departure, Station destination, String departureTime, boolean isExactTime) {
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
		Date userInput = null;
		SimpleDateFormat format = new SimpleDateFormat(fullPattern);
		String updatedDepartureTime = departureDate+" "+departureTime;
		try {
			userInput = format.parse(updatedDepartureTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		userInput.setTime(userInput.getTime() - 1000*(60*5-1));
		return current.before(userInput);
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
	
}
