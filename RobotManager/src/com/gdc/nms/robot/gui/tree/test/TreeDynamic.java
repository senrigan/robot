package com.gdc.nms.robot.gui.tree.test;

import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;


public class TreeDynamic {
	private JTree tree;
	private DefaultMutableTreeNode rootNode;
	protected DefaultTreeModel treeModel;
	
	public TreeDynamic (){
		  rootNode = new DefaultMutableTreeNode("Aplicaciones");
		    treeModel = new DefaultTreeModel(rootNode);

		    tree = new JTree(treeModel);
		    tree.setEditable(false);
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
	
	/**
	 *
	 * @param node
	 * @return if the node is a leaf in the jtree
	 */
	public boolean isLeaf(DefaultMutableTreeNode node){
		return tree.getModel().isLeaf(node);
	}
	
	public void changeRootNode(DefaultMutableTreeNode node){
		treeModel.setRoot(node);
	}
	
	
	public void clearSelection(){
		tree.clearSelection();
	}
	
	/**
	 * 
	 * @return the rootNode of the jtree
	 */
	public DefaultMutableTreeNode getRoot(){
		return (DefaultMutableTreeNode) tree.getModel().getRoot();
	}
	
	/**
	 * add element to parentnode if this is null the element add to rootnode of tree
	 * @param parentNode
	 * @param element
	 * @return
	 */
	public DefaultMutableTreeNode addElement(Object parentNode,Object element){
		DefaultMutableTreeNode parentTreeNode = (DefaultMutableTreeNode)parentNode;			
		
	    if (parentNode == null) {
	      parentTreeNode = rootNode;
	    } 
	    System.out.println("parente node is null"+parentNode);
	    return addObject(parentTreeNode, element, true);
	}
	/**
	 * add a child  to parent node if this are no define the default is rootnode of jtree
	 * 
	 * @param parent
	 * @param child
	 * @return
	 */
	 public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
		      Object child) {
		    return addObject(parent, child, true);
	 }
		/**
		 * add a child  to parent node if this are no define the default is rootnode of jtree
		 * shuldbeVisible this show is the child show or not collapsable view
		 * @param parent
		 * @param child
		 * @return
		 */
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

		    treeModel.insertNodeInto(childNode, parent, parent.getChildCount());
		    if (shouldBeVisible) {
		      tree.scrollPathToVisible(new TreePath(childNode.getPath()));
		    }
		    DefaultTreeModel model =(DefaultTreeModel) tree.getModel();
		    model.reload(parent);
		    return childNode;
	 }
	 
	 /**
	  * remove node of the jtree if this is no succefull retur null in other case return the node where are removed 
	  * @param node
	  * @return
	  */
	 public DefaultMutableTreeNode removeNode(DefaultMutableTreeNode node){
	      MutableTreeNode parent = (MutableTreeNode) (node.getParent());
	      if (parent != null) {
	        treeModel.removeNodeFromParent(node);
	        return node;
	      }
	      return null;
	 }
	 
	 
	 
	 /**
	  * return the first node with contain the text especific in the rootNode of jtree
	  * @param text
	  * @return
	  */
	 public DefaultMutableTreeNode search(String text){
		 DefaultMutableTreeNode root = (DefaultMutableTreeNode)tree.getModel().getRoot();
		 return findNode(root, text);
	 }

	 /**
	  * search the text to seacrh in the root node and children of this
	  * @param root
	  * @param search
	  * @return
	  */
	 public DefaultMutableTreeNode findNode( DefaultMutableTreeNode root, String search ) {
		    Enumeration<?> nodeEnumeration = root.breadthFirstEnumeration();
		    while( nodeEnumeration.hasMoreElements() ) {
		      DefaultMutableTreeNode node =
		        (DefaultMutableTreeNode)nodeEnumeration.nextElement();
		    	  String found = node.getUserObject().toString();
		    	  System.out.println("checking node "+node+"parente"+node.getParent());
		    	  if( search.equals( found ) ) {
		    		  return node;
		    	  }
		    }
		    return null;
	 }
	 
	 /**
	  * print the enumeration returningg of breadthFirstEnumeration()
	  * @param enumer
	  */
	 private void printEnum(Enumeration<?> enumer){
		 while(enumer.hasMoreElements()){
			 DefaultMutableTreeNode node = (DefaultMutableTreeNode)enumer.nextElement();
			 System.out.println("*"+node.getUserObject().toString());
		 }
		 
	 }
	 /**
	  * print the childen of specific node
	  * @param node
	  */
	 public void pirntChildren(DefaultMutableTreeNode node){
			Enumeration<?> children = node.children();
			printEnum(children);
		}

}
