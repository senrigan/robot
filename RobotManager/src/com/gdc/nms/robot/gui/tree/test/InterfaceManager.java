package com.gdc.nms.robot.gui.tree.test;

import java.util.ArrayList;
import java.util.Set;


import com.gdc.nms.robot.gui.util.tet;
import com.gdc.nms.robot.util.AppExaminator;
import com.gdc.nms.robot.util.indexer.AppInformation;

public class InterfaceManager {
	private tet gui;
	public InterfaceManager(tet gui){
		this.gui=gui;
	}
	
	
	public void loadAllRobots(){
		ArrayList<AppInformation> installedApps = AppExaminator.getInstalledApps();
		for (AppInformation appInformation : installedApps) {
			System.out.println("add  "+appInformation.getAppName());
			gui.addNewRobotUI(appInformation.getAppName());
			
		}
	}
	
	
	public void setInfoText(String text){
		gui.setTextInfo(text);
	}
	
	
	
	
	public static void main(String[] args) {
		tet gui=new tet();
		InterfaceManager man=new InterfaceManager(gui);
		man.loadAllRobots();
		System.out.println("**"+gui.getMapRobots());
		Set<String> keySet = gui.getMapRobots().keySet();
		System.out.println("--"+keySet);
		for (String string : keySet) {
			System.out.println("string **"+string);
			gui.changeStatus(gui.getMapRobots().get(string));
			try {
				Thread.sleep(5000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gui.changeStatusToActive(gui.getMapRobots().get(string));
			man.setInfoText("pruebas");
			
		}
	}
	
	
	public void setAplicationName(String applicactionName){
		gui.setAplicationName(applicactionName);
	}
	
	public void setAliasName(String aliasName){
		gui.setAliasName(aliasName);
	}
	
	public void setIdRobot(String idRobot){
		gui.setIdRobot(idRobot);
	}
}
