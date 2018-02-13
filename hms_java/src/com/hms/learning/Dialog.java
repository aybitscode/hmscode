package com.hms.learning;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Dialog extends JDialog {
    private final JScrollPane scrollPane = new JScrollPane();
    //I added the breaks to the label below to be able to scroll down.
    private final JLabel lblContent = new JLabel("<html><body><p>Content<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>Content<br><br><br><br><br><br>Content</p></body></html>");
    JPanel p = new JPanel();

    public static void main(String[] args) {
                    Dialog dialog = new Dialog();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
    }

    public Dialog() { 
        setBounds(100, 100, 450, 300);
        p.setLayout(new FlowLayout());
        getContentPane().setLayout(new BorderLayout(0, 0));

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        p.add(lblContent);

        scrollPane.setViewportView(p);

    }
}