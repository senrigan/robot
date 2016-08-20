package com.gdc.nms.robot.gui.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.gdc.nms.robot.gui.newInterface.ButtonListener;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;
import sun.font.LayoutPathImpl.EmptyPath;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class tet extends JFrame {
	private ImageIcon executeIcon = new ImageIcon(
			"C:\\Users\\senrigan\\Documents\\desarrollo\\PachitaWindows\\icon\\icn-ejecutar-06.png");
	private ImageIcon infoIcon = new ImageIcon(
			"C:\\Users\\senrigan\\Documents\\desarrollo\\PachitaWindows\\icon\\icn-info-rbt-07.png");
	private ImageIcon logIcon = new ImageIcon(
			"C:\\Users\\senrigan\\Documents\\desarrollo\\PachitaWindows\\icon\\icn-logs-08.png");
	private ImageIcon addRobotIcon = new ImageIcon(
			"C:\\Users\\senrigan\\Documents\\desarrollo\\PachitaWindows\\icon\\icn-agregar-05.png");
	private ImageIcon deleteRobotIcon = new ImageIcon(
			"C:\\Users\\senrigan\\Documents\\desarrollo\\PachitaWindows\\icon\\icn-eliminar-04.png");
	private ImageIcon configIcon = new ImageIcon(
			"C:\\Users\\senrigan\\Documents\\desarrollo\\PachitaWindows\\icon\\icn-config-03.png");
	private ImageIcon redStatusIcon=new ImageIcon("C:\\Users\\senrigan\\Documents\\desarrollo\\PachitaWindows\\icon\\icn-rojo-10.png");
	private ImageIcon greenStatusIcon=new ImageIcon("C:\\Users\\senrigan\\Documents\\desarrollo\\PachitaWindows\\icon\\icn-verde-10.png");

	private JPanel contentPane;
	private JPanel verticalBox;
	private JScrollPane buttonScrollPanel;
	private JPanel contentCountPaneL;
	private JPanel panel_3;
	private JPanel menuPanel;
	private JLabel titleAbove;
	private HashMap<String,JButton> mapRobots;
	private RoundJTextArea infoArea;
	private JScrollPane scrollPane;
	private JButton showLogs;
	private JButton getInfoRobot;
	private JButton ActionStatusRobot;
	private JPanel panel;
	private RoundButton stoppedRobotCount;
	private RoundButton executionCountButton;
	private JButton ConfigurationMenu;
	private JButton deleteRobotMenu;
	private JButton addRobotMenu;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_6;
	private JLabel aplicationNameLabel;
	private JLabel lblNewLabel_7;
	private JLabel aliasNameLabel;
	private JLabel lblNewLabel_8;
	private JLabel robotIdLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
					tet frame = new tet();
					frame.addNewRobotUI("mudo");
//					frame.addNewRobotUI("kk");
//					frame.addNewRobotUI("ss");
//					frame.addNewRobotUI("test");
//					frame.addNewRobotUI("mudo");
//					frame.addNewRobotUI("kk");
//					frame.addNewRobotUI("ss");
//					frame.addNewRobotUI("test");
//					frame.addNewRobotUI("mudo");
//					frame.addNewRobotUI("kk");
//					frame.addNewRobotUI("ss");
//					frame.addNewRobotUI("test");
//					frame.addNewRobotUI("mudo");
//					frame.addNewRobotUI("kk");
//					frame.addNewRobotUI("ss");
//					frame.addNewRobotUI("test");
//					frame.addNewRobotUI("kk");
//					frame.addNewRobotUI("ss");
//					frame.addNewRobotUI("test");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	public void changeIcons() {
		// Image img = executeIcon.getImage() ;
		// Image newimg = img.getScaledInstance( 19,25,
		// java.awt.Image.SCALE_SMOOTH ) ;
		// executeIcon=new ImageIcon(newimg);
		executeIcon = changeIconSize(13, 19, executeIcon);
		// img=infoIcon.getImage();
		// newimg = img.getScaledInstance( 32,37, java.awt.Image.SCALE_SMOOTH )
		// ;
		//
		// infoIcon = new ImageIcon( newimg );
		infoIcon = changeIconSize(22, 27, infoIcon);
		//
		// img = logIcon.getImage() ;
		// newimg = img.getScaledInstance( 35,39, java.awt.Image.SCALE_SMOOTH )
		// ;
		// logIcon = new ImageIcon( newimg );

		logIcon = changeIconSize(23, 27, logIcon);
		// img=addRobotIcon.getImage();
		// newimg=img.getScaledInstance( 35,39, java.awt.Image.SCALE_SMOOTH ) ;
		// addRobotIcon=new ImageIcon(newimg);
		//
		addRobotIcon = changeIconSize(15, 15, addRobotIcon);

		deleteRobotIcon = changeIconSize(15, 15, deleteRobotIcon);

		configIcon = changeIconSize(15, 15, configIcon);
		
		redStatusIcon=changeIconSize(15, 15, redStatusIcon);
		
		greenStatusIcon=changeIconSize(15, 15, greenStatusIcon);

	}

	private ImageIcon changeIconSize(int width, int height, ImageIcon icon) {
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newimg);
	}
	
	private void modWindows(){
		this.setResizable(false);
	}
	
	private void createLeftContent(){
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(234, 244, 254));
		contentPane.add(leftPanel, "cell 1 3 10 19,grow");
		GridBagLayout gbl_leftPanel = new GridBagLayout();
		gbl_leftPanel.columnWidths = new int[] { 0 };
		gbl_leftPanel.rowHeights = new int[] { 60, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 49, 55, 0 };
		gbl_leftPanel.columnWeights = new double[] { 1.0 };
		gbl_leftPanel.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		leftPanel.setLayout(gbl_leftPanel);

		JPanel titlePaneL = new JPanel();
		titlePaneL.setBackground(new Color(65, 95, 124));
		GridBagConstraints gbc_titlePaneL = new GridBagConstraints();
		gbc_titlePaneL.insets = new Insets(0, 0, 0, 0);
		gbc_titlePaneL.fill = GridBagConstraints.BOTH;
		gbc_titlePaneL.gridx = 0;
		gbc_titlePaneL.gridy = 0;
		leftPanel.add(titlePaneL, gbc_titlePaneL);
		GridBagLayout gbl_titlePaneL = new GridBagLayout();
		gbl_titlePaneL.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_titlePaneL.rowHeights = new int[] { 0, 0, 0 };
		gbl_titlePaneL.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_titlePaneL.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		titlePaneL.setLayout(gbl_titlePaneL);

		JLabel lblNewLabel_3 = new JLabel("Robots registrados");
		lblNewLabel_3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.gridwidth = 3;
		gbc_lblNewLabel_3.gridheight = 2;
		gbc_lblNewLabel_3.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 0;
		titlePaneL.add(lblNewLabel_3, gbc_lblNewLabel_3);

		verticalBox=new JPanel();
		verticalBox.setLayout(new BoxLayout(verticalBox, BoxLayout.Y_AXIS));
		verticalBox.setBackground(new Color(234, 244, 254));
//		verticalBox.add(new JButton("hola"));
//		verticalBox.setPreferredSize(new Dimension(200, 300));
		
		JPanel mainPanel = new JPanel(new BorderLayout());
	    JPanel robotPanelContentL=new JPanel(new GridLayout(1,0));
	    robotPanelContentL.setBackground(new Color(234	, 244, 254));
		buttonScrollPanel = new JScrollPane(verticalBox);
		buttonScrollPanel.getViewport().setBackground(new Color(234, 244, 254));
		buttonScrollPanel.getViewport().setOpaque(true);
		buttonScrollPanel.setBorder(null);
		robotPanelContentL.add(buttonScrollPanel);
		// JViewport viewport = new JViewport();
		//
		//
		// //Component that need to be added in Scroll pane//
		//
		// viewport.setView(new JPanel());
		//
		// viewport.setOpaque(false);
		//
		// scrollPane_1.setViewport(viewport);
		//
		// scrollPane_1.getViewport().setOpaque(false);
		//
		// scrollPane_1.setOpaque(false);

		// // Add Scrollpane to Jframe or JPanel//
		// scrollPane_1.setOpaque(false);
		// scrollPane_1.setBackground(new Color(200, 244, 254));
		// scrollPane_1.getViewport().setOpaque(false);
		// scrollPane_1.getViewport().setBackground(new Color(200, 244, 254));
		GridBagConstraints gbc_robotPanelContentL = new GridBagConstraints();
		gbc_robotPanelContentL.gridheight = 12;
		gbc_robotPanelContentL.insets = new Insets(0, 0, 0, 0);
		gbc_robotPanelContentL.fill = GridBagConstraints.BOTH;
		gbc_robotPanelContentL.gridx = 0;
		gbc_robotPanelContentL.gridy = 1;
//		panel.add(buttonScrollPanel, gbc_buttonScrollPanel);
		leftPanel.add(robotPanelContentL, gbc_robotPanelContentL);


		contentCountPaneL = new JPanel();
		contentCountPaneL.setBackground(new Color(65, 95, 124));
		GridBagConstraints gbc_contentCountPaneL = new GridBagConstraints();
		gbc_contentCountPaneL.fill = GridBagConstraints.BOTH;
		gbc_contentCountPaneL.gridx = 0;
		gbc_contentCountPaneL.gridy = 13;
		leftPanel.add(contentCountPaneL, gbc_contentCountPaneL);
		GridBagLayout gbl_contentCountPaneL = new GridBagLayout();
		gbl_contentCountPaneL.columnWidths = new int[]{0, 0, 0, 42, 0, 0};
		gbl_contentCountPaneL.rowHeights = new int[]{0, 0, 0};
		gbl_contentCountPaneL.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentCountPaneL.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentCountPaneL.setLayout(gbl_contentCountPaneL);
		
		executionCountButton = new RoundButton("10");
		executionCountButton.setBackground(new Color(35, 181, 116));
//		btnNewButton_6.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		executionCountButton.setMargin(new Insets(1, 1, 1, 1));
		executionCountButton.setForeground(new Color(254, 255, 255));
		executionCountButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		executionCountButton.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_executionCountButton = new GridBagConstraints();
		gbc_executionCountButton.gridheight = 2;
		gbc_executionCountButton.insets = new Insets(17, 20, 0,10 );
		gbc_executionCountButton.gridx = 0;
		gbc_executionCountButton.gridy = 0;
		contentCountPaneL.add(executionCountButton, gbc_executionCountButton);
		
		JLabel lblNewLabel_4 = new JLabel("En ejecucion");
		lblNewLabel_4.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.gridheight = 2;
		gbc_lblNewLabel_4.insets = new Insets(17, 0, 0, 0);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 0;
		contentCountPaneL.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		stoppedRobotCount = new RoundButton("30");
		stoppedRobotCount.setBackground(new Color(227, 69, 69));
		stoppedRobotCount.setForeground(new Color(254, 255, 255));
		stoppedRobotCount.setFont(new Font("Tahoma", Font.PLAIN, 10));
//		btnNewButton_7.setText("1");
		stoppedRobotCount.setMargin(new Insets(1, 1, 1, 1));
		stoppedRobotCount.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_stoppedRobotCount = new GridBagConstraints();
		gbc_stoppedRobotCount.gridheight = 2;
		gbc_stoppedRobotCount.insets = new Insets(17, 0, 0, 0);
		gbc_stoppedRobotCount.gridx = 3;
		gbc_stoppedRobotCount.gridy = 0;
		contentCountPaneL.add(stoppedRobotCount, gbc_stoppedRobotCount);
		
		JLabel lblNewLabel_5 = new JLabel("Detenidos");
		lblNewLabel_5.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.gridheight = 2;
		gbc_lblNewLabel_5.insets = new Insets(17, 0, 0, 0);
		gbc_lblNewLabel_5.gridx = 4;
		gbc_lblNewLabel_5.gridy = 0;
		contentCountPaneL.add(lblNewLabel_5, gbc_lblNewLabel_5);
	}
	
	private void createRigthContent(){
		panel_3 = new JPanel();
		panel_3.setBorder(BorderFactory.createEmptyBorder(-1, 0, 1,0));
		panel_3.setBackground(new Color(234, 244, 254));

		contentPane.add(panel_3, "cell 12 3 20 19,grow");
		// panel_3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, -10));
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 43, 0, 0, 114, 0, 0, 0 };
		gbl_panel_3.rowHeights = new int[] { 60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_3.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 20.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		panel_3.setLayout(gbl_panel_3);

		panel = new JPanel();
		panel.setBackground(new Color(65, 95, 124));

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 158, 0, 84, 40 };
		gbl_panel.rowHeights = new int[] { 40, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, 1.0, 0.0 };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 6;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panel_3.add(panel, gbc_panel);
		ActionStatusRobot = new JButton("Ejecutar robot");
		ActionStatusRobot.setBorderPainted(false);
		// btnNewButton.setBorder(BorderFactory.createEmptyBorder());
		ActionStatusRobot.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ActionStatusRobot.setForeground(SystemColor.text);
		ActionStatusRobot.setIcon(executeIcon);
		ActionStatusRobot.setBackground(new Color(0, 0, 0, 0));
		ActionStatusRobot.setContentAreaFilled(false);
		// btnNewButton.setOpaque(true);
		GridBagConstraints gbc_ActionStatusRobot = new GridBagConstraints();
		gbc_ActionStatusRobot.gridheight = 2;
		gbc_ActionStatusRobot.insets = new Insets(0, 0, 0, 0);
		gbc_ActionStatusRobot.gridx = 0;
		gbc_ActionStatusRobot.gridy = 0;
		panel.add(ActionStatusRobot, gbc_ActionStatusRobot);

		getInfoRobot = new JButton("Obtener Informacion");
		getInfoRobot.setBorderPainted(false);
		// btnNewButton_1.setBorder(BorderFactory.createEmptyBorder());
		getInfoRobot.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getInfoRobot.setForeground(SystemColor.inactiveCaptionBorder);
		getInfoRobot.setIcon(infoIcon);
		GridBagConstraints gbc_getInfoRobot = new GridBagConstraints();
		gbc_getInfoRobot.gridheight = 2;
		getInfoRobot.setBackground(new Color(65, 95, 124));
		getInfoRobot.setContentAreaFilled(false);
		getInfoRobot.setOpaque(true);
		gbc_getInfoRobot.insets = new Insets(0, 0, 0, 0);
		gbc_getInfoRobot.gridx = 1;
		gbc_getInfoRobot.gridy = 0;
		panel.add(getInfoRobot, gbc_getInfoRobot);

		showLogs = new JButton("Ver Logs");
		showLogs.setBorderPainted(false);
		showLogs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		showLogs.setForeground(SystemColor.inactiveCaptionBorder);
		showLogs.setIcon(logIcon);
		GridBagConstraints gbc_showLogs = new GridBagConstraints();
		gbc_showLogs.gridwidth = 0;
		gbc_showLogs.gridheight = 0;
		gbc_showLogs.anchor = GridBagConstraints.WEST;
		showLogs.setBackground(new Color(65, 95, 124));
		showLogs.setContentAreaFilled(false);
		showLogs.setOpaque(true);
		gbc_showLogs.insets = new Insets(0, 0, 0, 0);
		gbc_showLogs.gridx = 2;
		gbc_showLogs.gridy = 0;
		panel.add(showLogs, gbc_showLogs);
//		center.add(arg0)
		scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(new Color(234, 244, 254));
		scrollPane.getViewport().setOpaque(true);
		
		createInfoImportant();
		
	
		scrollPane.setBorder(null);

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.gridheight = 6;
		gbc_scrollPane.insets = new Insets(100,20, 50, 20);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 7;
		infoArea = new RoundJTextArea();
		// textArea.setEditable(true);
		// textArea.setEnabled(true);
		scrollPane.setViewportView(infoArea);
		panel_3.add(scrollPane, gbc_scrollPane);
	}
	
	private void createInfoImportant(){
		lblNewLabel = new JLabel("Generales");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel_3.add(lblNewLabel, gbc_lblNewLabel);
		
		lblNewLabel_6 = new JLabel("Nombre de Aplicacion :");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.gridwidth = 2;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 2;
		panel_3.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		aplicationNameLabel = new JLabel("AplicationName");
		aplicationNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_aplicationNameLabel = new GridBagConstraints();
		gbc_aplicationNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_aplicationNameLabel.gridx = 2;
		gbc_aplicationNameLabel.gridy = 2;
		panel_3.add(aplicationNameLabel, gbc_aplicationNameLabel);
		
		lblNewLabel_7 = new JLabel("Alias:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.gridwidth = 2;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 3;
		panel_3.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		aliasNameLabel = new JLabel("AliasName");
		aliasNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_aliasNameLabel = new GridBagConstraints();
		gbc_aliasNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_aliasNameLabel.gridx = 2;
		gbc_aliasNameLabel.gridy = 3;
		panel_3.add(aliasNameLabel, gbc_aliasNameLabel);
		
		lblNewLabel_8 = new JLabel("Id Robot:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.gridwidth = 2;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 4;
		panel_3.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		robotIdLabel = new JLabel("robotId");
		robotIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_robotIdLabel = new GridBagConstraints();
		gbc_robotIdLabel.insets = new Insets(0, 0, 5, 5);
		gbc_robotIdLabel.gridx = 2;
		gbc_robotIdLabel.gridy = 4;
		panel_3.add(robotIdLabel, gbc_robotIdLabel);
	}
	private void createMenuPanel(){
		menuPanel = new JPanel();
		menuPanel.setOpaque(false);
		contentPane.add(menuPanel, "cell 15 1 14 1,grow");
		GridBagLayout gbl_menuPanel = new GridBagLayout();
		gbl_menuPanel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_menuPanel.rowHeights = new int[] { 0 };
		gbl_menuPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0 };
		gbl_menuPanel.rowWeights = new double[] { Double.MIN_VALUE };
		menuPanel.setLayout(gbl_menuPanel);

		addRobotMenu = new JButton("Agregar robot");
		addRobotMenu.setBorder(BorderFactory.createEmptyBorder());
		addRobotMenu.setForeground(SystemColor.inactiveCaptionBorder);
		addRobotMenu.setIcon(addRobotIcon);
		addRobotMenu.setBackground(new Color(58, 135, 249));
		addRobotMenu.setContentAreaFilled(false);
		addRobotMenu.setOpaque(true);
		GridBagConstraints gbc_addRobotMenu = new GridBagConstraints();
		gbc_addRobotMenu.insets = new Insets(0, 0, 0, 5);
		gbc_addRobotMenu.gridx = 0;
		gbc_addRobotMenu.gridy = 0;
		menuPanel.add(addRobotMenu, gbc_addRobotMenu);

		JLabel lblNewLabel_1 = new JLabel("|");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		menuPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		deleteRobotMenu = new JButton("Eliminar robot");
		deleteRobotMenu.setBorder(BorderFactory.createEmptyBorder());
		deleteRobotMenu.setForeground(SystemColor.inactiveCaptionBorder);

		deleteRobotMenu.setIcon(deleteRobotIcon);
		deleteRobotMenu.setBackground(new Color(58, 135, 249));
		deleteRobotMenu.setContentAreaFilled(false);
		deleteRobotMenu.setOpaque(true);
		GridBagConstraints gbc_deleteRobotMenu = new GridBagConstraints();
		gbc_deleteRobotMenu.insets = new Insets(0, 0, 0, 5);
		gbc_deleteRobotMenu.gridx = 2;
		gbc_deleteRobotMenu.gridy = 0;
		menuPanel.add(deleteRobotMenu, gbc_deleteRobotMenu);

		JLabel lblNewLabel_2 = new JLabel("|");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 0;
		menuPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		ConfigurationMenu = new JButton("Configuracion");
		ConfigurationMenu.setBorder(BorderFactory.createEmptyBorder());
		ConfigurationMenu.setForeground(SystemColor.inactiveCaptionBorder);

		ConfigurationMenu.setIcon(configIcon);
		ConfigurationMenu.setBackground(new Color(58, 135, 249));
		ConfigurationMenu.setContentAreaFilled(false);
		ConfigurationMenu.setOpaque(true);
		GridBagConstraints gbc_ConfigurationMenu = new GridBagConstraints();
		gbc_ConfigurationMenu.gridx = 4;
		gbc_ConfigurationMenu.gridy = 0;
		menuPanel.add(ConfigurationMenu, gbc_ConfigurationMenu);
	}
	
	private void createTitleContent(){
		setTitle("Sispro Robot Manager");
		changeIcons();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(58, 135, 249));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("",
				"[10px:10px:10px][20px:20px:20px,grow,left][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px,grow][20px:20px:20px][20px:20px:20px][20px:20px:20px][][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][]",
				"[20px:20px:20px][20px:20px:20px,grow][20px:20px:20px][20px:20px:20px,grow][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][20px:20px:20px][10px:10px:10px]"));

		titleAbove = new JLabel("<html> <b> Sispro </b> Robot Manager</html>");
		titleAbove.setForeground(Color.WHITE);
		titleAbove.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(titleAbove, "cell 1 1 9 1");
	}
	private void initComponents(){
	
		modWindows();
		createTitleContent();
		createMenuPanel();
		createLeftContent();
		createRigthContent();
		this.setVisible(true);
		mapRobots=new HashMap<String,JButton>();
	}
	
	public void  addNewRobotUI(final String robotName){
		if(EventQueue.isDispatchThread()){
			System.out.println("is in edt");
		}else{
			System.out.println("no esta en edt");
		}
		try {
			java.awt.EventQueue.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("addd **"+robotName);
					JButton button=new JButton(robotName);
					button.addActionListener(new ButtonListener());
					Border line = BorderFactory.createLineBorder(new Color(172, 189, 207));
					Border empty = new EmptyBorder(15, 10, 15, 0);
					CompoundBorder border = new CompoundBorder(line, empty);
					button.setBorder(border);
					button.setForeground(new Color(67,91,125));
					button.setMinimumSize(new Dimension(verticalBox.getWidth(), 50));
					button.setMaximumSize(new Dimension(verticalBox.getWidth(), 50));
					button.setOpaque(false);
					button.setHorizontalAlignment(SwingConstants.LEFT);
					
					button.setBackground(new Color(0, 0, 0,0));
					button.setFont(new Font("Tahoma",Font.PLAIN, 15));
					verticalBox.add(button);
					mapRobots.put(robotName, button);
					button.setIcon(greenStatusIcon);
					button.setIconTextGap(20);
					button.setPressedIcon(greenStatusIcon);
					
				}
			});
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void removeRobot(String robotName) throws Exception{
		if(mapRobots.containsKey(robotName)){
			JButton jButton = mapRobots.get(robotName);
			verticalBox.remove(jButton);
		}else{
			throw new Exception("the robot "+robotName +"is no found in the list");
		}
		
	}
	public void changeStatus(JButton button){
		System.out.println("change ststaus"+button.getIcon());
		if(button.getIcon().equals(redStatusIcon)){
			System.out.println("is equal red");
			button.setIcon(greenStatusIcon);
		}else if(button.getIcon().equals(greenStatusIcon)){
			System.out.println("is green equal");
			button.setIcon(redStatusIcon);
		}
	}
	
	public void changeStatusToActive(JButton button){
		button.setIcon(greenStatusIcon);
	}
	
	public void changeStatusToFalse(JButton button){
		button.setIcon(redStatusIcon);
	}
	
	public void setAplicationName(String applicactionName){
		aplicationNameLabel.setText(applicactionName);
	}
	
	public void setAliasName(String aliasName){
		aliasNameLabel.setText(aliasName);
	}
	
	public void setIdRobot(String idRobot){
		robotIdLabel.setText(idRobot);
	}
	public HashMap<String, JButton> getMapRobots(){
		return mapRobots;
	}
	
	public void setTextInfo(String info){
		infoArea.setText(info);
	}
	/**
	 * Create the frame.
	 */
	public tet() {
		initComponents();
	}
}