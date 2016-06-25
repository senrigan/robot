package com.gdc.srm.fix.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import com.gdc.srm.fix.util.CommandExecutor.REGISTRY_TYPE;

public class RegistryManager {
	
	public RegistryManager(){
	}
	
	
	public void createWindowsRegistrys() throws Exception{
		createRegistrySystem();
		registryUbication();
		registryForRunInStarUp();
		createImacrosRegistrys();

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
		String redRegistryWindows = CommandExecutor.redRegistryWindows(Installation.LOCALREGISTRY);
		if(!redRegistryWindows.contains("ERROR")){
		
		}else{					
			CommandExecutor.addRegistryWindows(Installation.LOCALREGISTRY, "tempPath", Installation.getTempFolderString());
		}
		CommandExecutor.addRegistryWindows(Installation.LOCALREGISTRY, 
				"installationPath",Installation.getInstallaPath().toString() );
		
	}
	
	
	public void registryMonitorData(ArrayList<Long> robotIds){

		Path installationPath = Installation.getInstallaPath();

		try {
			CommandExecutor.addRegistryWindows(Installation.LOCALREGISTRY, 
					"monitorPath", installationPath.resolve("data").toString());
			String strRobotIds=convertArrayListinStringWithComa(robotIds);
			CommandExecutor.addRegistryWindows(Installation.LOCALREGISTRY, "robotmustRun",strRobotIds );
			CommandExecutor.addRegistryWindows(Installation.LOCALREGISTRY, "installationRobot",strRobotIds );
			CommandExecutor.addRegistryWindows(Installation.LOCALREGISTRY, "robotnotRun","" );

			
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
			CommandExecutor.addRegistryWindows(Installation.LOCALREGISTRY, "ubicationRobot", Installation.getUbicationRobot());
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
	
	
	private void registryForRunInStarUp(){
		String starupPath="HKCU\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run";
		Path sysproRobotManager = Installation.getInstallaPath().resolve("SRM.exe");
		try {
			CommandExecutor.addRegistryWindows(starupPath, "sysproRobotManager","\""+sysproRobotManager.toString()+"\"" );
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
