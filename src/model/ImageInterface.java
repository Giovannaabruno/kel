package model;

/**
 * Interface for the ImageInterface
 */
public interface ImageInterface {
  /**
   * Method getLayer, gets a specific index of layers.
   *
   * @param index of layers
   * @return a specific index of layer
   */
  Layer getLayer(int index);

  /**
   * getNumberLayers, represents the number size of layers that are being used.
   *
   * @return the number size of layers that are being used
   */
  public int getNumberLayers();
}
