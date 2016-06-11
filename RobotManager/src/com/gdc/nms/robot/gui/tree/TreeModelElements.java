package com.gdc.nms.robot.gui.tree;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.sun.tools.classfile.Annotation.element_value;
  public  class TreeModelElements extends DefaultTreeModel {

        private Element data;

//        public TreeModelElements() {
//        	data = new Element("Aplication");
//        }
//        
        public TreeModelElements(Element data) {
        	super(data);
        	this.data=data;
        }
        
      

        @Override
        public Object getRoot() {
            return data;
        }

        @Override
        public Object getChild(Object parent, int index) {

            if (parent instanceof Element) {
                Element p = (Element) parent;
                Object child = p.getChildAt(index);

                return child;
            }

            return null;
        }
        
        public void removeNodeFromParent(Element paramMutableTreeNode)
        {
          Element localMutableTreeNode = (Element)paramMutableTreeNode.getParent();
          if (localMutableTreeNode == null) {
            throw new IllegalArgumentException("node does not have a parent.");
          }
          int[] arrayOfInt = new int[1];
          Object[] arrayOfObject = new Object[1];
          arrayOfInt[0] = localMutableTreeNode.getIndex(paramMutableTreeNode);
          
          localMutableTreeNode.remove(arrayOfInt[0]);
          arrayOfObject[0] = paramMutableTreeNode;
          nodesWereRemoved(localMutableTreeNode, arrayOfInt, arrayOfObject);
        }
        @Override
        public int getChildCount(Object parent) {

            if (parent instanceof Element) {
                Element e = (Element) parent;
                return e.getChildCount();
            }

            return 0;
        }

        @Override
        public int getIndexOfChild(Object parent, Object child) {

            if (parent instanceof Element && child instanceof Element) {
                Element e = (Element) parent;

                return e.getIndex((Element)child);
            }

            return -1;

        }

        @Override
        public boolean isLeaf(Object node) {
            //List<? super ArrayList> d = (List<? super ArrayList>) node;

            if (node instanceof Element) {
                Element e = (Element) node;
                return e.getChildCount() == 0;
            }

            return true;
        }

        @Override
        public void valueForPathChanged(TreePath path, Object newValue) {

        }

        @Override
        public void addTreeModelListener(TreeModelListener l) {

        }

        @Override
        public void removeTreeModelListener(TreeModelListener l) {

        }

		

	
    }