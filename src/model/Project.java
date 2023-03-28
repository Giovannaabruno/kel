package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Project which implements ProjectInterface.
 */
public class Project implements ProjectInterface {
  private ArrayList<Layer> layers;
  private int width;
  private int height;


  /**
   * Constructor for Project, represents objects width and height.
   *
   * @param width  of the image
   * @param height of the image
   */
  public Project(int height, int width) {
    this.width = width;
    this.height = height;
    layers = new ArrayList<Layer>();
    layers.add(new Layer(height, width, "background")); //completely white background layer
  }

  /**
   * Method getLayer for image/project.
   *
   * @param index amount
   * @return layer index
   */
  @Override
  public Layer getLayer(int index) {
    if (index < 0 || index >= this.layers.size()) {
      throw new IllegalArgumentException("Wrong index.");
    }
    return this.layers.get(index);
  }

  /**
   * Method getLayer, represents tpe of layer.
   *
   * @param name of layer type
   * @return layer or null for incorrect layer type
   */
  public Layer getLayer(String name) {
    for (Layer l : this.layers) {
      if (l.getName().equals(name)) {
        return l;
      }
    }
    return null;
  }

  /**
   * Method getNumLayers,represent total number of layers.
   *
   * @return layer size amount
   */
  public int getNumLayers() {
    return layers.size();
  }

  /**
   * Method addLayer, add layer to Image/project.
   *
   * @param l layer
   */
  @Override
  public void addLayer(Layer l) {
    if (l != null) {
      this.layers.add(l);
    } else {
      throw new IllegalArgumentException("Layer is null");
    }
  }

  /**
   * Method getWidth, gets the width of the project.
   *
   * @return width
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Method getHeight, gets the height of the project.
   *
   * @return height
   */
  public int getHeight() {
    return this.height;
  }


  /**
   * Number of layers
   * @return
   */
  public int getNumberLayers() {
    return this.layers.size();
  }


  /**
   * Combines all layers
   *
   * @return
   */
  public Layer combineAllLayers() {
    Layer bg = getLayer(0);
    Layer finalLayer = new Layer(bg.getHeight(), bg.getWidth(), "final");
    for(int l = 0; l < this.getNumberLayers(); l++) {
      Layer currentLayer = getLayer(l);
      for(int r = 0; r < finalLayer.getHeight(); r++) {
        for(int c = 0; c < finalLayer.getWidth(); c++) {

          Pixel oldPixel = finalLayer.getPixelAt(r, c);
          Pixel newPixel = currentLayer.getPixelAt(r, c);

          Pixel combined = new Pixel(
                  oldPixel.getRed() + newPixel.getRed(),
                  oldPixel.getGreen() + newPixel.getGreen(),
                  oldPixel.getBlue() + newPixel.getBlue(),
                  oldPixel.getAlpha() + newPixel.getAlpha()
          );
          finalLayer.setPixelAt(r, c, combined);
        }
      }
    }
    return finalLayer;
  }

}
