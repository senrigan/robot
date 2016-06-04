package com.gdc.nms.robot.util.jade;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.nms.robot.gui.RobotManagerGui;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class SRMAgent extends Agent {

	@Override
	protected void setup() {
		addBehaviour(new Searched(this, SRMAgentManager.POOLING_INTERVAL));
		addBehaviour(new MailBox());
		addBehaviour(new SuicideBox());

	}

	public String senMessage(AID reciver, String content) {
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		msg.addReceiver(reciver);
		msg.setContent(content);
		msg.setConversationId(content+""+System.currentTimeMillis());
		send(msg);
		System.out.println("//sending "+content+" to sender"+reciver);
		jade.lang.acl.ACLMessage msgResponse = blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM),
				SRMAgentManager.WAITTIMEOUT);
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

	public String getAppName(String senderName) {

		String[] split = senderName.split("_");
		if (split.length > 1) {
			return split[1];
		}
		return null;
	}

	public String parseAppName(String appName) {
		String parseName = appName;
		if (parseName.contains("_")) {
			parseName.replaceAll("_", " ");
		}
		parseName = parseName.substring(0, parseName.indexOf("@"));
		return parseName;
	}

	private class Searched extends TickerBehaviour {

		public Searched(Agent a, long period) {
			super(a, 1000);
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
			ACLMessage messageRecive = blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM),
					SRMAgentManager.WAITTIMEOUT);
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

	private class MailBox extends CyclicBehaviour {

		private static final String ALIVE_RESPONSE = "OK";

		@Override
		public void action() {
			try {
				jade.lang.acl.ACLMessage msg = myAgent
						.blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.SUBSCRIBE), 1000);
				if (msg != null) {
					messageResponse(msg);
					String appName = getAppName(msg.getSender().getName());
					appName = parseAppName(appName);
					RobotManagerGui.showMessage("el robot " + appName + " a iniciado correctamente");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
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
					if (!InitPlataform.getRobotRegister().keySet().isEmpty())
						guiManager.UpdateTree(InitPlataform.getRobotRegister().keySet());
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

	}

	private class SuicideBox extends CyclicBehaviour {

		@Override
		public void action() {
			try {
//				ACLMessage msg = blockingReceive(MessageTemplate.MatchPerformative(SRMAgentManager.KILLCODE), 1000);
				ACLMessage msg = blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.INFORM), 1000);


				if (msg != null) {
					String appName = msg.getSender().getName();
					appName = getAppName(appName);
					appName = parseAppName(appName);
					InitPlataform.removeToKill(appName);
					RobotManagerGui.showMessage("el robot " + appName + "se ha detenido correctamente");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}
}