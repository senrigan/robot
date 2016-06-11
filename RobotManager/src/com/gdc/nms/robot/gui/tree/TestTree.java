package com.gdc.nms.robot.gui.tree;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import com.gdc.nms.robot.util.JavaProcessInfo;
import com.gdc.nms.robot.util.JpsManager;


public class TestTree {
	private JTree tree;

    public static void main(String[] args) {
        new TestTree();
    }

    public TestTree() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }
                
                tree = new JTree(new TreeModelElements(getDataForTree()));

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new JScrollPane(tree));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
              
            }
        });
        try{
        	Thread.sleep(5000);
        	System.out.println("empezando a buscar");
        	Element findNode = findNode("mundo");
        	if(findNode==null){
        		System.out.println("es nulo");
        	}
        	System.out.println("valor"+findNode);
        	System.out.println(findNode.toString());
        }catch(Exception ex){
        	ex.printStackTrace();
        }
        
        try{
        	DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        	Element root =  (Element) model.getRoot();
        	Element findNode = findNode("apagado");
        	System.out.println("encontrado"+findNode);
        	System.out.println(findNode.getChildCount());
        	Element nElement =new Element("cambiando");
        	findNode.add(nElement);
        	System.out.println(findNode.getChildCount());
            tree.scrollPathToVisible(new TreePath(nElement.getPath()));
            Element findNode2 = findNode("hola");
            System.out.println("elimnando elemento"+findNode2);
            Element parent = (Element)findNode2.getParent();
//            System.out.println(parent.getChildCount());
//            parent.remove(findNode2);
            model.reload();
            model.reload(parent);
            int index = parent.getIndex(findNode2);
            Object[] indDel={0};
            System.out.println("ind del"+Arrays.toString(indDel));
            int childCount = parent.getChildCount();
            int []id=new int[childCount];
            System.out.println("child count"+childCount);
            for (int i=0, j=0;i<childCount;i++,j++) {
            	TreeNode childAt = parent.getChildAt(i);
            	int index2 = parent.getIndex(childAt);
            	System.out.println(i);
            	id[i]=index2;
            	System.out.println(childAt);
            	System.out.println(Arrays.toString(id));
//				if((int)indDel[0]!=i){
//					id[j]=i;
//					
//				}
			}
            TreeModelElements modEle=(TreeModelElements)tree.getModel();
            modEle.removeNodeFromParent(findNode2);
            modEle.reload();
            modEle.reload(parent);
//            model.nodesWereRemoved(findNode2,id, indDel);
//            System.out.println(parent.getChildCount());
//            model.reload(parent);
//            model.reload();
//            model.reload(findNode2);
//        }
        //            tree.scrollPathToVisible(new TreePath(root.getPath()));
            
//            model.nodesWereInserted(findNode, new int[]{findNode.getChildCount()-1,findNode.getChildCount()});

//        	model.insertNodeInto((MutableTreeNode)new Element("another_child"), (MutableTreeNode)root, root.getChildCount());
        }catch(Exception ex){
        	ex.printStackTrace();
        }
        
    }
    
    private Element getDataForTree(){
    	Element element=new Element("Servicios");
    	Element servicios=new Element("aplicaciones");
    	Element apagado=new Element("apagado");
    	servicios.add(element);
    	servicios.add(apagado);
//    	String args[]={"-v"};
//    	ArrayList<JavaProcessInfo> runningApps = JpsManager.getRunningApps(args);
//    	for (JavaProcessInfo javaProcessInfo : runningApps) {
//    		String data = javaProcessInfo.getData();
//    		if(data.contains("Robot")){
//    			String[] split = data.split("=");
//    			String idenfy=split[1];
//    			if(idenfy.contains("Robot")){
//    				element.add(new Element(idenfy));
//    			}
//    			
//    		}
//		}
//    	System.out.println(runningApps);
    	
    	element.add(new Element("hola"));
    	element.add(new Element("mundo"));
    	element.add(new Element("java"));
    	return servicios;
    }
    
    
    
    public final Element findNode(String searchString) {

        List<Element> searchNodes = getSearchNodes((Element)tree.getModel().getRoot());
        Element currentNode = (Element)tree.getLastSelectedPathComponent();

        Element foundNode = null;
        int bookmark = -1;

        if( currentNode != null ) {
            for(int index = 0; index < searchNodes.size(); index++) {
                if( searchNodes.get(index) == currentNode ) {
                    bookmark = index;
                    break;
                }
            }
        }

        for(int index = bookmark + 1; index < searchNodes.size(); index++) {    
            if(searchNodes.get(index).toString().toLowerCase().contains(searchString.toLowerCase())) {
                foundNode = searchNodes.get(index);
                break;
            }
        }

        if( foundNode == null ) {
            for(int index = 0; index <= bookmark; index++) {    
                if(searchNodes.get(index).toString().toLowerCase().contains(searchString.toLowerCase())) {
                    foundNode = searchNodes.get(index);
                    break;
                }
            }
        }
        System.out.println("ya acabo la busqueda");
        return foundNode;
    }   
    
    
    private final List<Element> getSearchNodes(Element root) {
        List<Element> searchNodes = new ArrayList<Element>();

        Enumeration<?> e = root.preorderEnumeration();
        while(e.hasMoreElements()) {
            searchNodes.add((Element)e.nextElement());
        }
        return searchNodes;
    }

    public static class TestModel implements TreeModel {

        private Element data;

        TestModel(Element data) {
//            data = new Element("data");
//
//            data.add(new Element("One"));
//            data.add(new Element("Two"));
//            data.add(new Element("Three"));
//            data.add(new Element("Four"));
//            data.add(new Element("Five"));
        	this.data=data;
        }

        @Override
        public Object getRoot() {
            return data;
        }

        @Override
        public Object getChild(Object parent, int index) {

            System.out.println("GetChild from " + parent + " @ " + index);

            if (parent instanceof Element) {
                Element p = (Element) parent;
                Object child = p.getChildAt(index);

                System.out.println("child = " + child);
                return child;
            }

            return null;
        }

        @Override
        public int getChildCount(Object parent) {

            if (parent instanceof Element) {
                Element e = (Element) parent;
                System.out.println("childCount = " + parent + "; " + e.getChildCount());
                return e.getChildCount();
            }

            return 0;
        }

        @Override
        public int getIndexOfChild(Object parent, Object child) {

            if (parent instanceof Element && child instanceof Element) {
                Element e = (Element) parent;

                System.out.println("indexOf " + child + " in " + parent + " is " + e.getIndex((Element)child));
                return e.getIndex((Element)child);
            }

            return -1;

        }

        @Override
        public boolean isLeaf(Object node) {
            //List<? super ArrayList> d = (List<? super ArrayList>) node;

            if (node instanceof Element) {
                Element e = (Element) node;
                System.out.println("isLeaf " + e + "; " + (e.getChildCount() == 0));
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

    
}