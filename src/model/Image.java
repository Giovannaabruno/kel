package model;

import java.util.ArrayList;

/**
 * this is a class for the image.
 */
public class Image {
  private final ArrayList<Layer> layers;

  /**
   * this is a constructor for Iimage.
   * @param width for image
   * @param height for image
   */
  public Image(int width, int height) {
    layers = new ArrayList<Layer>();
    layers.add(new Layer(width, height, "background")); //completely white background layer
  }

  public Layer getLayer(int index) {
    return layers.get(index);
  }

  public int getNumberLayers() {
    return layers.size();
  }
}