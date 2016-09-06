package com.gdc.nms.robot.gui.auxiliar;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CheckBoxList extends JList
{
   protected static Border noFocusBorder =
                                 new EmptyBorder(1, 1, 1, 1);

   public CheckBoxList()
   {
      setCellRenderer(new CellRenderer());

      addMouseListener(new MouseAdapter()
         {
            public void mousePressed(MouseEvent e)
            {
               int index = locationToIndex(e.getPoint());

               if (index != -1) {
                  JCheckBox checkbox = (JCheckBox)getModel().getElementAt(index);
                  checkbox.setSelected(!checkbox.isSelected());
                  repaint();
               }
            }
         }
      );

      setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
   }
   
   
   public ArrayList<JCheckBox> getSelectedCheckBox(){
       @SuppressWarnings("unchecked")
	ListModel<JCheckBox> model = getModel();
	   ArrayList<JCheckBox> list=new ArrayList<JCheckBox>();
       for(int i=0;i<model.getSize();i++){
     	  JCheckBox elementAt = model.getElementAt(i);
     	  if(elementAt.isSelected())
     	  list.add(elementAt);
       }
       return list;
   }
   
   public ArrayList<JCheckBox> getListCheckBox(){
       @SuppressWarnings("unchecked")
	ListModel<JCheckBox> model = getModel();
	   ArrayList<JCheckBox> list=new ArrayList<JCheckBox>();
       for(int i=0;i<model.getSize();i++){
     	  JCheckBox elementAt = model.getElementAt(i);
     	  list.add(elementAt);
       }
       return list;
   }

   protected class CellRenderer implements ListCellRenderer
   {
      public Component getListCellRendererComponent(
                    JList list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus)
      {
         JCheckBox checkbox = (JCheckBox) value;
         checkbox.setBackground(isSelected ?
                 getSelectionBackground() : getBackground());
         checkbox.setForeground(isSelected ?
                 getSelectionForeground() : getForeground());
         checkbox.setEnabled(isEnabled());
         checkbox.setFont(getFont());
         checkbox.setFocusPainted(false);
         checkbox.setBorderPainted(true);
         checkbox.setBorder(isSelected ?
          UIManager.getBorder(
           "List.focusCellHighlightBorder") : noFocusBorder);
         return checkbox;
      }
   }
}