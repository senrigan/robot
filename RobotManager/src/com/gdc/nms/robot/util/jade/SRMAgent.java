package com.gdc.nms.robot.util.jade;

import java.util.HashMap;
import java.util.Set;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class SRMAgent  extends Agent{
	
	
	
	@Override
	protected void setup() {
		super.setup();
		addBehaviour(new Searched());
		addBehaviour(new MailBox());
	}


	private class Searched extends CyclicBehaviour{

		@Override
		public void action() {
			HashMap<String, AID> robotRegister = InitPlataform.getRobotRegister();
			Set<String> keySet = robotRegister.keySet();
			for (String string : keySet) {
				AID aid = robotRegister.get(string);
			
			}
		}
		
		
		private void sendMessage(AID reciver,String content){
			ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
			msg.addReceiver(reciver);
			msg.setContent(content);
			send(msg);
		}
		
	}
	
	
	private class MailBox extends CyclicBehaviour{

		private static final String ALIVE_RESPONSE="OK";
		@Override
		public void action() {
			jade.lang.acl.ACLMessage msg=blockingReceive();
			
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
			if(content.equalsIgnoreCase("live")){
				AID sender = msg.getSender();
				InitPlataform.registerRobot(sender.getLocalName(), sender);
				ACLMessage replyMessage = msg.createReply();
				replyMessage.setPerformative(ACLMessage.INFORM);
				replyMessage.setContent(ALIVE_RESPONSE);
				send(replyMessage);
			}
			
		}
		
	}
}
