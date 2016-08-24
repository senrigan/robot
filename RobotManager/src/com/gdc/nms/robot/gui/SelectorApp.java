package com.gdc.nms.robot.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gdc.nms.robot.util.AppExaminator;
import com.gdc.nms.robot.gui.auxiliar.LoadingFrame;
import com.gdc.nms.robot.util.ValidatorManagement;
import com.gdc.nms.robot.util.indexer.AppInformation;
import com.gdc.nms.robot.util.indexer.AppJsonObject;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class SelectorApp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<AppJsonObject> comboBox;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton cancelButton;
	private JButton continueButton;
	private JFrame mainFrame=this;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectorApp frame = new SelectorApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SelectorApp() {
		RobotManager.getSRMGuiManager().enableAddRobot();
		init();
		setApplicationNames();
//		setTestApplicatioNames();
		setButtonListener();
	}
	
//	public SelectorApp(boolean addFlujos){
//		if(addFlujos){
//			init();
//			setInstalledApplicationNames();
//			setButtonListenerAddFlujos();
//		}else{
//			new SelectorApp();
//		}
//	}
	
	

	
	
	private  void init(){
		setResizable(false);
		setTitle("A\u00F1adir Robot");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450,170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 144, 144, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 17, 19, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblNewLabel_1 = new JLabel("Selecciona La Aplicacion que deseas a\u00F1adir \r\n");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridheight = 2;
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		lblNewLabel = new JLabel("Application");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 4;
		contentPane.add(comboBox, gbc_comboBox);
		
		cancelButton = new JButton("Cancelar");
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
		gbc_cancelButton.gridx = 1;
		gbc_cancelButton.gridy = 5;
		contentPane.add(cancelButton, gbc_cancelButton);
		
		continueButton = new JButton("Continuar");
		GridBagConstraints gbc_continueButton = new GridBagConstraints();
		gbc_continueButton.insets = new Insets(0, 0, 5, 5);
		gbc_continueButton.gridx = 2;
		gbc_continueButton.gridy = 5;
		contentPane.add(continueButton, gbc_continueButton);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void setInstalledApplicationNames(){
		ArrayList<AppInformation> installedApps = AppExaminator.getInstalledApps();
		for (AppInformation appInformation : installedApps) {
			AppJsonObject jsonApp=new AppJsonObject();
			jsonApp.setAlias(appInformation.getAlias());
			jsonApp.setId(appInformation.getIdRobot());
			comboBox.addItem(jsonApp);
		}
		
	}
	
	private void setApplicationNames(){
		ArrayList<AppJsonObject> appsName = ValidatorManagement.getAppsName();
		System.out.println("appsname"+appsName);
		Alias aliasComp=new Alias();
		java.util.Collections.sort(appsName,aliasComp);
		for (AppJsonObject appJsonObject : appsName) {
//			String alias = appJsonObject.getAlias();
			comboBox.addItem(appJsonObject);
			System.out.println("alias appname"+appJsonObject.getAlias()+" id"+appJsonObject.getId());
		}
	}
	
	
	
	
	
	private void setButtonListener(){
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindows();
			}
		});
		
		continueButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AppJsonObject selectedItem = (AppJsonObject) comboBox.getSelectedItem();
				LoadingFrame loading=new LoadingFrame();
				System.out.println("se selecciono la app"+selectedItem.getAlias()+"id"+selectedItem.getId());
				DateSelectorPanel dateSelector=new DateSelectorPanel(selectedItem);
				loading.close();
				closeWindows();
			}
		});
	}
	
	
	private void closeWindows(){
		this.dispose();
		RobotManager.getSRMGuiManager().enableAddRobot();
	}
	
	
	
	
	private class Alias implements Comparator<AppJsonObject>{

		@Override
		public int compare(AppJsonObject o1, AppJsonObject o2) {
			String alias1 = o1.getAlias();
			String alias2 = o2.getAlias();
			
			return alias1.compareTo(alias2);
		}
		
	}
	
	
	

	
	
	

}
