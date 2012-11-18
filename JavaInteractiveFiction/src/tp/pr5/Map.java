package tp.pr5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import tp.pr5.observers.MapObserver;
import tp.pr5.observers.Observable;

/**
 * Implements a map of rooms. From one room, following one direction leads only
 * to another unique room. Actually, the map stores the doors that connect the
 * rooms and it provides methods to navigate between adjacent rooms.
 * 
 * @author Grupo 3
 * 
 */

public class Map extends Observable<MapObserver> {

    /**
     * An ArrayList containing the doors in the map.
     */
    private ArrayList<Door> mapDoors;

    /**
     * The current room where player is located.
     */
    private Room currentRoom;

    private Vector<Room> previouslyRooms;

    /**
     * Default constructor of MAp. As usual, all attributes of a map are
     * initialized. In particular, the collection of doors.
     */
    public Map(Room initRoom) {
	this.mapDoors = new ArrayList<Door>();
	this.currentRoom = initRoom;
	previouslyRooms = new Vector<Room>();
    }

    /**
     * Map constructor without parameters.
     */
    public Map() {
	this.mapDoors = new ArrayList<Door>();
	this.currentRoom = null;
	previouslyRooms = new Vector<Room>();
    }

    public void setPreviouslyRoom(Room room) {
	previouslyRooms.add(room);
    }

    public Room getPreviouslyRoom() {
	return previouslyRooms.lastElement();
    }

    public void removeLastPreviouslyRoom() {
	previouslyRooms.removeElementAt(previouslyRooms.size() - 1);
    }

    /**
     * Returns a reference to the room where the player is currently located.
     * <b>This method is employed only by the unit tests</b>
     * 
     * @return A reference to the current room
     */

    public Room getCurrentRoom() {
	return currentRoom;
    }

    /**
     * Sets the current room
     * 
     * @param room
     *            the currentRoom to set
     */
    public void setCurrentRoom(Room room, Directions direction) {
	currentRoom = room;
	for (MapObserver observersIterator : observers) {
	    observersIterator.roomEntered(direction, room);
	}
    }

    /**
     * Create a new door between the rooms given as parameters, adds it to the
     * map and returns it.
     * 
     * @param sourceRoom
     *            The start-side of the door.
     * @param direction
     *            Position of the door in the source room.
     * @param targetRoom
     *            The end-side of the door.
     * @return New door just created. Users may then set its properties
     *         (open-closed). It returns null if there was problems during the
     *         creation.
     */
    public Door addDoor(Room sourceRoom, Directions direction, Room targetRoom) {

	Door newDoor = null;
	if (sourceRoom != null && targetRoom != null
		&& !direction.equals(Directions.UNKNOWN)) {
	    newDoor = new Door(sourceRoom, direction, targetRoom, false);
	    mapDoors.add(newDoor);
	}
	return newDoor;
    }

    /**
     * Adds a created door to the map. This method is not mandatory, but you may
     * want to create it to use it from the map loader.
     * 
     * @param door
     *            Door to be added.
     */
    public void addDoor(Door door) {

	if (!mapDoors.contains(door)) {
	    mapDoors.add(door);
	}
    }

    /**
     * Similar to addDoor method but creating a door that may be traversed in
     * both directions.
     * 
     * @param sourceRoom
     *            The start-side of the door.
     * @param direction
     *            Position of the door in the source room.
     * @param targetRoom
     *            The end-side of the door.
     * @return New door just created. Users may then set its properties
     *         (open-closed). It returns null if there was problems during the
     *         creation.
     */
    public Door addBidirectionalDoor(Room sourceRoom, Directions direction,
	    Room targetRoom) {

	Door newDoor = null;
	if (sourceRoom != null && targetRoom != null
		&& direction != Directions.UNKNOWN) {
	    newDoor = new Door(sourceRoom, direction, targetRoom, true);
	    mapDoors.add(newDoor);
	}
	return newDoor;
    }

    /**
     * Returns the door that is in a Direction of a Room. Let us note that may
     * be this door can not be used (if it is closed), but we don't care.
     * 
     * @param currentRoom
     *            The room where the player stays
     * @param directionChecked
     *            The direction to be checked
     * @return Door The door in the room at the given direction (or null if
     *         there is no door).
     */
    public Door getDoor(Room currentRoom, Directions directionChecked) {

	Iterator<Door> doorIterator = mapDoors.iterator();
	boolean doorFound = false;
	Door door = null;

	while (doorIterator.hasNext() && !doorFound) {
	    door = doorIterator.next();
	    if (door.isInRoom(currentRoom, directionChecked)) {
		doorFound = true;
	    }
	}

	if (doorFound)
	    return door;
	else
	    return null;
    }

    /**
     * Requests the player wants to examine a room.
     */
    public void playerExamineRoom() {
	for (MapObserver observersIterator : observers) {
	    observersIterator.playerHasExaminedRoom(currentRoom);
	}
    }

}
