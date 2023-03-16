package Model;


import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class ProjectTest {

  /**
   * TestAddLayer, add s layer effect to the orginal pixal.
   */
  @Test
  public void testAddLayer() {
    Project image = new Project(600, 600);
    Layer l1 = new Layer(new Pixel[600][600], "layer1");
    image.addLayer(l1);
    l1 = image.getLayer(1);
    assertEquals(l1.getName(), "layer1");

    Layer l2 = new Layer(new Pixel[600][600], "layer2");
    image.addLayer(l2);
    l2 = image.getLayer(2);
    assertEquals(l2.getName(), "layer2");
  }

  /**
   * TestGetLayer, getting the background for the image.
   */
  @Test
  public void testGetLayer() {
    Project image = new Project(600, 600);

    Layer l = image.getLayer(0);
    assertEquals(l.getName(), "background");
  }

  /**
   * TestNotGetLayer, tester when the image has a null layer.
   */
  @Test
  public void testNotGetLayer() {
    Project image = new Project(600, 600);
    assertThrows(IllegalArgumentException.class, () -> {
      Layer l = image.getLayer(-1);
    });
  }

  @Test
  public void testGetWidth() {
    Project image = new Project(600, 700);
    assertEquals(image.getWidth(), 600);
    assertNotEquals(image.getWidth(), 700);


  }

  @Test
  public void testGetHeight() {
    Project image = new Project(600, 700);
    assertEquals(image.getHeight(), 700);
    assertNotEquals(image.getHeight(), 600);

  }

  @Test
  public void testDarken() {
    Project image = new Project(600, 800);
    Layer l = image.getLayer(0);
    l.setPixelAt(0, 0, new Pixel(255, 255, 255));
    Pixel oldPixel = l.getPixelAt(0, 0).clone();
    l.setFilter("darken", 20);
    Pixel[][] grid = l.getFilteredGrid();
    Pixel newPixel = grid[0][0];
    assertTrue(newPixel.getRed() < oldPixel.getRed());
    assertTrue(newPixel.getGreen() < oldPixel.getGreen());
    assertTrue(newPixel.getBlue() < oldPixel.getBlue());

  }

  @Test
  public void testDarkenValue() {
    Project image = new Project(600, 800);
    Layer l = image.getLayer(0);
    l.setPixelAt(0, 0, new Pixel(0, 255, 0));
    Pixel oldPixel = l.getPixelAt(0, 0).clone();
    l.setFilter("darken-value", 20);
    Pixel[][] grid = l.getFilteredGrid();
    Pixel newPixel = grid[0][0];
    assertTrue(newPixel.getGreen() < oldPixel.getGreen());
  }

  @Test
  public void testDarkenLuma() {
    Project image = new Project(600, 800);
    Layer l = image.getLayer(0);
    l.setPixelAt(0, 0, new Pixel(255, 255, 255));
    Pixel oldPixel = l.getPixelAt(0, 0).clone();
    l.setFilter("darken-luma", 20);
    Pixel[][] grid = l.getFilteredGrid();
    Pixel newPixel = grid[0][0];
    assertTrue(newPixel.getRed() < oldPixel.getRed());
    assertTrue(newPixel.getGreen() < oldPixel.getGreen());
    assertTrue(newPixel.getBlue() < oldPixel.getBlue());
  }

  @Test
  public void testBrightening() {
    Project image = new Project(600, 800);
    Layer l = image.getLayer(0);
    l.setPixelAt(0, 0, new Pixel(0, 0, 0));
    Pixel oldPixel = l.getPixelAt(0, 0).clone();
    l.setFilter("brighten", 20);
    Pixel[][] grid = l.getFilteredGrid();
    Pixel newPixel = grid[0][0];
    assertTrue(newPixel.getRed() > oldPixel.getRed());
    assertTrue(newPixel.getGreen() > oldPixel.getGreen());
    assertTrue(newPixel.getBlue() > oldPixel.getBlue());
  }
  @Test
  public void testBrighteningValue() {
    Project image = new Project(600, 800);
    Layer l = image.getLayer(0);
    l.setPixelAt(0, 0, new Pixel(255, 0, 255));
    Pixel oldPixel = l.getPixelAt(0, 0).clone();
    l.setFilter("brighten-value", 20);
    Pixel[][] grid = l.getFilteredGrid();
    Pixel newPixel = grid[0][0];
    assertTrue(newPixel.getGreen() > oldPixel.getGreen());
  }

  @Test
  public void testBrighteningLuma() {
    Project image = new Project(600, 800);
    Layer l = image.getLayer(0);
    l.setPixelAt(0, 0, new Pixel(0, 0, 0));
    Pixel oldPixel = l.getPixelAt(0, 0).clone();
    l.setFilter("brighten-luma", 20);
    Pixel[][] grid = l.getFilteredGrid();
    Pixel newPixel = grid[0][0];
    assertTrue(newPixel.getRed() > oldPixel.getRed());
    assertTrue(newPixel.getGreen() > oldPixel.getGreen());
    assertTrue(newPixel.getBlue() > oldPixel.getBlue());
  }
  @Test
  public void testRedComponent() {
    Project image = new Project(600, 800);
    Layer l = image.getLayer(0);
    l.setPixelAt(0,0, new Pixel(255, 255, 255));
    Pixel oldPixel = l.getPixelAt(0, 0).clone();
    l.setFilter("red-component");
    Pixel[][] grid = l.getFilteredGrid();
    assertEquals(grid[0][0].getRed(), oldPixel.getRed());
    assertEquals(grid[0][0].getGreen(), 0);
    assertEquals(grid[0][0].getBlue(), 0);
  }

  @Test
  public void testgreenComponent(){
    Project image = new Project(600, 800);
    Layer l = image.getLayer(0);
    l.setPixelAt(0,0, new Pixel(255, 255, 255));
    Pixel oldPixel = l.getPixelAt(0, 0).clone();
    l.setFilter("green-component");
    Pixel[][] grid = l.getFilteredGrid();
    assertEquals(grid[0][0].getRed(), 0);
    assertEquals(grid[0][0].getGreen(), oldPixel.getGreen());
    assertEquals(grid[0][0].getBlue(), 0);

  }

  @Test
  public void testblueComponent(){
    Project image = new Project(600, 800);
    Layer l = image.getLayer(0);
    l.setPixelAt(0,0, new Pixel(255, 255, 255));
    Pixel oldPixel = l.getPixelAt(0, 0).clone();
    l.setFilter("blue-component");
    Pixel[][] grid = l.getFilteredGrid();
    assertEquals(grid[0][0].getRed(), 0);
    assertEquals(grid[0][0].getGreen(), 0);
    assertEquals(grid[0][0].getBlue(), oldPixel.getBlue());


  }

}