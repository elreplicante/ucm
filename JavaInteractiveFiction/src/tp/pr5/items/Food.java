package tp.pr5.items;

import tp.pr5.Constants;
import tp.pr5.Player;
import tp.pr5.Room;

/**
 * @author repli
 * 
 */
public class Food extends ExpirationItem {

    private int health;

    /**
     * Constructor of a kind of food that is consumed in just one use.
     * 
     * @param name
     *            Name of the item
     * @param description
     *            Description of the item
     * @param health
     *            represents how good is this food (it may be a negative value).
     */
    public Food(String name, String description, int health) {

	super(name, description, Constants.DEFAULT_TIMES);
	this.health = health;
    }

    /**
     * Constructor of food.
     * 
     * @param name
     *            Name of the item
     * @param description
     *            Description of the item
     * @param health
     *            represents how good is this food (it may be a negative value).
     * @param times
     *            indicates the number of times player may use the item.
     */
    public Food(String name, String description, int health, int times) {

	super(name, description, times);
	this.health = health;
    }

    /**
     * Returns true if the player can use this object in this room. The food can
     * be used at any place.
     * 
     * @param who
     *            Player that uses the item.
     * @param where
     *            Location of the player when using it.
     * @return true if the item was successfully used.
     */
    @Override
    public boolean use(Player who, Room where) {
	boolean used = false;
	if (canBeUsed()) {
	    super.use(who, where);
	    who.addHealth(this.health);
	    who.requestPlayerUpdate();
	    used = true;
	}
	return used;
    }

}