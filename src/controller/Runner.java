package controller;

import java.io.IOException;
import java.util.Scanner;

/**
 * Runner class.
 */
public class Runner {

  /**
   * Main Methode, the entry point of an executable program;
   * it is where the program control starts and ends.
   *
   * @param args arguments
   */
  public static void main(String[] args) throws IOException {
    String command;
    ImageController imgCtr = new ImageController();
    Scanner scan = new Scanner(System.in);
    while (scan.hasNextLine()) {
      command = scan.nextLine();
      imgCtr.readCommand(command);
    }
  }
}
