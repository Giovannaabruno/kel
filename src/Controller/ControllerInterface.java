package Controller;

import Model.Layer;

import java.io.IOException;


/**
 * Interface for ControllerInterface.
 */
public interface ControllerInterface {

  /**
   * LoadImage method, take module and loads it from file name.
   *
   * @param filename equals selected filename which will be load from module
   */
  Layer loadImage(String filename, String layerName);


  String getCurrentCommand();


  void newProject(int width, int height);

  void loadProject(String path);
  //this.img = loadImage(path);

  // save-project: save the project as one file as described above
  void saveProject();

  void saveProject(String fileName);

  void addLayer(String layerName);

  void addImageToLayer(String layerName, String imageName, int xPosition, int yPosition);

  void setFilter(String layerName, String filerOption);

  // save-image file-name: save the result of applying all filters on the image
  // how can you add multiple layers with transpareses that add up too 255.
  void saveImage(String fileName);
  // before it did not throw IOException.
  void quit() throws IOException;

  // before it did not throw IOException.
  void readCommand(String line) throws IOException;
}
