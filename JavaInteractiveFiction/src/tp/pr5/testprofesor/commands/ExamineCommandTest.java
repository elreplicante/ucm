package tp.pr5.testprofesor.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.pr5.Directions;
import tp.pr5.Map;
import tp.pr5.Room;
import tp.pr5.commands.Command;
import tp.pr5.commands.ExamineCommand;
import tp.pr5.commands.GoCommand;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.testprofesor.mockobjects.MockGame;
import tp.pr5.testprofesor.mockobjects.MockMap;

public class ExamineCommandTest {
	private MockGame game;
	private Map map;
	private Room sourceRoom;
	private Command testCommand;
	private String sourceDescription;

	@Before
	public void setUp() throws Exception {
		sourceDescription = "Source";
		sourceRoom = new Room(false, sourceDescription);
		map = new MockMap(sourceRoom);
		game = new MockGame(map);	
		testCommand = new ExamineCommand();
	}
	
	@Test
	public void testParseFailWrongVerb() {
		try {
			testCommand.parse("exam");
			fail("ERROR: parse does not throw the exception with a wrong command");
		} catch (WrongCommandFormatException e) {
		}
	}

	@Test
	public void testParseCorrect() {
		try {
			testCommand = testCommand.parse("examine");
			testCommand = testCommand.parse("examinar");

		} catch (WrongCommandFormatException e) {
			fail("ERROR: parse throws an exception with a correct command");
		}
	}


	@Test
	public void testGetHelp() {
		String help = testCommand.getHelp();
		help.toUpperCase();
		assertTrue("ERROR: getHelp returns a description which does not contain the word EXAMINE", help.contains("EXAMINE"));
		assertTrue("ERROR: getHelp returns a description which does not contain the word EXAMINAR", help.contains("EXAMINAR"));
	}

}
