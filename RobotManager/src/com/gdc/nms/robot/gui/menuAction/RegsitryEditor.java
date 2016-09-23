package com.gdc.nms.robot.gui.menuAction;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gdc.nms.robot.gui.auxiliar.RegisrtyEditor;
import com.gdc.nms.robot.gui.menuAction.controller.RegistryEditorController;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class RegsitryEditor extends JFrame {

	private JPanel contentPane;
	private JTextField wsCreationText;
	private JTextField wsConsultText;
	private JTextField ubicationText;
	private JButton wsConsultValidButton;
	private JLabel lblNewLabel;
	private JLabel wsCreationLabel;
	private JButton wsCractionValidButton;
	private JLabel wsConsultLabel;
	private JLabel ubicationLabel;
	private JButton CancelButton;
	private JButton acceptButton;
	private RegistryEditorController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegsitryEditor frame = new RegsitryEditor();
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
	public RegsitryEditor() {
		initComponents();
		controller=new RegistryEditorController();
		initListener();
	}
	
	private void initComponents(){
		setTitle("Editor de Registros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblNewLabel = new JLabel("Registros");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		wsCreationLabel = new JLabel("WS Creacion");
		GridBagConstraints gbc_wsCreationLabel = new GridBagConstraints();
		gbc_wsCreationLabel.anchor = GridBagConstraints.EAST;
		gbc_wsCreationLabel.insets = new Insets(0, 0, 5, 5);
		gbc_wsCreationLabel.gridx = 0;
		gbc_wsCreationLabel.gridy = 1;
		contentPane.add(wsCreationLabel, gbc_wsCreationLabel);
		
		wsCreationText = new JTextField();
		GridBagConstraints gbc_wsCreationText = new GridBagConstraints();
		gbc_wsCreationText.fill = GridBagConstraints.HORIZONTAL;
		gbc_wsCreationText.gridwidth = 2;
		gbc_wsCreationText.insets = new Insets(0, 0, 5, 5);
		gbc_wsCreationText.gridx = 1;
		gbc_wsCreationText.gridy = 1;
		contentPane.add(wsCreationText, gbc_wsCreationText);
		wsCreationText.setColumns(10);
		
		wsCractionValidButton = new JButton("Validar");
		GridBagConstraints gbc_wsCractionValidButton = new GridBagConstraints();
		gbc_wsCractionValidButton.anchor = GridBagConstraints.WEST;
		gbc_wsCractionValidButton.insets = new Insets(0, 0, 5, 0);
		gbc_wsCractionValidButton.gridx = 3;
		gbc_wsCractionValidButton.gridy = 1;
		contentPane.add(wsCractionValidButton, gbc_wsCractionValidButton);
		
		wsConsultLabel = new JLabel("WS Consulta");
		GridBagConstraints gbc_wsConsultLabel = new GridBagConstraints();
		gbc_wsConsultLabel.anchor = GridBagConstraints.EAST;
		gbc_wsConsultLabel.insets = new Insets(0, 0, 5, 5);
		gbc_wsConsultLabel.gridx = 0;
		gbc_wsConsultLabel.gridy = 3;
		contentPane.add(wsConsultLabel, gbc_wsConsultLabel);
		
		wsConsultText = new JTextField();
		GridBagConstraints gbc_wsConsultText = new GridBagConstraints();
		gbc_wsConsultText.gridwidth = 2;
		gbc_wsConsultText.insets = new Insets(0, 0, 5, 5);
		gbc_wsConsultText.fill = GridBagConstraints.HORIZONTAL;
		gbc_wsConsultText.gridx = 1;
		gbc_wsConsultText.gridy = 3;
		contentPane.add(wsConsultText, gbc_wsConsultText);
		wsConsultText.setColumns(10);
		
		wsConsultValidButton = new JButton("Validar");
		GridBagConstraints gbc_wsConsultValidButton = new GridBagConstraints();
		gbc_wsConsultValidButton.anchor = GridBagConstraints.WEST;
		gbc_wsConsultValidButton.insets = new Insets(0, 0, 5, 0);
		gbc_wsConsultValidButton.gridx = 3;
		gbc_wsConsultValidButton.gridy = 3;
		contentPane.add(wsConsultValidButton, gbc_wsConsultValidButton);
		
		ubicationLabel = new JLabel("Ubicacion");
		GridBagConstraints gbc_ubicationLabel = new GridBagConstraints();
		gbc_ubicationLabel.anchor = GridBagConstraints.EAST;
		gbc_ubicationLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ubicationLabel.gridx = 0;
		gbc_ubicationLabel.gridy = 5;
		contentPane.add(ubicationLabel, gbc_ubicationLabel);
		
		ubicationText = new JTextField();
		GridBagConstraints gbc_ubicationText = new GridBagConstraints();
		gbc_ubicationText.gridwidth = 2;
		gbc_ubicationText.insets = new Insets(0, 0, 5, 5);
		gbc_ubicationText.fill = GridBagConstraints.HORIZONTAL;
		gbc_ubicationText.gridx = 1;
		gbc_ubicationText.gridy = 5;
		contentPane.add(ubicationText, gbc_ubicationText);
		ubicationText.setColumns(10);
		
		CancelButton = new JButton("Cancelar");
		GridBagConstraints gbc_CancelButton = new GridBagConstraints();
		gbc_CancelButton.anchor = GridBagConstraints.EAST;
		gbc_CancelButton.insets = new Insets(0, 0, 0, 5);
		gbc_CancelButton.gridx = 2;
		gbc_CancelButton.gridy = 7;
		contentPane.add(CancelButton, gbc_CancelButton);
		
		acceptButton = new JButton("Aceptar");
		GridBagConstraints gbc_acceptButton = new GridBagConstraints();
		gbc_acceptButton.anchor = GridBagConstraints.WEST;
		gbc_acceptButton.gridx = 3;
		gbc_acceptButton.gridy = 7;
		contentPane.add(acceptButton, gbc_acceptButton);
	}
	
	
	private void initListener(){
		initCancelListener();
		initAccepListener();
	
	}
	
	private void initCancelListener(){
		CancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindows();
			}
		});
	}
	
	private void initAccepListener(){
		acceptButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.fieldValidation(wsCreationText.getText(),wsConsultText.getText(),ubicationText.getText());
			}
		});
	}
	
	private  void closeWindows(){
		this.dispose();
	}

}
