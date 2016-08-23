package com.gdc.nms.robot.gui.newInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.nms.robot.gui.tree.test.InterfaceManager;
import com.gdc.nms.robot.util.AppExaminator;
import com.gdc.nms.robot.util.indexer.AppInformation;
import com.gdc.nms.robot.util.indexer.FlujoInformation;

public class ButtonListener implements ActionListener{
	private static JButton lastButtonPressed;
	private static Color oldColor;
	private InterfaceManager srmGuiManager = RobotManager.getSRMGuiManager();
	@Override
	public void actionPerformed(ActionEvent paramActionEvent) {
		JButton buttonPress=(JButton) paramActionEvent.getSource();
//		System.out.println(paramActionEvent.getSource().getClass());
		System.out.println(buttonPress.getText()+paramActionEvent.getModifiers());
//		if(buttonPress.getModel().isPressed()){
			System.out.println("is pressed");
			System.out.println("lastbutton"+lastButtonPressed);
			if(lastButtonPressed==null){
				buttonPress.setEnabled(false);
				lastButtonPressed=buttonPress;
				oldColor=buttonPress.getBackground();
			}else{
				lastButtonPressed.setEnabled(true);
				lastButtonPressed.setBackground(oldColor);
				lastButtonPressed.setOpaque(false);
				buttonPress.setEnabled(false);
				lastButtonPressed=buttonPress;
				
				
			}
			lastButtonPressed.setOpaque(true);
			changColorBackgroundButtonPressed(lastButtonPressed);
			setInfoSelected();
//		}
	}
	
	private void setInfoSelected(){
		String appName = lastButtonPressed.getText();
		System.out.println("AppNameSelected"+appName);
		AppInformation appData = AppExaminator.getAppData(appName);
		if(appData!=null){
//			InterfaceManager srmGuiManager = RobotManager.getSRMGuiManager();
			srmGuiManager.setAplicationName(appData.getAppName());
			srmGuiManager.setAliasName(appData.getAlias());
			srmGuiManager.setIdRobot(""+appData.getIdRobot());
			srmGuiManager.setInfoText(parseInfo(appData));
			changeMainButtonStatus(appData);
		}

	}
	
	private String parseInfo(AppInformation appInfo){
		StringBuilder builder=new StringBuilder();
		builder.append("Flujos \n");
		builder.append("Numero de flujos: "+appInfo.getFlujos().size()+"\n");
		ArrayList<FlujoInformation> flujos = appInfo.getFlujos();
		for (int i = 0; i < flujos.size(); i++) {
			builder.append("Nombre de flujo "+(i+1)+": \t"+flujos.get(i).getSimpleName()+" \n");
			builder.append("Pasos : \t "+flujos.get(i).getNumSteps()+" \n");
		}
		return builder.toString();
		
	}
	
	
	private void changeMainButtonStatus(final AppInformation appInfo){
		if(appInfo.isRunningByAgent()){
			srmGuiManager.changeActionButton(true);
		}else {
			srmGuiManager.disableActionButton(true);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					if(appInfo.isServicesRunning()){
						srmGuiManager.changeActionButton(true);
					}else{
						srmGuiManager.changeActionButton(false);
					}
					srmGuiManager.disableActionButton(false);
				}
			});
		}
	}
	
	
	private void changColorBackgroundButtonPressed(JButton button){
		button.setBackground(new Color(200, 213,229));
	}
	
	
	public static JButton getLastSelectedButton(){
		return lastButtonPressed;
	}

}
