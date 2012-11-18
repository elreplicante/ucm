package tp.pr5.testprofesor.commands;


import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import tp.pr5.items.Item;
import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Room;
import tp.pr5.commands.Command;
import tp.pr5.commands.PickCommand;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.testprofesor.mockobjects.MockGame;
import tp.pr5.testprofesor.mockobjects.MockGameObserver;
import tp.pr5.testprofesor.mockobjects.MockItem;
import tp.pr5.testprofesor.mockobjects.MockMap;
import tp.pr5.testprofesor.mockobjects.MockPlayerObserver;

public class PickCommandTest {

	private MockGame game;
	private Room sourceRoom;
	private Map map;
	private String itemId;
	private Player playerTest;
	private Command testCommand;
	private Item itemTest;
	private MockGameObserver go;
	
	@Before
	public void setUp() throws Exception {
		sourceRoom = new Room(false, "Source");
		itemId = "theItemId";
		itemTest = new MockItem(itemId, "itemDescription");
		map = new MockMap(sourceRoom);
		game = new MockGame(map);	
		testCommand = new PickCommand();
		playerTest = game.getPlayer();
		go = new MockGameObserver();
		game.addGameObserver(go);
	}

	@Test
	public void testParseFailWrongVerb() {
		try {
			testCommand.parse("pik item");
			fail("ERROR: parse does not throw the exception with a wrong command");
		} catch (WrongCommandFormatException e) {
		}
	}
	
	@Test
	public void testParseNoParameter() {
		try {
			testCommand = testCommand.parse("pick");
			fail("ERROR: parse does not throw the exception with a wrong command");

		} catch (WrongCommandFormatException e) {
		}
	}

	
	@Test
	public void testParseCorrect() {
		try {
			testCommand = testCommand.parse("pick "+itemId);
			testCommand = testCommand.parse("coger "+itemId);

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}
	
	@Test
	public void testExecuteNoItemInRoom(){
		try {
			testCommand = testCommand.parse("pick "+itemId);
			game.executeCommand(testCommand);
			assertTrue("ERROR: execute does not notify an error when trying to pick an item that does not exist in the room", go.error());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}				
	}
	
	@Test
	public void testExecuteItemAlreadyInInventory(){
		try {
			sourceRoom.addItem(itemTest);
			playerTest.addItem(new MockItem(itemId, "desc"));
			testCommand = testCommand.parse("pick "+itemId);
			game.executeCommand(testCommand);
			assertTrue("ERROR: execute does not notify an error when trying to pick an itemthat already exists in the inventory", go.error());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}				
	}
	
	@Test
	public void testExecuteCorrect(){
		try {
			sourceRoom.addItem(itemTest);
			testCommand = testCommand.parse("pick "+itemId);
			MockPlayerObserver po = new MockPlayerObserver();
			game.addPlayerObserver(po);
			game.executeCommand(testCommand);
			assertNotNull("ERROR: Player inventory does not contain the item", playerTest.getItem(itemId));
			assertFalse("ERROR: Room already contains the item", sourceRoom.existsItem(itemId));
			assertTrue("ERROR: The player does not notify that the inventory has been updated",po.updated());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}				
	}
	@Test
	public void testGetHelp() {
		String help = testCommand.getHelp();
		help.toUpperCase();
		assertTrue("ERROR: getHelp returns a description which does not contain the word PICK", help.contains("PICK"));
		assertTrue("ERROR: getHelp returns a description which does not contain the word COGER", help.contains("COGER"));
	}

}
