package tp.pr5.testprofesor.commands;


import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import tp.pr5.Map;
import tp.pr5.Player;
import tp.pr5.Room;
import tp.pr5.commands.Command;
import tp.pr5.commands.DropCommand;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.items.Item;
import tp.pr5.testprofesor.mockobjects.MockGame;
import tp.pr5.testprofesor.mockobjects.MockGameObserver;
import tp.pr5.testprofesor.mockobjects.MockItem;
import tp.pr5.testprofesor.mockobjects.MockMap;
import tp.pr5.testprofesor.mockobjects.MockPlayerObserver;

public class DropCommandTest {

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
		testCommand = new DropCommand();
		playerTest = game.getPlayer();
		go = new MockGameObserver();
		game.addGameObserver(go);
	}

	@Test
	public void testParseFailWrongVerb() {
		try {
			testCommand.parse("drrop item");
			fail("ERROR: parse does not throw the exception with a wrong command");
		} catch (WrongCommandFormatException e) {
		}
	}
	
	@Test
	public void testParseNoParameter() {
		try {
			testCommand = testCommand.parse("drop");
			fail("ERROR: parse does not throw the exception with a wrong command");

		} catch (WrongCommandFormatException e) {
		}
	}

	
	@Test
	public void testParseCorrect() {
		try {
			testCommand = testCommand.parse("drop "+itemId);
			testCommand = testCommand.parse("soltar "+itemId);

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}
	
	@Test
	public void testExecuteNoItemInInventory(){
		try {
			testCommand = testCommand.parse("drop "+itemId);
			game.executeCommand(testCommand);
			assertTrue("ERROR: execute does not notify an error when trying to drop an item that does not exist in the player inventory", go.error());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}				
	}
	
	@Test
	public void testExecuteItemAlreadyInRoom(){
		try {
			sourceRoom.addItem(itemTest);
			playerTest.addItem(new MockItem(itemId, "desc"));
			testCommand = testCommand.parse("drop "+itemId);
			game.executeCommand(testCommand);
			assertTrue("ERROR: execute does not notify an error when trying to drop an item that already exists in the room", go.error());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}				
	}
	
	@Test
	public void testExecuteCorrect(){
		try {
			playerTest.addItem(itemTest);
			testCommand = testCommand.parse("drop "+itemId);
			MockPlayerObserver po = new MockPlayerObserver();
			game.addPlayerObserver(po);
			game.executeCommand(testCommand);
			assertTrue("ERROR: Room does not contain the item", sourceRoom.existsItem(itemId));
			assertNull("ERROR: Player inventory already contains", playerTest.getItem(itemId));
			assertTrue("ERROR: The player does not notify that the inventory has been updated",po.updated());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}				
	}
	@Test
	public void testGetHelp() {
		String help = testCommand.getHelp();
		help.toUpperCase();
		assertTrue("ERROR: getHelp returns a description which does not contain the word DROP", help.contains("DROP"));
		assertTrue("ERROR: getHelp returns a description which does not contain the word SOLTAR", help.contains("SOLTAR"));
	}

}
