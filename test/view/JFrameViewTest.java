package view;

import org.junit.Test;

import java.awt.image.BufferedImage;

import controller.ImageController;
import model.Layer;

import static org.junit.Assert.*;

public class JFrameViewTest {

  @Test
  public void runsWithoutException() {

    JFrameView view = new JFrameView();
    ImageController ic = new ImageController();

  }

  @Test
  public void ppmImageToBufferedImage() {
    JFrameView view = new JFrameView();
    ImageController ic = new ImageController();
    Layer layer = ic.loadImage("tako.ppm", "tako");
    BufferedImage img = view.ppmImageToBufferedImage(layer);
    int topLeftRGB = img.getRGB(0, 0);

  }

  @Test
  public void renderMessage() {
  }
}