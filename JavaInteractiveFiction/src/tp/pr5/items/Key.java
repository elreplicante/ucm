package tp.pr5.items;

import tp.pr5.Constants;
import tp.pr5.Door;
import tp.pr5.Player;
import tp.pr5.Room;

/**
 * @author Grupo3
 * 
 */
public class Key extends PersistentItem {

	private Door door;

	/**
	 * Default constructor of key.
	 */
	public Key(String id, String description, Door door) {

		super(id, description);
		this.door = door;
	}

	/**
	 * Toggle the state of the door (open-closed / closed-open). In order for
	 * the key to be able to perform the action, the player should stay on the
	 * room the door belongs to. Checks (and opens the door) if this key can be
	 * used to go from the input parameter where to go to another room thought
	 * the door that it opens. It returns true if the following situation holds:
	 * <ol>
	 * <li>The input parameter where is not null, and</li>
	 * <li>The door that appears in this object is not null, and</li>
	 * <li>From the room where we can access the door of this key</li>
	 * </ol>
	 * 
	 * @param who
	 *            Player that uses the key.
	 * @param where
	 *            Room where the player stays.
	 * @return true if the action was performed (the door was open or close
	 *         accordingly to it previous state).
	 */
	public boolean use(Player who, Room where) {

		boolean used = false;

		if (where == null || this.door == null || !this.door.isInRoom(where)) {
			used = false;
			System.out.println(Constants.MESSAGE_KEY_ERROR);
		} else {
			if (this.door.isOpen()) {
				this.door.close();
			} else {
				this.door.open();
			}
			used = true;
		}

		return used;
	}

	@Override
	public boolean canBeUsed() {
		return true;
	}

}
