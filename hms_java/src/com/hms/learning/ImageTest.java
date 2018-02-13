package com.hms.learning;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;

public class ImageTest {

  public static void main(String[] args) {
    ImagePanel panel = new ImagePanel(new ImageIcon("C:\\HotelManagement\\boot\\images\\welcome_bg_1200x500.jpg").getImage());

    JFrame frame = new JFrame();
    frame.getContentPane().add(panel);
    GridBagLayout gbl_panel = new GridBagLayout();
    gbl_panel.columnWidths = new int[]{0, 0};
    gbl_panel.rowHeights = new int[]{0, 0};
    gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
    gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
    panel.setLayout(gbl_panel);
    
    JLabel lblWelcomeToThisworld = new JLabel("Welcome to thisworld");
    GridBagConstraints gbc_lblWelcomeToThisworld = new GridBagConstraints();
    gbc_lblWelcomeToThisworld.gridx = 0;
    gbc_lblWelcomeToThisworld.gridy = 0;
    panel.add(lblWelcomeToThisworld, gbc_lblWelcomeToThisworld);
    frame.pack();
    frame.setVisible(true);
  }
}

class ImagePanel extends JPanel {

  private Image img;

  public ImagePanel(String img) {
    this(new ImageIcon(img).getImage());
  }

  public ImagePanel(Image img) {
    this.img = img;
    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);
  }

  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);
  }

}