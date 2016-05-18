package com.gdc.nms.robot.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.synth.SynthSeparatorUI;

import com.gdc.nms.robot.Main;
import com.gdc.nms.robot.util.AppExaminator;
import com.gdc.nms.robot.util.Constants;
import com.gdc.nms.robot.util.Environment;
import com.gdc.nms.robot.util.RobotInformation;
import com.gdc.nms.robot.util.VirtualMachineExaminator;
import com.gdc.nms.robot.util.indexer.AppInformation;
import com.gdc.nms.robot.util.jade.InitPlataform;
import com.gdc.nms.robot.util.registry.CommandExecutor;
import com.gdc.nms.robot.util.registry.CommandExecutor.REGISTRY_TYPE;
import com.gdc.robothelper.webservice.SisproRobotManagerHelper;
import com.gdc.robothelper.webservice.SisproRobotManagerHelperService;
import com.gdc.robothelper.webservice.robot.Webservice;

public class RobotManager extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private static Path installationPath;
	private static String ubication;
	private static final Logger LOGGER=Logger.getLogger(RobotManager.class.toString());
	private static boolean valueStart=false;
	private static boolean valueStop=false;
	private static boolean executeScan=true;
	private static RobotManagerGui robotManagerGui;
	public RobotManager() {
		Path regInstallationPath = AppExaminator.getInstallationPath();
		setInstallationPath(regInstallationPath);
		checkWindowsRegistry();
//		  DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
//	        //create the child nodes
//	        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("Vegetables");
//	        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Fruits");
//	 
//	        //add the child nodes to the root node
//	        root.add(vegetableNode);
//	        root.add(fruitNode);
//	         
//	        //create the tree by passing in the root node
//	        tree = new JTree(root);
//	        add(tree);
//	         
//	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	        this.setTitle("JTree Example");       
//	        this.pack();
//	        this.setVisible(true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
//		Thread hilo=new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				initRobot();
//				
//			}
//		});
		StartAgentPlatform();
//		hilo.start();
		robotManagerGui=new RobotManagerGui();
	}
	
	
	
	public static RobotManagerGui getGuiManager(){
		return robotManagerGui;
	}
	private void StartAgentPlatform(){
		if(!InitPlataform.getInstance().runAgentContainer()){
			JOptionPane.showMessageDialog(null, "No se pudo iniciar correctamente el escaneo ", "Agent Container", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void checkWindowsRegistry(){
		LOGGER.log(Level.INFO, "reading and creating windows registry necessary for sysprorobotmanager");
		checkRegistryRobotMustRun();
		checkRegistryRobotNoRunning();
		checkUbicationRegistry();
		checkWebServicesRegistry();
		
		
	}
	
	private void checkRegistryRobotMustRun(){
		String redRegistryWindows="";
		
		try {
			redRegistryWindows=CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "robotmustRun", "REG_SZ");
//			if(redRegistryWindows.equals("")){
//				createRegistryRobotMustRun();
//			}
		} catch (Exception e) {
			createRegistryRobotMustRun();
			e.printStackTrace();
		}
	}
	
	private void createRegistryRobotMustRun(){
		ArrayList<AppInformation> installedApps = AppExaminator.getInstalledApps();
		String idRobots="";
		for (AppInformation appInformation : installedApps) {
			idRobots+= appInformation.getIdRobot()+",";
		}
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "robotmustRun",idRobots );
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	private void checkRegistryRobotNoRunning(){
		try {
			CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "robotnotRun","REG_SZ");
		} catch (Exception e) {
			createregistryRobotNotRunning();
			e.printStackTrace();
		}
	}
	
	private void createregistryRobotNotRunning(){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "robotnotRun","");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	private void checkWebServicesRegistry(){
		try {
			CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "webservicesCreator", "REG_SZ");
		} catch (Exception e) {
			createWebServicesCreatorRegistry();
			e.printStackTrace();
		}
		
		try {
			CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "webservicesConsult", "REG_SZ");
		} catch (Exception e) {
			e.printStackTrace();
			createWebServicesConsultRegistry();
		}
	}
	
	
	
	private void createWebServicesCreatorRegistry(){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "webservicesCreator", Webservice.getUrl().toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	private void createWebServicesConsultRegistry(){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "webservicesConsult",
					SisproRobotManagerHelperService.getUrl().toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void initRobot(){
		try {
			String robotIds = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY,"robotmustRun","REG_SZ");
			LOGGER.info("startup all Robot id "+robotIds);
			final String[] split = robotIds.split(",");
			final HashMap<Long, String> robotNotRunning = getRobotNotRunning();
			LOGGER.info("Actual robot not running"+robotNotRunning.keySet());
			
			final ArrayList<AppInformation> runningApps = AppExaminator.getRunningApps();
			System.out.println("split"+split+"size"+split.length);
			for (int i = 0; i < split.length; i++) {
				final int b = i;
				Thread hilo=new Thread(new Runnable() {
					@Override
					public void run() {
						try{
							if(split[b].length()>0){
								
								long  idRobot=Long.parseLong(split[b]);
								System.out.println("idRobot"+idRobot);
								if(!robotNotRunning.containsKey(idRobot)){
									boolean alreadyRunning=false;
									for (AppInformation appInformation : runningApps) {
										if(appInformation.getIdRobot()==idRobot){
											alreadyRunning=true;
											break;
										}
									}
									LOGGER.info("the robot id"+idRobot+" alredyRunning"+alreadyRunning);
									if(!alreadyRunning ){
										runRobot(idRobot);
									}
								}else{
								}
								
							}
							
						}catch(NumberFormatException nex){
							LOGGER.log(Level.SEVERE,"cannot parse the id for run");
						}						
					}
				});
				hilo.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		String robotIds;
		try {
			Path regInstallationPath = AppExaminator.getInstallationPath();
			setInstallationPath(regInstallationPath);
			boolean runJarRobot = RobotManager.runJarRobot("32D");
			System.out.println("run jar"+runJarRobot);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void checkUbicationRegistry(){
		try {
			String ubicationRegist = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "installationPath","REG_SZ");
			setUbication(ubicationRegist);
		} catch (Exception e) {
			createUbicationPathRegistry();
			e.printStackTrace();
		}
	}
	
	
	private static void createUbicationPathRegistry(){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "installationPath", "C:\\Users\\senrigan\\Documents\\pruebas\\GDC\\RobotScript", REGISTRY_TYPE.REG_SZ);
//			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "installationPath", getCurrentPath().toString(), REGISTRY_TYPE.REG_SZ);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
        }
        return null;
    }

	
	
	private static HashMap<Long,String> getRobotNotRunning(){
		HashMap<Long,String> robotNotRun=new HashMap<Long,String>();
		try {
			String robotIds = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY,"robotnotRun","REG_SZ");
			if(robotIds.endsWith(",")){
				robotIds=robotIds.substring(0,robotIds.length()-1 );
				
			}
			String[] split = robotIds.split(",");
			for (int i = 0; i < split.length; i++) {
				String string = split[i];
				if(string.length()>0){
					try{
						
						long idRobot = Long.parseLong(string);
						robotNotRun.put(idRobot, "");
					}catch(NumberFormatException ex){
						ex.printStackTrace();
					}
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return robotNotRun;
	}
	
	public static boolean runRobotByApp(long idApp){
		ArrayList<AppInformation> notRunnigApps = AppExaminator.getNotRunnigApps();
		for (AppInformation appInformation : notRunnigApps) {
			System.out.println(appInformation.getAppName()+" idapp"+appInformation.getIdApp());
			String appName = appInformation.getAppName();
			System.out.println(appInformation.getIdApp() +" idrobot"+idApp);
			if(appInformation.getIdApp()==idApp){
				runJarRobot(appInformation.getAppName());
				return true;
			}
		}
		return false;
	}
	
	public static boolean runRobot(long idRobot){
		ArrayList<AppInformation> notRunnigApps = AppExaminator.getNotRunnigApps();
		for (AppInformation appInformation : notRunnigApps) {
			System.out.println(appInformation.getAppName()+" idapp"+appInformation.getIdApp());
			System.out.println(appInformation.getIdApp() +" idrobot"+idRobot);
			if(appInformation.getIdRobot()==idRobot){
				LOGGER.info("starup the robot id :"+idRobot);
				return runJarRobot(appInformation.getAppName());
			}
		}
		return false;
	}
	
	
	public static boolean runRobotWithGui(long idRobot,long idApp){
		ArrayList<AppInformation> notRunnigApps = AppExaminator.getNotRunnigApps();
		for (AppInformation appInformation : notRunnigApps) {
			System.out.println(appInformation.getAppName()+" idapp"+appInformation.getIdApp());
			System.out.println(appInformation.getIdApp() +" idrobot"+idRobot);
			if(appInformation.getIdRobot()==idRobot && appInformation.getIdApp()==idApp){
				LOGGER.info("starup the robot id :"+idRobot);
				return runJarRobot(appInformation.getAppName());
			}
		}
		return false;
	}
	
	
	private static boolean runJarRobot(final String appName){
		
		final CountDownLatch latch=new CountDownLatch(1);
//		final boolean valueTem=false;
		valueStart=false;
		Thread hilo=new Thread( new Runnable() {
			public void run() {
				boolean value=false;
				String java ="\""+Environment.getJava()+"\"";
				Path robotJar=Paths.get(installationPath.resolve("data").resolve(appName).resolve(Constants.JARNAME).toString());
				Path appPath=robotJar.getParent();
				String jar="\""+robotJar.toString()+"\"";
//				String command="cd  \""+appPath+"\" && "+java +" -Dname=\"Robot_"+appName+"\" "+" -jar "+robotJar.getFileName();
				String command=java +" -Dname=\"Robot_"+appName+"\" "+" -jar "+robotJar.getFileName();

				try {
					System.out.println("command"+command);
//					Runtime.getRuntime().exec(command);
					Runtime.getRuntime().exec(command,null,appPath.toFile());
//					Process exec = Runtime.getRuntime().exec(command);
//					BufferedReader in=new BufferedReader(new InputStreamReader(exec.getInputStream()));
//					String line;
//					while((line=in.readLine())!=null){
//						System.out.println("stopjar lines"+line);
//						if(line.contains("Robot has been started")){
//							value =true;
//						}
//						
//					}
					value=true;
				} catch (IOException e) {
					e.printStackTrace();
					value=false;
				}
				latch.countDown();
				valueStart=value;
			}
		});
		hilo.start();
		try {
			latch.await(60,TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return valueStart;
		
	}
	
	public static void runRobot(String applicationName){
		
	}
	
	public static void stopRobot(long idRobot) throws Exception{
		ArrayList<RobotInformation> runningRobot = VirtualMachineExaminator.getRunningRobot();
		for (RobotInformation robotInformation : runningRobot) {
			if(robotInformation.getRobotId()==idRobot){
				LOGGER.log(Level.INFO, "stopping robotid "+idRobot);
				if(!stopJar(robotInformation.getIdProcess())){
					LOGGER.log(Level.WARNING,"the robotid "+idRobot +"cannot stoped");
					throw new Exception("cannot stop jar of appName"+robotInformation.getAppName());
				}
			}
		}
	}
	public static void stopAllRobots() throws Exception{
		ArrayList<RobotInformation> runningRobot = VirtualMachineExaminator.getRunningRobot();
		for (RobotInformation robotInformation : runningRobot) {
			
				stopRobot(robotInformation.getRobotId());
			
		}
	}
	private static boolean stopJar(final long pid){
		valueStop=false;
		final CountDownLatch latch=new CountDownLatch(1);
		Thread hilo=new Thread(new Runnable() {
			
			@Override
			public void run() {
				String command="TaskKill /PID "+pid+" /F";
				try {
					Process exec = Runtime.getRuntime().exec(command);
					BufferedReader in=new BufferedReader(new InputStreamReader(exec.getInputStream()));
					String line;
					while((line=in.readLine())!=null){
						System.out.println("stopjar lines"+line);
						if(line.contains("SUCCESS")){
							valueStop= true;
						}
						
					}
				} catch (IOException e) {
					e.printStackTrace();
					valueStop=false;
				}
				
				latch.countDown();
				
			}
		});
		hilo.start();
		try {
			latch.await(60, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return valueStop;
	}
	
	public static void stopRobot(String applicationName){
		
	}
	
	
	public static void getRobotMustRunb(){
		
	}
	
	public static void getRobotMustNotRun(){
		
	}
	
	public static void addNewRobot(Path source){
		
	}
	
	public static void updateRobot(long idRobot,Path souruce){
		
	}
	
	public static void updateAllRobots(Path source){
		
	}

	public static Path getInstallationPath() {
		return installationPath;
	}
	
	
	public static Path getServicesFolderPath(){
		return getInstallationPath().resolve("data");
	}
	
	public static String getDateForFilesChanges(){
		Calendar cal = Calendar.getInstance();
		String dat=cal.get(Calendar.DAY_OF_MONTH)+"-"+
					(cal.get(Calendar.MONTH)+1)+"-"+
					cal.get(Calendar.YEAR)+"_"+
					cal.get(Calendar.HOUR_OF_DAY)+
					cal.get(Calendar.MINUTE);
		return dat;	
	}
	
	public static void setUbication(String ubicationRegistry){
		ubication=ubicationRegistry;
	}
	
	public static String getUbication(){
		return ubication;
	}

	public static void setInstallationPath(Path installationPath) {
		RobotManager.installationPath = installationPath;
	}
	
	
	
	public static ArrayList<AppInformation> getInstalledApp(){
		return AppExaminator.getInstalledApps();
	}
	
	
	
	public static void StopScanServices(){
		LOGGER.info("changing the status of scanning stoping services");
		RobotManager.executeScan=false;
	}
	
	public static void StartScanServices(){
		LOGGER.info("changing the status of scanning starting services");

		RobotManager.executeScan=true;
	}
	
	
	
	public static boolean isRunningScan(){
		return executeScan;
	}
	
	
	
	
	
	
	
	
	
	
}
