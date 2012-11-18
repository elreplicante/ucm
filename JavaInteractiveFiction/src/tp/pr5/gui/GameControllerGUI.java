/**
 * 
 */
package tp.pr5.gui;

import tp.pr5.Directions;
import tp.pr5.Game;
import tp.pr5.GameController;
import tp.pr5.commands.DropCommand;
import tp.pr5.commands.GoCommand;
import tp.pr5.commands.GoCommandHulk;
import tp.pr5.commands.PickCommand;
import tp.pr5.commands.UseCommand;
import tp.pr5.parsers.Parser;
import tp.pr5.parsers.ParserHulk;
import tp.pr5.parsers.ParserUseless;

/**
 * @author repli
 * 
 */
public class GameControllerGUI extends GameController {

	/**
	 * @param game
	 */
	public GameControllerGUI(Game game, Parser parser) {
		super(game, parser);
	}

	@Override
	public void runGame() {
		
		game.requestStart();
	}

	public void executeDropAction(String item) {
		DropCommand dropCommand = new DropCommand(item);
		game.executeCommand(dropCommand);
	}

	public void executePickAction(String item) {
		PickCommand pickCommand = new PickCommand(item);
		game.executeCommand(pickCommand);
		if (parser.getClass().equals(ParserUseless.class) && pickCommand.itemExistsInRoom()) {
		    DropCommand dropCommand = new DropCommand(item);
		    game.executeCommand(dropCommand);
		}
	}

	public void executeQuitAction() {
		game.requestQuit();
	}

	public void executeUseAction(String itemName) {
		UseCommand useCommand = new UseCommand(itemName);
		game.executeCommand(useCommand);
	}

	public void executeGoAction(Directions direction) {
		GoCommand goCommand;
		if(parser.getClass().equals(ParserHulk.class)) {
			goCommand = new GoCommandHulk(direction);
		} else {
			goCommand = new GoCommand(direction);
		}
		
		game.executeCommand(goCommand);
	}
	
	/*public void executeUndoAction() {
		UndoCommand undoCommand = new UndoCommand();
		game.executeCommand(undoCommand);
	}*/

}
