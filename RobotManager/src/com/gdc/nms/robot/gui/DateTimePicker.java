package com.gdc.nms.robot.gui;
import org.jdesktop.swingx.calendar.SingleDaySelectionModel;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
import java.awt.*;

public class DateTimePicker extends JXDatePicker {
    private JSpinner timeSpinner;
    private JPanel timePanel;
    private DateFormat timeFormat;
    private static Date spinnerDate;

    public DateTimePicker() {
        super();
        getMonthView().setSelectionModel(new SingleDaySelectionModel());
    }

    public DateTimePicker( Date d ) {
        this();
        setDate(d);
    }

    public void commitEdit() throws ParseException {
        commitTime();
        if(getLogewDate().before(this.getDate())){
        	super.commitEdit();
        }
    }

    public void cancelEdit() {
        super.cancelEdit();
        setTimeSpinners();
    }

    @Override
    public JPanel getLinkPanel() {
        super.getLinkPanel();
        if( timePanel == null ) {
            timePanel = createTimePanel();
        }
        setTimeSpinners();
        return timePanel;
    }
    
    private Date getDatePicker(){
    	return getDate();
    }

    private JPanel createTimePanel() {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout());
        //newPanel.add(panelOriginal);
    	Calendar cal=Calendar.getInstance();
		 Date now = cal.getTime();
		    cal.add(Calendar.YEAR, -50);
		    Date startDate = cal.getTime();
		    cal.add(Calendar.YEAR, 100);
		    Date endDate = cal.getTime();
        SpinnerDateModel dateModel = new SpinnerDateModel(now,startDate,endDate,Calendar.YEAR){
            @Override 
            public Object getNextValue() {
            		Calendar cal = Calendar.getInstance();
            		cal.setTime((Date)this.getValue());
            		cal.add(this.getCalendarField(), 1);
            		Date next=cal.getTime();
            		Date logewDate = getLogewDate();
            		
            		return next;
            	
            }

            @Override 
            public Object getPreviousValue() {
            	  Calendar cal = Calendar.getInstance();
                  cal.setTime((Date)this.getValue());
                  cal.add(this.getCalendarField(), -1);
                  Date prev = cal.getTime();
                  Date logewDate = getLogewDate();


                  return  prev ;
            }
        };
        if(DateSelectorPanel.spinnerDate!=null){
        	Calendar calendar = Calendar.getInstance();
        	calendar.setTime(DateSelectorPanel.spinnerDate);
        	calendar.add(Calendar.MINUTE, 5);
        	dateModel.setValue(calendar.getTime());
        }else{
        	Date calculateDateServer = DateSelectorPanel.getCalculateDateServer();
        	Calendar calendar = Calendar.getInstance();
        	calendar.setTime(calculateDateServer);
        	calendar.add(Calendar.MINUTE, 5);
        	dateModel.setValue(calendar.getTime());
        }
        timeSpinner = new JSpinner(dateModel);
        if( timeFormat == null ) timeFormat = DateFormat.getTimeInstance( DateFormat.SHORT );
        updateTextFieldFormat();
        newPanel.add(new JLabel( "Time:" ) );
        newPanel.add(timeSpinner);
        newPanel.setBackground(Color.WHITE);
        return newPanel;
    }

    private void updateTextFieldFormat() {
        if( timeSpinner == null ) return;
        JFormattedTextField tf = ((JSpinner.DefaultEditor) timeSpinner.getEditor()).getTextField();
        DefaultFormatterFactory factory = (DefaultFormatterFactory) tf.getFormatterFactory();
        DateFormatter formatter = (DateFormatter) factory.getDefaultFormatter();
        // Change the date format to only show the hours
        formatter.setFormat( timeFormat );
    }

    private void commitTime() {
        Date date = getDate();
        if (date != null) {
            Date time = (Date) timeSpinner.getValue();
            GregorianCalendar timeCalendar = new GregorianCalendar();
            timeCalendar.setTime( time );

            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get( Calendar.HOUR_OF_DAY ) );
            calendar.set(Calendar.MINUTE, timeCalendar.get( Calendar.MINUTE ) );
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            Date newDate = calendar.getTime();
            if(getLogewDate().before(newDate)){
            	
            	setDate(newDate);
            }else{
            	JOptionPane.showMessageDialog(null, "Es necesario Especificar Un Tiempo Futuro ",null,JOptionPane.WARNING_MESSAGE);;
            	setDate(getLogewDate());
            }
        }

    }

    private void setTimeSpinners() {
        Date date = getDate();
        if (date != null) {
            timeSpinner.setValue( date );
        }
    }

    public DateFormat getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(DateFormat timeFormat) {
        this.timeFormat = timeFormat;
        updateTextFieldFormat();
    }
    
    
    private Date getLogewDate(){
    	return this.getMonthView().getLowerBound();
    }
    
    public static  void setSpinnerDate(Date spinnerDateVal){
    	spinnerDate=spinnerDateVal;
    }
    public static void main(String[] args) {
        Date date = new Date();
        JFrame frame = new JFrame();
        frame.setTitle("Date Time Picker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DateTimePicker dateTimePicker = new DateTimePicker();
        dateTimePicker.setFormats( DateFormat.getDateTimeInstance( DateFormat.SHORT, DateFormat.MEDIUM ) );
        dateTimePicker.setTimeFormat( DateFormat.getTimeInstance( DateFormat.MEDIUM ) );
        dateTimePicker.setDate(date);
        
        dateTimePicker.getMonthView().setLowerBound(Calendar.getInstance().getTime());

        frame.getContentPane().add(dateTimePicker);
        Date date2 = dateTimePicker.getDate();
        frame.pack();
        frame.setVisible(true);
    }
}