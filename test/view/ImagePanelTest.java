package view;

import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Image;

import static org.junit.Assert.*;

/**
 * testers for ImagePanel class.
 */
public class ImagePanelTest {

  /**
   *
   * @throws IOException
   */
  @Test
  public void getImage() throws IOException {
    BufferedImage img = ImageIO.read(new File("images/tako.ppm"));
    ImagePanel panel = new ImagePanel(img);
    assertEquals(panel.getImage(), ImageIO.read(new File("images/tako.ppm")));
    assertNull(panel.getImage());

  }

  /**
   *  Tester for paintComponent method.
   */
  @Test
  public void paintComponent() throws IOException {
    BufferedImage img = ImageIO.read(new File("images/tako.ppm"));
    ImagePanel panel = new ImagePanel(img);
    assertEquals(panel.getImage(), img);
    if(panel != null && img != null) {
      assertEquals(panel.getImage().getWidth(), img.getWidth());
    }
  }

}
