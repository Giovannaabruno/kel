package Controller;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import Model.Layer;
import Model.Pixel;
import Model.Project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
    Layer l = new Layer(600, 800, "layer1");
    ImageController ic = new ImageController();
    ic.loadImage("./images/tako.ppm", "layer1");

  }

  /**
   * Test Load Image - with Valid PPM file format - check Height.
   */
  @Test
  public void testLoadImageValidFormatHeight() {
    ImageController imgCtr = new ImageController();
    Layer l1 = imgCtr.loadImage("images/A4-sample2.ppm", "dark");
    assertEquals(500, l1.getHeight());
  }

  /**
   * Test Load Image - with Valid PPM file format - check Width.
   */
  @Test
  public void testLoadImageValidFormatWidth() {
    ImageController imgCtr = new ImageController();
    Layer l1 = imgCtr.loadImage("images/A4-sample2.ppm", "dark");
    assertEquals(400, l1.getWidth());
  }

  /**
   Test Load Image - with invalid PPM file format.
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
    ic.newProject(600, 800);
    ic.readCommand("add-layer layer1");
    assertEquals(ic.getCurrentCommand(), "add-layer");
    ic.readCommand("set-filter layer1 blue-component");
    assertEquals(ic.getCurrentCommand(), "set-filter");


  }

  /**
   * Tester that test NewProject method with dimensions - check width
   * @throws IOException
   */
  @Test
  public void testNewProjectCheckWidth() throws IOException {
    ImageController imgCtr = new ImageController();
    imgCtr.readCommand("new-project 800 600");
    assertEquals(800, imgCtr.img.getWidth());
  }

  /**
   *  Tester that test NewProject method with dimensions - check height.
   * @throws IOException
   */
  @Test
  public void testNewProjectCheckHeight() throws IOException {
    ImageController imgCtr = new ImageController();
    imgCtr.readCommand("new-project 800 600");
    assertEquals(600, imgCtr.img.getHeight());
  }


  @Test
  public void testGetProject() {
    ImageController ic = new ImageController();
    ic.newProject(600, 800);
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
    ic.newProject(600, 800);
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
    ic.newProject(600, 800);
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
    assertTrue(true);

  }

  /**
   * Tester that test AddImageToLayer method.
   */
  @Test
  public void testAddImageToLayer() {
    ImageController ic = new ImageController();
    ic.newProject(800, 800);
    ic.addLayer("layer1");
    ic.addImageToLayer("layer1","images/tako.ppm",0,0);
    Pixel topLeft = ic.getProject().getLayer("layer1").getGrid()[0][0];
    assertEquals(173, topLeft.getRed());
    assertEquals(179, topLeft.getGreen());
    assertEquals(151, topLeft.getBlue());
    assertEquals(173, topLeft.getAlpha());
    Pixel bottomRigth = ic.getProject().getLayer("layer1").getGrid()[799][599];
    assertEquals(bottomRigth.getRed(), 23);
    assertEquals(bottomRigth.getGreen(), 167);
    assertEquals(bottomRigth.getBlue(), 106);
    assertEquals(bottomRigth.getAlpha(), 25);
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
    ic.setFilter("layer1","red-component");
    assertEquals("red-component", ic.getProject().getLayer("layer1").getFilter());
    assertNotEquals("recomponent", ic.getProject().getLayer("layer1").getFilter());
    ic.addLayer("layer2");
    ic.setFilter("layer2","green-component");
    assertEquals("green-component", ic.getProject().getLayer("layer2").getFilter());
    assertNotEquals("grcomponent", ic.getProject().getLayer("layer2").getFilter());

  }

  /**
   * Tester that test SaveImage method.
   *
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

  @Test
  public void loadDarkenAndSaveImage() {
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
  public void testReadCommand() throws IOException{
    ImageController ic = new ImageController();

    // command 1
    ic.readCommand("new-project 600 800");
    assertEquals(ic.getCurrentCommand(), "new-project");
    assertEquals(600, ic.getProject().getHeight());
    assertEquals(800, ic.getProject().getWidth());
    // command 2
//    ic.readCommand("add-layer tako");
//    assertEquals(ic.getCurrentCommand(), "add-layer");
//    //commdand 3
//    ic.readCommand("add-image-to-layer tako");
//    assertEquals(ic.getCurrentCommand(), "add-image-to-layer");
//    //command 4
//    ic.readCommand("set-filter tako");
//    assertEquals(ic.getCurrentCommand(), "set-filter");
//    //command 5
//    ic.readCommand("save-image tako");
//    assertEquals(ic.getCurrentCommand(), "save-image");
//
//    //command 5
//    ic.readCommand("save-project");
//    assertEquals(ic.getCurrentCommand(), "save-project");
//
//    //command 5
//    ic.readCommand("load-project tako");
//    assertEquals(ic.getCurrentCommand(), "load-project");
//
//    //command 5
//    ic.readCommand("quit");
//    assertEquals(ic.getCurrentCommand(), "quit");



  }
}