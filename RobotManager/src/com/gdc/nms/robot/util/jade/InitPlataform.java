package com.gdc.nms.robot.util.jade;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

//import jade.core.AgentContainer;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.util.leap.Properties;
//import jade.wrapper.AgentController;

public class InitPlataform {
//	private static AgentController controller;
	private static jade.wrapper.AgentContainer mainContainer;
	private static InitPlataform instance=null;
	
	private InitPlataform(){

	}
	
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
		Properties props = new Properties();
		props.setProperty(ProfileImpl.MAIN, "true");
		props.setProperty(ProfileImpl.PLATFORM_ID, "robot_platform");
		props.setProperty(ProfileImpl.MAIN_PORT, "1192");
		props.setProperty(ProfileImpl.GUI, "true");
		props.setProperty(ProfileImpl.MAIN_HOST, "localhost");
		Profile profile = new ProfileImpl(props);
		
//		Profile profile = new ProfileImpl("127.0.0.1",1192,"robot_platform");

		return profile;
	}
	
	
	private static void createContainer() throws IllegalStateException{
		jade.core.Runtime rt=jade.core.Runtime.instance();
		Profile profile =getProfile();		
        mainContainer =rt.createMainContainer(profile);
		 DFManager.initScan();
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
