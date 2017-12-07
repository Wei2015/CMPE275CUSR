package com.cmpe275.cusr.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cmpe275.cusr.model.Station;
import com.cmpe275.cusr.model.Train;
import com.cmpe275.cusr.model.TrainSchedule;


public interface ScheduleRepository extends CrudRepository<TrainSchedule, Long>{
	
	
	TrainSchedule findByStopAndTrain(Station stop, Train train);
	
	List<TrainSchedule> findByStopAndDepartTimeGreaterThanEqual(Station stop, String departTime);
	
	List<TrainSchedule> findByStopAndArrivalTimeGreaterThan(Station stop, String departTime);
	
}
