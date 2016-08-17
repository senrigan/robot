package com.gdc.nms.robot;





import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.gdc.nms.robot.gui.RobotManager;
import com.gdc.nms.robot.util.Language;

public class Main {
	 public static void main(String[] args) {
//		 Language.load();
//		 RobotManager robotManager= new RobotManager();
//		 robotManager.start();
		 final JFrame rootframe = new JFrame("Time Series Mining");
		    final JPanel mainPanel = new JPanel(new BorderLayout());
		    rootframe.setSize(new Dimension(400,400));
		    rootframe.setContentPane(mainPanel);
		    mainPanel.setLayout(new BorderLayout());
		    JPanel center=new JPanel(new GridLayout(2,1));
		    JPanel forSpecific=new JPanel();
		    forSpecific.setLayout(new BoxLayout(forSpecific, BoxLayout.Y_AXIS));
//		    JPanel test1 = new JPanel();
//		    test1.setPreferredSize(new Dimension(500,500));
		    JButton te=new JButton("hola mundo cruel");
		    te.setMargin(new Insets(0, 150, 0, 20));
		    forSpecific.add(te);
		    forSpecific.add(new JButton("hola"));
		    forSpecific.add(new JButton("hola"));
		    forSpecific.add(new JButton("hola"));
		    forSpecific.add(new JButton("hola"));
		    forSpecific.add(new JButton("hola"));
		    forSpecific.add(new JButton("hola"));
		    forSpecific.add(new JButton("hola"));

		    forSpecific.add(new JButton("hola"));

//		    test1.setBackground(Color.white);
//		    test1.add(new JButton("hola"));
//		    test1.add(new JButton("hola"));
//		    test1.add(new JButton("hola"));

		    final JScrollPane scrollSpecific = new JScrollPane(forSpecific);
		    center.add(scrollSpecific);
		    rootframe.add(center, BorderLayout.CENTER);
		    rootframe.setVisible(true);
	}
	 
}

