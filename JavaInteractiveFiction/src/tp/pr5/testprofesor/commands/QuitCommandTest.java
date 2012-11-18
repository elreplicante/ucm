package tp.pr5.testprofesor.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.pr5.Directions;
import tp.pr5.commands.Command;
import tp.pr5.commands.GoCommand;
import tp.pr5.commands.QuitCommand;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.testprofesor.mockobjects.MockGame;
import tp.pr5.testprofesor.mockobjects.MockGameObserver;
import tp.pr5.testprofesor.mockobjects.MockMap;

public class QuitCommandTest {
	private MockGame game;
	private Command testCommand;
	
	
	@Before
	public void setUp() throws Exception {
		game = new MockGame(new MockMap(null));	
		testCommand = new QuitCommand();
		
	}

	@Test
	public void testParseFailWrongVerb() {
		try {
			testCommand.parse("qit");
			fail("ERROR: parse does not throw the exception with a wrong command");
		} catch (WrongCommandFormatException e) {
		}
	}
	
	@Test
	public void testParseCorrect() {
		try {
			testCommand = testCommand.parse("salir");
			testCommand = testCommand.parse("quit");

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}

	@Test
	public void testExecute() {
		try {
			testCommand = testCommand.parse("quit");
			MockGameObserver go = new MockGameObserver();
			game.addGameObserver(go);
			game.executeCommand(testCommand);
			assertTrue("ERROR: The command does not request to finish the game", go.quit());

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}

	@Test
	public void testGetHelp() {
		String help = testCommand.getHelp();
		help.toUpperCase();
		assertTrue("ERROR: getHelp returns a description which does not contain the word QUIT", help.contains("QUIT"));
		assertTrue("ERROR: getHelp returns a description which does not contain the word SALIR", help.contains("SALIR"));
	}

}
