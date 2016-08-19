package com.gdc.nms.robot.util.jade;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.gdc.nms.robot.gui.RobotManager;

import jade.core.AID;
//import jade.core.AgentContainer;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.util.leap.Properties;
//import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.PlatformController;
import jade.wrapper.PlatformEvent;

public class InitPlataform {
//	private static AgentController controller;
	private static jade.wrapper.AgentContainer mainContainer;
	private static InitPlataform instance=null;
	private static HashMap<String,AID> robotRegister;
	private static HashMap<String,AID> robotToKill;
	private static SRMAgentManager srmAgentManager;
	private static final Logger LOGGER=Logger.getLogger(InitPlataform.class.toString());
	private InitPlataform(){
		LOGGER.addAppender(RobotManager.logAppender);
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
		RobotManager.getGuiManager().getJtreManager().addToRun(robotName);
	}
	
	
	public static void deRegisterRobot(String robotName){
		robotRegister.remove(robotName);
		RobotManager.getGuiManager().getJtreManager().changeRobotRunToStop(robotName);

	}
	
	
	public static void registerToKill(String robotName,AID senderId){
		robotToKill.put(robotName, senderId);
	}
	
	
	public static void removeToKill(String robotName){
		robotToKill.remove(robotName);
	}
	
	
	public static HashMap<String, AID> getMapToKill(){
		return robotToKill;
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
			LOGGER.error("Error :", ex);
			return false;
		}
	}
	
	
	private static Profile getProfile(){
//		String ip;
//		try {
//		 ip=InetAddress.getLocalHost().getHostAddress();
//		} catch (UnknownHostException e) {
//			ip=InetAddress.getLoopbackAddress().getHostAddress();
//		}
		
		String ip=InetAddress.getLoopbackAddress().getHostAddress();
		Properties props = new Properties();
		props.setProperty(ProfileImpl.MAIN, "true");
		props.setProperty(ProfileImpl.PLATFORM_ID, "robot_platform");
		props.setProperty(ProfileImpl.MAIN_PORT, "1192");
		props.setProperty(ProfileImpl.GUI, "true");
		
		props.setProperty(ProfileImpl.MAIN_HOST, ip);
		props.setProperty(ProfileImpl.LOCAL_HOST, ip);
//		props.setProperty(ProfileImpl.LOCALHOST_CONSTANT, "senrigan3");
		
//		props.setProperty(ProfileImpl.LOCAL_PORT, "1191");
//		props.setProperty(ProfileImpl.EXPORT_PORT, "3580");
//		props.setProperty(ProfileImpl.IMTP,"3850" );
//		props.setProperty(ProfileImpl.MTPS, "jade.mtp.http.MessageTransportProtocol(http://"+ip+":7778/acc)");
//		props.setProperty("jade_mtp_http_port","7778");
//		props.setProperty("jade_mtp_http_proxyHost","10.42.0.92");
		
		
//		props.setProperty("jade_mtp_http_proxyHost","http://senrigan:7778/acc");
		Profile profile = new ProfileImpl(props);
		
//		Profile profile = new ProfileImpl("127.0.0.1",1192,"robot_platform");

		return profile;
	}
	
	
	private static void createContainer() throws IllegalStateException{
		jade.core.Runtime rt=jade.core.Runtime.instance();
		Profile profile =getProfile();	
	
        mainContainer =rt.createMainContainer(profile);
        try {
			mainContainer.addPlatformListener(new PlatformController.Listener(){

				@Override
				public void bornAgent(PlatformEvent anEvent) {
					
					System.out.println("the robot  "+anEvent.getAgentGUID()+"is born");
					
				}

				@Override
				public void deadAgent(PlatformEvent anEvent) {
					System.out.println("the robot  "+anEvent.getAgentGUID()+"is dead");

					
				}

				@Override
				public void startedPlatform(PlatformEvent anEvent) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void suspendedPlatform(PlatformEvent anEvent) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void resumedPlatform(PlatformEvent anEvent) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void killedPlatform(PlatformEvent anEvent) {
					// TODO Auto-generated method stub
					
				}
				
			});
		} catch (ControllerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        robotRegister=new HashMap<String,AID>();
        try {
			System.out.println("container name"+mainContainer.getContainerName()+"platform name"+mainContainer.getPlatformName()+"name"+mainContainer.getName());
			initAgents();
        } catch (ControllerException e) {
			e.printStackTrace();
			LOGGER.error("Excepcion ",e);
		}
        
		
	}
	
	
	private static void initAgents(){
		if(srmAgentManager==null){
        	srmAgentManager=new SRMAgentManager();
        	srmAgentManager.init();
        }
	}
	
	
	public static SRMAgentManager getAgentManager(){
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
	            	LOGGER.error("Excepcion ",e);
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
//		try {
//			System.out.println(InetAddress.getLoopbackAddress());
//			System.out.println(""+InetAddress.getLocalHost().getHostAddress());
//			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
//			for (;networkInterfaces.hasMoreElements();) {
//				NetworkInterface nextElement = networkInterfaces.nextElement();
//				Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
//				for(;inetAddresses.hasMoreElements();){
//					InetAddress nextElement2 = inetAddresses.nextElement();
//					System.out.println(nextElement2.getHostAddress());
//				}
//			}
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		InitPlataform.getInstance().runAgentContainer();
// catch (SocketException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
}
