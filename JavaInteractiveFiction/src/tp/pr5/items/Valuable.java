package tp.pr5.items;

import tp.pr5.Constants;
import tp.pr5.Player;
import tp.pr5.Room;

/**
 * A valuable Item is anything that changes the score of the player when it is
 * used This objects can be used just once.
 * 
 * @author Grupo03
 * 
 */

public class Valuable extends ExpirationItem {

	private int score;

	/**
	 * Default constructor of a Valuable Item. The time takes the DEFAULT VALUE
	 * indicated in Constants
	 * 
	 * @param id
	 *            Identifier.
	 * @param description
	 *            Description of the item.
	 * @param score
	 *            The amount of score that it changes (it may be negative).
	 */
	public Valuable(String id, String description, int score) {
		super(id, description, Constants.DEFAULT_TIMES);
		this.score = score;
	}

	public String toString() {
		return super.toString() + Constants.LINE_SEPARATOR;
	}

	/**
	 * The points are added to the player.
	 * 
	 * @param who
	 *            Player that uses the item.
	 * @param where
	 *            Location of the player when using it.
	 * @return true if the action could take place.
	 */

	public boolean use(Player who, Room where) {

		boolean used = false;

		if (canBeUsed()) {
			super.use(who, where);
			who.addPoints(score);
			who.requestPlayerUpdate();
			used = true;
		}
		return used;
	}

}
