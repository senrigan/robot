package com.gdc.nms.robot.gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.gdc.nms.robot.util.CreatorRobotManager;
import com.gdc.nms.robot.util.InfoRobotMaker;
import com.gdc.nms.robot.util.ValidatorManagement;
import com.gdc.nms.robot.util.indexer.AppJsonObject;
import com.gdc.nms.robot.util.indexer.FlujoInformation;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

public class DateSelectorPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DateTimePicker datePickerElement;
	private JButton ContinueButton;
	private DateTimePicker dateTimePicker;
	private JLabel lblNewLabel;
	public static Date spinnerDate;
	private JFrame mainFrame=this;
	private AppJsonObject selectedItem;
	private JLabel lblNewLabel_1;
	private JSpinner retries;
	private JLabel lblNewLabel_2;
	private JComboBox<Integer> timeLapse;





	public DateSelectorPanel(AppJsonObject selectedItem) {
		initElemennts();
		initListeners();
		this.selectedItem=selectedItem;
	}
	

	private void initElemennts(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblNewLabel = new JLabel("<html>Selecciona una Fecha para que inicie el Robot <br> por primera vez</html>");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		
		   
	        
//		JButton textButton = new JButton("test");
		GridBagConstraints gbc_dateTimePicker = new GridBagConstraints();
		gbc_dateTimePicker.gridwidth = 2;
		gbc_dateTimePicker.insets = new Insets(0, 0, 5, 5);
		gbc_dateTimePicker.gridx = 1;
		gbc_dateTimePicker.gridy = 3;
		datePickerElement = getDatePickerElement();
		
		contentPane.add(datePickerElement, gbc_dateTimePicker);
		
		lblNewLabel_1 = new JLabel("Numero de Reintentos");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 4;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		retries = new JSpinner();
		retries.setValue(3);
		GridBagConstraints gbc_retries = new GridBagConstraints();
		gbc_retries.insets = new Insets(0, 0, 5, 5);
		gbc_retries.gridx = 2;
		gbc_retries.gridy = 4;
		contentPane.add(retries, gbc_retries);
		
		lblNewLabel_2 = new JLabel("Tiempo entre poleos");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 5;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		timeLapse = new JComboBox<Integer>();
		GridBagConstraints gbc_timeLapse = new GridBagConstraints();
		gbc_timeLapse.insets = new Insets(0, 0, 5, 5);
		gbc_timeLapse.fill = GridBagConstraints.HORIZONTAL;
		gbc_timeLapse.gridx = 2;
		gbc_timeLapse.gridy = 5;
		contentPane.add(timeLapse, gbc_timeLapse);
		
		ContinueButton = new JButton("Continuar");
		ContinueButton.setEnabled(false);
		GridBagConstraints gbc_ContinueButton = new GridBagConstraints();
		gbc_ContinueButton.insets = new Insets(0, 0, 0, 5);
		gbc_ContinueButton.gridx = 2;
		gbc_ContinueButton.gridy = 9;
		contentPane.add(ContinueButton, gbc_ContinueButton);
		setVisible(true);
		calculatetValidTimeLapse();
	}
	
	private void initListeners(){
		datePickerElement.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(datePickerElement.getDate()!=null){
					ContinueButton.setEnabled(true);
				}else{
					ContinueButton.setEnabled(false);
				}
			}
		});
		
		ContinueButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int showOpenDialog = chooser.showOpenDialog(mainFrame);
				if(showOpenDialog==JFileChooser.APPROVE_OPTION){
					File selectedFile = chooser.getSelectedFile();
					Path path = selectedFile.toPath();
				
//					ArrayList<FlujoInformation> validFlujos = ValidatorManagement.
//							getValidFlujos(path,selectedItem.getId());
					ArrayList<FlujoInformation> validFlujos;
					final InstallerRobotPanel installer=new InstallerRobotPanel();
					Path data;
					final InfoRobotMaker infoRobotM=new InfoRobotMaker();
					infoRobotM.setTimeLapse((Integer)timeLapse.getSelectedItem());
					infoRobotM.setRetries((Integer)retries.getValue());
					infoRobotM.setDateForRun(dateTimePicker.getDate());
					if(ValidatorManagement.isValidMainFolder(path)){
						
						validFlujos = ValidatorManagement.getValidFlujosWithoutCheckInstalled(path.resolve("application"),selectedItem.getId());
						if(!validFlujos.isEmpty()){
							closeWindows();
						}
						data=path.resolve("data");
//						final Path dataPath=data;
//						final ArrayList<FlujoInformation> validFinalFlujos=validFlujos;
//						final AppJsonObject finalJsonObject=selectedItem;
						infoRobotM.setAppSelected(selectedItem);
						infoRobotM.setDataFolder(data);
						infoRobotM.setFlujos(validFlujos);
					
						
						SwingUtilities.invokeLater(new Runnable() {
							
							@Override
							public void run() {
								installer.initSelectorWindowsAddFlujos(infoRobotM);;

							}
						});
						
					}else{
						validFlujos= ValidatorManagement.
								getValidFlujosWithoutCheckInstalled(path,selectedItem.getId());
						if(!validFlujos.isEmpty()){
							closeWindows();
						
						}
//						final ArrayList<FlujoInformation> validFinalFlujos=validFlujos;
//						final AppJsonObject finalJsonObject=selectedItem;
						infoRobotM.setAppSelected(selectedItem);
						infoRobotM.setFlujos(validFlujos);
						
						
						SwingUtilities.invokeLater(new Runnable() {
							
							@Override
							public void run() {
								installer.initSelectorWindowsAddFlujos(infoRobotM);
								

							}
						});
						
						
						

					}
					
										
				}
//				chooser.showOpenDialog(null);
//				JOptionPane.showMessageDialog(null, "La carpeta fue insatalada correctamente.", "Correcto", JOptionPane.INFORMATION_MESSAGE);
//				JOptionPane.showMessageDialog(null, "Carpeta Incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
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
	
	private void calculatetValidTimeLapse(){
		int total_time=1440;
//		List<Integer> validTime=new ArrayList<Integer>();
		for(int i=1;i<61;i++){
			if(total_time%i==0){
				timeLapse.addItem(i);
//				validTime.add(i);
			}
		}
		timeLapse.setSelectedItem(new Integer(15));
//		timeLapse.setSelectedItem(12);
//		return validTime;
	}
	
	
	
	public static Date getCalculateDateServer(){
		String dateServer = CreatorRobotManager.getcalculateDateServer();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parseDate=null;
		try {
			parseDate = dateFormat.parse(dateServer);
			Calendar cal=Calendar.getInstance();
			cal.setTime(parseDate);
			cal.add(Calendar.MINUTE, 5);
			parseDate=cal.getTime();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parseDate;
	}

	

	private void closeWindows(){
		this.dispose();
	}
	
	
	public static void main(String[] args) {
		
			int total_time=1440;
			List<Integer> validTime=new ArrayList<Integer>();
			for(int i=1;i<61;i++){
				if(total_time%i==0){
					validTime.add(i);
				}
			}
			System.out.println(validTime);
		
	}
}
