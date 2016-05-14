package com.gdc.nms.robot.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

import javax.lang.model.util.Elements;
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
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import com.gdc.nms.robot.gui.tree.Element;
import com.gdc.nms.robot.gui.tree.TreeListener;
import com.gdc.nms.robot.gui.tree.TreeModelElements;
import com.gdc.nms.robot.util.AppExaminator;
import com.gdc.nms.robot.util.Constants;
import com.gdc.nms.robot.util.ValidatorManagement;
import com.gdc.nms.robot.util.indexer.AppInformation;
import com.gdc.nms.robot.util.jade.InitPlataform;
import com.gdc.nms.robot.util.jade.SRMAgentManager;
import com.gdc.nms.robot.util.registry.CommandExecutor;

import jade.core.AID;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class RobotManagerGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTree appTree;
	private JPanel panelInfo;
	private JTextArea textArea;
	private JButton startButton;
	private JButton stopButton;
	private JScrollPane scrollPane_1;
	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private JMenuBar menuBar;
	private TreeListener listenerTree;
	private TreeModelElements model;
	private Element root;
	private Element runningNode;
	private Element notRunningNode;

	public RobotManagerGui() {
		super("SisproRobotManager");
		
		initComponents();
		
		// JMenuItem mnNewMenu = new JMenuItem("Actualizar Robot");
		// mnAr.add(mnNewMenu);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		setNodesParent();
//		runTask();
		expandAll();
		
	}
	
	private void initComponents(){
		model = new TreeModelElements(getDataForTree());
		appTree = new JTree(model);
		appTree.setEditable(false);
		listenerTree = new TreeListener(RobotManagerGui.this, appTree);
		appTree.addTreeSelectionListener(listenerTree);
		scrollPane_1 = new JScrollPane(appTree);
		root = (Element) model.getRoot();
		

		
		panelInfo = new JPanel();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, scrollPane_1, panelInfo);
		GridBagLayout gbl_panelInfo = new GridBagLayout();
		gbl_panelInfo.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelInfo.rowHeights = new int[] { 0, 171, 0, 0 };
		gbl_panelInfo.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panelInfo.rowWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		panelInfo.setLayout(gbl_panelInfo);
		textArea = new JTextArea();
		textArea.setOpaque(false);
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);
		scrollPane.getViewport().setOpaque(false);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panelInfo.add(scrollPane, gbc_scrollPane);

		// panel.add(textPane);

		startButton = new JButton("Run Robot");
		startButton.setEnabled(false);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		panelInfo.add(startButton, gbc_btnNewButton);

		stopButton = new JButton("Stop Robot");
		stopButton.setEnabled(false);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 2;
		panelInfo.add(stopButton, gbc_btnNewButton_1);
		getContentPane().add(splitPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 480);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAr = new JMenu("Archivo");
		menuBar.add(mnAr);
		setButtonsListeners();
		JMenuItem addRobotMenu = new JMenuItem("Agregar Robot");
		JMenuItem updateMenu = new JMenuItem("Actualizar Robots");
		JMenuItem deleteMenu=new JMenuItem("Eliminar Robots");
		
		addRobotMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {

							SelectorApp selector = new SelectorApp();
							selector.setVisible(true);

					}
				});

			}
		});
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
		
		deleteMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater( new Runnable() {
					public void run() {
						DeleteRobotPanel deleterPanel=new DeleteRobotPanel();
						deleterPanel.setVisible(true);
						
					}
				});
			}
		});
		
		mnAr.add(updateMenu);
		mnAr.add(addRobotMenu);
		mnAr.add(deleteMenu);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setButtonsListeners() {
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				Element element = (Element) appTree.getLastSelectedPathComponent();
//				
//				
//				AppInformation appinfo = element.getAppinfo();
//				
//				long idRobot = appinfo.getIdRobot();
//				long idApp=appinfo.getIdApp();
//				LoadingFrame loading=new LoadingFrame();
//				if (RobotManager.runRobotWithGui(idRobot,idApp)) {
//					loading.close();
//					enableButton(ButtonType.START, false);
//					JOptionPane.showMessageDialog(null, "el robot se inicio correctamente", "Info",
//							JOptionPane.INFORMATION_MESSAGE);
//					enableButton(ButtonType.STOP, false);
//					removeRegistryNotRunning(idRobot);
//					registryRunningRobot(idRobot);
//				} else {
//					loading.close();
//					JOptionPane.showMessageDialog(null, "No es Posible Iniciar al Robot en este momento", "Error",
//							JOptionPane.ERROR_MESSAGE);
//					enableButton(ButtonType.START, false);
//
//				}
				System.out.println("obteniuendo robost registrados");
				HashMap<String, AID> robotRegister = InitPlataform.getRobotRegister();
				Set<String> keySet = robotRegister.keySet();
				System.out.println("robots registrados"+keySet);
				SRMAgentManager agentManager = InitPlataform.getAgentManager();
				for (String string : keySet) {
					AID aid = robotRegister.get(string);
					agentManager.stopAgent(aid);
				}
			}
		});

		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Element element = (Element) appTree.getLastSelectedPathComponent();
				AppInformation appinfo = element.getAppinfo();
				long idRobot = appinfo.getIdRobot();
				LoadingFrame loading=new LoadingFrame();
				try {
					RobotManager.stopRobot(idRobot);
					loading.close();
					enableButton(ButtonType.STOP, false);

					JOptionPane.showMessageDialog(null, "El robot se detuvo correctamente", "Error",
							JOptionPane.INFORMATION_MESSAGE);
					enableButton(ButtonType.START, false);

					setInformation(" ");
					removeRegistryRunningRobot(idRobot);
					registryStopedRobot(idRobot);
				} catch (Exception e1) {
					loading.close();
					JOptionPane.showMessageDialog(null, "No es Posible Detener al Robot en este momento", "Error",
							JOptionPane.ERROR_MESSAGE);
					enableButton(ButtonType.STOP, true);

					e1.printStackTrace();
				}
			}
		});
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
	
	
	
	public  void UpdateTree(Set<String> runningApp){
		System.out.println("running app with updatetree"+runningApp);
		HashMap<String, AppInformation> installedAppsMap = AppExaminator.getInstalledAppsMap();
		System.out.println("installer apps");
		Element elementRun = new Element("En Ejecucion");
		Element elementStop=new Element("Detenidos");
		Set<String> keySet = installedAppsMap.keySet();
		for (String string : keySet) {
			AppInformation appInformation2 = installedAppsMap.get(string);
			if(runningApp.contains(string)){
				Element elementNode=new Element(appInformation2.getAppName());
				elementNode.setAppinfo(appInformation2);
				elementNode.setStopAble(true);
				elementRun.add(elementNode);
				runningApp.remove(string);
			}else{
				
				Element elementN=new Element(appInformation2.getAppName());
				elementN.setAppinfo(appInformation2);
				elementN.setStopAble(false);
				elementStop.add(elementN);
			}
//			for (String key:runningApp) {
//				AppInformation appInformation = installedAppsMap.get(key);
//				if(appInformation!=null){
//				}else{
//				}
//			}
		}
		Element elementTree = new Element("Aplicacion");
		System.out.println("running apps "+runningApp);
		if(!runningApp.isEmpty()){
			Element unknowElement=new Element("Desconocidos");
			for (String string : runningApp) {
				Element elementUnknow=new Element(string);
				elementUnknow.setAppinfo(new AppInformation());
				elementUnknow.setStopAble(true);
				unknowElement.add(elementUnknow);
				elementTree.add(unknowElement);
			}
			
		}
		
		elementTree.add(elementRun);
		elementTree.add(elementStop);
		updateTree(elementTree);
	
	}
	
	
	
	private  void updateTree(Element dataElement) {
		try {
			
			if(RobotManager.isRunningScan()){
				appTree.removeTreeSelectionListener(listenerTree);
				appTree.setModel(new TreeModelElements(dataElement));
				appTree.addTreeSelectionListener(listenerTree);
				expandAll();
				enableButton(ButtonType.START, false);
				enableButton(ButtonType.STOP, false);
			}
			Thread.sleep(10000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void expandAll() {
		int row = 0;
		while (row < appTree.getRowCount()) {
			appTree.expandRow(row);
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

	private void setNodesParent() {
		@SuppressWarnings("unchecked")
		Enumeration<Element> children = root.children();
		while (children.hasMoreElements()) {
			Element object = children.nextElement();
			if (object.toString().equals("Running")) {
				runningNode = object;
			} else if (object.toString().equals("Not Running")) {
				notRunningNode = object;
			}
		}
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		Element element = new Element("En ejecucion");
		ArrayList<AppInformation> runningApps = AppExaminator.getRunningApps();
		for (AppInformation appInformation : runningApps) {
			Element elementNode = new Element(appInformation.getAppName());
			elementNode.setAppinfo(appInformation);
			elementNode.setStopAble(true);
			element.add(elementNode);

		}
		return element;
	}

	private  Element getElementNotRunning() {
		Element element = new Element("Detenidos");
		ArrayList<AppInformation> notRunningApps = AppExaminator.getNotRunnigApps();
		for (AppInformation appInformation : notRunningApps) {
			Element elementNode = new Element(appInformation.getAppName());
			elementNode.setAppinfo(appInformation);
			elementNode.setStopAble(false);
			element.add(elementNode);
		}
		return element;
	}

	public void setInformation(String info) {
		this.textArea.setText(info);
	}

	public void enableButton(ButtonType type, boolean status) {
		switch (type) {
		case START:
			startButton.setEnabled(status);
			break;
		case STOP:
			stopButton.setEnabled(status);
			break;
		}
	}

	public enum ButtonType {
		START, STOP
	}
}
