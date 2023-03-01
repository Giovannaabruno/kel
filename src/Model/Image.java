package Model;

import java.util.ArrayList;

public class Image {
  private ArrayList<Layer> layers;
  private int width;
  private int height;


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
