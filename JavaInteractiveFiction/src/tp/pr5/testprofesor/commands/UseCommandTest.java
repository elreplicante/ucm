package tp.pr5.testprofesor.commands;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import tp.pr5.Directions;
import tp.pr5.Door;
import tp.pr5.items.*;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Room;
import tp.pr5.commands.Command;
import tp.pr5.commands.UseCommand;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.observers.GameObserver;
import tp.pr5.testprofesor.mockobjects.MockGame;
import tp.pr5.testprofesor.mockobjects.MockGameObserver;
import tp.pr5.testprofesor.mockobjects.MockMap;
import tp.pr5.testprofesor.mockobjects.MockPlayerObserver;

public class UseCommandTest {

	private MockGame game;
	private Room sourceRoom;
	private Map map;
	private String itemId;
	private Player playerTest;
	private Command testCommand;
	private MockGameObserver go;
	
	@Before
	public void setUp() throws Exception {
		sourceRoom = new Room(false, "Source");
		itemId = "theItemId";
		map = new MockMap(sourceRoom);
		game = new MockGame(map);	
		testCommand = new UseCommand();
		playerTest = game.getPlayer();
		go = new MockGameObserver();
		game.addGameObserver(go);
	}

	@Test
	public void testParseFailWrongVerb() {
		try {
			testCommand.parse("usse item");
			fail("ERROR: parse does not throw the exception with a wrong command");
		} catch (WrongCommandFormatException e) {
		}
	}
	
	@Test
	public void testParseNoParameter() {
		try {
			testCommand = testCommand.parse("use");
			fail("ERROR: parse does not throw the exception with a wrong command");

		} catch (WrongCommandFormatException e) {
		}
	}

	
	@Test
	public void testParseCorrect() {
		try {
			testCommand = testCommand.parse("use "+itemId);
			testCommand = testCommand.parse("usar "+itemId);

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}
	
	@Test
	public void testExecuteNoItemInInventory(){
		try {
			testCommand = testCommand.parse("use "+itemId);
			game.executeCommand(testCommand);
			assertTrue("ERROR: execute does not notify an error when trying to use an item that does not exist in the player inventory", go.error());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}				
	}
	
	@Test
	public void testExecuteUseValuable(){
		try {
			int points = playerTest.getPoints();
			int inc = 10;
			Valuable testValuable = new Valuable(itemId, "valuable", inc);
			playerTest.addItem(testValuable);			
			testCommand = testCommand.parse("use "+itemId);
			MockPlayerObserver po = new MockPlayerObserver();
			playerTest.addObserver(po);
			game.executeCommand(testCommand);
			assertEquals("ERROR: Player points has not been increased after using Valuable", points+inc, playerTest.getPoints());
			assertEquals("ERROR: Player points has not been properly notified", points+inc, po.score());
			assertTrue("ERROR: Player does not notify that an item has been used", po.used());
			assertNull("ERROR: The valuable has not been removed from player inventory", playerTest.getItem(itemId));
			assertTrue("ERROR: Player does not notify that the item used is empty", po.empty());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}				
	}
	
	@Test
	public void testExecuteUseFoodOnce(){
		try {
			int health = playerTest.getHealth();
			int inc = 10;
			Food testFood = new Food(itemId, "Food for only one use", inc);
			playerTest.addItem(testFood);
			testCommand = testCommand.parse("use "+itemId);
			MockPlayerObserver po = new MockPlayerObserver();
			playerTest.addObserver(po);
			game.executeCommand(testCommand);
			assertEquals("ERROR: Player health has not been increased after using Food", health+inc, playerTest.getHealth());
			assertEquals("ERROR: Player health has not been properly notified", health+inc, po.health());
			assertTrue("ERROR: Player does not notify that an item has been used", po.used());
			assertNull("ERROR: The food has not been removed from player inventory", playerTest.getItem(itemId));
			assertTrue("ERROR: Player does not notify that the item used is empty", po.empty());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}				
	}
	
	@Test
	public void testExecuteUseFoodMoreThanOnce(){
		try {
			int health = playerTest.getHealth();
			int inc = 10;
			// A food that can be used twice
			Food testFood = new Food(itemId, "Food for using twice", inc, 2);
			playerTest.addItem(testFood);
			testCommand = testCommand.parse("use "+itemId);
			MockPlayerObserver po = new MockPlayerObserver();
			playerTest.addObserver(po);
			game.executeCommand(testCommand);
			assertEquals("ERROR: Player health has not been increased after using Food", health+inc, playerTest.getHealth());
			assertEquals("ERROR: Player health has not been properly notified", health+inc, po.health());
			assertNotNull("ERROR: The food has been removed from player inventory but it can be used one more time", playerTest.getItem(itemId));
			assertTrue("ERROR: Player does not notify that an item has been used", po.used());
			assertFalse("ERROR: Player has notified that the item used is empty but the item can be used more than once", po.empty());
			// Use it again
			testCommand = testCommand.parse("use "+itemId);
			game.executeCommand(testCommand);
			assertEquals("ERROR: Player health has not been increased after using Food", health+2*inc, playerTest.getHealth());
			assertEquals("ERROR: Player health has not been properly notified", health+2*inc, po.health());
			assertNull("ERROR: The food has not been removed from player inventory", playerTest.getItem(itemId));
			assertTrue("ERROR: Player does not notify that an item has been used", po.used());
			assertTrue("ERROR: Player does not notify that the item used is empty", po.empty());
		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}				
	}

	@Test
	public void testExecuteUseKeyCorrect(){
		try {
			Room target = new Room(false, "target");
			map.addDoor(sourceRoom, Directions.NORTH, target);
			Door retDoor = map.getDoor(sourceRoom, Directions.NORTH);
			Key testKey = new Key(itemId, "The key", retDoor);
			playerTest.addItem(testKey);
			testCommand = testCommand.parse("use "+itemId);
			MockPlayerObserver po = new MockPlayerObserver();
			playerTest.addObserver(po);
			game.executeCommand(testCommand);
			assertTrue("ERROR: Using the key does not open the door", retDoor.isOpen());
			assertNotNull("ERROR: The key has been removed from player inventory", playerTest.getItem(itemId));
			assertTrue("ERROR: Player does not notify that an item has been used", po.used());
			assertFalse("ERROR: Player has notified that the item used is empty but the item can be used more than once", po.empty());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}				
	}
	
	@Test
	public void testExecuteUseKeyWrongPlace(){
		try {
			Room target = new Room(false, "target");
			map = new MockMap(target);
			map.addDoor(sourceRoom, Directions.NORTH, target);
			game = new MockGame(map);
			game.addGameObserver(go);
			MockPlayerObserver po = new MockPlayerObserver();
			playerTest.addObserver(po);
			Door retDoor = map.getDoor(sourceRoom, Directions.NORTH);
			Key testKey = new Key(itemId, "The key", retDoor);
			playerTest.addItem(testKey);
			testCommand = testCommand.parse("use "+itemId);
			game.executeCommand(testCommand);
			assertTrue("ERROR: execute does not notify an error when trying to use a key that cannot open the door from the current position (targetRoom)", go.error());
			assertFalse("ERROR: Player notifies that an item has been used but it cannot be used", po.used());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}				
	}
	
	@Test
	public void testGetHelp() {
		String help = testCommand.getHelp();
		help.toUpperCase();
		assertTrue("ERROR: getHelp returns a description which does not contain the word USE", help.contains("USE"));
		assertTrue("ERROR: getHelp returns a description which does not contain the word USAR", help.contains("USAR"));
	}

}
