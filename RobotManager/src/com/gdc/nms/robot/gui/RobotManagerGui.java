package com.gdc.nms.robot.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.log4j.Logger;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import com.gdc.nms.robot.gui.auxiliar.LoadingFrame;
import com.gdc.nms.robot.gui.tree.Element;
//import com.gdc.nms.robot.gui.tree.TreeListener;
import com.gdc.nms.robot.gui.tree.TreeModelElements;
import com.gdc.nms.robot.gui.tree.test.RobotJTree;
import com.gdc.nms.robot.gui.tree.test.TreeDynamic;
import com.gdc.nms.robot.gui.util.process.JavaProcess;
import com.gdc.nms.robot.gui.util.process.JavaProcessInfo;
import com.gdc.nms.robot.util.AppExaminator;
import com.gdc.nms.robot.util.Constants;
import com.gdc.nms.robot.util.indexer.AppInformation;
import com.gdc.nms.robot.util.jade.InitPlataform;
import com.gdc.nms.robot.util.jade.SRMAgentManager;
import com.gdc.nms.robot.util.registry.CommandExecutor;
import com.gdc.robothelper.webservice.ClientSRMHelperWebService;
import com.gdc.robothelper.webservice.SisproRobotManagerHelperService;
import com.gdc.robothelper.webservice.robot.CreatorRobotWebService;
import com.gdc.robothelper.webservice.robot.Webservice;

import jade.core.AID;
import sun.tools.tree.SynchronizedStatement;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class RobotManagerGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RobotJTree dinamicTree;
	private JTree appTree;
	private JPanel panelInfo;
	private JTextArea textArea;
	private JButton startButton;
	private JButton stopButton;
	private JScrollPane scrollPane_1;
	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private JMenuBar menuBar;
//	private TreeListener listenerTree;
	private TreeModelElements model;
	private Element root;
	private JButton logButton;
	private JButton getRobotDataButton;
	private JMenuItem addRobotMenu;
	private JMenuItem deleteMenu;
	private JMenuItem updateMenu;
	private JMenuItem wsConsultMenu;
	private JMenuItem addFlujoMenu;
	private JMenu configMenu;
	private JMenu mnAr;
	private JMenuItem ubicationMenu;
	private JMenuItem wsCreactionMenu;
	private static final Logger LOGGER=Logger.getLogger(RobotManagerGui.class);
	private static int parche=0;

	

	public RobotManagerGui() {
		super("SisproRobotManager");
		LOGGER.addAppender(RobotManager.logAppender);
		
		initComponents();
		
		// JMenuItem mnNewMenu = new JMenuItem("Actualizar Robot");
		// mnAr.add(mnNewMenu);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
//		setNodesParent();
//		runTask();
		expandAll();
		
	}
	
	private void initComponents(){
//		model = new TreeModelElements(getDataForTree());
		dinamicTree=new RobotJTree();
//		listenerTree = new TreeListener(RobotManagerGui.this, dinamicTree.getTree().getTree());
//		dinamicTree.getTree().getTree().addTreeSelectionListener(listenerTree);
		Element elementRunning = getElementRunning();
		Enumeration<?> children = elementRunning.children();
		System.out.println("childen elementRunning"+children);
		while (children.hasMoreElements()) {
			Element object = (Element) children.nextElement();
			Element elem=new Element(object.toString());
			elem.setAppinfo(object.getAppinfo());
			elem.setStopAble(object.isStopAble());
			elem.setUserObject(object.getUserObject());
			elem.setAllowsChildren(object.getAllowsChildren());
			
			System.out.println("childrne Notrunning "+elem.getUserObject().toString());

			dinamicTree.addToRun(elem);
		}
//		dinamicTree.addToRun(getElementRunning());
		Element elementNotRunning = getElementNotRunning();
		System.out.println("childen elementNotRunning"+children);
		children=elementNotRunning.children();
		System.out.println();
		while (children.hasMoreElements()) {
			Element object = (Element) children.nextElement();
			Element elem=new Element(object.toString());
			elem.setAppinfo(object.getAppinfo());
			elem.setStopAble(object.isStopAble());
			elem.setUserObject(object.getUserObject());
			elem.setAllowsChildren(object.getAllowsChildren());
			
			System.out.println("childrne Notrunning "+elem.getUserObject().toString());

			dinamicTree.addToStop(elem);
		}
//		dinamicTree.addToStop(getElementNotRunning());
//		appTree = new JTree(model);
//		appTree.setEditable(false);
//		listenerTree = new TreeListener(RobotManagerGui.this, appTree);
//		appTree.addTreeSelectionListener(listenerTree);
		scrollPane_1 = new JScrollPane(dinamicTree.getTree().getTree());
//		root = (Element) model.getRoot();
		

		
		panelInfo = new JPanel();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, scrollPane_1, panelInfo);
		GridBagLayout gbl_panelInfo = new GridBagLayout();
		gbl_panelInfo.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelInfo.rowHeights = new int[] { 0, 171, 0, 0, 0 };
		gbl_panelInfo.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panelInfo.rowWeights = new double[] { 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		panelInfo.setLayout(gbl_panelInfo);
		textArea = new JTextArea();
		textArea.setOpaque(false);
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);
		scrollPane.getViewport().setOpaque(false);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panelInfo.add(scrollPane, gbc_scrollPane);
		
		getRobotDataButton = new JButton("Obtener Datos Robot");
		GridBagConstraints gbc_getRobotDataButton = new GridBagConstraints();
		gbc_getRobotDataButton.insets = new Insets(0, 0, 5, 5);
		gbc_getRobotDataButton.gridx = 0;
		gbc_getRobotDataButton.gridy = 2;
		panelInfo.add(getRobotDataButton, gbc_getRobotDataButton);
		
		logButton = new JButton("Logs");
		GridBagConstraints gbc_logButton = new GridBagConstraints();
		gbc_logButton.insets = new Insets(0, 0, 5, 0);
		gbc_logButton.gridx = 1;
		gbc_logButton.gridy = 2;
		panelInfo.add(logButton, gbc_logButton);
		logButton.setVisible(false);

		// panel.add(textPane);

		startButton = new JButton("Run Robot");
		startButton.setEnabled(false);
		GridBagConstraints gbc_startButton = new GridBagConstraints();
		gbc_startButton.insets = new Insets(0, 0, 0, 5);
		gbc_startButton.gridx = 0;
		gbc_startButton.gridy = 3;
		panelInfo.add(startButton, gbc_startButton);

		stopButton = new JButton("Stop Robot");
		stopButton.setEnabled(false);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 3;
		panelInfo.add(stopButton, gbc_btnNewButton_1);
		getContentPane().add(splitPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 480);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnAr = new JMenu("Archivo");
		setButtonsListeners();
		addRobotMenu = new JMenuItem("Agregar Robot");
		addFlujoMenu =new JMenuItem("Agregar flujo");
		updateMenu = new JMenuItem("Actualizar Robots");
		deleteMenu=new JMenuItem("Eliminar Robots");
		wsConsultMenu=new JMenuItem("WS Consulta");
		configMenu=new JMenu("Configuracion");
		
	
		
		ubicationMenu = new JMenuItem("Configurar Ubicacion");
		configMenu.add(ubicationMenu);
		configMenu.add(wsConsultMenu);
		mnAr.add(updateMenu);
		mnAr.add(addRobotMenu);
//		mnAr.add(addFlujoMenu);
		mnAr.add(deleteMenu);
		menuBar.add(mnAr);
		menuBar.add(configMenu);
		
		wsCreactionMenu = new JMenuItem("WS Creacion");
		configMenu.add(wsCreactionMenu);
		configMenuBar();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

		
	private void configMenuBar(){
		
		addRobotMenuAction();
		updateMenuAction();
		deleteMenuAction();
		configMenuAction();
	}
	
	private void addFlujoMenuAction(){
		addFlujoMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater( new Runnable() {
					public void run() {
						//initSelectorWindowsddFlujos();
					}
				});
			}
			
		});
			
	}

	private void addRobotMenuAction(){
		addRobotMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						System.out.println("servicio de consulta"+checkWebServicesConsult());
						System.out.println("consultando servicio de creacion"+checkWebServicesCreator());
							if(checkWebServicesConsult() && checkWebServicesCreator()){
								SelectorApp selector = new SelectorApp();
								selector.setVisible(true);

							}else{
								JOptionPane.showMessageDialog(null, "No es posible conectar con el servidor","Error",JOptionPane.ERROR_MESSAGE);
							}
							
					}
				});

			}
		});
	}
	
	public RobotJTree getJtreManager(){
		return dinamicTree;
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
	
	
	public static void main(String[] args) {
		boolean checkWebServicesConsult = RobotManagerGui.checkWebServicesConsult();
		System.out.println(checkWebServicesConsult);
		boolean checkWebServicesCreator = RobotManagerGui.checkWebServicesCreator();
		System.out.println(checkWebServicesCreator);
		
	}
	private void updateMenuAction(){
		updateMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Actualizar Los Robot Detendra todos los robots y no podran Reportar mientras Se realiza la ejecucion ", "Warning",
				        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
				    SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							UpadaterRobotPanel updater=new UpadaterRobotPanel();
							updater.setVisible(true);
						}
					});
				} else {
				}
			}
		});
	}
	private void deleteMenuAction(){
		deleteMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater( new Runnable() {
					public void run() {
						if(checkWebServicesCreator()){
							DeleteRobotPanel deleterPanel=new DeleteRobotPanel();
							deleterPanel.setVisible(true);							
						}else{
							JOptionPane.showMessageDialog(null, "No es posible conectar con el servidor","Error",JOptionPane.ERROR_MESSAGE);
						}
						
						
					}
				});
			}
		});
	}
	private void configMenuAction(){
//		final RegisrtryEditorManager reg=new RegisrtryEditorManager();
		
		ubicationMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new  Runnable() {
					public void run() {
//						reg.showUbicationRegistry();
						
					}
				});
			}
		});
		
		wsConsultMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
//						reg.showWebServiceConsult();
					}
				});
			}
		});
		
		wsCreactionMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater( new Runnable() {
					public void run() {
//						reg.showWebServicesCreator();
					}
				});
			}
		});
	}
	public static void  showMessage(String message){
		JOptionPane.showMessageDialog(null, message, "Info",
				JOptionPane.INFORMATION_MESSAGE);
	}
	private void setButtonsListeners() {
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Element element = (Element) dinamicTree.getSelectdNode();
				
				
				AppInformation appinfo = element.getAppinfo();
				
				long idRobot = appinfo.getIdRobot();
				long idApp=appinfo.getIdApp();
				LoadingFrame loading=LoadingFrame.getInstance();
				if (RobotManager.runRobotWithGui(idRobot,idApp)) {
					loading.close();
					
					HashMap<String, AID> robotRegister = InitPlataform.getRobotRegister();
					JOptionPane.showMessageDialog(null, "Espere unos minutos a que el robot inicie", "Info",
							JOptionPane.INFORMATION_MESSAGE);
					enableButton(ButtonType.STOP, false);
					enableButton(ButtonType.START, false);

//					if(robotRegister.containsKey(appinfo.getAlias())){
//						
//						JOptionPane.showMessageDialog(null, "el robot se inicio correctamente", "Info",
//								JOptionPane.INFORMATION_MESSAGE);
//						removeRegistryNotRunning(idRobot);
//						registryRunningRobot(idRobot);
//					}else{
//						System.out.println("el robot no se ha registrado todavia");
//					}
				} else {
					loading.close();
					JOptionPane.showMessageDialog(null, "No es Posible Iniciar al Robot en este momento", "Error",
							JOptionPane.ERROR_MESSAGE);
					enableButton(ButtonType.START, false);

				}
//				System.out.println("obteniuendo robost registrados");
//				HashMap<String, AID> robotRegister = InitPlataform.getRobotRegister();
//				Set<String> keySet = robotRegister.keySet();
//				System.out.println("robots registrados"+keySet);
//				SRMAgentManager agentManager = InitPlataform.getAgentManager();
//				for (String string : keySet) {
//					AID aid = robotRegister.get(string);
//					agentManager.stopAgent(aid);
//				}
			}
		});

		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("se activo stop button");
				Element element = (Element) dinamicTree.getSelectdNode();
				AppInformation appinfo = element.getAppinfo();
//				long idRobot = appinfo.getIdRobot();
				LoadingFrame loading=LoadingFrame.getInstance();
				try {
//					AID aid = InitPlataform.getRobotRegister().get(appinfo.getAppName());
					boolean stopAgent;
//					if(aid!=null){
//						stopAgent = SRMAgentManager.stopAgent(aid);
//						
//					}else{
//						stopAgent=false;
//					}
					stopAgent=false;//parchie para que mate el processo
					if(stopAgent){
	//					RobotManager.stopRobot(idRobot);
						System.out.println("intentando cumunciarse con el agente para detenerlos");
							loading.close();
							enableButton(ButtonType.STOP, false);
							enableButton(ButtonType.START, false);
							
							
							JOptionPane.showMessageDialog(null, "Espere unos minutos mientras el robot es detenido", "Error",
									JOptionPane.INFORMATION_MESSAGE);
							
	//					setInformation(" ");
	//					removeRegistryRunningRobot(idRobot);
						
					}else{
						System.out.println("deteniendo robot de forma bruta");
//						boolean runningByLockFile = AppExaminator.isRunningByLockFile(appinfo);
//						System.out.println("is runningbyLockFule"+runningByLockFile);
						Object selectdNode = dinamicTree.getSelectdNode();

						if(appinfo.isServicesRunning()){
							
							String appPid = AppExaminator.readPidFile(appinfo.getFolderPath());
							long pid=Long.parseLong(appPid);
							if(JavaProcess.isValidJavaProceesId(pid)){
								System.out.println("pid is valid java process"+pid);
								if(RobotManager.stopJar(pid)){
									JOptionPane.showMessageDialog(null, "El robot fue detenido exitosamente", "Error",
											JOptionPane.INFORMATION_MESSAGE);
									System.out.println("dinamicteee getseletednode"+dinamicTree.getSelectdNode());
//									dinamicTree.getTree().clearSelection();
									dinamicTree.addToStop(selectdNode);
									System.out.println("robot detenido exitosamente");

								}else{
									System.out.println("no fue posible detener el robot");

									JOptionPane.showMessageDialog(null, "No es Posible Detener al Robot en este momento", "Error",
											JOptionPane.ERROR_MESSAGE);
								}
							}else{
								System.out.println("searchinng services in java oprocess");
								ArrayList<JavaProcessInfo> allJavaRobotProces = JavaProcess.getALLJavaRobotProces();
								for (JavaProcessInfo javaProcessInfo : allJavaRobotProces) {
									String commandLine = javaProcessInfo.getCommandLine();
									System.out.println("commandlinde"+commandLine);
									if(commandLine.contains(appinfo.getAppName()) ){
										long processid = javaProcessInfo.getProcessid();
										if(RobotManager.stopJar(processid)){
											System.out.println("robot detenido exitosamente");
//											dinamicTree.getTree().clearSelection();
											dinamicTree.addToStop(selectdNode);
											JOptionPane.showMessageDialog(null, "El robot fue detenido exitosamente", "Error",
													JOptionPane.INFORMATION_MESSAGE);
										}else{
											System.out.println("no fue posible dteenr el robot");
											JOptionPane.showMessageDialog(null, "No es Posible Detener al Robot en este momento", "Error",
													JOptionPane.ERROR_MESSAGE);
										}
									}
								}
							}
						}else{
							System.out.println("el servicio no esta corriendo");
						}
							
//						else{
//							JOptionPane.showMessageDialog(null, "El robot no esta en ejecucion", "Error",
//									JOptionPane.ERROR_MESSAGE);
//						}
					}
//					registryStopedRobot(idRobot);
				} catch (Exception e1) {
					loading.close();
//					JOptionPane.showMessageDialog(null, "No es Posible Detener al Robot en este momento", "Error",
//							JOptionPane.ERROR_MESSAGE);
//					enableButton(ButtonType.STOP, true);

					e1.printStackTrace();
					LOGGER.error("excepcion ", e1);

				}
			}
		});
		
		logButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new  Runnable() {
					public void run() {
						AppInformation appinfo = getSelectedAppInformation();
						Path path = Paths.get(appinfo.getFolderPath());
						path=path.resolve("robot.log");
						String logApp = readLogFile(path);
						InfoWindows infoWin=new InfoWindows("logs "+appinfo.getAlias(),logApp); 
						
					}
				});
			}
		});
		
		
		getRobotDataButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						AppInformation selectedAppInformation = getSelectedAppInformation();
						String agentInfo = SRMAgentManager.getAgentInfo(selectedAppInformation);
						System.out.println("Agent info"+agentInfo);
						setInformation(agentInfo);
						
					}
				});
			}
		});
	}
	
	
	public AppInformation getSelectedAppInformation(){
		Element element = (Element) dinamicTree.getSelectdNode();
		AppInformation appinfo = element.getAppinfo();
		return appinfo;
	}
	
	
	public void setLogVisible(boolean visible){
		logButton.setVisible(visible);
	}
	
	
	private String readLogFile(Path logPath){
		String text="";
		try (BufferedReader br = new BufferedReader(new FileReader(logPath.toFile())))
		{

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				text+=sCurrentLine+"\n";
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);
		} 
		return text;
	}

	private void registryStopedRobot(long idRobot) {
		try {
			String registry = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "robotnotRun",
					"REG_SZ");
			if (registry.endsWith(",")) {
				registry = registry.substring(0, registry.length() - 1);
			}
			String[] split = registry.split(",");
			boolean isContained = false;
			for (int i = 0; i < split.length; i++) {
				if (split[i].length() > 0) {
					long stopedid = Long.parseLong(split[i]);
					if (idRobot == stopedid) {
						isContained = true;
					}

				}
			}
			if (!isContained) {
				registry = registry + "," + idRobot;
			}
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "robotnotRun", registry);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
	}

	private void registryRunningRobot(long idRobot) {
		try {
			String registry = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "robotmustRun",
					"REG_SZ");
			if (registry.endsWith(",")) {
				registry = registry.substring(0, registry.length() - 1);
			}
			String[] split = registry.split(",");
			boolean isContained = false;
			for (int i = 0; i < split.length; i++) {
				if (split[i].length() > 0) {
					long stopedid = Long.parseLong(split[i]);
					if (idRobot == stopedid) {
						isContained = true;
					}

				}
			}
			if (!isContained) {
				registry = registry + "," + idRobot;
			}
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "robotmustRun", registry);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
	}

	private void removeRegistryRunningRobot(long idRobot) {
		try {
			String registry = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "robotmustRun",
					"REG_SZ");
			if (registry.endsWith(",")) {
				registry = registry.substring(0, registry.length() - 1);
			}

			String[] split = registry.split(",");
			boolean isContained = false;
			String newRegistry = "";
			for (int i = 0; i < split.length; i++) {
				if (split[i].length() > 0) {
					long stopedid = Long.parseLong(split[i]);

					if (idRobot == stopedid) {
						isContained = true;
					} else {
						newRegistry += stopedid + ",";
					}

				}
			}
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "robotmustRun", newRegistry);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
	}

	private void removeRegistryNotRunning(long idRobot) {
		try {
			String registry = CommandExecutor.readRegistrySpecificRegistry(Constants.LOCALREGISTRY, "robotnotRun",
					"REG_SZ");
			if (registry.endsWith(",")) {
				registry = registry.substring(0, registry.length() - 1);
			}

			String[] split = registry.split(",");
			boolean isContained = false;
			String newRegistry = "";
			for (int i = 0; i < split.length; i++) {
				if (split[i].length() > 0) {

					long stopedid = Long.parseLong(split[i]);

					if (idRobot == stopedid) {
						isContained = true;
					} else {
						newRegistry += stopedid + ",";
					}
				}
			}
			CommandExecutor.addRegistryWindows(Constants.LOCALREGISTRY, "robotnotRun", newRegistry);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
	}

//	public void runTask() {
//		// Timer timer=new Timer();
//		// TimerTask task=new TimerTask() {
//		//
//		// @Override
//		// public void run() {
//		// try{
//		// updateNodes(runningNode, getElementRunning());
//		// updateNodes(notRunningNode, getElementNotRunning());
//		//
//		// }catch(Exception ex){
//		// ex.printStackTrace();
//		// }
//		//
//		// }
//		// };
//		// timer.schedule(task, 0,30000);
//		Thread hilo = new Thread(new Runnable() {
//			public void run() {
//				while (true) {
//					updateTree();
//				}
//			}
//		});
//		hilo.start();
//	}

//	private  void updateTree() {
//		try {
//
//			// appTree.clearSelection();
//			// appTree.setModel(new TreeModelElements());
//			if(RobotManager.isRunningScan()){
//				
//				appTree.removeTreeSelectionListener(listenerTree);
//				
//				appTree.setModel(new TreeModelElements(getDataForTree()));
//				appTree.addTreeSelectionListener(listenerTree);
//				// DefaultTreeModel model = (DefaultTreeModel)appTree.getModel();
//				// DefaultMutableTreeNode
//				// root=(DefaultMutableTreeNode)model.getRoot();
//				// root.add(new DefaultMutableTreeNode(getDataForTree()));
//				// model.reload();
//				
//				expandAll();
//				enableButton(ButtonType.START, false);
//				enableButton(ButtonType.STOP, false);
//				
//			}
//			Thread.sleep(10000);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public void changeRobotToRun(String simpleRobotName){
		DefaultMutableTreeNode robotNode = dinamicTree.getRobotNode(simpleRobotName);
		if(robotNode!=null){
			dinamicTree.changeRobotStopToRun(robotNode);
		}
	}
	
	public  void UpdateTree(Set<String> runningApp){
		Set<String > newRunning=new HashSet<String>();
		newRunning.addAll(runningApp);
		System.out.println("running app with updatetree"+newRunning);
		HashMap<String, AppInformation> installedAppsMap = AppExaminator.getInstalledAppsMap();
		System.out.println("installer apps");
		Element elementRun = new Element("En Ejecucion");
		Element elementStop=new Element("Detenidos");
		Set<String> keySet = installedAppsMap.keySet();
		System.out.println("keyset"+keySet);
		for (String string : keySet) {
			AppInformation appInformation2 = installedAppsMap.get(string);
			for (String string2 : newRunning) {
				if(parche>=1){
					System.out.println("si se encontro el elemento");
					Element elementNode=new Element(appInformation2.getAppName());
					elementNode.setAppinfo(appInformation2);
					elementNode.setStopAble(true);
					elementRun.add(elementNode);
					System.out.println("removiendo de corriendo");
					newRunning.remove(string);
					if(checkListToKill(appInformation2.getAlias())){
						elementNode.setStopAble(false);
						
					}
				}else{
					
					if(string2.equalsIgnoreCase(string)){
						System.out.println("si se encontro el elemento");
						Element elementNode=new Element(appInformation2.getAppName());
						elementNode.setAppinfo(appInformation2);
						elementNode.setStopAble(true);
						elementRun.add(elementNode);
						System.out.println("removiendo de corriendo");
						newRunning.remove(string);
						if(checkListToKill(appInformation2.getAlias())){
							elementNode.setStopAble(false);
							
						}
					}else{
						System.out.println("descnonosco el elemento");
						Element elementN=new Element(appInformation2.getAppName());
						elementN.setAppinfo(appInformation2);
						elementN.setStopAble(false);
						elementStop.add(elementN);
					}
				}
				parche++;
			}
//			if(newRunning.contains(string)){
//				System.out.println("si se encontro el elemento");
//				Element elementNode=new Element(appInformation2.getAppName());
//				elementNode.setAppinfo(appInformation2);
//				elementNode.setStopAble(true);
//				elementRun.add(elementNode);
//				System.out.println("removiendo de corriendo");
//				newRunning.remove(string);
//				if(checkListToKill(appInformation2.getAlias())){
//					elementNode.setStopAble(false);
//					
//				}
//			}else{
//				System.out.println("descnonosco el elemento");
//				Element elementN=new Element(appInformation2.getAppName());
//				elementN.setAppinfo(appInformation2);
//				elementN.setStopAble(false);
//				elementStop.add(elementN);
//			}
//			for (String key:runningApp) {
//				AppInformation appInformation = installedAppsMap.get(key);
//				if(appInformation!=null){
//				}else{
//				}
//			}
		}
		Element elementTree = new Element("Aplicacion");
		System.out.println("running apps "+runningApp);
		if(!newRunning.isEmpty()){
			Element unknowElement=new Element("Desconocidos");
			for (String string : newRunning) {
				AppInformation appInformation2 = installedAppsMap.get(string);
				Element elementNode=new Element(appInformation2.getAppName());
				elementNode.setAppinfo(appInformation2);
				elementNode.setStopAble(true);
				elementRun.add(elementNode);
//				Element elementUnknow=new Element(string);
//				elementUnknow.setAppinfo(new AppInformation());
//				elementUnknow.setStopAble(true);
//				unknowElement.add(elementUnknow);
//				elementTree.add(unknowElement);
			}
			
		}
		
		
		elementTree.add(elementRun);
		elementTree.add(elementStop);
//		updateTree(elementTree);
	
	}
	
	
	private boolean checkListToKill(String app){
		if(InitPlataform.getMapToKill()!=null)
			return InitPlataform.getMapToKill().containsKey(app);
		return false;
	}
	
	
//	private  void updateTree(Element dataElement) {
//		try {
//			
////			if(RobotManager.isRunningScan()){
//				appTree.removeTreeSelectionListener(listenerTree);
//				appTree.setModel(new TreeModelElements(dataElement));
//				appTree.addTreeSelectionListener(listenerTree);
//				expandAll();
//				enableButton(ButtonType.START, false);
//				enableButton(ButtonType.STOP, false);
////			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			LOGGER.error("excepcion ", e);
//
//		}
//	}

	private void expandAll() {
		int row = 0;
		
		while (row < dinamicTree.getTree().getTree().getRowCount()) {
			dinamicTree.getTree().getTree().expandRow(row);
			row++;
		}
	}

	@SuppressWarnings("unchecked")
	private void updateNodes(Element toUpdate, Element dataForUpdate) {
		Enumeration<Element> children = (Enumeration<Element>) dataForUpdate.children();
		Enumeration<Element> childrenNode = toUpdate.children();
		ArrayList<Element> elementsToAdd = new ArrayList<Element>();
		ArrayList<Element> foundElements = new ArrayList<Element>();
		while (children.hasMoreElements()) {
			Element object = children.nextElement();
			while (childrenNode.hasMoreElements()) {
				Element object2 = childrenNode.nextElement();
				if (object.toString().equals(object2.toString())) {
					if (elementsToAdd.contains(object2)) {
						elementsToAdd.remove(object2);
					}
					foundElements.add(object2);
					break;
				} else {
					// long index=runninNode.getIndex(object2);
					if (!elementsToAdd.contains(object2)) {
						elementsToAdd.add(object2);
					}
				}
			}
		}
		for (Element element : elementsToAdd) {
			if (!foundElements.contains(element)) {
				toUpdate.remove(element);

			}
		}
	}

//	private void setNodesParent() {
//		@SuppressWarnings("unchecked")
//		Enumeration<Element> children = root.children();
//		while (children.hasMoreElements()) {
//			Element object = children.nextElement();
//			if (object.toString().equals("Running")) {
//				runningNode = object;
//			} else if (object.toString().equals("Not Running")) {
//				notRunningNode = object;
//			}
//		}
//	}

	public  Element getDataForTree() {
//		Vector<Thread> hilos=new Vector<>();
		final CountDownLatch latch= new CountDownLatch(2);

		final Element elementTree = new Element("Aplicacion");
		Thread hilo2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				setName("hlo 2");
				
				elementTree.add(getElementRunning());
				latch.countDown();
			}
		},"hilo 2");
//		hilos.add(hilo2);
		hilo2.start();
		
		Thread hilo=new Thread(new Runnable() {
			
			@Override
			public void run() {
				elementTree.add(getElementNotRunning());
				latch.countDown();

			}
		},"hilo 1");
		hilo.start();
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
			LOGGER.error("excepcion ", e);

		}
//		for (Thread thread : hilos) {
//			try {
//				thread.join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}

		// System.out.println("childAt"+childAt.);
		return elementTree;

	}

	private  Element getElementRunning() {
		Element element = new Element("");
		ArrayList<AppInformation> runningApps = AppExaminator.getRunningApps();
		for (AppInformation appInformation : runningApps) {
			Element elementNode = new Element(appInformation.getAppName());
			elementNode.setAppinfo(appInformation);
			elementNode.setStopAble(true);
			element.add(elementNode);
			System.out.println("Running elementNode"+elementNode.toString());

		}
		return element;
	}

	private  Element getElementNotRunning() {
		Element element = new Element("");
		ArrayList<AppInformation> notRunningApps = AppExaminator.getNotRunnigApps();
		for (AppInformation appInformation : notRunningApps) {
			Element elementNode = new Element(appInformation.getAppName());
			elementNode.setAppinfo(appInformation);
			elementNode.setStopAble(false);
			element.add(elementNode);
			System.out.println("Running elementNode"+elementNode.toString());

		}
		return element;
	}
	
	public void  setInformation(String info){
		this.textArea.setText(info);
	}
	
	public void enableButton(ButtonType type,boolean status){
		switch(type){
		case START:
				startButton.setEnabled(status);
				break;
		case STOP:
				stopButton.setEnabled(status);
				break;
		case LOG:
				logButton.setEnabled(status);
				break;
		case DATA:
				getRobotDataButton.setEnabled(status);
		
		}
	}
	
	public enum ButtonType{
		START,STOP,LOG,DATA
	}

}