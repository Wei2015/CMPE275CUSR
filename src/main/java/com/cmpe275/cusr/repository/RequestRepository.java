package com.cmpe275.cusr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cmpe275.cusr.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
	
	@Query("SELECT COUNT(*) from Request r WHERE r.connectionType = ?1")
	int getCountOnType(String connectionType);

}
