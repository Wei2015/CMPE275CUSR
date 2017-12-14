package com.cmpe275.cusr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmpe275.cusr.model.Train;
import com.cmpe275.cusr.model.TrainStatus;

public interface TrainStatusRepository extends JpaRepository<TrainStatus, Long>{
	
	TrainStatus findByTrainAndDate(Train train, String date);

}
