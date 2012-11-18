package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import tp.pr5.Directions;
import tp.pr5.RoomInfo;
import tp.pr5.observers.MapObserver;

public class MapPanel extends JPanel implements MapObserver {

	private MapCell[][] mapCell;
	private JTextArea bottomText;
	private MapCell currentCell;
	private int row;
	private int col;
	private boolean undoing = false;

	/**
	 * Getter for the MapCell
	 * 
	 * @return the mapCell
	 */
	public MapCell[][] getMapCell() {
		if (mapCell != null) {
			return mapCell;
		} else
			return null;
	}

	/**
	 * Getter for the bottom text box
	 * 
	 * @return the bottomText
	 */
	public JTextArea getBottomText() {
		return bottomText;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * MapPanel constructor
	 * 
	 * @param theMap
	 *            the map, in order to load rooms into map cells
	 */
	public MapPanel() {
		super(new BorderLayout());
		this.row = 5;
		this.col = 5;
		this.initialize();
	}

	/**
	 * Initializes the Map Panel
	 */
	private void initialize() {

		JPanel roomContainer = new JPanel(new GridLayout(11, 11));
		roomContainer.setBorder(BorderFactory.createTitledBorder("Map"));
		roomContainer.setPreferredSize(new Dimension(1000, 300));

		this.bottomText = new JTextArea();
		bottomText.setEditable(false);
		int row = 5;
		int col = 5;

		this.mapCell = new MapCell[11][11];

		this.mapCell[row][col] = new MapCell(bottomText, this);
		this.mapCell[row][col].setBackground(Color.GREEN);

		for (int i = 0; i < mapCell.length; i++) {
			for (int j = 0; j < mapCell.length; j++) {
				mapCell[i][j] = new MapCell(bottomText, this);
				roomContainer.add(mapCell[i][j]);
			}
		}

		this.currentCell = mapCell[5][5];

		JScrollPane scrollPane = new JScrollPane(bottomText);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Room"));
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(500, 150));

		JPanel centralPanel = new JPanel(new BorderLayout());
		centralPanel.add(roomContainer, BorderLayout.CENTER);
		centralPanel.add(scrollPane, BorderLayout.SOUTH);

		centralPanel.setVisible(true);

		this.add(centralPanel);
	}

	private void move(Directions direction) {
		switch (direction) {
		case NORTH:
			row--;
			break;
		case SOUTH:
			row++;
			break;
		case EAST:
			col++;
			break;
		case WEST:
			col--;
			break;
		case UNKNOWN:
			break;
		}

		if ((row <= 0 || row >= 11) && (col <= 0 || col >= 11))
			System.exit(1);
		else
			this.currentCell = mapCell[row][col];

	}

	public void setUndoing(boolean undoing) {
		this.undoing = undoing;
	}

	@Override
	public void roomEntered(Directions direction, final RoomInfo targetRoom) {
		if (undoing) {
			currentCell.setNotVisited();
		}
		currentCell.left();
		if (direction != null) {
			move(direction);
		}
		currentCell.enter(targetRoom);
		currentCell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(((MapCell) e.getSource()).isVisited())
					playerHasExaminedRoom(targetRoom);
			}
		});
	}

	@Override
	public void playerHasExaminedRoom(RoomInfo r) {
		bottomText.setText(r.getDescription());
	}

}
