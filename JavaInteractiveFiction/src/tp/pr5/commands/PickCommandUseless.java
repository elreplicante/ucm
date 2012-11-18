/**
 * 
 */
package tp.pr5.commands;

import tp.pr5.Room;
import tp.pr5.commands.exceptions.CommandExecutionException;

/**
 * @author repli
 * 
 */
public class PickCommandUseless extends PickCommand {

    public void execute() throws CommandExecutionException {

	Room currentRoom = map.getCurrentRoom();
	if (player.getItem(itemId) == null) {
	    if (currentRoom.existsItem(itemId)) {
		currentRoom.pickItem(player, itemId);
		DropCommand dropCommand = new DropCommand(itemId);
		dropCommand.configureContext(game, map, player);
		dropCommand.execute();
		player.requestInventoryUpdate();
		if (!undoing)
		    game.setLastCommand(this);
		map.playerExamineRoom();
	    } else {
		throw new CommandExecutionException(itemId
			+ " doesn't exists in the current room.");
	    }
	} else {
	    throw new CommandExecutionException(
		    "Pick command couldn't be executed");
	}
    }

}
