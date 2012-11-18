/**
 * 
 */
package tp.pr5.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import tp.pr5.Game;

/**
 * @author repli
 * 
 */
public class MenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for MenuBar
	 * 
	 * @param game
	 *            the execution context
	 */
	public MenuBar() {
		super();
		this.initialize();
	}

	/**
	 * Initializes the MenuBar. Adds the "Quit" action
	 */
	private void initialize() {
		JMenu menuFile = new JMenu("Menu");
		menuFile.setMnemonic(KeyEvent.VK_F);
		JMenuItem menuItem = new JMenuItem("Quit");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Game Over");
				System.exit(0);
			}
		});

		menuFile.add(menuItem);
		this.add(menuFile);
	}

}
