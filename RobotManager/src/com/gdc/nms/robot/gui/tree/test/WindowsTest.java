package com.gdc.nms.robot.gui.tree.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

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
					TreeDynamic tr = frame.getTreeDynamic();
					
					
					tr.addElement(null,new String("hola"));
					tr.addElement(null,new String("hola2"));
					tr.addElement(null,new String("hola3"));
					tr.addElement(null,new String("hola5"));
					DefaultMutableTreeNode rootParent = tr.getRoot();
					System.out.println(rootParent);
					
					DefaultMutableTreeNode node = new DefaultMutableTreeNode("mundo");
					
					tr.addElement(node, new String("sen"));
					tr.addElement(node, "que onda");
					tr.addElement(node, "senrigan");
					tr.addElement(rootParent, node);

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try {
			Thread.sleep(2000);
			TreeDynamic treeDynamic = frame.getTreeDynamic();
			int indexOfChild = treeDynamic.getTree().getModel().getIndexOfChild(treeDynamic.getRoot(),treeDynamic.search("senrigan"));
			System.out.println("index of child"+indexOfChild);
			TreeNode search = treeDynamic.search("hola2");
			System.out.println("search resul"+search+"index"+treeDynamic.getRoot().getIndex(search));
			search = treeDynamic.search("mundo");
			System.out.println("search resul"+search+"index"+treeDynamic.getRoot().getIndex(search));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
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
		tr=new TreeDynamic();
		JTree tree = tr.getTree();
		contentPane.add(tree);
		
	}

}
