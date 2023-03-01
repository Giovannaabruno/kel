package Controller;

import Model.Image;

public class ImageController {
  private Image img;

  public ImageController() {

  }

  public void readCommand(String line) {
    String[] words = line.split(" ");

    switch(words[0]) {
      case "new-project":
        break;
      case "add-layer":
        break;
      case "add-image-to-layer":
        break;
      case "set-filter":
        break;
      case "save-image":
        break;
      case "save-project":
        break;
      case "load-project":
        break;
      case "quit":
        break;
      default:
        System.out.println("Invalid command");
    }

  }
}
