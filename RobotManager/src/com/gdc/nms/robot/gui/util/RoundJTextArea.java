package com.gdc.nms.robot.gui.util;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Shape;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class RoundJTextArea extends JTextArea {
    private Shape shape;
    public RoundJTextArea() {
//    	super();
////        super(size);
        setOpaque(false); // As suggested by @AVD in comment.
    }
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
         super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
         g.setColor(getBackground());
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        
    }
//    public boolean contains(int x, int y) {
//         if (shape == null || !shape.getBounds().equals(getBounds())) {
//             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
//         }
//         return shape.contains(x, y);
//    }
    
    
    
    public static void main(String[] args) {
   	 JFrame frame = new JFrame("Rounded corner text filed demo");
   	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   	    frame.setSize(400, 400);
   	    frame.setLayout(new FlowLayout());
   	    JTextArea field = new JTextArea();
   	    frame.add(field);
   	    frame.setVisible(true);
	}
}
