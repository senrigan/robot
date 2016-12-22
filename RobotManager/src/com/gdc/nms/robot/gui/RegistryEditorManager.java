package com.gdc.nms.robot.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.gdc.nms.robot.Main;
import com.gdc.nms.robot.gui.auxiliar.RegisrtyEditor;
import com.gdc.nms.robot.util.AppExaminator;
import com.gdc.nms.robot.util.Constants;
import com.gdc.nms.robot.util.Language;
import com.gdc.nms.robot.util.indexer.AppInformation;
import com.gdc.nms.robot.util.registry.CommandExecutor;
import com.gdc.nms.robot.util.registry.CommandExecutor.REGISTRY_TYPE;
import com.gdc.robothelper.webservice.ClientSRMHelperWebService;
import com.gdc.robothelper.webservice.SisproRobotManagerHelperService;
import com.gdc.robothelper.webservice.WebServicesManager;
import com.gdc.robothelper.webservice.robot.CreatorRobotWebService;
import com.gdc.robothelper.webservice.robot.news.CreatorNewRobotWebService;
import com.gdc.robothelper.webservice.robot.olds.CreatorOldRobotWebService;



public class RegistryEditorManager {
	private static final Logger LOGGER = Logger.getLogger(RegistryEditorManager.class.toString());
	public RegistryEditorManager(){
		
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
				createUbicationRegistruCreation(reg.getTexBox());
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
			LOGGER.log(Level.SEVERE,"excepcion ", e);

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
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		}

	}
	
	
	public static  void checkRegistryRobotNoRunning(){
		try {
			CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "robotnotRun","REG_SZ");
		} catch (Exception e) {
			createregistryRobotNotRunning();
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		}
	}
	
	
	
	
	public static void main(String[] args) {
		RegistryEditorManager rd=new RegistryEditorManager();
		rd.showUbicationRegistry();
		rd.showWebServiceConsult();
		rd.showWebServicesCreator();
	}
	
	
	
	private static void createregistryRobotNotRunning(){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "robotnotRun","");
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		}
	}
	
	

	public static void checkWebServicesRegistry(){
		try {
			CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "webservicesCreator", "REG_SZ");
		} catch (Exception e) {
			createWebServicesCreatorRegistry();
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		}
		
		try {
			CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "webservicesConsult", "REG_SZ");
		} catch (Exception e) {
			e.printStackTrace();
			createWebServicesConsultRegistry();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		}
	}
	
	
	public static void createWebServicesCreatorRegistry(String wsUrl){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "webservicesCreator", wsUrl);
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		}
	}
	private static void createWebServicesCreatorRegistry(){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "webservicesCreator",WebServicesManager.getWebServicesCreatorUrl().toString());
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		}
	}
	
	
	public static String getWebServicesCreatorRegistry(){
		String urlRegistry=null;
		try {
			urlRegistry=CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "webservicesCreator", "REG_SZ");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		}
		return urlRegistry;
		
	}
	
	
	public static void createWebServicesConsultRegistry(String wsUrl){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "webservicesConsult",wsUrl);
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		}
	}
	
	private static void createWebServicesConsultRegistry(){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "webservicesConsult",
					SisproRobotManagerHelperService.getUrl().toString());
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		}
	}
	
	
	public static String getWebServicesConsultRegistry(){
		String wsUrl=null;
		try {
			wsUrl=CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "webservicesConsult", "REG_SZ");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		}
		return wsUrl;
		
	}
	public static void createUbicationRegistruCreation(String ubication){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "ubicationRobot", ubication, REGISTRY_TYPE.REG_SZ);
			RobotManager.setUbication(ubication);
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		}

	}
	private static void createUbicationPathRegistry(){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "installationPath", getCurrentPath().toString(), REGISTRY_TYPE.REG_SZ);
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		}

	}
	
	

	public static Path getCurrentPath() {
        try {
            Path currentPath = Paths.get(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());

            if (Files.isRegularFile(currentPath, new java.nio.file.LinkOption[0])) {
                return currentPath.getParent();
            }
            return currentPath;
        } catch (URISyntaxException e) {
			LOGGER.log(Level.SEVERE,"excepcion ", e);

        }
        return null;
    }

	
	public static String getInstallationPathRegistry(){
		String ubicationRegist=null;
		try {
			ubicationRegist = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "installationPath","REG_SZ");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		}
		return ubicationRegist;
	}
	

	/**
	 * this for ubucation is for robotCreation
	 */
	public static void checkUbicationCreationRegistry(){
		try{
			String ubicationRegist = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "ubicationRobot","REG_SZ");
			RobotManager.setUbication(ubicationRegist);
		}catch(Exception ex){
			createUbicationRegistruCreation();
			LOGGER.log(Level.SEVERE,"excepcion ", ex);

		}
	}
	
	public static void checkUbicationRegistry(){
		try {
			String ubicationRegist = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "installationPath","REG_SZ");
		} catch (Exception e) {
			createUbicationPathRegistry();
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		}
	}
	
	
	private static void createUbicationRegistruCreation(){
		try {
			String ubication="generic";
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "ubicationRobot", ubication, REGISTRY_TYPE.REG_SZ);
			RobotManager.setUbication(ubication);
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE,"excepcion ", e);

		}

	}
	
	public static boolean getAutoRestarRegistry(){
		try {
			String redRegistryWindows = CommandExecutor.redRegistryWindows(Constants.LOCALREGISTRY+"\\"+Language.get("registry.robot.restar"));
			if(redRegistryWindows.equals("1")){
				return true;
			}
		}  catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;

	}
	
	public static boolean getImacrosPasswordRegistry(){
		try {
			String redRegistryWindows = CommandExecutor.redRegistryWindows(Constants.LOCALREGISTRY+"\\"+Language.get("registry.robot.imacrospassword") );
			if(redRegistryWindows.equals("1")){
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void checkImacrosPasswordRegistry(){
		try {
			String redRegistryWindows = CommandExecutor.redRegistryWindows(Constants.LOCALREGISTRY+"\\"+Language.get("registry.robot.imacrospassword") );
			if(redRegistryWindows==null){
				createImacrosPasswordRegistry();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			createImacrosPasswordRegistry();
		}
	}
	
	private void createImacrosPasswordRegistry(){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, Language.get("registry.robot.imacrospassword"), "1",REGISTRY_TYPE.REG_BINARY );
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static  void setImacrosPasswordRegistry(boolean value){
		try{
			
			if(value){
				CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, Language.get("registry.robot.imacrospassword"), "1",REGISTRY_TYPE.REG_BINARY );
				
			}else{
				CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, Language.get("registry.robot.imacrospassword"), "1",REGISTRY_TYPE.REG_BINARY );
				
			}
		}catch(Exception ex){
			
		}
	}

	
}
