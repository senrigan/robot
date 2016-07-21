package com.gdc.nms.robot.gui.tree.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXDatePicker;

public class test extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					test frame = new test();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		Properties p=System.getProperties();
//		p.list(System.out);
		test.updateRobot(Paths.get("C:\\Users\\senrigan\\Documents\\pruebas\\GDC\\RobotScript\\data\\32D\\bot-1.0.jar"), -805);
	}

	
	public static void updateRobot(Path robotPath , long robotId){
		try {
			FileSystem fileSystem = FileSystems.newFileSystem(robotPath, null);
			Path path=fileSystem.getPath("/META-INF/robot.properties");
			OutputStream outputStream = Files.newOutputStream(path, StandardOpenOption.WRITE);
			String str="id="+robotId;
			outputStream.write(str.getBytes());
			outputStream.close();
			fileSystem.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		final JLabel label = new JLabel();
		label.setText("Choose Date by selecting below.");
	
		final JXDatePicker datePicker = new JXDatePicker(new Date(System.currentTimeMillis()));
		datePicker.setFormats("dd-MM-yyyy HH:mm:ss");
		datePicker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar cal= Calendar.getInstance();
				cal.setTime(datePicker.getDate());
				
				label.setText(cal.toString());
			}

		});
		 
		 
		this.getContentPane().add(label, BorderLayout.NORTH);
		this.getContentPane().add(datePicker, BorderLayout.CENTER);
	}

}
