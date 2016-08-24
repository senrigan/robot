package com.gdc.nms.robot.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gdc.nms.robot.util.CreatorRobotManager;
import com.gdc.nms.robot.util.ValidatorManagement;
import com.gdc.nms.robot.util.indexer.AppJsonObject;

import javafx.scene.control.ComboBox;

import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JTextField;

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
		initComponents();
		initListener();
		initDataComponents();
	}
	
	public void initComponents(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblNewLabel = new JLabel("Servicio");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		listServices = new JComboBox();
		GridBagConstraints gbc_listServices = new GridBagConstraints();
		gbc_listServices.gridwidth = 2;
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
		gbl_extraPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_extraPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_extraPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_extraPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		extraPanel.setLayout(gbl_extraPanel);
		
		lblNewLabel_2 = new JLabel("Fecha de Inicio");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		extraPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		datePickerElement = getDatePickerElement();
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 2;
		extraPanel.add(datePickerElement, gbc_btnNewButton);
		
		lblNewLabel_3 = new JLabel("Numero Reintentos");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 3;
		extraPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Tiempo entre Poleos");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 4;
		extraPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Archivos Flujo");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 5;
		extraPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 5;
		extraPanel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		cancelButton = new JButton("Cancelar");
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.anchor = GridBagConstraints.EAST;
		gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
		gbc_cancelButton.gridx = 3;
		gbc_cancelButton.gridy = 7;
		contentPane.add(cancelButton, gbc_cancelButton);
		
		ContinueButton = new JButton("Continuar");
		GridBagConstraints gbc_ContinueButton = new GridBagConstraints();
		gbc_ContinueButton.gridx = 4;
		gbc_ContinueButton.gridy = 7;
		contentPane.add(ContinueButton, gbc_ContinueButton);
	}
	
	
	private void initDataComponents(){
		setApplicationNames();
	}
	
	
	public void initListener(){
		cancelListener();
		continueListener();
		listServicesListener();
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
		
	}
	
	private void listServicesListener(){
		listServices.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<AppJsonObject> services=(JComboBox<AppJsonObject>)e.getSource();
				AppJsonObject selectedItem = (AppJsonObject)services.getSelectedItem();
				if(selectedItem!=null){
					System.out.println("no es nulo");
				}else{
					System.out.println("es nulo");
				}
				
			}
		});
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
//			String alias = appJsonObject.getAlias();
			listServices.addItem(appJsonObject);
			System.out.println("alias appname"+appJsonObject.getAlias()+" id"+appJsonObject.getId());
		}
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
		String dateServer = CreatorRobotManager.getcalculateDateServer();
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
		extraPanel.setVisible(false);
	}
	
	private void showExtraPanel(){
		extraPanel.setVisible(true);
	}
	

}
