package com.gdc.nms.robot.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.gdc.nms.robot.util.indexer.AppInformation;
import com.gdc.nms.robot.util.indexer.FlujoInformation;
import com.gdc.nms.robot.util.indexer.StepInformation;
import com.gdc.nms.robot.util.jade.DFConsult;
import com.gdc.nms.robot.util.registry.CommandExecutor;

import jade.core.AID;


public class AppExaminator {
	

	public static ArrayList<AppInformation> getInstalledApps(){
		ArrayList<AppInformation> apps=new ArrayList<AppInformation>();
		try {
			Path installationPath = getInstallationPath();

			Path dataInstallation = installationPath.resolve("data");
			File dataFolder = dataInstallation.toFile();
			File[] listFiles = dataFolder.listFiles();
			Arrays.sort(listFiles);
			for(File  file:listFiles){
				if(file.isDirectory()){
					Path resolve = file.toPath().resolve("bot-1.0.jar");
					if(Files.exists(resolve)){
						
						apps.add(getAppData(file.toPath()));
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return apps;
	}
	
	
	public static HashMap<String,AppInformation> getInstalledAppsMap(){
		HashMap<String, AppInformation> apps= new HashMap<String,AppInformation>();
		try {
			Path installationPath = getInstallationPath();

			Path dataInstallation = installationPath.resolve("data");
			File dataFolder = dataInstallation.toFile();
			File[] listFiles = dataFolder.listFiles();
			Arrays.sort(listFiles);
			for(File  file:listFiles){
				if(file.isDirectory()){
					
					if(validAppFolde(file.toPath())){
						apps.put(file.getName(), getAppData(file.toPath()));
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return apps;
	}
	
	
	public static boolean validAppFolde(Path pathFolder){
		Path applicationFolder = pathFolder.resolve("application");
//		System.out.println(""+Files.exists(pathFolder.resolve("bot-1.0.jar"))+" "+Files.exists(applicationFolder)+" "+Files.exists(applicationFolder.resolve("app-config.xml")));
		if(Files.exists(pathFolder.resolve("bot-1.0.jar"))&&
				Files.exists(applicationFolder)&& Files.exists(applicationFolder.resolve("app-config.xml"))){
			File[] listFiles = applicationFolder.toFile().listFiles();
			for (File file : listFiles) {
				if(file.isDirectory()){
					File[] listFiles2 = file.listFiles();
					for (File file2 : listFiles2) {
//						System.out.println(file2.getName());
						if(file2.getName().endsWith(".iim")){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	
	public static Path getInstallationPath(){
		String LOCALREGISTRY="HKCU\\Software\\GDC\\Robot";
		String redRegistryWindows="";
		
		try {
			redRegistryWindows=CommandExecutor.readRegistrySpecificRegistry(LOCALREGISTRY, "installationPath", "REG_SZ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Path installationPath = Paths.get(redRegistryWindows);
		return installationPath;
	}
	
	
	public static  ArrayList<AppInformation>  getRunningApps(){
		Vector<AID> services = DFConsult.services;
//		ArrayList<RobotInformation> runningRobot = VirtualMachineExaminator.getRunningRobot();
//		System.out.println("running roboits"+runningRobot);
		ArrayList<AppInformation> running=new ArrayList<AppInformation>();
		
//		for(RobotInformation robot:runningRobot){
//			AppInformation applicationInfo = robot.getApplicationInfo();
//			running.add(applicationInfo);
//		}
//		if(!running.isEmpty())
//			Collections.sort(running);
		return running;
	}
	
	public static ArrayList<AppInformation> getNotRunnigApps(){

		ArrayList<AppInformation> installedApps = getInstalledApps();
		if(!installedApps.isEmpty())
		Collections.sort(installedApps);
		ArrayList<AppInformation> notRunning=new ArrayList<AppInformation>();
		ArrayList<AppInformation> runningApps = getRunningApps();
		for (AppInformation appInformation : installedApps) {
			int idSearched = binarySearchForIdApp(runningApps, appInformation.getIdApp());
			if(idSearched==-1){
				notRunning.add(appInformation);
			}
			
		}
		return notRunning;
	}
	
	
	
	public static int binarySearchForIdApp(ArrayList<AppInformation> list,long id){
		int n=list.size();
		int centro,inf=0,sup=n-1;
		while(inf<=sup){
			centro=(sup+inf)/2;
			if(list.get(centro).getIdApp()==id){
				return centro;
			}else if(id<list.get(centro).getIdApp()){
				sup=centro-1;
			}else{
				inf=centro+1;
			}
		}
		return -1;
	}
	
	
	public static AppInformation getAppData(Path appFolder){
		File file=appFolder.toFile();
		AppInformation app=new AppInformation();
		setMetaInfoApp(appFolder, app);
		app.setAppName(file.getName());
		ArrayList<FlujoInformation> flujosApp = getFlujosApp(appFolder.resolve("application"));
		app.setFlujos(flujosApp);
		app.setIdRobot(getRobotID(appFolder));
		return app;
	}
	
	
	public static long getRobotID(Path appFolder){
		Path jarPath=appFolder.resolve(Constants.JARNAME);
		long robotId=0;
		try{
			URL url=new URL("jar:file:"+jarPath.toString()+"!/META-INF/robot.properties");
			InputStream is=url.openStream();
			BufferedReader readeR=new BufferedReader(new InputStreamReader(is));
			StringBuilder out=new StringBuilder();
			String line;
			while((line=readeR.readLine())!=null){
				out.append(line);
			}
			String [] split=out.toString().split("=");
			robotId=Long.parseLong(split[1].trim());
			
		}catch(Exception ex){
			
		}
		return robotId;
		
	}
	private static void setMetaInfoApp(Path folderApp,AppInformation app){
		app.setFolderPath(folderApp.toString());
		Path resolve = folderApp.resolve("application").resolve("app-config.xml");
		try{
			InputStream fXmlFile = new FileInputStream(resolve.toFile());;
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
			app.setAlias(doc.getDocumentElement().getAttribute("alias"));
			app.setIdApp(Long.parseLong(doc.getDocumentElement().getAttribute("id")));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		

	}
	
	public static ArrayList<FlujoInformation> getFlujosApp(Path appPath){
		ArrayList<FlujoInformation> flujos=new ArrayList<FlujoInformation>();
		File applicationFolder = appPath.toFile();
		File[] listFiles = applicationFolder.listFiles();
		Arrays.sort(listFiles);
		for(File file: listFiles){
			if(file.isDirectory()){
				FlujoInformation flujo=new FlujoInformation();
				String directoryName = file.getName();
				String[] directorySplit = directoryName.split("\\.");
				long idFlujo;
				String nameFlujo;
				if(directorySplit.length>1){
					idFlujo=Long.parseLong(directorySplit[0]);
					nameFlujo=directorySplit[1];
				}else{
					idFlujo=-1;
					nameFlujo=directoryName;
				}
				
				File[] steps = file.listFiles();
				ArrayList<StepInformation> stepsFiles = indexSteps(steps);
				flujo.setIdFlujo(idFlujo);
				flujo.setName(nameFlujo);
				flujo.setSteps(stepsFiles);
				flujo.setNumSteps(stepsFiles.size());
				flujo.setPath(file.toPath());
				flujos.add(flujo);
			}
		}
		return flujos;
	}
	
	
	private static ArrayList<StepInformation> indexSteps(File[] steps){
		Arrays.sort(steps);
		ArrayList<StepInformation> stepsFiles=new ArrayList<StepInformation>();
		for(File step:steps){
			String fileName = step.getName();
			if(fileName.endsWith(".iim")){
				String[] split = fileName.split("\\.");
				if(split.length>=3){
					int numStep=Integer.parseInt(split[0]);
					String name=split[1];
					StepInformation stepInfo=new StepInformation();
					stepInfo.setName(name);
					stepInfo.setNumStep(numStep);
					stepInfo.setPath(step.toPath());
					stepsFiles.add(stepInfo);
					
					
				}
			}
		}
		return stepsFiles;
	}

	
	
	
	

}
