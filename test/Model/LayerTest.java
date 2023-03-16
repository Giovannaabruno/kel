package Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


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
  public void getHeight() {
    Layer ll = new Layer(600,600, "image.tako.pmm");
    assertEquals(ll.getHeight(), 600);
    assertNotEquals(ll.getHeight(), 500);
  }

  /**
   * Tester that test getWidth method.
   */
  @Test
  public void getWidth() {
    Layer ll = new Layer(600,600, "image.tako.pmm");
    assertEquals(ll.getWidth(), 600);
    assertNotEquals(ll.getWidth(), 500);
  }

  /**
   * Tester that test getName method.
   */
  @Test
  public void getName() {
    Layer ll = new Layer(600,600, "image.tako.pmm");
    assertEquals(ll.getName(), "image.tako.pmm");
    assertNotEquals(ll.getName(), "mage.tako.pmm");

  }

  /**
   * Tester that test getGrid method.
   */
  @Test
  public void getGrid() {
    Layer ll = new Layer(600,600, "image.tako.pmm");
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
  public void getPixelAt() {
    Layer ll = new Layer(600,600, "image.tako.pmm");
    assertEquals(ll.getPixelAt(0, 0),
            new Pixel(255, 255, 255, 255));

    assertEquals(ll.getPixelAt(ll.getHeight() / 2, ll.getWidth() / 2),
            new Pixel(255, 255, 255, 255));

    assertEquals(ll.getPixelAt(ll.getHeight() - 1, ll.getWidth() - 1),
            new Pixel(255, 255, 255, 255));

    assertNotEquals(ll.getPixelAt(ll.getHeight() / 2, ll.getWidth() / 2),
            new Pixel(255,128, 255, 255));



  }
// case "red-component":
//         return redComponent();
//      case "green-component":
//              return greenComponent();
//      case "blue-component":
//              return blueComponent();
//      case "brighten-value":

  /**
   * Tester that test setFilter method.
   */
  @Test
  public void setFilter() {
    Layer ll = new Layer(600,600, "image.tako.pmm");
    ll.setFilter("red-component", 80);
    assertEquals(ll.getFilter(), "red-component");

    ll.setFilter("green-component", 80);
    assertEquals(ll.getFilter(), "green-component");
    ll.setFilter("blue-component", 80);
    assertNotEquals(ll.getFilter(), "blue");
  }

  /**
   * Tester that test SetFilter method.
   */
  @Test
  public void testSetFilter() {
    Layer ll = new Layer(600,600, "image.tako.pmm");
    ll.setFilter("red-component", 80);
    assertEquals(ll.getFilter(), "red-component");

    ll.setFilter("green-component", 80);
    assertEquals(ll.getFilter(), "green-component");

    ll.setFilter("blue-component", 80);
    assertNotEquals(ll.getFilter(), "blue");

  }

  /**
   * Tester that test GetFilter method.
   */
  @Test
  public void GetFilter() {
    Layer ll = new Layer(600,600, "image.tako.pmm");
    ll.setFilter("red-component", 80);
    assertEquals(ll.getFilter(), "red-component");

    ll.setFilter("green-component", 80);
    assertEquals(ll.getFilter(), "green-component");

    ll.setFilter("blue-component", 80);
    assertNotEquals(ll.getFilter(), "blue");


  }

  /**
   * Tester that test FilteredGrid method.
   */
  @Test
  public void getFilteredGrid() {
    Layer ll = new Layer(600,600, "image.tako.pmm");
    ll.setFilter("red-component");
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