package Controller;

import java.io.IOException;
import java.util.Scanner;


public class Runner {

  public static void main(String[] args) throws IOException {
    String command;
    ImageController imgCtr = new ImageController();
    Scanner scan = new Scanner(System.in);
    while (scan.hasNextLine()){
      command = scan.nextLine();
      imgCtr.readCommand(command);
    }
  }
}
