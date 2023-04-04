
package controller;

import model.Layer;

/** Class GUIController
 *
 */
public class GUIController implements Features{
  /**
   * Method loadProject, represents the loadProject effect.
   * @param path to project file
   */
  @Override
  public void loadProject(String path) {

  }

  /**
   * Method saveProject, represents the saveProject effect.
   * @param filename name of the file
   */
  @Override
  public void saveProject(String filename) {

  }

  /**
   * Method  newProject, represents the  newProject effect.
   * @param height of the canvas
   * @param width  of the canvas
   */
  @Override
  public void newProject(int height, int width) {

  }

  /**
   * Method saveLayer, represents the saveLayer effect.
   * @param fileName name of the file
   * @param layer
   */
  @Override
  public void saveLayer(String fileName, Layer layer) {

  }

  /**
   * Method addLayer, represents the addLayer effect.
   * @param layerName Name of type of layer/ filter.
   */
  @Override
  public void addLayer(String layerName) {

  }

  /**
   * Method addImageToLayer, represents the addImageToLayer effect.
   * @param layerName type of layer/filter type.
   * @param imageName name of the image
   * @param xPosition x positions of the canvas
   * @param yPosition y positions of the canvas
   */
  @Override
  public void addImageToLayer(String layerName, String imageName, int xPosition, int yPosition) {

  }

  /**
   * Method setFilter, represents the setFilter effect.
   * @param layerName   name of layer/filter type.
   * @param filerOption what is the filter option called.
   */
  @Override
  public void setFilter(String layerName, String filerOption) {

  }

  /**
   * Method saveImage, represents the saveImage effect.
   * @param fileName name of the file
   */
  @Override
  public void saveImage(String fileName) {

  }
}
