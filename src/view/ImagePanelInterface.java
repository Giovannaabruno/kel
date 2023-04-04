package view;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Interface for ImagePanelInterface.
 */
public interface ImagePanelInterface {
  /**
   * Method  BufferedImag, represent BufferedImag for image.
   *
   * @return image
   */
  BufferedImage getImage();

  /**
   * Method paintComponent, allows the orginal image to be altered.
   *
   * @param g the <code>Graphics</code> object to protect
   */
  void paintComponent(Graphics g);
}
