package controller;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import model.Layer;
import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Testers for ImageController class.
 */
public class ImageControllerTest {

  /**
   * Tester that test LoadImage method.
   */
  @Test
  public void testLoadImage() {
    ImageController ic = new ImageController();
    ic.newProject(800,600);
    Layer l = ic.loadImage("images/tako.ppm", "layer1");
    // check that it doesn't
    assertNotNull(l);
    ///THIS NEEDS assert!!!!

  }

  /**
   * Test Load Image - with Valid PPM file format - check Height.
   */
  @Test
  public void testLoadImageValidFormatHeight() {
    ImageController imgCtr = new ImageController();
    Layer l1 = imgCtr.loadImage("images/tako.ppm", "dark");
    assertEquals(800, l1.getHeight());
  }

  /**
   * Test Load Image - with Valid PPM file format - check Width.
   */
  @Test
  public void testLoadImageValidFormatWidth() {
    ImageController imgCtr = new ImageController();
    Layer l1 = imgCtr.loadImage("images/tako.ppm", "dark");
    assertEquals(600, l1.getWidth());
  }

  /**
   * Test Load Image - with invalid PPM file format.
   */
  @Test
  public void testLoadImageWrongFormat() {
    ImageController imgCtr = new ImageController();
    assertEquals(null,
            imgCtr.loadImage("images/wrong_format.ppm", "dark"));
  }

  /**
   * Tester that test GetCurrentCommand method.
   */
  @Test
  public void testGetCurrentCommand() throws IOException {
    ImageController ic = new ImageController();
    ic.newProject(800, 600);
    ic.readCommand("add-layer layer1");
    assertEquals(ic.getCurrentCommand(), "add-layer");
    ic.readCommand("set-filter layer1 blue-component");
    assertEquals(ic.getCurrentCommand(), "set-filter");


  }

  /**
   * Tester that test NewProject method with dimensions - check width.
   *
   * @throws IOException invalid width
   */
  @Test
  public void testNewProjectCheckWidth() throws IOException {
    ImageController imgCtr = new ImageController();
    imgCtr.readCommand("new-project 800 600");
    assertEquals(600, imgCtr.img.getWidth());
    assertNotEquals(800, imgCtr.img.getWidth());
  }

  /**
   * Tester that test NewProject method with dimensions - check height.
   *
   * @throws IOException invalid height
   */
  @Test
  public void testNewProjectCheckHeight() throws IOException {
    ImageController imgCtr = new ImageController();
    imgCtr.readCommand("new-project 800 600");
    assertEquals(800, imgCtr.img.getHeight());
  }


  @Test
  public void testGetProject() {
    ImageController ic = new ImageController();
    ic.newProject(800, 600);
    ic.getProject();
    assertEquals(600, ic.getProject().getWidth());
    assertEquals(800, ic.getProject().getHeight());
  }

  /**
   * Tester that test SaveProject method.
   */
  @Test
  public void testSaveProject() {
    ImageController ic = new ImageController();
    ic.newProject(800, 600);
    ic.saveProject("tako.ppm.collager");
    File file = new File("tako.ppm.collager");
    assertTrue(file.exists());
  }

  /**
   * Tester that test TestSaveProject method. this one is testing it is using the
   * correct format. MAY NEED TO BE RECHECKED.
   */
  @Test
  public void testTestSaveProject() {
    ImageController ic = new ImageController();
    ic.newProject(800, 600);
    ic.saveProject();
    File file = new File("tako.ppm.collager");
    assertTrue(file.exists());
  }

  /**
   * Tester that test AddLayer method.
   */
  @Test
  public void testAddLayer() {
    ImageController ic = new ImageController();
    ic.newProject(600, 600);
    ic.addLayer("layer2");
    assertNotNull(ic.getProject().getLayer("layer2"));

    /// NEED TO BE FIXED

  }

  /**
   * Tester that test AddImageToLayer method.
   */
  @Test
  public void testAddImageToLayer() {
    ImageController ic = new ImageController();
    ic.newProject(800, 600);
    ic.addLayer("layer1");
    ic.addImageToLayer("layer1", "images/tako.ppm", 0, 0);
    //*Remove later*/ //System.out.println(ic.getProject().getLayer("layer1").
    // getGrid()[5][20].getBlue());
    Pixel topLeft = ic.getProject().getLayer("layer1").getGrid()[0][0];
    assertEquals(173, topLeft.getRed());
    assertEquals(179, topLeft.getGreen());
    assertEquals(151, topLeft.getBlue());
    //assertEquals(173, topLeft.getAlpha());
    Pixel bottomRigth = ic.getProject().getLayer("layer1").getGrid()[799][599];
    assertEquals(bottomRigth.getRed(), 167);
    assertEquals(bottomRigth.getGreen(), 106);
    assertEquals(bottomRigth.getBlue(), 25);
    //assertEquals(bottomRigth.getAlpha(), 25);
    //tests whether the top left pixel has been correctly read in
  }

  /**
   * Tester that test SetFilter method.
   */
  @Test
  public void testSetFilter() {
    ImageController ic = new ImageController();
    ic.newProject(600, 800);
    ic.addLayer("layer1");
    ic.setFilter("layer1", "red-component");
    assertEquals("red-component", ic.getProject().getLayer("layer1").getFilter());
    assertNotEquals("recomponent", ic.getProject().getLayer("layer1").getFilter());
    ic.addLayer("layer2");
    ic.setFilter("layer2", "green-component");
    assertEquals("green-component", ic.getProject().getLayer("layer2").getFilter());
    assertNotEquals("grcomponent", ic.getProject().getLayer("layer2").getFilter());

  }

  /**
   * Tester that test SaveImage method.
   */
  @Test
  public void testSaveImage() {
    ImageController ic = new ImageController();
    ic.newProject(800, 800);
    ic.saveImage("tako.ppm"); /// check the individual pixal.
    File file = new File("tako.ppm");
    assertTrue(file.exists());
  }

  /**
   * Tester that test Quit method.
   */
  @Test
  public void testQuit() throws IOException {
    ImageController imgCtr = new ImageController();
    imgCtr.quit();
    assertEquals(null, imgCtr.img);
  }

  /**
   * Tester that test loadDarkenAndSaveImage.
   */
  @Test
  public void testLoadDarkenAndSaveImage() {
    ImageController ic = new ImageController();
    ic.newProject(800, 800);
    ic.addImageToLayer("background", "./images/tako.ppm", 0, 0);
    ic.setFilter("background", "darken");
    ic.saveImage("newTako.ppm");
    File file = new File("newTako.ppm");
    assertTrue(file.exists());
  }

  /**
   * Tester that test ReadCommand method.
   */
  @Test
  public void testReadCommand() throws IOException {
    ImageController ic = new ImageController();

    // command 1
    ic.readCommand("new-project 800 600");
    assertEquals(ic.getCurrentCommand(), "new-project");
    assertEquals(800, ic.getProject().getHeight());
    assertEquals(600, ic.getProject().getWidth());
    assertNotEquals(600, ic.getProject().getHeight());
    assertNotEquals(800, ic.getProject().getWidth());
    // command 2
    ic.readCommand("add-layer layerName");
    assertEquals(ic.getCurrentCommand(), "add-layer");
    assertEquals(ic.getProject().getNumLayers(), 2);
    assertNotEquals(ic.getCurrentCommand(), 0);

    //commdand 3
    ic.readCommand("add-image-to-layer layerName images/tako.ppm");
    assertEquals(ic.getCurrentCommand(), "add-image-to-layer");
    assertNotEquals(ic.getCurrentCommand(), "add layers to pic");

    //command 4
    ic.readCommand("set-filter layerName red-component");
    assertEquals(ic.getCurrentCommand(), "set-filter");
    assertNotEquals(ic.getCurrentCommand(), "red");

    //command 5
    ic.readCommand("save-image image.ppm");
    assertEquals(ic.getCurrentCommand(), "save-image");
    assertNotEquals(ic.getCurrentCommand(), "savePic");

    //command 6
    ic.readCommand("save-project");
    assertEquals(ic.getCurrentCommand(), "save-project");
    assertNotEquals(ic.getCurrentCommand(), "savProject");

    //command 7
    // its breaking and running out of input
    ic.readCommand("load-project image.Project");
    assertEquals(ic.getCurrentCommand(), "load-project");
    assertNotEquals(ic.getCurrentCommand(), "loadProject");

    //command 8
    ic.readCommand("quit");
    assertEquals(ic.getCurrentCommand(), "quit");
    assertNotEquals(ic.getCurrentCommand(), "Out");

    /// make sure it does not crase with invalied comanied
    ic.readCommand("Invalid-command");
    assertEquals(ic.getCurrentCommand(), "Invalid-command");
    assertNotEquals(ic.getCurrentCommand(), "Out");

  }
}