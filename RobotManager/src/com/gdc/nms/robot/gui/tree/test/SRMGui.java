package com.gdc.nms.robot.gui.tree.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;

public class SRMGui extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SRMGui frame = new SRMGui();
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
	public SRMGui() {
		setTitle("Sispro Robot Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 808, 575);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(58, 135, 249));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 344, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		ImageIcon icon=new ImageIcon("C:\\Users\\senrigan\\Desktop\\sispro robot manager.png");
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance( 700,70,  java.awt.Image.SCALE_SMOOTH ) ; 
		icon = new ImageIcon( newimg );
		
		JLabel lblNewLabel_2 = new JLabel("<html> <b> Sispro </b> Robot Manager</html>");
		lblNewLabel_2.setForeground(new Color(254, 255, 255));
		lblNewLabel_2.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
		System.out.println(lblNewLabel_2.getText());
		

		lblNewLabel_2.setFont(new Font("Tahoma", lblNewLabel_2.getFont().getStyle(), 20));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridheight = 2;
		gbc_lblNewLabel_2.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 1;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(58, 135, 249));
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.gridwidth = 4;
		gbc_panel_5.insets = new Insets(0, 0, 5, 5);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 4;
		gbc_panel_5.gridy = 1;
		contentPane.add(panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_5.rowHeights = new int[]{0, 0};
		gbl_panel_5.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JButton btnNewButton_3 = new JButton("Agregar robot");
		btnNewButton_3.setForeground(SystemColor.inactiveCaptionBorder);
		icon=new ImageIcon("C:\\Users\\senrigan\\Documents\\desarrollo\\PachitaWindows\\icon\\icn-agregar-05.png");
		img = icon.getImage() ;  
		newimg = img.getScaledInstance( 15,15,  java.awt.Image.SCALE_SMOOTH ) ; 
		icon = new ImageIcon( newimg );
		btnNewButton_3.setIcon(icon);
		btnNewButton_3.setBackground(new Color(58, 135, 249));
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setOpaque(true);
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.gridx = 3;
		gbc_btnNewButton_3.gridy = 0;
		panel_5.add(btnNewButton_3, gbc_btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Eliminar robot");
		btnNewButton_4.setForeground(SystemColor.inactiveCaptionBorder);
		icon=new ImageIcon("C:\\Users\\senrigan\\Documents\\desarrollo\\PachitaWindows\\icon\\icn-eliminar-04.png");
		img = icon.getImage() ;  
		newimg = img.getScaledInstance( 15,15,  java.awt.Image.SCALE_SMOOTH ) ; 
		icon = new ImageIcon( newimg );
		btnNewButton_4.setIcon(icon);
		
		JLabel label = new JLabel("|");
		label.setForeground(SystemColor.inactiveCaptionBorder);
		label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 4;
		gbc_label.gridy = 0;
		panel_5.add(label, gbc_label);
		btnNewButton_4.setIcon(icon);
		btnNewButton_4.setBackground(new Color(58, 135, 249));
		btnNewButton_4.setContentAreaFilled(false);
		btnNewButton_4.setOpaque(true);
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_4.gridx = 5;
		gbc_btnNewButton_4.gridy = 0;
		panel_5.add(btnNewButton_4, gbc_btnNewButton_4);
		
		JLabel label_1 = new JLabel("|");
		label_1.setForeground(SystemColor.inactiveCaptionBorder);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 0, 5);
		gbc_label_1.gridx = 6;
		gbc_label_1.gridy = 0;
		panel_5.add(label_1, gbc_label_1);
		
		JButton btnNewButton_5 = new JButton("Configuracion");
		btnNewButton_5.setForeground(SystemColor.inactiveCaptionBorder);
		btnNewButton_5.setIcon(new ImageIcon("C:\\Users\\senrigan\\Documents\\desarrollo\\PachitaWindows\\icon\\icn-config-03.png"));
		btnNewButton_5.setBackground(new Color(58, 135, 249));
		btnNewButton_5.setContentAreaFilled(false);
		btnNewButton_5.setOpaque(true);
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_5.gridx = 7;
		gbc_btnNewButton_5.gridy = 0;
		panel_5.add(btnNewButton_5, gbc_btnNewButton_5);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 7;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 3;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 10, 0, 0, 0, 0, 188, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(65, 95, 124));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.anchor = GridBagConstraints.NORTH;
		gbc_panel_3.gridheight = 2;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridwidth = 5;
		gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 42, 137, 0};
		gbl_panel_3.rowHeights = new int[]{21, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblNewLabel = new JLabel("Robot Registrados");
		lblNewLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setForeground(new Color(254, 255, 255));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_3.add(lblNewLabel, gbc_lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBackground(new Color(65, 95, 124));
		lblNewLabel.setOpaque(true);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.anchor = GridBagConstraints.WEST;
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.gridwidth = 5;
		gbc_panel_4.gridheight = 5;
		gbc_panel_4.fill = GridBagConstraints.VERTICAL;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 2;
		panel.add(panel_4, gbc_panel_4);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(65, 95, 124));
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.anchor = GridBagConstraints.SOUTH;
		gbc_panel_6.gridwidth = 5;
		gbc_panel_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 7;
		panel.add(panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_6.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_6.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		
		JLabel lblNewLabel_1 = new JLabel("En ejecucion");
		lblNewLabel_1.setForeground(SystemColor.inactiveCaptionBorder);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panel_6.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Detenidos");
		lblNewLabel_3.setForeground(SystemColor.inactiveCaptionBorder);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 4;
		gbc_lblNewLabel_3.gridy = 1;
		panel_6.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 4;
		gbc_panel_1.gridheight = 7;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 4;
		gbc_panel_1.gridy = 3;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{295, 0};
		gbl_panel_1.rowHeights = new int[]{0, 16, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(65, 95, 124));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 2;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		panel_1.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JButton btnNewButton = new JButton("Ejecutar robot");
		btnNewButton.setForeground(SystemColor.text);
		icon=new ImageIcon("C:\\Users\\senrigan\\Documents\\desarrollo\\PachitaWindows\\icon\\icn-ejecutar-06.png");
		img = icon.getImage() ;  
		newimg = img.getScaledInstance( 20,20,  java.awt.Image.SCALE_SMOOTH ) ; 
		icon = new ImageIcon( newimg );
		btnNewButton.setIcon(icon);
		btnNewButton.setBackground(new Color(65, 95, 124));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setOpaque(true);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel_2.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Obtener Informacion");
		btnNewButton_1.setForeground(SystemColor.inactiveCaptionBorder);
		icon=new ImageIcon("C:\\Users\\senrigan\\Documents\\desarrollo\\PachitaWindows\\icon\\icn-info-rbt-07.png");
		img = icon.getImage() ;  
		newimg = img.getScaledInstance( 30,30,  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon( newimg );
		btnNewButton_1.setIcon(icon);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		btnNewButton_1.setBackground(new Color(65, 95, 124));
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setOpaque(true);
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 0;
		panel_2.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Ver Logs");
		btnNewButton_2.setForeground(SystemColor.inactiveCaptionBorder);
		
		icon=new ImageIcon("C:\\Users\\senrigan\\Documents\\desarrollo\\PachitaWindows\\icon\\icn-logs-08.png");
		img = icon.getImage() ;  
		newimg = img.getScaledInstance( 30,30,  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon( newimg );
		btnNewButton_2.setIcon(icon);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.anchor = GridBagConstraints.EAST;
		btnNewButton_2.setBackground(new Color(65, 95, 124));
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setOpaque(true);
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 4;
		gbc_btnNewButton_2.gridy = 0;
		panel_2.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 7;
		panel_1.add(panel_7, gbc_panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{0};
		gbl_panel_7.rowHeights = new int[]{0};
		gbl_panel_7.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);
	}

}
