package com.hms.learning;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class ComboBoxTwo extends JFrame implements ItemListener, FocusListener, PopupMenuListener {

    private static final long serialVersionUID = 1L;
    private JComboBox mainComboBox;
    private JComboBox subComboBox;
    private JComboBox comboBox;

    public ComboBoxTwo() {
        String[] items = {"Select Item", "Color", "Shape", "Fruit"};
        String[] subItems1 = {"Select Color", "Red", "Blue", "Green"};
        mainComboBox = new JComboBox(items);
        mainComboBox.addItemListener(this);
        mainComboBox.addFocusListener(fcsListener);
        mainComboBox.setEditable(true);
        add(mainComboBox, BorderLayout.WEST);
        subComboBox = new JComboBox(subItems1);
        subComboBox.setEditable(true);
        subComboBox.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXX");
        subComboBox.addItemListener(this);
        
        //add(subComboBox, BorderLayout.EAST);
        comboBox = new JComboBox();
        comboBox.setEditable(true);
        add(comboBox, BorderLayout.EAST);
        
        comboBox.addPopupMenuListener(this);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (e.getSource() == mainComboBox) {
                System.out.println("Source  : mainComboBox");
            } else if (e.getSource() == subComboBox) {
                System.out.println("Source  : subComboBox");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new ComboBoxTwo();
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
//
    private FocusListener fcsListener = new FocusListener() {

        @Override
        public void focusGained(FocusEvent e) {
            dumpInfo(e);
        }

        @Override
        public void focusLost(FocusEvent e) {
            dumpInfo(e);
        }

        private void dumpInfo(FocusEvent e) {
            System.out.println("Source  : " + name(e.getComponent()));
            System.out.println("Opposite : " + name(e.getOppositeComponent()));
            System.out.println("Temporary: " + e.isTemporary());
            final Component c = e.getComponent();//works for editable JComboBox too
            if (c instanceof JFormattedTextField) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        ((JFormattedTextField) c).selectAll();
                    }
                });
            } else if (c instanceof JTextField) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        ((JTextField) c).selectAll();
                    }
                });
            }
        }

        private String name(Component c) {
            return (c == null) ? null : c.getName();
        }
    };

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void popupMenuCanceled(PopupMenuEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("from visible cancelled");
	}

	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("from in visible");
	}

	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("from visible");
	}
}