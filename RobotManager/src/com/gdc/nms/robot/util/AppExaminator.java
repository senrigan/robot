package com.gdc.nms.robot.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.nms.robot.util.indexer.AppInformation;
import com.gdc.nms.robot.util.indexer.FlujoInformation;
import com.gdc.nms.robot.util.indexer.StepInformation;
import com.gdc.nms.robot.util.registry.CommandExecutor;




public class AppExaminator {
	/**
	 * 
	 * @return a list of all elements with information of application installed in data folder
	 */
	private static final Logger LOGGER=Logger.getLogger(AppExaminator.class.toString());
	static{
		LOGGER.addAppender(RobotManager.logAppender);
	}
	public static ArrayList<AppInformation> getInstalledApps(){
		ArrayList<AppInformation> apps=new ArrayList<AppInformation>();
		try {
			Path installationPath = getInstallationPath();

			Path dataInstallation = installationPath.resolve("data");
			File dataFolder = dataInstallation.toFile();
			File[] listFiles = dataFolder.listFiles();
			if(listFiles!=null){
				
				Arrays.sort(listFiles);
				for(File  file:listFiles){
					if(file.isDirectory()){
						FileFilter fileFilter=new WildcardFileFilter(Constants.REGEX_JARNAME+"*");
						File [] dir = file.listFiles(fileFilter);
						for (File fileApp : dir) {
							String fileName=fileApp.getName();
							if(fileName.startsWith(Constants.REGEX_JARNAME)){
								if(fileName.endsWith("exe")||fileName.endsWith("jar")){
									AppInformation appData = getAppData(file.toPath(),fileApp);
									if(appData!=null && appData.getIdRobot()!=0){
										apps.add(appData);
										
									}
								}
								
							}
						}
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			LOGGER.error("excepcion ", ex);

		}
		return apps;
	}
	
	
	
	public static File[] getBotFiles(Path folder){
		FileFilter fileFilter=new WildcardFileFilter(Constants.REGEX_JARNAME+"*");
		File [] dir = folder.toFile().listFiles(fileFilter);
		return dir;
	}
	
	public static ArrayList<String> getValidBotFiles(Path folder){
		File[] botFiles = getBotFiles(folder);
		ArrayList<String> validBotFiles=new ArrayList<String>();
		for (File file : botFiles) {
			String name = file.getName();
			
			if(name.endsWith("exe")||name.endsWith("EXE")|| 
					name.endsWith("jar")|| name.endsWith("JAR")){
				validBotFiles.add(file.toString());
			}
		}
		return validBotFiles;
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
						FileFilter fileFilter=new WildcardFileFilter(Constants.REGEX_JARNAME+"*");
						File [] dir = file.listFiles(fileFilter);
						for (File fileApp : dir) {
							String fileName=fileApp.getName();
							if(fileName.startsWith(Constants.REGEX_JARNAME)){
								if(fileName.endsWith("exe")||fileName.endsWith("jar")){
									AppInformation appData = getAppData(file.toPath(),fileApp);
									if(appData!=null && appData.getIdRobot()!=0){
										apps.put(file.getName(), getAppData(file.toPath()));
										
									}
								}
								
							}
						}
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			LOGGER.error("excepcion ", ex);

		}
		return apps;
	}
	
	/**
	 * 
	 * @param appName
	 * @return true if the appname exist in data folder other wise return false
	 */
	public static boolean appNameAlreadyExist(String appName){
		AppInformation appInformation = getInstalledAppsMap().get(appName);
		if(appInformation!=null){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param app
	 * @return null if the app no exist
	 */
	
	public static String getNewNameForAppIfExist(String app){
		FileFilter fileFilter=new WildcardFileFilter(app+"*");
		Path folderData = Paths.get(RobotManager.getInstallationPathRegistry()).resolve("data");
		File [] files = folderData.toFile().listFiles(fileFilter);
		if(files.length>0){
			String graterName="";
			System.out.println("folderDAra"+folderData);
			System.out.println(Arrays.toString(files));
			graterName=files[0].getName();
			for(int i=0;i<files.length;i++){
				if(graterName.compareTo(files[i].getName())<0){
					graterName=files[i].getName();
				}
			}
			System.out.println("graterName"+graterName);
			if(graterName.contains(Constants.APPSEPARATORROBOT)){
				int indexLastCaracter=graterName.lastIndexOf(Constants.APPSEPARATORROBOT);
				String lastName = graterName.substring(indexLastCaracter+1, graterName.length());
				int parseInt = Integer.parseInt(lastName);
				System.out.println("num parser "+parseInt);
				return app+" "+Constants.APPSEPARATORROBOT+(parseInt+1);
				
			}else{
				return graterName+" "+Constants.APPSEPARATORROBOT+2;
			}
			
		}
		return null;
	}
	
	
	
	public static boolean validAppFolde(Path pathFolder){
		Path applicationFolder = pathFolder.resolve("application");
		
		if(!getValidBotFiles(pathFolder).isEmpty()&&
				Files.exists(applicationFolder)&& Files.exists(applicationFolder.resolve("app-config.xml"))){
			File[] listFiles = applicationFolder.toFile().listFiles();
			for (File file : listFiles) {
				if(file.isDirectory()){
					File[] listFiles2 = file.listFiles();
					for (File file2 : listFiles2) {
						if(file2.getName().endsWith(".iim")){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public static boolean isRunningByLockFile(AppInformation app){
		Path path = Paths.get( app.getFolderPath());

		File file = path.resolve(".lock").toFile();
		if(file.delete()){
			return false;
			
			
		}else{
			try{
				LOGGER.info("retry again tho delete lock file for robot: "+app.getAppName());
				Thread.sleep(20000L);
				if(file.delete()){
					LOGGER.info("the lock file can deleted for robot :"+app.getAppName());
					return false;
				}
				LOGGER.info("the lock file can not deleted for robot :"+app.getAppName());
			}catch(InterruptedException ex){
				ex.printStackTrace();
				LOGGER.error("Error: ", ex);
			}
		}
		return true;
	}
	
	public static boolean isRunningByLockFileAgent(AppInformation app){
		Path path = Paths.get( app.getFolderPath());
		File file = path.resolve(".lock").toFile();
		if(file.delete()){
			return false;
			
			
		}
		return true;
	}
	
	public static boolean isRunningByLockFile(String app){
		Path path = Paths.get( app);
		File file = path.resolve(".lock").toFile();
		if(file.delete()){
			return false;
		}
		return true;
	}
	
	
	public static Path getInstallationPath(){
		String LOCALREGISTRY="HKCU\\Software\\GDC\\Robot";
		String redRegistryWindows="";
		
		try {
			redRegistryWindows=CommandExecutor.readRegistrySpecificRegistry(LOCALREGISTRY, "installationPath", "REG_SZ");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
		Path installationPath = Paths.get(redRegistryWindows);
		return installationPath;
	}
	
	public static String readLockFile(AppInformation app){
		Path path = Paths.get( app.getFolderPath());
		File file = path.resolve(".lock").toFile();
		String sCurrentLine,content="";

		try{
			
			BufferedReader fil = new BufferedReader(new FileReader(file));
			
			while ((sCurrentLine = fil.readLine()) != null) {
				content+=sCurrentLine;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			LOGGER.error("excepcion ", ex);

		}
		return content;
	}
	
	
	public static String readPidFile(String app){
		Path path = Paths.get( app);
		File file = path.resolve("robot.pid").toFile();
		if(file.exists()){
			String sCurrentLine,content="";
			FileInputStream fileInputStream=null;
			BufferedReader br=null;
			try{
				
				br = new BufferedReader(new FileReader(file));
				
				while ((sCurrentLine = br.readLine()) != null) {
					content=sCurrentLine;
					System.out.println("pid del robot a detener"+sCurrentLine);
				}
			}catch(Exception ex){
				ex.printStackTrace();
				LOGGER.error("excepcion ", ex);
				
			}
			return content;
			
		}else{
			return null;
		}
	}
	

	public static  ArrayList<AppInformation>  getRunningApps(){
//		Vector<AID> services = DFConsult.services;
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
		System.out.println("++++ file name "+file.getName());
		ArrayList<FlujoInformation> flujosApp;
		if(Files.exists(appFolder.resolve("application"))){
			
			 flujosApp= getFlujosApp(appFolder.resolve("application"));
		}else{
			flujosApp = getFlujosApp(appFolder);
		}
		if(flujosApp==null || flujosApp.isEmpty()){
			return null;
		}
		app.setFlujos(flujosApp);
		app.setIdRobot(getRobotID(appFolder));
		System.out.println("id robot"+app.getIdRobot()+" "+app.getAppName());
		if(app.getIdRobot()==0){
			return null;
		}
		return app;
	}
	
	public static AppInformation getAppData(String appName){
		Path appFolder = RobotManager.getInstallationPath().resolve("data").resolve(appName);
		if(Files.exists(appFolder)){
			File file=appFolder.toFile();
			AppInformation app=new AppInformation();
			setMetaInfoApp(appFolder, app);
			app.setAppName(file.getName());
			System.out.println("++++ file name "+file.getName());
			ArrayList<FlujoInformation> flujosApp;
			if(Files.exists(appFolder.resolve("application"))){
				
				flujosApp= getFlujosApp(appFolder.resolve("application"));
			}else{
				flujosApp = getFlujosApp(appFolder);
			}
			if(flujosApp==null || flujosApp.isEmpty()){
				return null;
			}
			app.setFlujos(flujosApp);
			app.setIdRobot(getRobotID(appFolder));
			System.out.println("id robot"+app.getIdRobot()+" "+app.getAppName());
			if(app.getIdRobot()==0){
				return null;
			}
			return app;
			
		}
		return null;
	}
	
	
	public static AppInformation getAppData(Path appFolder,File botFile){
		File file=appFolder.toFile();
		AppInformation app=new AppInformation();
		setMetaInfoApp(appFolder, app);
		app.setAppName(file.getName());
		System.out.println("++++ file name "+file.getName());
		ArrayList<FlujoInformation> flujosApp;
		if(Files.exists(appFolder.resolve("application"))){
			
			 flujosApp= getFlujosApp(appFolder.resolve("application"));
		}else{
			flujosApp = getFlujosApp(appFolder);
		}
		if(flujosApp==null || flujosApp.isEmpty()){
			return null;
		}
		app.setFlujos(flujosApp);
		app.setIdRobot(getRobotID(botFile));
		System.out.println("id robot"+app.getIdRobot()+" "+app.getAppName());
		if(app.getIdRobot()==0){
			return null;
		}else{
			app.setBotFile(botFile);			
		}
		return app;
	}
	
	
	public static long getRobotID(Path appFolder){
		Path jarPath=appFolder.resolve(Constants.JARNAME);
		long robotId=0;
		File file=appFolder.toFile();
		try{
			FileFilter fileFilter=new WildcardFileFilter(Constants.REGEX_JARNAME+"*");
			File [] dir = file.listFiles(fileFilter);
			for (File file2 : dir) {
				try{
					if(file2.getName().endsWith("jar")||file2.getName().endsWith("exe")){
						jarPath=file2.toPath();
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
					}
					return robotId;
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			
		}catch(Exception ex){
			LOGGER.error("excepcion ", ex);

		}
		return robotId;
		
	}
	
	
	public static long getRobotID(File botFile){
		Path jarPath=botFile.toPath();
		long robotId=0;
		try{
				try{
					if(botFile.getName().endsWith("jar")||botFile.getName().endsWith("exe")){
						jarPath=botFile.toPath();
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
					}
					return robotId;
					
				}catch(Exception ex){
					ex.printStackTrace();
					LOGGER.error("Exepcion",ex);
				}
			
		}catch(Exception ex){
			LOGGER.error("excepcion ", ex);

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
			LOGGER.error("excepcion ", ex);

		}
		

	}
	
	public static ArrayList<FlujoInformation> getFlujosApp(Path appPath){
		ArrayList<FlujoInformation> flujos=new ArrayList<FlujoInformation>();
		File applicationFolder = appPath.toFile();
		System.out.println("consultando carpeta"+appPath.toString()+"aplicationfolder file"+applicationFolder);
		File[] listFiles = applicationFolder.listFiles();
		System.out.println("lista de archivos"+Arrays.toString(listFiles));
//		Arrays.sort(listFiles);
		for(File file: listFiles){
			if(file.isDirectory()){
				FlujoInformation flujo=new FlujoInformation();
				String directoryName = file.getName();
				String[] directorySplit = directoryName.split("\\.");
				long idFlujo;
				String nameFlujo;
				if(directorySplit.length>1){
					if(directorySplit[0].contains(".")){
						idFlujo=Long.parseLong(directorySplit[0]);
						nameFlujo=directorySplit[1];						
					}else{
						idFlujo=-1;
						nameFlujo=directoryName;
					}
				}else{
					idFlujo=-1;
					nameFlujo=directoryName;
				}
				
				File[] steps = file.listFiles();
				if(steps !=null && steps.length>0){
					ArrayList<StepInformation> stepsFiles = indexSteps(steps);
					flujo.setIdFlujo(idFlujo);
					flujo.setName(nameFlujo);
					flujo.setSteps(stepsFiles);
					flujo.setNumSteps(stepsFiles.size());
					flujo.setPath(file.toPath());
					flujos.add(flujo);					
				}
			}
		}
		return flujos;
	}
	
	
	public static void main(String[] args) {
		ArrayList<FlujoInformation> flujosApp = AppExaminator.getFlujosApp(Paths.get("C:\\Users\\senrigan\\Desktop"));
		System.out.println(flujosApp);
	}
	
	
	public static boolean flujoConstainsStepValid(FlujoInformation flujo){
		ArrayList<StepInformation> steps = flujo.getSteps();
		if(!steps.isEmpty()){
			return true;
		}
		return false;
	}
	
	
	public static boolean flujosConstanisStepsValid(ArrayList<FlujoInformation> flujos){
		for (FlujoInformation flujoInformation : flujos) {
			boolean fl = flujoConstainsStepValid(flujoInformation);
			if(!fl){
				return false;
			}
		}
		return true;
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
