package model;

import java.util.ArrayList;

/**
 * this is a class for the image.
 */
public class Image implements ImageInterface {
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

  /**
   * Method getLayer, gets a specific index of layers.
   * @param index of layers
   * @return a specific index of layer
   */
  public Layer getLayer(int index) {
    return layers.get(index);
  }

  /**
   *  getNumberLayers, represents the number size of layers that are being used.
   * @return the number size of layers that are being used
   */
  public int getNumberLayers() {
    return layers.size();
  }
}