package com.gdc.nms.robot.util.indexer;

import java.nio.file.Path;
import java.util.ArrayList;

import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.nms.robot.util.AppExaminator;

public class ServicesChangeChecker {
	
	private static ServicesChangeChecker instance;
	
	public static ServicesChangeChecker getInstance(){
		if(instance==null){
			instance=new ServicesChangeChecker();
		}
		return instance;
	}
	
	private ServicesChangeChecker (){
		
	}
	
	public void  checkForChangeServices(){
		ArrayList<AppInformation> installedApps = AppExaminator.getInstalledApps();
		ArrayList<String> robotList = RobotManager.getSRMGuiManager().getRobotList();
		for (AppInformation appInformation : installedApps) {
			boolean found=false;
			for (String string : robotList) {
				if(appInformation.getAppName()==string){
					found=true;
					break;
				}
			}
			if(found){
				RobotManager.getSRMGuiManager().addNewServices(appInformation.getAppName());
			}else{
				RobotManager.getSRMGuiManager().RemoveServices(appInformation.getAppName());
			}
		}
	}
	
	
	public void checkForNewServices(Path servicesPath){
		AppInformation appData = AppExaminator.getAppData(servicesPath);
		if(appData!=null){
			RobotManager.getSRMGuiManager().addNewServices(appData.getAppName());
		}
	}
	
	public void checkForDeleteServices(Path servicesPath){
		AppInformation appData = AppExaminator.getAppData(servicesPath);
		if(appData==null){
			RobotManager.getSRMGuiManager().RemoveServices(appData.getAppName());
		}
	}

}
