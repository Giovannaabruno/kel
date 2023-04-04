package view;

import org.junit.Test;

import java.awt.image.BufferedImage;

import controller.ImageController;
import model.Layer;
import model.Pixel;

import static org.junit.Assert.*;

/**
 * Testers for JFrameView class.
 */
public class JFrameViewTest {

  /**
   * Tester for runsWithoutException method.
   */
  @Test
  public void testRunsWithoutException() {
    JFrameView view = new JFrameView();
    ImageController ic = new ImageController();
    assertNotNull(ic);
    assertNotNull(view);

  }

  /**
   * Tester for PpmImageToBufferedImage method.
   */
  @Test
  public void testPpmImageToBufferedImage() {
    JFrameView view = new JFrameView();
    ImageController ic = new ImageController();
    Layer layer = ic.loadImage("images/tako.ppm", "tako");
    BufferedImage img = view.ppmImageToBufferedImage(layer);
    int topLeftRGB = img.getRGB(0, 0);

    Pixel topLeft = layer.getPixelAt(0, 0);

    int argb = topLeft.getAlpha() << 24;
    argb |= topLeft.getRed() << 16;
    argb |= topLeft.getGreen() << 8;
    argb |= topLeft.getBlue();
    assertEquals(topLeftRGB,  argb);
    assertNotEquals(topLeftRGB, 0);
  }

  /**
   * Tester for renderMessage method.
   */
  @Test
  public void renderMessage() {

  }
}