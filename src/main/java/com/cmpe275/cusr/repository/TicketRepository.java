package com.cmpe275.cusr.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cmpe275.cusr.model.Ticket;
import com.cmpe275.cusr.model.Train;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	@Query("SELECT t from Ticket t WHERE t.user.userId = ?1 AND is_cancelled = false")
	ArrayList<Ticket> findTicketsByUserId(long ID);
	
	List<Ticket> findByDepartDate(String date);
	List<Ticket> findByReturnDate(String date);
	
	//List<Ticket> findByDepartDateAndDepartTrains(String date, Train train);
	//List<Ticket> findByReturnDateAndReturnTrains(String date, Train train);
}