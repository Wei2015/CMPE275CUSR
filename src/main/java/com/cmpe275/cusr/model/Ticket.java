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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User user;
	
	@Column(name="DEPART_DATE", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date departDate;
	
	@Column(name="RETURN_DATE", nullable=true)
	@Temporal(TemporalType.DATE)
	private Date returnDate;
	
	@Column(name="DEPART_SEGMENT1_DEPART_TIME", nullable=false)
	@Temporal(TemporalType.TIME)
	private Date departSegment1DepartTime;
	
	@Column(name="DEPART_SEGMENT1_ARRIVAL_TIME", nullable=false)
	@Temporal(TemporalType.TIME)
	private Date departSegment1ArrivalTime;
	
	@Column(name="DEPART_SEGMENT2_DEPART_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private Date departSegment2DepartTime;
	
	@Column(name="DEPART_SEGMENT2_ARRIVAL_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private Date departSegment2ArrivalTime;
	
	@Column(name="DEPART_SEGMENT3_DEPART_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private Date departSegment3DepartTime;
	
	@Column(name="DEPART_SEGMENT3_ARRIVAL_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private Date departSegment3ArrivalTime;
	
	@Column(name="RETURN_SEGMENT1_DEPART_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private Date returnSegment1DepartTime;
	
	@Column(name="RETURN_SEGMENT1_ARRIVAL_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private Date returnSegment1ArrivalTime;
	
	@Column(name="RETURN_SEGMENT2_DEPART_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private Date returnSegment2DepartTime;
	
	@Column(name="RETURN_SEGMENT2_ARRIVAL_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private Date returnSegment2ArrivalTime;
	
	@Column(name="RETURN_SEGMENT3_DEPART_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private Date returnSegment3DepartTime;
	
	@Column(name="RETURN_SEGMENT3_ARRIVAL_TIME", nullable=true)
	@Temporal(TemporalType.TIME)
	private Date returnSegment3ArrivalTime;
	
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
	private List<Train> departTrains;
	
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDepartDate() {
		return departDate;
	}

	public void setDepartDate(Date departDate) {
		this.departDate = departDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Date getDepartSegment1DepartTime() {
		return departSegment1DepartTime;
	}

	public void setDepartSegment1DepartTime(Date departSegment1DepartTime) {
		this.departSegment1DepartTime = departSegment1DepartTime;
	}

	public Date getDepartSegment1ArrivalTime() {
		return departSegment1ArrivalTime;
	}

	public void setDepartSegment1ArrivalTime(Date departSegment1ArrivalTime) {
		this.departSegment1ArrivalTime = departSegment1ArrivalTime;
	}

	public Date getDepartSegment2DepartTime() {
		return departSegment2DepartTime;
	}

	public void setDepartSegment2DepartTime(Date departSegment2DepartTime) {
		this.departSegment2DepartTime = departSegment2DepartTime;
	}

	public Date getDepartSegment2ArrivalTime() {
		return departSegment2ArrivalTime;
	}

	public void setDepartSegment2ArrivalTime(Date departSegment2ArrivalTime) {
		this.departSegment2ArrivalTime = departSegment2ArrivalTime;
	}

	public Date getDepartSegment3DepartTime() {
		return departSegment3DepartTime;
	}

	public void setDepartSegment3DepartTime(Date departSegment3DepartTime) {
		this.departSegment3DepartTime = departSegment3DepartTime;
	}

	public Date getDepartSegment3ArrivalTime() {
		return departSegment3ArrivalTime;
	}

	public void setDepartSegment3ArrivalTime(Date departSegment3ArrivalTime) {
		this.departSegment3ArrivalTime = departSegment3ArrivalTime;
	}

	public Date getReturnSegment1DepartTime() {
		return returnSegment1DepartTime;
	}

	public void setReturnSegment1DepartTime(Date returnSegment1DepartTime) {
		this.returnSegment1DepartTime = returnSegment1DepartTime;
	}

	public Date getReturnSegment1ArrivalTime() {
		return returnSegment1ArrivalTime;
	}

	public void setReturnSegment1ArrivalTime(Date returnSegment1ArrivalTime) {
		this.returnSegment1ArrivalTime = returnSegment1ArrivalTime;
	}

	public Date getReturnSegment2DepartTime() {
		return returnSegment2DepartTime;
	}

	public void setReturnSegment2DepartTime(Date returnSegment2DepartTime) {
		this.returnSegment2DepartTime = returnSegment2DepartTime;
	}

	public Date getReturnSegment2ArrivalTime() {
		return returnSegment2ArrivalTime;
	}

	public void setReturnSegment2ArrivalTime(Date returnSegment2ArrivalTime) {
		this.returnSegment2ArrivalTime = returnSegment2ArrivalTime;
	}

	public Date getReturnSegment3DepartTime() {
		return returnSegment3DepartTime;
	}

	public void setReturnSegment3DepartTime(Date returnSegment3DepartTime) {
		this.returnSegment3DepartTime = returnSegment3DepartTime;
	}

	public Date getReturnSegment3ArrivalTime() {
		return returnSegment3ArrivalTime;
	}

	public void setReturnSegment3ArrivalTime(Date returnSegment3ArrivalTime) {
		this.returnSegment3ArrivalTime = returnSegment3ArrivalTime;
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

	public List<Train> getDepartTrains() {
		return departTrains;
	}

	public void setDepartTrains(List<Train> departTrains) {
		this.departTrains = departTrains;
	}

	public List<Train> getReturnTrains() {
		return returnTrains;
	}

	public void setReturnTrains(List<Train> returnTrains) {
		this.returnTrains = returnTrains;
	}
}