package com.gdc.nms.robot.util.jade;

import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class DFManager {
	public static String DFTYPE="robot";
	public static Long TIMERESCAN=6000L;
	
	
	public static void initScan(){
		Object []obj= new Object[1];
		AgentContainer container = InitPlataform.getContainer();
		try {
			AgentController createNewAgent = container.createNewAgent("robotScaner", "com.gdc.nms.robot.util.jade.DFConsult", obj);
			createNewAgent.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
	}

}
