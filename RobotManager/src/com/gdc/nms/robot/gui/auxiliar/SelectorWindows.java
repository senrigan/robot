package com.gdc.nms.robot.gui.auxiliar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXBusyLabel;


import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;

public class SelectorWindows extends JFrame {

	private JPanel principalPanel;
	private JButton CancelButton;
	private JButton ContinueButton;
	private JPanel ContentPanel;
	private JLabel instructionLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectorWindows frame = new SelectorWindows();
					frame.setTitleWindows("gola");
					frame.setInstructionLabel("seleccionea algo");
//					JButton button=new JButton("hola");

					final CheckBoxList cbList = new CheckBoxList();
				    JCheckBox check1 = new JCheckBox("One");
				    JCheckBox check2 = new JCheckBox("two");
				    JCheckBox[] myList = { check1, check2};
				    cbList.setListData(myList);
					frame.setContent(cbList);
					
					ActionListener listener=new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							ArrayList<JCheckBox> selectedCheckBox = cbList.getSelectedCheckBox();
							System.out.println("selected items are "+selectedCheckBox);
						}
					};
					frame.setContinueAction(listener);
					listener=new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							JButton source = (JButton)e.getSource();
							System.out.println(source.getText());
							JFrame root = (JFrame)SwingUtilities.getRoot(source);
							root.setBackground(Color.BLUE);
							root.getContentPane().setBackground(Color.BLUE);
							root.dispose();
						}
					};
					frame.setCancelAction(listener);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SelectorWindows() {
		initComponents();
	}
	
	
	public void  setInstructionLabel (String Instruction){
		instructionLabel.setText(Instruction);
	}
	
	public void setTitleWindows(String title){
		setTitle(title);
	}
	
	public void setContent(JComponent component){
		ContentPanel.add(component);
	}
	
	
	public void setContinueAction(ActionListener actionListener){
		ContinueButton.addActionListener(actionListener);
	}
	
	public void setCancelAction(ActionListener actionListener){
		CancelButton.addActionListener(actionListener);
	}
	
	
	
	private void initComponents(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		principalPanel = new JPanel();
		principalPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(principalPanel);
		GridBagLayout gbl_principalPanel = new GridBagLayout();
		gbl_principalPanel.columnWidths = new int[]{333, 0, 0};
		gbl_principalPanel.rowHeights = new int[]{29, 0, 0, 0};
		gbl_principalPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_principalPanel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		principalPanel.setLayout(gbl_principalPanel);
		
		instructionLabel = new JLabel("");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		principalPanel.add(instructionLabel, gbc_btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		principalPanel.add(scrollPane, gbc_scrollPane);
		
		ContentPanel = new JPanel();
		scrollPane.setViewportView(ContentPanel);
		
		CancelButton = new JButton("Cancelar");
		GridBagConstraints gbc_CancelButton = new GridBagConstraints();
		gbc_CancelButton.anchor = GridBagConstraints.EAST;
		gbc_CancelButton.insets = new Insets(0, 0, 0, 5);
		gbc_CancelButton.gridx = 0;
		gbc_CancelButton.gridy = 2;
		principalPanel.add(CancelButton, gbc_CancelButton);
		
		ContinueButton = new JButton("Continuar");
		GridBagConstraints gbc_ContinueButton = new GridBagConstraints();
		gbc_ContinueButton.anchor = GridBagConstraints.NORTHEAST;
		gbc_ContinueButton.gridx = 1;
		gbc_ContinueButton.gridy = 2;
		principalPanel.add(ContinueButton, gbc_ContinueButton);
		setVisible(true);
	}
	
	
	private void setListeners(){
		
	}

}
