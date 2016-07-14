package com.gdc.nms.robot.util.jade;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.apache.log4j.Logger;

import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.nms.robot.gui.RobotManagerGui;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class SRMAgent extends Agent {
	private static final Logger LOGGER=Logger.getLogger(SRMAgent.class.toString());
	static {
		LOGGER.addAppender(RobotManager.logAppender);
	}

	@Override
	protected void setup() {
		Searched search=new Searched(this, SRMAgentManager.POOLING_INTERVAL);
		MailBox mail=new MailBox();
		SuicideBox suic=new SuicideBox();
//		addBehaviour(new Searched(this, SRMAgentManager.POOLING_INTERVAL));
//		addBehaviour(new MailBox());
//		addBehaviour(new SuicideBox());
		ParallelBehaviour p = new ParallelBehaviour(this,ParallelBehaviour.WHEN_ALL);
		SequentialBehaviour s1 = new SequentialBehaviour(this);
		SequentialBehaviour s2 = new SequentialBehaviour(this);
		s1.addSubBehaviour(suic);
		s2.addSubBehaviour(mail);
//		s1.addSubBehaviour(search);
		p.addSubBehaviour(s1);
		p.addSubBehaviour(s2);
		addBehaviour(p);

	}

	public String senMessage(AID reciver, String content) {
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		msg.addReceiver(reciver);
		msg.setContent(content);
		msg.setConversationId(content+""+System.currentTimeMillis());
		send(msg);
		System.out.println("//sending "+content+" to sender"+reciver);
		jade.lang.acl.ACLMessage msgResponse = blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM));
		if (msgResponse != null) {
			return msgResponse.getContent();
		}
		return null;
	}

	public void senMessageToKill(AID reciver) {
//		ACLMessage msg = new ACLMessage(SRMAgentManager.KILLCODE);
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		msg.addReceiver(reciver);
		msg.setContent(SRMAgentManager.OFF);
		msg.setConversationId("OFF-" + System.currentTimeMillis());
		send(msg);

	}

	public static String getAppName(String senderName) {

		String[] split = senderName.split("_");
		if (split.length > 1) {
			return split[1];
		}
		return null;
	}

	public static String parseAppName(String appName) {
		appName=appName.replaceAll("\\s+$", "");
		String parseName = appName;
		if (parseName.contains("_")) {
			parseName=parseName.replaceAll("_", " ");
		}
		if(parseName.contains("@")){
			parseName = parseName.substring(0, parseName.indexOf("@"));			
		}
		return parseName;
	}

	private class Searched extends TickerBehaviour {

		public Searched(Agent a, long period) {
			super(a, 1000L);
		}

		private boolean sendMessage(AID reciver, String content, String responseWaiting) {
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			msg.addReceiver(reciver);
			msg.setContent(content);
			try {
				msg.setContentObject(content+""+System.currentTimeMillis());
			} catch (IOException e) {
				e.printStackTrace();
			}
			send(msg);
			block(SRMAgentManager.WAITTIMEOUT);
			ACLMessage messageRecive = blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM));
			// System.out.println("se recibio el mensaje de "+messageRecive);
			if (messageRecive != null) {
				System.out.println("mens" + messageRecive.getContent());
				return true;

			}
			return false;
		}

		@Override
		protected void onTick() {
			try {

				System.out.println("poleando elementos");
				HashMap<String, AID> robotRegister = InitPlataform.getRobotRegister();
				Set<String> keySet = robotRegister.keySet();
				for (String string : keySet) {
					AID aid = robotRegister.get(string);
					if (!sendMessage(aid, SRMAgentManager.AYA, SRMAgentManager.IAA)) {
						System.out.println("no respondio el agente" + aid);
						InitPlataform.deRegisterRobot(string);
					}

				}
//				RobotManagerGui guiManager = RobotManager.getGuiManager();
//				if (guiManager != null) {
//
//					guiManager.UpdateTree(InitPlataform.getRobotRegister().keySet());
//				}
				// block(SRMAgentManager.POOLING_INTERVAL);
				System.out.println("termino la espera del poleo");
			} catch (Exception ex) {
				ex.printStackTrace();
				LOGGER.error("Excepcion", ex);
			}
		}

	}
//	private class MailBoxConfirm extends CyclicBehaviour{
//
//		@Override
//		public void action() {
//			ACLMessage msg=myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM));
//			if(msg!=null){
//				organizeMessageType(msg);
//			}
//			block(5000L);
//		}
//		
//		
//		private void organizeMessageType(ACLMessage msg){
//			String content = msg.getContent();
//			switch(content){
//			case "IAA":
//				break;
//			case "RUN":
//				break;
//			case "WAT":
//				break;
//			}
//		}
//		
//	}
	private class MailBox extends CyclicBehaviour {

		private static final String ALIVE_RESPONSE = "OK";

		@Override
		public void action() {
			try {
				ParallelBehaviour paralle=new ParallelBehaviour();
			
				ACLMessage msg=myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.SUBSCRIBE));
//				jade.lang.acl.ACLMessage msg = myAgent
//						.blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.SUBSCRIBE),5000L);
				if (msg != null) {
					messageResponse(msg);
					String appName = getAppName(msg.getSender().getName());
					appName = parseAppName(appName);
					RobotManagerGui.showMessage("el robot " + appName + " a iniciado correctamente");
				}
				block(5000L);
			} catch (Exception ex) {
				ex.printStackTrace();
				LOGGER.error("Excepcion", ex);

			}

		}

		public void messageResponse(ACLMessage msg) {
			try {

				String content = msg.getContent();
				if (content.equalsIgnoreCase(SRMAgentManager.IAA)) {
					AID sender = msg.getSender();
					// System.out.println("registrando
					// sender"+sender.getLocalName()+"sender"+sender);
					String appName = parseAppName(getAppName(sender.getName()));
					InitPlataform.registerRobot(appName, sender);
					ACLMessage replyMessage = msg.createReply();
					replyMessage.setPerformative(ACLMessage.CONFIRM);
					replyMessage.setContent(ALIVE_RESPONSE);
					replyMessage.setConversationId("" + ALIVE_RESPONSE + System.currentTimeMillis());
					send(replyMessage);
					RobotManagerGui guiManager = RobotManager.getGuiManager();
//					if (!InitPlataform.getRobotRegister().keySet().isEmpty())
//						guiManager.UpdateTree(InitPlataform.getRobotRegister().keySet());
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				LOGGER.error("Excepcion", ex);

			}

		}

	}

	private class SuicideBox extends CyclicBehaviour {

		@Override
		public void action() {
			try {
//				ACLMessage msg = blockingReceive(MessageTemplate.MatchPerformative(SRMAgentManager.KILLCODE), 1000);
				ACLMessage msg=receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
//				ACLMessage msg = blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));


				if (msg != null) {
					String appName = msg.getSender().getName();
					appName = getAppName(appName);
					appName = parseAppName(appName);
					InitPlataform.removeToKill(appName);
					RobotManagerGui.showMessage("el robot " + appName + "se ha detenido correctamente");
				}
				block(5000L);
			} catch (Exception ex) {
				ex.printStackTrace();
				LOGGER.error("Excepcion", ex);

			}

		}
	}
	
	
}