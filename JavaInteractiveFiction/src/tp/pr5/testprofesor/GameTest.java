package tp.pr5.testprofesor;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

//import tp.pr2.Command;
import tp.pr5.Directions;
import tp.pr5.Door;
import tp.pr5.items.Food;
import tp.pr5.items.Item;
import tp.pr5.items.Key;
import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Room;
import tp.pr5.items.Valuable;

public class GameTest {

	@Test
	public void runGameWithNullMapTest() {
		try{
			Game g = new Game(null);
			//g.runGame();
		} catch (Exception e) {
			fail("ERROR: The game should control that the map can be a null pointer." +
			" In this case, the runGame should do nothing and the game finishes.");
		}

	}

	@Test	
	public void testNoGetMethods() {
		Method [] methods = Game.class.getDeclaredMethods();
		for (Method m:methods) {
			if(m.isAccessible()) {
				Class<?> retType = m.getReturnType();
				if (retType.equals(Player.class) || retType.equals(Map.class))
					fail ("ERROR: Public methods to access map and player are forbidden");
			}
		}
	}

}
