package com.gdc.srm.robot.gui.conf;

import com.gdc.srm.register.windows.RegistryManager;

public class ConfigurationController {
	
	public boolean saveUbicationRobot(String newUbication){
		RegistryManager.setUbicationRegistry(newUbication);
		String ubicationRobotRegistry = RegistryManager.getUbicationRobotRegistry();
		if(ubicationRobotRegistry.equals(newUbication)){
			return true;
		}
		return false;
	}
	
	
	public boolean saveWebServicesConsult(String newUrl){
		RegistryManager.setWebServicesConsultRegistry(newUrl);
		String webServicesConsultRegistry = RegistryManager.getWebServicesConsultRegistry();
		if(webServicesConsultRegistry.equals(newUrl)){
			return true;
		}
		return false;
	}
	
	
	public boolean saveWebServicesCreator(String newUrl){
		RegistryManager.setWebServicesCreatorRegistry(newUrl);
		String webSErvicesCreatorUrlRegistry = RegistryManager.getWebSErvicesCreatorUrlRegistry();
		if(webSErvicesCreatorUrlRegistry.equals(newUrl)){
			return true;
		}
		return false;
	}
	
	
	public boolean saveAutoStart(boolean active){
		RegistryManager.createAutoRestarRegistry(active);
		boolean autoRestarRegistry = RegistryManager.getAutoRestarRegistry();
		if(autoRestarRegistry==active){
			return true;
		}
		return false;
	}
	
	
	public boolean saveEncriptationImacros(boolean active){
		RegistryManager.createImacrosPasswordRegistry(active);
		boolean imacrosEncript = RegistryManager.getImacrosPasswordRegistry();
		if(imacrosEncript==active){
			return true;
		}
		return false;	
	}
	
	
}
