package com.gdc.nms.robot.util.jade;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.HashMap;

import jade.core.AID;
//import jade.core.AgentContainer;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.util.leap.Properties;
//import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class InitPlataform {
//	private static AgentController controller;
	private static jade.wrapper.AgentContainer mainContainer;
	private static InitPlataform instance=null;
	private static HashMap<String,AID> robotRegister;
	private static SRMAgentManager srmAgentManager;
	private InitPlataform(){

	}
	

	public static InitPlataform getInstance(){
		if(instance==null){
			instance=new InitPlataform();
		}
		
		return instance;
	}
	
	public static  HashMap<String,AID> getRobotRegister(){
		return robotRegister;
	}
	
	
	public static void registerRobot(String robotName,AID senderId){
		robotRegister.put(robotName, senderId);
	}
	
	
	public static void deRegisterRobot(String robotName){
		robotRegister.remove(robotName);
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
        robotRegister=new HashMap<String,AID>();
        try {
			System.out.println("container name"+mainContainer.getContainerName()+"platform name"+mainContainer.getPlatformName()+"name"+mainContainer.getName());
		} catch (ControllerException e) {
			e.printStackTrace();
		}
        if(srmAgentManager!=null){
        	srmAgentManager=new SRMAgentManager();
        	srmAgentManager.init();
        }
		
	}
	
	
	public SRMAgentManager getAgentManager(){
		return srmAgentManager;
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
