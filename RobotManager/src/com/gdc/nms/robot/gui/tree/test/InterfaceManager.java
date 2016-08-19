package com.gdc.nms.robot.gui.tree.test;

import java.util.ArrayList;


import com.gdc.nms.robot.gui.util.tet;
import com.gdc.nms.robot.util.AppExaminator;
import com.gdc.nms.robot.util.indexer.AppInformation;

public class InterfaceManager {
	private tet gui;
	public InterfaceManager(tet gui){
		this.gui=gui;
	}
	
	
	private void loadAllRobots(){
//		try {
//		EventQueue.invokeLater(new Runnable() {
				
//				@Override
//				public void run() {
		
					ArrayList<AppInformation> installedApps = AppExaminator.getInstalledApps();
					for (AppInformation appInformation : installedApps) {
						System.out.println("add  "+appInformation.getAppName());
						gui.addNewRobotUI(appInformation.getAppName());
						
					}
					
//				}
//			});
				
		
	}
	
	
	
	
	public static void main(String[] args) {
		tet gui=new tet();
		InterfaceManager man=new InterfaceManager(gui);
		man.loadAllRobots();
		System.out.println("**"+gui.getMapRobots());
//		gui.addNewRobotUI("hola");
	}
}
