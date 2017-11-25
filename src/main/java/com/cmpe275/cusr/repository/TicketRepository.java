package com.cmpe275.cusr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmpe275.cusr.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
}