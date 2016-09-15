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
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;

import com.gdc.nms.robot.Main;
import com.gdc.nms.robot.gui.tree.test.InterfaceManager;
import com.gdc.nms.robot.gui.util.SRMGUI;
import com.gdc.nms.robot.gui.util.process.JavaProcess;
import com.gdc.nms.robot.util.AppExaminator;
import com.gdc.nms.robot.util.Constants;
import com.gdc.nms.robot.util.Environment;
import com.gdc.nms.robot.util.LogLayout;
import com.gdc.nms.robot.util.indexer.AppInformation;
import com.gdc.nms.robot.util.jade.InitPlataform;
import com.gdc.nms.robot.util.jade.SRMAgentManager;
import com.gdc.nms.robot.util.registry.CommandExecutor;
import com.gdc.nms.robot.util.registry.CommandExecutor.REGISTRY_TYPE;
import com.gdc.robothelper.webservice.SisproRobotManagerHelperService;
import com.gdc.robothelper.webservice.robot.Webservice;

import jade.core.AID;

public class RobotManager extends JFrame {
	private static final long serialVersionUID = 1L;

	
	private static Path installationPath;
	private static String ubication;
	private static final Logger LOGGER=Logger.getLogger(RobotManager.class);
	private static boolean valueStart=false;
	private static boolean valueStop=false;
	private static boolean executeScan=true;
	private static RobotManagerGui robotManagerGui;
	private static InterfaceManager srmGuiManager;
	private static SRMGUI srmGui;
	public static Appender logAppender;
	private static RobotManager instance;
	public RobotManager() {
		
	}
	
	public static RobotManager getInstance(){
		if(instance==null){
			instance=new RobotManager();
		}
		return instance;
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
//			robotManagerGui=new RobotManagerGui();
			Thread hilo=new Thread(new Runnable() {
				
				@Override
				public void run() {
					initAllRobots();
					
				}
			});
			srmGui=new SRMGUI();
			srmGuiManager=new InterfaceManager(srmGui);
			srmGuiManager.loadAllRobots();
			StartAgentPlatform();
			hilo.start();
			
		}else{
			JOptionPane.showMessageDialog(null, "El Programa ya esta en ejecucion", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static InterfaceManager getSRMGuiManager(){
		return srmGuiManager;
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
//		checkRegistryRobotMustRun();
//		checkRegistryRobotNoRunning();
		checkUbicationRegistry();
		checkWebServicesRegistry();
		checkUbicationCreationRegistry();
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
			System.out.println("running apps"+runningApps);
			for (AppInformation appInformation : runningApps) {
				LOGGER.info("starting robot:  "+appInformation.getAlias());
				System.out.println("robot to run"+appInformation.getFolderPath());
				final AppInformation apF=appInformation;
				if(appInformation.isServicesRunning()){
//					srmGuiManager.
					System.out.println("changing services to already running"+appInformation.getAppName());
					srmGuiManager.changeStatusServicesToActive(appInformation.getAppName());
//					RobotManager.getGuiManager().getJtreManager().addToRun(appInformation.getAppName()); 
				}else{
					Thread th=new Thread( new Runnable() {
						public void run() {
							runJarRobot(apF.getBotFile());
							
						}
					});
					th.start();
					
				}
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
				runJarRobot(appInformation.getBotFile());
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
				return runJarRobot(appInformation.getBotFile());
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
				return runJarRobot(appInformation.getBotFile());
			}
		}
		return false;
	}
	
	
	public static boolean runRobot(AppInformation appInformatio){
		if(appInformatio!=null){
			return runJarRobot(appInformatio.getBotFile());
		}
		return false;
	}
	
	public static void stopRobot(AppInformation appInformation){
		HashMap<String, AID> robotRegister = InitPlataform.getRobotRegister();
		AID aid = robotRegister.get(appInformation.getAppName());
		if(aid!=null){
			
			InitPlataform.getAgentManager().getAgent().senMessageToKill(aid);
			JOptionPane.showMessageDialog(null, "Espere unos minutos a que el robot "+appInformation.getAppName()+" sea detenido");
			
		}else{
			int result = JOptionPane.showConfirmDialog(null, 
					   "No es posible detener el servicio de manera normal , deseas forzar su detencion ?",null, JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
			   long pid = appInformation.getPID();
			   if(JavaProcess.isValidJavaProceesId(pid)){
				   stopJar(pid);
			   }
			} 
		}
	}
	
	public static boolean runJarRobot(final File botFile){
		final CountDownLatch latch=new CountDownLatch(1);
		valueStart=false;
		System.out.println("bot file oto run "+botFile);
		Thread hilo=new Thread( new Runnable() {
			public void run() {
				boolean value=false;
				String java ="\""+Environment.getJava()+"\"";
				String nuewName=botFile.getParentFile().getName().replaceAll("\\s+$", "");
				System.out.println("before run the robos wildacars"+installationPath.resolve("data").resolve(nuewName));
				System.out.println("bot files"+botFile.getName());
				LOGGER.info("botfiles detected "+botFile.getName());
					File parentFile=botFile.getParentFile();
					String command=java +" -Dname=\"Robot_"+nuewName+"\" "+" -jar "+botFile.getName();
					try {
						System.out.println("command"+command);
						Process exec = Runtime.getRuntime().exec(command,null,parentFile);
						LOGGER.info("Analizando robot "+botFile.toString());
						if(searchForError(exec)){
							LOGGER.info("retry to run : "+botFile.toString());
							exec = Runtime.getRuntime().exec(command,null,parentFile);
						}
						
						value=true;
					} catch (IOException e) {
						e.printStackTrace();
						value=false;
						LOGGER.error("excepcion ", e);

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
			LOGGER.error("excepcion ", e);

		}
		return valueStart;
		
	}
	
	
	
	private static boolean searchForError(Process proc){
		try{
			BufferedReader stdInput = new BufferedReader(new 
					InputStreamReader(proc.getInputStream()));
			BufferedReader stdError = new BufferedReader(new 
					InputStreamReader(proc.getErrorStream()));
			System.out.println("Here is the standard output of the command:\n");
			String s = null;
			int maxLinesToRead=20;
			int linesCount=0;
			while ((s = stdInput.readLine()) != null) {
				if(linesCount<maxLinesToRead){
					System.out.println(s);
					if(checkErrorByJacob(s)){
						System.out.println("copyin dlls jacobs");
						LOGGER.info("the robot no contains jacob dlls");
						copyDllToJavaHome();
						return true;
					}else{
						if(s.equals("Robot has been started.")){
							return false;
						}
					}
					
					linesCount++;
				}else{
					break;
				}
				
			}
//			System.out.println("Here is the standard error of the command (if any):\n");
//			while ((s = stdError.readLine()) != null) {
//				System.out.println(s);
//			}
			return false;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	private static void  copyDllToJavaHome() throws IOException{
		Path javaHome = Environment.getJavaHome();
		javaHome=javaHome.resolve("bin");
		System.out.println("javahome "+javaHome.toString());
		String architecture = System.getProperty("sun.arch.data.model");
		Path  dll=null;
		String tempFolder=getInstallationPath().resolve("inMonitor").resolve("lib").toString();
		String namedll="";
		if(architecture.endsWith("64")){
			namedll="jacob-1.18-x64.dll";			
		}else{ 
			namedll="jacob-1.18-x86.dll";
		}
		dll=Paths.get(tempFolder).resolve(namedll);

		File source=dll.toFile();
		File dest=javaHome.toFile();
		LOGGER.info("Copyng dlls jacob "+" source : "+source+" dest "+dest);

		if(!javaHome.resolve(namedll).toFile().exists()){	
			FileUtils.copyFileToDirectory(source, dest);
		}
	}
	
	
	public static boolean existJacobInJava(){
		Path javaHome = Environment.getJavaHome();
		javaHome=javaHome.resolve("bin");
		String architecture = System.getProperty("sun.arch.data.model");
		String namedll="";
		if(architecture.endsWith("64")){
			namedll="jacob-1.18-x64.dll";			
		}else{ 
			namedll="jacob-1.18-x86.dll";
		}
		if(Files.exists(javaHome.resolve(namedll))){
			return true;
		}
		return false;
	}
	
	private static boolean checkErrorByJacob(String error){
		if(error.contains("java.lang.UnsatisfiedLinkError")){
			return true;
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
				System.out.println("before run the robos wildacars"+installationPath.resolve("data").resolve(nuewName));
				File[] botFiles = AppExaminator.getBotFiles(installationPath.resolve("data").resolve(nuewName));
				System.out.println("bot files"+Arrays.toString(botFiles));
				LOGGER.info("botfiles detected "+Arrays.toString(botFiles));
				if(botFiles.length>0){
					
					Path robotJar=botFiles[0].toPath();
					Path appPath=robotJar.getParent();
					String jar="\""+robotJar.toString()+"\"";
					String command=java +" -Dname=\"Robot_"+nuewName+"\" "+" -jar "+robotJar;
					
					try {
						System.out.println("command"+command);
						Runtime.getRuntime().exec(command,null,appPath.toFile());
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
						if(line.contains("SUCCESS")|| line.contains("Correcto")){
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
	
	
	
	
	
	
	
	
	
	
}
