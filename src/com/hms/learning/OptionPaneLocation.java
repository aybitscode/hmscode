package com.hms.learning;
import javax.swing.*;

public class OptionPaneLocation 
{   
    private void createAndDisplayGUI()
    {       
//        JOptionPane optionPane = new JOptionPane("Its me"
//                                    , JOptionPane.PLAIN_MESSAGE
//                                    , JOptionPane.DEFAULT_OPTION
//                                    , null, null);
//        optionPane.setWantsInput(true);             
//        JDialog dialog = optionPane.createDialog( "TEST");
//        dialog.setLocation(10, 20);
//        dialog.setVisible(true);
//        System.out.println(optionPane.getInputValue());
    	  final JOptionPane pane = new JOptionPane("Hello");
    	    final JDialog d = pane.createDialog((JFrame)null, "Title");
    	    d.setLocation(10,10);
    	    d.setVisible(true);
    }

    public static void main(String... args)
    {
        Runnable runnable = new Runnable()
        {
            public void run()
            {
                new OptionPaneLocation().createAndDisplayGUI();
            }
        };
        SwingUtilities.invokeLater(runnable);
    }
}