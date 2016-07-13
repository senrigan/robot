package com.gdc.nms.robot.gui.tree.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import com.sun.xml.internal.ws.policy.jaxws.DefaultPolicyResolver;

public class WindowsTest extends JFrame {

	private JPanel contentPane;
	private TreeDynamic tr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		final WindowsTest frame = new WindowsTest();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
					

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public TreeDynamic getTreeDynamic(){
		return tr;
	}
	/**
	 * Create the frame.
	 */
	public WindowsTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		RobotJTree roj=new RobotJTree();
		tr=roj.getTree();
		JTree tree = tr.getTree();
		contentPane.add(tree);
		
	}

}
