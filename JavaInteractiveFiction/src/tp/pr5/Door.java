/**
 * @author Grupo 3
 * 
 *         A door links two rooms A and B in one direction. If it is defined as
 *         Door(A,NORTH,B) it means that B is at NORTH of A. Doors can be
 *         bidirectional. I.e, B is at NORTH of A => A is at SOUTH of B Doors
 *         are open or close. By default, a door is created closed.
 * 
 */

package tp.pr5;

public class Door {

    /**
     * The source room
     */

    private Room source;

    /**
     * The target room
     */
    private Room target;

    /**
     * The direction where the source room connects to the target room
     */
    private Directions direction;

    /**
     * The opposite direction where the source room connects to the target room
     */
    private Directions opDirection;

    /**
     * True if you can cross the door from both sides
     */
    private boolean bidirectional;

    /**
     * If false, you need a key to open and cross the door
     */
    private boolean isOpen;

    /**
     * Constructor of Door By default a door is closed
     * 
     * @param source
     *            Source room (in the example A).
     * @param direction
     *            Represents how is placed the target room w.r.t the source
     *            room.
     * @param target
     *            Target room (in the example B).
     * @param bidirectional
     *            - B is at NORTH of A => A is at SOUTH of B.
     * 
     */

    public Door(Room source, Directions direction, Room target,
	    boolean bidirectional) {

	this.source = source;
	this.direction = direction;
	this.opDirection = direction.opposite();
	this.target = target;
	this.bidirectional = bidirectional;
	this.isOpen = false;
    }

    /**
     * Constructor of Door with an extra parameter to specify the initial status
     * of the Door (open or closed).
     * 
     * * @param source Source room (in the example A).
     * 
     * @param direction
     *            Represents how is placed the target room w.r.t the source
     *            room.
     * @param target
     *            Target room (in the example B).
     * @param bidirectional
     *            - B is at NORTH of A => A is at SOUTH of B.
     * @param isOpen
     *            True if it is open.
     * 
     */

    public Door(Room source, Directions direction, Room target,
	    boolean bidirectional, boolean isOpen) {

	this.source = source;
	this.direction = direction;
	this.opDirection = direction.opposite();
	this.target = target;
	this.bidirectional = bidirectional;
	this.isOpen = isOpen;
    }

    /**
     * 
     * Checks if the door is in a room.
     * 
     * @param room
     *            The room to check
     * @return Returns true if the door belongs to the input Room room . An
     *         unidirectional door belongs only to the source room. However, if
     *         the door is bidirectional then it checks that room == source ||
     *         room == target.
     */

    public boolean isInRoom(Room room) {

	boolean isInRoom = false;
	if (bidirectional) {
	    if (room.equals(source) || room.equals(target)) {
		isInRoom = true;
	    }
	} else if (room.equals(source)) {
	    isInRoom = true;
	}

	return isInRoom;
    }

    /**
     * Checks if the door is in a room in the given direction.
     * 
     * @param room
     *            The room to check
     * @param where
     *            Direction used
     * @return Returns true if the door belongs to the input Room room . In this
     *         method, an unidirectional door belongs to the source and the
     *         target room although it is not crossable in that direction. This
     *         way we can show different messages if there is no door or if
     *         there is a door but it is not crossable in this direction.
     */

    public boolean isInRoom(Room room, Directions where) {

	boolean isInRoom;
	if (room.equals(source) && where.equals(direction)
		|| room.equals(target) && where.equals(opDirection)) {
	    isInRoom = true;
	} else {
	    isInRoom = false;
	}

	return isInRoom;
    }

    /**
     * Opens the door.
     * 
     */

    public void open() {

	isOpen = true;
    }

    /**
     * Closes a door.
     * 
     */

    public void close() {

	isOpen = false;
    }

    /**
     * Returns the room of the other side from whereAmI if it is possible (even
     * if the door is closed).
     * 
     * @param whereAmI
     *            Room.
     * @return When the door isInRoom(whereAmI) and the player could go through
     *         it (it's bidirectional or being unidirectional whereAmI is the
     *         source room) it returns the room at the other side (null
     *         otherwise). Note that although the door could be closed, the room
     *         at the other side is returned.
     */

    public Room nextRoom(final Room whereAmI) {

	Room nextRoom = null;

	if (source.equals(whereAmI)) {
	    nextRoom = target;
	} else if ((target.equals(whereAmI)) && (bidirectional)) {
	    nextRoom = source;
	}

	return nextRoom;
    }

    public Room nextRoomHulk(final Room whereAmI) {

	Room nextRoom = null;

	if (source.equals(whereAmI)) {
	    nextRoom = target;
	} else if ((target.equals(whereAmI))) {
	    nextRoom = source;
	}

	return nextRoom;
    }

    /**
     * Returns true if this door links roomA and roomB and we can go from roomA
     * to roomB.
     * 
     * @param roomA
     *            the source room
     * @param roomB
     *            the target room
     * @return true if there is a crossable door from roomA to roomB and the
     *         door is open
     */

    public boolean connect(final Room roomA, final Room roomB) {

	boolean connected = false;
	if (isOpen) {
	    if (bidirectional) {
		if (roomA == source && roomB == target || roomB == source
			&& roomA == target) {
		    connected = true;
		} else {
		    connected = false;
		}
	    } else {
		if (roomA == source && roomB == target) {
		    connected = true;
		} else {
		    connected = false;
		}
	    }
	}

	return connected;
    }

    /**
     * Checks if the door is open.
     * 
     * @return true if the door is open
     */

    public boolean isOpen() {

	return isOpen;
    }

}
