package tp.pr5.parsers;

import java.util.ArrayList;

import tp.pr5.Constants;
import tp.pr5.commands.Command;
import tp.pr5.commands.DropCommand;
import tp.pr5.commands.ExamineCommand;
import tp.pr5.commands.GoCommand;
import tp.pr5.commands.HelpCommand;
import tp.pr5.commands.LookCommand;
import tp.pr5.commands.PickCommand;
import tp.pr5.commands.QuitCommand;
import tp.pr5.commands.UseCommand;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

/**
 * The parser is in charge of parsing the user input in order to generate a
 * command for the game. Actually, the parser delegates command objects to parse
 * and generate a correct command according to the user input. <br>
 * Up to now, the valid commands are: <br>
 * <ul>
 * <li>EXAMINE|EXAMINAR</li>
 * <li>GO|IR { NORTH|EAST|SOUTH|WEST }</li>
 * <li>HELP|AYUDA</li>
 * <li>LOOK|MIRA [&lt;&lt;id&gt;&gt;]</li>
 * <li>PICK|COGER &lt;&lt;id&gt;&gt;</li>
 * <li>DROP|SOLTAR &lt;&lt;id&gt;&gt;</li>
 * <li>QUIT|SALIR</li>
 * <li>USE|USAR &lt;&lt;id&gt;&gt;</li>
 * 
 * @author Grupo03 Sergio Revilla - Jose Carlos Gonzalez
 */

public class Parser {

    protected ArrayList<Command> allCommands;

    /**
     * It returns information about all the commands that the game understands
     * 
     * @return String A string with the information about all the available
     *         commands
     */

    public static String getHelp() {

	return Constants.MESSAGE_HELP;
    }

    /**
     * Parse the next command according to the user input. It returns the
     * corresponding command.
     * 
     * @param line
     *            is a String that possibly contains a command
     * @param executionContext
     *            Game where a string is parsed
     * @return A command. If the line does not contain a correct command then
     *         the method returns a not valid command
     * @throws WrongCommandFormatException
     *             When the String does not fit the syntax of any command.
     */
    public Command parseCommand(java.lang.String line)
	    throws WrongCommandFormatException {

	allCommands = new ArrayList<Command>();

	allCommands.add(new DropCommand());
	allCommands.add(new ExamineCommand());
	allCommands.add(new GoCommand());
	allCommands.add(new HelpCommand());
	allCommands.add(new LookCommand());
	allCommands.add(new PickCommand());
	allCommands.add(new QuitCommand());
	allCommands.add(new UseCommand());

	for (Command iterableCommand : allCommands) {
	    try {
		return iterableCommand.parse(line);

	    } catch (WrongCommandFormatException wcfe) {

	    }
	}

	throw new WrongCommandFormatException(Constants.MESSAGE_WHAT);
    }

}
