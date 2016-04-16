package com.gdc.nms.robot.util;

import com.gdc.nms.robot.util.indexer.AppInformation;
import com.gdc.robothelper.webservice.ClientWebService;
import com.gdc.robothelper.webservice.robot.CreatorRobotWebService;

public class DeleteServiceController {
	private AppInformation app;
	
	public DeleteServiceController(AppInformation app){
		this.app=app;
	}
	
	public void deleteService(){
			long idRobot = app.getIdRobot();
			CreatorRobotWebService.deleteRobot(idRobot);
	}
}
