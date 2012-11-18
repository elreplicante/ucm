package tp.pr5.commands;

import javax.swing.undo.CannotUndoException;

import tp.pr5.Constants;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

public class UseCommand implements Command {

	private Game game;
	private Player player;
	private Map map;
	private String itemId;
	private String commandName;

	public UseCommand() {
		this.game = null;
		this.player = null;
		this.map = null;
		this.itemId = null;

	}

	public UseCommand(String id) {

		this.game = null;
		this.itemId = id;
		commandName = "Use";
	}

	@Override
	public void configureContext(Game game, Map map, Player player) {

		this.game = game;
		this.map = map;
		this.player = player;
	}

	@Override
	public void execute() throws CommandExecutionException {

		if (player.getItem(itemId) != null) {
			if (player.getItem(itemId).canBeUsed()) {
				player.getItem(itemId).use(player, map.getCurrentRoom());
				player.itemUsed(itemId);
				if (!player.getItem(itemId).canBeUsed()) {
					player.getLastItems().add(player.getItem(itemId));
					player.itemEmpty(itemId);
					player.removeItem(itemId);
				}
				game.setLastCommand(this);

			} else {
				throw new CommandExecutionException();
			}
		} else {
			throw new CommandExecutionException(
					Constants.MESSAGE_TRY_USE_ITEM_BUT_NOT_EXISTS + itemId);
		}
	}
	
	/*public void undoUse() {
		if(player.getItem(itemId) != null) {
			player.getItem(itemId).undoUse(player, map.getCurrentRoom());
			player.itemUsed(itemId);
		} else {
			player.getLastItems().lastElement().undoUse(player, map.getCurrentRoom());
			player.itemUsed(player.getLastItems().lastElement().getId());
			// OJO!!!! Si se entra aqui es porque el item usado se ha destruido porque se ha terminado
			// entonces lo que hay que hacer es que la primera vez que se usa el item, se usa de esta forma
			// y se añade este item al player y se borra del vector de items
			player.addItem(player.getLastItems().lastElement());
			player.removeLastItem();
		}
	}*/

	@Override
	public String getHelp() {

		return "USE|USAR <<id>>";
	}

	@Override
	public Command parse(String string) throws WrongCommandFormatException {

		UseCommand parsedCommand;
		String[] userInput = string.split(" ");

		if ((userInput[0].equalsIgnoreCase("use") || userInput[0]
				.equalsIgnoreCase("usar")) && userInput.length == 2) {
			parsedCommand = new UseCommand(userInput[1]);
			parsedCommand.configureContext(game, map, player);
			return parsedCommand;
		} else {
			throw new WrongCommandFormatException("Bad use command syntax");
		}
	}

	@Override
	public boolean canUndo() {
		if ((game.getLastCommands().size() == 0)) {
			return false;
		} else
			return true;
	}

	public void undo() throws CannotUndoException {
		/*UseCommand useCommand = new UseCommand(itemId);
		useCommand.configureContext(game, map, player);
		useCommand.undoUse();*/
	}

	public String getCommandName() {
		return commandName;
	}

	@Override
	public String getId() {
		return itemId;
	}

}
