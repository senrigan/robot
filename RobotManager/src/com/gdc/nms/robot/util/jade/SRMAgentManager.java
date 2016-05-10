package com.gdc.nms.robot.util.jade;

import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class SRMAgentManager {
	public  static  long WAITTIMEOUT=10000L;
	public static long POOLING_INTERVAL=45000L;
	public static String AYA="AYA";
	public static String IAA="IAA";
	public static String STA="STA";
	public static String RUN="RUN";
	public static String WAT="WAT";
	private  SRMAgent srmAgent;
	
	
	public void init(){
		try {
			AgentController agent = InitPlataform.getContainer().createNewAgent("srmagent","com.gdc.nms.robot.util.jade.SRMAgent",new Object[1]);
			agent.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
		srmAgent=new SRMAgent();
	}
	
	
	public SRMAgent getAgent(){
		return srmAgent;
	}
}
