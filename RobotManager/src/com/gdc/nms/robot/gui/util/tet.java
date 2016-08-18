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
import java.awt.event.ActionEvent;

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
	private JPanel contentPane;
	private JPanel verticalBox;
	private JScrollPane buttonScrollPanel;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_5;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tet frame = new tet();
					frame.addNewRobotUI("mudo");
					frame.addNewRobotUI("kk");
					frame.addNewRobotUI("ss");
					frame.addNewRobotUI("test");
					frame.addNewRobotUI("mudo");
					frame.addNewRobotUI("kk");
					frame.addNewRobotUI("ss");
					frame.addNewRobotUI("test");
					frame.addNewRobotUI("mudo");
					frame.addNewRobotUI("kk");
					frame.addNewRobotUI("ss");
					frame.addNewRobotUI("test");
					frame.addNewRobotUI("mudo");
					frame.addNewRobotUI("kk");
					frame.addNewRobotUI("ss");
					frame.addNewRobotUI("test");
					frame.addNewRobotUI("kk");
					frame.addNewRobotUI("ss");
					frame.addNewRobotUI("test");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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

	}

	private ImageIcon changeIconSize(int width, int height, ImageIcon icon) {
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newimg);
	}
	private void initComponents(){
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

		lblNewLabel = new JLabel("<html> <b> Sispro </b> Robot Manager</html>");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel, "cell 1 1 9 1");

		panel_5 = new JPanel();
		panel_5.setOpaque(false);
		contentPane.add(panel_5, "cell 15 1 14 1,grow");
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel_5.rowHeights = new int[] { 0 };
		gbl_panel_5.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0 };
		gbl_panel_5.rowWeights = new double[] { Double.MIN_VALUE };
		panel_5.setLayout(gbl_panel_5);

		JButton addRobotMenu = new JButton("Agregar robot");
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
		panel_5.add(addRobotMenu, gbc_addRobotMenu);

		JLabel lblNewLabel_1 = new JLabel("|");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		panel_5.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JButton deleteRobotMenu = new JButton("Eliminar robot");
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
		panel_5.add(deleteRobotMenu, gbc_deleteRobotMenu);

		JLabel lblNewLabel_2 = new JLabel("|");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 0;
		panel_5.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JButton ConfigurationMenu = new JButton("Configuracion");
		ConfigurationMenu.setBorder(BorderFactory.createEmptyBorder());
		ConfigurationMenu.setForeground(SystemColor.inactiveCaptionBorder);

		ConfigurationMenu.setIcon(configIcon);
		ConfigurationMenu.setBackground(new Color(58, 135, 249));
		ConfigurationMenu.setContentAreaFilled(false);
		ConfigurationMenu.setOpaque(true);
		GridBagConstraints gbc_ConfigurationMenu = new GridBagConstraints();
		gbc_ConfigurationMenu.gridx = 4;
		gbc_ConfigurationMenu.gridy = 0;
		panel_5.add(ConfigurationMenu, gbc_ConfigurationMenu);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(234, 244, 254));
		contentPane.add(panel, "cell 1 3 10 19,grow");
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0 };
		gbl_panel.rowHeights = new int[] { 60, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 49, 55, 0 };
		gbl_panel.columnWeights = new double[] { 1.0 };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(65, 95, 124));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

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
		panel_1.add(lblNewLabel_3, gbc_lblNewLabel_3);

		verticalBox=new JPanel();
		verticalBox.setLayout(new BoxLayout(verticalBox, BoxLayout.Y_AXIS));
		verticalBox.setBackground(new Color(234, 244, 254));
//		verticalBox.add(new JButton("hola"));
//		verticalBox.setPreferredSize(new Dimension(200, 300));
		
		JPanel mainPanel = new JPanel(new BorderLayout());
	    JPanel center=new JPanel(new GridLayout(1,0));
	    center.setBackground(new Color(234	, 244, 254));
		buttonScrollPanel = new JScrollPane(verticalBox);
		buttonScrollPanel.getViewport().setBackground(new Color(234, 244, 254));
		buttonScrollPanel.getViewport().setOpaque(true);
		buttonScrollPanel.setBorder(null);
		center.add(buttonScrollPanel);
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
		GridBagConstraints gbc_buttonScrollPanel = new GridBagConstraints();
		gbc_buttonScrollPanel.gridheight = 12;
		gbc_buttonScrollPanel.insets = new Insets(0, 0, 0, 0);
		gbc_buttonScrollPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonScrollPanel.gridx = 0;
		gbc_buttonScrollPanel.gridy = 1;
//		panel.add(buttonScrollPanel, gbc_buttonScrollPanel);
		panel.add(center, gbc_buttonScrollPanel);


		panel_2 = new JPanel();
		panel_2.setBackground(new Color(65, 95, 124));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 13;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 42, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		RoundButton executionCountButton = new RoundButton("10");
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
		panel_2.add(executionCountButton, gbc_executionCountButton);
		
		JLabel lblNewLabel_4 = new JLabel("En ejecucion");
		lblNewLabel_4.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.gridheight = 2;
		gbc_lblNewLabel_4.insets = new Insets(17, 0, 0, 0);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 0;
		panel_2.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		RoundButton stoppedRobotCount = new RoundButton("30");
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
		panel_2.add(stoppedRobotCount, gbc_stoppedRobotCount);
		
		JLabel lblNewLabel_5 = new JLabel("Detenidos");
		lblNewLabel_5.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.gridheight = 2;
		gbc_lblNewLabel_5.insets = new Insets(17, 0, 0, 0);
		gbc_lblNewLabel_5.gridx = 4;
		gbc_lblNewLabel_5.gridy = 0;
		panel_2.add(lblNewLabel_5, gbc_lblNewLabel_5);

		panel_3 = new JPanel();
		panel_3.setBorder(BorderFactory.createEmptyBorder(-1, 0, 1,0));
		panel_3.setBackground(new Color(234, 244, 254));

		contentPane.add(panel_3, "cell 12 3 20 19,grow");
		// panel_3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, -10));
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 43, 0, 0, 114, 0, 0, 0 };
		gbl_panel_3.rowHeights = new int[] { 60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_3.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, 20.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		panel_3.setLayout(gbl_panel_3);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(65, 95, 124));

		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] { 158, 0, 84, 40 };
		gbl_panel_4.rowHeights = new int[] { 40, 0 };
		gbl_panel_4.columnWeights = new double[] { 1.0, 1.0, 1.0, 0.0 };
		gbl_panel_4.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_4.setLayout(gbl_panel_4);

		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridwidth = 6;
		gbc_panel_4.insets = new Insets(0, 0, 0, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		panel_3.add(panel_4, gbc_panel_4);
		JButton ActionStatusRobot = new JButton("Ejecutar robot");
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
		panel_4.add(ActionStatusRobot, gbc_ActionStatusRobot);

		JButton getInfoRobot = new JButton("Obtener Informacion");
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
		panel_4.add(getInfoRobot, gbc_getInfoRobot);

		JButton showLogs = new JButton("Ver Logs");
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
		panel_4.add(showLogs, gbc_showLogs);
//		center.add(arg0)
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(new Color(234, 244, 254));
		scrollPane.getViewport().setOpaque(true);
		scrollPane.setBorder(null);

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.gridheight = 7;
		gbc_scrollPane.insets = new Insets(100,20, 50, 20);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 7;
		RoundJTextArea textArea = new RoundJTextArea();
		// textArea.setEditable(true);
		// textArea.setEnabled(true);
		scrollPane.setViewportView(textArea);
		panel_3.add(scrollPane, gbc_scrollPane);
		this.setVisible(true);
	}
	
	public void  addNewRobotUI(String robotName){
		JButton button=new JButton(robotName);
		button.addActionListener(new ButtonListener());
//		button.setContentAreaFilled(true);
		Border line = BorderFactory.createLineBorder(new Color(172, 189, 207));
		Border empty = new EmptyBorder(15, 0, 15, 0);
		CompoundBorder border = new CompoundBorder(line, empty);
//		button.setBorder(new LineBorder(new Color(172, 189, 207)));
//		button.setMargin(new Insets(15,0, 15,0));
		button.setBorder(border);
		button.setForeground(new Color(67,91,125));
		button.setMinimumSize(new Dimension(verticalBox.getWidth(), 50));
		button.setMaximumSize(new Dimension(verticalBox.getWidth(), 50));
//		button.setPreferredSize(new Dimension(500, 0));
		button.setOpaque(false);
		button.setHorizontalAlignment(SwingConstants.CENTER);

//		button.setMargin(new Insets(50, 0, 50, 0));
//		button.setBorder(BorderFactory.createEmptyBorder());
		button.setBackground(new Color(0, 0, 0,0));
		button.setFont(new Font("Tahoma",Font.PLAIN, 15));
		verticalBox.add(button);
//		System.out.println("width"+verticalBox.getWidth()+"height"+verticalBox.getHeight());
	}
	/**
	 * Create the frame.
	 */
	public tet() {
		initComponents();
	}
}
