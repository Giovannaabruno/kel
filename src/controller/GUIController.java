package controller;

import model.Layer;

/**
 * Class GUIController.
 */
public class GUIController implements Features {
  /**
   * Method loadProject, represents the loadProject effect.
   *
   * @param path to project file
   */
  @Override
  public void loadProject(String path) {
    System.out.println("HI");
  }

  /**
   * Method saveProject, represents the saveProject effect.
   *
   * @param filename name of the file
   */
  @Override
  public void saveProject(String filename) {
    System.out.println("HI");

  }

  /**
   * Method  newProject, represents the  newProject effect.
   *
   * @param height of the canvas
   * @param width  of the canvas
   */
  @Override
  public void newProject(int height, int width) {
    System.out.println("HI");
  }

  /**
   * Method saveLayer, represents the saveLayer effect.
   *
   * @param fileName name of the file
   * @param layer equals Layer
   */
  @Override
  public void saveLayer(String fileName, Layer layer) {
    System.out.println("HI");
  }

  /**
   * Method addLayer, represents the addLayer effect.
   *
   * @param layerName Name of type of layer/ filter.
   */
  @Override
  public void addLayer(String layerName) {
    System.out.println("HI");
  }

  /**
   * Method addImageToLayer, represents the addImageToLayer effect.
   *
   * @param layerName type of layer/filter type.
   * @param imageName name of the image
   * @param xPosition x positions of the canvas
   * @param yPosition y positions of the canvas
   */
  @Override
  public void addImageToLayer(String layerName, String imageName, int xPosition, int yPosition) {
    System.out.println("HI");
  }

  /**
   * Method setFilter, represents the setFilter effect.
   *
   * @param layerName   name of layer/filter type.
   * @param filerOption what is the filter option called.
   */
  @Override
  public void setFilter(String layerName, String filerOption) {
    System.out.println("HI");
  }

  /**
   * Method saveImage, represents the saveImage effect.
   *
   * @param fileName name of the file
   */
  @Override
  public void saveImage(String fileName) {
    System.out.println("HI");
  }
}
