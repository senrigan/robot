package com.gdc.nms.robot.gui;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.gdc.nms.robot.gui.auxiliar.CheckBoxList;
import com.gdc.nms.robot.gui.auxiliar.LoadingFrame;
import com.gdc.nms.robot.gui.util.SRMGUI;
import com.gdc.nms.robot.util.AppExaminator;
import com.gdc.nms.robot.util.CreatorRobotManager;
import com.gdc.nms.robot.util.InfoRobotMaker;
import com.gdc.nms.robot.util.Language;
import com.gdc.nms.robot.util.ValidatorManagement;
import com.gdc.nms.robot.util.indexer.AppJsonObject;
import com.gdc.nms.robot.util.indexer.FlujoInformation;
import com.gdc.nms.robot.util.indexer.FlujoJsonObject;
import com.gdc.robothelper.webservice.WebServicesManager;

import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;

public class AddNewRobotPanel extends JFrame {

	private JPanel contentPane;
	private JComboBox listServices;
	private JLabel lblNewLabel;
	private JButton cancelButton;
	private JButton ContinueButton;
	private DateTimePicker dateTimePicker;
	public static Date spinnerDate;
	private JPanel extraPanel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField textField;
	private JButton btnNewButton;
	private DateTimePicker datePickerElement;
	private JButton searchButton;
	private JSpinner retries;
	private JComboBox<Integer> timeLapse;
	private AppJsonObject selectedItem;
	private JPanel flujosPanel;
	private JScrollPane scrollPane;
	private JPanel flujosContentPanel;
	private InfoRobotMaker infoRobotM;
	private ArrayList<FlujoJsonObject> flujosOfServcies;
	private CheckBoxList cbList;
	private LoadingFrame loading;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewRobotPanel frame = new AddNewRobotPanel();
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
	public AddNewRobotPanel() {
		loading=LoadingFrame.getInstance();
	
		initComponents();
		initListener();
		initDataComponents();
		hiddenExtraPanel();
		hiddenFlujosPanel();
		enableContinue(false);
		loading.hiddenLoadingFrame();
	}
	
	
	private void enableContinue(boolean enable){
		ContinueButton.setEnabled(enable);
	}
	
	public void initComponents(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblNewLabel = new JLabel("Servicio");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		listServices = new JComboBox<AppJsonObject>();
		GridBagConstraints gbc_listServices = new GridBagConstraints();
		gbc_listServices.gridwidth = 3;
		gbc_listServices.insets = new Insets(0, 0, 5, 0);
		gbc_listServices.fill = GridBagConstraints.HORIZONTAL;
		gbc_listServices.gridx = 3;
		gbc_listServices.gridy = 1;
		contentPane.add(listServices, gbc_listServices);
		
		extraPanel = new JPanel();
		GridBagConstraints gbc_extraPanel = new GridBagConstraints();
		gbc_extraPanel.gridwidth = 4;
		gbc_extraPanel.gridheight = 5;
		gbc_extraPanel.insets = new Insets(0, 0, 5, 5);
		gbc_extraPanel.fill = GridBagConstraints.BOTH;
		gbc_extraPanel.gridx = 1;
		gbc_extraPanel.gridy = 2;
		contentPane.add(extraPanel, gbc_extraPanel);
		GridBagLayout gbl_extraPanel = new GridBagLayout();
		gbl_extraPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_extraPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_extraPanel.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_extraPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		extraPanel.setLayout(gbl_extraPanel);
		
		lblNewLabel_2 = new JLabel("Fecha de Inicio");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 2;
		extraPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		datePickerElement = getDatePickerElement();
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 2;
		extraPanel.add(datePickerElement, gbc_btnNewButton);
		
		lblNewLabel_3 = new JLabel("Numero Reintentos");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 3;
		extraPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		retries  =new JSpinner();
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.gridwidth = 2;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 3;
		gbc_btnNewButton_2.gridy = 3;
		extraPanel.add(retries, gbc_btnNewButton_2);
		
		lblNewLabel_4 = new JLabel("Tiempo entre Poleos");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 4;
		extraPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		timeLapse = new JComboBox<Integer>();
		GridBagConstraints gbc_timeLapse = new GridBagConstraints();
		gbc_timeLapse.fill = GridBagConstraints.HORIZONTAL;
		gbc_timeLapse.gridwidth = 2;
		gbc_timeLapse.insets = new Insets(0, 0, 5, 5);
		gbc_timeLapse.gridx = 3;
		gbc_timeLapse.gridy = 4;
		extraPanel.add(timeLapse, gbc_timeLapse);
		
		lblNewLabel_5 = new JLabel("Archivos Flujo");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 5;
		extraPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("Times New Roman", Font.BOLD, 9));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 5;
		extraPanel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		searchButton = new JButton("Buscar");
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.gridx = 5;
		gbc_searchButton.gridy = 5;
		extraPanel.add(searchButton, gbc_searchButton);
		
	
		flujosPanel();
		cancelButton = new JButton("Cancelar");
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.anchor = GridBagConstraints.EAST;
		gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
		gbc_cancelButton.gridx = 3;
		gbc_cancelButton.gridy = 8;
		contentPane.add(cancelButton, gbc_cancelButton);
		
		ContinueButton = new JButton("Continuar");
		GridBagConstraints gbc_ContinueButton = new GridBagConstraints();
		gbc_ContinueButton.insets = new Insets(0, 0, 0, 5);
		gbc_ContinueButton.gridx = 4;
		gbc_ContinueButton.gridy = 8;
		contentPane.add(ContinueButton, gbc_ContinueButton);
	}
	
	
	private void flujosPanel(){
		flujosPanel = new JPanel();
		GridBagConstraints gbc_flujosPanel = new GridBagConstraints();
		gbc_flujosPanel.gridwidth = 4;
		gbc_flujosPanel.insets = new Insets(0, 0, 5, 5);
		gbc_flujosPanel.fill = GridBagConstraints.BOTH;
		gbc_flujosPanel.gridx = 1;
		gbc_flujosPanel.gridy = 7;
		contentPane.add(flujosPanel, gbc_flujosPanel);
		GridBagLayout gbl_flujosPanel = new GridBagLayout();
		gbl_flujosPanel.columnWidths = new int[]{0, 0};
		gbl_flujosPanel.rowHeights = new int[]{0, 0, 0};
		gbl_flujosPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_flujosPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		flujosPanel.setLayout(gbl_flujosPanel);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		flujosPanel.add(scrollPane, gbc_scrollPane);
		
		flujosContentPanel = new JPanel();
		scrollPane.setViewportView(flujosContentPanel);
	}
	
	private void searchListener(){
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkExistFlujosPanel()){
					clearFolderText();
					deleteFlujosContent();
				}
				showFileChoser();
			}
		});
	}
	
	
	private void calculatetValidTimeLapse(){
		int total_time=1440;
		for(int i=1;i<61;i++){
			if(total_time%i==0){
				timeLapse.addItem(i);
			}
		}
		setDefaultTimePolling();
	}
	
	
	private void setDefaultTimePolling(){
		timeLapse.setSelectedItem(new Integer(15));

	}
	
	private void showFileChoser(){
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int showOpenDialog = chooser.showOpenDialog(this);
		if(showOpenDialog==JFileChooser.APPROVE_OPTION){
			File selectedFile = chooser.getSelectedFile();
			Path path = selectedFile.toPath();
			ArrayList<FlujoInformation> validFlujos;
			Path data;
			infoRobotM=new InfoRobotMaker();
			infoRobotM.setTimeLapse((Integer)timeLapse.getSelectedItem());
			infoRobotM.setRetries((Integer)retries.getValue());
			infoRobotM.setDateForRun(dateTimePicker.getDate());
			System.out.println("dateTimePicker****"+dateTimePicker.getDate());
			if(ValidatorManagement.isValidMainFolder(path)){
				System.out.println("is valid main folder");
				validFlujos=ValidatorManagement.getValidFlujosWithoutCheckInstalled(path.resolve("application"),flujosOfServcies);
//				validFlujos = ValidatorManagement.getValidFlujosWithoutCheckInstalled(path.resolve("application"),selectedItem.getId());
				if(validFlujos==null){
					JOptionPane.showMessageDialog(null, "No se han encontrado flujos validos correspondientes al servicio ", "Error",JOptionPane.ERROR_MESSAGE);
					closeWindows();
				}else{
					
					System.out.println("valid flujos main"+validFlujos.size());
					if(validFlujos.isEmpty()){
						JOptionPane.showMessageDialog(null, "El Servicio no contine Flujos para agregar ", "Error",JOptionPane.ERROR_MESSAGE);
						closeWindows();
					}else{
						if(AppExaminator.flujosConstanisStepsValid(validFlujos)){
							data=path.resolve("data");
							infoRobotM.setAppSelected(selectedItem);
							infoRobotM.setDataFolder(data);
							infoRobotM.setFlujos(validFlujos);
							textField.setText(path.toString());

							
						}else{
							JOptionPane.showMessageDialog(null, "No existen Pasos validos", "Error",JOptionPane.ERROR_MESSAGE);
							
						}
					}
				}
				
			}else{
				validFlujos= ValidatorManagement.
						getValidFlujosWithoutCheckInstalled(path,flujosOfServcies);
				if(validFlujos==null){
					JOptionPane.showMessageDialog(null, "No se han encontrado flujos validos correspondientes al servicio ", "Error",JOptionPane.ERROR_MESSAGE);
					closeWindows();
				}else{
					
					if(validFlujos.isEmpty()){
						JOptionPane.showMessageDialog(null, "No existen flujos validos", "Error",JOptionPane.ERROR_MESSAGE);
						
					}else{
						if(AppExaminator.flujosConstanisStepsValid(validFlujos)){
							infoRobotM.setAppSelected(selectedItem);
							infoRobotM.setFlujos(validFlujos);
							textField.setText(path.toString());

							
							
//						SwingUtilities.invokeLater(new Runnable() {
//							
//							@Override
//							public void run() {
//								installer.initSelectorWindowsAddFlujos(infoRobotM);
//								AddNewRobotPanel.this.dispose();
//							}
//						});
							
						}else{
							JOptionPane.showMessageDialog(null, "No existen Pasos validos", "Error",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				
				
				

			}
			if(infoRobotM.getFlujos()!=null &&!infoRobotM.getFlujos().isEmpty()){
				setValidFlujos(infoRobotM.getFlujos());
				showFlujosPanel();
			}
			

			
								
		}
	}
	private boolean checkExistFlujosPanel(){
		if(cbList!=null){
			System.out.println("list of checkBox"+cbList.getListCheckBox());
			if(cbList.getListCheckBox().size()>0){
				return true;
			}			
		}
		return false;
	}
	
	private void initDataComponents(){
		setApplicationNames();
		calculatetValidTimeLapse();
		setDefaultRetries();
	}
	
	
	public void initListener(){
		cancelListener();
		continueListener();
		listServicesListener();
		searchListener();
		initWindowsListener();
	}
	
	
	private void initWindowsListener(){
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				RobotManager.getSRMGuiManager().alReadyInUseAddRobotMenu(true);
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				RobotManager.getSRMGuiManager().alReadyInUseAddRobotMenu(false);

			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	private void cancelListener(){
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindows();
			}
		});
	}
	
	private void continueListener(){
		ContinueButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isValidForm()){
					CreatorRobotManager creator=new CreatorRobotManager();
					ArrayList<FlujoInformation> selectedFlows = getSelectedFlows();
					infoRobotM.setFlujos(selectedFlows);
					infoRobotM.setDateForRun(dateTimePicker.getDate());
					if(creator.createRobotWithPath(infoRobotM, false)){
						JOptionPane.showMessageDialog(null, "El robot se instalo correctamente", "info", JOptionPane.INFORMATION_MESSAGE);
						closeWindows();
					}else{
						JOptionPane.showMessageDialog(null, "No es posible instalar el robot", "Error", JOptionPane.ERROR_MESSAGE);

					}
					
					
				}else{
					System.out.println("nada esta bien");
				}
			}
		});
	}
	
	private void listServicesListener(){
		listServices.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<AppJsonObject> services=(JComboBox<AppJsonObject>)e.getSource();
				selectedItem = (AppJsonObject)services.getSelectedItem();
				if(selectedItem!=null){
					loading.showLoadingFrame(Language.get("addrobot.loading.services.message"));
					if(checkIfServicesContainFlujos()){
						clearElements();
						System.out.println("no es nulo");
						ArrayList<FlujoJsonObject> flujosName = ValidatorManagement.getFlujosName(selectedItem.getId());
						System.out.println("flujosname"+flujosName);
						showExtraPanel();
						hiddenFlujosPanel();
					}else{
						JOptionPane.showMessageDialog(null, "El Servicio Seleccionado no contiene Flujos", "Error", JOptionPane.ERROR_MESSAGE);
						hiddenExtraPanel();
					}
					loading.hiddenLoadingFrame();
				}else{
					System.out.println("es nulo");
					hiddenExtraPanel();
				}
				
			}
		});
	}
	
	private boolean checkIfServicesContainFlujos(){
		ArrayList<FlujoJsonObject> flujosName = ValidatorManagement.getFlujosName(selectedItem.getId());
		if(flujosName!=null && !flujosName.isEmpty()){
			flujosOfServcies=flujosName;
			return true;
		}
		return false;
	}
	private void clearFolderText(){
		textField.setText("");
		
	}
	private void clearElements(){
		retries.setValue(new Integer(3));
		clearFolderText();
//		datePickerElement=new DateTimePicker();
//		dateTimePicker=new DateTimePicker();
//		datePickerElement=new DateTimePicker();
		setDefaultRetries();
		setDefaultTimePolling();
		deleteFlujosContent();

	}
	
	private void closeWindows(){
		this.dispose();
	}
	
	private void setApplicationNames(){
		ArrayList<AppJsonObject> appsName = ValidatorManagement.getAppsName();
		System.out.println("appsname"+appsName);
		Alias aliasComp=new Alias();
		java.util.Collections.sort(appsName,aliasComp);
		listServices.addItem(null);
		for (AppJsonObject appJsonObject : appsName) {
			listServices.addItem(appJsonObject);
			System.out.println("alias appname"+appJsonObject.getAlias()+" id"+appJsonObject.getId());
		}
	}
	
	
	private void setDefaultRetries(){
//		retries.setValue(new Integer(3));
//		SpinnerModel model = retries.getModel();
		retries.setModel(new SpinnerNumberModel(3, 0, 1440, 1));
	}

	private class Alias implements Comparator<AppJsonObject>{

		@Override
		public int compare(AppJsonObject o1, AppJsonObject o2) {
			String alias1 = o1.getAlias();
			String alias2 = o2.getAlias();
			
			return alias1.compareTo(alias2);
		}
		
	}
	
	
	
	private DateTimePicker getDatePickerElement(){
	
		String dateServer = WebServicesManager.getcalculateDateServer();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parseDate;
		dateTimePicker = new DateTimePicker();
		try {
			parseDate = dateFormat.parse(dateServer);
			Calendar cal=Calendar.getInstance();
			cal.setTime(parseDate);
			cal.add(Calendar.MINUTE, 5);
			parseDate=cal.getTime();
			spinnerDate=parseDate;
			DateTimePicker.setSpinnerDate(parseDate);
			dateTimePicker=new DateTimePicker(parseDate);
			System.out.println("parser datae"+parseDate);
			dateTimePicker.setFormats( DateFormat.getDateTimeInstance( DateFormat.SHORT, DateFormat.MEDIUM ) );
			dateTimePicker.setTimeFormat( DateFormat.getTimeInstance( DateFormat.MEDIUM ) );
			dateTimePicker.setDate(parseDate);
			dateTimePicker.getMonthView().setLowerBound(parseDate);
			System.out.println(dateTimePicker.getDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTimePicker;
	}
	
	
	private void  hiddenExtraPanel(){
		setBounds(new Rectangle(100, 100, 450, 100));

		extraPanel.setVisible(false);
	}
	
	private void hiddenFlujosPanel(){
		Object selectedItem = listServices.getSelectedItem();
		if(selectedItem!=null){
			setBounds(new Rectangle(100, 100, 450, 250));
			
		}else{
			setBounds(new Rectangle(100, 100, 450,100));
		}
		
		flujosPanel.setVisible(false);
	}
	
	private void showFlujosPanel(){
		setBounds(new Rectangle(100, 100, 450, 350));
		flujosPanel.setVisible(true);
	}
	
	private void showExtraPanel(){
		setBounds(new Rectangle(100, 100, 450, 300));
		extraPanel.setVisible(true);
	}
	
	private void setValidFlujos(ArrayList<FlujoInformation> newFlujos){
		deleteFlujosContent();
//		Vector<JCheckBox> validFlujosToCheckBox = getValidFlujosToCheckBox(newFlujos);
		JCheckBox[] validFlujosToCheckBoxArray = getValidFlujosToCheckBoxArray(newFlujos);
		
		cbList = new CheckBoxList();
		cbList.setListData(validFlujosToCheckBoxArray);
	
//		cbList.addListSelectionListener(new ListSelectionListener() {
//			
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				JCheckBox checkBox=(JCheckBox)((CheckBoxList)e.getSource()).getSelectedValue();
//				if(checkBox.isSelected()){
//					System.out.println("the folder "+checkBox.getText()+" is Selected");
//				}else{
//					System.out.println("the folder "+checkBox.getText()+" is Deselect");
//
//				}
//				
//			}
//		});
		flujosContentPanel.add(cbList);
	}
	
	
	private void deleteFlujosContent(){

		if(SwingUtilities.isEventDispatchThread()){	
			System.out.println("is in edt");
			flujosContentPanel.removeAll();
			flujosContentPanel.repaint();
		}else{
			System.out.println("are not in edt");
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						flujosContentPanel.removeAll();
						flujosContentPanel.repaint();
					}
				});
		}
	}
	private JCheckBox[] getValidFlujosToCheckBoxArray(ArrayList<FlujoInformation> newFlujos){
		Vector<JCheckBox> validFlujosToCheckBox = getValidFlujosToCheckBox(newFlujos);
		JCheckBox[] checkArray=new JCheckBox[validFlujosToCheckBox.size()];
		int size=validFlujosToCheckBox.size();
		for(int i=0;i<size;i++){
			checkArray[i]=validFlujosToCheckBox.elementAt(i);
		}
		return checkArray;
	}
	
	private Vector<JCheckBox> getValidFlujosToCheckBox(ArrayList<FlujoInformation> newFlujos){
		Vector<JCheckBox> listJcheckbox=new Vector();
		JCheckBox checkBox;
		for(FlujoInformation flujo:newFlujos){
			checkBox=new JCheckBox(flujo.getName());
			setCheckBoxListner(checkBox);
			listJcheckbox.add(checkBox);
		}
		return listJcheckbox;
		
	}
	
	private void setCheckBoxListner(JCheckBox checkBoxElement){
		checkBoxElement.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JCheckBox check=(JCheckBox)e.getSource();
//				if(check.isSelected()){
//					System.out.println("the folder "+check.getText()+" is selected");
//				}else{
//					System.out.println("the folder"+check.getText()+" is deseleected");
//				}
				checkIfExistSelectCheckBox();
			}
		});
	}
	
	
	private ArrayList<FlujoInformation> getSelectedFlows(){
		ArrayList<JCheckBox> selectedCheckBox = cbList.getSelectedCheckBox();
		ArrayList<FlujoInformation> newFlujos=new ArrayList<FlujoInformation>();
		ArrayList<FlujoInformation> flujos = infoRobotM.getFlujos();
		for (JCheckBox jCheckBox : selectedCheckBox) {
			for (FlujoInformation flujo : flujos) {
				String flujName=flujo.getName();
				System.out.println("flujoName"+flujName+"selectedName"+jCheckBox.getText());
				System.out.println("equals"+flujName.equalsIgnoreCase(jCheckBox.getText()));
				if(flujName.equalsIgnoreCase(jCheckBox.getText())){
					newFlujos.add(flujo);
					break;
				}
			}
		}
		return newFlujos;
	}
	
	private void checkIfExistSelectCheckBox(){
		ArrayList<JCheckBox> selectedCheckBox = cbList.getSelectedCheckBox();
		if(!selectedCheckBox.isEmpty()){
			enableContinue(true);
		}else{
			enableContinue(false);
		}
	}
	
	
	private boolean isValidForm(){
		System.out.println("datepikckerElement"+datePickerElement.getDate());;
		System.out.println("dateleTimePicker"+dateTimePicker.getDate());
		if(textField!=null &&!textField.equals("")){
			if(datePickerElement.getDate()!=null){
				if(timeLapse.getSelectedItem()!=null){
					if(retries.getValue()!=null){
						ArrayList<JCheckBox> selectedCheckBox = cbList.getSelectedCheckBox();	
						if(selectedCheckBox!=null){
							System.out.println(selectedCheckBox);
							return true;
						}else{
							JOptionPane.showMessageDialog(this,"Es necesario Seleccionar un flujo" , "Error", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(this,"Es necesario especificar numero de reintentos" , "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(this,"Es necesario especificar un tiempo de poleo" , "Error", JOptionPane.ERROR_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(this,"Es necesario especificar la fecha de inicio del robot" , "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(this,"Es necesario seleccionar una ruta de flujos" , "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		return false;
	}
	
	
	
	
	

	

}
