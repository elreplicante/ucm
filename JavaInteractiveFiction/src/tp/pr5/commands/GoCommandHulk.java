/**
 * 
 */
package tp.pr5.commands;

import tp.pr5.Constants;
import tp.pr5.Directions;
import tp.pr5.Door;
import tp.pr5.Room;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

/**
 * @author repli
 *
 */
public class GoCommandHulk extends GoCommand {
	

	public GoCommandHulk() {
		super();
	}
	
	public GoCommandHulk(Directions dir) {
		super(dir);
	}
	public Command parse(String string) throws WrongCommandFormatException {

		GoCommandHulk parsedCommand;
		String[] userInput = string.split(" ");

		if ((userInput[0].equalsIgnoreCase("go") || userInput[0]
				.equalsIgnoreCase("ir")) && (userInput.length >= 2)) {
			if (userInput[1].equalsIgnoreCase("north")) {
				parsedCommand = new GoCommandHulk(Directions.NORTH);
			} else if (userInput[1].equalsIgnoreCase("south")) {
				parsedCommand = new GoCommandHulk(Directions.SOUTH);
			} else if (userInput[1].equalsIgnoreCase("east")) {
				parsedCommand = new GoCommandHulk(Directions.EAST);
			} else if (userInput[1].equalsIgnoreCase("west")) {
				parsedCommand = new GoCommandHulk(Directions.WEST);
			} else {
				throw new WrongCommandFormatException("Bad go command syntax");
			}
			parsedCommand.configureContext(game, map, player);
			return parsedCommand;
		} else {
			throw new WrongCommandFormatException("Bad go command syntax");
		}
	}
	
	public void execute() throws CommandExecutionException {

		if (game != null && direction != Directions.UNKNOWN) {

			Room room = map.getCurrentRoom();
			Door door = map.getDoor(room, direction);

			if (door == null)
				throw new CommandExecutionException(Constants.MESSAGE_WALL
						+ direction);

			
			else if (direction != Directions.UNKNOWN && door != null) {
				map.setPreviouslyRoom(room);
				player.addHealth(Constants.LOST_LIVE);
				map.setCurrentRoom(door.nextRoomHulk(room), direction);
				player.requestPlayerUpdate();
				game.setLastCommand(this);
			}
		}

		else
			throw new CommandExecutionException(Constants.MESSAGE_WHAT);
	}

}
