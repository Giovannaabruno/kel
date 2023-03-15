package View;

import java.io.IOException;

/**
 * visual representation of a model of Collaging.
 */
public class CollagingCommandView implements CollagingView {
  private final Appendable ap;

  /**
   * creates a view Object for a command line based Collaging project.
   */
  public CollagingCommandView() {
    this.ap = System.out;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    ap.append(message);
  }


}