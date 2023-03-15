package Model;

import java.util.ArrayList;

/**
 * Class Project which implements ProjectInterface.
 */
public class Project implements ProjectInterface {
  private ArrayList<Layer> layers;
  private int width;
  private int height;


  /**
   * Constructor for Project, represents objects width and height.
   * @param width of the image
   * @param height of the image
   */
  public Project(int width, int height) {
    this.width = width;
    this.height = height;
    layers = new ArrayList<Layer>();
    layers.add(new Layer(width, height, "background")); //completely white background layer
  }

  /**
   * Method getLayer for image/project.
   * @param index amount
   * @return layer index
   */
  @Override
  public Layer getLayer(int index) {
    if(index < 0 || index >= this.layers.size()) {
      throw new IllegalArgumentException("Wrong index.");
    }
    return this.layers.get(index);
  }

  /**
   * Method getLayer, represents tpe of layer.
   * @param name of layer type
   * @return layer or null for incorrect layer type
   */
  public Layer getLayer(String name) {
    for(Layer l : this.layers) {
      if(l.getName().equals(name)) {
        return l;
      }
    }
    return null;
  }

  /**
   * Method getNumLayers,represent size of the layer.
   * @return layer size
   */
  public int getNumLayers() {
    return layers.size();
  }

  /**
   * Method addLayer, add layer to Image/project.
   * @param l layer
   */
  @Override
  public void addLayer(Layer l) {
    if(l != null) {
      this.layers.add(l);
    }
    else {
      throw new IllegalArgumentException("Layer is null");
    }
  }
  public int getWidth() {
    return this.width;
  }
  public int getHeight() {
    return this.height;
  }
}
