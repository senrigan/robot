package com.gdc.nms.robot.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
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
	private Box verticalBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tet frame = new tet();
					frame.setVisible(true);
					frame.addNewRobotUI("mudo");
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

		JLabel lblNewLabel = new JLabel("<html> <b> Sispro </b> Robot Manager</html>");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel, "cell 1 1 9 1");

		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		contentPane.add(panel_5, "cell 15 1 14 1,grow");
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel_5.rowHeights = new int[] { 0 };
		gbl_panel_5.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0 };
		gbl_panel_5.rowWeights = new double[] { Double.MIN_VALUE };
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

		JLabel lblNewLabel_1 = new JLabel("|");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		panel_5.add(lblNewLabel_1, gbc_lblNewLabel_1);

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

		JLabel lblNewLabel_2 = new JLabel("|");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 0;
		panel_5.add(lblNewLabel_2, gbc_lblNewLabel_2);

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

		verticalBox=Box.createVerticalBox();
//		verticalBox.add(new JButton("hola"));
		verticalBox.set
		JScrollPane scrollPane_1 = new JScrollPane(verticalBox);
		scrollPane_1.getViewport().setBackground(new Color(234, 244, 254));
		scrollPane_1.getViewport().setOpaque(true);
		scrollPane_1.setBorder(null);
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
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 42, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		RoundButton btnNewButton_6 = new RoundButton("10");
//		btnNewButton_6.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		btnNewButton_6.setMargin(new Insets(1, 1, 1, 1));
		btnNewButton_6.setForeground(Color.BLUE);
		btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_6.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.gridheight = 2;
		gbc_btnNewButton_6.insets = new Insets(17, 20, 0,10 );
		gbc_btnNewButton_6.gridx = 0;
		gbc_btnNewButton_6.gridy = 0;
		panel_2.add(btnNewButton_6, gbc_btnNewButton_6);
		
		JLabel lblNewLabel_4 = new JLabel("En ejecucion");
		lblNewLabel_4.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.gridheight = 2;
		gbc_lblNewLabel_4.insets = new Insets(17, 0, 0, 0);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 0;
		panel_2.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		RoundButton btnNewButton_7 = new RoundButton("30");
		btnNewButton_7.setForeground(Color.RED);
		btnNewButton_7.setFont(new Font("Tahoma", Font.PLAIN, 10));
//		btnNewButton_7.setText("1");
		btnNewButton_7.setMargin(new Insets(1, 1, 1, 1));
		btnNewButton_7.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.gridheight = 2;
		gbc_btnNewButton_7.insets = new Insets(17, 0, 0, 0);
		gbc_btnNewButton_7.gridx = 3;
		gbc_btnNewButton_7.gridy = 0;
		panel_2.add(btnNewButton_7, gbc_btnNewButton_7);
		
		JLabel lblNewLabel_5 = new JLabel("Detenidos");
		lblNewLabel_5.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.gridheight = 2;
		gbc_lblNewLabel_5.insets = new Insets(17, 0, 0, 0);
		gbc_lblNewLabel_5.gridx = 4;
		gbc_lblNewLabel_5.gridy = 0;
		panel_2.add(lblNewLabel_5, gbc_lblNewLabel_5);

		JPanel panel_3 = new JPanel();
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
		JButton btnNewButton = new JButton("Ejecutar robot");
		btnNewButton.setBorderPainted(false);
		// btnNewButton.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setIcon(executeIcon);
		btnNewButton.setBackground(new Color(0, 0, 0, 0));
		btnNewButton.setContentAreaFilled(false);
		// btnNewButton.setOpaque(true);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridheight = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel_4.add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_1 = new JButton("Obtener Informacion");
		btnNewButton_1.setBorderPainted(false);
		// btnNewButton_1.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setForeground(SystemColor.inactiveCaptionBorder);
		btnNewButton_1.setIcon(infoIcon);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridheight = 2;
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
	}
	
	public void  addNewRobotUI(String robotName){
		JButton button=new JButton(robotName);
		button.setContentAreaFilled(true);
		button.setPreferredSize(new Dimension(verticalBox.getWidth(), 100));
		button.setOpaque(false);
		button.setMargin(new Insets(50, 0, 50, 0));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setBackground(new Color(0, 0, 0,0));
		button.setFont(new Font("Tahoma",Font.PLAIN, 15));
		verticalBox.add(button);
	}
	/**
	 * Create the frame.
	 */
	public tet() {
		initComponents();
	}
}
