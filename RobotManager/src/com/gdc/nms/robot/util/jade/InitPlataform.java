package com.gdc.nms.robot.util.jade;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

//import jade.core.AgentContainer;
import jade.core.Profile;
import jade.core.ProfileImpl;
//import jade.wrapper.AgentController;
import jade.core.Runtime;

public class InitPlataform {
//	private static AgentController controller;
	private static jade.wrapper.AgentContainer mainContainer;
	private static InitPlataform instance=null;
	
	private InitPlataform(){
		
	};
	
	public static InitPlataform getInstance(){
		if(instance==null){
			instance=new InitPlataform();
		}
		
		return instance;
	}
	
	
	public static jade.wrapper.AgentContainer getContainer(){
		return mainContainer;
	}
	
	public boolean runAgentContainer (){
		try{
			createContainer();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	
	private static Profile getProfile(){
		Profile profile = new ProfileImpl("localhost",1192,"robot_platform");
		return profile;
	}
	
	
	private static void createContainer() throws IllegalStateException{
		Runtime rt=Runtime.instance();
		 mainContainer= rt.createAgentContainer(getProfile());
//		 DFManager.initScan();
	}
	
	
	public static jade.wrapper.AgentContainer getMainContainer(){
		return mainContainer;
	}
	
	
	private static boolean isAvailablePort(int port) {
	    ServerSocket ss = null;
	    DatagramSocket ds = null;
	    try {
	        ss = new ServerSocket(port);
	        ss.setReuseAddress(true);
	        ds = new DatagramSocket(port);
	        ds.setReuseAddress(true);
	        return true;
	    } catch (IOException e) {
	    } finally {
	        if (ds != null) {
	            ds.close();
	        }
	        if (ss != null) {
	            try {
	                ss.close();
	            } catch (IOException e) {
	            }
	        }
	    }
	    return false;
	}
	
	public static void main(String[] args) {
//		int port=0;
//		System.out.println(port);
//		while(port<1500L){
//			System.out.println("puerto libre port"+port +" "+InitPlataform.isAvailablePort(port));
//		    port++;
//		}
		InitPlataform.getInstance().runAgentContainer();
	}
	
}
