package tp.pr5.commands;

import javax.swing.undo.CannotUndoException;

import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Room;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

public class PickCommand implements Command {

	protected String itemId;
	protected Player player;
	protected Map map;
	protected Game game;
	protected boolean undoing = false;
	protected String commandName;
	protected boolean itemExists;
	/**
	 * @param execContext2
	 * @param string
	 */
	public PickCommand(String itemId) {
		this.itemId = itemId;
		commandName = "Pick";
		itemExists = false;
	}

	public PickCommand() {
		this.itemId = null;
		this.player = null;
		this.map = null;
	}

	public void setUndoing(boolean undoing) {
		this.undoing = undoing;
	}

	@Override
	public void configureContext(Game game, Map map, Player player) {
		this.map = map;
		this.player = player;
		this.game = game;
	}
	
	public boolean itemExistsInRoom() {
	    return itemExists;
	}

	@Override
	public void execute() throws CommandExecutionException {

		Room currentRoom = map.getCurrentRoom();
		if (player.getItem(itemId) == null) {
			if (currentRoom.existsItem(itemId)) {
			    itemExists = true;
				currentRoom.pickItem(player, itemId);
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

	@Override
	public String getHelp() {

		return "PICK|COGER <<id>>";
	}

	@Override
	public Command parse(String string) throws WrongCommandFormatException {

		PickCommand parsedCommand;
		String[] userInput = string.split(" ");

		if ((userInput[0].equalsIgnoreCase("pick") || userInput[0]
				.equalsIgnoreCase("coger")) && userInput.length == 2) {
			parsedCommand = new PickCommand(userInput[1]);
			parsedCommand.configureContext(null, map, player);
			return parsedCommand;
		} else {
			throw new WrongCommandFormatException("Bad pick command syntax");
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
		DropCommand dropCommand = new DropCommand(itemId);
		dropCommand.setUndoing(true);
		game.executeCommand(dropCommand);
		dropCommand.setUndoing(false);
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
