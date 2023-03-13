package Model;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

public class ProjectTest {

  /**
   * testAddLayer, add s layer effect to the orginal pixal.
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
   * testGetLayer, getting the background for the image.
   */
  @Test
  public void testGetLayer() {
    Project image = new Project(600, 600);

    Layer l = image.getLayer(0);
    assertEquals(l.getName(), "background");
  }

  /**
   * testNotGetLayer, tester when the image has a null layer.
   */
  @Test
  public void testNotGetLayer() {
    Project image = new Project(600, 600);
    assertThrows(IllegalArgumentException.class, () -> {
      Layer l = image.getLayer(-1);
    });
  }
}