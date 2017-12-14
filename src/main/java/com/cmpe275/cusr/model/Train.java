package com.cmpe275.cusr.model;


import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.FetchType;


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
	
	private String departureTime;
	
	@Column(name="TYPE", nullable=false)
	private String type;
	
	@Column(name="CAPACITY")
	private int capacity;

	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn (name="TRAIN_ID")
	private List<TrainStatus> trainStatus;
	
	public Train() {
		super();
	}
	
	public Train(String bound, String departureTime, String type) {
		super();
		this.bound = bound;
		this.departureTime = departureTime;
		this.type = type;
		this.capacity = 1000;
	}
	

	public long getTrainId() {
		return trainId;
	}

	public String getBound() {
		return bound;
	}

	public void setBound(String bound) {
		this.bound = bound;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<TrainStatus> getTrainStatus() {
		return trainStatus;
	}

	public void setTrainStatus(List<TrainStatus> trainStatus) {
		this.trainStatus = trainStatus;
	}

		//for testing purpose
		@Override
		public String toString() {
			StringBuilder result = new StringBuilder();
			result.append("TrainBound: ");
			result.append(getBound());
			result.append("\nDeparture Time: ");
			result.append(getDepartureTime());
			result.append("\nExpress or Regular: ");
			result.append(getType());
			result.append("\nCapacity: ");
			result.append(getCapacity());
			return result.toString();
		}

}

