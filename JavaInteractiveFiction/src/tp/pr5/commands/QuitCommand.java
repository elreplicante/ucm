package tp.pr5.commands;

import javax.swing.undo.CannotUndoException;

import tp.pr5.Constants;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

public class QuitCommand implements Command {

	private Game game;

	public QuitCommand() {
		this.game = null;
	}

	@Override
	public void configureContext(Game game, Map map, Player player) {
		this.game = game;

	}

	/**
	 * Request the given Game's runGame loop to quit
	 * 
	 * @throws CommandExecutionException
	 *             if there exist any execution error.
	 */
	@Override
	public void execute() throws CommandExecutionException {

		if (game == null) {
			throw new CommandExecutionException(
					"Quit command couldn't be executed");
		} else {
			game.requestQuit();
		}
	}

	/**
	 * Parses the String returning an instance of QuitCommand or throwing a
	 * WrongCommandFormatException()
	 * 
	 * @param string
	 *            text String to parse
	 * @param execContext
	 *            Executing Game
	 * @return Command reference to an instance of QuitCommand
	 * @throws WrongCommandFormatException
	 *             When the String is not QUIT|SALIR
	 */

	@Override
	public Command parse(String string) throws WrongCommandFormatException {

		QuitCommand parsedCommand;
		String[] userInput = string.split(" ");

		if ((userInput[0].equalsIgnoreCase("quit") || userInput[0]
				.equalsIgnoreCase("salir")) && userInput.length == 1) {
			parsedCommand = new QuitCommand();
			parsedCommand.configureContext(game, null, null);
			return parsedCommand;
		} else {
			throw new WrongCommandFormatException("Bad quit command syntax");
		}
	}

	@Override
	public String getHelp() {

		return Constants.MESSAGE_QUIT_HELP;
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
