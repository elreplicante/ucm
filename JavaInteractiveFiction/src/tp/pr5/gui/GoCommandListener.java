/**
 * 
 */
package tp.pr5.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import tp.pr5.Directions;

/**
 * @author repli
 *
 */
public class GoCommandListener implements ActionListener {

	
	private JComboBox directionsBox;
	private GameControllerGUI gameController;
	
	
	public GoCommandListener(JComboBox box, GameControllerGUI gameController) {
		this.directionsBox = box;
		this.gameController = gameController;
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override

	public void actionPerformed(ActionEvent arg0) {
		int index = directionsBox.getSelectedIndex();
		if (directionsBox.getItemAt(index).equals(Directions.UNKNOWN)) {
			JOptionPane.showMessageDialog(null,
					"What are you dreaming about?");
		} else {
			Directions direction = Directions.numberDirection(index);
			gameController.executeGoAction(direction);
			
			}
	}
}
