package com.gdc.nms.robot.util.jade;

import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.nms.robot.util.indexer.AppInformation;

import jade.core.AID;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class SRMAgentManager {
	public  static  long WAITTIMEOUT=20000L;
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
	private static AgentValidator agentValidator;
	private static StatusAgent staAgent;
	private static final Logger LOGGER = Logger.getLogger(SRMAgent.class.toString());
	
	public void init(){
		LOGGER.addAppender(RobotManager.logAppender);
		srmAgent=new SRMAgent();
		agentValidator=new AgentValidator();
		staAgent=new StatusAgent();
		try {
			//			AgentController agent = InitPlataform.getContainer().createNewAgent("srmagent","com.gdc.nms.robot.util.jade.SRMAgent",new Object[1]);
			AgentController agent = InitPlataform.getContainer().acceptNewAgent("srmagent", srmAgent);
			agent.start();
			agent=InitPlataform.getContainer().acceptNewAgent("validagent", agentValidator);
			agent.start();
			agent=InitPlataform.getContainer().acceptNewAgent("statusagent",staAgent);
			agent.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
			LOGGER.error("Excepcion", e);
		}
	}
	
	
	public SRMAgent getAgent(){
		return srmAgent;
	}
	
	
	public StatusAgent getStatusAgent(){
		return staAgent;
	}
	
	
	public static  boolean  stopAgent(AID agent){
		srmAgent.senMessageToKill(agent);
		return true;
	}
	
	
	public static String getAgentInfo(final AppInformation appInfo){
		final StringBuilder infoAgent=new StringBuilder();
		
			
			
		HashMap<String, AID> robotRegister = InitPlataform.getRobotRegister();
		AID aid = robotRegister.get(appInfo.getAlias());
		if(aid!=null){
			StatusAgent agent = InitPlataform.getAgentManager().getStatusAgent();
			String status = agent.getStatus(robotRegister.get(appInfo.getAlias()));
			infoAgent.append(status);
		}else{
			JOptionPane.showMessageDialog(null, "No es posible cumunicarse con el robot");
		}
				
			
	
		
		return infoAgent.toString();
	}
}
