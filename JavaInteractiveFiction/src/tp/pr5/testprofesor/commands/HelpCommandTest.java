package tp.pr5.testprofesor.commands;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import tp.pr5.commands.Command;
import tp.pr5.commands.HelpCommand;
import tp.pr5.commands.exceptions.CommandExecutionException;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.testprofesor.mockobjects.MockGame;
import tp.pr5.testprofesor.mockobjects.MockMap;


public class HelpCommandTest {
	private MockGame game;
	private Command testCommand;
	
	@Before
	public void setUp() throws Exception {
		game = new MockGame(new MockMap(null));	
		testCommand = new HelpCommand();
	}

	@Test
	public void testParseFailWrongVerb() {
		try {
			testCommand.parse("hel");
			fail("ERROR: parse does not throw the exception with a wrong command");
		} catch (WrongCommandFormatException e) {
		}
	}
	
	@Test
	public void testParseCorrect() {
		try {
			testCommand = testCommand.parse("help");
			testCommand = testCommand.parse("ayuda");

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}

	@Test
	public void testGetHelp() {
		String help = testCommand.getHelp();
		help.toUpperCase();
		assertTrue("ERROR: getHelp returns a description which does not contain the word HELP", help.contains("HELP"));
		assertTrue("ERROR: getHelp returns a description which does not contain the word AYUDA", help.contains("AYUDA"));
	}

}
