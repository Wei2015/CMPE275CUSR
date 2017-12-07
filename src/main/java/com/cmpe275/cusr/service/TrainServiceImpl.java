package com.cmpe275.cusr.service;

import java.util.ArrayList;
import java.util.Collections;
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
	
	public OneWayList searchOneWay(SearchContent content) {
		
		//get input parameters
		String departureDate = content.getDepartureDate();
		String departureTime = content.getDepartureTime() + ":00";
		
		Station departure = content.getDepartureStation();
		Station destination = content.getDestinationStation();
		
		int numberOfSeats = content.getNumberOfSeats();
		String trainType = content.getTrainType();
		
		Station[] allStations = Station.values();
		//result container
		OneWayList result = new OneWayList();
		
		//find direct trip, tested OK!
		List<Segment> directTrips= getDirectTrip(departure,destination,departureTime);
		for (Segment s: directTrips) {
			OneWayTrip oneTrip = new OneWayTrip(departureDate, numberOfSeats);
			List<Segment> connection = new ArrayList<>();
			connection.add(s);
			oneTrip.setConnections(connection);
			result.getFirstFive().add(oneTrip);
		}
		
		//find one change trip, some bug exits. NB need to be i--
		for (int i = departure.getIndex()+1; i < destination.getIndex(); i++) {
			Station oneStop = allStations[i];
			List<Segment> firstTrip = getDirectTrip(departure, oneStop, departureTime);
			List<Segment> lastTrip = getDirectTrip(oneStop, destination, departureTime);
			List<List<Segment>> oneStopTrips= getOneStopTrip(firstTrip, lastTrip);
			for (List<Segment> l: oneStopTrips) {
				OneWayTrip oneTrip = new OneWayTrip(departureDate, numberOfSeats);
				oneTrip.setConnections(l);
				result.getFirstFive().add(oneTrip);
			}
		}
		
		//find two change trip
		for (int i=departure.getIndex()+1; i < destination.getIndex()-1; i++) {
			Station firstStop = allStations[i];
			for (int j=i+1; j < destination.getIndex(); j++) {
				Station secondStop = allStations[j];
				List<Segment> firstTrip = getDirectTrip(departure, firstStop, departureTime);
				List<Segment> secondTrip = getDirectTrip(firstStop, secondStop, departureTime);
				List<Segment> lastTrip = getDirectTrip(secondStop, destination, departureTime);
				
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
	
	//Search for arrival time at stop not later than departure time 
	private List<List<Segment>> getOneStopTrip (List<Segment> firstPart, List<Segment> secondPart) {
		List<List<Segment>> result = new ArrayList<>();
		if (firstPart == null || secondPart == null||firstPart.size()==0 || secondPart.size()==0) return result;
		
		for (Segment first : firstPart) {
			if (first.getArrivalTime()==null) continue;
			for (Segment second: secondPart) {
				if (first.getArrivalTime().compareTo(second.getDepartureTime())<0 && !first.getBound().equals(second.getBound())) {
					List<Segment> foundOne = new ArrayList<>();
					foundOne.add(first);
					foundOne.add(second);
					result.add(foundOne);
				}
			}
		}
		return result;
	}
	
	
	private List<Segment> getDirectTrip (Station departure, Station destination, String departureTime) {
		//Get direction for "NB" or "SB" 
		String direction = (destination.getIndex()-departure.getIndex()) >0? "SB" : "NB";
		//Obtain list of train based on departure time and station
		List<TrainSchedule> departGroup = scheduleRepo.findByStopAndDepartTimeGreaterThanEqual(departure, departureTime);
		
		List<Segment> directGroup = new ArrayList<>();
		
		for (TrainSchedule t : departGroup) {
			if (t.getTrain().getBound().contains(direction)) {
				String departTime = t.getDepartTime();
				String bound = t.getTrain().getBound();
				Train train = t.getTrain();
				String arrivalTime = null;
				TrainSchedule arrival = scheduleRepo.findByStopAndTrain(destination,train);
				if (arrival != null ) {
					arrivalTime = arrival.getArrivalTime();
				}
				directGroup.add(new Segment(bound, departTime, arrivalTime, departure, destination));
			}
		}

		return directGroup;
	}
			
	
}

