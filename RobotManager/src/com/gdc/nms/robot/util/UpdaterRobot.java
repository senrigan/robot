package com.gdc.nms.robot.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipException;

import org.apache.commons.io.FileUtils;

import com.gdc.nms.robot.gui.ResultUpdate;
import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.nms.robot.gui.auxiliar.UpdateTask;
import com.gdc.nms.robot.util.indexer.AppInformation;
import com.gdc.nms.robot.util.jade.InitPlataform;
import com.gdc.nms.robot.util.jade.SRMAgentManager;

import jade.core.AID;

public class UpdaterRobot {
		public static  long getRobotID(Path jarPath){
		    	long robotID=-1;
		    	try {
		        	URL url = new URL("jar:file:"+jarPath.toString()+"!/META-INF/robot.properties");
		        	System.out.println(url.toString());
		        	InputStream is = url.openStream();
		        	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		            StringBuilder out = new StringBuilder();
		            String line;
		            while ((line = reader.readLine()) != null) {
		                out.append(line);
		            }
		            String[] split = out.toString().split("=");
		            robotID=Long.parseLong(split[1].trim());
				} catch (ZipException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    	return robotID;
		    	
		}
	    
	    	
	    	
	   /**
	    * check if the jar file contains robot.properties
	    * @param jarPath
	    * @return
	    * @throws IOException
	    */
	   public  boolean isValidRobot(Path jarPath) throws IOException{
		URL url = new URL("jar:file:"+jarPath.toString()+"!/META-INF/robot.properties");
       	System.out.println(url.toString());
       	InputStream is = url.openStream();
       	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
       	StringBuilder out = new StringBuilder();
       	String line;
       	while ((line = reader.readLine()) != null) {
           out.append(line);
       	}
       	return true;
	   }
	   
	   /**
	    * return the fist .jar found .zip
	    * @param file
	    * @return
	    */
	   public Path getUpdateRoboFilestPath(File file){
		   Path tempPath = Environment.getTempPath();
		   Path updateFolder = tempPath.resolve(Constants.UPDATEROBOTEMP);
		   try{
			   if(Files.exists(updateFolder)){
				   FileUtils.deleteDirectory(updateFolder.toFile());
				   ExtracterJar.unZipIt(file.toString(), updateFolder.toString());
				   File updateFolderFile = updateFolder.toFile();
				   File[] listFiles = updateFolderFile.listFiles();
				   for (File fileFolder : listFiles) {
					   if(fileFolder.getName().endsWith(".jar")){
						   return fileFolder.toPath();
					   }
				   }
			   }else{
				   Files.createDirectories(updateFolder);
				   ExtracterJar.unZipIt(file.toString(), updateFolder.toString());
				   File updateFolderFile = updateFolder.toFile();
				   File[] listFiles = updateFolderFile.listFiles();
				   for (File fileFolder : listFiles) {
					   if(fileFolder.getName().endsWith(".jar")){
						   return fileFolder.toPath();
					   }
				   }
			   }
			   
		   }catch(Exception ex){
			   ex.printStackTrace();
		   }
		   return null;
			
	   }
	   
	   
	 public void copyJarInMonitor(Path path ) throws IOException{
		 Path installationPath = RobotManager.getInstallationPath();
		 Path botPath = installationPath.resolve("inMonitor").resolve("bot.jar");
		 if(Files.exists(botPath)){
			 	System.out.println("borrando el bothPath");
				FileUtils.forceDelete(botPath.toFile());
				FileUtils.copyFile(path.toFile(), botPath.toFile());
				Path libTemp = path.getParent().resolve("lib");
				Path libInstalled = botPath.getParent().resolve("lib");
				File[] listFiles = libTemp.toFile().listFiles();
				for (File file : listFiles) {
					Path  fileInstalled= libInstalled.resolve(libTemp.toFile().getName());
					if(!Files.exists(fileInstalled)){
						FileUtils.copyFileToDirectory(file,libInstalled.toFile());
					}
				}
			
		 }
	 }
	 
	 
	 public void updateAllRobots(ResultUpdate resultUpdate) throws IOException{
		 Path installationPath = RobotManager.getInstallationPath();
		 Path robotPath = installationPath.resolve("inMonitor").resolve(Constants.JARNAME);
		 ArrayList<AppInformation> installedApp = RobotManager.getInstalledApp();
		 Path data = installationPath.resolve("data");
		 ExecutorService ex=Executors.newFixedThreadPool(5);
		 
		 for (AppInformation appInformation : installedApp) {
			Path appFolder = data.resolve(appInformation.getAppName());
			HashMap<String, AID> robotRegister = InitPlataform.getRobotRegister();
			if(robotRegister.containsKey(appInformation.getAlias())){
				ex.execute(new UpdateTask(robotRegister.get(appInformation.getAlias()), resultUpdate, appFolder, appInformation));
			}else{
				if(Files.exists(appFolder)){
					resultUpdate.appendText("\n Procesando Aplicacion "+appInformation.getAppName());
					Path installedBot=appFolder.resolve(Constants.JARNAME);
					long robotID = getRobotID(installationPath);
					resultUpdate.appendText("\n Leyendo Datos del Robot "+robotID);
					CreatorRobotManager creator=new CreatorRobotManager();
					Path propertiesPath=installationPath.resolve("inMonitor").resolve("robot.properties");
					resultUpdate.appendText(" \n \tModificando archivo de propiedades");
					CreatorRobotManager.updateRobot(robotPath, robotID);
//					creator.modifyFileRobotId(robotID, robotPath);
//					resultUpdate.appendText(" \n \tAgregando Archivo de Propiedades");
//					creator.modifyJar(robotPath, propertiesPath, Constants.PROPERTIESJARBOT);
					if(robotID==getRobotID(robotPath)){
						resultUpdate.appendText("\n \t Borrando robot ");
						FileUtils.forceDelete(installedBot.toFile());
						resultUpdate.appendText("\n \tIntegrando Nuevo Robot");
						FileUtils.copyFile(robotPath.toFile(), installedBot.toFile());
						resultUpdate.appendText("\n Robot integrado completamente aplicacion "+appInformation.getAppName());
					}else{
						
					}
						
					
					
				}

			}
		}
		 try {
			ex.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
	 }
	 
	 public void stopAllRobots() throws Exception{
		 
			RobotManager.stopAllRobots();
		
	 }
	 
	 
	 
	 
	 
	 
}
