package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Model.Layer;
import Model.Pixel;
import Model.Project;
import View.CollagingCommandView;
import View.CollagingView;
// ask teacher about saveProject and saveImage.

/**
 * Class for ImageController which implements ControllerInterface.
 */
public class ImageController implements ControllerInterface {
  protected Project img;
  private String currentCommand;

  private CollagingView view; //kel

  /**
   * constructor for the controller.
   */
  public ImageController() {
    // Blank constructor, it does not do much and is unnessary for the controller.
  }

  private static int clamp(int value) {
    return Math.max(0, Math.min(255, value));
  }

  /**
   * LoadImage method, take module and loads it from file name.
   *
   * @param filename  equals selected filename which will be load from module
   * @param layerName name of the type of layer/filer
   * @return selected layer
   */
  public Layer loadImage(String filename, String layerName) {
    Pixel[][] pixels;
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException var12) {
      System.out.println("File " + filename + " not found!");
      return null;
    }

    StringBuilder builder = new StringBuilder();

    String s;
    while (sc.hasNextLine()) {
      s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());
    s = sc.next();
    if (!s.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    //System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    //System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    //System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    pixels = new Pixel[height][width];
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[i][j] = new Pixel(r, g, b);

        //System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
      }


    }

    //Project img = new Project(pixels[0].length, pixels.length);
    Layer layer = new Layer(pixels, layerName);
    //img.addLayer(layer);
    return layer;
  }

  /**
   * Getter method getCurrentCommand,allows user to select the current commands.
   *
   * @return currentCommand
   */
  public String getCurrentCommand() {
    return this.currentCommand;
  }

  /**
   * Method newProject will create a new
   * project with the given name and given dimensions.
   * Every project has a white background layer by default.
   *
   * @param width  of the canvas
   * @param height of the canvas
   */
  public void newProject(int width, int height) {
    this.img = new Project(width, height);
  }

  /**
   * Returns the current project
   *
   * @return the current project
   */
  public Project getProject() {
    return img;
  }

  /**
   * Method loadProject, loads a project into the program.
   *
   * @param path to project file
   */
  public void loadProject(String path) {
    File file = new File(path);
    try {
      Scanner scan = new Scanner(path);
      scan.nextLine(); //reads the "C1"
      int width = scan.nextInt();
      int height = scan.nextInt();
      int maxVal = scan.nextInt();

      img = new Project(width, height);

      while (scan.hasNext()) {
        String layerName = scan.next();
        String filterName = scan.next();

        Pixel[][] pixels = new Pixel[height][width];
        for (int i = 0; i < height; i++) { //rows
          for (int j = 0; j < width; j++) { //columns  (might be switched later)
            int red = scan.nextInt();
            int green = scan.nextInt();
            int blue = scan.nextInt();
            int alpha = scan.nextInt();
            pixels[i][j] = new Pixel(red, green, blue, alpha);
          }
        }
        Layer l = new Layer(pixels, layerName);
        l.setFilter(filterName);
        img.addLayer(l);

      }


    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }

  }

  /**
   * Method saveProject, save the project as one file as described above.
   */
  public void saveProject() {
    saveProject("tako.ppm");

  }

  /**
   * Method saveProject, save the project as one file as described above.
   *
   * @param filename name of the file
   */
  /// method that saves file of a txt
  public void saveProject(String filename) {
    File file = new File(filename);
    try {
      int width = img.getWidth();
      int height = img.getHeight();
      PrintWriter pw = new PrintWriter(file);
      pw.println("C1"); // maybe p3
      pw.print(width + " ");
      pw.println(height);
      pw.println(255); //incomplete: not the true maximum value.
//      for (int i = 0; i < img.getNumLayers(); i++) {
//        Layer layer = img.getLayer(i);
//        pw.print(layer.getName() + " ");
//        pw.println(layer.getFilter());
      for (int i = 0; i < img.getNumLayers(); i++) {
        Layer layer = img.getLayer(i);
        pw.println(layer.getName() + " " + layer.getFilter());

        for (int j = 0; j < width; j++) {
          for (int k = 0; k < height; k++) {
            Pixel pix = layer.getPixelAt(j, k);
            pw.print(pix.getRed() + " ");
            pw.print(pix.getGreen() + " ");
            pw.print(pix.getBlue() + " ");
            pw.println(pix.getAlpha()); //might be .print with a space at the end

          }
        }
        pw.println();
      }

    } catch (FileNotFoundException ex) {
      throw new RuntimeException(ex);
    }


  }

  /**
   * Method saveLayer, saves layer as ppm file.
   *
   * @param fileName name of the file
   */
  private void saveLayer(String fileName, Layer layer) {
    File file = new File(fileName);
    try {
      int width = layer.getWidth();
      int height = layer.getHeight();
      PrintWriter pw = new PrintWriter(file);
      pw.println("C1"); // maybe p3
      pw.print(width + " ");
      pw.println(height);
      pw.println(255); //incomplete: not the true maximum value.
//      for (int i = 0; i < img.getNumLayers(); i++) {
//        Layer layer = img.getLayer(i);
//        pw.print(layer.getName() + " ");
//        pw.println(layer.getFilter());
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < height; k++) {
          Pixel pix = layer.getPixelAt(j, k);
          pw.print(pix.getRed() + " ");
          pw.print(pix.getGreen() + " ");
          pw.print(pix.getBlue() + " ");
          pw.println(pix.getAlpha());

        }
      }
    } catch (FileNotFoundException ex) {
      throw new RuntimeException(ex);
    }


  }

  /**
   * Method addLayer,  adds a new layer with the given name to the top of the whole project.
   * This layer always has a fully transparent white image and the Normal filter on by default.
   * Any attempt at creating another layer with the same name reports an error to the user,
   * but continues the program.
   *
   * @param layerName Name of type of layer/ filter.
   */
  public void addLayer(String layerName) {
    Layer l0 = this.img.getLayer(0);
    Layer layer = new Layer(l0.getWidth(), l0.getHeight(), layerName);
    this.img.addLayer(layer);
  }

  /**
   * Method addImageToLayer,places an image on the layer such that the top
   * left corner of the image is at (x-pos, y-pos).
   *
   * @param layerName type of layer/filter type.
   * @param imageName name of the image
   * @param xPosition x positions of the canvas
   * @param yPosition y positions of the canvas
   */
  public void addImageToLayer(String layerName, String imageName, int xPosition, int yPosition) {
    Layer layer = img.getLayer(layerName);
    Layer l1 = this.loadImage(imageName, layerName);
    //System.out.println("TEST:" + l1.getGrid()[0][0].getRed());

    //    Layer l1 = newImg.getLayer(1);
    try {
      for (int r = 0; r < l1.getHeight(); r++) {
        for (int c = 0; c < l1.getWidth(); c++) {
          Pixel p = l1.getPixelAt(r, c);
          layer.setPixelAt(r + yPosition, c + xPosition, p);
        }
      }
    } catch (Exception e) {
      System.out.print("error");
    }
  }
// save-image file-name: save the result of applying all filters on the image
// how can you add multiple layers with transpareses that add up too 255.

  /**
   * Method setFilter, sets the filter of the given layer where filter-option
   * is one of the following at the moment.
   *
   * @param layerName   name of layer/filter type.
   * @param filerOption what is the filter option called.
   */
  public void setFilter(String layerName, String filerOption) {
    Layer l = this.img.getLayer(layerName);
    int amount = 20;
    if (filerOption.equals("red-component")) {
      l.setFilter("red-component");
    } else if (filerOption.equals("green-component")) {
      l.setFilter("green-component");
    } else if (filerOption.equals("blue-component")) {
      l.setFilter("blue-component");
    } else if (filerOption.equals("brighten-value")) {
      l.setFilter("brighten-value", amount);
    } else if (filerOption.equals("brighten-intensity")) {
      l.setFilter("brighten", amount);
    } else if (filerOption.equals("brighten-luma")) {
      l.setFilter("brighten-luma", amount);
    } else if (filerOption.equals("darken-value")) {
      l.setFilter("darken-value", amount);
    } else if (filerOption.equals("darken-intensity")) {
      l.setFilter("darken", amount);
    } else if (filerOption.equals("darken-luma")) {
      l.setFilter("darken-luma", amount);
    }
  }

    /**
     * Method Save image, save the result of applying all filters on the image.
     */
    public void saveImage (String fileName){
      Layer firstLayer = this.img.getLayer(0);
      Layer finalLayer = new Layer(
              firstLayer.getWidth(),
              firstLayer.getHeight(),
              "layer1");

      //concern: this doesn't use alpha to determine how much each layer contributes
      //a layer with 0 alpha will affect the end result just as much as a layer with 255 alpha
      for (int i = 0; i < this.img.getNumLayers(); i++) {
        Layer layer = this.img.getLayer(i);
        for (int r = 0; r < layer.getHeight(); r++) {
          for (int c = 0; c < layer.getWidth(); c++) {
            Pixel layerp = layer.getPixelAt(r, c);
            Pixel finalp = finalLayer.getPixelAt(r, c);
            int newred = clamp(finalp.getRed() + layerp.getRed());
            int newgreen = clamp(finalp.getGreen() + layerp.getGreen());
            int newblue = clamp(finalp.getBlue() + layerp.getBlue());
            int newalpha = clamp(finalp.getAlpha() + layerp.getAlpha());

            finalp.setRed(newred);
            finalp.setGreen(newgreen);
            finalp.setBlue(newblue);
            finalp.setAlpha(newalpha);
          }
        }
      }
      saveLayer(fileName, finalLayer);

    }

    /**
     * Method quit, quits the project and loses all unsaved work.
     */
    public void quit () throws IOException {
      this.img = null;
      this.view = new CollagingCommandView(); // kal
      this.view.renderMessage("Quiting the project... Bye!"); //kal
      System.exit(0);
    }


    /**
     * Method readCommand, reader for the layer/ filter type commands.
     *
     * @param line
     */
    public void readCommand (String line) throws IOException {
      String[] words = line.split(" ");
      this.currentCommand = words[0];
      switch (words[0]) {
        case "new-project":
          //two different ways to create a project: with a name or with dimensions
          if (words.length > 2) {
            newProject(Integer.parseInt(words[2]), Integer.parseInt(words[1]));
          } else {
            newProject(600, 600);
            //the name doesn't do anything right now
          }
          break;
        case "add-layer":
          addLayer(words[1]);
          break;
        case "add-image-to-layer":
          String layerName = words[1];
          String imageName = words[2];
          int xPos = Integer.parseInt(words[3]);
          int yPos = Integer.parseInt(words[4]);
          addImageToLayer(layerName, imageName, xPos, yPos);
          break;
        case "set-filter":
          String layer = words[1];
          String filerOption = words[2];
          setFilter(layer, filerOption);
          break;
        case "save-image":
          if (words.length > 1) {
            saveImage(words[1]);
          } else {
            throw new IllegalArgumentException("Too few arguments, need filename");
          }
          break;
        case "save-project":
          if (words.length > 1) {
            saveProject(words[1]);
          } else {
            saveProject();
          }
          break;
        case "load-project":
          loadProject(words[1]);
          break;
        case "quit":
        case "QUIT":
          this.quit();
          break;
        default:
          System.out.println("Invalid command");
      }

    }
  }
