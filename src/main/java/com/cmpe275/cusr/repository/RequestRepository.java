package com.cmpe275.cusr.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;


import com.cmpe275.cusr.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
	
	
	@Query("SELECT COUNT(REQUEST_ID) from Request r WHERE r.date = ?1")
	int getTotalCount(String date);
	
	@Query("SELECT COUNT(REQUEST_ID) from Request r WHERE r.numberOfConnections = ?1 AND r.date = ?2")
	int getCountOnConnectionTypeAndDate(String numberOfConnections, String date);
	
	@Query("SELECT SUM(timeCost) from Request r WHERE r.numberOfConnections = ?1 AND r.date = ?2")
	long getEachSearchTime(String numberOfConnections, String date);
	
}
