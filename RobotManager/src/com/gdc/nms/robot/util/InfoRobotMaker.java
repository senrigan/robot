package com.gdc.nms.robot.util;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;

import com.gdc.nms.robot.util.indexer.AppJsonObject;
import com.gdc.nms.robot.util.indexer.FlujoInformation;

public class InfoRobotMaker {
	private Path dataFolder;
	private ArrayList<FlujoInformation> flujos;
	private AppJsonObject appSelected;
	private Date dateForRun;
	private int timeLapse;
	private int retries;
	/**
	 * 
	 * @return a path of folder with contains a files for run flujos
	 */
	public Path getDataFolder() {
		return dataFolder;
	}
	public void setDataFolder(Path dataFolder) {
		this.dataFolder = dataFolder;
	}
	public ArrayList<FlujoInformation> getFlujos() {
		return flujos;
	}
	public void setFlujos(ArrayList<FlujoInformation> flujos) {
		this.flujos = flujos;
	}
	public AppJsonObject getAppSelected() {
		return appSelected;
	}
	public void setAppSelected(AppJsonObject appSelected) {
		this.appSelected = appSelected;
	}
	public Date getDateForRun() {
		return dateForRun;
	}
	public void setDateForRun(Date dateForRun) {
		this.dateForRun = dateForRun;
	}
	public int getTimeLapse() {
		return timeLapse;
	}
	public void setTimeLapse(int timeLapse) {
		this.timeLapse = timeLapse;
	}
	public int getRetries() {
		return retries;
	}
	public void setRetries(int retries) {
		this.retries = retries;
	}
	
	
	
	
}
