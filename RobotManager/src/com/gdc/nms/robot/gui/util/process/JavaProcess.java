package com.gdc.nms.robot.gui.util.process;

import java.util.ArrayList;
import java.util.Arrays;

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
	
	public static void main(String[] args) {
		ArrayList<JavaProcessInfo> allJavaProcess = JavaProcess.getAllJavaProcess();
		System.out.println(allJavaProcess);
		
		
		
		try {
			String wmiValue = jWMI.getWMIValue("Select * from Win32_Process ' ","Namej¿");
			System.out.println("**"+wmiValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
