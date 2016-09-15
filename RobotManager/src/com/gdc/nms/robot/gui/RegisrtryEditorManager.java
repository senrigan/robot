package com.gdc.nms.robot.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.gdc.nms.robot.gui.auxiliar.RegisrtyEditor;
import com.gdc.nms.robot.util.AppExaminator;
import com.gdc.nms.robot.util.Constants;
import com.gdc.nms.robot.util.indexer.AppInformation;
import com.gdc.nms.robot.util.registry.CommandExecutor;
import com.gdc.robothelper.webservice.ClientSRMHelperWebService;
import com.gdc.robothelper.webservice.SisproRobotManagerHelper;
import com.gdc.robothelper.webservice.SisproRobotManagerHelperService;
import com.gdc.robothelper.webservice.robot.CreatorRobotWebService;
import com.gdc.robothelper.webservice.robot.news.CreatorNewRobotWebService;
import com.gdc.robothelper.webservice.robot.news.Webservice;
import com.gdc.robothelper.webservice.robot.olds.CreatorOldRobotWebService;

import org.apache.log4j.Logger;


public class RegisrtryEditorManager {
	private static final Logger LOGGER = Logger.getLogger(RegisrtryEditorManager.class);
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
		URL webServicesConsult = ClientSRMHelperWebService.getWebServicesConsult();
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
				
				if(ClientSRMHelperWebService.existeConexion(texBox)){
					createWebServicesConsultRegistry(texBox);
					URL wsUrl = ClientSRMHelperWebService.getWebServicesConsult();
					
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
				
				if(ClientSRMHelperWebService.existeConexion(texBox)){
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
					createWebServicesConsultRegistry(texBox);
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
				
				if(CreatorNewRobotWebService.existeConexion(texBox)){
					JOptionPane.showMessageDialog(null, "El webservices el valido","Correcto",JOptionPane.INFORMATION_MESSAGE);

				}else{
					JOptionPane.showMessageDialog(null, "No es posible conectar con el webservices", "Error WebServices", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
	}
	
	
	public static  void checkRegistryRobotMustRun(){
		String redRegistryWindows="";
		
		try {
			redRegistryWindows=CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "robotmustRun", "REG_SZ");
//			if(redRegistryWindows.equals("")){
//				createRegistryRobotMustRun();
//			}
		} catch (Exception e) {
			createRegistryRobotMustRun();
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
	}
	
	private static void createRegistryRobotMustRun(){
		ArrayList<AppInformation> installedApps = AppExaminator.getInstalledApps();
		String idRobots="";
		for (AppInformation appInformation : installedApps) {
			idRobots+= appInformation.getIdRobot()+",";
		}
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "robotmustRun",idRobots );
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}

	}
	
	
	private static  void checkRegistryRobotNoRunning(){
		try {
			CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "robotnotRun","REG_SZ");
		} catch (Exception e) {
			createregistryRobotNotRunning();
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
	}
	
	
	
	
	public static void main(String[] args) {
		RegisrtryEditorManager rd=new RegisrtryEditorManager();
		rd.showUbicationRegistry();
		rd.showWebServiceConsult();
		rd.showWebServicesCreator();
	}
	
	
	
	private static void createregistryRobotNotRunning(){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "robotnotRun","");
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
	}
	
	

	private void checkWebServicesRegistry(){
		try {
			CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "webservicesCreator", "REG_SZ");
		} catch (Exception e) {
			createWebServicesCreatorRegistry();
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
		
		try {
			CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "webservicesConsult", "REG_SZ");
		} catch (Exception e) {
			e.printStackTrace();
			createWebServicesConsultRegistry();
			LOGGER.error("excepcion ", e);

		}
	}
	
	
	public static void createWebServicesCreatorRegistry(String wsUrl){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "webservicesCreator", wsUrl);
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
	}
	private void createWebServicesCreatorRegistry(){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "webservicesCreator", Webservice.getUrl().toString());
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
	}
	
	
	public static String getWebServicesCreatorRegistry(){
		String urlRegistry=null;
		try {
			urlRegistry=CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "webservicesCreator", "REG_SZ");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
		return urlRegistry;
		
	}
	
	
	public static void createWebServicesConsultRegistry(String wsUrl){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "webservicesConsult",wsUrl);
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
	}
	
	private static void createWebServicesConsultRegistry(){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "webservicesConsult",
					SisproRobotManagerHelperService.getUrl().toString());
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
	}
	
	
	public static String getWebServicesConsultRegistry(){
		String wsUrl=null;
		try {
			wsUrl=CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "webservicesConsult", "REG_SZ");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
		return wsUrl;
		
	}
	
	
	
}
