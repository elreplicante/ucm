/**
 * 
 */
package tp.pr5.gui;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import tp.pr5.Constants;
import tp.pr5.Directions;
import tp.pr5.RoomInfo;
import tp.pr5.items.Item;
import tp.pr5.observers.GameObserver;
import tp.pr5.observers.MapObserver;
import tp.pr5.observers.PlayerObserver;

/**
 * @author repli
 *
 */
public class InfoPanel extends JPanel implements GameObserver, MapObserver,
	PlayerObserver {
	
	private JLabel infoText;
	
	public InfoPanel() {
		infoText = new JLabel();
		infoText.setOpaque(true);
		this.add(infoText);
		this.setVisible(true);
	}
	
    @Override
    public void playerLookedInventory(List<Item> inventory) {
    }

    @Override
    public void inventoryUpdate(List<Item> inventory) {
    	infoText.setText(Constants.MESSAGE_CHANGES);
    }

    @Override
    public void playerUpdate(int newHealth, int newScore) {
    	infoText.setText("HEALTH = " + newHealth + ", SCORE =" + newScore);
    }
    
    @Override
    public void playerDead() {
    	infoText.setText(Constants.MESSAGE_DEAD);
    }

    @Override
    public void itemLooked(String description) {
    }

    @Override
    public void itemUsed(String itemName) {
    	infoText.setText(Constants.MESSAGE_CHANGES);
    }

    @Override
    public void itemEmpty(String itemName) {
    	infoText.setText("The " + itemName
				+ " is empty. It is deleted from the inventory.");
    }

    @Override
    public void roomEntered(Directions direction, RoomInfo targetRoom) {
    	infoText.setText(Constants.MESSAGE_CHANGE_ROOM + " " + direction);
    }

    @Override
    public void playerHasExaminedRoom(RoomInfo r) {
    }

    @Override
    public void gameStart(RoomInfo initialRoom, int playerPoints,
	    int playerHealth) {
    	infoText.setText("Game starts " + "HEALTH = " + playerHealth + ", SCORE =" + playerPoints);
    }

    @Override
    public void gameError(String msg) {
    }

    @Override
    public void gameHelp() {
    }

    @Override
    public void gameOver(boolean win) {
    	if (!win)
			playerDead();
		Constants.println(Constants.MESSAGE_FIN);
    }

    @Override
    public void gameQuit() {
    }

}
