package tp.pr5;

import tp.pr5.observers.GameObserver;
import tp.pr5.observers.MapObserver;
import tp.pr5.observers.PlayerObserver;
import tp.pr5.parsers.Parser;

/**
 * Abstract class that represents a controller. The controller contains a
 * reference to the entrance point to the model (the game) and it runs the game.
 * Additionally, the controller can register different types of observers.
 * 
 * @author Grupo03
 * 
 */
public abstract class GameController {

    /**
     * A reference to the entrance point of the model: the game.
     */
    protected Game game;
    protected Parser parser;

    /**
     * Constructor that uses the model.
     * 
     * @param game
     *            The reference to the model
     */
    public GameController(Game game, Parser parser) {
	this.game = game;
	this.parser = parser;
    }

    /**
     * Abstract method that runs the game. This method is different whether the
     * application runs the game on console or on a Swing Window;
     */

    /**
     * Registers a GameObserver to the model.
     * 
     * @param gameObserver
     *            The observer that wants to be registered
     */
    public void registerGameObserver(GameObserver gameObserver) {
	game.addGameObserver(gameObserver);
    }

    /**
     * Registers a MapObserver to the model.
     * 
     * @param mapObserver
     *            The observer that wants to be registered.
     */
    public void registerMapObserver(MapObserver mapObserver) {
	game.addMapObserver(mapObserver);

    }

    /**
     * Registers a PlayerObserver to the model.
     * 
     * @param playerObserver
     *            The observer that wants to be registered
     */
    public void registerPlayerObserver(PlayerObserver playerObserver) {

	game.addPlayerObserver(playerObserver);
    }

    /**
     * Abstract method that runs the game. This method is different whether the
     * application runs the game on console or on a Swing Window
     */
    public abstract void runGame();

}
