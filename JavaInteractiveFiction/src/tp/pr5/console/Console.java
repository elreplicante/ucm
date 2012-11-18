/**
 * 
 */
package tp.pr5.console;

import java.util.List;

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
public class Console implements GameObserver, PlayerObserver, MapObserver {



	@Override
	public void roomEntered(Directions direction, RoomInfo targetRoom) {
		Constants.println(Constants.MESSAGE_CHANGE_ROOM + direction);
		playerHasExaminedRoom(targetRoom);
	}

	@Override
	public void playerHasExaminedRoom(RoomInfo room) {
		Constants.println(room.getDescription());
	}

	@Override
	public void playerLookedInventory(List<Item> inventory) {
		if (inventory.isEmpty()) {
			Constants.println(Constants.MESSAGE_POOR);
		} else {
			Constants.println(Constants.MESSAGE_LOOK);
			for (int i = 0; i < inventory.size(); i++) {
				Constants.println(inventory.get(i).getDescription());
			}
		}
	}

	@Override
	public void inventoryUpdate(List<Item> inventory) {
		//TODO crear un atributo inventory e igualarlo???
	}

	@Override
	public void playerUpdate(int newHealth, int newScore) {
		Constants.println("HEALTH = " + newHealth + ", SCORE =" + newScore);
	}

	@Override
	public void playerDead() {
		Constants.println(Constants.MESSAGE_DEAD);
	}

	@Override
	public void itemLooked(String description) {
		Constants.println(description);
	}

	@Override
	public void itemUsed(String itemName) {
		Constants.println(Constants.MESSAGE_CHANGES);
	}

	@Override
	public void itemEmpty(String itemName) {
		Constants.println("The " + itemName
				+ " is empty. It is deleted from the inventory.");
	}

	@Override
	public void gameStart(RoomInfo initialRoom, int playerPoints,
			int playerHealth) {
		Constants.print(initialRoom.getDescription());
		this.playerUpdate(playerHealth, playerPoints);
	}

	@Override
	public void gameError(String msg) {
		Constants.println(msg);
	}

	@Override
	public void gameHelp() {
		Constants.print(Constants.MESSAGE_HELP);
	}

	@Override
	public void gameOver(boolean win) {
		
		if (!win)
			playerDead();
		Constants.println(Constants.MESSAGE_FIN);
		System.exit(0);
	}

	@Override
	public void gameQuit() {
		System.exit(0);
	}

	protected static void prompt() {
		System.out.println(Constants.PROMPT);
	}
}
