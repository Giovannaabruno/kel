package view;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


/**
 * Testers for ImageView class.
 */
public class ImageViewTest {
  Appendable out;

  /**
   * Test rendering message.
   */
  @Test
  public void testRenderMessage() throws IOException {
    out = new StringBuffer();
    CollagingView view = new CollagingCommandView(out);
    view.renderMessage("Testing Message!");
    assertEquals("Testing Message!", out.toString());
  }


}