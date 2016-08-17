package com.gdc.nms.robot.gui.tree;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import com.gdc.nms.robot.gui.RobotManagerGui;
import com.gdc.nms.robot.gui.RobotManagerGui.ButtonType;
import com.gdc.nms.robot.util.indexer.AppInformation;
import com.gdc.nms.robot.util.indexer.FlujoInformation;
import com.gdc.nms.robot.util.jade.InitPlataform;
import com.gdc.nms.robot.util.jade.SRMAgent;
import com.gdc.nms.robot.util.jade.SRMAgentManager;
import com.gdc.nms.robot.util.jade.StatusAgent;
import com.sun.org.apache.xerces.internal.util.Status;

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
		Object selectedComponent = tree.getLastSelectedPathComponent();
		Object[] path = tree.getSelectionPath().getPath();
		String jTreeVarSelectdPath=""; 
		
		boolean isRunning=false;
		for (int i=0; i<path.length; i++) {
			System.out.println("equals in ejecucion "+path[i].equals("En ejecucion"));
			if(path[i].toString().contains("ejecucion")){
				isRunning=true;
			}
		        jTreeVarSelectdPath += path[i];
		        System.out.println(jTreeVarSelectdPath);
		        if (i+1 <path.length ) {
		            jTreeVarSelectdPath += File.separator;
		        }
		    }
//		System.out.println(leadSelectionPath+" -"+leadSelectionPath.getParentPath());
		System.out.println("is running selected"+isRunning);
		if(!(selectedComponent.toString().equals("Detenidos") || selectedComponent.toString().equals("En ejecucion") || selectedComponent.toString().equals("Aplicacion"))){
			System.out.println("selected componetent "+selectedComponent);
			Element app = (Element) tree.getLastSelectedPathComponent();
			System.out.println("parent"+	app.getParent());
			AppInformation appInfo = app.getAppinfo();
			if(appInfo!=null){
				
				
				
				if(app.isStopAble()|| isRunning){
					
					gui.enableButton(ButtonType.STOP,true);
					gui.enableButton(ButtonType.START,false);
					
					
				}else{
					gui.enableButton(ButtonType.STOP,false);
					
					gui.enableButton(ButtonType.START,true);
					
				}
				gui.setLogVisible(true);
				gui.enableButton(ButtonType.DATA, true);

//			HashMap<String, AID> mapToKill = InitPlataform.getMapToKill();
//			HashMap<String, AID> robotRegister = InitPlataform.getRobotRegister();
//			if(mapToKill.containsKey(appinfo.getAlias())){
//				gui.enableButton(ButtonType.STOP, false);
//				gui.enableButton(ButtonType.START, false);
//			}
				String proccesText = proccesText(appInfo);
//			if(robotRegister.containsKey(appinfo.getAlias())){
//				gui.enableButton(ButtonType.STOP,true);
//				gui.enableButton(ButtonType.START,false);
//				proccesText+="\n"+getDataRobot(robotRegister.get(appinfo.getAlias()), "STA");
//
//			}
				
				gui.setInformation(proccesText);
		}
			
		}else{
			gui.enableButton(ButtonType.STOP, false);
			gui.enableButton(ButtonType.START, false);
			gui.enableButton(ButtonType.DATA, false);
			gui.setLogVisible(false);
			gui.setInformation("");
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
