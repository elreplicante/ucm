package tp.pr5;

import jargs.gnu.CmdLineParser;
import jargs.gnu.CmdLineParser.OptionException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import tp.pr5.console.Console;
import tp.pr5.console.GameControllerConsole;
import tp.pr5.gui.GameControllerGUI;
import tp.pr5.gui.MainWindow;
import tp.pr5.maploader.MapLoaderFromTxtFile;
import tp.pr5.maploader.exceptions.WrongMapFormatException;
import tp.pr5.parsers.Parser;
import tp.pr5.parsers.ParserHulk;
import tp.pr5.parsers.ParserUseless;
import tp.pr5.solution.VueltaAtras;

public class Main {

    static CmdLineParser argsParser = new CmdLineParser();
    static Parser parser;
    static Game game;
    static Map map;

    /**
     * printUsage method
     * 
     * Prints a args guide and close the aplication
     */
    private static void printUsage() {
	System.err
		.println("Wrong sintaxis. Wrong value"
			+ Constants.LINE_SEPARATOR
			+ Constants.LINE_SEPARATOR
			+ "Usage: tp.pr5.Main (-i|--interface) (console | swing | both) "
			+ "(-m|--map) <mapFilename>"
			+ "(-p|-player) <playertype> (normal | hulk | useless)"
			+ Constants.LINE_SEPARATOR);

    }

    private static void configureAndLaunchConsole(Game game) {

	GameControllerConsole gcc = new GameControllerConsole(game, parser);
	Console consoleView = new Console();

	gcc.registerGameObserver(consoleView);
	gcc.registerMapObserver(consoleView);
	gcc.registerPlayerObserver(consoleView);

	gcc.runGame();
    }

    private static void configureAndLaunchGUI(Game game) {

	GameControllerGUI controllerGUI = new GameControllerGUI(game, parser);
	MainWindow mainWindow = new MainWindow(controllerGUI);
	controllerGUI.runGame();
    }

    private static void configurePlayer(String playerType) {

	if (playerType.equals("hulk")) {
	    parser = new ParserHulk();
	} else if (playerType.equals("normal")) {
	    parser = new Parser();
	} else if (playerType.equals("useless")) {
	    parser = new ParserUseless();
	}
    }

    private static void configureUIMode(String mode) {

	if (mode.equals("console")) {
	    configureAndLaunchConsole(game);
	} else if (mode.equals("swing")) {
	    configureAndLaunchGUI(game);
	} else if (mode.equals("both")) {
	    configureAndLaunchGUI(game);
	    configureAndLaunchConsole(game);
	} else if (mode.equals("solution")) {
	    VueltaAtras solution = new VueltaAtras(game, map);
	} else {
	    printUsage();
	    System.exit(-2);
	}
    }

    /**
     * Main method
     * 
     * Main entrance of the application
     * 
     * @param args
     *            - Command args. When the application is launched with wrong or
     *            missing parameters a help text is shown.
     * @author
     * @throws WrongMapFormatException
     */
    public static void main(String[] args) throws IOException,
	    WrongMapFormatException {

	CmdLineParser.Option modeOption = argsParser.addStringOption('i',
		"interface");
	CmdLineParser.Option mapOption = argsParser.addStringOption('m', "map");
	CmdLineParser.Option playerOption = argsParser.addStringOption('p',
		"player");

	try {
	    argsParser.parse(args);
	    String uiMode = (String) argsParser.getOptionValue(modeOption);
	    String mapName = (String) argsParser.getOptionValue(mapOption);
	    String playerType = (String) argsParser
		    .getOptionValue(playerOption);
	    MapLoaderFromTxtFile mapLoader = new MapLoaderFromTxtFile();

	    try {
		FileInputStream file = new FileInputStream(mapName);
		map = mapLoader.loadMap(file);
		game = new Game(map);

		configurePlayer(playerType);
		configureUIMode(uiMode);

	    } catch (WrongMapFormatException e) {
		System.err.println("Map not especified");
		System.exit(-3);

	    } catch (NullPointerException e) {
		printUsage();
		System.exit(-5);
	    } catch (FileNotFoundException e) {
		System.err.println(":~$ java tp.pr5.Main " + mapOption
			+ Constants.LINE_SEPARATOR + "File not found.");

		System.exit(-3);

	    } catch (java.io.IOException fileErrEx) {

		System.err.println(":~$ java tp.pr5.Main " + mapOption
			+ Constants.LINE_SEPARATOR + fileErrEx.getMessage());
		System.exit(-4);

	    }
	} catch (OptionException inputEx) {
	    printUsage();
	    System.exit(-1);

	}

    }

}
