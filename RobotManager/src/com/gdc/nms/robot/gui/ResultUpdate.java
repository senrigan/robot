package com.gdc.nms.robot.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gdc.nms.robot.util.UpdaterRobot;

import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.JButton;

public class ResultUpdate extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JTextArea textInfo;
	private JButton ContinueButton;
	private static boolean isUpdateActive;



	/**
	 * Create the frame.
	 */
	public ResultUpdate() {
		initComponent();
	}
	
	private void initComponent(){
		setTitle("Actualizador");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblNewLabel = new JLabel("Proceso de Actualizacion");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		textInfo = new JTextArea();
		textInfo.setEditable(false);
		textInfo.setOpaque(false);
		scrollPane.setViewportView(textInfo);
		
		ContinueButton = new JButton("Continuar");
		GridBagConstraints gbc_ContinueButton = new GridBagConstraints();
		gbc_ContinueButton.insets = new Insets(0, 0, 5, 5);
		gbc_ContinueButton.anchor = GridBagConstraints.EAST;
		gbc_ContinueButton.gridx = 1;
		gbc_ContinueButton.gridy = 3;
		contentPane.add(ContinueButton, gbc_ContinueButton);
	}
	
	
	private void initListeners(){
		ContinueButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				closeWindows();
			}
		});
	}
	
	
	public void initUpdate(Path folder){
		UpdaterRobot updater=new UpdaterRobot();
		try{
			isUpdateActive=true;
			appendText("Deteniendo Los Robots ...");
			updater.stopAllRobots();
			appendText("OK \n");
			try {
				appendText("Copiando nuevo Robot ....");
				updater.copyJarInMonitor(folder);
				appendText("Ok \n");
				appendText("Actualizando Robots ....");
				updater.updateAllRobots(this);
				appendText("actualizacion Completada");
			
			}catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"No es Posible Actualizar los archivos","Error.", JOptionPane.ERROR_MESSAGE);
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		isUpdateActive=false;

			
		
	}
		
	public static boolean isActiveUpdate(){
		return isUpdateActive;
	}
	
	public void  setText(String text){
		this.textInfo.setText(text);
	}
	
	public void appendText(String text){
		this.textInfo.append(text);
	}
	
	
	public void closeWindows(){
		this.dispose();
	}
	
	
	
	
	

}
