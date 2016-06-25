package com.gdc.nms.robot.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JOptionPane;

import com.gdc.nms.robot.gui.auxiliar.RegisrtyEditor;
import com.gdc.robothelper.webservice.ClientWebService;
import com.gdc.robothelper.webservice.SisproRobotManagerHelper;
import com.gdc.robothelper.webservice.SisproRobotManagerHelperService;
import com.gdc.robothelper.webservice.robot.CreatorRobotWebService;
import com.gdc.robothelper.webservice.robot.news.CreatorNewRobotWebService;
import com.gdc.robothelper.webservice.robot.news.Webservice;
import com.gdc.robothelper.webservice.robot.olds.CreatorOldRobotWebService;

public class RegisrtryEditorManager {
	public RegisrtryEditorManager(){
		
	}
	
	
	public void  showUbicationRegistry(){
		String ubication = RobotManager.getUbication();
		final RegisrtyEditor reg=new RegisrtyEditor();
		reg.setTitleWindows("Editar Ubicacion");
		reg.setTexBox(ubication);
		reg.showValidButton(false);
		reg.setRegistryLabel("Ubicacion");
		reg.setContinueAction(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RobotManager.createUbicationRegistruCreation(reg.getTexBox());
				if(RobotManager.getUbication().equals(reg.getTexBox())){
					JOptionPane.showMessageDialog(null, "El registro ha sido cambiado exitosamente","Regsitro Actualizado",JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showInternalMessageDialog(null, "No es posible modificar el registro", "Error Modificacion Registro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	
	
	public void showWebServiceConsult(){
		URL webServicesConsult = ClientWebService.getWebServicesConsult();
		final RegisrtyEditor reg=new RegisrtyEditor();
		reg.setTitleWindows("Editar Ubicacion");
		if(webServicesConsult!=null){
			reg.setTexBox(webServicesConsult.toString());
			
		}else{
			reg.setTexBox(SisproRobotManagerHelperService.getUrl().toString());
		}
		reg.setRegistryLabel("WebServices de Consulta");
		reg.setContinueAction(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String texBox = reg.getTexBox();
				
				if(ClientWebService.existeConexion(texBox)){
					RobotManager.createWebServicesConsultRegistry(texBox);
					URL wsUrl = ClientWebService.getWebServicesConsult();
					
					System.out.println(wsUrl);
					System.out.println("textbpox"+texBox);
					System.out.println(wsUrl.toString().equals(texBox));
					if(wsUrl!=null && wsUrl.toString().equals(texBox)){
						
						JOptionPane.showMessageDialog(null, "El registro ha sido cambiado exitosamente","Regsitro Actualizado",JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "No es posible modificar el registro", "Error Modificacion Registro", JOptionPane.ERROR_MESSAGE);
						
					}
				}else{
					JOptionPane.showMessageDialog(null, "No es posible conectar con el webservices", "Error WebServices", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		
		
		
	
		
		reg.setValidationAction(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String texBox = reg.getTexBox();
				
				if(ClientWebService.existeConexion(texBox)){
					JOptionPane.showMessageDialog(null, "El webservices el valido","Correcto",JOptionPane.INFORMATION_MESSAGE);

				}else{
					JOptionPane.showInternalMessageDialog(null, "No es posible conectar con el webservices", "Error WebServices", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		
	}
	
	public void showWebServicesCreator(){
		URL webServicesCreator = CreatorRobotWebService.getWebServicesCreator();
		final RegisrtyEditor reg=new RegisrtyEditor();
		reg.setTitleWindows("Editar Ubicacion");
		if(webServicesCreator!=null){
			reg.setTexBox(webServicesCreator.toString());
			
		}else{
			
			reg.setTexBox(com.gdc.robothelper.webservice.robot.Webservice.getUrl().toString());
		}
		final String webserviceUrl = webServicesCreator.toString();
		reg.setRegistryLabel("WebServices de Creacion");
		reg.setContinueAction(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String texBox = reg.getTexBox();
				boolean validConnection=false;
				if(webserviceUrl.contains("pp")){
					validConnection=CreatorNewRobotWebService.existeConexion(texBox);
				}else{
					validConnection=CreatorOldRobotWebService.existeConexion(texBox);
				}
				
				if(validConnection){
					RobotManager.createWebServicesConsultRegistry(texBox);
					URL wsUrl = CreatorRobotWebService.getWebServicesCreator();
					
					System.out.println(wsUrl);
					System.out.println("textbpox"+texBox);
					System.out.println(wsUrl.toString().equals(texBox));
					if(wsUrl!=null && wsUrl.toString().equals(texBox)){
						
						JOptionPane.showMessageDialog(null, "El registro ha sido cambiado exitosamente","Regsitro Actualizado",JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "No es posible modificar el registro", "Error Modificacion Registro", JOptionPane.ERROR_MESSAGE);
						
					}
				}else{
					JOptionPane.showMessageDialog(null, "No es posible conectar con el webservices", "Error WebServices", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		
		
		
	
		
		reg.setValidationAction(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String texBox = reg.getTexBox();
				
				if(ClientWebService.existeConexion(texBox)){
					JOptionPane.showMessageDialog(null, "El webservices el valido","Correcto",JOptionPane.INFORMATION_MESSAGE);

				}else{
					JOptionPane.showInternalMessageDialog(null, "No es posible conectar con el webservices", "Error WebServices", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
	}
	
	
	
	
	public static void main(String[] args) {
		RegisrtryEditorManager rd=new RegisrtryEditorManager();
//		rd.showUbicationRegistry();
//		rd.showWebServiceConsult();
		rd.showWebServicesCreator();
	}
}
