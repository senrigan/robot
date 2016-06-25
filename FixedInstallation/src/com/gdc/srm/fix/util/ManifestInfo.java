package com.gdc.srm.fix.util;

public class ManifestInfo {
	private String  manifestVersion;
	private String createBy;
	private String MainClass;
	private String ClassPath="";
	
	public String getManifestVersion() {
		return manifestVersion;
	}
	public void setManifestVersion(String manifestVersion) {
		this.manifestVersion = manifestVersion;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getMainClass() {
		return MainClass;
	}
	/*
	 *  first class with jar will running this shuld contain the full name package with name class  
	 */
	public void setMainClass(String mainClass) {
		MainClass = mainClass;
	}
	public String getClassPath() {
		return ClassPath;
	}
	/*
	 * classpath with path for found libraries for run without error the jar
	 */
	public void setClassPath(String classPath) {
		ClassPath = classPath;
	}
	
	public void addToClassPath(String library){
		this.ClassPath+=" "+library+" ";
	}

}
