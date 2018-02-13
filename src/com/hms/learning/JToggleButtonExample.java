package com.hms.learning;
import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;
class JToggleButtonExample extends JFrame
{
JToggleButton t1,t2,t3,t4,t5,t6,t7,t8;
ImageIcon i1,i2,i3,i4,i5,i6,i7;

    public JToggleButtonExample()
    {
        // Create and show the GUI
        createAndShowGUI();
    }
   
    private void createAndShowGUI()
    {
        // Set frame properties
        setTitle("JToggleButton Demo");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create some image icons
        i1=new ImageIcon("camera.png");
        i2=new ImageIcon("music.png");
        i3=new ImageIcon("safari.png");
        i4=new ImageIcon("message.png");
        i5=new ImageIcon("music1.png");
        i6=new ImageIcon("music2.png");
        i7=new ImageIcon("music3.png");
        
        
        // Created using
        // default constructor
        // Comes with no text, icon
        // and is not selected
        t1=new JToggleButton();
        
        // Created using
        // Second constructor
        // Takes javax.swing.Action
        // object
        t2=new JToggleButton(new DefaultEditorKit.BeepAction());
        
        // Third constructor
        // Takes javax.swing.Icon
        t3=new JToggleButton(i1);
        
        // Fourth constructor
        // takes icon and boolean
        // representing whether the
        // toggle button should be
        // selected
        // This icon acts as every icon
        // i.e. it is the same on rollover,
        // selected and rollover selected
        t4=new JToggleButton(i2,true);
        
        // Fifth constructor
        // takes String text on toggle button
        t5=new JToggleButton("Toggle Button 5");
        
        // Sixth constructor
        // takes String text with boolean selected
        t6=new JToggleButton("Toggle Button 6",true);
        
        // Seventh constructor
        // Takes String text with icon
        t7=new JToggleButton("Toggle Button 7",i3);
        
        // Eighth constructor
        // Takes String text with icon and
        // boolean selected
        t8=new JToggleButton("Toggle Button 8",i4,true);
                
        // Set some background
        t1.setBackground(Color.LIGHT_GRAY);
        
        // Set some foreground
        t1.setForeground(Color.BLACK);
                        
        // Set some action command
        t1.setActionCommand("JToggleButton t1");
        
        // Set some text to t1,t2
        t1.setText("Toggle Button 1");
        t2.setText("Toggle Button 2");
        
        // Set some cursor for t2
        t2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Set roll over icon for t4
        t4.setRolloverIcon(i5);
        
        // Set content area filled false
        // Just see the effect
        t4.setContentAreaFilled(false);
        
        // Remove the border around t4
        t4.setBorderPainted(false);
        
        // Remove the thin line around
        // the icon (here)
        // This line is a focus indication.
        // Remove that indication.
        t4.setFocusPainted(false);
        
        // Set some roll over selected icon
        // This icon comes when the toggle button
        // is selected and then mouse is hovered on it
        t4.setRolloverSelectedIcon(i6);
        
        // Now set some custom selected icon
        t4.setSelectedIcon(i7);

        // Set roll over disabled for t3
        // When roll over is disabled
        // the roll over icon is never seen
        t3.setRolloverEnabled(false);
        
        // Disable t8
        t8.setEnabled(false);
        
        // Set disabled selected icon
        // This icon appears when the
        // JFrame is opened, because
        // t8 is selected by this time
        t8.setDisabledSelectedIcon(i6);
        
        // Now set disabled icon
        // The icon cannot be seen
        // because once after the
        // toggle button is disabled
        // selection cannot be changed
        // i.e. all the events are blocked
        // they are not generated
        t8.setDisabledIcon(i6);
        
        // Set icon text gap
        // The default gap between
        // icon and text is 4
        t7.setIconTextGap(10);
        
        // This sets the position of text
        // TOP/BOTTOM/CENTER
        // This is with respect to the icon
        // SwingConstants.TOP=1;
        // SwingConstants.BOTTOM=3;
        // SwingConstants.CENTER=0;
        // Default value is 0 (CENTER)
        t7.setVerticalTextPosition(3);
        
        // Set horizontal text position
        // RIGHT/LEFT/CENTER/LEADING/TRAILING
        // TRALIING is the default
        t7.setHorizontalTextPosition(SwingConstants.CENTER);
        
        // When focus, border painted,
        // content area filled is set to false
        // then t7 looks pretty much like a desktop icon!
        t7.setFocusPainted(false);
        t7.setBorderPainted(false);
        t7.setContentAreaFilled(false);
        
        // Print whether t4 is selected
        System.out.println("t4 is selected? "+t4.isSelected());
        
        // Print focus painted, border painted, content
        // area filled
        System.out.println("Focus painted for t4? "+t4.isFocusPainted());
        System.out.println("Border painted for t4? "+t4.isBorderPainted());
        System.out.println("Content area filled for t4? "+t4.isContentAreaFilled());
                
        // Add all JToggleButtons
        add(t1);
        add(t2);
        add(t3);
        add(t4);
        add(t5);
        add(t6);
        add(t7);
        add(t8);
        
        setSize(700,550);
        setVisible(true);
    }
   
    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable(){
            public void run()
            {
                new JToggleButtonExample();
            }
        });
    }
}