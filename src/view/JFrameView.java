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

  private final ImagePanel imagePanel;

  private final CommandPanel commandPanel;
  private final JButton loadButton;
  /**
   * Constructor for the JFrameView class.
   */
  public JFrameView() {
    super("Collaging Images");

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new FlowLayout());

    this.imagePanel = new ImagePanel();
    topPanel.add(this.imagePanel);

    this.loadButton = new JButton("Load Image");
    bottomPanel.add(this.loadButton);

    this.commandPanel = new CommandPanel();
    bottomPanel.add(this.commandPanel);

    this.add(topPanel);
    this.add(bottomPanel);

    this.pack();
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