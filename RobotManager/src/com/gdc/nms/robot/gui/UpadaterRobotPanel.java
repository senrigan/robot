package com.gdc.nms.robot.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.gdc.nms.robot.util.UpdaterRobot;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class UpadaterRobotPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JButton searchButton;
	private JButton continueButton;
	private JButton cancelButton;
	private JFrame mainFrame;
	private Path folder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpadaterRobotPanel frame = new UpadaterRobotPanel();
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
	public UpadaterRobotPanel() {
		initComponents();
		setListener();
		if(!ResultUpdate.isActiveUpdate()){
		}else{
			searchButton.setEnabled(false);
			continueButton.setEnabled(false);
			JOptionPane.showMessageDialog(null, "Existe una actualizacion en curso","Error",JOptionPane.ERROR_MESSAGE);
			this.dispose();
		}
			
	}
	
	
	private void initComponents(){
		setResizable(false);
		setTitle("Actualizar Robot");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 18, 0, 0, 128, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblNewLabel = new JLabel("Seleccionar el archivo de actualizaci\u00F3n del Robot");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		searchButton = new JButton("Ubicaci\u00F3n");
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.insets = new Insets(0, 0, 5, 5);
		gbc_searchButton.gridx = 2;
		gbc_searchButton.gridy = 2;
		contentPane.add(searchButton, gbc_searchButton);
		
		cancelButton = new JButton("Cancelar");
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.anchor = GridBagConstraints.NORTHEAST;
		gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
		gbc_cancelButton.gridx = 1;
		gbc_cancelButton.gridy = 4;
		contentPane.add(cancelButton, gbc_cancelButton);
		
		continueButton = new JButton("Continuar");
		GridBagConstraints gbc_continueButton = new GridBagConstraints();
		gbc_continueButton.insets = new Insets(0, 0, 5, 5);
		gbc_continueButton.gridx = 2;
		gbc_continueButton.gridy = 4;
		contentPane.add(continueButton, gbc_continueButton);
		continueButton.setEnabled(false);
	}
	
	private void setListener(){
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindows();
			}
		});
		
		continueButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if (JOptionPane.showConfirmDialog(null, "Continuar Con la Actualizacion ? ", "WARNING",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					closeWindows();
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							ResultUpdate result=new ResultUpdate();
							result.setVisible(true);
							result.initUpdate(folder);
						}
					});
					
				}else{
					closeWindows();
				}
			}
		});
		
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter(null, "zip"));
				int showOpenDialog = chooser.showOpenDialog(mainFrame);
				if(showOpenDialog==JFileChooser.APPROVE_OPTION){
					File selectedFile = chooser.getSelectedFile();
					if(selectedFile.getName().endsWith(".zip")){
						UpdaterRobot updater=new UpdaterRobot();
						Path robotPath = updater.getUpdateRoboFilestPath(selectedFile);
						try{
							if(updater.isValidRobot(robotPath)){
							
								System.out.println("es  valido");
								textField.setText(selectedFile.toString());
								continueButton.setEnabled(true);
								setPath(robotPath);
								
							}else{
								JOptionPane.showMessageDialog(null,
										"El archivo dado No es Valido","Error.", JOptionPane.ERROR_MESSAGE);
								continueButton.setEnabled(false);
								textField.setText(" ");
							}
							
						}catch(Exception ex){
							ex.printStackTrace();
							JOptionPane.showMessageDialog(null,
									"El archivo dado No Es Valido","Error.", JOptionPane.ERROR_MESSAGE);
							continueButton.setEnabled(false);
							textField.setText(" ");

						}
					}else{
						JOptionPane.showMessageDialog(null,
								 "El archivo dado no contiene una extension valida","Error.", JOptionPane.ERROR_MESSAGE);
						continueButton.setEnabled(false);
						textField.setText(" ");

					}
				}
			}
		});
	}
	
	
	private void closeWindows(){
		this.dispose();
	}
	
	
	private void setPath(Path path){
		this.folder=path;
	}

}
