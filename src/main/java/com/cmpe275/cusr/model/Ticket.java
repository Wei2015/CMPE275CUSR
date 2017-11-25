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
	
	@Column(name="DATE", nullable=false)
	private java.util.Calendar date;
	
	@Column(name="DEPARTURE_TIME", nullable=false)
	@Temporal(TemporalType.TIME)
	private java.util.Date departureTime;
	
	@Column(name="DEPARTURE_STATION", nullable=false)
	private String departureStation;
	
	@Column(name="ARRIVAL_STATION", nullable=false)
	private String arrivalStation;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User user;
	
	
	@ManyToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinTable(name="TRAIN_SELECTED", 
				joinColumns= {@JoinColumn(name="TICKET_ID", referencedColumnName="TICKET_ID")}, 
				inverseJoinColumns= {@JoinColumn(name="TRAIN_SELECTED",referencedColumnName="TRAIN_ID")}) 
	private List<Train> trains;

}
