package com.cmpe275.cusr.model;


import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.EnumType;


enum Station {
    A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z
}


@Entity
@Table(name="TRAIN")
public class Train {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TRAIN_ID")
	private long trainId;
	
	@Column(name="BOUND", nullable=false)
	private String bound;
	
	@Column(name="DEPARTURE_TIME", nullable=false)
	@Temporal(TemporalType.TIME)
	private java.util.Date departureTime;
	
	@Column(name="TYPE", nullable=false)
	private String type;
	
	@Column(name="CAPACITY")
	private int capacity;
	
	@ElementCollection
    @CollectionTable(name="TRAIN_SCHEDULE")
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name="STATION")
    @Column(name="DEPART_TIME_AT_STATION")
	@Temporal(TemporalType.TIME)
    private Map<Station, java.util.Date> trainTimeTable;
	
	@ElementCollection
    @CollectionTable(name="TRAIN_STATUS")
    @MapKeyJoinColumn(name="AVAILABLE_DATE", nullable=false)
    @Column(name="AVAILABLE_SEATS")
    private Map<java.util.Calendar, Integer> trainStatus;
	
}

