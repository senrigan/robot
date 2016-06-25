package com.gdc.srm.fix.util;

import java.util.ArrayList;

public class MonitorSteps {
	private long monitorId;
	private ArrayList<String> name;
	
	public MonitorSteps(long monitorId,ArrayList<String> stepsNames){
		this.monitorId=monitorId;
		this.name=stepsNames;
	}
	public long getMonitorId() {
		return monitorId;
	}
	public void setMonitorId(long monitorId) {
		this.monitorId = monitorId;
	}
	public ArrayList<String> getStepsNames() {
		return name;
	}
	public void setStepsNames(ArrayList<String> stepsNames) {
		this.name = stepsNames;
	}
	
	
}
