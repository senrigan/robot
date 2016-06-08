package com.gdc.nms.robot.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gdc.nms.robot.util.CreatorRobotManager;
import com.gdc.nms.robot.util.InfoRobotMaker;
import com.gdc.nms.robot.util.indexer.AppInformation;
import com.gdc.nms.robot.util.indexer.AppJsonObject;
import com.gdc.nms.robot.util.indexer.FlujoInformation;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class InstallerRobotPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JButton cancelButton;
	private JButton continueButton;
	private JTextArea textArea;
//	private  ArrayList<FlujoInformation> validFlujos;
//	private  AppJsonObject selectedItem;
//	private Path data;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InstallerRobotPanel frame = new InstallerRobotPanel();
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
	public InstallerRobotPanel() {
		setTitle("A\u00F1adir Robot");
		initComponents();
		setListener();
	}
	
	
	private void initComponents(){
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{-9, 160, 145, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 126, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblNewLabel = new JLabel("Las siguentes carpetas seran Instaladas En la aplicacion");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.anchor = GridBagConstraints.SOUTHWEST;
		gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
		gbc_cancelButton.gridx = 1;
		gbc_cancelButton.gridy = 4;
		contentPane.add(cancelButton, gbc_cancelButton);
		
		continueButton = new JButton("Continuar");
		
		GridBagConstraints gbc_continueButton = new GridBagConstraints();
		gbc_continueButton.anchor = GridBagConstraints.SOUTHEAST;
		gbc_continueButton.insets = new Insets(0, 0, 0, 5);
		gbc_continueButton.gridx = 2;
		gbc_continueButton.gridy = 4;
		contentPane.add(continueButton, gbc_continueButton);
	}
	
	
	private void setText(String text){
		
		this.textArea.setText(text);
		System.out.println("texteando area");
	}
	
	


	private void setListener(){
		
		
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindows();
			}
		});
	}
	
	public void generateCheckData(){
		
	}
	
	public void addFlujosServices(final InfoRobotMaker infoRobotM){
		Path data=infoRobotM.getDataFolder();
		final ArrayList<FlujoInformation> validFlujos=infoRobotM.getFlujos();
		final AppJsonObject selectedItem=infoRobotM.getAppSelected();
		if(checkIfExistApp(selectedItem.getAlias(), selectedItem.getId())){
			JOptionPane.showMessageDialog(null, "Se ha detectado Que la aplicacion Ya se Encuentra Instalada Solo se procedera a Agregar Nuevos Flujos", "Correcto", JOptionPane.INFORMATION_MESSAGE);
			addFiles=true;
		}
	}
	
	public void createFlujosWithData(final InfoRobotMaker infoRobotM ){
		Path data=infoRobotM.getDataFolder();
		final ArrayList<FlujoInformation> validFlujos=infoRobotM.getFlujos();
		final AppJsonObject selectedItem=infoRobotM.getAppSelected();
		final Date calculateTime=infoRobotM.getDateForRun();
		boolean addFiles=false;
		if(checkIfExistApp(selectedItem.getAlias(), selectedItem.getId())){
			JOptionPane.showMessageDialog(null, "Se ha detectado Que la aplicacion Ya se Encuentra Instalada Solo se procedera a Agregar Nuevos Flujos", "Correcto", JOptionPane.INFORMATION_MESSAGE);
			addFiles=true;
		}
		final boolean addFilesRobot=addFiles;
		if(!validFlujos.isEmpty()){
			
			processText(validFlujos, selectedItem, data);
			setVisible(true);

			continueButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CreatorRobotManager creator=new CreatorRobotManager();

					if(creator.createRobotWithPath(infoRobotM ,addFilesRobot)){
						JOptionPane.showMessageDialog(null, "La carpeta fue insatalada correctamente.", "Correcto", JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null,
								 "No fue posible Instalar la aplicacion Correctamente","Error.", JOptionPane.ERROR_MESSAGE);

					}
					closeWindows();
				}
			});
			
			
		}
		else{
			JOptionPane.showMessageDialog(null,
					 "No existe Flujo Valido Correspondiente a la aplicacion dada","Error.", JOptionPane.ERROR_MESSAGE);

			closeWindows();
		}
	}
	public void createFlujosWithoutData(final InfoRobotMaker infoRobot){
		final  ArrayList<FlujoInformation> validFlujos=infoRobot.getFlujos();
		final AppJsonObject selectedItem=infoRobot.getAppSelected();
		final Date calculateTime=infoRobot.getDateForRun();
		boolean addFiles=false;
		if(checkIfExistApp(selectedItem.getAlias(), selectedItem.getId())){
			JOptionPane.showMessageDialog(null, "Se ha detectado Que la aplicacion Ya se Encuentra Instalada Solo se procedera a Agregar Nuevos Flujos", "Correcto", JOptionPane.INFORMATION_MESSAGE);
			addFiles=true;
		}
		final boolean addFilesRobot=addFiles;

		if(!validFlujos.isEmpty()){
			setVisible(true);
			
				
			processText(validFlujos, selectedItem, null);
			
			continueButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					CreatorRobotManager creator=new CreatorRobotManager();

					if(creator.createRobot(infoRobot,addFilesRobot)){
						
						
						JOptionPane.showMessageDialog(null, "La carpeta fue insatalada correctamente.", "Correcto", JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null,
								 "No fue posible Instalar la aplicacion Correctamente","Error.", JOptionPane.ERROR_MESSAGE);

					}
					closeWindows();
				}
			});
			
			
		}
		else{
			JOptionPane.showMessageDialog(null,
					 "No existe Flujo Valido Correspondiente a la aplicacion dada","Error.", JOptionPane.ERROR_MESSAGE);

			closeWindows();
		}
	}
	
	private void closeWindows(){
		this.dispose();
	}
	
	
	private void processText(ArrayList<FlujoInformation> validFlujos,AppJsonObject selectedItem,Path data){
		StringBuilder builder=new StringBuilder();
		builder.append("Flujos a Instalar : \n");
		for (FlujoInformation flujo :validFlujos) {
			builder.append("\t"+flujo.getName()+"\n");
		}
		if(data!=null){
			builder.append("Carpeta de Recursos : \n");
			builder.append("\t"+data.getFileName());
			System.out.println(data.getFileName());
		}
		setText(builder.toString());
		
	}
	private boolean checkIfExistApp(String appName,long idApp){
		ArrayList<AppInformation> installedApp = RobotManager.getInstalledApp();
		for (AppInformation appInformation : installedApp) {
			if( appInformation.getIdApp()==idApp ||appInformation.getAppName().equals(appName) ||
					appInformation.getAlias().equals(appName)){
				return true;
			}
		}
		return false;
	}
	
}
