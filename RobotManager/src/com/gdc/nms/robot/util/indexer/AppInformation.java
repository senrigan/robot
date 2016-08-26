package com.gdc.nms.robot.util.indexer;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import com.gdc.nms.robot.gui.util.process.JavaProcess;
import com.gdc.nms.robot.util.AppExaminator;
import com.gdc.nms.robot.util.jade.InitPlataform;

import jade.core.AID;

public class AppInformation  implements Comparable<AppInformation>{
	private String alias;
	private String appName;
	private long idApp;
	private ArrayList<FlujoInformation> flujos;
	private long idRobot;
	private HashMap<String,String> propierties;
	private String folderPath;
	private File botFile;
	/**
	 * 
	 * @return the real folder name
	 */
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
		if(!JavaProcess.isServicesAlreadyRunningForMoreFiveMinutes(appName)){
			Path path = Paths.get(getFolderPath()).resolve(".lock");
			try{
				if(Files.exists(path)){
					File file = path.toFile();
					if(file.delete()){
						return false;
					}else{
						Thread.sleep(5000L);
						if(file.delete()){
							return false;
						}
					}
					return true;
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
			path=null;
			return false;
			
		}else{
			Path path = Paths.get(getFolderPath()).resolve(".lock");
			try{
				if(Files.exists(path)){
					File file = path.toFile();
					if(file.delete()){
						return false;
					}else{
						Thread.sleep(5000L);
						if(file.delete()){
							return false;
						}
					}
					return true;
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
			path=null;
			return false;
		}
	}
	
	
	/**
	 * get the num of process identify in the SO 
	 * @return
	 */
	public long getPID(){
		return Long.parseLong(AppExaminator.readPidFile(folderPath));
	}
	
	public static void main(String[] args) {
		ArrayList<AppInformation> installedApps = AppExaminator.getInstalledApps();
		for (AppInformation appInformation : installedApps) {
			System.out.println("--"+appInformation.getBotFile());
		}
//			Files.delete(path);
	}
	public File getBotFile() {
		if(botFile!=null){
			return botFile;
			
		}else{
			return Paths.get(getFolderPath()).resolve("bot.jar").toFile();
		}
	}
	public void setBotFile(File botFile) {
		this.botFile = botFile;
	}
	
	
	public boolean isRunningByAgent(){
		HashMap<String, AID> robotRegister = InitPlataform.getRobotRegister();
		if(robotRegister.containsKey(appName)){
			return true;
		}
		return false;
	}
	
	
	
	public File getLogFile(){
		Path botFile=Paths.get(folderPath).resolve("robot.log");
		if(Files.exists(botFile)){
			return botFile.toFile();
		}
		return null;
	}
	
	
	

	
	
	
	
}
