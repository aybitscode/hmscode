package com.hms.learning;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class HtmlAndJButton {

    final String buttonText = " Whatever words, <br> but nothing wise";
    final String buttonText1 = " Whatever words, <br> but nothing wise, "
            + "<br> plus 1st. line, ";
    final String buttonText2 = " Whatever words, <br> but nothing wise, "
            + "<br> plus 1st. line, <br> plus 2nd. line,";
    private JButton btn1 = new JButton("Toggle");
    private JButton button = new JButton(buttonText);
    private JButton button1 = new JButton("Toggle");
    private JButton button2 = new JButton("Toggle");

    public HtmlAndJButton() {
        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                button.setText("<html><font color=" + (button.isEnabled()
//                        ? "blue" : "red") + ">" + buttonText + "</font></html>");
//                button.setEnabled(!button.isEnabled());
//                button1.setText("<html><font color=" + (button1.isEnabled()
//                        ? "red" : "green") + ">" + buttonText1 + "</font></html>");
//                button1.setEnabled(!button1.isEnabled());
//                button2.setText("<html><font color=" + (button2.isEnabled()
//                        ? "green" : "yellow") + ">" + buttonText2 + "</font></html>");
//                button2.setEnabled(!button2.isEnabled());
            	button.setEnabled(false);
            	button1.setEnabled(false);
            	button2.setEnabled(false);
            }
        });
        button.setText("<html><font color=red>" + buttonText + "</font></html>");
        button1.setText("<html><font color=green>" + buttonText1 + "</font></html>");
        button2.setText("<html><font color=yellow>" + buttonText2 + "</font></html>");
        JFrame f = new JFrame("ButtonTest");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new GridLayout(2, 2));
        f.add(button);
        f.add(button1);
        f.add(button2);
        f.add(btn1);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                HtmlAndJButton t = new HtmlAndJButton();
            }
        });
    }
}