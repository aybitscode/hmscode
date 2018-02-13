package com.hms.learning;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

public class BorderPanel extends JFrame{

    public BorderPanel() {

        JButton btn0 = new JButton( "Hilfe" );
        JButton btn1 = new JButton( "Apply" );
        JButton btn2 = new JButton( "Cancel" );
        JButton btn3 = new JButton( "OK" );

        JPanel sub1 = new JPanel(new FlowLayout( FlowLayout.TRAILING, 5, 5 ));
        JPanel sub2 = new JPanel(new FlowLayout( FlowLayout.TRAILING, 5, 5 ));

        JMenuBar mnu = new JMenuBar();
        mnu.add( new JMenu( "File" ) );
        mnu.add( new JMenu( "Insert" ) );
        mnu.add( new JMenu( "Edit" ) );
        mnu.add( new JMenu( "Help" ) );

        JComboBox<String> cmb = new JComboBox<String>();
        cmb.getEditor().getEditorComponent().addFocusListener( new FocusAdapter() {
            @Override
            public void focusGained( FocusEvent e ) {
                if(e.getSource() instanceof JTextComponent) {
                    ((JTextComponent)e.getSource()).setCaretPosition( 0 );
                }
            }
        } );

        cmb.setEditable( true );
        cmb.addItem("");
        cmb.addItem( "Lorem" );
        cmb.addItem( "Ipsum" );
        cmb.addItem( "Dolor" );
        cmb.addItem( "Sit Amet" );

        JPanel pnl = new JPanel();
        pnl.add(cmb);

        sub1.add( btn0 );
        sub2.add( btn1 );
        sub2.add( btn2 );
        sub2.add( btn3 );

        JPanel bar = new JPanel();
        bar.setLayout( new BorderLayout( 20, 20 ) );
        bar.add( sub1, BorderLayout.WEST );
        bar.add( Box.createHorizontalGlue(), BorderLayout.CENTER );
        bar.add( sub2, BorderLayout.EAST );

        setDefaultCloseOperation( EXIT_ON_CLOSE );
        getContentPane().setLayout( new BorderLayout() );
        getContentPane().add( mnu, BorderLayout.NORTH );
        getContentPane().add( pnl, BorderLayout.CENTER );
        getContentPane().add( bar, BorderLayout.SOUTH );
        setSize( new Dimension( 600, 400 ) );
        setLocationRelativeTo( null );
    }

    public static void main( String[] args ) {
        EventQueue.invokeLater( new Runnable() {
            @Override
            public void run() {
                new BorderPanel().setVisible( true );
            }
        } );
    }
}