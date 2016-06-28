package com.gdc.nms.robot.gui.menuAction.controller;

import java.net.URL;

import javax.swing.JOptionPane;

import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.robothelper.webservice.ClientWebService;
import com.gdc.robothelper.webservice.robot.CreatorRobotWebService;
import com.gdc.robothelper.webservice.robot.news.CreatorNewRobotWebService;
import com.gdc.robothelper.webservice.robot.olds.CreatorOldRobotWebService;

public class RegistryEditorController {
	
	
	public boolean webServicesCreationValidation(String webservicesUrl){
		return false;
	}

	public void fieldValidation(String wsCreaction,String wsConsult,String ubication) {
		if(!isEmpatyString(ubication) && !isEmpatyString(wsCreaction) 
				&& !isEmpatyString(wsConsult)){
			
		}else{
			JOptionPane.showMessageDialog(null,"Es necesario llenar todos los campos","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public void modifyWsCreation(String url){
		
		if(validWSCreationConection(url)){
			RobotManager.createWebServicesConsultRegistry(url);
			URL wsUrl = CreatorRobotWebService.getWebServicesCreator();
			
			if(wsUrl!=null && wsUrl.toString().equals(url)){
				System.out.println("Webservices creacion de modificado correctamente");
//				JOptionPane.showMessageDialog(null, "El registro ha sido cambiado exitosamente","Regsitro Actualizado",JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, "No es posible modificar el registro WebServices Creacion", "Error Modificacion Registro", JOptionPane.ERROR_MESSAGE);
				
			}
		}else{
			JOptionPane.showInternalMessageDialog(null, "No es posible conectar con el webservices", "Error WebServices", JOptionPane.ERROR_MESSAGE);

		}
	}
	
	
	public void modifyWSConsult(String url){
		
	}
	
	
	public boolean isNewWebservices(String url){
		if(!url.isEmpty() && url.contains("pp")){
			return true;
		}
		return false;
	}
	
	
	public boolean validWSCreationConection(String url){
		if(isNewWebservices(url)){
			return CreatorNewRobotWebService.existeConexion(url);
		}else{
			return CreatorOldRobotWebService.existeConexion(url);
		}
	}
	
	
	public boolean validWSConsult(String url){
		
		return ClientWebService.existeConexion(url);
	}
	
	
	
	
	
	private boolean isEmpatyString(String text){
		text=text.trim();
		if(text!=null && !text.isEmpty())
			return false;
		return true;
	}
}