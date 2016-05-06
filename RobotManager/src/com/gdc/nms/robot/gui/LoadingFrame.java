package com.gdc.nms.robot.gui;





import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import java.awt.BorderLayout;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class LoadingFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
    JLabel imageLabel = new JLabel();
    JLabel headerLabel = new JLabel();

    public LoadingFrame() {
        try {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            contentPane = (JPanel) getContentPane();
            contentPane.setLayout(new BorderLayout());
            setSize(new Dimension(100, 80));
            // add the header label
           
            // add the image label
//            ImageIcon ii = new ImageIcon(new URL(
//                    ));
            setUndecorated(true);
            getRootPane().setWindowDecorationStyle(JRootPane.NONE);
//            ImageIcon ii=new ImageIcon(this.getClass().getResource("loading.gif"));
        	ClassLoader loader=LoadingFrame.class.getClassLoader();
            ImageIcon ii=new ImageIcon(loader.getResource("/com/gdc/robotmanager/loading.gif"));

//            imageLabel.setBackground(new Color(0,0,0,0));
            
            
            imageLabel.setIcon(ii);
            contentPane.add(imageLabel, BorderLayout.CENTER);
            headerLabel.setText("Procesando ....");
            contentPane.add(headerLabel,BorderLayout.SOUTH);
            // show it
            this.setLocationRelativeTo(null);
            this.setVisible(true);
//            setUndecorated(true);
            setBackground(new Color(0,0,0,0));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	Path currrentRelativePath =Paths.get("");
    
//    	System.out.println(LoadingFrame.class.getClass().getResource("loading.gif"));
        LoadingFrame test=new LoadingFrame();
        try {
			Thread.sleep(10000);
			test.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    public void close(){
    	this.dispose();
    }

}