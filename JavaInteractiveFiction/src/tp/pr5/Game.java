package tp.pr5;

import java.util.ArrayList;
import java.util.List;

import tp.pr5.commands.Command;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.observers.GameObserver;
import tp.pr5.observers.MapObserver;
import tp.pr5.observers.Observable;
import tp.pr5.observers.PlayerObserver;

/**
 * @author Grupo03 Sergio Revilla Jose Carlos Gonzalez
 * 
 * 
 */

public class Game extends Observable<GameObserver> {

    /**
     * The player of the game.
     */
    private final Player player;

    /**
     * The map of the game.
     */
    private Map map;

    /**
     * True if the player is out of life or completed the game.
     */
    private boolean endGame;

    private List<Command> lastCommands;

    /**
     * Default constructor. Initializes the map and the initial room. As usual,
     * all attributes must be initialized in the constructor.
     * 
     * @param map
     *            The Map where the Game is going to be played
     * 
     * 
     */
    public Game(Map map) {
	super();
	this.map = map;
	this.player = new Player();
	this.endGame = false;
	lastCommands = new ArrayList<Command>();

    }

    public List<Command> getLastCommands() {
	return lastCommands;
    }

    public void setLastCommand(Command command) {
	lastCommands.add(command);
    }

    public void removeLastCommand() {
	lastCommands.remove(lastCommands.size() - 1);
    }

    /**
     * Registers a GameObserver to the model.
     * 
     * @param observer
     *            The observer that wants to be registered
     */
    public void addGameObserver(GameObserver observer) {
	this.addObserver(observer);
    }

    /**
     * Removes a GameObserver attached to the model.
     * 
     * @param observer
     *            The observer that wants to be registered
     */
    public void removeGameObserver(GameObserver observer) {
	this.removeObserver(observer);
    }

    /**
     * Registers a PlayerObserver to the player contained in model.
     * 
     * @param observer
     *            The observer that wants to be registered
     */
    public void addPlayerObserver(PlayerObserver observer) {
	player.addObserver(observer);
    }

    /**
     * Removes a PlayerObserver attached to the player contained in the model.
     * 
     * @param observer
     *            The observer that wants to be deregistered
     */
    public void removePlayerObserver(PlayerObserver observer) {
	player.removeObserver(observer);
    }

    /**
     * Registers a MapObserver to the map contained in model.
     * 
     * @param observer
     *            The observer that wants to be registered
     */
    public void addMapObserver(MapObserver observer) {
	map.addObserver(observer);
    }

    /**
     * Removes a MapObserver attached to the map contained in the model.
     * 
     * @param observer
     *            The observer that wants to be deregistered
     */
    public void removeMapObserver(MapObserver observer) {
	map.removeObserver(observer);
    }

    /**
     * Checks if the game is finished.
     * 
     * @return true if the game has finished
     */
    public boolean isOver() {
	return endGame || player.dead();
    }

    /**
     * Requests the game to quit
     */
    public void requestQuit() {
	endGame = true;
	for (GameObserver iterableGameObserver : observers) {
	    iterableGameObserver.gameQuit();
	}
    }

    public void requestHelp() {
	for (GameObserver iterableGameObserver : observers) {
	    iterableGameObserver.gameHelp();
	}
    }

    public void requestStart() {

	for (GameObserver iterableGameObserver : observers) {
	    iterableGameObserver.gameStart(map.getCurrentRoom(),
		    player.getPoints(), player.getHealth());
	}
    }

    public void executeCommand(Command command) {

	try {
	    command.configureContext(this, map, player);
	    command.execute();
	    if (player.dead()) {

		for (GameObserver observerIterator : observers) {
		    observerIterator.gameOver(false);
		}
	    }
	    if (map.getCurrentRoom().isExit()) {

		for (GameObserver observerIterator : observers) {
		    observerIterator.gameOver(true);
		}
	    }

	} catch (CommandExecutionException e) {
	    requestError(e.getMessage());

	}
    }

    public void requestError(String errorMsg) {
	for (GameObserver iterableGameObserver : observers) {
	    iterableGameObserver.gameError(errorMsg);
	}
    }

}