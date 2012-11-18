package tp.pr5.commands;

import javax.swing.undo.CannotUndoException;

import tp.pr5.Constants;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.parsers.Parser;

public class HelpCommand implements Command {

	private Game game;

	/**
	 * Default constructor (null game)
	 */
	public HelpCommand() {

		this.game = null;
	}

	@Override
	public void execute() throws CommandExecutionException {

		if (Parser.getHelp() != null) {
			this.game.requestHelp();
		} else {
			throw new CommandExecutionException(Constants.BAD_COMMAND_EXECUTION);
		}
	}

	@Override
	public String getHelp() {

		return Constants.MESSAGE_HELP_HELP;
	}

	@Override
	public Command parse(String string) throws WrongCommandFormatException {

		HelpCommand parsedCommand = new HelpCommand();
		String[] userInput = string.split(" ");

		if ((userInput[0].equalsIgnoreCase("help") || userInput[0]
				.equalsIgnoreCase("ayuda")) && userInput.length == 1) {
			parsedCommand.configureContext(game, null, null);
			return parsedCommand;

		} else {
			throw new WrongCommandFormatException("Bad help command syntax");
		}

	}

	@Override
	public void configureContext(Game game, Map map, Player player) {
		this.game = game;
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
