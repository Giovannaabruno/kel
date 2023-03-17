package model;

import org.junit.Test;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;


/**
 * Tester for Layer class.
 */
public class LayerTest {
  // int height, int width, String name
  // Pixel[][] grid, String name



  /**
   * Tester that test getHeight method.
   */
  @Test
  public void testGetHeight() {
    Layer ll = new Layer(800,600, "image/tako.pmm");
    assertEquals(ll.getHeight(), 800);
    assertNotEquals(ll.getHeight(), 500);
  }

  /**
   * Tester that test getWidth method.
   */
  @Test
  public void testGetWidth() {
    Layer ll = new Layer(800,600, "image/tako.pmm");
    assertEquals(ll.getWidth(), 600);
    assertNotEquals(ll.getWidth(), 500);
  }

  /**
   * Tester that test getName method.
   */
  @Test
  public void testGetName() {
    Layer ll = new Layer(800,600, "image/tako.pmm");
    assertEquals(ll.getName(), "image/tako.pmm");
    assertNotEquals(ll.getName(), "mage.tako.pmm");

  }

  /**
   * Tester that test getGrid method.
   */
  @Test
  public void testGetGrid() {
    Layer ll = new Layer(800,600, "image/tako.pmm");
    Pixel[][] grid = ll.getGrid();
    assertEquals(grid[0][0],
            new Pixel(255,255, 255, 255));
    assertEquals(grid[ll.getHeight() / 2][ll.getWidth() / 2],
            new Pixel(255,255, 255, 255));
    assertEquals(grid[ll.getHeight() - 1][ll.getWidth() - 1],
            new Pixel(255,255, 255, 255));
    assertNotEquals(grid[ll.getHeight() / 2][ll.getWidth() / 2],
            new Pixel(255,128, 255, 255));
  }

  /**
   * Tester that test getPixelAt method.
   */
  @Test
  public void testGetPixelAt() {
    Layer ll = new Layer(800,600, "image/tako.pmm");
    assertEquals(ll.getPixelAt(0, 0),
            new Pixel(255, 255, 255, 255));

    assertEquals(ll.getPixelAt(ll.getHeight() / 2, ll.getWidth() / 2),
            new Pixel(255, 255, 255, 255));

    assertEquals(ll.getPixelAt(ll.getHeight() - 1, ll.getWidth() - 1),
            new Pixel(255, 255, 255, 255));

    assertNotEquals(ll.getPixelAt(ll.getHeight() / 2, ll.getWidth() / 2),
            new Pixel(255,128, 255, 255));



  }


  /**
   * Tester that test setFilter method.
   */
  @Test
  public void setFilter() {
    Layer ll = new Layer(800,600, "image.tako.pmm");
    ll.setFilter("red-component", 80);
    assertEquals(ll.getFilter(), "red-component");
    assertNotEquals(ll.getFilter(), "red");

    ll.setFilter("green-component", 80);
    assertEquals(ll.getFilter(), "green-component");
    assertNotEquals(ll.getFilter(), "green");
    ll.setFilter("blue-component", 80);
    assertNotEquals(ll.getFilter(), "blue");
  }

  /**
   * Tester that test SetFilter method.
   */
  @Test
  public void testSetFilter() {
    Layer ll = new Layer(800,600, "image/tako.pmm");
    ll.setFilter("red-component", 80);
    assertEquals(ll.getFilter(), "red-component");
    assertNotEquals(ll.getFilter(), "red");

    ll.setFilter("green-component", 80);
    assertEquals(ll.getFilter(), "green-component");
    assertNotEquals(ll.getFilter(), "green");

    ll.setFilter("blue-component", 80);
    assertNotEquals(ll.getFilter(), "blue");

  }

  /**
   * Tester that test GetFilter method.
   */
  @Test
  public void testGetFilter() {
    Layer ll = new Layer(800,600, "image/tako.pmm");
    ll.setFilter("red-component", 80);
    assertEquals(ll.getFilter(), "red-component");
    assertNotEquals(ll.getFilter(), "red");

    ll.setFilter("green-component", 80);
    assertEquals(ll.getFilter(), "green-component");
    assertNotEquals(ll.getFilter(), "green");

    ll.setFilter("blue-component", 80);
    assertNotEquals(ll.getFilter(), "blue");


  }

  /**
   * Tester that test FilteredGrid method.
   */
  @Test
  public void testGetFilteredGrid() {
    Layer ll = new Layer(800,600, "image/tako.pmm");
    int amount = 30;
    ll.setFilter("red-component", amount);
    Pixel[][] grid = ll.getFilteredGrid();
    assertEquals(grid[0][0],
            new Pixel(255,0, 0, 255));
    assertEquals(grid[ll.getHeight() / 2][ll.getWidth() / 2],
            new Pixel(255,0, 0, 255));
    assertEquals(grid[ll.getHeight() - 1][ll.getWidth() - 1],
            new Pixel(255,0, 0, 255));
    assertNotEquals(grid[ll.getHeight() / 2][ll.getWidth() / 2],
            new Pixel(855, 0, 0, 255));

  }
}