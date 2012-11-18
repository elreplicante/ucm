package tp.pr5.commands;

import javax.swing.undo.CannotUndoException;

import tp.pr5.Constants;
import tp.pr5.Directions;
import tp.pr5.Door;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Room;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

public class GoCommand implements Command {

	protected Game game;
	protected Player player;
	protected Map map;
	protected Directions direction;
	protected String commandName;

	/**
	 * Gets the direction
	 * 
	 * @return the direction to get
	 */
	public Directions getDirection() {
		return direction;
	}

	/**
	 * It builds a command without the context of execution. Therefore, if the
	 * command is executed an exception should be raised (it does not know where
	 * to go).
	 */

	public GoCommand() {
		this.game = null;
		this.direction = null;
	}

	public GoCommand(Directions direction) {
		this.direction = direction;
		commandName = "Go";
	}

	@Override
	public void configureContext(Game game, Map map, Player player) {
		this.game = game;
		this.map = map;
		this.player = player;

	}

	/**
	 * Moves from the Games current Room to the Room on the given Direction,
	 * provided in the constructor An opened Door must exist between both Doors
	 * to be moved.
	 * 
	 * @throws CommandExecutionException
	 *             When the player cannot go to other room (there is a wall, a
	 *             closed door...)
	 */
	public void execute() throws CommandExecutionException {

		if (game != null && direction != Directions.UNKNOWN) {

			Room room = map.getCurrentRoom();
			Door door = map.getDoor(room, direction);

			if (door == null)
				throw new CommandExecutionException(Constants.MESSAGE_WALL
						+ direction);

			else if (!door.isOpen())
				throw new CommandExecutionException(Constants.MESSAGE_DOOR
						+ direction + Constants.MESSAGE_DOOR2);

			else if (!door.connect(room, door.nextRoom(room)))
				throw new CommandExecutionException(
						Constants.MESSAGE_BIDIRECTIONAL);

			else if (direction != Directions.UNKNOWN && door != null
					&& door.isOpen()) {
				map.setPreviouslyRoom(room);
				player.addHealth(Constants.LOST_LIVE);
				map.setCurrentRoom(door.nextRoom(room), direction);
				player.requestPlayerUpdate();
				game.setLastCommand(this);
			}
		}

		else
			throw new CommandExecutionException(Constants.MESSAGE_WHAT);
	}

	public void undoGo() {
		map.setCurrentRoom(map.getPreviouslyRoom(), direction);
		player.addHealth(5);
		player.requestPlayerUpdate();
		map.removeLastPreviouslyRoom();
	}

	/**
	 * 
	 * @see tp.pr5.commands.Command#getHelp()
	 * @return the command syntax GO|IR { NORTH|EAST|SOUTH|WEST }
	 */
	public String getHelp() {

		return Constants.MESSAGE_GO_HELP;
	}

	/**
	 * Parses the String returning a GoCommand instance or throwing a
	 * WrongCommandFormatException()
	 * 
	 * @param string
	 *            text String to parse
	 * @param execContext
	 *            Executing Game
	 * @return Command Reference to an instance of GoCommand
	 * @throws WrongCommandFormatException
	 *             When the String is not GO|IR { NORTH|EAST|SOUTH|WEST }
	 */

	public Command parse(String string) throws WrongCommandFormatException {

		GoCommand parsedCommand;
		String[] userInput = string.split(" ");

		if ((userInput[0].equalsIgnoreCase("go") || userInput[0]
				.equalsIgnoreCase("ir")) && (userInput.length >= 2)) {
			if (userInput[1].equalsIgnoreCase("north")) {
				parsedCommand = new GoCommand(Directions.NORTH);
			} else if (userInput[1].equalsIgnoreCase("south")) {
				parsedCommand = new GoCommand(Directions.SOUTH);
			} else if (userInput[1].equalsIgnoreCase("east")) {
				parsedCommand = new GoCommand(Directions.EAST);
			} else if (userInput[1].equalsIgnoreCase("west")) {
				parsedCommand = new GoCommand(Directions.WEST);
			} else {
				throw new WrongCommandFormatException("Bad go command syntax");
			}
			parsedCommand.configureContext(game, map, player);
			return parsedCommand;
		} else {
			throw new WrongCommandFormatException("Bad go command syntax");
		}
	}

	@Override
	public boolean canUndo() {
		if ((game.getLastCommands().size() == 0)) {
			return false;
		} else
			return true;
	}

	@Override
	public void undo() throws CannotUndoException {
		GoCommand goCommand = new GoCommand(direction.opposite());
		goCommand.configureContext(game, map, player);
		goCommand.undoGo();
	}

	@Override
	public String getCommandName() {
		return commandName;
	}

	@Override
	public String getId() {
		return direction.toString();
	}

}