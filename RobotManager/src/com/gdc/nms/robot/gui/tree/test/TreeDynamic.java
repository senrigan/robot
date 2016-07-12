package com.gdc.nms.robot.gui.tree.test;

import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class TreeDynamic {
	private JTree tree;
	private DefaultMutableTreeNode rootNode;
	protected DefaultTreeModel treeModel;
	
	public TreeDynamic (){
		  rootNode = new DefaultMutableTreeNode("Aplicaciones");
		    treeModel = new DefaultTreeModel(rootNode);

		    tree = new JTree(treeModel);
		    tree.setEditable(true);
//		    tree.getSelectionModel().setSelectionMode(
//		        TreeSelectionModel.SINGLE_TREE_SELECTION);
		    tree.setShowsRootHandles(true);
	}
	
	
	public JTree getTree(){
		return tree;
	}
	
	  /** Remove all nodes except the root node. */
	public void clear() {
	    rootNode.removeAllChildren();
	    treeModel.reload();
	}
	
	public void pirntChildren(){
		Enumeration children = rootNode.children();
		printEnum(children);
	}
	
	public void printC(){
		
	}
	public DefaultMutableTreeNode getRoot(){
		return (DefaultMutableTreeNode) tree.getModel().getRoot();
	}
	public DefaultMutableTreeNode addElement(Object parentNode,Object element){
		DefaultMutableTreeNode parentTreeNode = (DefaultMutableTreeNode)parentNode;

	    if (parentNode == null) {
	      parentTreeNode = rootNode;
	    } 
	    
	    return addObject(parentTreeNode, element, true);
	}
	
	 public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
		      Object child) {
		    return addObject(parent, child, true);
	 }
	 
	 public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
		      Object child, boolean shouldBeVisible) {
		 	DefaultMutableTreeNode childNode;
		 	if(child instanceof DefaultMutableTreeNode){
		 		childNode=(DefaultMutableTreeNode)child;
		 	}else{
		 		
		 		childNode = new DefaultMutableTreeNode(child);
		 	}

		    if (parent == null) {
		      parent = rootNode;
		    }
		    // It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
		    treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

		    // Make sure the user can see the lovely new node.
		    if (shouldBeVisible) {
		      tree.scrollPathToVisible(new TreePath(childNode.getPath()));
		    }
		    DefaultTreeModel model =(DefaultTreeModel) tree.getModel();
		    model.reload(parent);
		    return childNode;

	 }
	 
	 
	 
	 
	 public DefaultMutableTreeNode search(String text){
		 DefaultMutableTreeNode root = (DefaultMutableTreeNode)tree.getModel().getRoot();
		 return findNode(root, text);
	 }

	 
	 public DefaultMutableTreeNode findNode( DefaultMutableTreeNode root, String search ) {
		    Enumeration nodeEnumeration = root.breadthFirstEnumeration();
		    while( nodeEnumeration.hasMoreElements() ) {
		      DefaultMutableTreeNode node =
		        (DefaultMutableTreeNode)nodeEnumeration.nextElement();
		     
		    	  String found = node.getUserObject().toString();
		    	  if( search.equals( found ) ) {
		    		  return node;
		    	  }
		    	  
		      
	    	  
		      
		    }
		    return null;
	 }
	 
	 
	 private void printEnum(Enumeration enumer){
		 while(enumer.hasMoreElements()){
			 DefaultMutableTreeNode node = (DefaultMutableTreeNode)enumer.nextElement();
			 System.out.println("*"+node.getUserObject().toString());
		 }
		 
	 }
	 private TreeNode searchTree( TreePath path, String q) 
	 {
	      TreeNode node = (TreeNode)path.getLastPathComponent();
		  if(node==null) return null;
		  if(node.toString().equals(q))
		  {
		    tree.setScrollsOnExpand(true);
		    tree.setSelectionPath(path);
		    System.out.println("node is found"+node+" "+node.toString());
		    
		    return node;
		  }
		  System.out.println("buscando en hoja");
		  if(!node.isLeaf() && node.getChildCount()>=0)
		  {
		    Enumeration e = node.children();
		    while(e.hasMoreElements()){
		    	return searchTree(path.pathByAddingChild(e.nextElement()), q);
		    	
		    }
		  }
		  return node;
		  
	 }

}
