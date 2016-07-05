package com.gdc.nms.robot.util.jade;

import java.util.Iterator;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.gdc.nms.robot.gui.RobotManager;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class DFConsult  extends Agent  {
	public static Vector<AID> services;
	private static final Logger LOGGER=Logger.getLogger(DFConsult.class.toString());
	static{
		LOGGER.addAppender(RobotManager.logAppender);
	}
	protected void setup() {
	  	// Search for services of type "weather-forecast"
	
	  	
	  	
	  	this.addBehaviour(new Behaviour() {
			
			@Override
			public boolean done() {
				return false;
			}
			
			@Override
			public void action() {
				while(true){
					
					try {
						getDFSubscriptos();
						Thread.sleep(DFManager.TIMERESCAN);
					} catch (InterruptedException e) {
						e.printStackTrace();
						LOGGER.error("Excepcion ", e);
					}
				}
			}
		});
	  } 
	
	
	
	private void getDFSubscriptos(){
//	 	System.out.println("Agent "+getLocalName()+" searching for services of type \"robots\"");
	  	try {
	  		// Build the description used as template for the search
	  		DFAgentDescription template = new DFAgentDescription();
	  		ServiceDescription templateSd = new ServiceDescription();
	  		templateSd.setType(DFManager.DFTYPE);
	  		template.addServices(templateSd);
	  		
	  		SearchConstraints sc = new SearchConstraints();
	  		// We want to receive 10 results at most
	  		sc.setMaxResults(Long.MAX_VALUE);
	  		
	  		DFAgentDescription[] results = DFService.search(this, template, sc);
	  		
	  		
	  		if (results.length > 0) {
//	  			System.out.println("Agent "+getLocalName()+" found the following robot services:");
	  			for (int i = 0; i < results.length; ++i) {
	  				DFAgentDescription dfd = results[i];
	  				AID provider = dfd.getName();
	  				// The same agent may provide several services; we are only interested
	  				// in the weather-forcast one
	  				Iterator<?> it = dfd.getAllServices();
	  				while (it.hasNext()) {
	  					ServiceDescription sd = (ServiceDescription) it.next();
	  					
//	  					System.out.println("all adress agent"+provider.getAllAddresses());
	  					if (sd.getType().equals(DFManager.DFTYPE)) {
	  						services.add(provider);
	  						System.out.println("- Service \""+sd.getName()+"\" provided by agent "+provider.getName());
	  					}
	  				}
	  			}
	  		}	
	  		else {
//	  			System.out.println("Agent "+getLocalName()+" did not find any robot service");
	  		}
	  	}
	  	catch (FIPAException fe) {
	  		fe.printStackTrace();
			LOGGER.error("Excepcion ", fe);

	  	}
	}
}
