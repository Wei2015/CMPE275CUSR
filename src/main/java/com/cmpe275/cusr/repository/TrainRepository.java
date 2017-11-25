package com.cmpe275.cusr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmpe275.cusr.model.Train;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {
	
}