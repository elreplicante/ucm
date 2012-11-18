/**
 * 
 */
package tp.pr5.items;

import tp.pr5.Constants;
import tp.pr5.Player;
import tp.pr5.Room;

/**
 * @author Jose Carlos Gonzalez Sergio Revilla
 * 
 */
/**
 * @author repli
 *
 */
/**
 * @author repli
 * 
 */
public abstract class ExpirationItem extends Item {

	private int times;

	/**
	 * Constructor of Expiration Item.
	 * 
	 * @param id
	 *            Identifier
	 * @param description
	 *            Description of the Item.
	 * @param times
	 */
	public ExpirationItem(String id, String description, int times) {

		super(id, description);
		this.times = times;
	}

	/**
	 * Default constructor of an Expiration Item There are defined the ID and
	 * the Description of the Item. The number of times that the item can be
	 * used is defined as a constant 10
	 * 
	 * @param id
	 * @param description
	 */
	public ExpirationItem(String id, String description) {

		super(id, description);
		this.times = Constants.DEFAULT_EXPIRATION_LIVE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tp.pr2.Item#toString()
	 */
	@Override
	public String toString() {

		return super.toString() + "//" + this.times + Constants.LINE_SEPARATOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tp.pr3.items.Item#canBeUsed()
	 */
	public boolean canBeUsed() {
		return this.times > 0;
	}

	public void undoUse(Player who, Room where) {
		this.times += 1;
	}
	
	public boolean use(Player who, Room where) {
		this.times -= Constants.DEFAULT_TIMES;
		return true;
	}

	public int getTimes() {
		return this.times;
	}

}
