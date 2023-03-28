package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;

import controller.ImageController;
import model.Layer;
import model.Pixel;
import model.Project;

/**
 * Implements the CollagingView interface. Constructs a GUI using the Swing framework.
 */
public class JFrameView extends JFrame implements CollagingView {
  private final JButton loadButton;
  private final JButton saveButton;

  private JList layerList;
  private DefaultListModel<String> listModel;

  private JLabel projectNameLabel;
  private JButton newProjectButton;


  private JPanel commandPanel;

  private ImagePanel imagePanel;

  private Project currentProject;

  /**
   * Constructor for the JFrameView class.
   */
  public JFrameView() {
    super("Collaging Images");

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

    JFrame imageWindow = new JFrame();
    imageWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));


    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new FlowLayout());


    this.loadButton = new JButton("Load Image");
    bottomPanel.add(this.loadButton);

    this.saveButton = new JButton("Save Image");
    bottomPanel.add(this.saveButton);

    this.commandPanel = new CommandPanel();
    bottomPanel.add(this.commandPanel);

    ImageController ic = new ImageController();

    this.newProjectButton = new JButton("New Project");
    topPanel.add(newProjectButton);

    this.projectNameLabel = new JLabel("No Project");
    topPanel.add(this.projectNameLabel);
    currentProject = ic.newProject(800,600);
    this.newProjectButton.addActionListener(new ActionListener() {
      ///part5

      /**
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
       String projectName = (String) JOptionPane.showInputDialog(
           topPanel,
           "Enter project name.",
               "New Project Dialog",
               JOptionPane.PLAIN_MESSAGE,
               null,
               null,
               ""
       );
       projectNameLabel.setText("Project Name: " + projectName);
       currentProject = ic.newProject(800,600);
      }
    });

    Layer layer = ic.loadImage("images/tako.ppm", "tako");
    BufferedImage img = ppmImageToBufferedImage(layer);
    this.imagePanel = new ImagePanel(img);
    imageWindow.add(imagePanel);
    imageWindow.setVisible(true);
    imageWindow.setTitle(layer.getName());
    if(currentProject != null) {
      currentProject.addLayer(new Layer(800, 600, "newLayer"));
      currentProject.addLayer(new Layer(800, 600, "layer 2"));
      this.listModel = new DefaultListModel<>();
      for (int l = 0; l < currentProject.getNumberLayers(); l++) {
        listModel.addElement(currentProject.getLayer(l).getName());
      }

      this.layerList = new JList<>(listModel);
      layerList.setSelectedIndex(1);
      layerList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
      layerList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
      layerList.setVisibleRowCount(-1); // max number of possible rows

      JScrollPane listScroller = new JScrollPane(layerList);
      bottomPanel.add(listScroller);
    }

    this.add(topPanel);
    this.add(bottomPanel);
    this.setPreferredSize(new Dimension(600,800));
    this.pack();
    this.setVisible(true);
  }



  /**
   * Method ppmImageToBufferedImage, it will conver a ppm image to a
   * buffer image in order to draw it on the screen.
   * @param layer
   * @return find Image
   */
  public BufferedImage ppmImageToBufferedImage(Layer layer) {
    java.awt.image.BufferedImage finalImage =
            new java.awt.image.BufferedImage(layer.getWidth(), layer.getHeight(), BufferedImage.TYPE_INT_ARGB
            );

    for(int y = 0; y < layer.getHeight(); y++) {
      for(int x = 0; x < layer.getWidth(); x++) {
        Pixel p = layer.getPixelAt(y, x);
        char a = (char) p.getAlpha();
        char r = (char) p.getRed();
        char g = (char) p.getGreen();
        char b = (char) p.getBlue();

        int argb = a << 24;
        argb |= r << 16;
        argb |= g << 8;
        argb |= b;
        finalImage.setRGB(x, y, argb);
      }
    }
    return finalImage;
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
