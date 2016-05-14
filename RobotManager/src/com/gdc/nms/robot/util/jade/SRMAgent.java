package com.gdc.nms.robot.util.jade;

import java.util.HashMap;
import java.util.Set;

import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.nms.robot.gui.RobotManagerGui;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class SRMAgent  extends Agent{
	
	
	
	@Override
	protected void setup() {
		super.setup();
		addBehaviour(new Searched());
		addBehaviour(new MailBox());
	}
	
	
	
	public String senMessage(AID reciver,String content){
		ACLMessage msg=new ACLMessage(ACLMessage.REQUEST);
		msg.addReceiver(reciver);
		msg.setContent(content);
		send(msg);
		jade.lang.acl.ACLMessage msgResponse=blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM),SRMAgentManager.WAITTIMEOUT);
		if(msgResponse!=null){
			return msgResponse.getContent();
		}
		return null;
	}


	private class Searched extends CyclicBehaviour{

		@Override
		public void action() {
			HashMap<String, AID> robotRegister = InitPlataform.getRobotRegister();
			Set<String> keySet = robotRegister.keySet();
			for (String string : keySet) {
				AID aid = robotRegister.get(string);
				if(!sendMessage(aid, SRMAgentManager.AYA,SRMAgentManager.IAA)){
					System.out.println("no respondio el agente"+aid);
					InitPlataform.deRegisterRobot(string);
				}
				
			}
			RobotManagerGui guiManager = RobotManager.getGuiManager();
			if(guiManager!=null){
				
				guiManager.UpdateTree(InitPlataform.getRobotRegister().keySet());
			}
			try {
				Thread.sleep(SRMAgentManager.POOLING_INTERVAL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		private boolean sendMessage(AID reciver,String content,String responseWaiting){
			ACLMessage msg=new ACLMessage(ACLMessage.REQUEST);
			msg.addReceiver(reciver);
			msg.setContent(content);
			send(msg);
			ACLMessage messageRecive = blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM),SRMAgentManager.WAITTIMEOUT);
//			System.out.println("se recibio el mensaje de "+messageRecive);
			if(messageRecive!=null ){
				System.out.println("mens"+messageRecive.getContent());
				return true;
				
			}
			return false;
		}
		
	}
	
	
	private class MailBox extends CyclicBehaviour{

		private static final String ALIVE_RESPONSE="OK";
		@Override
		public void action() {
			jade.lang.acl.ACLMessage msg=blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.SUBSCRIBE));
			
			if(msg!=null){				
				messageResponse(msg);
			}
//			else{
//				System.out.println("Waiting Message from Robots");
//				block();
//			}
		}
		
		
		
		public void messageResponse(ACLMessage msg){
			String content = msg.getContent();
			if(content.equalsIgnoreCase(SRMAgentManager.IAA)){
				AID sender = msg.getSender();
//				System.out.println("registrando sender"+sender.getLocalName()+"sender"+sender);
				InitPlataform.registerRobot(sender.getLocalName(), sender);
				ACLMessage replyMessage = msg.createReply();
				replyMessage.setPerformative(ACLMessage.CONFIRM);
				replyMessage.setContent(ALIVE_RESPONSE);
				send(replyMessage);
			}
			
		}
		
	}
}
