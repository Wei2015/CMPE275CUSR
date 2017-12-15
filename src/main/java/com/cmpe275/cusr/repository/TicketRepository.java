package com.cmpe275.cusr.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cmpe275.cusr.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	@Query("SELECT t from Ticket t WHERE t.user.userId = ?1")
	ArrayList<Ticket> findTicketsByUserId(long ID);
}