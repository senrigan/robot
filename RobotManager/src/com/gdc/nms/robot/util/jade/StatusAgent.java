package com.gdc.nms.robot.util.jade;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class StatusAgent extends Agent{

	@Override
	protected void setup() {
		super.setup();
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
	
	
	public String getStatus(AID reciver){
		String content=senMessage(reciver, "STA");
		return content;
	}

}
