package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import tp.pr5.Constants;
import tp.pr5.Directions;
import tp.pr5.RoomInfo;
import tp.pr5.observers.GameObserver;

/**
 * @author repli
 * 
 */
public class MainWindow implements GameObserver {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private GameControllerGUI gameController;
	private PlayerPanel playerPanel;
	private MapPanel mapPanel;
	private JFrame mainWindow;

	public MainWindow(GameControllerGUI gameController) {
		this.gameController = gameController;
		mainWindow = new JFrame();
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainWindow.setVisible(true);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initialize();
	}

	/**
	 * MainWindow constructor.
	 * 
	 * @param string
	 *            The window name
	 * @param theGame
	 *            The execution context
	 */
	public MainWindow(String string) {

		JFrame mainWindow = new JFrame(string);
		mainWindow.setVisible(true);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initialize();
	}

	/**
	 * Initializes the Main Window
	 */
	private void initialize() {

		JPanel mainContainer = new JPanel(new BorderLayout());
		JPanel buttons = new JPanel(new GridLayout(4, 2, 5, 5));
		mapPanel = new MapPanel();

		final JComboBox directionsBox = new JComboBox();
		directionsBox.setModel(new DefaultComboBoxModel(Directions.values()));
		directionsBox.setToolTipText("The directions in which you can move");

		/* PLAYER PANEL */
		JPanel playerInfo = new JPanel();
		playerInfo.setBorder(BorderFactory.createTitledBorder("Player Info"));
		playerInfo
				.setToolTipText("Here's the player info: Score, Health and the items that actually has in the inventory");

		playerPanel = new PlayerPanel(mapPanel);
		playerPanel.playerUpdate(100, 0);
		playerInfo.add(playerPanel);

		this.gameController.registerMapObserver(mapPanel);
		this.gameController.registerPlayerObserver(playerPanel);
		this.gameController.registerGameObserver(this);

		InfoPanel infoPanel = new InfoPanel();

		this.gameController.registerMapObserver(infoPanel);
		this.gameController.registerPlayerObserver(infoPanel);
		this.gameController.registerGameObserver(infoPanel);

		/* BOTON GO */
		JButton goButton = new JButton();
		initGoButton(goButton, directionsBox, mapPanel, playerPanel);

		/* MENU */
		MenuBar menu = new MenuBar();
		mainContainer.add(menu);
		mainWindow.setJMenuBar(menu);

		/* CAJA DE TEXTO DE PICK */
		final JTextArea itemTextBox = new JTextArea();
		initItemBox(itemTextBox);

		/* BOTON PICK */
		JButton pickButton = new JButton();
		initPickButton(pickButton, playerPanel, mapPanel, itemTextBox);

		/* BOTON DROP */
		JButton dropButton = new JButton();
		initDropButton(dropButton, mapPanel, playerPanel);

		/* BOTON QUIT */
		JButton quitButton = new JButton();
		initQuitButton(quitButton);

		/* BOTON USE */
		JButton useButton = new JButton();
		initUseButton(useButton, playerPanel);

		/* BOTON UNDO */

		JButton undoButton = new JButton();
		initUndoButton(undoButton, playerPanel, mapPanel);

		/* A�ADIR BOTONES AL GRIDLAYOUT */
		buttons.add(goButton);
		buttons.add(directionsBox);
		buttons.add(pickButton);
		buttons.add(itemTextBox);
		buttons.add(dropButton);
		buttons.add(useButton);
		buttons.add(quitButton);
		buttons.add(undoButton);
		buttons.setPreferredSize(new Dimension(200, 100));

		/* PANEL DE BOTONES */
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
		buttonPanel.add(buttons);

		/* BLOQUE SUPERIOR - LOS BOTONES Y EL PLAYER PANEL */
		JSplitPane northPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				buttonPanel, playerInfo);
		northPanel.setPreferredSize(new Dimension(100, 135));

		mainContainer.add(northPanel, BorderLayout.NORTH);
		mainContainer.add(mapPanel, BorderLayout.CENTER);
		mainContainer.add(infoPanel, BorderLayout.SOUTH);

		mainWindow.add(mainContainer);
		mainWindow.pack();

	}

	/**
	 * Initializes the GO button
	 * 
	 * @param goButton
	 *            the button
	 * @param directionsBox
	 *            the DIRECTIONS box
	 * @param mapPanel
	 *            the mapPanel
	 * @param playerPanel
	 *            the Player Panel
	 */
	private void initGoButton(JButton goButton, final JComboBox directionsBox,
			final MapPanel mapPanel, final PlayerPanel playerPanel) {

		goButton.setText("GO");
		goButton.setToolTipText("Moves the player in the given direction");
		goButton.addActionListener(new GoCommandListener(directionsBox, gameController));
	}

	/**
	 * Initializes the DROP button
	 * 
	 * @param dropButton
	 *            the DROP button
	 * @param playerPanel
	 *            the Player Panel
	 * @param mapPanel
	 *            the Map Panel
	 */
	private void initDropButton(JButton dropButton, final MapPanel mapPanel,
			final PlayerPanel playerPanel) {

		dropButton.setText("DROP");
		dropButton.setToolTipText("Drops the selected item from the inventory");
		dropButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gameController.executeDropAction(playerPanel.getSelectedItem());
			}
		});

	}

	/**
	 * Initializes the USE button
	 * 
	 * @param useButton
	 *            the USE button
	 * @param playerPanel
	 *            the Player Panel
	 */
	private void initUseButton(JButton useButton, final PlayerPanel playerPanel) {
		useButton.setText("USE");
		useButton.setToolTipText("Tries to use the selected item");
		useButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameController.executeUseAction(playerPanel.getSelectedItem());

			}
		});
	}

	/**
	 * Initializes the PICK button
	 * 
	 * @param pickButton
	 *            the PICK button
	 * @param playerPanel
	 *            the Player Panel
	 * @param mapPanel
	 *            the Map Panel
	 * @param itemString
	 *            the string written on the upper text box
	 */
	private void initPickButton(JButton pickButton,
			final PlayerPanel playerPanel, final MapPanel mapPanel,
			final JTextArea itemString) {

		pickButton.setText("PICK");
		pickButton.setToolTipText("Pick an item from the room");
		pickButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gameController.executePickAction(itemString.getText());

			}
		});
	}

	/**
	 * Initializes the upper text box
	 * 
	 * @param itemTextBox
	 *            the text box
	 */
	private void initItemBox(JTextArea itemTextBox) {
		itemTextBox.setOpaque(true);
		itemTextBox.setEditable(true);
		itemTextBox.setBackground(Color.WHITE);
		itemTextBox.setLineWrap(true);
		itemTextBox.setRows(1);
	}

	/**
	 * Initializes the QUIT button
	 * 
	 * @param quitButton
	 *            the QUIT button
	 */
	private void initQuitButton(JButton quitButton) {
		quitButton.setText("QUIT");
		quitButton.setToolTipText("Quits the actual game");
		quitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int confirmation = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to exit?", "EXIT",
						JOptionPane.OK_CANCEL_OPTION);
				if (JOptionPane.OK_OPTION == confirmation) {
					System.exit(0);
				}

			}
		});
	}
	
	private void initUndoButton(JButton undoButton, PlayerPanel playerPanel, final MapPanel mapPanel) {
		undoButton.setText("UNDO");
		undoButton.setToolTipText("Undo the last recent action");
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mapPanel.setUndoing(true);
				//gameController.executeUndoAction();
				mapPanel.setUndoing(false);
			}
		});
	}

	@Override
	public void gameStart(RoomInfo initialRoom, int playerPoints,
			int playerHealth) {
		mapPanel.roomEntered(null, initialRoom);
		mapPanel.playerHasExaminedRoom(initialRoom);
		playerPanel.playerUpdate(playerHealth, playerPoints);
	}

	@Override
	public void gameError(String msg) {
		JOptionPane.showMessageDialog(null, msg);

	}

	@Override
	public void gameHelp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameOver(boolean win) {
		String msg;
		if (win) {
			msg = Constants.MESSAGE_SALIDA;
		} else {
			msg = Constants.MESSAGE_FIN;
		}

		JOptionPane.showMessageDialog(null, msg);
		System.exit(0);
	}

	@Override
	public void gameQuit() {
		int confirm = JOptionPane.showConfirmDialog
				(null, "Do you want to exit?",
						"EXIT", JOptionPane.YES_NO_OPTION);
		
		if (confirm == JOptionPane.YES_OPTION)
			gameOver(true);

	}

}
