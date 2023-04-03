package model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tester class that represents ImageTest.
 */
public class ImageTest {

  /**
   * Tester for getLayer method.
   */
  @Test
  public void testGetLayer() {
    Project image = new Project(800, 600);
    Layer layer = image.getLayer(0);
    assertEquals(layer.getName(),"background");
    image.addLayer(new Layer(800,600, "layer1"));
    Layer layer1 = image.getLayer(1);
    assertEquals(layer1.getName(),"layer1");
    assertNotEquals(layer1.getName(),"layer2");


  }

  /**
   * Tester for getNumberLayers method.
   */
  @Test
  public void testGetNumberLayers() {
    Project image = new Project(800, 600);
    image.addLayer(new Layer(800,600, "layer1"));
    image.addLayer(new Layer(800,600, "layer2"));
    image.addLayer(new Layer(800,600, "layer3"));
    int numberLayers = image.getNumberLayers();
    assertEquals(numberLayers, 4); // background count as a layer
    assertNotEquals(numberLayers, 50);

  }
}