package com.cmpe275.cusr.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name="TICKET")
public class Ticket {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TICKET_ID")
	private long ticketId;
	
	@Column(name="NUM_OF_SEATS", nullable=false)
	private int numOfSeats;
	
	@Column(name="DATE", nullable=false)
	private java.util.Calendar date;
	
	@Column(name="RETURN_DATE", nullable=true)
	private java.util.Calendar ReturnDate;
	
	@Column(name="DEPARTURE_TIME", nullable=false)
	@Temporal(TemporalType.TIME)
	private java.util.Date departureTime;
	
	@Column(name="ARRIVAL_TIME", nullable=false)
	@Temporal(TemporalType.TIME)
	private java.util.Date arrivalTime;
	
	@Column(name="RETURN_DEPARTURE_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private java.util.Date returnDepartureTime;
	
	@Column(name="RETURN_ARRIVAL_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private java.util.Date returnArrivalTime;
	
	@Column(name="DEPARTURE_STATION", nullable=false)
	private Character departureStation;
	
	@Column(name="ARRIVAL_STATION", nullable=false)
	private Character arrivalStation;
	
	@Column(name="IS_CANCELLED", nullable=false)
	private boolean isCancelled;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User user;
	
	@ManyToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="TRAINS_SELECTED", 
				joinColumns= {@JoinColumn(name="TICKET_ID", referencedColumnName="TICKET_ID")}, 
				inverseJoinColumns= {@JoinColumn(name="SELECTED_TRAIN_ID",referencedColumnName="TRAIN_ID")}) 
	private List<Train> trains;
	
	public Ticket() {
		super();
	}
	
	public long getTicketId() {
		return ticketId;
	}
	
	public java.util.Calendar getDate() {
		return date;
	}
	
	public void setDate(java.util.Calendar date) {
		this.date = date;
	}
	
	public java.util.Date getDepartureTime() {
		return departureTime;
	}
	
	public void setDepartureTime(java.util.Date departureTime) {
		this.departureTime = departureTime;
	}

	public java.util.Date getArrivalTime() {
		return arrivalTime;
	}
	
	public void setArrivalTime(java.util.Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public Character getDepartureStation() {
		return departureStation;
	}
	
	public void setDepartureStation(Character departureStation) {
		this.departureStation = departureStation;
	}
	
	public Character getArrivalStation() {
		return arrivalStation;
	}
	
	public void setArrivalStation(Character arrivalStation) {
		this.arrivalStation = arrivalStation;
	}
	
	public boolean getIsCancelled() {
		return isCancelled;
	}

	public void setIsCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Train> getTrains() {
		return trains;
	}
	
	public void setTrains(List<Train> trains) {
		this.trains = trains;
	}
}
