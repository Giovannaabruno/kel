package view;

import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;

/**
 * Implements the CollagingView interface. Constructs a GUI using the Swing framework.
 */
public class JFrameView extends JFrame implements CollagingView {
  private final JButton loadButton;
  private final JButton saveButton;

  /**
   * Constructor for the JFrameView class.
   */
  public JFrameView() {
    super("Collaging Images");

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel topPanel = new JPanel();
    topPanel.setLayout(new FlowLayout());

    this.loadButton = new JButton("Load");
    topPanel.add(this.loadButton);
    this.saveButton = new JButton("Save");
    topPanel.add(this.saveButton);

    this.add(topPanel);
    this.setSize(800,600);
    this.setVisible(true);
  }

  /**
   * Renders a given message to the GUI Application.
   *
   * @param message the message to be printed
   * @throws IOException if the transmission of the message to the data output fails
   */
  @Override
  public void renderMessage(String message) throws IOException {

  }

}