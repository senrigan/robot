package com.gdc.srm.register.windows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.gdc.srm.register.util.CommandExecutor;
import com.gdc.srm.register.util.CommandExecutor.REGISTRY_TYPE;
import com.gdc.srm.util.Constants;
import com.gdc.srm.util.ConstantsPath;


public class RegistryManager {
	
	public RegistryManager(){
	}
	
	
	public void createWindowsRegistrys() throws Exception{
		createRegistrySystem();
		registryUbication();
		registryForRunInStarUp();
		createImacrosRegistrys();
		checkAutoRestarRegistry();
		checkImacrosPasswordRegistry();
		createRobotNotRunRegistry();

	}
	
	
	private void createImacrosRegistrys() throws IOException, InterruptedException{
		setImacrosMasterPasswordRegistry();
		setEncriptionModImacros(true);
	}
	
	
	private void setImacrosMasterPasswordRegistry() throws IOException, InterruptedException{
		String exit = CommandExecutor.addRegistryWindows(Constants.IMACROS_REGISTRY, Constants.MASTERPASWORD_REGISTRYKEY, Constants.IM_MASTERPASWORD_ENCRIPT,REGISTRY_TYPE.REG_SZ);
		System.out.println("exit"+exit);
	}
	
	private void setEncriptionModImacros(boolean active) throws IOException, InterruptedException{
		if(active){
			CommandExecutor.addRegistryWindows(Constants.IMACROS_REGISTRY, Constants.IM_ENCRIPTATION_REGISTRYKEY, Constants.IM_ENCRIPT_TYPE,REGISTRY_TYPE.REG_SZ);
		}else{
			CommandExecutor.addRegistryWindows(Constants.IMACROS_REGISTRY, Constants.IM_ENCRIPTATION_REGISTRYKEY, Constants.IM_ENCRIPT_TYPE_NO,REGISTRY_TYPE.REG_SZ);
		}

	}
	
	private void createRobotNotRunRegistry() throws IOException, InterruptedException{
		CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, Constants.ROBOTNOTRUN_REGISTRY, " ");
	}
	
	
	private void addRobotToNoRunRegistry(String nedIdRobot){
		
	}
	
	
//	private boolean alredyExist(String idRobot){
//		
//	}
	
	private void createRegistrySystem() throws IOException, InterruptedException{
		String redRegistryWindows = CommandExecutor.redRegistryWindows(Constants.LOCALREGISTRY);
		if(!redRegistryWindows.contains("ERROR")){
		
		}else{					
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "tempPath", ConstantsPath.getTempPath().toString());
		}
		CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, 
				"installationPath",getInstallationPathRegistry() );
	}
	
	
	public void registryMonitorData(ArrayList<Long> robotIds){

		Path installationPath = getInstallationPath();

		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, 
					"monitorPath", installationPath.resolve("data").toString());
			String strRobotIds=convertArrayListinStringWithComa(robotIds);
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "robotmustRun",strRobotIds );
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "installationRobot",strRobotIds );
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "robotnotRun","" );

			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	

	
	private String convertArrayListinStringWithComa(ArrayList<?> list){
		String result="";
		for (Object object : list) {
			result+=object+",";
		}
		return result;
	}
	
	
	private void registryUbication(){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, Constants.UBICATION_ROBOT_REGISTYKEY, getUbicationRobotRegistry());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	
	public static void setUbicationRegistry(String newUbication){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, Constants.UBICATION_ROBOT_REGISTYKEY, newUbication);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		RegistryManager rg=new RegistryManager();
		try {
			rg.createImacrosRegistrys();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean getAutoRestarRegistry(){
		try {
			String redRegistryWindows = CommandExecutor.redRegistryWindows(Constants.LOCALREGISTRY+"\\"+Constants.AUTORESTAR_REGISTRYKEY);
			if(redRegistryWindows.equals("1")){
				return true;
			}
		}  catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;

	}
	private void checkAutoRestarRegistry(){
		try{
			String redRegistryWindows = CommandExecutor.redRegistryWindows(Constants.LOCALREGISTRY+"\\"+Constants.AUTORESTAR_REGISTRYKEY);
			if(redRegistryWindows==null){
				createAutoRestarRegistry();
			}
		}catch(Exception ex){
			createAutoRestarRegistry();
			ex.printStackTrace();
			
		}
	}
	
	private void createAutoRestarRegistry(){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, Constants.AUTORESTAR_REGISTRYKEY, "1",REGISTRY_TYPE.REG_BINARY );
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void createAutoRestarRegistry(boolean active){
		try{
			if(active){
				CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, Constants.AUTORESTAR_REGISTRYKEY, "1",REGISTRY_TYPE.REG_BINARY );
			}else{
				CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, Constants.AUTORESTAR_REGISTRYKEY, "0",REGISTRY_TYPE.REG_BINARY );
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static boolean getImacrosPasswordRegistry(){
		try {
			String redRegistryWindows = CommandExecutor.redRegistryWindows(Constants.LOCALREGISTRY+"\\"+Constants.IMACROSPASSWORD_REGISTRYKEY);
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
			String redRegistryWindows = CommandExecutor.redRegistryWindows(Constants.LOCALREGISTRY+"\\"+Constants.IMACROSPASSWORD_REGISTRYKEY);
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
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, Constants.IMACROSPASSWORD_REGISTRYKEY, "1",REGISTRY_TYPE.REG_BINARY );
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void  createImacrosPasswordRegistry(boolean active){
		try {
			if(active){
				CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, Constants.IMACROSPASSWORD_REGISTRYKEY, "1",REGISTRY_TYPE.REG_BINARY );
			}else{
				CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, Constants.IMACROSPASSWORD_REGISTRYKEY, "0",REGISTRY_TYPE.REG_BINARY );
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	public static String getUbicationRobotRegistry(){
		 try {
			return CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY,Constants.UBICATION_ROBOT_REGISTYKEY,REGISTRY_TYPE.REG_SZ.getName() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return null;
	}	
	
	public static String getInstallationPathRegistry(){
		String ubicationRegist=null;
		try {
			ubicationRegist = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY,Constants.INSTALLATION_PATH_KEY,REGISTRY_TYPE.REG_SZ.getName());
		} catch (Exception e) {
			e.printStackTrace();

		}
		return ubicationRegist;
	}
	
	
	public static String getWebSErvicesCreatorUrlRegistry(){
		String registry=null;
		try {
			 registry = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, Constants.WEBSERVICESCREATOR_REGISTRYKEY, 
					CommandExecutor.REGISTRY_TYPE.REG_SZ.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return registry;
	}
	
	
	public static String getWebServicesConsultRegistry(){
		String registry=null;
		try {
			registry = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, Constants.WEBSERVICESCONSULT_REGISTRYKEY, 
					CommandExecutor.REGISTRY_TYPE.REG_SZ.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return registry;
		

	}
	
	public static void setWebServicesConsultRegistry(String urlWebServices){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, Constants.WEBSERVICESCONSULT_REGISTRYKEY,
					urlWebServices);
		} catch (IOException e) {
			e.printStackTrace();

		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}
	
	
	
	public static void setWebServicesCreatorRegistry(String urlWebServices){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, Constants.WEBSERVICESCREATOR_REGISTRYKEY,
					urlWebServices);
		} catch (IOException e) {
			e.printStackTrace();

		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}

	
	
	
	public static Path  getInstallationPath(){
		return Paths.get(getInstallationPathRegistry());
	}
	
	private void registryForRunInStarUp(){
		Path sysproRobotManager = getInstallationPath().resolve(Constants.EXECUTABLE_NAME);
		try {
			CommandExecutor.addRegistryWindows(Constants.STARTUP_REGISTRY, Constants.APPLICATION_NAME_STARUP_REG,"\""+sysproRobotManager.toString()+"\"" );
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public static  void setImacrosPasswordRegistry(boolean value){
		try{
			
			if(value){
				CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, Constants.IMACROSPASSWORD_REGISTRYKEY, "1",REGISTRY_TYPE.REG_BINARY );
				
			}else{
				CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY,Constants.IMACROSPASSWORD_REGISTRYKEY, "1",REGISTRY_TYPE.REG_BINARY );
				
			}
		}catch(Exception ex){
			
		}
	}
	
}
