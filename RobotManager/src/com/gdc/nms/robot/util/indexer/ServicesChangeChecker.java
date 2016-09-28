package com.gdc.nms.robot.util.indexer;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.nms.robot.util.AppExaminator;

public class ServicesChangeChecker {
	
	private static ServicesChangeChecker instance;
	private static final Logger LOGGER=Logger.getLogger(ServicesChangeChecker.class.getName());
	
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
		try{
			AppInformation appData = AppExaminator.getAppData(servicesPath);
			LOGGER.log(Level.INFO, "Try to add Services "+appData.getAppName());
			if(appData!=null){
				RobotManager.getSRMGuiManager().addNewServices(appData.getAppName());
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	public void checkForDeleteServices(Path servicesPath){
		AppInformation appData = AppExaminator.getAppData(servicesPath);
		LOGGER.log(Level.INFO, "Try to remove Services "+appData.getAppName());

		if(appData==null){
			RobotManager.getSRMGuiManager().RemoveServices(servicesPath.getFileName().toString());
		}
	}

}
