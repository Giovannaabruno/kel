package Model;

import java.util.ArrayList;

public class Project implements ProjectInterface {
  private ArrayList<Layer> layers;
  private int width;
  private int height;


  public Project(int width, int height) {
    this.width = width;
    this.height = height;
    layers = new ArrayList<Layer>();
    layers.add(new Layer(width, height, "background")); //completely white background layer
  }

  @Override
  public Layer getLayer(int index) {
    if(index < 0 || index >= this.layers.size()) {
      throw new IllegalArgumentException("Wrong index.");
    }
    return this.layers.get(index);
  }

  public Layer getLayer(String name) {
    for(Layer l : this.layers) {
      if(l.getName().equals(name)) {
        return l;
      }
    }
    return null;
  }
  public int getNumLayers() {
    return layers.size();
  }

  @Override
  public void addLayer(Layer l) {
    if(l != null) {
      this.layers.add(l);
    }
    else {
      throw new IllegalArgumentException("Layer is null");
    }
  }
}
