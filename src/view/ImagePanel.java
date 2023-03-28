package view;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import model.Image;

/**
 * Class representing a panel which holds an image in a scroll pane.
 */
public class ImagePanel extends JPanel {
  private final JLabel imageLabel;
  private BufferedImage img;
  
  /**
   * Constructor for the ImagePanel class.
   */
  public ImagePanel(BufferedImage img) {
    super();
    this.setBorder(new TitledBorder("Current Image"));
    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    this.img = img;
    this.imageLabel  = new JLabel();
    setPreferredSize(new Dimension(600, 800));
  }


  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    System.out.println("Calling paint");
    g.drawImage(this.img, 0, 0,  getWidth(), getHeight(), this); /// draw image
  }
}
