package com.cmpe275.cusr.model;

import java.util.ArrayList;
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
	
	@Column(name="PASSENGER", nullable=false)
	private ArrayList<String> passenger = new ArrayList<String>();
	
	@Column (name="PRICE", nullable=false)
	private double price;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID", nullable=false)
	private User user;
	
	@Column(name="DEPART_DATE", nullable=false)
	private String departDate;
	
	@Column(name="RETURN_DATE", nullable=true)
	private String returnDate;
	
	@Column(name="DEPART_SEGMENT1_DEPART_TIME", nullable=false)
	private String departSegment1DepartTime;
	
	@Column(name="DEPART_SEGMENT1_ARRIVAL_TIME", nullable=false)
	private String departSegment1ArrivalTime;
	
	@Column(name="DEPART_SEGMENT2_DEPART_TIME", nullable=true)
	private String departSegment2DepartTime;
	
	@Column(name="DEPART_SEGMENT2_ARRIVAL_TIME", nullable=true)
	private String departSegment2ArrivalTime;
	
	@Column(name="DEPART_SEGMENT3_DEPART_TIME", nullable=true)
	private String departSegment3DepartTime;
	
	@Column(name="DEPART_SEGMENT3_ARRIVAL_TIME", nullable=true)
	private String departSegment3ArrivalTime;
	
	@Column(name="RETURN_SEGMENT1_DEPART_TIME", nullable=true)
	private String returnSegment1DepartTime;
	
	@Column(name="RETURN_SEGMENT1_ARRIVAL_TIME", nullable=true)
	private String returnSegment1ArrivalTime;
	
	@Column(name="RETURN_SEGMENT2_DEPART_TIME", nullable=true)
	private String returnSegment2DepartTime;
	
	@Column(name="RETURN_SEGMENT2_ARRIVAL_TIME", nullable=true)
	private String returnSegment2ArrivalTime;
	
	@Column(name="RETURN_SEGMENT3_DEPART_TIME", nullable=true)
	private String returnSegment3DepartTime;
	
	@Column(name="RETURN_SEGMENT3_ARRIVAL_TIME", nullable=true)
	private String returnSegment3ArrivalTime;
	
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

	public ArrayList<String> getPassenger() {
		return passenger;
	}

	public void setPassenger(ArrayList<String> passenger) {
		this.passenger = passenger;
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

	public String getDepartDate() {
		return departDate;
	}

	public void setDepartDate(String departDate) {
		this.departDate = departDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getDepartSegment1DepartTime() {
		return departSegment1DepartTime;
	}

	public void setDepartSegment1DepartTime(String departSegment1DepartTime) {
		this.departSegment1DepartTime = departSegment1DepartTime;
	}

	public String getDepartSegment1ArrivalTime() {
		return departSegment1ArrivalTime;
	}

	public void setDepartSegment1ArrivalTime(String departSegment1ArrivalTime) {
		this.departSegment1ArrivalTime = departSegment1ArrivalTime;
	}

	public String getDepartSegment2DepartTime() {
		return departSegment2DepartTime;
	}

	public void setDepartSegment2DepartTime(String departSegment2DepartTime) {
		this.departSegment2DepartTime = departSegment2DepartTime;
	}

	public String getDepartSegment2ArrivalTime() {
		return departSegment2ArrivalTime;
	}

	public void setDepartSegment2ArrivalTime(String departSegment2ArrivalTime) {
		this.departSegment2ArrivalTime = departSegment2ArrivalTime;
	}

	public String getDepartSegment3DepartTime() {
		return departSegment3DepartTime;
	}

	public void setDepartSegment3DepartTime(String departSegment3DepartTime) {
		this.departSegment3DepartTime = departSegment3DepartTime;
	}

	public String getDepartSegment3ArrivalTime() {
		return departSegment3ArrivalTime;
	}

	public void setDepartSegment3ArrivalTime(String departSegment3ArrivalTime) {
		this.departSegment3ArrivalTime = departSegment3ArrivalTime;
	}

	public String getReturnSegment1DepartTime() {
		return returnSegment1DepartTime;
	}

	public void setReturnSegment1DepartTime(String returnSegment1DepartTime) {
		this.returnSegment1DepartTime = returnSegment1DepartTime;
	}

	public String getReturnSegment1ArrivalTime() {
		return returnSegment1ArrivalTime;
	}

	public void setReturnSegment1ArrivalTime(String returnSegment1ArrivalTime) {
		this.returnSegment1ArrivalTime = returnSegment1ArrivalTime;
	}

	public String getReturnSegment2DepartTime() {
		return returnSegment2DepartTime;
	}

	public void setReturnSegment2DepartTime(String returnSegment2DepartTime) {
		this.returnSegment2DepartTime = returnSegment2DepartTime;
	}

	public String getReturnSegment2ArrivalTime() {
		return returnSegment2ArrivalTime;
	}

	public void setReturnSegment2ArrivalTime(String returnSegment2ArrivalTime) {
		this.returnSegment2ArrivalTime = returnSegment2ArrivalTime;
	}

	public String getReturnSegment3DepartTime() {
		return returnSegment3DepartTime;
	}

	public void setReturnSegment3DepartTime(String returnSegment3DepartTime) {
		this.returnSegment3DepartTime = returnSegment3DepartTime;
	}

	public String getReturnSegment3ArrivalTime() {
		return returnSegment3ArrivalTime;
	}

	public void setReturnSegment3ArrivalTime(String returnSegment3ArrivalTime) {
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