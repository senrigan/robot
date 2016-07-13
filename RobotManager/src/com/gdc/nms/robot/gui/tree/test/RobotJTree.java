package com.gdc.nms.robot.gui.tree.test;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import sun.tools.tree.SynchronizedStatement;

public class RobotJTree {
	private TreeDynamic tree;
	private DefaultMutableTreeNode root;
	private DefaultMutableTreeNode runNode;
	private DefaultMutableTreeNode stopNode;
	
	public TreeDynamic getTree(){
		return tree;
	}
	
	public RobotJTree() {
		tree=new TreeDynamic();
		configDefaultNodes();
	}
	
	private void configDefaultNodes(){
		root=new DefaultMutableTreeNode("Aplicacion");
		runNode=new DefaultMutableTreeNode("En ejecucion");
		stopNode=new DefaultMutableTreeNode("Detenidos");
		root.add(runNode);
		root.add(stopNode);
		tree.changeRootNode(root);
		
	}
	
	public void addToRun(Object robotNode){
		System.out.println("adding nuew node Running "+robotNode.toString());
		DefaultMutableTreeNode node=(DefaultMutableTreeNode)robotNode;
		String nodeStringContent = node.getUserObject().toString();
		DefaultMutableTreeNode search = tree.search(nodeStringContent);
		if(search==null){
			tree.addElement(runNode,robotNode );
			
		}else{
			System.out.println("this node is in Jtree");
		}
	}
	public void removeRun(Object robotNode){
		tree.removeNode((DefaultMutableTreeNode) robotNode);
	}
	public void addToStop(Object robotNode){
		System.out.println("adding nuew node Stop "+robotNode.toString());

		DefaultMutableTreeNode node=(DefaultMutableTreeNode)robotNode;
		String nodeStringContent = node.getUserObject().toString();
		DefaultMutableTreeNode search = tree.search(nodeStringContent);
		if(search==null){
			tree.addElement(stopNode, robotNode);
			
		}else{
			System.out.println("this node is in Jtree");
		}
	}
	
	public void removeStop(Object robotNode){
		tree.removeNode((DefaultMutableTreeNode)robotNode);
	}
	
	public Object getSelectdNode(){
		return tree.getTree().getLastSelectedPathComponent();
	}
	
	public void changeRobotRunToStop(Object robotNode){
		DefaultMutableTreeNode removeNode = tree.removeNode((DefaultMutableTreeNode)robotNode);
		if(removeNode!=null){
			addToStop(removeNode);
		}
	}
	
	
	public void changeRobotStopToRun(Object robotNode){
		DefaultMutableTreeNode removeNode = tree.removeNode((DefaultMutableTreeNode)robotNode);
		if(removeNode!=null){
			addToRun(removeNode);
		}
	}
	
}
