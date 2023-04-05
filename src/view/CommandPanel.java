package view;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import controller.ImageController;

/**
 * A class representing a panel with commands for processing an image.
 */
public class CommandPanel extends JPanel {

  private ImageController ic;


  protected void executeCommand(String command) {
    switch (command) {
      case "None":
        break;
      case "New Project":
        ic.newProject(800,600);
        break;
      case "Add Layer":
        String layerName = JOptionPane.showInputDialog(this, "Enter layer name.");
        ic.addLayer(layerName);
        break;
      case "Set Filter":
        layerName = JOptionPane.showInputDialog(this, "Enter layer name");
        String filterOption = JOptionPane.showInputDialog(this, "Enter filter option");
        if(filterOption.equals("darkenBlending") || filterOption.equals("inversionBlending") ||
                filterOption.equals("brightenBlending")) {
            String otherName = JOptionPane.showInputDialog(this, "Enter other layer");
            ic.setFilter(layerName, filterOption, otherName);
        }
        else {
          ic.setFilter(layerName, filterOption, null);
        }

        break;
      case "Add Image to Layer":
        layerName = JOptionPane.showInputDialog(this, "Enter layer name");
        String imageName = JOptionPane.showInputDialog(this, "Enter image name");
        int xPos = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter x Position"));
        int yPos = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter y Position"));
        ic.addImageToLayer(layerName,imageName, xPos, yPos);
        break;
      case "Save Image":
        filterOption = JOptionPane.showInputDialog(this, "Enter filter name");
        ic.saveImage(filterOption);
        break;
      case "Save Project":
        String fileName = JOptionPane.showInputDialog(this, "Enter file name");
        ic.saveProject(fileName);
        break;
      default:
        System.out.println("imcorrect command");
    }
  }

  /**
   * Constructor for the CommandPanel class.
   */
  public CommandPanel(ImageController ic) {
    super();
    this.ic = ic;
    this.setBorder(BorderFactory.createTitledBorder("Commands:"));
    String[] options = {"None", "New Project", "Add Layer",
            "Set Filter", "Add Image to Layer", "Save Image",
            "Save Project"};
    JComboBox<String> comboBox = new JComboBox<>();
    for (String option : options) {
      comboBox.addItem(option);
    }
    comboBox.addItemListener(new ItemChangeListener());
    this.add(comboBox);
  }

  /**
   * Returns a string for the command selected by the user from the combo boxes.
   *
   * @param event the event triggered by selecting an option from the combo boxes
   * @return a string version of the command chosen
   */
  public String getComboBoxChoice(ActionEvent event) {
    if (event.getSource() instanceof JComboBox) {
      String choice = (String) ((JComboBox<String>) event.getSource()).getSelectedItem();
      System.out.println(choice);
    }
    throw new IllegalStateException("Something went wrong.");
  }

  class ItemChangeListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        String command = (String) e.getItem();
        executeCommand(command);
      }
    }
  }

}