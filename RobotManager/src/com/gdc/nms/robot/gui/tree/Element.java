package com.gdc.nms.robot.gui.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import com.gdc.nms.robot.util.indexer.AppInformation;

public  class Element extends DefaultMutableTreeNode{

        private List<Element> nodes;
        private Element parent;
        private AppInformation appinfo;
        
        private boolean stopAble;

        private String name;

        public Element(String n) {
        	super(n);
            nodes = new ArrayList<Element>();
            name = n;
            
        }

        @Override
        public String toString() {
        	
            return name;
        }

        protected void setParent(Element parent) {
            this.parent = parent;
        }

        public void add(Element node) {
            node.setParent(this);
            nodes.add(node);
        }

        public void remove(Element node) {
            node.setParent(null);
            nodes.remove(node);
        }
        
        
        public void remove(int nodPosition[]){
        	for (int i = 0; i < nodPosition.length; i++) {
        		remove((Element)getChildAt(nodPosition[i]));
			}
        }
        
        public void remove(int nodPosition){
        	
        	remove((Element)getChildAt(nodPosition));
			
        }

        @Override
        public TreeNode getChildAt(int childIndex) {
            return nodes.get(childIndex);
        }

        @Override
        public int getChildCount() {
            return nodes.size();
        }

        @Override
        public TreeNode getParent() {
            return parent;
        }

        @Override
        public int getIndex(TreeNode node) {
            return nodes.indexOf(node);
        }

        @Override
        public boolean getAllowsChildren() {
            return true;
        }

        @Override
        public boolean isLeaf() {
            return nodes.isEmpty();
        }
        
        public boolean  isStopAble() {
			return stopAble;
		}
        
        public void setStopAble(boolean stopAble){
        	this.stopAble=stopAble;
        }

        @Override
        public Enumeration children() {
            return Collections.enumeration(nodes);
        }

		public AppInformation getAppinfo() {
			return appinfo;
		}

		public void setAppinfo(AppInformation appinfo) {
			this.appinfo = appinfo;
		}
		public Enumeration preorderEnumeration()
		  {
		    return new PreorderEnumeration(this);
		  }
		  private final class PreorderEnumeration
		    implements Enumeration<TreeNode>
		  {
		    private final Stack<Enumeration> stack = new Stack();
		    
		    public PreorderEnumeration(TreeNode paramTreeNode)
		    {
		      Vector localVector = new Vector(1);
		      localVector.addElement(paramTreeNode);
		      stack.push(localVector.elements());
		    }
		    
		    public boolean hasMoreElements()
		    {
		      return (!stack.empty()) && (((Enumeration)stack.peek()).hasMoreElements());
		    }
		    
		    public TreeNode nextElement()
		    {
		      Enumeration localEnumeration1 = (Enumeration)stack.peek();
		      TreeNode localTreeNode = (TreeNode)localEnumeration1.nextElement();
		      Enumeration localEnumeration2 = localTreeNode.children();
		      if (!localEnumeration1.hasMoreElements()) {
		        stack.pop();
		      }
		      if (localEnumeration2.hasMoreElements()) {
		        stack.push(localEnumeration2);
		      }
		      return localTreeNode;
		    }
		  }
		  
		  public TreeNode[] getPath()
		  {
		    return getPathToRoot(this, 0);
		  }
		  
		  protected TreeNode[] getPathToRoot(TreeNode paramTreeNode, int paramInt)
		  {
		    TreeNode[] arrayOfTreeNode;
		    if (paramTreeNode == null)
		    {
		      if (paramInt == 0) {
		        return null;
		      }
		      arrayOfTreeNode = new TreeNode[paramInt];
		    }
		    else
		    {
		      paramInt++;
		      arrayOfTreeNode = getPathToRoot(paramTreeNode.getParent(), paramInt);
		      arrayOfTreeNode[(arrayOfTreeNode.length - paramInt)] = paramTreeNode;
		    }
		    return arrayOfTreeNode;
		  }

		@Override
		public Object clone() {
			return super.clone();
		}

	
        
    }