package view;

import java.awt.event.ActionEvent;

import javax.swing.*;

/**
 * A class representing a panel with commands for processing an image.
 */
public class CommandPanel extends JPanel {

  /**
   * Constructor for the CommandPanel class.
   */
  public CommandPanel() {
    super();
    this.setBorder(BorderFactory.createTitledBorder("Commands:"));
    String[] options = {"None", "New Project", "Add Layer",
                        "Set Filter", "Add Image to Layer",
                        "Add Layer", "Set Filter", "Save Image",
                        "Save Project"};
    JComboBox<String> comboBox = new JComboBox<>();
    for (String option : options) {
      comboBox.addItem(option);
    }
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
      return (String) ((JComboBox<String>) event.getSource()).getSelectedItem();
    }
    throw new IllegalStateException("Something went wrong.");
  }

}