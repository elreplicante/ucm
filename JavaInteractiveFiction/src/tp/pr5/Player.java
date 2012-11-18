/**
 * A character in a video game or role playing game who is controlled by a
 * player. It contains the health and the points of this player. Additionally,
 * it stores the items that the player picks from the rooms that the player
 * traverses
 * 
 * @author Grupo 3
 * 
 */

package tp.pr5;

import java.util.Iterator;
import java.util.Vector;

import tp.pr5.items.Item;
import tp.pr5.observers.Observable;
import tp.pr5.observers.PlayerObserver;

public class Player extends Observable<PlayerObserver> {

    /**
     * Player's health
     */
    private int health;

    /**
     * Player's score
     */
    private int score;

    /**
     * A vector of items.
     * 
     * Contains the items carried by the player
     */
    private Vector<Item> inventory;

    private Vector<Item> lastItems;

    /**
     * Constructor of player. Initially his attributes are: 100 for the health
     * and 0 for the points. The inventory is empty.
     */

    public Player() {
	this.health = Constants.INITIAL_LIVE;
	this.score = Constants.INITIAL_SCORE;
	this.inventory = new Vector<Item>();
	lastItems = new Vector<Item>();

    }

    public Vector<Item> getLastItems() {
	return lastItems;
    }

    public void removeLastItem() {
	lastItems.removeElementAt(lastItems.size() - 1);
    }

    public Vector<Item> getInventory() {
	return inventory;
    }

    /**
     * Add an item in the inventory
     * 
     * @param itemToAdd
     *            The name of the item must be unique in the player inventory.
     * @return true if the item was added and false when the player already had
     *         an item with the same name.
     * 
     */

    public boolean addItem(Item itemToAdd) {

	boolean itemAdded;
	String itemId = itemToAdd.getId();

	if (getItem(itemId) == null) {
	    inventory.add(itemToAdd);
	    this.requestInventoryUpdate();
	    itemAdded = true;
	} else {
	    itemAdded = false;
	}
	return itemAdded;
    }

    /**
     * Returns the item from the inventory according to the item name It returns
     * the Item. If it does not exist it returns null.
     * 
     * @param itemId
     *            Item name
     * @return Item with that name or null if the player does not have an item
     *         with this name that item.
     */
    public Item getItem(String itemId) {

	Iterator<Item> itemIterator = inventory.iterator();
	Item itemToGet = null;
	while (itemIterator.hasNext()) {
	    itemToGet = itemIterator.next();
	    if (itemToGet.getId().equalsIgnoreCase(itemId))
		return itemToGet;
	}
	return null;
    }

    /**
     * Delete a given item from the inventory.
     * 
     * @param itemId
     *            Name of the item
     * @return true if the player had that item and it was removed from the
     *         inventory.
     */
    public boolean removeItem(String itemId) {

	boolean itemRemoved = false;
	if (getItem(itemId) != null) {
	    inventory.remove(getItem(itemId));
	    this.requestInventoryUpdate();
	    itemRemoved = true;
	}

	return itemRemoved;
    }

    /**
     * Checks if the player is dead.
     * 
     * @return true if the health <=0
     */
    public boolean dead() {

	return health <= 0;

    }

    /**
     * Returns the player scoring
     * 
     * @return Current points.
     */
    public int getPoints() {

	return score;
    }

    /**
     * Sum the value of points.
     * 
     * @param points
     *            Points to be added. It could be a negative number and
     *            therefore the player may have negative points.
     */
    public void addPoints(int points) {

	score += points;
    }

    /**
     * Returns the player health The health of the player. It is always a number
     * greater or equal to zero.
     * 
     * @return The health of the player. It is always a number greater or equal
     *         to zero.
     */

    public int getHealth() {

	return health;
    }

    /**
     * The health is updated.
     * 
     * @param healthToAdd
     *            Quantity of health that is gain. It could be negative, but the
     *            method guarantees that the player has no negative health.
     */
    public void addHealth(int healthToAdd) {

	health += healthToAdd;

	if (health < 0) {
	    health = 0;
	}
    }

    /**
     * Shows the items carried by the player.
     * 
     * @return A string with the names of the items contained in the player
     *         inventory
     */
    public String showItems() {

	String showItems = "";
	if (inventory.isEmpty()) {
	    showItems = Constants.MESSAGE_POOR;
	} else {
	    for (int inventoryIterator = 0; inventoryIterator < inventory
		    .size(); inventoryIterator++) {
		showItems = showItems.concat(inventory.elementAt(
			inventoryIterator).toString());
	    }
	}
	return showItems;
    }

    /**
     * Requests the player to inform the observers that the player attributes
     * have changed.
     */
    public void requestPlayerUpdate() {
	for (PlayerObserver observersIterator : observers) {
	    observersIterator.playerUpdate(health, score);
	}
    }

    /**
     * Requests the player to inform the observers about the inventory
     * description.
     */
    public void requestLookInventory() {
	for (PlayerObserver observersIterator : observers) {
	    observersIterator.playerLookedInventory(inventory);
	}
    }

    /**
     * Requests the player to inform the observers that the inventory has
     * changed.
     */
    public void requestInventoryUpdate() {
	for (PlayerObserver observersIterator : observers) {
	    observersIterator.inventoryUpdate(inventory);
	}
    }

    /**
     * Requests the player to inform the observers about an item description.
     * 
     * @param itemName
     *            Name of the requested item description
     */
    public void lookItem(String itemName) {
	for (PlayerObserver observersIterator : observers) {
	    observersIterator.itemLooked(itemName);
	}
    }

    /**
     * Requests the player to inform the observers about an item has been used.
     * 
     * @param itemName
     *            Used item
     */
    public void itemUsed(String itemName) {
	for (PlayerObserver observersIterator : observers) {
	    observersIterator.itemUsed(itemName);
	}
    }

    /**
     * Requests the player to inform the observers that an item is empty.
     * 
     * @param itemName
     *            Empty item
     */
    public void itemEmpty(String itemName) {
	for (PlayerObserver observersIterator : observers) {
	    observersIterator.itemEmpty(itemName);
	}
    }

}
