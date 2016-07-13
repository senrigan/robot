package com.gdc.nms.robot.util.indexer;

import java.io.InterruptedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class AppInformation  implements Comparable<AppInformation>{
	private String alias;
	private String appName;
	private long idApp;
	private ArrayList<FlujoInformation> flujos;
	private long idRobot;
	private HashMap<String,String> propierties;
	private String folderPath;
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public long getIdApp() {
		return idApp;
	}
	public void setIdApp(long idApp) {
		this.idApp = idApp;
	}
	public ArrayList<FlujoInformation> getFlujos() {
		return flujos;
	}
	public void setFlujos(ArrayList<FlujoInformation> flujos) {
		this.flujos = flujos;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	
	public String getFolderPath() {
		return folderPath;
	}
	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}
	public long getIdRobot() {
		return idRobot;
	}
	public void setIdRobot(long idRobot) {
		this.idRobot = idRobot;
	}
	
	
	@Override
	public String toString() {
		return appName;
	}
	@Override
	public int compareTo(AppInformation o) {
		return new Integer(new Long(this.idApp).compareTo(o.getIdApp()));
	}
	public HashMap<String, String> getPropierties() {
		return propierties;
	}
	public void setPropierties(HashMap<String, String> propierties) {
		this.propierties = propierties;
	}
	
	public boolean isServicesRunning(){
		Path path = Paths.get(getFolderPath()).resolve(".lock");
		try{
			if(Files.exists(path)){
				if(Files.deleteIfExists(path)){
					return true;
				}else{
					Thread.sleep(20000L);
				}
			}
			return false;
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	
	
	
}
