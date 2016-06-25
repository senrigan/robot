package com.gdc.srm.fix.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CommandExecutor {
	
	public static String executeCommand(String command) throws IOException, InterruptedException {

		StringBuffer output = new StringBuffer();

		Process p;
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			int exitValue = p.exitValue();
            String line = "";			

			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
			
			
			line = "";			

			BufferedReader readerError= new BufferedReader(new InputStreamReader(p.getErrorStream()));
        	while ((line = readerError.readLine())!= null) {
				output.append(line + "\n");
			}
        	if(exitValue==1)
        		output.append("ERROR");
            p.destroy();


		return output.toString();

	}
	public static String deleteRegistryWindows(String registryPath) throws IOException, InterruptedException{
		String deleteReg="REG DELETE "+registryPath +" /f";
		return executeCommand(deleteReg);
	}
	
	public static  String addRegistryWindows(String registryPath, String nameValue , String value) throws IOException, InterruptedException
	{
		String addReg="REG ADD \""+registryPath+"\" /v \""+nameValue +"\" /t REG_SZ /d \""+value +"\" /f";
		return executeCommand(addReg);
	}
	
	
	public static String redRegistryWindows(String registryPath) throws IOException, InterruptedException{
		String queryReg="REG QUERY ";
		String outPut=executeCommand(queryReg+registryPath);
		return outPut;
	}
	
	
	
	public static String addRegistryWindows(String registryPath,String nameValue,String value,REGISTRY_TYPE regType) throws IOException, InterruptedException{
		
		String addReg="REG ADD \""+registryPath+"\" /v \""+nameValue +"\" /t "+regType.getName()+" /d \""+value +"\" /f";
		System.out.println(addReg);
		return executeCommand(addReg);
	}
	

    public static String readRegistrySpecificRegistry(String registryPath, String keyName, String regType) throws Exception {
        String redRegistryWindows = redRegistryWindows(registryPath+" /v "+keyName);
    	
        return redRegistryWindows.split(regType)[1].trim();
    }
    
    
    
    public enum REGISTRY_TYPE{
    	REG_BINARY("REG_BINARY"),
    	REG_DWORD("REG_DWORD"),
    	REG_EXPAND_SZ("REG_EXPAND_SZ"),
    	REG_MULTI_SZ("REG_MULTI_SZ"),
    	REG_SZ("REG_SZ"),
    	REG_RESOURCE_LIST("REG_RESOURCE_LIST"),
    	REG_RESOURCE_REQUIREMENTS_LIST("REG_RESOURCE_REQUIREMENTS_LIST"),
    	REG_FULL_RESOURCE_DESCRIPTOR("REG_FULL_RESOURCE_DESCRIPTOR"),
    	REG_NONE("REG_NONE"),
    	REG_LINK("REG_LINK"),
    	REG_QWORD("REG_QWORD");
    	private String name;
    	private REGISTRY_TYPE(String name) {
    		this.name=name;
		}
    	
    	public String getName(){
    		return this.name();
    	}
    }
}
