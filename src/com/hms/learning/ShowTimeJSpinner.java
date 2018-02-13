package com.hms.learning;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ShowTimeJSpinner{
  public static void main(String[] args) {
  ShowTimeJSpinner h = new ShowTimeJSpinner();
  }

  public ShowTimeJSpinner(){
  JFrame frame = new JFrame("Creating JSpinner Component with time");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  Date date = new Date();
  SpinnerDateModel sm = 
  new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
  JSpinner spinner = new JSpinner(sm);
  JSpinner.DateEditor de = new JSpinner.DateEditor(spinner, "hh:mm:a");
  spinner.setEditor(de);
  frame.add(spinner,BorderLayout.NORTH);
  frame.setSize(100,100);
  frame.setVisible(true);
  }
}