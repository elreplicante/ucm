package tp.pr5.observers;

import tp.pr5.RoomInfo;

/**
 * Interface of the observers that want to be notified about the events related
 * to the game. More precisely, the game notifies when the game starts and
 * finishes, when the players requests help and quit and when an error occurs (a
 * command cannot be executed)
 * 
 * @author repli
 * 
 */
public interface GameObserver {

    /**
     * Notifies that the game starts. It provides the initial state of the game.
     * 
     * @param initialRoom
     *            Room where the game starts
     * @param playerPoints
     *            Initial player points
     * @param playerHealth
     *            Initial player health
     */
    void gameStart(RoomInfo initialRoom, int playerPoints, int playerHealth);

    /**
     * Notifies that the game cannot execute a command.
     * 
     * @param msg
     *            A message that describes the error
     */
    void gameError(String msg);

    /**
     * Notifies that the player requests help information
     */
    void gameHelp();

    /**
     * Notifies that the game is finished and whether the player wins or is
     * death.
     * 
     * @param win
     *            <code>true</code> if the player arrives at an exit room.
     *            <code>false</code> if the player dies
     */
    void gameOver(boolean win);

    /**
     * Notifies that the player requests to quit the game.
     */
    void gameQuit();
}
