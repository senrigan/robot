package com.gdc.nms.robot.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.gdc.nms.robot.gui.auxiliar.LoadingFrame;
import com.gdc.nms.robot.util.Language;
import com.gdc.robothelper.webservice.WebServicesManager;
import com.gdc.srm.register.windows.RegistryManager;
import com.gdc.srm.robot.gui.conf.ConfigurationController;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class NewConfigurationPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField webservicesCreatorText;
	private JTextField webservicesConsultText;
	private JLabel wsCreatorLabel;
	private JButton validWSCreatorButton;
	private JLabel wsConsultLabel;
	private JButton validWSConsultButton;
	private JLabel autoRestarLabel;
	private JCheckBox restarRobotCheckBox;
	private JLabel encriptationImacrosLabel;
	private JCheckBox encriptationImacrosCheckBox;
	private JButton saveButtton;
	private JButton defautlValuesButton;
	private JLabel ubicationLabel;
	private JTextField ubicationField;
	private ConfigurationController controller;
	private boolean wsCreatorChange;
	private boolean wsConsultChange;
	private boolean ubicationChange;
	private boolean restarChange;
	private boolean passworChange;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Language.load();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewConfigurationPanel frame = new NewConfigurationPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void initComponents(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBackground(new Color(234, 244, 254));
		setBounds(100, 100, 552, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		wsCreatorLabel = new JLabel(Language.get("webservices.configuration.creator.label"));
		GridBagConstraints gbc_wsCreatorLabel = new GridBagConstraints();
		gbc_wsCreatorLabel.anchor = GridBagConstraints.WEST;
		gbc_wsCreatorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_wsCreatorLabel.gridx = 1;
		gbc_wsCreatorLabel.gridy = 1;
		contentPane.add(wsCreatorLabel, gbc_wsCreatorLabel);
		
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
		
		wsConsultLabel = new JLabel(Language.get("webservices.configuration.consult.label"));
		GridBagConstraints gbc_wsConsultLabel = new GridBagConstraints();
		gbc_wsConsultLabel.anchor = GridBagConstraints.WEST;
		gbc_wsConsultLabel.insets = new Insets(0, 0, 5, 5);
		gbc_wsConsultLabel.gridx = 1;
		gbc_wsConsultLabel.gridy = 2;
		contentPane.add(wsConsultLabel, gbc_wsConsultLabel);
		
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
		
		ubicationLabel = new JLabel(Language.get("configuration.ubication.label"));
		GridBagConstraints gbc_ubicationLabel = new GridBagConstraints();
		gbc_ubicationLabel.anchor = GridBagConstraints.WEST;
		gbc_ubicationLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ubicationLabel.gridx = 1;
		gbc_ubicationLabel.gridy = 3;
		contentPane.add(ubicationLabel, gbc_ubicationLabel);
		
		ubicationField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 3;
		contentPane.add(ubicationField, gbc_textField);
		ubicationField.setColumns(10);
		
		autoRestarLabel = new JLabel(Language.get("webservices.configuration.autorestar.check"));
		GridBagConstraints gbc_autoRestarLabel = new GridBagConstraints();
		gbc_autoRestarLabel.anchor = GridBagConstraints.WEST;
		gbc_autoRestarLabel.insets = new Insets(0, 0, 5, 5);
		gbc_autoRestarLabel.gridx = 1;
		gbc_autoRestarLabel.gridy = 4;
		contentPane.add(autoRestarLabel, gbc_autoRestarLabel);
		
		restarRobotCheckBox = new JCheckBox("");
		GridBagConstraints gbc_restarRobotCheckBox = new GridBagConstraints();
		gbc_restarRobotCheckBox.gridwidth = 2;
		gbc_restarRobotCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_restarRobotCheckBox.gridx = 2;
		gbc_restarRobotCheckBox.gridy = 4;
		contentPane.add(restarRobotCheckBox, gbc_restarRobotCheckBox);
		
		encriptationImacrosLabel = new JLabel(Language.get("webservices.configuration.setmasterpassword.check"));
		GridBagConstraints gbc_encriptationImacrosLabel = new GridBagConstraints();
		gbc_encriptationImacrosLabel.anchor = GridBagConstraints.WEST;
		gbc_encriptationImacrosLabel.insets = new Insets(0, 0, 5, 5);
		gbc_encriptationImacrosLabel.gridx = 1;
		gbc_encriptationImacrosLabel.gridy = 5;
		contentPane.add(encriptationImacrosLabel, gbc_encriptationImacrosLabel);
		
		encriptationImacrosCheckBox = new JCheckBox("");
		GridBagConstraints gbc_encriptationImacrosCheckBox = new GridBagConstraints();
		gbc_encriptationImacrosCheckBox.gridwidth = 2;
		gbc_encriptationImacrosCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_encriptationImacrosCheckBox.gridx = 2;
		gbc_encriptationImacrosCheckBox.gridy = 5;
		contentPane.add(encriptationImacrosCheckBox, gbc_encriptationImacrosCheckBox);
		
		defautlValuesButton = new JButton(Language.get("webservices.configuration.defautl.button"));
		GridBagConstraints gbc_defautlValuesButton = new GridBagConstraints();
		gbc_defautlValuesButton.anchor = GridBagConstraints.WEST;
		gbc_defautlValuesButton.gridwidth = 3;
		gbc_defautlValuesButton.insets = new Insets(0, 0, 0, 5);
		gbc_defautlValuesButton.gridx = 1;
		gbc_defautlValuesButton.gridy = 6;
		contentPane.add(defautlValuesButton, gbc_defautlValuesButton);
		
		saveButtton = new JButton(Language.get("webservices.configuration.save.button"));
		GridBagConstraints gbc_saveButtton = new GridBagConstraints();
		gbc_saveButtton.gridx = 4;
		gbc_saveButtton.gridy = 6;
		contentPane.add(saveButtton, gbc_saveButtton);
	}
	/**
	 * Create the frame.
	 */
	public NewConfigurationPanel() {
		controller = new ConfigurationController();
		initComponents();
		initDataComponents();
		initListeners();
		setVisible(true);
	}
	
	private void initDataComponents(){
		initWebServicesCreatorText();
		initWebServicesConsultText();
		setEncryptionCheckBoxData();
		setUbicationData();
	}
	
	private void initListeners(){
		wsConsultValidatorListener();
		wsCreatorValidatorListener();
		restarCheckBoxListener();
		encryptionCheckBoxListener();
		initWindowsListener();
		saveButtonListener();
		changeFielDListener();
		
	}
	private void changeFielDListener(){
		wsCreatorTextListener();
		wsConsultTextListener();
		ubicationListener();
		
	}
	private void restarListener(){
	}
	private void ubicationListener(){
		ubicationField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				ubicationChange=true;
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				ubicationChange=true;
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				ubicationChange=true;
			}
		});
	}
	
	private void wsCreatorTextListener(){
		webservicesCreatorText.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				wsCreatorChange=true;
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				wsCreatorChange=true;
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				System.out.println("cambiado");
				wsCreatorChange=true;
			}
		});
	}
	
	private void wsConsultTextListener(){
		webservicesConsultText.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				wsConsultChange=true;
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				wsConsultChange=true;
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				wsConsultChange=true;
			}
		});
	}
	
	
	
	private void saveButtonListener(){
		saveButtton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveDataConfig();
				
			}
		});
	}
	
	
	
	private void saveDataConfig(){
		if(wsCreatorChange){
			
			String wsCreator = webservicesCreatorText.getText();
			System.out.println("creator si hay nuevos cambios *****"+wsCreator+"  url "+wsCreator);
			if(WebServicesManager.checkNewVersionCreator(wsCreator)){
				System.out.println("si exte concion nueva version");
				controller.saveWebServicesCreator(wsCreator);
			}else if(WebServicesManager.checkOldVersionCreator(wsCreator)){
				System.out.println("si exte concion vieja version version");

				controller.saveWebServicesCreator(wsCreator);
			}else{
				JOptionPane.showMessageDialog(null, Language.get("configuration.save.wscreator.error"), "title", JOptionPane.ERROR_MESSAGE);
				return ;
			}
		}
		if(wsConsultChange){
			String wsConsult = webservicesConsultText.getText();
			if(WebServicesManager.checkWebServicesConsult(wsConsult)){
				if(!controller.saveWebServicesConsult(wsConsult)){
					//error save
					JOptionPane.showMessageDialog(null, Language.get("configuration.save.wsconsult.error"), "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}else{
				
			}
			
		}
		if(ubicationChange){
			boolean saveUbicationRobot = controller.saveUbicationRobot(ubicationField.getText());
			if(!saveUbicationRobot){
				//error vase
				JOptionPane.showMessageDialog(null, Language.get("configuration.save.ubication.error"),"Error", JOptionPane.ERROR_MESSAGE);;
				return;
			}
		}
		
		if(restarChange){
			boolean selected = restarRobotCheckBox.isSelected();
			boolean saveAutoStart = controller.saveAutoStart(selected);
			if(!saveAutoStart){
				JOptionPane.showMessageDialog(null,Language.get("configuration.save.restar.error"), "Error", JOptionPane.ERROR_MESSAGE);
				return ;
			}
		}
		if(passworChange){
			boolean selected = encriptationImacrosCheckBox.isSelected();
			boolean saveEncriptationImacros = controller.saveEncriptationImacros(selected);
			if(!saveEncriptationImacros){
				JOptionPane.showMessageDialog(null, Language.get("configuration.save.passwor.error"), "Error",JOptionPane.ERROR_MESSAGE);
				return ;
			}
		}
		
		JOptionPane.showMessageDialog(null, Language.get("configuration.save.sucess"), "Info", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	private void initWindowsListener(){
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				RobotManager.getSRMGuiManager().alReadyInUseConfigurationMenu(true);
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				RobotManager.getSRMGuiManager().alReadyInUseConfigurationMenu(false);
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
	}
	
	private void wsConsultValidatorListener(){
		validWSConsultButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("consultando wsConsult");
				LoadingFrame.getInstance().showLoadingFrame("configuration.loading.wsconsult.message");

				if(WebServicesManager.checkWebServicesConsult(webservicesConsultText.getText())){
					JOptionPane.showMessageDialog(null, Language.get("webservices.configuration.wsconsult.validator.message"), "Info", JOptionPane.INFORMATION_MESSAGE);

				}else{
					JOptionPane.showMessageDialog(null, Language.get("webservices.configuration.wsconsult.validator.message.error"), "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				LoadingFrame.getInstance().close();
			}
		});
	}
	
	
	private void wsCreatorValidatorListener(){
		validWSCreatorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoadingFrame.getInstance().showLoadingFrame("configuration.loading.wscreator.message");
				System.out.println("consultando wsCreator");
				if(WebServicesManager.checkNewVersionCreator(webservicesCreatorText.getText())){
					JOptionPane.showMessageDialog(null, Language.get("webservices.configuration.wscreator.validator.message"), "Info", JOptionPane.INFORMATION_MESSAGE);
					LoadingFrame.getInstance().close();

				}else if(WebServicesManager.checkOldVersionCreator(webservicesCreatorText.getText())){
					JOptionPane.showMessageDialog(null, Language.get("webservices.configuration.wscreator.validator.message"), "Info", JOptionPane.INFORMATION_MESSAGE);
					LoadingFrame.getInstance().close();

				}else{
					JOptionPane.showMessageDialog(null, Language.get("webservices.configuration.wscreator.validator.message.error"), "Error", JOptionPane.INFORMATION_MESSAGE);
					LoadingFrame.getInstance().close();

				}
//				LoadingFrame.getInstance().close();
			}
		});
	}
	
	
	private void restarCheckBoxListener(){
		restarRobotCheckBox.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
//				restarChange=true;
				restarChange=(!restarChange);
				
			}
		});
	}
	
	private void encryptionCheckBoxListener(){
		encriptationImacrosCheckBox.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
//				passworChange=true;
				passworChange=(!passworChange);
			}
		});
	}
	
	private void setEncryptionCheckBoxData(){
		boolean imacrosPasswordRegistry = RegistryEditorManager.getImacrosPasswordRegistry();
		encriptationImacrosCheckBox.setSelected(imacrosPasswordRegistry);
	}
	
	private void setUbicationData(){
		String ubicationRobotRegistry = RegistryManager.getUbicationRobotRegistry();
		ubicationField.setText(ubicationRobotRegistry);
		
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
