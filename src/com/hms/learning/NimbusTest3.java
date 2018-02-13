package com.hms.learning;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class NimbusTest3 extends JFrame {

    private static final long serialVersionUID = 1L;
    private javax.swing.JToggleButton button;

    public NimbusTest3() {
        button = new javax.swing.JToggleButton();
        button.setText("Text");
        add(button);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        Timer t = new Timer(1000, new ActionListener() {

            private Random r = new Random();

            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
                try {
                    LookAndFeel lnf = UIManager.getLookAndFeel().getClass().newInstance();
                    UIDefaults uiDefaults = lnf.getDefaults();
                    uiDefaults.put("nimbusBase", c);
                    UIManager.getLookAndFeel().uninitialize();
                    UIManager.setLookAndFeel(lnf);
                } catch (InstantiationException ex) {
                } catch (IllegalAccessException ex) {
                } catch (UnsupportedLookAndFeelException ex) {
                }
                UIDefaults defaults = UIManager.getDefaults();
                defaults.put("Button.background", c);
                SwingUtilities.updateComponentTreeUI(button);
            }
        });
        t.start();
    }

    public static void main(String args[]) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            return;
        }

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new NimbusTest3().setVisible(true);
            }
        });
    }
}