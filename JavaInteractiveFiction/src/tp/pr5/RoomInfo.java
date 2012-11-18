package tp.pr5;

/**
 * 
 * Interface that narrows the Room interface. With this interface only the
 * methods that do not modify the room are exposed to the observers.
 * 
 * @author repli
 * 
 */
public interface RoomInfo {

    /**
     * Returns the room name.
     * 
     * @return Room name
     */
    String getName();

    /**
     * Returns the room description and a list with the ids of the items and
     * their description that belongs to this room. <<description room>> <<item
     * descriptions>>.
     * 
     * @return The room description
     */
    String getDescription();

    /**
     * Is it an exit room?.
     * 
     * @return true if it is an exit room
     */
    boolean isExit();

}
