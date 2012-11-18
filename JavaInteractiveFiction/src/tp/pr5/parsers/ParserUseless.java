/**
 * 
 */
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
import tp.pr5.commands.PickCommandUseless;
import tp.pr5.commands.QuitCommand;
import tp.pr5.commands.UseCommand;
import tp.pr5.commands.exceptions.WrongCommandFormatException;

/**
 * @author repli
 * 
 */
public class ParserUseless extends Parser {

    public Command parseCommand(java.lang.String line)
	    throws WrongCommandFormatException {

	allCommands = new ArrayList<Command>();

	allCommands.add(new DropCommand());
	allCommands.add(new ExamineCommand());
	allCommands.add(new GoCommand());
	allCommands.add(new HelpCommand());
	allCommands.add(new LookCommand());
	allCommands.add(new PickCommandUseless());
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
