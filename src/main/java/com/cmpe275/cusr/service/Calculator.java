package com.cmpe275.cusr.service;

import com.cmpe275.cusr.model.Station;

public class Calculator {
	
	private static final double REGULAR_UNIT = 1;
	private static final int EXPRESS_FOLD = 2;
	private static final int COVER_STOPS = 5; 
	
	public static double pricePerSeat(Station start, Station end, String bound) {
		
		int stops = Math.abs(end.getIndex() - start.getIndex());
		double result = 0;
		if (stops % COVER_STOPS == 0) {
			result = stops/COVER_STOPS * REGULAR_UNIT;
		} else {
			result = (stops/COVER_STOPS + 1) * REGULAR_UNIT;
		}
		
		if (bound.endsWith("00")) {
			result *= EXPRESS_FOLD;
		}
		
		return result;
	}

}