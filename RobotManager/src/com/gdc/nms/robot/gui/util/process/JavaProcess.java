package com.gdc.nms.robot.gui.util.process;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.gdc.nms.robot.util.DeleteServiceController;

public class JavaProcess {
	private static final Logger LOGGER=Logger.getLogger(JavaProcess.class);

	public static ArrayList<JavaProcessInfo> getAllJavaProcess(){
		ArrayList<JavaProcessInfo> process=new ArrayList<JavaProcessInfo>();
		String wmiValue;
		try {
			wmiValue = jWMI.getWMIValue("Select * from Win32_Process where name like '%java%' ","name,processid,commandline,CreationDate");
			String[] split = wmiValue.split("\n");
			for(int i=0;i<split.length;i++){
				JavaProcessInfo javaP=new JavaProcessInfo();
				javaP.setName(split[i]);
			
				javaP.setProcessid(Long.parseLong(split[i+1].trim()));
				javaP.setCommandLine(split[i+2]);
				DateFormat dt=new SimpleDateFormat("yyyymmddHHMMSS");
				System.out.println("date fornat"+split[i+3]);
				Date date = dt.parse(split[i+3]);
				javaP.setCreationDate(date);
				i=i+3;
				process.add(javaP);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return process;
	}
	
	
	public static boolean isValidJavaProceesId(long pid){
		if(getJavaProcessInfo(pid)!=null){
			return true;
		}

		return false;
	}
	
	
	public static JavaProcessInfo getJavaProcessInfo(long pid){
		
		try {
			String wmiValue;
			wmiValue = jWMI.getWMIValue("Select * from Win32_Process where name like '%java%' and processid="+pid+" ","name,processid,commandline,CreationDate");
			if(wmiValue!=""){				
				System.out.println("*"+wmiValue);
				String[] split = wmiValue.split("\n");
				System.out.println(split.length);
				for(int i=0;i<split.length;i++){
					JavaProcessInfo javaP=new JavaProcessInfo();
					javaP.setName(split[i]);
					javaP.setProcessid(Long.parseLong(split[i+1].trim()));
					javaP.setCommandLine(split[i+2]);
					DateFormat dt=new SimpleDateFormat("yyyymmddHHMMSS");
					Date date = dt.parse(split[i+3]);
					javaP.setCreationDate(date);
					i=i+3;
					return javaP;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	
	
	public static ArrayList<JavaProcessInfo> getALLJavaRobotProces(){
		ArrayList<JavaProcessInfo> allJavaProcess = getAllJavaProcess();
		ArrayList<JavaProcessInfo> botProcess=new ArrayList<JavaProcessInfo>();
		for (JavaProcessInfo javaProcessInfo : allJavaProcess) {
			int indexOf = javaProcessInfo.getCommandLine().lastIndexOf("bot");
			String commandLine = javaProcessInfo.getCommandLine();
			commandLine=commandLine.trim();
			commandLine=commandLine.replaceAll("\"","");
			if(isValidBotString(commandLine)){
				botProcess.add(javaProcessInfo);
			}
		}
		return botProcess;
	}
	
	
	public static  boolean isValidBotString(String string){
		return string.matches(".*bot(.)*[.]+(jar|exe)");
	}
	
	
	public static boolean isServicesAlreadyRunningForMoreFiveMinutes(String serviceName){
		LOGGER.info("checking if already running the java process for serviceName "+serviceName);
		JavaProcessInfo javaProcesServices = getJavaProcesServices(serviceName);
		if(javaProcesServices!=null){
			Date creationDate = javaProcesServices.getCreationDate();
			Date actualDate=new Date();
			Long diferenceTime=creationDate.getTime()-actualDate.getTime();
			Long millis = TimeUnit.MINUTES.toMillis(5);
			LOGGER.info("the services "+serviceName+" already running for "+diferenceTime);
			if(diferenceTime.compareTo(millis)>0){
				return true;
			}
			
		}
		return false;
	}
	
	
	public static JavaProcessInfo getJavaProcesServices(String serviceFolderName){
		ArrayList<JavaProcessInfo> allJavaRobotProces = getALLJavaRobotProces();
		for (JavaProcessInfo javaProcessInfo : allJavaRobotProces) {
			if(javaProcessInfo.getCommandLine().contains(serviceFolderName))
				return javaProcessInfo;
		}
		return null;
	}
	
	public static void main(String[] args) {
//		ArrayList<JavaProcessInfo> allJavaProcess2 = JavaProcess.getAllJavaProcess();
//		System.out.println(allJavaProcess2);
//		ArrayList<JavaProcessInfo> allJavaProcess = JavaProcess.getALLJavaRobotProces();
//		System.out.println("exact"+allJavaProcess);
//		boolean validJavaProceesId = JavaProcess.isValidJavaProceesId(4924L);
//		System.out.println(validJavaProceesId);
//		System.out.println(JavaProcess.isValidBotString("C:\\Program Files\\Java\\jre1.8.0_101\\bin\\javaw.exe -jar C:\\Program Files\\GDC\\RobotScript\\data\\SubastaSAT\\bot-1.0.jar "));
//		=2016-08-12-130356.561349-300
//		Date da=new Date(130356561349L-300); 
//		
//		
//		DateFormat dt=new SimpleDateFormat("yyyymmddHHMMSS");
//		try {
//			Date date = dt.parse("20160812173128.877456-300");
//			System.out.println(date);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		String st=new String("CommandLine=\"C:\\Program Files\\Java\\jdk1.8.0_71\\jre\\bin\\java.exe\" -Dname=\"Robot_hola\" -jar bot.jar");
		if(st.contains("hola"))
			System.out.println("hola");
		System.out.println();
				
		
	}
}
