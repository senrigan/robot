package com.gdc.nms.robot.util.jade;

import jade.core.AID;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class SRMAgentManager {
	public  static  long WAITTIMEOUT=10000L;
	public static long POOLING_INTERVAL=15000L;
	public static final String AYA="AYA";
	public static final String IAA="IAA";
	public static final String STA="STA";
	public static final String RUN="RUN";
	public static final String WAT="WAT";
	public static final String OFF="OFF";
	public static final String NXT="NXT";
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
	
	
	public boolean  stopAgent(AID agent){
		System.out.println("sending message to "+agent+"message "+OFF);
		String senMessage = srmAgent.senMessage(agent, OFF);
		if(senMessage!=null && senMessage.equals(OFF)){
			return true;
		}
		return false;
	}
}
