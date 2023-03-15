package Model;


import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
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
    assertEquals(image.getWidth(),600);
    assertNotEquals(image.getWidth(),700);


  }
  @Test
  public void testGetHeight() {
    Project image = new Project(600, 700);
    assertEquals(image.getHeight(),700);
    assertNotEquals(image.getHeight(),600);

  }
}
