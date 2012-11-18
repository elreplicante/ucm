package tp.pr5.commands;

import javax.swing.undo.CannotUndoException;

import tp.pr5.Constants;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

public class ExamineCommand implements Command {

	private Game game;
	private Map map;

	/**
	 * It builds a command without the context of execution.
	 */
	public ExamineCommand() {
		this.game = null;
		this.map = null;
	}

	/**
	 * Constructor with a game.
	 * 
	 * @param execContext
	 *            The game
	 */
	public ExamineCommand(Game execContext) {
		this.game = execContext;
	}

	@Override
	public void configureContext(Game game, Map map, Player player) {
		this.game = game;
		this.map = map;

	}

	/**
	 * Shows the current Door description, provided in the constructor.
	 * 
	 * @throws CommandExecutionException
	 *             if there exist any execution error.
	 */
	public void execute() throws CommandExecutionException {

		if (map.getCurrentRoom() != null) {
			map.playerExamineRoom();
		} else {
			throw new CommandExecutionException(Constants.BAD_COMMAND_EXECUTION);
		}
	}

	/**
	 * Returns A description of the command syntax. The string does not end with
	 * the line separator. It is up to the caller adding it before printing.
	 * 
	 * @return The syntax accepted by the parse method (EXAMINE or EXAMINAR)
	 */
	public String getHelp() {
		return Constants.MESSAGE_EXAMINE_HELP;
	}

	/**
	 * Parses the String returning an instance of ExamineCommand or throwing a
	 * WrongCommandFormatException()
	 * 
	 * @param string
	 *            text String to parse
	 * @param execContext
	 *            Executing Game
	 * @return Command Reference to an instance of ExamineCommand
	 * @throws WrongCommandFormatException
	 *             When the String is not EXAMINE or EXAMINAR.
	 */
	public Command parse(String string) throws WrongCommandFormatException {

		ExamineCommand parsedCommand;
		String[] userInput = string.split(" ");

		if ((userInput[0].equalsIgnoreCase("examine") || userInput[0]
				.equalsIgnoreCase("examinar")) && userInput.length == 1) {
			parsedCommand = new ExamineCommand(game);
			parsedCommand.configureContext(game, map, null);
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
