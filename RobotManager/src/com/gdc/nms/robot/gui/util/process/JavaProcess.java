package com.gdc.nms.robot.gui.util.process;

import java.util.ArrayList;

public class JavaProcess {
	public static ArrayList<JavaProcessInfo> getAllJavaProcess(){
		ArrayList<JavaProcessInfo> process=new ArrayList<JavaProcessInfo>();
		String wmiValue;
		try {
			wmiValue = jWMI.getWMIValue("Select * from Win32_Process where name like '%java%' ","name,processid,commandline");
			String[] split = wmiValue.split("\n");
			for(int i=0;i<split.length;i++){
				JavaProcessInfo javaP=new JavaProcessInfo();
				javaP.setName(split[i]);
			
				javaP.setProcessid(Long.parseLong(split[i+1].trim()));
				javaP.setCommandLine(split[i+2]);
				i=i+2;
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
			wmiValue = jWMI.getWMIValue("Select * from Win32_Process where name like '%java%' and processid="+pid+" ","name,processid,commandline");
			String[] split = wmiValue.split("\n");
			for(int i=0;i<split.length;i++){
				JavaProcessInfo javaP=new JavaProcessInfo();
				javaP.setName(split[i]);
				
				javaP.setProcessid(Long.parseLong(split[i+1].trim()));
				javaP.setCommandLine(split[i+2]);
				i=i+2;
				return javaP;
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
//			System.out.println(javaProcessInfo.getCommandLine());
//			System.out.println(javaProcessInfo.getCommandLine().matches(".*bot(.)*[.]+(jar|exe)"));
			String commandLine = javaProcessInfo.getCommandLine();
			commandLine=commandLine.trim();
			commandLine=commandLine.replaceAll("\"","");
//			System.out.println("commandline"+commandLine+"isvalid"+isValidBotString(commandLine));
			if(isValidBotString(commandLine)){
				botProcess.add(javaProcessInfo);
			}
		}
		return botProcess;
	}
	
	
	public static  boolean isValidBotString(String string){
		return string.matches(".*bot(.)*[.]+(jar|exe)");
	}
	
	public static void main(String[] args) {
//		ArrayList<JavaProcessInfo> allJavaProcess2 = JavaProcess.getAllJavaProcess();
//		System.out.println(allJavaProcess2);
//		ArrayList<JavaProcessInfo> allJavaProcess = JavaProcess.getALLJavaRobotProces();
//		System.out.println("exact"+allJavaProcess);
		boolean validJavaProceesId = JavaProcess.isValidJavaProceesId(4924L);
		System.out.println(validJavaProceesId);
//		System.out.println(JavaProcess.isValidBotString("C:\\Program Files\\Java\\jre1.8.0_101\\bin\\javaw.exe -jar C:\\Program Files\\GDC\\RobotScript\\data\\SubastaSAT\\bot-1.0.jar "));
		
		
		
		
		
	}
}
