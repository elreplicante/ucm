/**
 * 
 */
package tp.pr5.console;

import java.util.Scanner;
import tp.pr5.Game;
import tp.pr5.GameController;
import tp.pr5.commands.Command;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.parsers.Parser;

/**
 * @author repli
 * 
 */
public class GameControllerConsole extends GameController {

	public GameControllerConsole(Game game, Parser parser) {
		super(game, parser);

	}

	@Override
	public void runGame() {

		Scanner reader = new Scanner(System.in);
		game.requestStart();

		while (!game.isOver()) {
			Console.prompt();
			String input = reader.nextLine();
			try {
				Command command = this.parser.parseCommand(input);
				game.executeCommand(command);

			} catch (WrongCommandFormatException e) {
				game.requestError(e.getMessage());
			}
		}
	}

}
