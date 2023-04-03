package view;

import org.junit.Test;

import java.io.IOException;

import model.Project;

import static org.testng.AssertJUnit.assertEquals;


/**
 * Testers for CollagingCommandView class.
 */
public class CollagingCommandViewTest {

  /**
   * Tester renderMessage. NEEDS TO BE FINISHED
   */
  @Test
  public void renderMessage() throws IOException {
    CollagingCommandView commandView = new CollagingCommandView();
    commandView.renderMessage("Get Collage");
   // assertEquals(s, "Get Collage");

  }
}