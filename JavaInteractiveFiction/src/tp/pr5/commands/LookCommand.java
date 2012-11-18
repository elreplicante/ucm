package tp.pr5.commands;

import javax.swing.undo.CannotUndoException;

import tp.pr5.Constants;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

public class LookCommand implements Command {

    private Game game;
    private Player player;
    private String itemId;

    public LookCommand() {

	this.game = null;
	this.itemId = "";
    }

    /**
     * Full constructor
     * 
     * @param execContext
     *            the game
     * @param itemId
     *            the item id
     */
    public LookCommand(String itemId) {

	this.game = null;
	this.itemId = itemId;
    }

    /**
     * Contructor with a game
     * 
     * @param execContext
     *            the game
     */
    public LookCommand(Game execContext) {

	this.game = execContext;
	this.itemId = "";
    }

    @Override
    public void configureContext(Game game, Map map, Player player) {
	this.game = game;
	this.player = player;

    }

    @Override
    public void execute() throws CommandExecutionException {

	if (itemId == "") {
	    if (player.showItems().equals(""))
		player.requestLookInventory();
	    else
		throw new CommandExecutionException(Constants.MESSAGE_POOR);

	} else {
	    if (player.getItem(itemId) != null) {
		player.lookItem(itemId);
	    } else if (player.getItem(itemId) == null) {
		throw new CommandExecutionException("There is no " + itemId
			+ " in your inventory.");
	    }
	}
    }

    @Override
    public String getHelp() {
	return Constants.MESSAGE_LOOK_HELP;
    }

    @Override
    public Command parse(String string) throws WrongCommandFormatException {

	LookCommand parsedCommand = null;
	String[] userInput = string.split(" ");

	if (userInput[0].equalsIgnoreCase("look")
		|| userInput[0].equalsIgnoreCase("mira")) {
	    if (userInput.length == 2) {
		parsedCommand = new LookCommand(userInput[1]);
	    } else if (userInput.length == 1) {
		parsedCommand = new LookCommand(game);
	    }
	    parsedCommand.configureContext(game, null, player);
	    return parsedCommand;
	} else {
	    throw new WrongCommandFormatException(Constants.BAD_COMMAND_SYNTAX);
	}
    }

	@Override
	public boolean canUndo() {
		return false;
	}

	@Override
	public void undo() throws CannotUndoException {
	}

	@Override
	public String getCommandName() {
		return "";
	}

	@Override
	public String getId() {
		return "";
	}

}
