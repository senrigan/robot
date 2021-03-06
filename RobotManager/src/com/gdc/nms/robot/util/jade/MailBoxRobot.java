package com.gdc.nms.robot.util.jade;

import java.util.Arrays;
import java.util.HashMap;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class MailBoxRobot extends Agent{
	
	public static void initReciver(){
		Object []obj= new Object[1];
		AgentContainer container = InitPlataform.getContainer();
		try {
			AgentController createNewAgent = container.createNewAgent("robotReciver", "com.gdc.nms.robot.util.jade.MailBoxRobot", obj);
			createNewAgent.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void setup() {
		super.setup();
		addBehaviour(new MailReader());
	}

	@Override
	protected void takeDown() {
		super.takeDown();
	}
	
	
	
	private class MailReader extends CyclicBehaviour{
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
