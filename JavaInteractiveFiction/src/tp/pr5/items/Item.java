package tp.pr5.items;

import tp.pr5.Player;
import tp.pr5.Room;

/**
 * @author Grupo 3
 * 
 *         Every object that the player may pick belongs to that abstract class.
 *         Every item as a name (or id), which should be unique (though this
 *         class does not ensure that; it is one of the game/map tasks), and a
 *         description. This is basically an abstract definition of the generic
 *         item types you would find in a Standard RPG;
 * 
 */
public abstract class Item {

	private String id;
	private String description;

	/**
	 * The item is built The id is unique
	 * 
	 * @param id
	 *            name (or id) of the item being built.
	 * @param description
	 *            Description.
	 */
	protected Item(String id, String description) {

		this.id = id;
		this.description = description;
	}

	/**
	 * Returns the item name (or ID)
	 * 
	 * @return The id.
	 */
	public String getId() {

		return this.id;
	}

	/**
	 * Method that returns a string with information about an Item. The pattern
	 * is: --item[<>]=<>
	 * 
	 * @return The string with the item information
	 */
	@Override
	public String toString() {
		return "--item[" + this.id + "]=" + this.description;
	}

	/**
	 * Method called when using the item. Derived classes must implement the
	 * method.
	 * 
	 * @param who
	 *            Player that uses the item.
	 * @param where
	 *            Location of the player when using it.
	 * @return true if the item was successfully used.
	 * 
	 */

	public abstract boolean use(Player who, Room where);

	/**
	 * Depending on the type of the item, it could be used forever (a key) or
	 * just a limited number of times (food and valuable). This method returns
	 * true when the item can be used at lest once more time.
	 * 
	 * @return true if the item can be used. This does not guarantee that the
	 *         invocation to use method returns true, because player could try
	 *         to use it on a wrong location, etc.
	 */

	public abstract boolean canBeUsed();

	public String getDescription() {
		return description;
	}

}
