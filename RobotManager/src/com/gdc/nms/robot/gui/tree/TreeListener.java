package com.gdc.nms.robot.gui.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import com.gdc.nms.robot.gui.RobotManagerGui;
import com.gdc.nms.robot.gui.RobotManagerGui.ButtonType;
import com.gdc.nms.robot.util.indexer.AppInformation;
import com.gdc.nms.robot.util.indexer.FlujoInformation;
import com.gdc.nms.robot.util.jade.InitPlataform;
import com.gdc.nms.robot.util.jade.SRMAgent;
import com.gdc.nms.robot.util.jade.SRMAgentManager;

import jade.core.AID;

public class TreeListener implements TreeSelectionListener{
	private RobotManagerGui gui;
	private JTree tree;
	public TreeListener(RobotManagerGui gui, JTree tree){
		this.gui=gui;
		this.tree=tree;
	}
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		Element app = (Element) tree.getLastSelectedPathComponent();
		AppInformation appinfo = app.getAppinfo();
		if(appinfo!=null){
			
			
			
			if(app.isStopAble()){
				
				gui.enableButton(ButtonType.STOP,true);
				gui.enableButton(ButtonType.START,false);

				
			}else{
				gui.enableButton(ButtonType.STOP,false);

				gui.enableButton(ButtonType.START,true);

			}
			
			HashMap<String, AID> mapToKill = InitPlataform.getMapToKill();
			HashMap<String, AID> robotRegister = InitPlataform.getRobotRegister();
			if(mapToKill.containsKey(appinfo.getAlias())){
				gui.enableButton(ButtonType.STOP, false);
				gui.enableButton(ButtonType.START, false);
			}
			String proccesText = proccesText(appinfo);
			if(robotRegister.containsKey(appinfo.getAlias())){
				gui.enableButton(ButtonType.STOP,true);
				gui.enableButton(ButtonType.START,false);
				proccesText+="\n"+getDataRobot(robotRegister.get(appinfo.getAlias()), "STA");

			}
			gui.setInformation(proccesText);
			
		}
		
	}
	
	
	
	private String getDataRobot(AID sender,String content){
		SRMAgent agent = InitPlataform.getAgentManager().getAgent();
		String senMessage = agent.senMessage(sender, content);
		
		return senMessage;
		
		
	}
	
	
	
	private String proccesText(AppInformation appInfo){
		appInfo.getAppName();
		StringBuilder builder=new StringBuilder();
		System.out.println("inicando stringbuilder"+builder.toString());
		builder.append("Nombre de Aplicacion :");
		builder.append(appInfo.getAppName()+"\n");
		builder.append("Alias :");
		builder.append(appInfo.getAlias()+"\n");
		builder.append("RobotID:");
		builder.append(appInfo.getIdRobot()+"\n");

		HashMap<String, String> propierties = appInfo.getPropierties();
		if(propierties!=null)
		if(!propierties.isEmpty()){
			
			builder.append("Propiedades del Robot  :\n");
			Set<String> keySet = propierties.keySet();
			for (String key : keySet) {
				String value = propierties.get(key);
				builder.append("\n \t"+key+" :"+value);
				
			}
		}
		System.out.println("continuando stringbuilder flujos"+builder.toString());

		builder.append("\nFlujos : \n");
		ArrayList<FlujoInformation> flujos = appInfo.getFlujos();
		if(flujos!=null)
		for (FlujoInformation flujoInformation : flujos) {
			
			builder.append("\n \t"+flujoInformation.getName()+" , Numero de Pasos : "+flujoInformation.getNumSteps());
		}
		System.out.println("terminando stringbuilder"+builder.toString());
		
		
		return builder.toString();
	}
		
	

}
