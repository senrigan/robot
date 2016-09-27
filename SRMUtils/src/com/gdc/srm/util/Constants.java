package com.gdc.srm.util;

public class Constants {

	// Properties

	public static final String UNKNOWN_PROPERTY_VALUE = "unknown";

	public static final String OS_NAME_PROPERTY = "os.name";

	public static final String USER_HOME_PROPERTY = "user.home";

	public static final String TEMP_DIR_PROPERTY = "java.io.tmpdir";
	
	


	public static final String INSTALLATION_REGISTRY_NAME = "registry.properties";

	public static final String JAVA_PATH_PROPERTY =    "java.home";

	


	public static final char SEPARATOR = ',';

	// Language

	public static final String NAME = "name";

	public static final String SETUP_TITLE = "setup.title";
	
	// Installation Registry Properties

	// generic paths
	public static final String INTALLER_REGISTRY_FOLDER= ".gdc";
	
	public static final String INTALLATION_PATH_PROPERTY="installation.path";
	
	public static final String INTALLATION_VERSION_PROPERTY="installlation.version";
	
	public static final String IMACROS_REGISTRY="HKCU\\SOFTWARE\\Ipswitch\\iMacros";
	
	public static final String LOCALREGISTRY="HKCU\\Software\\GDC\\Robot";
	
	public static final String AUTORESTAR_REGISTRYKEY="autoRestarRobot";
	public static final String IMACROSPASSWORD_REGISTRYKEY="imacrosPasswordEncript";
	public static final String UBICATION_ROBOT_REGISTYKEY="ubicationRobot";
	private static final String registryImacros="HKLM\\SOFTWARE\\Ipswitch\\iMacros";
	public static final String UnistallRegistry="HKLM\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall";
	public static final String APPLICATION_NAME = "SisproRobotInstaller";

	

	private Constants() {
	}
}
