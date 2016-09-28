package com.gdc.nms.robot.gui.auxiliar;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

import com.gdc.nms.robot.util.Language;

import pic.ImageTest;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class LoadingFrame extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel imageLabel = new JLabel();
	private JLabel headerLabel = new JLabel();
	private static LoadingFrame instance;
	private static boolean inUse;

	public static LoadingFrame getInstance() {
		if (instance == null) {
			instance = new LoadingFrame();
		}
		return instance;
	}

	private LoadingFrame() {
		super();
		initComponents();
	}

	public void showLoadingFrame(String text) {
		System.out.println("inUse Loadigg Frame :"+inUse);
		if(inUse){
			
		}else{
			showLoadingFrame(text, null);
			inUse=true;
		}
		
	}

	public void showLoadingFrame(final String text, Component c) {
		if (SwingUtilities.isEventDispatchThread()) {
			System.out.println("is en edt");
			headerLabel.setText(text);
			this.setLocationRelativeTo(c);
			this.setAlwaysOnTop(true);
			this.setVisible(true);

		} else {
			System.out.println("no is en edt");
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					showElement();
					headerLabel.setText(text);
				}
			});
		}
	}

	private void showElement() {
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}

	public void hiddenLoadingFrame() {
		this.setVisible(false);
		headerLabel.setText("");
		inUse=false;
	}

	public void changeText(String newText) {
		headerLabel.setText(newText);
	}

	private void initComponents() {
		try {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			contentPane = (JPanel) getContentPane();
			contentPane.setLayout(new BorderLayout());
			setSize(new Dimension(100, 80));
			setUndecorated(true);
			getRootPane().setWindowDecorationStyle(JRootPane.NONE);
			// ClassLoader loader=LoadingFrame.class.getClassLoader();
			ImageIcon ii = new ImageIcon(ImageTest.class.getResource("/pic/loading.gif"));
			System.out.println("image icon " + ii);
			imageLabel.setIcon(ii);
			contentPane.add(imageLabel, BorderLayout.CENTER);
			headerLabel.setText("");
			contentPane.add(headerLabel, BorderLayout.SOUTH);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			setBackground(new Color(0, 0, 0, 0));
		} catch (Exception exception) {
			System.out.println("Error al cargar la imagen de precarga");
		}
	}

	public static void main(String[] args) {
		// System.out.println(ImageTest.class.getResource("/pic/loading.gif"));
		//// System.out.println(LoadingFrame.class.getClass().getResource("loading.gif"));
		// LoadingFrame test=LoadingFrame.getInstance();
		// try {
		// test.showLoadingFrame("hola");
		// Thread.sleep(10000);
		// test.close();
		// Thread.sleep(15000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// test.showLoadingFrame("mundo");
		Language.load();
		LoadingFrame.getInstance().showLoadingFrame(Language.get("addrtobo.loading.message"));

	}

	public void close() {
		this.dispose();
		inUse=false;
	}

}