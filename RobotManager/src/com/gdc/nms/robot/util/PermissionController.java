package com.gdc.nms.robot.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PermissionController {
	
	private static final String  AUTH_USER="Authenticated Users";
	private static final String EVERYONE="Everyone";
	private static final String USERS="Users";
	private static final String USERS_ES="Usuarios";
	private static final String SYSTEM="SYSTEM";
	public static void main(String[] args) {
		PermissionController ts=new PermissionController();
		
//		ts.modifyPermissionFolderFullControl(Paths.get("G:\\Documentos\\Test\\pruebas.txt"),ts.getCurrentUserName());
//		ts.modifyPermissionFolder(Paths.get("G:\\Documentos\\Test"),"users");
//		ts.modifyPermissionFolder(Paths.get("G:\\Documentos\\Test"),"Everyone");
//		System.out.println(ts.getListUser());
		ts.modifyPermissionAllUsers(Paths.get("G:\\Documentos\\TEST2"));
//		System.out.println(ts.modifyPermissionAllUsers());
		System.out.println();
	}
	
	
	private void modifyPermissionFolderFullControl(Path folder,String user){
		System.out.println(user);
		try {
			Process exec = Runtime.getRuntime().exec("icacls "+folder.toString()+" /grant:r \""+user+"\":(OI)(CI)F ");
			String line="";
		       BufferedReader in = new BufferedReader(
		               new InputStreamReader(exec.getInputStream()) );
		       while ((line = in.readLine()) != null) {
		         System.out.println(line);
		       }
		       in.close();
			System.out.println(exec.exitValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void modifyPermissionAllUsers(Path folder){
		String currentUser = getCurrentUserName();
		ArrayList<String> listUsers = getListUser();
		if(!listUsers.isEmpty()){
			if(listUsers.indexOf(currentUser)==-1){
				listUsers.add(currentUser);
			}
		}else{
			listUsers.add(currentUser);
		}
		listUsers.add(AUTH_USER);
		listUsers.add(EVERYONE);
		listUsers.add(SYSTEM);
		if(System.getProperty("user.language").contains("es")){
			listUsers.add(USERS_ES);
		}else{
			
			listUsers.add(USERS);
		}
		System.out.println("listusers**"+listUsers);
		for (String userAccount : listUsers) {
			try{
				modifyPermissionFolderFullControl(folder, userAccount);
			}catch(Exception ex){
				
			}
		}
	}
	private String getCurrentUserName(){
		return System.getenv("USERNAME");
	}
	
	
	private ArrayList<String> getListUser(){
		ArrayList<String> listUsers=new ArrayList<String>();
		try {
			Process exec = Runtime.getRuntime().exec("wmic useraccount  get  Name");
			String line="";
		       BufferedReader in = new BufferedReader(
		               new InputStreamReader(exec.getInputStream()) );
		       while ((line = in.readLine()) != null) {
		    	   System.out.println("line"+line);
		    	   if(!line.isEmpty() ){
		    		   if(!line.contains("Name")){
		    			   System.out.println("agregando"+line);
		    			   listUsers.add(line.trim());		    			   
		    		   }
		    	   }
		       }
		       in.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listUsers;
	}
}
