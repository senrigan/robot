package com.gdc.nms.robot.gui.tree.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.gdc.nms.robot.gui.InfoWindows;
import com.gdc.nms.robot.gui.newInterface.ButtonListener;
import com.gdc.nms.robot.gui.util.SRMGUI;
import com.gdc.nms.robot.util.AppExaminator;
import com.gdc.nms.robot.util.indexer.AppInformation;

public class InterfaceManager {
	private SRMGUI gui;
	public InterfaceManager(SRMGUI gui){
		this.gui=gui;
	}
	
	
	public void loadAllRobots(){
		ArrayList<AppInformation> installedApps = AppExaminator.getInstalledApps();
		for (AppInformation appInformation : installedApps) {
			System.out.println("add  "+appInformation.getAppName());
			gui.addNewRobotUI(appInformation.getAppName());
			
		}
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
				// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
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
	  
	  
	  
}
