package tp.pr5.testprofesor.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.pr5.Directions;
import tp.pr5.Door;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Room;
import tp.pr5.commands.Command;
import tp.pr5.commands.LookCommand;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.observers.GameObserver;
import tp.pr5.testprofesor.mockobjects.MockGame;
import tp.pr5.testprofesor.mockobjects.MockGameObserver;
import tp.pr5.testprofesor.mockobjects.MockItem;
import tp.pr5.testprofesor.mockobjects.MockMap;
import tp.pr5.testprofesor.mockobjects.MockPlayerObserver;

public class LookCommandTest {
	
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
		testCommand = new LookCommand();
		playerTest = game.getPlayer();
		go = new MockGameObserver();
		game.addGameObserver(go);
	}

	@Test
	public void testParseFailWrongVerb() {
		try {
			testCommand.parse("lok");
			fail("ERROR: parse does not throw the exception with a wrong command");
		} catch (WrongCommandFormatException e) {
		}
	}
	
	@Test
	public void testParseCorrectNoParameter() {
		try {
			testCommand = testCommand.parse("look");
			testCommand = testCommand.parse("mira");

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}

	
	@Test
	public void testParseCorrectWithParameter() {
		try {
			testCommand = testCommand.parse("look "+itemId);
			testCommand = testCommand.parse("mira "+itemId);

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}
	
	@Test
	public void testExecuteNoItemInInventory(){
		try {
			testCommand = testCommand.parse("look "+itemId);
			game.executeCommand(testCommand);
			assertTrue("ERROR: execute does not notify an error when trying to look an item that does not exist in the player inventory", go.error());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}				
	}
	
	@Test
	public void testExecuteCorrect(){
		try {
			MockPlayerObserver po = new MockPlayerObserver();
			playerTest.addItem(new MockItem(itemId, "desc"));
			playerTest.addObserver(po);
			testCommand = testCommand.parse("look "+itemId);
			game.executeCommand(testCommand);
			assertTrue("ERROR: The game does not notify that the player requests to look an item", po.look());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}				
	}

	@Test
	public void testGetHelp() {
		String help = testCommand.getHelp();
		help.toUpperCase();
		assertTrue("ERROR: getHelp returns a description which does not contain the word LOOK", help.contains("LOOK"));
		assertTrue("ERROR: getHelp returns a description which does not contain the word MIRA", help.contains("MIRA"));

	}

}
