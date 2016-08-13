package com.gdc.nms.robot.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.gdc.nms.robot.gui.DeleteDirectory;
import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.nms.robot.gui.util.process.JavaProcess;
import com.gdc.nms.robot.gui.util.process.JavaProcessInfo;
import com.gdc.nms.robot.util.indexer.AppInformation;
import com.gdc.nms.robot.util.jade.InitPlataform;
import com.gdc.robothelper.webservice.robot.CreatorRobotWebService;

import jade.core.AID;




public class DeleteServiceController {
	private AppInformation app;
	private static final Logger LOGGER=Logger.getLogger(DeleteServiceController.class);

	
	public DeleteServiceController(AppInformation app){
		this.app=app;
	}
	
	public boolean deleteService(){
//			RobotManager.StopScanServices();
			LOGGER.info("try to stop robot for services "+app.getAppName());
			if(stopRobot()){
				long idRobot = app.getIdRobot();
				LOGGER.info("Starting to Delete Robot id :"+idRobot);
				boolean continueProcess=false;
				continueProcess=CreatorRobotWebService.deleteRobot(idRobot);
				LOGGER.info("robot webservices is deleted :"+continueProcess);
				if(continueProcess){
					continueProcess=deleteServices(app.getFolderPath());
					LOGGER.info("moving folder to trash "+continueProcess);
				}
//			RobotManager.StartScanServices();
				return continueProcess;
				
			}else{
				JOptionPane.showMessageDialog(null,"No es posible detener el robot del servicio "+app.getAlias()+"es necesario detenerlo manualmente  y volver a intertarlo");
				return false;
			}
	}
	
	
	private boolean stopServiceAgent(){
		AID aid = InitPlataform.getRobotRegister().get(app.getAppName());
		app.getAppName();
		return false;
	}
	
	
	private boolean  stopRobot(){
		if(app.isServicesRunning()){
			System.out.println("services is running"+app.getAppName());
			long pid = app.getPID();
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
//		Path servicesFolderPath = RobotManager.getServicesFolderPath();
//		createTrashFolder(servicesFolderPath);
//		Path trashFolder = servicesFolderPath.resolve("TRASH");
//		String name = serviceFolder.getFileName().toString();
//		String dateForFilesChanges = RobotManager.getDateForFilesChanges();
//		System.out.println(dateForFilesChanges);
//		Path resolve = serviceFolder.resolve("bot-1.0.jar");
//		ArrayList<String> validBotFiles = AppExaminator.getValidBotFiles(serviceFolder);
//		for (String string : validBotFiles) {
//			File botFile=new File(string);
//			Files.move(botFile.toPath(),resolve.resolveSibling("_D"+botFile.getName()));
//		}
////		Path move = Files.move(resolve, resolve.resolveSibling("_Dvot-1.0.jar"));
////		System.out.println("move"+move);
//		Path destinationPath = trashFolder.resolve(name+"_"+dateForFilesChanges);
//		if(Files.exists(destinationPath)){
//			Files.delete(destinationPath);
//		}
//		FileUtils.copyDirectoryToDirectory(serviceFolder.toFile(), destinationPath.toFile());
		DeleteDirectory.delete(serviceFolder.toFile());
		return true;
	}
	
	/**
	 * create the trash folder only if exist
	 * @param folder
	 * @throws IOException
	 */
	private void createTrashFolder(Path folder) throws IOException{
		Path trashFolder = folder.resolve("TRASH");
		if(!Files.exists(trashFolder)){
			Files.createDirectory(trashFolder);
		}
	}
	
	
	
	
	public static void main(String[] args) {
		DeleteServiceController te=new DeleteServiceController(null);
		Path path = Paths.get("C:\\Users\\senrigan\\Documents\\pruebas\\GDC\\RobotScript\\data\\DYP");
		boolean moveServicesToDelete;
		try {
			moveServicesToDelete = te.deleteServicesFolder(path);
			System.out.println(moveServicesToDelete);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
