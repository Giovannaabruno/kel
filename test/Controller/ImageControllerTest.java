package Controller;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ImageControllerTest {

  @Test
  public void testLoadImage() {
  }

  @Test
  public void testGetCurrentCommand() {
  }

  @Test
  public void testNewProject() {
  }

  @Test
  public void testLoadProject() {
  }

  @Test
  public void testSaveProject() {
  }

  @Test
  public void testTestSaveProject() {
  }

  @Test
  public void testAddLayer() {
  }

  @Test
  public void testAddImageToLayer() {
  }

  @Test
  public void testSetFilter() {
  }

  @Test
  public void testSaveImage() {
  }

  @Test
  public void testQuit() {
  }

  @Test
  public void testReadCommand() throws IOException {
    ImageController ic = new ImageController();
    // command 1
    ic.readCommand("new-project tako");
    assertEquals(ic.getCurrentCommand(), "new-project");
    // command 2
    ic.readCommand("add-layer tako");
    assertEquals(ic.getCurrentCommand(), "add-layer");
    //commdand 3
    ic.readCommand("add-image-to-layer tako");
    assertEquals(ic.getCurrentCommand(), "add-image-to-layer");
    //command 4
    ic.readCommand("set-filter tako");
    assertEquals(ic.getCurrentCommand(), "set-filter");
    //command 5
    ic.readCommand("save-image tako");
    assertEquals(ic.getCurrentCommand(), "save-image");

    //command 5
    ic.readCommand("save-project");
    assertEquals(ic.getCurrentCommand(), "save-project");

    //command 5
    ic.readCommand("load-project tako");
    assertEquals(ic.getCurrentCommand(), "load-project");

    //command 5
    ic.readCommand("quit");
    assertEquals(ic.getCurrentCommand(), "quit");



  }
}