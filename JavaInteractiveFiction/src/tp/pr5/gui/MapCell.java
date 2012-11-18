/**
 * 
 */
package tp.pr5.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

import tp.pr5.Room;
import tp.pr5.RoomInfo;

/**
 * @author repli
 * 
 */
public class MapCell extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RoomInfo roomInfo;
	private JTextArea textArea;
	private boolean visited;
	private MapPanel mapPanel;

	/**
	 * Full MapCell constructor
	 * 
	 * @param roomInfo
	 *            The room that will be contained into the cell
	 * @param textArea
	 *            a reference to the bottom text area to print the room
	 *            description
	 */
	public MapCell(JTextArea textArea, MapPanel mapPanel) {

		super();
		setOpaque(true);
		setBorderPainted(true);
		this.roomInfo = null;
		this.textArea = textArea;
		this.mapPanel = mapPanel;
		this.visited = false;
		this.initialize();
	}
	
	public void setRoomInfo(RoomInfo roomInfo) {
		this.roomInfo = roomInfo;
	}

	/**
	 * MapCell default constructor
	 */
	public MapCell() {
		super();
		setOpaque(true);
		setBorderPainted(true);
	}



	/**
	 * Sets a cell(room) visited, so description is shown in the bottom text
	 * area and makes cell clickable in the future
	 */
	public void setVisited() {
		if (roomInfo != null) {
			this.setText(roomInfo.getName());
			textArea.setText(roomInfo.getDescription());

		}
		visited = true;
	}

	public void setNotVisited() {
		this.setText(null);
		visited = false;
	}

	public boolean isVisited() {
		return visited;
	}

	/**
	 * Initializes the MapCell. Adds the ActionListener
	 */
	private void initialize() {

		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (visited)
					mapPanel.playerHasExaminedRoom(roomInfo);
			}
		});
	}
	
	public void enter(RoomInfo targetRoom) {
		this.roomInfo = targetRoom;
		this.setText(targetRoom.getName());
		this.mapPanel.playerHasExaminedRoom(targetRoom);
		this.setBackground(Color.GREEN);
		this.setVisited();
	}
	
	public void left() {
		this.setBackground(null);
	}

}
