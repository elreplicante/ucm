package tp.pr5;

/**
 * @author Grupo 3
 * 
 */

public class Constants {

    /**
     * Short way to make a System.out.println().
     * 
     * @param message
     *            The string to be printed
     */
    public static void println(String message) {
	System.out.println(message);
    }

    /**
     * Short way to make a System.out.print().
     * 
     * @param message
     *            The string to be printed
     */
    public static void print(String message) {
	System.out.print(message);
    }

    /**
     * Used in ExpirationItem class. It defines the default value of the live of
     * this kind of Items.
     */
    public static final int DEFAULT_EXPIRATION_LIVE = 10;

    /**
     * Used in Player class. Initial value of the live.
     */
    public static final int INITIAL_LIVE = 100;

    /**
     * Used in Player class. Initial value of the score of a player.
     */
    public static final int INITIAL_SCORE = 0;

    public static final int DEFAULT_TIMES = 1;

    /**
     * Line separator
     */
    public static final String LINE_SEPARATOR = System
	    .getProperty("line.separator");

    /**
     * Used in player class. This value represents the amount of health that the
     * player looses when she changes the room.
     */
    public static final int LOST_LIVE = -5;

    /**
     * Used in Player class. If there are no items, then the player shows this
     * message.
     */
    public static final String MESSAGE_POOR = "You are poor, you have not got any item (yet).";

    /**
     * Used in Game class. This message is shown when the player changes the
     * room
     */
    public static final String MESSAGE_CHANGE_ROOM = "...moving to ";

    /**
     * Used in Game class. When the command is unknown this messaged is showed.
     */
    public static final String MESSAGE_WHAT = "What?";

    /**
     * Used in Game class. This message is shown when there is a door, it is
     * closed and the player tries to go in this direction.
     */
    public static final String MESSAGE_DOOR = "There is a door in the ";

    public static final String MESSAGE_DOOR2 = ", but it is closed.";

    /**
     * Used in Game class. When the user tries to go to a direction, but there
     * is a wall.
     */
    public static final String MESSAGE_WALL = "There is no room in direction ";

    /**
     * Used in Game class. This message is shown when an Item changes something
     * (open a door, change your live...).
     */
    public static final String MESSAGE_CHANGES = "Something changes ...";

    public static final String MESSAGE_KEY_ERROR = "I did not go to TP classes last week, I do not know how to use it...";

    /**
     * Used in Game class. Prompt.
     */

    public static final String PROMPT = "> ";

    public static final String MESSAGE_SALIDA = "Llegamos a la salida";
    /**
     * Used in Game class. It indicates the end of the game.
     */
    public static final String MESSAGE_FIN = "GAME OVER " + LINE_SEPARATOR
	    + "Thank you for playing, goodbye.";

    public static final String MESSAGE_DEAD = "You are dead." + LINE_SEPARATOR;

    public static final String MESSAGE_TRY_USE_ITEM_BUT_NOT_EXISTS = "Someone stole your ";

    public static final String MESSAGE_CANT_BE_USED = "I did not go to TP classes last week, I do not know how to use it...";

    /**
     * Used in Room class. When a room hasn't any item then this message is
     * shown.
     */
    public static final String MESSAGE_NO_ITEMS = "Do you get to pick what you dream about?";

    /**
     * Shows game help
     */
    public static final String MESSAGE_HELP = "You are lost. You are alone. You wander around"
	    + LINE_SEPARATOR
	    + "Your command words are:"
	    + LINE_SEPARATOR
	    + "   EXAMINE|EXAMINAR"
	    + LINE_SEPARATOR
	    + "   GO|IR { NORTH|EAST|SOUTH|WEST }"
	    + LINE_SEPARATOR
	    + "   HELP|AYUDA"
	    + LINE_SEPARATOR
	    + "   LOOK|MIRA [<<id>>]"
	    + LINE_SEPARATOR
	    + "   PICK|COGER <<id>>"
	    + LINE_SEPARATOR
	    + "   DROP|SOLTAR <<id>>"
	    + LINE_SEPARATOR
	    + "   QUIT|SALIR"
	    + LINE_SEPARATOR + "   USE|USAR <<id>>" + LINE_SEPARATOR;

    public static final String MESSAGE_DROP_HELP = "DROP|SOLTAR <<id>>";

    public static final String MESSAGE_QUIT_HELP = "QUIT|SALIR";

    public static final String MESSAGE_EXAMINE_HELP = "EXAMINE|EXAMINAR";

    public static final String MESSAGE_GO_HELP = "GO|IR { NORTH|EAST|SOUTH|WEST }";

    public static final String MESSAGE_HELP_HELP = "HELP|AYUDA";

    public static final String MESSAGE_LOOK_HELP = "LOOK|MIRA [<<id>>]";

    public static final String BAD_COMMAND_SYNTAX = "Bad command syntax";

    public static final String BAD_COMMAND_EXECUTION = "Bad command execution";

    public static final String MESSAGE_BIDIRECTIONAL = "Impossible to go through the door from this side.";

    public static final String MESSAGE_LOOK = "My items are:" + LINE_SEPARATOR;

    public static final String MESSAGE_EMPTY = " is empty. It is deleted from the inventory.";

    public static final String MESSAGE_ROOM = "It contains the following items:"
	    + LINE_SEPARATOR;

    public static final String MESSAGE_EMPTY_ROOM = "This room is empty";

    public static final int MAX_ROOM_ITEMS = 10;
}
