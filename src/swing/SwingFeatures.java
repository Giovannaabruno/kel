package swing;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This example shows the different user interface elements in Java Swing.
 * Please use these examples as guidelines only to see how to use them. This
 * example has not been designed very well, it is only meant to illustrate code
 * snippets.
 *
 * Feel free to try out different modifications to see how the program changes
 */

/*
Notes:
Should the GUI have a Project variable that contains all the layers/images?
  -if not, where will these images be stored?
Should the image loaded at the start be the first image in the project? What is the image
doing otherwise?
Should there be a list of BufferedImages that represent a viewable version of each layer?
Java can only display BufferedImages (or other types), not PPM files.
The GUI should show the current layer- the currentLayerIndex helps with this
It should display all the layer names, with the current layer marked (maybe in bold)
  -again, currentLayerIndex can help with this
Maybe the GUI should show the project on the left, the current layer on the right, and the list
of layers below them all.
Like this:
    current project image       current layer image
     (ic.saveImage() )          ic.getProject().getLayers().get(currentLayerIndex).getPixelGrid()
                    layer1
                    layer2  <-    (shows what currentLayerIndex is)
                    layer3
How do you convert a Layer into a BufferedImage?
Part 7 example: whatever method is called, catch the exception and store it
try {
  ic.saveLayer("bad file", "bad layer");
} catch (Exception e) {
  String error = e.getSimpleClassName();
  //can print error to relevant text box in GUI, this is very open ended
}
 */

/**
 *
 */
public class SwingFeatures {

  /**
   *
   * @param args
   */
  public static void main(String[] args) {
    SwingFeaturesFrame.setDefaultLookAndFeelDecorated(false);
    SwingFeaturesFrame frame = new SwingFeaturesFrame();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    try {
      // Set cross-platform Java L&F (also called "Metal")
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

      //UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());

      //   UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      //    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      //    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
      //    {
      //       if ("Nimbus".equals(info.getName())) {
      //          UIManager.setLookAndFeel(info.getClassName());
      //         break;
      //    }
      // }
    } catch (UnsupportedLookAndFeelException e) {
      // handle exception
    } catch (ClassNotFoundException e) {
      // handle exception
    } catch (InstantiationException e) {
      // handle exception
    } catch (IllegalAccessException e) {
      // handle exception
    } catch (Exception e) {
    }

  }

}