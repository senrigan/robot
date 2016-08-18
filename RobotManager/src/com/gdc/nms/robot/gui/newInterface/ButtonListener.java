package com.gdc.nms.robot.gui.newInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.text.ChangedCharSetException;

public class ButtonListener implements ActionListener{
	private static JButton lastButtonPressed;
	private static Color oldColor;
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

//		}
	}
	
	
	private void changColorBackgroundButtonPressed(JButton button){
		button.setBackground(new Color(200, 213,229));
	}

}
