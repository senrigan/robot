package com.gdc.nms.robot.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;

import com.gdc.nms.robot.Main;
import com.gdc.nms.robot.util.AppExaminator;
import com.gdc.nms.robot.util.Constants;
import com.gdc.nms.robot.util.Environment;
import com.gdc.nms.robot.util.LogLayout;
import com.gdc.nms.robot.util.RobotInformation;
import com.gdc.nms.robot.util.VirtualMachineExaminator;
import com.gdc.nms.robot.util.indexer.AppInformation;
import com.gdc.nms.robot.util.jade.InitPlataform;
import com.gdc.nms.robot.util.jade.SRMAgentManager;
import com.gdc.nms.robot.util.registry.CommandExecutor;
import com.gdc.nms.robot.util.registry.CommandExecutor.REGISTRY_TYPE;
import com.gdc.robothelper.webservice.SisproRobotManagerHelperService;
import com.gdc.robothelper.webservice.robot.Webservice;

import jade.core.AID;

public class RobotManager extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private static Path installationPath;
	private static String ubication;
	private static final Logger LOGGER=Logger.getLogger(RobotManager.class);
	private static boolean valueStart=false;
	private static boolean valueStop=false;
	private static boolean executeScan=true;
	private static RobotManagerGui robotManagerGui;
	public static Appender logAppender;
	public RobotManager() {
		
	}
	
	public void start(){
		CreateLogFile();
		if(srmAlreadyRunning()){
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
				LOGGER.error("excepcion ", e);

			} catch (InstantiationException e) {
				e.printStackTrace();
				LOGGER.error("excepcion ", e);

			} catch (IllegalAccessException e) {
				e.printStackTrace();
				LOGGER.error("excepcion ", e);

			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
				LOGGER.error("excepcion ", e);

			}
			Thread hilo=new Thread(new Runnable() {
				
				@Override
				public void run() {
					initAllRobots();
					
				}
			});
			StartAgentPlatform();
			hilo.start();
			robotManagerGui=new RobotManagerGui();
			
		}else{
			JOptionPane.showMessageDialog(null, "El Programa ya esta en ejecucion", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	private void CreateLogFile(){
		Path currentPath = getCurrentPath();
		currentPath = currentPath.resolve("inMonitor").resolve("srm.log");
		System.out.println("**** cuenrrrent path for srmlog"+currentPath.toString());
		try {
			
			logAppender=new FileAppender(new LogLayout(),currentPath.toString());
			LOGGER.addAppender(logAppender);
			logAppender.setLayout(new LogLayout());
			
			LOGGER.info("The RobotManager Instance is Created");
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);
		}
		
	}
	
	private boolean srmAlreadyRunning(){
		return checkAndCreatedLockFile();
	}
	
	private boolean checkAndCreatedLockFile(){
		Path currentPath = getCurrentPath();
		File lockFile = new File(currentPath.resolve("SRM.LOCK").toString());
		boolean created=false;
		if(lockFile.exists()){
			if(lockFile.delete()){
				LOGGER.info("succeful delete lockfile");
				created=createLockFile(lockFile);
			}else{
				LOGGER.info("unable to delete lockfile");
			}
		}else{
			created= createLockFile(lockFile);
		}
		
		return created;
	}
	
	private boolean createLockFile(File lockFile){
		try {
			if(lockFile.createNewFile()){
				LOGGER.info("succeful create file try hidden file");
				hiddeFile(lockFile.toPath());
				LOGGER.info("change permission lockfile");
				FileChannel channel =  new RandomAccessFile(lockFile, "rw").getChannel();
				LOGGER.info("try lock file");
				FileLock lock = channel.lock();
//				channel.tryLock();
				
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}catch(Exception e){
			LOGGER.error("excepcion ", e);
		}
		return false;
	}
	
	
	public static void hiddeFile(Path fileToHide){
		try {
			Boolean attribute = (Boolean)Files.getAttribute(fileToHide, "dos:hidden", LinkOption.NOFOLLOW_LINKS);
			if(attribute !=null && !attribute){
				System.out.println("ocultando archivo");
				Files.setAttribute(fileToHide, "dos:hidden", Boolean.TRUE, LinkOption.NOFOLLOW_LINKS);
			}else{
				System.out.println("el archivo ya esta oculta");
			}
		} catch (IOException e) {
			LOGGER.error("excepcion ", e);

			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		RobotManager.hiddeFile(Paths.get("C:\\Program Files\\GDC\\RobotScript\\hola.txt"));
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
		LOGGER.info( "reading and creating windows registry necessary for sysprorobotmanager");
		checkRegistryRobotMustRun();
		checkRegistryRobotNoRunning();
		checkUbicationRegistry();
		checkWebServicesRegistry();
		checkUbicationCreationRegistry();
		
		
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
			LOGGER.error("excepcion ", e);

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
			LOGGER.error("excepcion ", e);

		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}

	}
	
	private void checkRegistryRobotNoRunning(){
		try {
			CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "robotnotRun","REG_SZ");
		} catch (Exception e) {
			createregistryRobotNotRunning();
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
	}
	
	private void createregistryRobotNotRunning(){
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
	
	private void createWebServicesConsultRegistry(){
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
	
	
	public static void initAllRobots(){
		try {
			
			final ArrayList<AppInformation> runningApps = AppExaminator.getInstalledApps();
			LOGGER.info("startup all Robot ");
			for (AppInformation appInformation : runningApps) {
				LOGGER.info("starting robot:  "+appInformation.getAlias());
				runJarRobot(appInformation.getAppName());
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

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
							LOGGER.info("cannot parse the id for run");
							LOGGER.error("excepcion ", nex);

						}						
					}
				});
				hilo.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
	}
	
	

	
	private static void checkUbicationRegistry(){
		try {
			String ubicationRegist = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "installationPath","REG_SZ");
		} catch (Exception e) {
			createUbicationPathRegistry();
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
	}
	
	/**
	 * this for ubucation is for robotCreation
	 */
	private static void checkUbicationCreationRegistry(){
		try{
			String ubicationRegist = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "ubicationRobot","REG_SZ");
			setUbication(ubicationRegist);
		}catch(Exception ex){
			createUbicationRegistruCreation();
			LOGGER.error("excepcion ", ex);

		}
	}
	
	private static void createUbicationRegistruCreation(){
		try {
			String ubication="generic";
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "ubicationRobot", ubication, REGISTRY_TYPE.REG_SZ);
//			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "installationPath", getCurrentPath().toString(), REGISTRY_TYPE.REG_SZ);
			setUbication(ubication);
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}

	}
	
	
	public static void createUbicationRegistruCreation(String ubication){
		try {
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "ubicationRobot", ubication, REGISTRY_TYPE.REG_SZ);
//			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "installationPath", getCurrentPath().toString(), REGISTRY_TYPE.REG_SZ);
			setUbication(ubication);
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}

	}
	private static void createUbicationPathRegistry(){
		try {
//			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "installationPath", "C:\\Users\\senrigan\\Documents\\pruebas\\GDC\\RobotScript", REGISTRY_TYPE.REG_SZ);
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "installationPath", getCurrentPath().toString(), REGISTRY_TYPE.REG_SZ);
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}

	}
	
	public static String getInstallationPathRegistry(){
		String ubicationRegist=null;
		try {
			ubicationRegist = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "installationPath","REG_SZ");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
		return ubicationRegist;
	}
	
	
	public static Path getCurrentPath() {
        try {
            Path currentPath = Paths.get(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());

            if (Files.isRegularFile(currentPath, new java.nio.file.LinkOption[0])) {
                return currentPath.getParent();
            }
            return currentPath;
        } catch (URISyntaxException e) {
			LOGGER.error("excepcion ", e);

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
			LOGGER.error("excepcion ", e);

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
		System.out.println("+++++ App Nanme : "+appName);
		final CountDownLatch latch=new CountDownLatch(1);
//		final boolean valueTem=false;
		valueStart=false;
		Thread hilo=new Thread( new Runnable() {
			public void run() {
				boolean value=false;
				String java ="\""+Environment.getJava()+"\"";
				String nuewName=appName.replaceAll("\\s+$", "");
				File[] botFiles = AppExaminator.getBotFiles(installationPath.resolve("data").resolve(nuewName));
				if(botFiles.length>0){
					
					Path robotJar=botFiles[0].toPath();
					Path appPath=robotJar.getParent();
					String jar="\""+robotJar.toString()+"\"";
//				String command="cd  \""+appPath+"\" && "+java +" -Dname=\"Robot_"+appName+"\" "+" -jar "+robotJar.getFileName();
					String command=java +" -Dname=\"Robot_"+nuewName+"\" "+" -jar "+robotJar.getFileName();
					
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
						LOGGER.error("excepcion ", e);

					}
					latch.countDown();
					valueStart=value;
				}else{
					valueStart=false;
				}
				
			}
		});
		hilo.start();
		try {
			latch.await(60,TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
		return valueStart;
		
	}
	
	public static void runRobot(String applicationName){
		
	}
//ya no funciona es necesario realizar otro metodo	
//	public static void stopRobot(long idRobot) throws Exception{
//		ArrayList<RobotInformation> runningRobot = VirtualMachineExaminator.getRunningRobot();
//		for (RobotInformation robotInformation : runningRobot) {
//			if(robotInformation.getRobotId()==idRobot){
//				LOGGER.info( "stopping robotid "+idRobot);
//				if(!stopJar(robotInformation.getIdProcess())){
//					
//					LOGGER.info("the robotid "+idRobot +"cannot stoped");
//					throw new Exception("cannot stop jar of appName"+robotInformation.getAppName());
//				}
//			}
//		}
//	}
	public static void stopAllRobots() throws Exception{
		HashMap<String, AID> robotRegister = InitPlataform.getRobotRegister();
		Set<String> keySet = robotRegister.keySet();
		for (String string : keySet) {
			AID aid = robotRegister.get(string);
			SRMAgentManager.stopAgent(aid);
			
		}
		
	}
	public static boolean stopJar(final long pid){
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
					LOGGER.error("excepcion ", e);
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
		if(ubication==null){
			try {
				String ubicationRegist = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "ubicationRobot","REG_SZ");
				setUbication(ubicationRegist);
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("excepcion ", e);

			}
			
		}
		
		return ubication;
	}

	public static void setInstallationPath(Path installationPath) {
		RobotManager.installationPath = installationPath;
	}
	
	
	
	public static ArrayList<AppInformation> getInstalledApp(){
		return AppExaminator.getInstalledApps();
	}
	
	
	
//	public static void StopScanServices(){
//		LOGGER.info("changing the status of scanning stoping services");
//		RobotManager.executeScan=false;
//	}
//	
//	public static void StartScanServices(){
//		LOGGER.info("changing the status of scanning starting services");
//
//		RobotManager.executeScan=true;
//	}
//	
//	
//	
//	public static boolean isRunningScan(){
//		return executeScan;
//	}
	
	
	
	
	
	
	
	
	
	
}
