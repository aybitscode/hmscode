//package com.hms.learning;
//
//import java.awt.BorderLayout;
//import java.awt.Desktop;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStreamWriter;
///*from w  w w  . j a v a2s  .  co  m*/
//import javax.swing.JButton;
//import javax.swing.JFileChooser;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//
//public class FileSave {
//  JTextArea textArea;
//  JButton save;
//  void initUI() {
//    JFrame frame = new JFrame(Main.class.getSimpleName());
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    textArea = new JTextArea(24, 80);
//    save = new JButton("Save to file");
//    save.addActionListener(e -> saveToFile());
//    frame.add(new JScrollPane(textArea));
//    JPanel buttonPanel = new JPanel();
//    buttonPanel.add(save);
//    frame.add(buttonPanel, BorderLayout.SOUTH);
//    frame.setSize(500, 400);
//    frame.setVisible(true);
//  }
//
//  protected void saveToFile() {
//    JFileChooser fileChooser = new JFileChooser();
//    int retval = fileChooser.showSaveDialog(save);
//    if (retval == JFileChooser.APPROVE_OPTION) {
//      File file = fileChooser.getSelectedFile();
//      if (file == null) {
//        return;
//      }
//      if (!file.getName().toLowerCase().endsWith(".txt")) {
//        file = new File(file.getParentFile(), file.getName() + ".txt");
//      }
//      try {
//        textArea.write(new OutputStreamWriter(new FileOutputStream(file),
//            "utf-8"));
//        Desktop.getDesktop().open(file);
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//    }
//  }
//
//  public static void main(String[] args) {
//    new Main().initUI();
//  }
//}