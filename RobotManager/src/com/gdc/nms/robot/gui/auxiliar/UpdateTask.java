package com.gdc.nms.robot.gui.auxiliar;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import com.gdc.nms.robot.gui.ResultUpdate;
import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.nms.robot.util.Constants;
import com.gdc.nms.robot.util.CreatorRobotManager;
import com.gdc.nms.robot.util.UpdaterRobot;
import com.gdc.nms.robot.util.indexer.AppInformation;
import com.gdc.nms.robot.util.jade.InitPlataform;

import jade.core.AID;

public class UpdateTask implements Runnable {
	private AID robot;
	private ResultUpdate resultUpdate;
	private Path appFolder;
	private AppInformation appInformation;
	public UpdateTask(AID robot,ResultUpdate resultUpdate,Path appFolder,AppInformation appInformation){
		this.robot=robot;
		this.resultUpdate=resultUpdate;
		this.appFolder=appFolder;
		this.appInformation=appInformation;
	}
	public void run() {
		HashMap<String, AID> robotRegister = InitPlataform.getRobotRegister();
		while(robotRegister.containsKey(robot)){
			try{
				Thread.sleep(20000L);
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		Path installationPath = RobotManager.getInstallationPath();
		Path robotPath = installationPath.resolve("inMonitor").resolve(Constants.JARNAME);
		if(Files.exists(appFolder)){
			resultUpdate.appendText("\n Procesando Aplicacion "+appInformation.getAppName());
			Path installedBot=appFolder.resolve(Constants.JARNAME);
			long robotID = UpdaterRobot.getRobotID(installationPath);
			resultUpdate.appendText("\n Leyendo Datos del Robot "+robotID);
			CreatorRobotManager creator=new CreatorRobotManager();
			Path propertiesPath=installationPath.resolve("inMonitor").resolve("robot.properties");
			resultUpdate.appendText(" \n \tModificando archivo de propiedades");
			CreatorRobotManager.updateRobot(robotPath, robotID);
//			creator.modifyFileRobotId(robotID, robotPath);
//			resultUpdate.appendText(" \n \tAgregando Archivo de Propiedades");
//			creator.modifyJar(robotPath, propertiesPath, Constants.PROPERTIESJARBOT);
			if(robotID==UpdaterRobot.getRobotID(robotPath)){
				try{					
					resultUpdate.appendText("\n \t Borrando robot ");
					FileUtils.forceDelete(installedBot.toFile());
					resultUpdate.appendText("\n \tIntegrando Nuevo Robot");
					FileUtils.copyFile(robotPath.toFile(), installedBot.toFile());
					resultUpdate.appendText("\n Robot integrado completamente aplicacion "+appInformation.getAppName());
				}catch(Exception ex){
					
				}
			}else{
				
			}
				
			
			
		}
		
		
	}
} 


