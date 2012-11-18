package tp.pr5.commands;

import javax.swing.undo.CannotUndoException;

import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

/**
 * This interface represents a command supported by the application. Every
 * command that player may perform implements this interface. It forces commands
 * to provide with the implementation of four different methods:
 * <ul>
 * <li>Parse method tries to parse a string with the information of the command
 * the class represents</li>
 * <li>Help method returns a string with an explanation of the kind of
 * expression that the parse method supports</li>
 * <li>ConfigureContext method establishes the context needed to execute the
 * command</li>
 * <li>Execute method performs the actual work of the command, executing it.</li>
 * </ul>
 * The <code>execute</code> method does not have any parameter. Therefore the
 * context of execution must be given to the command object prior to its
 * invocation using the <code>setContext</code> method. Note that the actual
 * context depends on the subclass because the information needed varies between
 * commands.
 * 
 * @author Grupo 3
 * 
 */
public interface Command {

	/**
	 * Executes the command. It must be implemented in every non abstract
	 * subclass.
	 * 
	 * @throws CommandExecutionException
	 *             if there exist any execution error.
	 */
	void execute() throws CommandExecutionException;

	/**
	 * Returns A description of the command syntax. The string does not end with
	 * the line separator. It is up to the caller adding it before printing.
	 * 
	 * @return The command's syntax.
	 */
	String getHelp();

	/**
	 * Parsers the String returning an instance its corresponding subclass if
	 * the string fits the command's syntax. Otherwise it throws an
	 * WrongCommandFormatException. Each non abstract subclass must implement
	 * its corresponding parse.
	 * 
	 * @param string
	 *            Text String
	 * @param execContext
	 *            Executing Game
	 * @return Command Reference pointing to an instance of a Command subclass,
	 *         if it is corresponding to the String cad
	 * @throws WrongCommandFormatException
	 *             When the String cad does not fit the command syntax.
	 */
	Command parse(String string) throws WrongCommandFormatException;

	/**
	 * Inverts the last command executed.
	 * 
	 * If it is a pick command, executes a drop command, and so on
	 * 
	 * @throws CannotUndoException
	 */

	void configureContext(Game game, Map map, Player player);
	
	boolean canUndo();
	
	void undo() throws CannotUndoException;
	
	String getCommandName();
	
	String getId();
}
