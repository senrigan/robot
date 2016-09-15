package com.gdc.nms.robot.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gdc.nms.robot.util.Language;
import com.gdc.robothelper.webservice.WebServicesManager;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class WebServicesConfiguration extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField webservicesCreatorText;
	private JTextField webservicesConsultText;
	private JLabel lblNewLabel;
	private JButton validWSCreatorButton;
	private JLabel lblNewLabel_1;
	private JButton validWSConsultButton;
	private JLabel lblNewLabel_2;
	private JCheckBox restarRobotCheckBox;
	private JLabel lblNewLabel_3;
	private JCheckBox encriptationImacrosCheckBox;
	private JButton saveButtton;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Language.load();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WebServicesConfiguration frame = new WebServicesConfiguration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void initComponents(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(234, 244, 254));
		setBounds(100, 100, 552, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblNewLabel = new JLabel(Language.get("webservices.configuration.creator.label"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		webservicesCreatorText = new JTextField();
		GridBagConstraints gbc_webservicesCreatorText = new GridBagConstraints();
		gbc_webservicesCreatorText.gridwidth = 2;
		gbc_webservicesCreatorText.insets = new Insets(0, 0, 5, 5);
		gbc_webservicesCreatorText.fill = GridBagConstraints.BOTH;
		gbc_webservicesCreatorText.gridx = 2;
		gbc_webservicesCreatorText.gridy = 1;
		contentPane.add(webservicesCreatorText, gbc_webservicesCreatorText);
		webservicesCreatorText.setColumns(10);
		
		validWSCreatorButton = new JButton(Language.get("webservices.configuration.creator.valid.button"));
		GridBagConstraints gbc_validWSCreatorButton = new GridBagConstraints();
		gbc_validWSCreatorButton.insets = new Insets(0, 0, 5, 0);
		gbc_validWSCreatorButton.gridx = 4;
		gbc_validWSCreatorButton.gridy = 1;
		contentPane.add(validWSCreatorButton, gbc_validWSCreatorButton);
		
		lblNewLabel_1 = new JLabel(Language.get("webservices.configuration.consult.label"));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		webservicesConsultText = new JTextField();
		GridBagConstraints gbc_webservicesConsultText = new GridBagConstraints();
		gbc_webservicesConsultText.gridwidth = 2;
		gbc_webservicesConsultText.insets = new Insets(0, 0, 5, 5);
		gbc_webservicesConsultText.fill = GridBagConstraints.BOTH;
		gbc_webservicesConsultText.gridx = 2;
		gbc_webservicesConsultText.gridy = 2;
		contentPane.add(webservicesConsultText, gbc_webservicesConsultText);
		webservicesConsultText.setColumns(10);
		
		validWSConsultButton = new JButton(Language.get("webservices.configuration.consult.valid.button"));
		GridBagConstraints gbc_validWSConsultButton = new GridBagConstraints();
		gbc_validWSConsultButton.insets = new Insets(0, 0, 5, 0);
		gbc_validWSConsultButton.gridx = 4;
		gbc_validWSConsultButton.gridy = 2;
		contentPane.add(validWSConsultButton, gbc_validWSConsultButton);
		
		lblNewLabel_2 = new JLabel(Language.get("webservices.configuration.autorestar.check"));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 3;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		restarRobotCheckBox = new JCheckBox("");
		GridBagConstraints gbc_restarRobotCheckBox = new GridBagConstraints();
		gbc_restarRobotCheckBox.gridwidth = 2;
		gbc_restarRobotCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_restarRobotCheckBox.gridx = 2;
		gbc_restarRobotCheckBox.gridy = 3;
		contentPane.add(restarRobotCheckBox, gbc_restarRobotCheckBox);
		
		lblNewLabel_3 = new JLabel(Language.get("webservices.configuration.setmasterpassword.check"));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 4;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		encriptationImacrosCheckBox = new JCheckBox("");
		GridBagConstraints gbc_encriptationImacrosCheckBox = new GridBagConstraints();
		gbc_encriptationImacrosCheckBox.gridwidth = 2;
		gbc_encriptationImacrosCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_encriptationImacrosCheckBox.gridx = 2;
		gbc_encriptationImacrosCheckBox.gridy = 4;
		contentPane.add(encriptationImacrosCheckBox, gbc_encriptationImacrosCheckBox);
		
		btnNewButton = new JButton(Language.get("webservices.configuration.defautl.button"));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 5;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		saveButtton = new JButton(Language.get("webservices.configuration.save.button"));
		GridBagConstraints gbc_saveButtton = new GridBagConstraints();
		gbc_saveButtton.gridx = 4;
		gbc_saveButtton.gridy = 5;
		contentPane.add(saveButtton, gbc_saveButtton);
	}
	/**
	 * Create the frame.
	 */
	public WebServicesConfiguration() {
		initComponents();
		initDataComponents();
		initListeners();
	}
	
	private void initDataComponents(){
		initWebServicesCreatorText();
		initWebServicesConsultText();
	}
	
	private void initListeners(){
		wsConsultValidatorListener();
		wsCreatorValidatorListener();
		restarCheckBoxListener();
		encryptionCheckBoxListener();
	}
	
	
	private void wsConsultValidatorListener(){
		validWSConsultButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("consultando wsConsult");
				if(WebServicesManager.checkWebServicesConsult(webservicesConsultText.getText())){
					JOptionPane.showMessageDialog(null, Language.get("webservices.configuration.wsconsult.validator.message"), "Info", JOptionPane.INFORMATION_MESSAGE);

				}else{
					JOptionPane.showMessageDialog(null, Language.get("webservices.configuration.wsconsult.validator.message.error"), "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}
	
	
	private void wsCreatorValidatorListener(){
		validWSCreatorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("consultando wsCreator");
				if(WebServicesManager.checkNewVersionCreator(webservicesCreatorText.getText())){
					JOptionPane.showMessageDialog(null, Language.get("webservices.configuration.wscreator.validator.message"), "Info", JOptionPane.INFORMATION_MESSAGE);
				}else if(WebServicesManager.checkOldVersionCreator(webservicesCreatorText.getText())){
					JOptionPane.showMessageDialog(null, Language.get("webservices.configuration.wscreator.validator.message"), "Info", JOptionPane.INFORMATION_MESSAGE);

				}else{
					JOptionPane.showMessageDialog(null, Language.get("webservices.configuration.wscreator.validator.message.error"), "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}
	
	
	private void restarCheckBoxListener(){
		restarRobotCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
	private void encryptionCheckBoxListener(){
		encriptationImacrosCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
	private void  initWebServicesCreatorText(){
		URL wsCreatorUrl = WebServicesManager.getWebServicesCreatorUrl();
		webservicesCreatorText.setText(wsCreatorUrl.toString());
		webservicesCreatorText.setFont(new Font("Arial",Font.PLAIN, 9));
	}
	
	
	private void initWebServicesConsultText(){
		URL wsConsultUrl = WebServicesManager.getWebServicesConsult();
		webservicesConsultText.setText(wsConsultUrl.toString());
		webservicesConsultText.setFont(new Font("Arial",Font.PLAIN, 9));

	}
	
	

}
