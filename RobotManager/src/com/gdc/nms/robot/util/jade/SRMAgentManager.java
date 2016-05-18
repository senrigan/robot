package com.gdc.nms.robot.util.jade;

import jade.core.AID;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class SRMAgentManager {
	public  static  long WAITTIMEOUT=10000L;
	public static long POOLING_INTERVAL=60000L;
	public static final String AYA="AYA";
	public static final String IAA="IAA";
	public static final String STA="STA";
	public static final String RUN="RUN";
	public static final String WAT="WAT";
	public static final String OFF="OFF";
	public static final String NXT="NXT";
	public static final int KILLCODE=666;
	private static   SRMAgent srmAgent;
	
	
	public void init(){
		srmAgent=new SRMAgent();
		try {
			//			AgentController agent = InitPlataform.getContainer().createNewAgent("srmagent","com.gdc.nms.robot.util.jade.SRMAgent",new Object[1]);
			AgentController agent = InitPlataform.getContainer().acceptNewAgent("srmagent", srmAgent);
			agent.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
	}
	
	
	public SRMAgent getAgent(){
		return srmAgent;
	}
	
	
	public static  boolean  stopAgent(AID agent){
		srmAgent.senMessageToKill(agent);
		return true;
	}
}
