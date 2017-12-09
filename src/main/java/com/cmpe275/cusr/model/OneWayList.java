package com.cmpe275.cusr.model;

import java.util.ArrayList;
import java.util.List;



public class OneWayList {

	private List<OneWayTrip> firstFive;
	
	private static int[] COUNT = {1,2,3};
	
	private String message;


	public OneWayList() {
		super();
		firstFive = new ArrayList<OneWayTrip>();
		message = "";
	}
	
	public static int[] getCOUNT() {
		return COUNT;
	}

	public static void setCOUNT(int[] cOUNT) {
		COUNT = cOUNT;
	}

	public List<OneWayTrip> getFirstFive() {
		return firstFive;
	}

	public void setFirstFive(List<OneWayTrip> firstFive) {
		this.firstFive = firstFive;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
