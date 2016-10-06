package com.gdc.srm.robot.util;

import java.nio.file.Files;
import java.nio.file.Path;

import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.nms.robot.util.AppExaminator;
import com.gdc.nms.robot.util.indexer.AppInformation;


public class NecromancerWorker implements  Runnable {
	private Path botFile;
	private int retriesToStart=0;
	private NecromancerRobot necromancer;
	public NecromancerWorker(Path bothFile,NecromancerRobot necromancer) {
		this.botFile=bothFile;
		this.necromancer=necromancer;
	}

	@Override
	public void run() {
		if(Files.exists(botFile)){
			
			do{
				System.out.println("tryin to start"+botFile);
				RobotManager.runJarRobot(botFile.toFile());
				if(alReadyStart()){
					System.out.println("the "+botFile+" started");
					retriesToStart=5;
					
				}else{
					System.out.println("retrie to start robot"+botFile);
					retriesToStart++;
				}
			}while(retriesToStart<5);
		}
	}
	
	
	private boolean alReadyStart(){
		String parentFile = botFile.getParent().getFileName().toString();
		System.out.println("parentFile"+parentFile);
		AppInformation appData = AppExaminator.getAppData(parentFile);
		if(appData.isServicesRunning()){
			necromancer.removeFromDeadList(appData.getAppName());
			return true;
		}
		return false;
	}
	

}
