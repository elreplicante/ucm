package tp.pr5.commands;

import javax.swing.undo.CannotUndoException;

import tp.pr5.Constants;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Room;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

public class DropCommand implements Command {

	private Game game;
	private Player player;
	private Map map;
	private String itemId;
	String commandName;
	private boolean undoing = false;

	/**
	 * It builds a drop command without the context of execution.
	 */

	public DropCommand() {

		this.game = null;
		this.itemId = "";
		this.map = null;
		this.player = null;
		commandName = "Drop";
	}

	/**
	 * It builds a command with the context of execution and the id.
	 * 
	 * @param execContext
	 *            Executing Game
	 * @param itemId
	 *            The item's id
	 */
	public DropCommand(String itemId) {
		game = null;
		this.itemId = itemId;
	}
	
	public void setUndoing(boolean undoing) {
		this.undoing = undoing;
	}

	public void configureContext(Game game, Map map, Player player) {
		this.game = game;
		this.map = map;
		this.player = player;
	}

	/**
	 * The execution must drop the given Item in the constructor from the given
	 * Player on the given Room
	 */
	public void execute() throws CommandExecutionException {

		Room currentRoom = map.getCurrentRoom();
		if (player.getItem(itemId) != null && !currentRoom.existsItem(itemId)) {
			currentRoom.addItem(player.getItem(itemId));
			player.removeItem(itemId);
			map.playerExamineRoom();
			if(!undoing)
				game.getLastCommands().add(this);
		} else if (player.getItem(itemId) == null
				|| currentRoom.existsItem(itemId)) {
			throw new CommandExecutionException("You do not have any " + itemId
					+ ".");
		}
	}

	public String getHelp() {
		return Constants.MESSAGE_DROP_HELP;
	}

	/**
	 * Parsers the String returning an DropCommand instance or throwing a
	 * WrongCommandFormatException()
	 * 
	 * @param string
	 *            Text String to parse
	 * @param execContext
	 *            Executing Game
	 * @return Command Reference to an instance of DropCommand
	 * @throws WrongCommandFormatException
	 *             When the String is not PICK <>
	 */
	public Command parse(String string) throws WrongCommandFormatException {

		DropCommand parsedCommand;
		String[] userInput = string.split(" ");

		if ((userInput[0].equalsIgnoreCase("drop") || userInput[0]
				.equalsIgnoreCase("soltar")) && userInput.length == 2) {
			parsedCommand = new DropCommand(userInput[1]);
			parsedCommand.configureContext(game, map, player);
			return parsedCommand;
		} else {
			throw new WrongCommandFormatException(Constants.BAD_COMMAND_SYNTAX);
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
		PickCommand pickCommand = new PickCommand(itemId);
		pickCommand.setUndoing(true);
		game.executeCommand(pickCommand);
		pickCommand.setUndoing(false);
	}

	@Override
	public String getCommandName() {
		return commandName;
	}

	@Override
	public String getId() {
		return itemId;
	}
}
