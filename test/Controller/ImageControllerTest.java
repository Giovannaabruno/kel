package Controller;

import org.junit.Test;

import java.io.IOException;

import Model.Layer;

import static org.junit.Assert.assertEquals;

public class ImageControllerTest {

  // Test Load Image - with Valid PPM file format - check Height.
  @Test
  public void testLoadImageValidFormatHeight() {
    ImageController imgCtr = new ImageController();
    Layer l1 = imgCtr.loadImage("images/A4-sample2.ppm", "dark");
    assertEquals(500, l1.getHeight());
  }

  // Test Load Image - with Valid PPM file format - check Width.
  @Test
  public void testLoadImageValidFormatWidth() {
    ImageController imgCtr = new ImageController();
    Layer l1 = imgCtr.loadImage("images/A4-sample2.ppm", "dark");
    assertEquals(400, l1.getWidth());
  }

  // Test Load Image - with invalid PPM file format.
  @Test
  public void testLoadImageWrongFormat() {
    ImageController imgCtr = new ImageController();
    assertEquals(null,
            imgCtr.loadImage("images/wrong_format.ppm", "dark"));
  }



  @Test
  public void testGetCurrentCommand() {
  }

  // Test new project with dimensions - check width.
  @Test
  public void testNewProjectCheckWidth() throws IOException {
    ImageController imgCtr = new ImageController();
    imgCtr.readCommand("new-project 800 600");
    assertEquals(800, imgCtr.img.getWidth());
  }

  // Test new project with dimensions - check height.
  @Test
  public void testNewProjectCheckHeight() throws IOException {
    ImageController imgCtr = new ImageController();
    imgCtr.readCommand("new-project 800 600");
    assertEquals(600, imgCtr.img.getHeight());
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

  // Test quiting the project.
  @Test
  public void testQuit() throws IOException {
    ImageController imgCtr = new ImageController();
    imgCtr.quit();
    assertEquals(null, imgCtr.img);
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