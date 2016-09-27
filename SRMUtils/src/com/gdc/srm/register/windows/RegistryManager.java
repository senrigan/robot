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

	}
	
	
	private void createImacrosRegistrys() throws IOException, InterruptedException{
		setImacrosMasterPasswordRegistry();
		setEncriptionModImacros();
	}
	
	
	private void setImacrosMasterPasswordRegistry() throws IOException, InterruptedException{
		String exit = CommandExecutor.addRegistryWindows(Constants.IMACROS_REGISTRY, "MasterPassword", "RzNuM3I0bEQ0dDRDMDBt",REGISTRY_TYPE.REG_SZ);
		System.out.println("exit"+exit);
	}
	
	private void setEncriptionModImacros() throws IOException, InterruptedException{
		String exit = CommandExecutor.addRegistryWindows(Constants.IMACROS_REGISTRY, "EncryptionPasswordMode", "Storedkey",REGISTRY_TYPE.REG_SZ);

	}
	
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
			ubicationRegist = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "installationPath","REG_SZ");
		} catch (Exception e) {
			e.printStackTrace();

		}
		return ubicationRegist;
	}
	
	
	public static Path  getInstallationPath(){
		return Paths.get(getInstallationPathRegistry());
	}
	
	private void registryForRunInStarUp(){
		String starupPath="HKCU\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run";
		Path sysproRobotManager = getInstallationPath().resolve("SRM.exe");
		try {
			CommandExecutor.addRegistryWindows(starupPath, "sysproRobotManager","\""+sysproRobotManager.toString()+"\"" );
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
