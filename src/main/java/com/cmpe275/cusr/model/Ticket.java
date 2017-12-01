package com.cmpe275.cusr.model;


import java.util.Date;
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
	private Date departDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User user;
	
	@Column(name="RETURN_DATE", nullable=true)
	private Date returnDate;
	
	@Column(name="DEPART_TIME", nullable=false)
	@Temporal(TemporalType.TIME)
	private Date departTime;
	
	@Column(name="STOP1_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private Date stop1Time;
	
	@Column(name="STOP2_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private Date stop2Time;
	
	@Column(name="ARRIVAL_TIME", nullable=false)
	@Temporal(TemporalType.TIME)
	private Date arrivalTime;
	
	@Column(name="RETURN_DEPART_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private Date returnDepartTime;
	
	@Column(name="RETURN_STOP1_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private Date returnStop1Time;
	
	@Column(name="RETURN_STOP2_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private Date returnStop2Time;
	
	@Column(name="RETURN_ARRIVAL_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private Date returnArrivalTime;
	
	@Column(name="DEPART_STATION", nullable=false)
	private Station departStation;
	
	@Column(name="ARRIVAL_STATION", nullable=false)
	private Station arrivalStation;
	
	@Column(name="STOP1", nullable=true)
	private Station stop1;
	
	@Column(name="STOP2", nullable=true)
	private Station stop2;
	
	@Column(name="RETURN_STOP1", nullable=true)
	private Station returnStop1;
	
	@Column(name="RETURN_STOP2", nullable=true)
	private Station returnStop2;
	
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

	public Date getDepartDate() {
		return departDate;
	}

	public void setDepartDate(Date departDate) {
		this.departDate = departDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Date getDepartTime() {
		return departTime;
	}

	public void setDepartTime(Date departTime) {
		this.departTime = departTime;
	}

	public Date getStop1Time() {
		return stop1Time;
	}

	public void setStop1Time(Date stop1Time) {
		this.stop1Time = stop1Time;
	}

	public Date getStop2Time() {
		return stop2Time;
	}

	public void setStop2Time(Date stop2Time) {
		this.stop2Time = stop2Time;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Date getReturnDepartTime() {
		return returnDepartTime;
	}

	public void setReturnDepartTime(Date returnDepartTime) {
		this.returnDepartTime = returnDepartTime;
	}

	public Date getReturnStop1Time() {
		return returnStop1Time;
	}

	public void setReturnStop1Time(Date returnStop1Time) {
		this.returnStop1Time = returnStop1Time;
	}

	public Date getReturnStop2Time() {
		return returnStop2Time;
	}

	public void setReturnStop2Time(Date returnStop2Time) {
		this.returnStop2Time = returnStop2Time;
	}

	public Date getReturnArrivalTime() {
		return returnArrivalTime;
	}

	public void setReturnArrivalTime(Date returnArrivalTime) {
		this.returnArrivalTime = returnArrivalTime;
	}

	public Station getDepartStation() {
		return departStation;
	}

	public void setDepartStation(Station departStation) {
		this.departStation = departStation;
	}

	public Station getArrivalStation() {
		return arrivalStation;
	}

	public void setArrivalStation(Station arrivalStation) {
		this.arrivalStation = arrivalStation;
	}

	public Station getStop1() {
		return stop1;
	}

	public void setStop1(Station stop1) {
		this.stop1 = stop1;
	}

	public Station getStop2() {
		return stop2;
	}

	public void setStop2(Station stop2) {
		this.stop2 = stop2;
	}

	public Station getReturnStop1() {
		return returnStop1;
	}

	public void setReturnStop1(Station returnStop1) {
		this.returnStop1 = returnStop1;
	}

	public Station getReturnStop2() {
		return returnStop2;
	}

	public void setReturnStop2(Station returnStop2) {
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