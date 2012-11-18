/*package tp.pr5.commands;

import javax.swing.undo.CannotUndoException;

import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

public class UndoCommand implements Command {

	private Game game;

	public UndoCommand() {
		game = null;
	}

	@Override
	public void execute() throws CommandExecutionException {
		if (game.getLastCommands().size() != 0) {
			if (game.getLastCommands().lastElement().canUndo())
				game.getLastCommands().lastElement().undo();
				game.removeLastCommand();
		} else {
			throw new CommandExecutionException("Cannot undo");
		}
	}

	@Override
	public String getHelp() {
		return null;
	}

	@Override
	public void configureContext(Game game, Map map, Player player) {
		this.game = game;
	}

	@Override
	public void undo() throws CannotUndoException {

	}

	@Override
	public boolean canUndo() {
		return false;
	}

	@Override
	public Command parse(String string) throws WrongCommandFormatException {
		UndoCommand undoCommand;

		String[] userInput = string.split(" ");

		if (userInput[0].equalsIgnoreCase("UNDO")) {
			undoCommand = new UndoCommand();
			undoCommand.configureContext(game, null, null);
		} else {
			throw new WrongCommandFormatException("What?");
		}
		return undoCommand;
	}

	@Override
	public String getCommandName() {
		return "";
	}

	@Override
	public String getId() {
		return "";
	}

}*/

