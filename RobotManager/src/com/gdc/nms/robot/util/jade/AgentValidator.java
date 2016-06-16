package com.gdc.nms.robot.util.jade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.nms.robot.gui.RobotManagerGui;
import com.gdc.nms.robot.util.AppExaminator;
import com.gdc.nms.robot.util.indexer.AppInformation;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AgentValidator extends Agent{

	@Override
	protected void setup() {
		super.setup();
		addBehaviour(new Searched(this,SRMAgentManager.POOLING_INTERVAL ));
	}
	
	
	
	private class Searched extends TickerBehaviour {

		public Searched(Agent a, long period) {
			super(a, period);
		}

		private boolean sendMessage(AID reciver, String content, String responseWaiting) {
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			msg.addReceiver(reciver);
			msg.setContent(content);
			msg.setConversationId(content+""+System.currentTimeMillis());
			
			send(msg);
			System.out.println("bloqueando hasta recivir");
			ACLMessage messageRecive = blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM),
					SRMAgentManager.WAITTIMEOUT);
			System.out.println("termino espera de recivir mensaje");
			System.out.println("message recive"+messageRecive);
			// System.out.println("se recibio el mensaje de "+messageRecive);
			if (messageRecive != null) {
				if(messageRecive.getContent().equalsIgnoreCase(responseWaiting)){
					
					System.out.println("mens" + messageRecive.getContent());
					return true;
				}

			}
			return false;
		}

		@Override
		protected void onTick() {
			try {

				System.out.println("poleando elementos agentValidator");
				HashMap<String, AID> robotRegister = InitPlataform.getRobotRegister();
				Set<String> keySet = robotRegister.keySet();
				HashMap<String, AppInformation> installedAppsMap = AppExaminator.getInstalledAppsMap();
				for (String string : keySet) {
					AID aid = robotRegister.get(string);
					if (!sendMessage(aid, SRMAgentManager.AYA, SRMAgentManager.IAA)) {
						System.out.println("no respondio el agente" + aid);
						String parseAppName = SRMAgent.parseAppName(string);
						if(installedAppsMap.containsKey(parseAppName)){
							if(AppExaminator.isRunningByLockFile(installedAppsMap.get(parseAppName))){
								continue;
							}
							
						}else{
							InitPlataform.deRegisterRobot(string);
							
						}
					}
					
				}
				RobotManagerGui guiManager = RobotManager.getGuiManager();
				if (guiManager != null) {

					guiManager.UpdateTree(InitPlataform.getRobotRegister().keySet());
				}
				// block(SRMAgentManager.POOLING_INTERVAL);
				System.out.println("termino la espera del poleo");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}
}