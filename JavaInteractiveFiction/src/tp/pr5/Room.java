package tp.pr5;

import java.util.ArrayList;
import java.util.Iterator;

import tp.pr5.items.Item;

/**
 * It represents a room in the adventure game. Every room has a textual
 * description about itself. This description is displayed when the player
 * enters the room. Some rooms are exit rooms. When the player arrives at this
 * room, the game finishes. Rooms can contain Items. In this case, the name of
 * these items are displayed in the room description.
 * 
 * @author Grupo03 Sergio Revilla Jose Carlos Gonzalez
 * 
 */

public class Room implements RoomInfo {

    /**
     * True if the room is an exit room
     */
    private boolean isExit;

    /**
     * The room's description.
     */
    private String description;

    /**
     * The room's name.
     */
    private String roomName;

    /**
     * An ArrayList of items that represents the items in the room
     */
    private ArrayList<Item> roomItems;

    /**
     * Room's full constructor.
     * 
     * @param isExit
     *            true if the room is an exit room
     * @param roomName
     *            the room's name
     * @param description
     *            the room's description
     */
    public Room(boolean isExit, String description, String roomName) {

	this.isExit = isExit;
	this.roomName = roomName;
	this.description = description;
	this.roomItems = new ArrayList<Item>();
    }

    public ArrayList<Item> getRoomItems() {
	return roomItems;
    }

    /**
     * Room's basic constructor.
     * 
     * @param isExit
     *            true if the room is an exit room
     * @param roomDescription
     *            the room's name
     */
    public Room(boolean isExit, String roomDescription) {

	this.isExit = isExit;
	this.description = roomDescription;
	this.roomItems = new ArrayList<Item>();
    }

    /**
     * A room created with a set of items. Inputs an array of items and puts it
     * into the roomItems ArrayList
     * 
     * @param exit
     *            Is it an exit room?
     * @param description
     *            Room description
     * @param items
     *            an array with the items contained in the room
     */
    public Room(boolean exit, String description, Item[] items) {
	this.isExit = exit;
	this.description = description;
	this.roomItems = new ArrayList<Item>();

	// Checking for duplicates on array creation
	for (int i = 0; i < items.length; i++) {
	    if (!this.existsItem(items[i].getId()))
		this.roomItems.add(items[i]);
	}
    }

    /**
     * Gets the room name
     * 
     * @return the room name to get
     */
    public String getName() {

	return roomName;
    }

    /**
     * Returns the room description and a list with the ids of the items and
     * their description that belongs to this room. <<description room>> <<item
     * descriptions>>
     * 
     * @return description The room description
     */

    public String getDescription() {

	String roomDescription = description;
	Iterator<Item> itemIterator = roomItems.iterator();

	if (roomItems.isEmpty()) {
	    roomDescription = roomDescription.concat(Constants.LINE_SEPARATOR
		    + Constants.MESSAGE_EMPTY_ROOM);
	} else {
	    roomDescription = roomDescription.concat(Constants.LINE_SEPARATOR
		    + Constants.MESSAGE_ROOM);
	    while (itemIterator.hasNext()) {
		Item item = itemIterator.next();
		roomDescription = roomDescription.concat(item.toString());
	    }
	}
	return roomDescription;
    }

    /**
     * Pick an item from the room and add it to the player's inventory.
     * 
     * @param who
     *            the player
     * @param itemIdToPick
     *            the identifier of the item
     * @return true if the action was successfully performed
     */
    public boolean pickItem(Player who, String itemIdToPick) {

	boolean itemFound = false;
	Iterator<Item> itemIterator = this.roomItems.iterator();
	Item item = null;

	if (existsItem(itemIdToPick)) {
	    while (itemIterator.hasNext() && !itemFound) {
		item = itemIterator.next();
		if (item.getId().equalsIgnoreCase(itemIdToPick)) {
		    itemFound = true;
		}
	    }

	    if (who.addItem(item)) {
		roomItems.remove(item);
	    }

	}
	return itemFound;
    }

    /**
     * Return true if the Item <> exists.
     * 
     * @param itemIdToCheck
     *            Name of the item.
     * @return true if the room has an item with that name
     */
    public boolean existsItem(String itemIdToCheck) {

	boolean existsItem = false;
	Iterator<Item> itemIterator = roomItems.iterator();
	Item item = null;
	while (itemIterator.hasNext()) {
	    item = itemIterator.next();
	    if (item.getId().equalsIgnoreCase(itemIdToCheck)) {
		existsItem = true;
	    }
	}
	return existsItem;
    }

    /**
     * Is it an exit room?.
     * 
     * @return if it is an exit room
     */
    public boolean isExit() {

	return isExit;
    }

    /**
     * Add a given item. The name (id) of the item should be unique in the room.
     * 
     * @param itemToAdd
     *            Item to be added
     * @return true if the action was completed
     */
    public boolean addItem(Item itemToAdd) {

	if (existsItem(itemToAdd.getId())) {
	    return false;
	}
	return roomItems.add(itemToAdd);
    }

}