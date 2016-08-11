package com.gdc.nms.robot.gui.util;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.awt.AWTUtilities;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

import java.awt.Color;

public class ScrollTest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScrollTest frame = new ScrollTest();
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
	public ScrollTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setViewport(new JViewport().setOpaque(false));
		scrollPane.getViewport().setOpaque(true);
//	    scrollPane.setBorder(null);
	    scrollPane.getViewport().setBackground(new Color(200, 244, 254));
//	    this.setBackground(Color.black);
	    contentPane.add(scrollPane, BorderLayout.CENTER);
	}

}
