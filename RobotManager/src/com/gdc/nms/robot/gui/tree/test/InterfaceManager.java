package com.gdc.nms.robot.gui.tree.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.gdc.nms.robot.gui.AddNewRobotPanel;
import com.gdc.nms.robot.gui.DeleteRobotPanel;
import com.gdc.nms.robot.gui.InfoWindows;
import com.gdc.nms.robot.gui.NewConfigurationPanel;
import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.nms.robot.gui.auxiliar.LoadingFrame;
import com.gdc.nms.robot.gui.newInterface.ButtonListener;
import com.gdc.nms.robot.gui.util.SRMGUI;
import com.gdc.nms.robot.util.AppExaminator;
import com.gdc.nms.robot.util.Language;
import com.gdc.nms.robot.util.indexer.AppInformation;
import com.gdc.nms.robot.util.jade.SRMAgentManager;
import com.gdc.robothelper.webservice.ClientSRMHelperWebService;
import com.gdc.robothelper.webservice.SisproRobotManagerHelperService;
import com.gdc.robothelper.webservice.WebServicesManager;
import com.gdc.robothelper.webservice.robot.CreatorRobotWebService;
import com.gdc.robothelper.webservice.robot.Webservice;


public class InterfaceManager {
	private SRMGUI gui;
	private boolean addRobotActive;
	private boolean getInfoActive;
	private boolean deleteRobotActive;
	private boolean configurationRobotActive;
	private boolean showlogsActive;
	private Logger LOGGER=Logger.getLogger(InterfaceManager.class.getName());
	public InterfaceManager(SRMGUI gui){
		this.gui=gui;
	}
	
//	public InterfaceManager(){
//		
//	}
//	public void setSRMGUI(SRMGUI gui){
//		this.gui=gui;
//	}
	
	
	public void loadAllRobots(){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ArrayList<AppInformation> installedApps = AppExaminator.getInstalledApps();
				for (AppInformation appInformation : installedApps) {
					System.out.println("add  "+appInformation.getAppName());
					gui.addNewRobotUI(appInformation.getAppName());
					
				}				
			}
		});
	}
	
	
	public void setInfoText(String text){
		gui.setTextInfo(text);
	}
	
	
	
	
	public static void main(String[] args) {
		SRMGUI gui=new SRMGUI();
		InterfaceManager man=new InterfaceManager(gui);
		man.loadAllRobots();
		System.out.println("**"+gui.getMapRobots());
		Set<String> keySet = gui.getMapRobots().keySet();
		System.out.println("--"+keySet);
		for (String string : keySet) {
			System.out.println("string **"+string);
			gui.changeStatus(gui.getMapRobots().get(string));
			try {
				Thread.sleep(5000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gui.changeStatusToActive(gui.getMapRobots().get(string));
			man.setInfoText("pruebas");
			
		}
		
		try {
			System.out.println("reading data logFile"+new Date());
			man.readFile(new File("C:\\Program Files\\GDC\\RobotScript\\data\\testapp\\robot.log"));
			System.out.println("finish reading data logFile"+new Date());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void setAplicationName(String applicactionName){
		gui.setAplicationName(applicactionName);
	}
	
	public void setAliasName(String aliasName){
		gui.setAliasName(aliasName);
	}
	
	public void setIdRobot(String idRobot){
		gui.setIdRobot(idRobot);
	}
	
	public void changeActionButton(boolean isRunning){
		gui.changeStatusExecuteButton(isRunning);
	}
	
	public void addRunningToCount(){
		gui.changeExecutionCount(gui.getExecutionCount()+1);
	}
	
	public void addStopedCount(){
		gui.changeStopedCount(gui.getStopetCount()+1);
	}
	
	public void decreaseRunningCount(){
		gui.changeExecutionCount(gui.getExecutionCount()-1);
	}
	
	public void decreaseStoppedCount(){
		gui.changeStopedCount(gui.getStopetCount()-1);
	}
	
	
	
	public void disableActionButton(boolean disable){
		gui.isEnableActionButton(!disable);
	}
	
	
	public static void showLogsSelectedApp(){
		JButton lastSelectedButton = ButtonListener.getLastSelectedButton();
		AppInformation appData = AppExaminator.getAppData(lastSelectedButton.getText());
		System.out.println("appData"+appData);
		File logFile = appData.getLogFile();
		System.out.println("logFile"+logFile);
		if(logFile!=null){
			String readFileText;
			try {
				readFileText = readFile(logFile);
				InfoWindows infoWin=new InfoWindows("logs "+appData.getAlias(),readFileText); 
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			JOptionPane.showMessageDialog(null,"Error","No existe logs",JOptionPane.ERROR_MESSAGE);

			
		}
		
	}
	
	
	 public static String readFile(File file)
	            throws IOException {

	        String filePath = file.toString();
//	        printFileContents(filePath);
	        Path path = Paths.get(filePath);

	        FileChannel fileChannel =  FileChannel.open(path);
	        ByteBuffer buffer = ByteBuffer.allocate(8000);
	        int noOfBytesRead = fileChannel.read(buffer);
	        StringBuilder fullText=new StringBuilder();
			
	        while (noOfBytesRead != -1) {
	            System.out.println("Number of bytes read: " + noOfBytesRead);
	            buffer.flip();
	            System.out.print("Buffer contents: ");

	            while (buffer.hasRemaining()) {
	            	fullText.append((char)buffer.get());
//	                System.out.print((char) buffer.get());                    
	            }

//	            System.out.println(" ");
	            buffer.clear();
	            noOfBytesRead = fileChannel.read(buffer);
	        }
	        fileChannel.close();
	        return fullText.toString();
	    }
	 
	 
	  public static void printFileContents(String path)
	            throws IOException {

	        FileReader fr = new FileReader(path);
	        BufferedReader br = new BufferedReader(fr);
	        String textRead = br.readLine();
	        System.out.println("File contents: ");

	        while (textRead != null) {
	            System.out.println(" contente:    " + textRead);
	            textRead = br.readLine();
	         }
	         fr.close();
	         br.close();
	    }
	  
	  public static AppInformation getLastSelectedInfo(){
		  JButton lastSelectedButton = ButtonListener.getLastSelectedButton();
		  if(lastSelectedButton!=null){
			  String text = lastSelectedButton.getText();
			  AppInformation appData = AppExaminator.getAppData(text);
			  return appData;
			  
		  }
		  return null;
	  }
	  public static String getInfoRobot(){
		  AppInformation selectedAppInformation = getLastSelectedInfo();
		  LoadingFrame.getInstance().showLoadingFrame(Language.get("robot.getinfo.message"));
		  if(selectedAppInformation!=null){
			  
			  String agentInfo = SRMAgentManager.getAgentInfo(selectedAppInformation);
			  LoadingFrame.getInstance().close();
			  return agentInfo;
		  }
		  LoadingFrame.getInstance().close();
		  return null;
	  }
	  
	  
	  public static boolean  runSelectedRobot(){
		  
		  AppInformation lastSelectedInfo = getLastSelectedInfo();
		  if(lastSelectedInfo!=null){
			 return RobotManager.runRobot(lastSelectedInfo);
		  }
		  return false;
	  }
	  
	  public static void stopSelectedRobot(){
		  stopRobot(getLastSelectedInfo());;
	  }
	  public static void stopRobot(AppInformation appInformation){
		  RobotManager.stopRobot(appInformation);
	  }
	  
	  public ArrayList<String> getRobotList(){
		  HashMap<String, JButton> mapRobots = gui.getMapRobots();
		  ArrayList<String> robotNames=new ArrayList<String>();
		  Set<String> keySet = mapRobots.keySet();
		  for (String key : keySet) {
			  robotNames.add(key);
		  }
		  return robotNames;
	  }
	  
	  
	  public void changeStatusServicesToActive(String serviceName){
		  JButton jButton = gui.getMapRobots().get(serviceName);
		  if(jButton!=null){
			  gui.changeStatusToActive(jButton);
			  checkIfAreSelected(serviceName, true);

		  }
	  }
	  
	  
	  public void changeStattusServicesToStoped(String serviceName){
		  JButton jButton = gui.getMapRobots().get(serviceName);
		  if(jButton!=null){
			  gui.changeStatusToStoped(jButton);
			  checkIfAreSelected(serviceName, false);
		  }
	  }
	  
	  
	  private void checkIfAreSelected(String serviceName,final boolean running){
		  System.out.println("checking Selecting Robot");
		  AppInformation lastSelectedInfo = getLastSelectedInfo();
		  if(lastSelectedInfo!=null){
			  String appName = lastSelectedInfo.getAppName();
			  System.out.println("appName is equal"+appName+" to servicesname"+serviceName+"equals"+appName.equals(serviceName));
			  if(appName.equals(serviceName)){
				  SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						gui.changeStatusExecuteButton(!running);
						
					}
				});
			  }
			  
		  }
		  
	  }
/**
 * invoke a new instance of addnewRobotPanel and show them only if already not runnining other instances of same 	  
 */
	  public  void showAddRobot(){
		
//		System.out.println("servicio de consulta"+checkWebServicesConsult());
//		System.out.println("consultando servicio de creacion"+checkWebServicesCreator());
		if(addRobotActive){
			
			JOptionPane.showMessageDialog(null, Language.get("addrobot.error"), "Error", JOptionPane.ERROR_MESSAGE);
			alReadyInUseAddRobotMenu(addRobotActive);
		}else{
			alReadyInUseAddRobotMenu(addRobotActive);
			System.out.println("cheking addRobot Loadint message"+Language.get("addrtobo.loading.message"));
			LoadingFrame.getInstance().showLoadingFrame(Language.get("addrtobo.loading.message"));
			if(WebServicesManager.canConnectToConsultWebservices() && checkWebServicesCreator()){
				AddNewRobotPanel addRobot=new AddNewRobotPanel();
				addRobot.setVisible(true);
				addRobotActive=true;
				alReadyInUseAddRobotMenu(addRobotActive);
			}else{
				LoadingFrame.getInstance().hiddenLoadingFrame();
				JOptionPane.showMessageDialog(null, Language.get("addrobot.error.connection"),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
			
			
		}
	  }
	private static boolean checkWebServicesCreator(){
		URL webServicesCreator = CreatorRobotWebService.getWebServicesCreator();
		if(webServicesCreator!=null){
			System.out.println("consultando webservices"+webServicesCreator);
			return CreatorRobotWebService.existeConexion(webServicesCreator.toString());
		}else{
			webServicesCreator=Webservice.getUrl();
			System.out.println("consultando webservices"+webServicesCreator);
			return CreatorRobotWebService.existeConexion(webServicesCreator.toString());
		}
	}
	
	private static boolean checkWebServicesConsult(){
		URL webServicesConsult = ClientSRMHelperWebService.getWebServicesConsult();
		if(webServicesConsult!=null){
			System.out.println("comprobando webservices"+webServicesConsult);
			return ClientSRMHelperWebService.existeConexion(webServicesConsult.toString());
		}else{
			webServicesConsult=SisproRobotManagerHelperService.getUrl();
			System.out.println("comprobando webservices"+webServicesConsult);

			return ClientSRMHelperWebService.existeConexion(webServicesConsult.toString());
		}
	}
	
	
	public  void showDeleteRobot(){
		if(!AppExaminator.getInstalledApps().isEmpty()){
			if(deleteRobotActive){
				alReadyInUseDeleteMenu(deleteRobotActive);
				JOptionPane.showMessageDialog(null, Language.get("deleterobot.error"),"Error",JOptionPane.ERROR_MESSAGE);
			}else{
				
				alReadyInUseDeleteMenu(deleteRobotActive);
				if(checkWebServicesCreator()){
					DeleteRobotPanel deleterPanel=new DeleteRobotPanel();
					deleterPanel.setVisible(true);
					deleteRobotActive=true;
					alReadyInUseDeleteMenu(deleteRobotActive);
				}else{
					JOptionPane.showMessageDialog(null, "No es posible conectar con el servidor","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}else{
			JOptionPane.showMessageDialog(null, "No existen servicios a eliminar","Error",JOptionPane.ERROR_MESSAGE);

		}
	}
	
	
	public  void showConfiguractionSRM(){
		if(configurationRobotActive){
			alReadyInUseConfigurationMenu(configurationRobotActive);
		}else{
			LoadingFrame.getInstance().showLoadingFrame("configuration.loading.message");
			NewConfigurationPanel configPanel=new NewConfigurationPanel();
			LoadingFrame.getInstance().close();
			configurationRobotActive=true;
			alReadyInUseConfigurationMenu(configurationRobotActive);
		}
	}
	
	
	/**
	 * add to newServicesName to Interface only if not exist yet
	 * @param newServicesName
	 */
	public void addNewServices(String newServicesName){
		
		if(!gui.getMapRobots().containsKey(newServicesName)){
//			System.out.println("***no esta conetnido"+newServicesName);
			gui.addNewRobotUI(newServicesName);
			LOGGER.log(Level.INFO, "add new services "+newServicesName);
			AppInformation appData = AppExaminator.getAppData(newServicesName);
			if(appData!=null){
				if(RobotManager.runJarRobot(appData.getBotFile())){
					changeStatusServicesToActive(newServicesName);
				}
			}
		}else{
			LOGGER.log(Level.SEVERE, "cannot add the services "+ newServicesName);
		}
	}
	
	
	/**
	 * remove to newServicesName to Interface only if not exist yet
	 * @param newServicesName
	 */
	public void RemoveServices(String newServicesName){
		
		if(!gui.getMapRobots().containsKey(newServicesName)){
			System.out.println("***no esta conetnido"+newServicesName);
			gui.removeRobot(newServicesName);
		}else{
			System.out.println("**si esta contenido"+newServicesName);
		}
	}
	
	public  void showMessage(final String message ,final String title,final int messageType){
		if(SwingUtilities.isEventDispatchThread()){
			System.out.println("mostrando mensaje estando en el edt"+message);
			JOptionPane.showMessageDialog(null, message, title, messageType);
			
		}else{
			System.out.println("mostrando mensaje sin estar en edt"+message);
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					JOptionPane.showMessageDialog(null, message, title, messageType);

				}
			});
		}
	}
	
	
	public void  showFields(){
		gui.showFields();
	}
	
	
	
	public void disableAddRobot(){
		gui.disableAddRobotMenu();
	}
	
	public void enableAddRobot(){
		gui.enableAddRobotMenu();
	}
	
	public void disableConfigurationRobot(){
		gui.disableConfigurationRobotMenu();
	}
	
	public void enableConfigurationRobot(){
		gui.enableConfigurationRobotMenu();
	}
	
	public void enableDeleteRobot(){
		gui.enableDeleteRobotMenu();
	}
	
	public void disableDeleteRobot(){
		gui.disableDeleteRobotMenu();
	}
	
	public void alReadyInUseAddRobotMenu(boolean inUse){
		if(inUse){
			disableAddRobot();
		}else{
			enableAddRobot();
		}
		addRobotActive=inUse;
		
	}
	
	public void alReadyInUseConfigurationMenu(boolean inUse){
		if(inUse){
			disableConfigurationRobot();
		}else{
			enableConfigurationRobot();
		}
		configurationRobotActive=inUse;
	}
	
	
	public void alReadyInUseDeleteMenu(boolean inUse){
		if(inUse){
			disableDeleteRobot();
		}else{
			enableDeleteRobot();
		}
		deleteRobotActive=inUse;
	}
	
	
	
	  
}
