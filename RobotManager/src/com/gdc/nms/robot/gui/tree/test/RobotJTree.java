package com.gdc.nms.robot.gui.tree.test;

import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

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
		String nodeStringContent=null;
		DefaultMutableTreeNode node=null;
		if(robotNode instanceof DefaultMutableTreeNode){
			System.out.println("adding nuew node Running "+((DefaultMutableTreeNode)robotNode).getUserObject().toString());
			node=(DefaultMutableTreeNode)robotNode;
			nodeStringContent = node.getUserObject().toString();
			
		}else if(robotNode instanceof String){
			System.out.println("adding nuew node Running "+(String)robotNode);
			nodeStringContent=(String)robotNode;
			node=new DefaultMutableTreeNode(robotNode);
		}
		DefaultMutableTreeNode search = tree.search(nodeStringContent);
		
		if(search==null){
			tree.addElement(runNode,node );
			
		}else{
			DefaultMutableTreeNode searchInStop =removeifExistInStop(search);
			System.out.println("parente"+searchInStop);
			removeStop(searchInStop);
				tree.addElement(runNode, searchInStop);
			tree.treeModel.reload();

		}
	}
	
	
	public DefaultMutableTreeNode removeifExistInStop(DefaultMutableTreeNode node){
		int numChilder = stopNode.getChildCount();
		for (int i = 0; i < numChilder; i++) {
			TreeNode chhildNode = stopNode.getChildAt(i);
			if(chhildNode.toString().equals(node.getUserObject())){
				System.out.println("same word");
				stopNode.remove(i);
				return (DefaultMutableTreeNode) chhildNode;
			}
		}
		return null;
	}
	
	public DefaultMutableTreeNode removeifExistInRun(DefaultMutableTreeNode node){
		int numChilder = runNode.getChildCount();
		for (int i = 0; i < numChilder; i++) {
			TreeNode chhildNode =runNode.getChildAt(i);
			if(chhildNode.toString().equals(node.getUserObject())){
				System.out.println("same word");
				runNode.remove(i);
				return (DefaultMutableTreeNode) chhildNode;
			}
		}
		return null;
	}
	public void removeRun(Object robotNode){
		tree.removeNode((DefaultMutableTreeNode) robotNode);
	}
	public void addToStop(Object robotNode){
		DefaultMutableTreeNode node=null;
		String nodeStringContent=null;
		if(robotNode instanceof DefaultMutableTreeNode){
			System.out.println("adding nuew node stop "+((DefaultMutableTreeNode)robotNode).getUserObject().toString());
			node=(DefaultMutableTreeNode)robotNode;
			nodeStringContent = node.getUserObject().toString();
		}else if(robotNode instanceof String){
			nodeStringContent=(String) robotNode;
		}
//		System.out.println("adding nuew node Stop "+robotNode.toString());
//
//		DefaultMutableTreeNode node=(DefaultMutableTreeNode)robotNode;
//		String nodeStringContent = node.getUserObject().toString();
		DefaultMutableTreeNode search = tree.search(nodeStringContent);
		if(search==null){
			tree.addElement(stopNode, node);
			
		}else{
			DefaultMutableTreeNode searchInRun =removeifExistInRun(search);
			System.out.println("parente"+searchInRun);
			removeStop(searchInRun);
			tree.addElement(stopNode, searchInRun);
			tree.treeModel.reload();
		}
	}
	
	public void removeStop(Object robotNode){
		System.out.println("removing node"+tree.removeNode((DefaultMutableTreeNode)robotNode));;
	}
	
	public Object getSelectdNode(){
		return tree.getTree().getLastSelectedPathComponent();
	}
	
	public void changeRobotRunToStop(Object robotNode){
		DefaultMutableTreeNode removeNode = tree.removeNode((DefaultMutableTreeNode)robotNode);
		if(removeNode!=null){
			addToStop(removeNode);
		}else{
			System.out.println("is null for remove to start");
		}
	}
	
	
	public void changeRobotStopToRun(Object robotNode){
		DefaultMutableTreeNode removeNode = tree.removeNode((DefaultMutableTreeNode)robotNode);
		if(removeNode!=null){
			addToRun(removeNode);
		}else{
			System.out.println("is null for remove to stop");
		}
	}
	
	public boolean robotExitinTree(String simpleRobotName){
		DefaultMutableTreeNode search = tree.search(simpleRobotName);
		if(search!=null){
			return true;
		}
		return true;
	}
	
	
	public DefaultMutableTreeNode getRobotNode(String simpleRobotName){
		return tree.search(simpleRobotName);
	}
	
}
