package com.gdc.nms.robot.gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import com.gdc.nms.robot.util.AppExaminator;
import com.gdc.nms.robot.util.DeleteServiceController;
import com.gdc.nms.robot.util.indexer.AppInformation;

import java.awt.GridBagLayout;
import javax.swing.JComboBox;

import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DeleteRobotPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblSeleccioneElServicio;
	private JComboBox<AppInformation> comboBox;
	private JButton continuButton;
	private final static int YES_OPTION=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DeleteRobotPanel frame = new DeleteRobotPanel();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		
		
		
		
		
		
	}

	/**
	 * Create the frame.
	 */
	public DeleteRobotPanel() {
		initComponents();
		
	}
	
	
	private void initComponents(){
		initGui();
		initListener();
		initData();
	}
	
	
	private void initGui(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblSeleccioneElServicio = new JLabel("Seleccione el Servicio a Eliminar");
		GridBagConstraints gbc_lblSeleccioneElServicio = new GridBagConstraints();
		gbc_lblSeleccioneElServicio.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccioneElServicio.gridx = 2;
		gbc_lblSeleccioneElServicio.gridy = 1;
		contentPane.add(lblSeleccioneElServicio, gbc_lblSeleccioneElServicio);
		
		comboBox = new JComboBox<AppInformation>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 3;
		contentPane.add(comboBox, gbc_comboBox);
		
		continuButton = new JButton("Continuar");
		GridBagConstraints gbc_continuButton = new GridBagConstraints();
		gbc_continuButton.insets = new Insets(0, 0, 0, 5);
		gbc_continuButton.gridx = 3;
		gbc_continuButton.gridy = 5;
		contentPane.add(continuButton, gbc_continuButton);
	}
	
	
	private void initListener(){
		initContinueListener();
		initWindowsListener();
	}
	
	private void initContinueListener(){
	continuButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int optionSelected = JOptionPane.showConfirmDialog(null, "Se procedera a eliminar el Servicio "+comboBox.getSelectedItem().toString()
						,"Eliminador de Robots",JOptionPane.YES_NO_OPTION);
				if(optionSelected==YES_OPTION){
					AppInformation app=(AppInformation)comboBox.getSelectedItem();
					DeleteServiceController deleter=new DeleteServiceController(app);
					if(deleter.deleteService()){
						JOptionPane.showMessageDialog(null,"El robot del servicio "+app.getAlias()+"ha sido eliminado correctamente");
					}else{
						JOptionPane.showMessageDialog(null,"No es posible eliminar el robot del servicio "+app.getAlias());

					}
					
				}
				System.out.println("option selected"+optionSelected);
			}
		});
	}
	
	private void initWindowsListener(){
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				RobotManager.getSRMGuiManager().alReadyInUseDeleteMenu(true);

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
				RobotManager.getSRMGuiManager().alReadyInUseDeleteMenu(false);
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				
			}
		});
	}
	

	
	
	private void initData(){
		ArrayList<AppInformation> installedApps = AppExaminator.getInstalledApps();
		for (AppInformation appInformation : installedApps) {
			
			comboBox.addItem(appInformation);
		}
	}

}
