package com.gdc.nms.robot.gui.tree.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import net.miginfocom.swing.MigLayout;
import sun.font.LayoutPathImpl.EmptyPath;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.FlowLayout;

public class tet extends JFrame {
	private ImageIcon executeIcon=new ImageIcon("C:\\Users\\senrigan\\Documents\\desarrollo\\PachitaWindows\\icon\\icn-ejecutar-06.png");
	private ImageIcon infoIcon=new ImageIcon("C:\\Users\\senrigan\\Documents\\desarrollo\\PachitaWindows\\icon\\icn-info-rbt-07.png");
	private ImageIcon logIcon=new ImageIcon("C:\\Users\\senrigan\\Documents\\desarrollo\\PachitaWindows\\icon\\icn-logs-08.png");
	private ImageIcon addRobotIcon=new ImageIcon("C:\\Users\\senrigan\\Documents\\desarrollo\\PachitaWindows\\icon\\icn-agregar-05.png");
	private ImageIcon deleteRobotIcon=new ImageIcon("C:\\Users\\senrigan\\Documents\\desarrollo\\PachitaWindows\\icon\\icn-eliminar-04.png");
	private ImageIcon configIcon=new ImageIcon("C:\\Users\\senrigan\\Documents\\desarrollo\\PachitaWindows\\icon\\icn-config-03.png");
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tet frame = new tet();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void  changeIcons(){
//		Image img = executeIcon.getImage() ;  
//		Image newimg = img.getScaledInstance( 19,25,  java.awt.Image.SCALE_SMOOTH ) ; 
//		executeIcon=new ImageIcon(newimg);
		executeIcon=changeIconSize(19, 25, executeIcon);
//		img=infoIcon.getImage();
//		newimg = img.getScaledInstance( 32,37,  java.awt.Image.SCALE_SMOOTH ) ;  
//
//		infoIcon = new ImageIcon( newimg );
		infoIcon=changeIconSize(32, 37, infoIcon);
//
//		img = logIcon.getImage() ;  
//		newimg = img.getScaledInstance( 35,39,  java.awt.Image.SCALE_SMOOTH ) ;  
//		logIcon = new ImageIcon( newimg );
		
		logIcon=changeIconSize(35, 39, logIcon);
//		img=addRobotIcon.getImage();
//		newimg=img.getScaledInstance( 35,39,  java.awt.Image.SCALE_SMOOTH ) ;  
//		addRobotIcon=new ImageIcon(newimg);
//		
		addRobotIcon=changeIconSize(20, 20, addRobotIcon);
		
		deleteRobotIcon=changeIconSize(20, 20, deleteRobotIcon);
		
		configIcon=changeIconSize(20, 20, configIcon);
		
	}
	
	
	
	private ImageIcon changeIconSize(int width,int height ,ImageIcon icon){
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance( width,height,  java.awt.Image.SCALE_SMOOTH ) ; 
		return new ImageIcon(newimg);
	}
	/**
	 * Create the frame.
	 */
	public tet() {
		setTitle("Sispro Robot Manager");
		changeIcons();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(58, 135, 249));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[10px:10px:10px][20px:20px:20px,grow,left][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px,grow][20px:20px:20px][20px:20px:20px][20px:20px:20px][][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][]", "[20px:20px:20px][20px:20px:20px,grow][20px:20px:20px][20px:20px:20px,grow][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][10px:10px:10px]"));
		
		JLabel lblNewLabel = new JLabel("<html> <b> Sispro </b> Robot Manager</html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel, "cell 1 1 9 1");
		
		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		contentPane.add(panel_5, "cell 15 1 14 1,grow");
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_5.rowHeights = new int[]{0};
		gbl_panel_5.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_panel_5.rowWeights = new double[]{Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		
		JButton btnNewButton_3 = new JButton("Agregar robot");
		btnNewButton_3.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton_3.setForeground(SystemColor.inactiveCaptionBorder);
		btnNewButton_3.setIcon(addRobotIcon);
		btnNewButton_3.setBackground(new Color(58, 135, 249));
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setOpaque(true);
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.gridx = 0;
		gbc_btnNewButton_3.gridy = 0;
		panel_5.add(btnNewButton_3, gbc_btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Eliminar robot");
		btnNewButton_4.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton_4.setForeground(SystemColor.inactiveCaptionBorder);
		
		btnNewButton_4.setIcon(deleteRobotIcon);
		btnNewButton_4.setBackground(new Color(58, 135, 249));
		btnNewButton_4.setContentAreaFilled(false);
		btnNewButton_4.setOpaque(true);
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_4.gridx = 2;
		gbc_btnNewButton_4.gridy = 0;
		panel_5.add(btnNewButton_4, gbc_btnNewButton_4);
		
		
		JButton btnNewButton_5 = new JButton("Configuracion");
		btnNewButton_5.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton_5.setForeground(SystemColor.inactiveCaptionBorder);
		
		btnNewButton_5.setIcon(configIcon);
		btnNewButton_5.setBackground(new Color(58, 135, 249));
		btnNewButton_5.setContentAreaFilled(false);
		btnNewButton_5.setOpaque(true);
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.gridx = 4;
		gbc_btnNewButton_5.gridy = 0;
		panel_5.add(btnNewButton_5, gbc_btnNewButton_5);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(200, 244, 254));
		contentPane.add(panel, "cell 1 3 10 19,grow");
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0};
		gbl_panel.rowHeights = new int[]{48, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 49, 55, 0};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		panel_1.setBackground(new Color(65, 95, 124));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.getViewport().setBackground(new Color(200, 244, 254));
		scrollPane_1.getViewport().setOpaque(true);
		scrollPane_1.setBorder(null);
//		   JViewport viewport = new JViewport();
//
//
//		 //Component that need to be added in Scroll pane//
//
//		   viewport.setView(new JPanel());
//
//		   viewport.setOpaque(false);
//
//		   scrollPane_1.setViewport(viewport);
//
//		   scrollPane_1.getViewport().setOpaque(false);
//
//		   scrollPane_1.setOpaque(false);

//		 // Add Scrollpane to Jframe or JPanel//
//		scrollPane_1.setOpaque(false);
//		scrollPane_1.setBackground(new Color(200, 244, 254));
//		scrollPane_1.getViewport().setOpaque(false);
//		scrollPane_1.getViewport().setBackground(new Color(200, 244, 254));
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 12;
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 1;
		panel.add(scrollPane_1, gbc_scrollPane_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(65, 95, 124));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 13;
		panel.add(panel_2, gbc_panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(200, 244, 254));

		contentPane.add(panel_3, "cell 12 3 18 19,grow");
//		panel_3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, -10));
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{43, 0, 0, 114, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(65, 95, 124));
		
		
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{158, 0, 84, 0};
		gbl_panel_4.rowHeights = new int[] {40, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_panel_4.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);

		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridwidth = 6;
		gbc_panel_4.insets = new Insets(0, 0, 0, 0);
		gbc_panel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		panel_3.add(panel_4, gbc_panel_4);
		JButton btnNewButton = new JButton("Ejecutar robot");
		btnNewButton.setBorderPainted(false);
//		btnNewButton.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setIcon(executeIcon);
		btnNewButton.setBackground(new Color(65, 95, 124));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setOpaque(true);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel_4.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Obtener Informacion");
		btnNewButton_1.setBorderPainted(false);
//		btnNewButton_1.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setForeground(SystemColor.inactiveCaptionBorder);
		btnNewButton_1.setIcon(infoIcon);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		btnNewButton_1.setBackground(new Color(65, 95, 124));
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setOpaque(true);
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 0);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 0;
		panel_4.add(btnNewButton_1, gbc_btnNewButton_1);
		
		
		JButton btnNewButton_2 = new JButton("Ver Logs");
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setForeground(SystemColor.inactiveCaptionBorder);
		btnNewButton_2.setIcon(logIcon);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.gridwidth = 0;
		gbc_btnNewButton_2.gridheight = 0;
		gbc_btnNewButton_2.anchor = GridBagConstraints.WEST;
		btnNewButton_2.setBackground(new Color(65, 95, 124));
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setOpaque(true);
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 0);
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 0;
		panel_4.add(btnNewButton_2, gbc_btnNewButton_2);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.getViewport().setBackground(new Color(200, 244, 254));
				scrollPane.getViewport().setOpaque(true);
				scrollPane.setBorder(null);
				
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.gridwidth = 5;
				gbc_scrollPane.gridheight = 7;
				gbc_scrollPane.insets = new Insets(100, 20,50, 20);
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 7;
				RoundJTextArea textArea = new RoundJTextArea();
				//		textArea.setEditable(true);
				//		textArea.setEnabled(true);
						scrollPane.setViewportView(textArea);
						panel_3.add(scrollPane, gbc_scrollPane);
	}
}