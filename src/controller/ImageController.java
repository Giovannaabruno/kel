package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import model.Layer;
import model.Pixel;
import model.Project;
import view.CollagingCommandView;
import view.CollagingView;

import static java.lang.System.out;
// ask teacher about saveProject and saveImage.

/**
 * Class for ImageController which implements ControllerInterface.
 */
public class ImageController implements ControllerInterface {
  protected Project img;
  private String currentCommand;

  /**
   * constructor for the controller.
   */
  public ImageController() {
    // Blank constructor, it does not do much and is unnessary for the controller.
  }

  /**
   * Method clamps, sets a value of range between a
   * defined minimum bound and a maximum bound.
   * @param value amount
   * @return Max range of values
   */
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
      out.println("File " + filename + " not found!");
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
      out.println("Invalid PPM file: plain RAW file should begin with P3");
      return null;
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
   * @return
   */
  public Project newProject(int height, int width) {
    this.img = new Project(height, width);
    return this.img;
  }

  /**
   * Returns the current project.
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
      Scanner scan = new Scanner(file);
      /*Remove later*/
      out.println("Scanner working");

      String str = scan.next(); //reads the "C1"
      /*Remove later*/
      out.println("first line: " + str);
      int width = scan.nextInt();
      int height = scan.nextInt();
      int maxVal = scan.nextInt();

      /*Remove later*/
      out.println("Width/height/max read");

      img = new Project(height, width);

      while (scan.hasNext()) {
        String layerName = scan.next();
        String filterName = scan.next();

        /*Remove later*/
        out.println("Layer name: " + layerName);
        /*Remove later*/
        out.println("Filter name: " + filterName);


        Pixel[][] pixels = new Pixel[height][width];
        for (int i = 0; i < height; i++) { //rows
          for (int j = 0; j < width; j++) { //columns  (might be switched later)
            int red = 0;
            int green = 0;
            int blue = 0;
            int alpha = 0;
            if (scan.hasNextInt()) {
              red = scan.nextInt();
            }
            if (scan.hasNextInt()) {
              green = scan.nextInt();
            }
            if (scan.hasNextInt()) {
              blue = scan.nextInt();
            }
            if (scan.hasNextInt()) {
              alpha = scan.nextInt();
            }
            pixels[i][j] = new Pixel(red, green, blue, alpha);
          }
        }
        Layer l = new Layer(pixels, layerName);
        l.setFilter(filterName, 30);
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
    saveProject("image.Project");

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

        for (int j = 0; j < height; j++) {
          for (int k = 0; k < width; k++) {
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
      for (int j = 0; j < height; j++) {
        for (int k = 0; k < width; k++) {
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
    Layer layer = new Layer(l0.getHeight(), l0.getWidth(), layerName);
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
      out.print("error");
      e.printStackTrace();
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
    switch (filerOption) {
      case "red-component":
        l.setFilter("red-component", amount);
        break;
      case "green-component":
        l.setFilter("green-component", amount);
        break;
      case "blue-component":
        l.setFilter("blue-component", amount);
        break;
      case "brighten-value":
        l.setFilter("brighten-value", amount);
        break;
      case "brighten-intensity":
        l.setFilter("brighten", amount);
        break;
      case "brighten-luma":
        l.setFilter("brighten-luma", amount);
        break;
      case "darken-value":
        l.setFilter("darken-value", amount);
        break;
      case "darken-intensity":
        l.setFilter("darken", amount);
        break;
      case "darken-luma":
        l.setFilter("darken-luma", amount);
        break;
      case "darkenBlending":
        l.setFilter("darkenBlending", amount);
        break;
      case "brightenBlending":
        l.setFilter("brightenBlending", amount);
        break;
      case "inversionBlending":
        l.setFilter("inversionBlending", amount);
        break;

      default:
        l.setFilter("red-component", 30);
    }
  }


  /**
   * Method Save image, save the result of applying all filters on the image.
   *
   * @param fileName name of the file
   */
  public void saveImage(String fileName) {
    Layer firstLayer = this.img.getLayer(0);
    Layer finalLayer = new Layer(
            firstLayer.getHeight(),
            firstLayer.getWidth(),
            "layer1");

    /*remove later*/
    out.println("FirstLayer Height, Width: " + firstLayer.getHeight()
            + ", " + firstLayer.getWidth());
    out.println(finalLayer.getHeight() + ", " + finalLayer.getWidth());

    //concern: this doesn't use alpha to determine how much each layer contributes
    //a layer with 0 alpha will affect the end result just as much as a layer with 255 alpha
    for (int i = 0; i < this.img.getNumLayers(); i++) {
      Layer layer = this.img.getLayer(i);
      out.println("Layer #" + i + " has height of " + layer.getHeight() +
              " and width " + layer.getWidth());
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
   *
   * @throws IOException found error
   */
  public void quit() throws IOException {
    this.img = null;
    //kel
    CollagingView view = new CollagingCommandView(out); // added the out agrument
    view.renderMessage("Quiting the project... Bye!"); //kal
    //System.exit(0); //warning: will stop all programs
  }


  /**
   * Method readCommand, reader for the layer/ filter type commands.
   *
   * @param line input command
   * @throws IOException found error
   */
  public void readCommand(String line) throws IOException {
    String[] words = line.split(" ");
    this.currentCommand = words[0];
    switch (words[0]) {
      case "new-project":
        //two different ways to create a project: with a name or with dimensions
        if (words.length > 2) {
          newProject(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
        } else {
          newProject(600, 800);
          //the name doesn't do anything right now
        }
        break;
      case "add-layer":
        addLayer(words[1]);
        break;
      case "add-image-to-layer":
        String layerName = words[1];
        String imageName = words[2];
        int xPos = 0;
        int yPos = 0;
        if (words.length > 3) {
          xPos = Integer.parseInt(words[3]);
          yPos = Integer.parseInt(words[4]);
        }

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
        System.out.println(words[1]);
        loadProject(words[1]);
        break;
      case "quit":
      case "QUIT":
        this.quit();
        break;
      default:
        out.println("Invalid-command");
        this.currentCommand = "Invalid-command";
    }

  }
}