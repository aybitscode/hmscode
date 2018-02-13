package com.hms.learning;
import java.awt.* ;
import java.awt.event.* ;

import javax.swing.* ;


public class RadioButton {
public static void main(String[] args) {
RadioButtonFrame frame  = new RadioButtonFrame();
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.show();
}
}

class RadioButtonFrame extends JFrame
{
public RadioButtonFrame()
{
setTitle("Radio Button Test");
setSize(400,200);

Container contentPane = getContentPane();
label = new JLabel("The quick brown fox jumps over the lazy dog");
label.setFont(new Font("Serif",Font.PLAIN,12));
contentPane.add(label, BorderLayout.CENTER);

buttonPanel = new JPanel();

group = new ButtonGroup();
addRadioButton("Small", 8);
addRadioButton("Medium", 12);
addRadioButton("Large", 18);
addRadioButton("Extra Large", 36);

contentPane.add(buttonPanel, BorderLayout.SOUTH);

WindowFocusListener listen = new WindowFocusListener()
{
public void windowLostFocus(WindowEvent e) {
System.out.println("Window FOcus Listener : focus lost");
// requestFocus();
setLocation(200,300);
show();
}

public void windowGainedFocus(WindowEvent e) {
System.out.println("window focus listener : gain focus ");
}
};

addWindowFocusListener(listen);
}

public void addRadioButton(String name, final int size)
{
boolean selected = size == 12 ;
JRadioButton button = new JRadioButton(name,selected);
group.add(button);
buttonPanel.add(button);

ActionListener listener =
new ActionListener(){
public void actionPerformed(ActionEvent e) {
label.setFont(new Font("Serif",Font.PLAIN,size));
}
};
button.addActionListener(listener);


}

private JLabel label ;
private JPanel buttonPanel ;
private ButtonGroup group ;
}