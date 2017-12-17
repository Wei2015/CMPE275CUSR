package com.cmpe275.cusr.service;

import com.cmpe275.cusr.model.OneWayList;
import com.cmpe275.cusr.model.SearchContent;

public interface TrainService {
	
	public boolean verfiyDateAndTime(SearchContent content, OneWayList result);
	
	public void searchOneWay(SearchContent content, OneWayList result);
	
	

}
