package com.gdc.nms.robot.gui.auxiliar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;

public class RegisrtyEditor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel registryName;
	private JButton cancelButtton;
	private JButton confirmButton;
	private JButton validationButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisrtyEditor frame = new RegisrtyEditor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void init(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		registryName = new JLabel("New label");
		GridBagConstraints gbc_registryName = new GridBagConstraints();
		gbc_registryName.insets = new Insets(0, 0, 5, 5);
		gbc_registryName.gridx = 0;
		gbc_registryName.gridy = 1;
		contentPane.add(registryName, gbc_registryName);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 4;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		validationButton = new JButton("Validar");
		GridBagConstraints gbc_validationButton = new GridBagConstraints();
		gbc_validationButton.insets = new Insets(0, 0, 5, 0);
		gbc_validationButton.gridx = 5;
		gbc_validationButton.gridy = 1;
		contentPane.add(validationButton, gbc_validationButton);
		
		cancelButtton = new JButton("Cancelar");
		GridBagConstraints gbc_cancelButtton = new GridBagConstraints();
		gbc_cancelButtton.insets = new Insets(0, 0, 5, 5);
		gbc_cancelButtton.gridx = 2;
		gbc_cancelButtton.gridy = 2;
		contentPane.add(cancelButtton, gbc_cancelButtton);
		
		confirmButton = new JButton("Continuar");
		GridBagConstraints gbc_confirmButton = new GridBagConstraints();
		gbc_confirmButton.anchor = GridBagConstraints.WEST;
		gbc_confirmButton.insets = new Insets(0, 0, 5, 5);
		gbc_confirmButton.gridx = 3;
		gbc_confirmButton.gridy = 2;
		contentPane.add(confirmButton, gbc_confirmButton);
		setVisible(true);
	}
	/**
	 * Create the frame.
	 */
	public RegisrtyEditor() {
		init();
		initDefaultListener();
	}
	
	
	public void initDefaultListener(){
		cancelButtton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
	}
	
	public void close(){
		this.dispose();
	}
	public void setTitleWindows(String title){
		setTitle(title);
	}
	
	public void setValidationAction(ActionListener listener){
		validationButton.addActionListener(listener);
	}
	public void setContinueAction(ActionListener listener){
		confirmButton.addActionListener(listener);
	}
	
	
	public void setCancelAction(ActionListener listener){
		cancelButtton.addActionListener(listener);
	}
	
	
	public void setTexBox(String text){
		textField.setText(text);
	}
	
	public String getTexBox(){
		return textField.getText();
	}
	
	
	public void showValidButton(boolean show){
		validationButton.setVisible(show);
	}
	
	
	public void setRegistryLabel(String label){
		registryName.setText(label);
	}
	
	

}
