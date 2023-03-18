package model;

import java.util.ArrayList;

/**
 * this is a class for the image.
 */
public class Image {
  private final ArrayList<Layer> layers;
  private final int width;
  private final int height;

  /**
   * this is a constructor for Iimage. 
   * @param width
   * @param height
   */
  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    layers = new ArrayList<Layer>();
    layers.add(new Layer(width, height, "background")); //completely white background layer
  }

  public Layer getLayer(int index) {
    return layers.get(index);
  }
}
