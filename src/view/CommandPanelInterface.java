package view;

import java.awt.event.ActionEvent;

public interface CommandPanelInterface {
  /**
   * Returns a string for the command selected by the user from the combo boxes.
   * @param event the event triggered by selecting an option from the combo boxes
   * @return a string version of the command chosen
   */
  String getComboBoxChoice(ActionEvent event);
}
