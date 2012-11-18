package tp.pr5.testprofesor.commands;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import tp.pr5.Door;
import tp.pr5.Directions;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Room;
import tp.pr5.commands.Command;
import tp.pr5.commands.GoCommand;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.testprofesor.mockobjects.MockGame;
import tp.pr5.testprofesor.mockobjects.MockGameObserver;
import tp.pr5.testprofesor.mockobjects.MockMap;
import tp.pr5.testprofesor.mockobjects.MockMapObserver;
import tp.pr5.testprofesor.mockobjects.MockPlayerObserver;

public class GoCommandTest {
	private MockGame game;
	private Map map;
	private Room sourceRoom;
	private Room targetRoom;
	private Command testCommand;
	private MockGameObserver go;
	private MockMapObserver mo;
	
	
	@Before
	public void setUp() throws Exception {
		sourceRoom = new Room(false, "Source");
		targetRoom = new Room(false, "Target");
		map = new MockMap(sourceRoom);
		map.addDoor(sourceRoom, Directions.NORTH, targetRoom);
		game = new MockGame(map);
		go = new MockGameObserver();
		game.addGameObserver(go);
		mo = new MockMapObserver();
		map.addObserver(mo);
		testCommand = new GoCommand();
	}

	@Test
	public void testParseFailWrongVerb() {
		try {
			testCommand.parse("move north");
			fail("ERROR: parse does not throw the exception with a wrong command");
		} catch (WrongCommandFormatException e) {
		}
	}
	
	@Test
	public void testParseFailWrongDirection() {
		try {
			testCommand.parse("go norte");
			fail("ERROR: parse does not throw the exception with a wrong command");
		} catch (WrongCommandFormatException e) {
		}
	}
	
	@Test
	public void testParseCorrect() {
		try {
			testCommand = testCommand.parse("go east");
			testCommand = testCommand.parse("ir east");

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}

	@Test
	public void testExecuteNoDoor() {
		Player playerTest = game.getPlayer();
		MockPlayerObserver po = new MockPlayerObserver();
		playerTest.addObserver(po);
		int health = po.health();
		try {
			testCommand = testCommand.parse("go east");
			game.executeCommand(testCommand);
			assertTrue("ERROR: execute does not notify an error when trying to move in a direction without a door", go.error());
			assertEquals("ERROR: Player health has changed when trying to move in a direction with a closed door",health, po.health());
			assertFalse("ERROR: Map has notified that the player enters a room after trying to move in a direction without a door", mo.enter());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}
	
	@Test
	public void testExecuteClosedDoor() {
		Player playerTest = game.getPlayer();
		MockPlayerObserver po = new MockPlayerObserver();
		playerTest.addObserver(po);
		int health = po.health();
		try {
			testCommand = testCommand.parse("go north");
			game.executeCommand(testCommand);
			assertTrue("ERROR: execute does not notify an error when trying to move in a direction with a closed door", go.error());
			assertEquals("ERROR: Player health has changed when trying to move in a direction with a closed door",health, po.health());
			assertFalse("ERROR: Map has notified that the player enters a room after trying to move in a direction with a closed door", mo.enter());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}
	
	@Test
	public void testExecuteOpenDoor() {
		Player playerTest = game.getPlayer();
		int health = playerTest.getHealth();		
		MockPlayerObserver po = new MockPlayerObserver();
		playerTest.addObserver(po);
		// Open the door
		Door retDoor = map.getDoor(sourceRoom, Directions.NORTH);
		retDoor.open();
		try {
			testCommand = testCommand.parse("go north");
			game.executeCommand(testCommand);
			assertEquals("ERROR: execute changes the room through an open door but the player " +
					"arrives to a room that is not the targetRoom", targetRoom, map.getCurrentRoom());
			assertFalse("ERROR: Player arrives at a non-exit room but the command requests to finish the game", game.isQuitRequested());
			assertTrue("ERROR: The command moves the player but it does not consume health", playerTest.getHealth()<health);
			assertTrue("ERROR: Player health has not changed but the player has moved", po.health()<health);
			assertTrue("ERROR: Map does not notified that the player enters a room after moving the player to another room", mo.enter());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}
	
	@Test
	public void testExecuteArrivesExit() {
		
		// Now the target room is an exit room
		targetRoom = new Room(true, "Target");
		map = new MockMap(sourceRoom);
		map.addDoor(sourceRoom, Directions.NORTH, targetRoom);
		game = new MockGame(map);
		game.addGameObserver(go);
		game.addMapObserver(mo);
		// Open the door
		Door retDoor = map.getDoor(sourceRoom, Directions.NORTH);
		retDoor.open();
		try {
			testCommand = testCommand.parse("go north");
			game.executeCommand(testCommand);
			assertEquals("ERROR: execute changes the room through an open door but the player " +
					"arrives to a room that is not the targetRoom", targetRoom, map.getCurrentRoom());
			assertTrue("ERROR: Map does not notified that the player enters a room after moving the player to another room", mo.enter());
			assertTrue("ERROR: Player arrives at an exit room but the game does not request to finish the game", go.gameOver());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
		//catch (CommandExecutionException e) {
	//		fail("ERROR: execute throws an exception when trying to move in a direction with an open door");
		//}
	}

	@Test
	public void testGetHelp() {
		String help = testCommand.getHelp();
		help.toUpperCase();
		assertTrue("ERROR: getHelp returns a description which does not contain the word GO", help.contains("GO"));
		assertTrue("ERROR: getHelp returns a description which does not contain the word IR", help.contains("IR"));
	}

}