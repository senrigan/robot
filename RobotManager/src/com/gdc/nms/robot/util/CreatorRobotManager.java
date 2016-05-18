package com.gdc.nms.robot.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipException;

import org.apache.commons.io.FileUtils;

import com.gdc.nms.robot.gui.DeleteDirectory;
import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.nms.robot.util.indexer.FlujoInformation;
import com.gdc.nms.robot.util.indexer.StepInformation;
import com.gdc.robothelper.webservice.robot.CreatorRobotWebService;


public class CreatorRobotManager {
	
	private String applicationName;
	private long idApp;
	private ArrayList<FlujoInformation> flujos;
	private long realRobot;
	private Path appPath;
	private Path dataPath;
	private boolean addFiles;
	private Date initDate;
	private int retries;
	private int timeLapse;
	public CreatorRobotManager(){
		
	}
	
	public  boolean createRobot(InfoRobotMaker infoRobotM,boolean addFiles){
		this.applicationName=infoRobotM.getAppSelected().getAlias();
		this.idApp=infoRobotM.getAppSelected().getId();
		this.flujos=infoRobotM.getFlujos();
		this.addFiles=addFiles;
		this.initDate=infoRobotM.getDateForRun();
		this.timeLapse=infoRobotM.getTimeLapse();
		this.retries=infoRobotM.getRetries();

		return createFilesForRobot();
	}
	
	public  boolean createRobotWithPath(InfoRobotMaker infoRobotM,boolean addFiles ){
		this.applicationName=infoRobotM.getAppSelected().getAlias();
		this.idApp=infoRobotM.getAppSelected().getId();
		this.flujos=infoRobotM.getFlujos();
		this.dataPath=infoRobotM.getDataFolder();
		this.addFiles=addFiles;
		this.initDate=infoRobotM.getDateForRun();
		this.timeLapse=infoRobotM.getTimeLapse();
		this.retries=infoRobotM.getRetries();
		return createFilesForRobot();
	}
	
	public boolean createFilesForRobot(){
		if(addFiles){
			return addFilesAndFolderExistRobot();
		}else{
			
			if(createNewRobot()){
				return true;
				
			}else{
				Path installationPath = RobotManager.getInstallationPath();
				appPath = installationPath.resolve("data").resolve(applicationName);
				if(Files.exists(appPath)){
					DeleteDirectory.delete(appPath.toString());
				}
				CreatorRobotWebService.deleteRobot(realRobot);
				return false;
			}
		}
		
	}
	
	private boolean addFilesAndFolderExistRobot(){
		if(createFolderApplication()){
				if(dataPath!=null){
					if(createDataApplication()){
						
					}
				}
				return true;
			
		}
		return false;
	}
	private boolean createNewRobot(){
		System.out.println("//// calculateInitDateWebServices"+ getInitDateForWebServices());
		String location=RobotManager.getUbication();
		String idRobot = CreatorRobotWebService.getIdRobot(applicationName, "0", location, ""+idApp, ""+retries,""+timeLapse, getInitDateForWebServices(),"");
		try{
			realRobot=Long.parseLong(idRobot);
			System.out.println(realRobot);
			Path installationPath = RobotManager.getInstallationPath();
			Path botJar = installationPath.resolve("inMonitor").resolve("bot-1.0.jar");
			Path propertiesPath=installationPath.resolve("inMonitor").resolve("robot.properties");
			modifyFileRobotId(realRobot, propertiesPath);
			modifyJar(botJar, propertiesPath, Constants.PROPERTIESJARBOT);
			if(getRobotID(botJar)==realRobot){
				
				if(createFolderApplication()){
					if(copyRobotJarToAppFolder(botJar)){
						createAppConfig();
						if(dataPath!=null){
							if(createDataApplication()){
							
							}
						}
						return true;
					}
				}
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return false;
	}
	
	
	private String getInitDateForWebServices(){
		SimpleDateFormat formate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formate.format(initDate);
	}
	public static String getcalculateDateServer() {
		String dateServerString = CreatorRobotWebService.getDateServer();
		System.out.println("date server"+dateServerString);
		SimpleDateFormat formate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateServer;
		String format="";
		
		
		
		try {
			dateServer = formate.parse(dateServerString);
			Calendar cal=Calendar.getInstance();
			cal.setTime(dateServer);
			cal.add(Calendar.MINUTE, 15);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			Date calculateTime = cal.getTime();
			format = formate.format(calculateTime);
			System.out.println(format);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("calculated date"+format);
		return format;
	}
	
	public  boolean createDataApplication(){
		
		try {
			File dataFolder = dataPath.toFile();
			File[] listFiles = dataFolder.listFiles();
			Path realDataPath = appPath.resolve("data");
			if(!Files.exists(realDataPath)){
				Files.createDirectories(realDataPath);
			}
			for (File file : listFiles) {
				Path fileInstalled = realDataPath.resolve(file.getName());
				if(Files.exists(fileInstalled)){
					Files.delete(fileInstalled);
				}
				FileUtils.copyFileToDirectory(file, realDataPath.toFile());
			}
			FileUtils.copyDirectoryToDirectory(dataPath.toFile(), appPath.toFile());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	
	public void modifyFileRobotId(long robotId,Path robotFile) throws IOException{
		FileWriter fw=null;
		BufferedWriter bw=null;
		try {
			fw = new FileWriter(robotFile.toFile());
			bw = new BufferedWriter(fw);
			bw.write("id="+robotId);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			bw.close();
		}
		

	}
	
	private boolean createFolderApplication(){
		Path installationPath = RobotManager.getInstallationPath();
		appPath = installationPath.resolve("data").resolve(applicationName);
		System.out.println("creando folder aplicaction");
		if(Files.exists(appPath)){
			if(copyRobotConfig())
			if(createSubFolderApplication(appPath))
				return true;
		}else{
			try {
				Files.createDirectories(appPath);
				if(copyRobotConfig())
				if(createSubFolderApplication(appPath))
					return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	
	
	private boolean copyRobotConfig(){
		Path robotConf = appPath.resolve("robot-config.xml");
		if(Files.exists(robotConf)){
			try {
				Files.delete(robotConf);
				Path xml = RobotManager.getInstallationPath().resolve("inMonitor").resolve("robot-config.xml");
				Files.copy(xml, robotConf);
				return  true;
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			return false;
			
		}else{
			Path xml = RobotManager.getInstallationPath().resolve("inMonitor").resolve("robot-config.xml");
			try {
				Files.copy(xml, robotConf);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
	}
	
	
	private boolean createSubFolderApplication(Path parentFolder){
		Path applicationPath = parentFolder.resolve("application");
		System.out.println("creadno subfolderApplication");
		if(Files.exists(applicationPath)){
			if(createFlujosFolder(applicationPath))
				return true;
		}else{
			try {
				System.out.println("creando aplicacion");
				Files.createDirectories(applicationPath);
				if(createFlujosFolder(applicationPath))
					return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	private boolean createFlujosFolder(Path parentFolder){
		System.out.println("cradno Flujos Folder num"+flujos.size());
		for (FlujoInformation flujo : flujos) {
			System.out.println("procesando flujo name"+flujo.getName());
			Path newFlujoPath = parentFolder.resolve(flujo.getIdFlujo()+"."+flujo.getName());
			System.out.println("//newFlujosPAth"+newFlujoPath);
			ArrayList<StepInformation> steps = flujo.getSteps();
			if(Files.exists(newFlujoPath)){
				try {
					createStepsFiles(newFlujoPath,flujo.getSteps());
				} catch (IOException e) {
					e.printStackTrace();
					deleteStepsCreate(newFlujoPath, steps);
					return false;
				}
			}else{
				try{
					
					Files.createDirectories(newFlujoPath);
					createStepsFiles(newFlujoPath,flujo.getSteps());
				}catch(IOException e){
					
					e.printStackTrace();
					deleteStepsCreate(newFlujoPath, steps);
					return false;

				}
					
				
			}
		}
		return true;
	}
	
	
	private void createStepsFiles(Path flujoPath,ArrayList<StepInformation> steps) throws IOException{
		System.out.println("flujos path"+flujoPath);
	 	
    	InputStream inStream = null;
    	OutputStream outStream = null;
		for (StepInformation stepInformation : steps) {
			System.out.println("///steps"+stepInformation);
//			FileUtils.copyFileToDirectory(stepInformation.getPath().toFile(),flujoPath.toFile() );
//			Files.copy(stepInformation.getPath(), flujoPath);
//			FileUtils.cop
//			System.out.println("copi from"+stepInformation.getPath().toFile()+" to"+flujoPath.toFile());
			String nameFile = stepInformation.getPath().toFile().getName();
			System.out.println("namefiled "+nameFile);
			Path toPath = flujoPath.resolve(nameFile);
			System.out.println("////creando archivo en "+toPath.toString());
			if(!Files.exists(toPath)){
				if(toPath.toFile().createNewFile()){
					System.out.println("se esta creando el archivo"+toPath);
				}
			}
			inStream = new FileInputStream(stepInformation.getPath().toFile());
	    	    outStream = new FileOutputStream(toPath.toFile());
	        	
	    	    byte[] buffer = new byte[1024];
	    		
	    	    int length;
	    	    //copy the file content in bytes 
	    	    while ((length = inStream.read(buffer)) > 0){
	    	  
	    	    	outStream.write(buffer, 0, length);
	    	 
	    	    }
	    	 
	    	    inStream.close();
	    	    outStream.close();
		}
		
	}
	
	
	private void deleteStepsCreate(Path flujoPath,ArrayList<StepInformation> steps){
		for (StepInformation stepInformation : steps) {
			Path actualStepPath=flujoPath.resolve(stepInformation.getPath().getFileName());
			if(Files.exists(actualStepPath)){
				
				try {
					Files.delete(actualStepPath);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	
	 public void modifyJar(Path jarPath,Path FileToAdd,String fileJarPath ) throws IOException{
		  String jarName = jarPath.toString();
		  String fileName = FileToAdd.toString();

		  // Create file descriptors for the jar and a temp jar.

		  File jarFile = new File(jarName);
		  File tempJarFile = new File(jarName + ".tmp");

		  // Open the jar file.

		  JarFile jar = new JarFile(jarFile);
		  System.out.println(jarName + " opened.");

		  // Initialize a flag that will indicate that the jar was updated.

		  boolean jarUpdated = false;

		  try {
		     // Create a temp jar file with no manifest. (The manifest will
		     // be copied when the entries are copied.)

		     Manifest jarManifest = jar.getManifest();
		     JarOutputStream tempJar =
		        new JarOutputStream(new FileOutputStream(tempJarFile));

		     // Allocate a buffer for reading entry data.

		     byte[] buffer = new byte[1024];
		     int bytesRead;

		     try {
		        // Open the given file.

		        FileInputStream file = new FileInputStream(fileName);

		        try {
		           // Create a jar entry and add it to the temp jar.

		           JarEntry entry = new JarEntry(fileJarPath);
		           tempJar.putNextEntry(entry);

		           // Read the file and write it to the jar.

		           while ((bytesRead = file.read(buffer)) != -1) {
		              tempJar.write(buffer, 0, bytesRead);
		           }
		           file.close();
		           System.out.println(entry.getName() + " added.");
		        }
		        finally {
		           file.close();
		        }

		        // Loop through the jar entries and add them to the temp jar,
		        // skipping the entry that was added to the temp jar already.

		        for (Enumeration entries = jar.entries(); entries.hasMoreElements(); ) {
		           // Get the next entry.

		           JarEntry entry = (JarEntry) entries.nextElement();

		           // If the entry has not been added already, add it.
		           if (! entry.getName().equals(fileJarPath)) {
		              // Get an input stream for the entry.

		              InputStream entryStream = jar.getInputStream(entry);

		              // Read the entry and write it to the temp jar.

		              tempJar.putNextEntry(entry);

		              while ((bytesRead = entryStream.read(buffer)) != -1) {
		                 tempJar.write(buffer, 0, bytesRead);
		              }
		           }
		        }
		        tempJar.close();

		        jarUpdated = true;
		     }
		     catch (Exception ex) {
		    	 
		    	 ex.printStackTrace();
		        System.out.println(ex);

		        // Add a stub entry here, so that the jar will close without an
		        // exception.

		        tempJar.putNextEntry(new JarEntry("stub"));
		     }
		     finally {
		        tempJar.close();
		        jar.close();
		     }
		  }
		  finally {
		     jar.close();
		     System.out.println(jarName + " closed.");

		     // If the jar was not updated, delete the temp jar file.

		     if (! jarUpdated) {
		        tempJarFile.delete();
		     }
		  }

		  // If the jar was updated, delete the original jar file and rename the
		  // temp jar file to the original name.

		  if (jarUpdated) {
		     jarFile.delete();
		     tempJarFile.renameTo(jarFile);
		     System.out.println(jarName + " updated.");
		  }
	 }
	
	 private static long getRobotID(Path jarPath){
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
	    
	 
	 private boolean copyRobotJarToAppFolder(Path robotPath){
		 try {
			FileUtils.copyFileToDirectory(robotPath.toFile(),appPath.toFile(),false);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return false;
	 }
	 
	 
	 private boolean createAppConfig() throws IOException{
		 Path file = appPath.resolve("application").resolve("app-config.xml");
		 ArrayList<String> list=new ArrayList<String>();
		 list.add("<app-config id=\""+idApp+"\" alias=\""+applicationName+"\"  />");
		 try{
			 Files.write(file, list, Charset.forName("UTF-8"));
			 return true;
		 }catch(Exception ex){
			 
		 }
		 return false;
	 }
	 public static void main(String[] args) {
//		 String idRobot = CreatorRobotWebService.getIdRobot("BANCOS AUTORIZADOS", "0", "GUADALAJARA", ""+86, "2","5", CreatorRobotManager.getcalculateDateServer());
//		 System.out.println("robot id"+idRobot);
		 long robotID = CreatorRobotManager.getRobotID(Paths.get("C:\\Users\\senrigan\\Documents\\pruebas\\GDC\\RobotScript\\inMonitor\\bot-1.0.jar"));
		 System.out.println("robbot id"+robotID);
	 }
}
