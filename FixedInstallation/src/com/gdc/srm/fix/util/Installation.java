package com.gdc.srm.fix.util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.jar.JarEntry;

import com.gdc.srm.fix.util.os.OperatingSystem;
import com.gdc.srm.fix.util.os.windows.WindowsOS;

public class Installation {

	private static Path INSTALLATIONPATH;

	private static final Set<String> clients = new HashSet<String>();

	private static final Set<String> files = new HashSet<String>();

	private static long libVersion;

	private static boolean installed;

	private static ArrayList<String> monitorsNamesPaths;
	private static ArrayList<String> monitorNames = new ArrayList<String>();
	private static ArrayList<ArrayList<String>> monitorData=new ArrayList<ArrayList<String>>();
	private static String tempFolder;
	private static HashMap<String,JarEntry> toSkip;
	
	public static final String LOCALREGISTRY="HKCU\\Software\\GDC\\Robot";
	private static final String registryImacros="HKLM\\SOFTWARE\\Ipswitch\\iMacros";
	public static final String UnistallRegistry="HKLM\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall";
	
	private static String robotUbication=Language.get("ubication.robot");

	
	public static void setMonitorData(ArrayList<ArrayList<String>> monitorsData){
		monitorData=monitorsData;
	}
	
	public static ArrayList<ArrayList<String>> getMonitorData(){
		return monitorData;
	}
	public static void setMonitorAppsPath(ArrayList<String> obj){
		monitorsNamesPaths=obj;
	}
	
	public static ArrayList<String> getMonitorAppsPath(){
		return monitorsNamesPaths;
	}
	
	public static void setMonitorAppsNmaes(ArrayList<String> names){
		monitorNames=names;
	}
	
	public static ArrayList<String> getMonitorAppsNames(){
		return monitorNames;
	}
	
	public static void setInstallationPath(Path installationPath) {
		INSTALLATIONPATH = installationPath;
	}

	public static boolean isClientInstalled(String client) {
	    return clients.contains(client);
	}

	public static Path getInstallaPath() {
		return ;
	}

	public static boolean addClient(String client) {
		if (clients.contains(client)) {
			return false;
		}
		return clients.add(client);
	}

	public static boolean removeClient(String client) {
		if (clients.contains(client)) {
			return clients.remove(client);
		}
		return false;
	}

	public static void setClients(Set<String> clients){
		Installation.clients.clear();
		Installation.clients.addAll(clients);
	}

	public static Set<String> getClients() {
		return new HashSet<String>(clients);
	}

	public static Set<String> getFiles() {
		return new HashSet<String>(files);
	}

	public static boolean addFile(String file) {
		if (files.contains(file)) {
			return false;
		}
		return files.add(file);
	}

	public static boolean removeFile(String file) {
		if (files.contains(file)) {
			return files.remove(files);
		}
		return false;
	}

	public static void setFiles(Set<String> files){
		Installation.files.clear();
		Installation.files.addAll(files);
	}
	
	public static void setTempFolderPath(String tempFolderPath){
		tempFolder=tempFolderPath;
	}
	
	public static String getTempFolderString(){
		return tempFolder;
	}
	
	public static Path getTempFolderPath(){
		return Paths.get(tempFolder);
	}
	
	public static void setFilesToSkip(HashMap<String,JarEntry> toSkipFiles){
		toSkip=toSkipFiles;
	}
	
	public static void setRobotUbication(String ubication){
		
	}
	
	public static HashMap<String,JarEntry> getFilesToSkip(){
		return toSkip;
	}

	protected static void parseInstallationPath(String installationPath) {
		if (installationPath == null || installationPath.isEmpty()) {
			throw new InstallationParseException("Installation Path was not set.");
		}
		Path path = Paths.get(installationPath);
		if (Files.exists(path) && Files.isDirectory(path)) {
			setInstallationPath(path);
		} else {
			throw new InstallationParseException("Installation Path does not exist.");
		}
	}

	protected static void parseFiles(String filesStr) {
		if (filesStr == null || filesStr.isEmpty()) {
			return;
		}
		String[] files = filesStr.split(Character.toString(Constants.SEPARATOR));
		for (String file : files) {
			if (file == null || file.isEmpty()) {
				continue;
			}
			addFile(file);
		}
	}

	protected static void parseClients(String clientsStr) {
		if (clientsStr == null || clientsStr.isEmpty()) {
			return;
		}
		String[] clients = clientsStr.split(Character.toString(Constants.SEPARATOR));
		for (String client : clients) {
			if (client == null || client.isEmpty()) {
				continue;
			} else if (Files.exists(INSTALLATIONPATH.resolve(client)) && Files.isDirectory(INSTALLATIONPATH.resolve(client))) {
				addClient(client);
			}
		}
	}

	protected static void parseLibVersion(String libVersionStr) {
	    if (libVersionStr == null || libVersionStr.isEmpty()) {
	        throw new InstallationParseException("Invalid lib version.");
	    }
	    try {
	        libVersion = Long.parseLong(libVersionStr);
	    } catch (NumberFormatException e) {
	        throw new InstallationParseException("Invalid lib version.");
	    }
	}

	protected static void setInstalled(boolean installed) {
		Installation.installed = installed;
	}

	public static void load() throws InstallationParseException {
		setInstalled(false);
		Path registry = getInstallationRegistry();
		if (Files.exists(registry)) {
			Properties properties = new Properties();
			try {
				properties.load(Files.newInputStream(registry));
				parseInstallationPath(properties.getProperty(Constants.INTALLATION_PATH_PROPERTY));
				parseLibVersion(properties.getProperty(Constants.INTALLATION_VERSION_PROPERTY));
				setInstalled(true);
			} catch (IOException e) {
				throw new InstallationParseException("Unexpected error while trying to read the installation file.", e);
			}
		}
	}
	
	public static  boolean isInstalledRegistry(){
		OperatingSystem os = Environment.getOS();
		if(os instanceof WindowsOS){
			try{
				
				String redRegistryWindows = CommandExecutor.redRegistryWindows(LOCALREGISTRY);
				if(!isErrorConseleRegistry(redRegistryWindows)){
					return true;
				}
//				}else{					
//					CommandExecutor.addRegistryWindows(LOCALREGISTRY, "tempPath", getTempFolderPath());
//				}
				return false;
			}catch(Exception ex){
				return false;
			}
		}else{
			setInstalled(false);
			Path registry = getInstallationRegistry();
			if (Files.exists(registry)) {
				Properties properties = new Properties();
				try {
					properties.load(Files.newInputStream(registry));
					parseInstallationPath(properties.getProperty(Constants.INTALLATION_PATH_PROPERTY));
					parseLibVersion(properties.getProperty(Constants.INTALLATION_VERSION_PROPERTY));
					setInstalled(true);
					return true;
				} catch (IOException e) {
					throw new InstallationParseException("Unexpected error while trying to read the installation file.", e);
				}
			}
			return false;
		}
	}
	
	
	
	
	public static boolean  isInstalledImacros(){
		try {
			System.out.println("entro al metodo de comprobacion");
			String redRegistryWindows = CommandExecutor.redRegistryWindows(registryImacros);
			System.out.println("esta instalado"+redRegistryWindows);
			if(!redRegistryWindows.contains("ERROR")){
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	
	private static boolean isErrorConseleRegistry(String consoleExit){
		if(consoleExit.contains("ERROR")){
			return true;
		}
		return false;
	}
	

	public static boolean isInstalled() {
		return installed;
	}

	public static void setLibVersion(long libVersion) {
        Installation.libVersion = libVersion;
    }

	public static long getLibVersion() {
	    return libVersion;
	}

	private static String convertSetToString(Set<String> set) {
		StringBuilder builder = new StringBuilder();
		for (String value : set) {
			builder.append(value).append(Constants.SEPARATOR);
		}
		if (builder.length() > 0 && builder.charAt(builder.length() - 1) == Constants.SEPARATOR) {
			builder.deleteCharAt(builder.length() - 1);
		}
		return builder.toString();
	}

	protected static Path getInstallationRegistry() {
		return Environment.getInstallationCachePath().resolve(Constants.INSTALLATION_REGISTRY_NAME);
	}
	/**
	 * this method save a propertys in file for knowledge of installation paths and version 
	 * @throws IOException
	 */
	
	public static void store() throws IOException {
		Properties properties = new Properties();
		properties.setProperty(Constants.INTALLATION_PATH_PROPERTY, getInstallaPath().toString());
		properties.setProperty(Constants.INTALLATION_VERSION_PROPERTY, Long.toString(getLibVersion()));

		if (Files.notExists(Environment.getInstallationCachePath())) {
			Files.createDirectories(Environment.getInstallationCachePath());
		}
		if (Files.notExists(getInstallationRegistry())) {
			Files.createFile(getInstallationRegistry());
		}
		OutputStream outputStream = Files.newOutputStream(getInstallationRegistry());
		properties.store(outputStream, "");
	}

	private Installation() {
	}
	
	
	public static void setUbicationRobot(String ubication){
		robotUbication=ubication;
	}
	
	public static String getUbicationRobot(){
		return robotUbication;
	}
	


}
