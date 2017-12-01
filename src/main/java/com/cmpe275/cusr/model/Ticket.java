package com.cmpe275.cusr.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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


@Entity
@Table(name="TICKET")
public class Ticket {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TICKET_ID")
	private long ticketId;
	
	@Column(name="IS_CANCELLED", nullable=false)
	private boolean isCancelled;
	
	@Column(name="NUM_OF_SEATS", nullable=false)
	private int numOfSeats;
	
	@Column (name="PRICE", nullable=false)
	private double price;
	
	@Column(name="DEPART_DATE", nullable=false)
	private java.util.Calendar departDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User user;
	
	@Column(name="RETURN_DATE", nullable=true)
	private java.util.Calendar returnDate;
	
	@Column(name="DEPART_TIME", nullable=false)
	@Temporal(TemporalType.TIME)
	private java.util.Date departTime;
	
	@Column(name="STOP1_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private java.util.Date stop1Time;
	
	@Column(name="STOP2_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private java.util.Date stop2Time;
	
	@Column(name="ARRIVAL_TIME", nullable=false)
	@Temporal(TemporalType.TIME)
	private java.util.Date arrivalTime;
	
	@Column(name="RETURN_DEPART_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private java.util.Date returnDepartTime;
	
	@Column(name="RETURN_STOP1_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private java.util.Date returnStop1Time;
	
	@Column(name="RETURN_STOP2_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private java.util.Date returnStop2Time;
	
	@Column(name="RETURN_ARRIVAL_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private java.util.Date returnArrivalTime;
	
	@Column(name="DEPART_STATION", nullable=false)
	private Character departStation;
	
	@Column(name="ARRIVAL_STATION", nullable=false)
	private Character arrivalStation;
	
	@Column(name="STOP1", nullable=true)
	private Character stop1;
	
	@Column(name="STOP2", nullable=true)
	private Character stop2;
	
	@Column(name="RETURN_STOP1", nullable=true)
	private Character returnStop1;
	
	@Column(name="RETURN_STOP2", nullable=true)
	private Character returnStop2;
	
	@ManyToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="DEPART_TRAINS", 
				joinColumns= {@JoinColumn(name="TICKET_ID", referencedColumnName="TICKET_ID")}, 
				inverseJoinColumns= {@JoinColumn(name="DEPART_TRAIN_ID",referencedColumnName="TRAIN_ID")}) 
	private List<Train> trains;
	
	@ManyToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="RETURN_TRAINS", 
				joinColumns= {@JoinColumn(name="TICKET_ID", referencedColumnName="TICKET_ID")}, 
				inverseJoinColumns= {@JoinColumn(name="RETURN_TRAIN_ID",referencedColumnName="TRAIN_ID")}) 
	private List<Train> returnTrains;
	
	public Ticket() {
		super();
	}
	
	public long getTicketId() {
		return ticketId;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public int getNumOfSeats() {
		return numOfSeats;
	}

	public void setNumOfSeats(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public java.util.Calendar getDepartDate() {
		return departDate;
	}

	public void setDepartDate(java.util.Calendar departDate) {
		this.departDate = departDate;
	}

	public java.util.Calendar getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(java.util.Calendar returnDate) {
		this.returnDate = returnDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public java.util.Date getDepartTime() {
		return departTime;
	}

	public void setDepartTime(java.util.Date departTime) {
		this.departTime = departTime;
	}

	public java.util.Date getStop1Time() {
		return stop1Time;
	}

	public void setStop1Time(java.util.Date stop1Time) {
		this.stop1Time = stop1Time;
	}

	public java.util.Date getStop2Time() {
		return stop2Time;
	}

	public void setStop2Time(java.util.Date stop2Time) {
		this.stop2Time = stop2Time;
	}

	public java.util.Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(java.util.Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public java.util.Date getReturnDepartTime() {
		return returnDepartTime;
	}

	public void setReturnDepartTime(java.util.Date returnDepartTime) {
		this.returnDepartTime = returnDepartTime;
	}

	public java.util.Date getReturnStop1Time() {
		return returnStop1Time;
	}

	public void setReturnStop1Time(java.util.Date returnStop1Time) {
		this.returnStop1Time = returnStop1Time;
	}

	public java.util.Date getReturnStop2Time() {
		return returnStop2Time;
	}

	public void setReturnStop2Time(java.util.Date returnStop2Time) {
		this.returnStop2Time = returnStop2Time;
	}

	public java.util.Date getReturnArrivalTime() {
		return returnArrivalTime;
	}

	public void setReturnArrivalTime(java.util.Date returnArrivalTime) {
		this.returnArrivalTime = returnArrivalTime;
	}

	public Character getDepartStation() {
		return departStation;
	}

	public void setDepartStation(Character departStation) {
		this.departStation = departStation;
	}

	public Character getArrivalStation() {
		return arrivalStation;
	}

	public void setArrivalStation(Character arrivalStation) {
		this.arrivalStation = arrivalStation;
	}

	public Character getStop1() {
		return stop1;
	}

	public void setStop1(Character stop1) {
		this.stop1 = stop1;
	}

	public Character getStop2() {
		return stop2;
	}

	public void setStop2(Character stop2) {
		this.stop2 = stop2;
	}

	public Character getReturnStop1() {
		return returnStop1;
	}

	public void setReturnStop1(Character returnStop1) {
		this.returnStop1 = returnStop1;
	}

	public Character getReturnStop2() {
		return returnStop2;
	}

	public void setReturnStop2(Character returnStop2) {
		this.returnStop2 = returnStop2;
	}

	public List<Train> getTrains() {
		return trains;
	}

	public void setTrains(List<Train> trains) {
		this.trains = trains;
	}

	public List<Train> getReturnTrains() {
		return returnTrains;
	}

	public void setReturnTrains(List<Train> returnTrains) {
		this.returnTrains = returnTrains;
	}
	
}
