package tp.pr5.observers;

import tp.pr5.Directions;
import tp.pr5.RoomInfo;

/**
 * Interface of the observers that want to be notified about the events related
 * to the rooms. More precisely, the map notifies when the player enter a room
 * or when the player asked for the room description.
 * 
 * @author repli
 * 
 */
public interface MapObserver {

    /**
     * Notifies that the player entered a room coming from a given direction.
     * 
     * @param direction
     *            The direction the player comes
     * @param targetRoom
     *            Room where the player enters
     */
    void roomEntered(Directions direction, RoomInfo targetRoom);

    /**
     * Notifies that the player examined a room.
     * 
     * @param r
     *            Room requested
     */
    void playerHasExaminedRoom(RoomInfo r);

}
