package com.gdc.nms.robot.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.gdc.nms.robot.gui.DeleteDirectory;
import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.nms.robot.gui.util.process.JavaProcess;
import com.gdc.nms.robot.gui.util.process.JavaProcessInfo;
import com.gdc.nms.robot.util.indexer.AppInformation;





public class DeleteServiceController {
	private AppInformation app;
	private static final Logger LOGGER=Logger.getLogger(DeleteServiceController.class);

	
	public DeleteServiceController(AppInformation app){
		this.app=app;
	}
	/**
	 * if the services si started try to stoped the process services
	 * @return
	 */
	public boolean deleteService(){
			LOGGER.info("try to stop robot for services "+app.getAppName());
			if(stopRobot()){
				long idRobot = app.getIdRobot();
				LOGGER.info("Starting to Delete Robot id :"+idRobot);
				boolean continueProcess=false;
				LOGGER.info("robot webservices is deleted :"+continueProcess);
				System.out.println("appfolerd "+app.getFolderPath());
					continueProcess=deleteServices(app.getFolderPath());
					LOGGER.info("moving folder to trash "+continueProcess);
				startDeleteFolderTask(new File(app.getFolderPath()));
				return continueProcess;
				
			}else{
				JOptionPane.showMessageDialog(null,"No es posible detener el robot del servicio "+app.getAlias()+"es necesario detenerlo manualmente  y volver a intertarlo");
				return false;
			}
	}

	private void startDeleteFolderTask(final File folderPath){
		Thread thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(folderPath.exists()){
					try {
						deleteServices(folderPath.toString());
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}
	
	
	
	
	private boolean  stopRobot(){
		if(app.isServicesRunning()){
			System.out.println("services is running"+app.getAppName());
			long pid = app.getPID();
			System.out.println("PID services"+app.getPID()+"app name"+app.getAppName());
			if(JavaProcess.isValidJavaProceesId(pid)){
				if(RobotManager.stopJar(pid)){
					return true;
				}
			}else{
				ArrayList<JavaProcessInfo> allJavaRobotProces = JavaProcess.getALLJavaRobotProces();
				for (JavaProcessInfo javaProcessInfo : allJavaRobotProces) {
					String commandLine = javaProcessInfo.getCommandLine();
					if(commandLine.contains(app.getAppName()) ){
						long processid = javaProcessInfo.getProcessid();
						if(RobotManager.stopJar(processid)){
							return true;
						}
					}
				}
			}
			
		}else{
			return true;
		}
		return false;
	}
	
	private boolean deleteServices(String serviceFolderPath){
		Path serviceFolder = Paths.get(serviceFolderPath);
		try {
			return deleteServicesFolder(serviceFolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	private boolean deleteServicesFolder(Path serviceFolder) throws IOException{
		DeleteDirectory.delete(serviceFolder.toFile());
		return true;
	}
	
	
	
	
	
	public static void main(String[] args) {
		DeleteServiceController dl=new DeleteServiceController(null);
		dl.deleteServices(new String("C:\\Program Files\\GDC\\RobotScript\\data\\hola"));
	}
}
