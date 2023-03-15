package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Model.Project;
import Model.Layer;
import Model.Pixel;
import View.CollagingCommandView;
import View.CollagingView;

// ask teacher about saveProject and saveImage.
public class ImageController implements ControllerInterface {
  protected Project img;
  private String currentCommand;
  private CollagingView view;

//  public ImageController() {
//
//
//  }


  /**
   * LoadImage method, take module and loads it from file name.
   * @param filename  equals selected filename which will be load from module
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


  public String getCurrentCommand() {
    return this.currentCommand;
  }


  public void newProject(int width, int height) {
    this.img = new Project(width, height);
  }

  public void loadProject(String path) {
    //this.img = loadImage(path);

  }
// save-project: save the project as one file as described above
  public void saveProject() {
    saveProject("tako.ppm");

  }

  public void saveProject(String fileName) {
    File file = new File(fileName);
    try {
      int width = img.getLayer(0).getWidth();
      int height = img.getLayer(0).getHeight();
      PrintWriter pw = new PrintWriter(file);
      pw.println("C1");
      pw.print(width + " ");
      pw.println(height);
      pw.println(255); //incomplete: not the true maximum value.
      for(int i = 0 ; i < img.getNumLayers() ; i++) {
        Layer layer = img.getLayer(i);
        pw.print(layer.getName() + " ");
        pw.println(layer.getFilter());
        for(int j = 0; j < width ; j++) {
          for(int k = 0; k < height ; k++) {
            Pixel pix = layer.getPixelAt(j, k);
            pw.print(pix.getRed() + " ");
            pw.print(pix.getGreen() + " ");
            pw.print(pix.getBlue() + " ");
            pw.println(pix.getAlpha());

          }
        }
      }
      pw.close();



    }catch( Exception e) {

    }
  }

  public void addLayer(String layerName) {
    Layer l0 = this.img.getLayer(0);
    Layer layer = new Layer(l0.getWidth(), l0.getHeight(), layerName);
    this.img.addLayer(layer);
  }

  public void  addImageToLayer(String layerName, String imageName, int xPosition, int yPosition) {
    Layer layer = img.getLayer(layerName);
    Layer l1 = this.loadImage(imageName, layerName);
//    Layer l1 = newImg.getLayer(1);
    for(int r = 0; r < l1.getHeight(); r++) {
      for(int c = 0; c < l1.getWidth(); c++) {
        Pixel p = l1.getPixelAt(r, c);
        layer.setPixelAt(r + yPosition, c + xPosition, p);
      }
    }
  }

  public void  setFilter(String layerName, String filerOption) {
    Layer l = this.img.getLayer(layerName);
    if (filerOption.equals("red-component")) {
      l.setFilter("red-component");

    } else if (filerOption.equals("green-component")) {
      l.setFilter("green-component");

    } else if (filerOption.equals("blue-component")) {
      l.setFilter("blue-component");

    } else if (filerOption.equals("brighten-value")) {
      l.setFilter("brighten");

    } else if (filerOption.equals("darken-value")) {
      l.setFilter("darken");
    }
  }
// save-image file-name: save the result of applying all filters on the image
// how can you add multiple layers with transpareses that add up too 255.
  public void  saveImage() {


  }

  public void  quit() throws IOException {
    this.img = null;
    this.view = new CollagingCommandView();
    this.view.renderMessage("Quiting the project... Bye!");
    System.exit(0);
  }


    public void readCommand(String line) throws IOException {
    String[] words = line.split(" ");
    this.currentCommand = words[0];
    switch(words[0]) {
      case "new-project":
        //two different ways to create a project: with a name or with dimensions
        if(words.length > 2) {
          newProject(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
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
        break;
      case "save-project":
        if(words.length > 1) {
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
