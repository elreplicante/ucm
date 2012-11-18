package tp.pr5.testprofesor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.pr5.commands.Command;
import tp.pr5.commands.DropCommand;
import tp.pr5.commands.ExamineCommand;
import tp.pr5.commands.GoCommand;
import tp.pr5.commands.HelpCommand;
import tp.pr5.commands.LookCommand;
import tp.pr5.commands.PickCommand;
import tp.pr5.commands.QuitCommand;
import tp.pr5.commands.UseCommand;
import tp.pr5.commands.exceptions.WrongCommandFormatException;
import tp.pr5.parsers.Parser;

/*import tp.pr5.Command;
import tp.pr5.Directions;
import tp.pr5.Parser;
import tp.pr5.VerbCommands;
*/
public class ParserTest {
	
	private String itemId = "testId";
	
	@Before
	public void setUp(){
		
	}
	

	@Test
	public void testparseCommandWrongCommand() {
		try {
			Parser.parseCommand("MOVE");
			fail("ERROR: parseCommand does not throw the exception with a wrong command");
		} 
		catch (WrongCommandFormatException e) {
		}
		catch (Exception e) {
			fail("ERROR: parseCommand does not throw a WrongCommandFormatException");
		}
	}

	@Test
	public void testParseCommandHelp() {
		Command c;
		try {
			c = Parser.parseCommand("help");
			assertEquals("ERROR: String \"help\" does not return a HelpCommand", HelpCommand.class, c.getClass());
			c = Parser.parseCommand("ayuda");
			assertEquals("ERROR: String \"ayuda\" does not return a HelpCommand", HelpCommand.class, c.getClass());
		} catch (WrongCommandFormatException e) {
			fail("ERROR: a correct string throws a WrongCommandFormatException");
		}
	}

	@Test
	public void testParseCommandQuit() {
		Command c;
		try {
			c = Parser.parseCommand("quit");
			assertEquals("ERROR: String \"quit\" does not return a QuitCommand", QuitCommand.class, c.getClass());
			c = Parser.parseCommand("salir");
			assertEquals("ERROR: String \"salir\" does not return a QuitCommand", QuitCommand.class, c.getClass());
		} catch (WrongCommandFormatException e) {
			fail("ERROR: a correct string throws a WrongCommandFormatException");
		}
	}	
	@Test
	public void testParseCommandGoWithoutDirection() {
			Command c;
		try {
			c = Parser.parseCommand("go north");
			assertEquals("ERROR: String \"go north\" does not return a GoCommand", GoCommand.class, c.getClass());
			c = Parser.parseCommand("ir north");
			assertEquals("ERROR: String \"ir north\" does not return a GoCommand", GoCommand.class, c.getClass());
		} catch (WrongCommandFormatException e) {
			fail("ERROR: a correct string throws a WrongCommandFormatException");
		}
	}

	
	@Test
	public void testParseCommandLookWithoutParam() {
		Command c;
		try {
			c = Parser.parseCommand("look");
			assertEquals("ERROR: String \"look\" does not return a LookCommand", LookCommand.class, c.getClass());
			c = Parser.parseCommand("mira");
			assertEquals("ERROR: String \"mira\" does not return a LookCommand", LookCommand.class, c.getClass());
		} catch (WrongCommandFormatException e) {
			fail("ERROR: a correct string throws a WrongCommandFormatException");
		}

	}

	@Test
	public void testParseCommandLookWithParam() {
		Command c;
		try {
			c = Parser.parseCommand("look "+itemId);
			assertEquals("ERROR: String \"look "+itemId+"\" does not return a LookCommand", LookCommand.class, c.getClass());
			c = Parser.parseCommand("mira "+itemId);
			assertEquals("ERROR: String \"mira "+itemId+"\" does not return a LookCommand", LookCommand.class, c.getClass());
		} catch (WrongCommandFormatException e) {
			fail("ERROR: a correct string throws a WrongCommandFormatException");
		}
	}

	@Test
	public void testParseCommandExamine() {
		Command c;
		try {
			c = Parser.parseCommand("examine");
			assertEquals("ERROR: String \"examine\" does not return a ExamineCommand", ExamineCommand.class, c.getClass());
			c = Parser.parseCommand("examinar");
			assertEquals("ERROR: String \"examinar\" does not return a ExamineCommand", ExamineCommand.class, c.getClass());
		} catch (WrongCommandFormatException e) {
			fail("ERROR: a correct string throws a WrongCommandFormatException");
		}
	}
	
	@Test
	public void testParseCommandPickWithoutId() {
		try {
			Parser.parseCommand("pick");
			fail("ERROR: parseCommand does not throw the exception with a wrong pick command (\"pick\" without id)");
		} 
		catch (WrongCommandFormatException e) {
		}
		catch (Exception e) {
			fail("ERROR: parseCommand does not throw a WrongCommandFormatException");
		}
	}
	
	@Test
	public void testParseCommandPickWithParam() {
		Command c;
		try {
			c = Parser.parseCommand("pick "+itemId);
			assertEquals("ERROR: String \"pick "+itemId+"\" does not return a PickCommand", PickCommand.class, c.getClass());
			c = Parser.parseCommand("coger "+itemId);
			assertEquals("ERROR: String \"coger "+itemId+"\" does not return a PickCommand", PickCommand.class, c.getClass());
		} catch (WrongCommandFormatException e) {
			fail("ERROR: a correct string throws a WrongCommandFormatException");
		}
	}
	
	@Test
	public void testParseCommandDropWithoutId() {
		try {
			Parser.parseCommand("drop");
			fail("ERROR: parseCommand does not throw the exception with a wrong drop command (\"drop\" without id)");
		} 
		catch (WrongCommandFormatException e) {
		}
		catch (Exception e) {
			fail("ERROR: parseCommand does not throw a WrongCommandFormatException");
		}
	}
	
	@Test
	public void testParseCommandDropWithParam() {
		Command c;
		try {
			c = Parser.parseCommand("drop "+itemId);
			assertEquals("ERROR: String \"drop "+itemId+"\" does not return a DropCommand", DropCommand.class, c.getClass());
			c = Parser.parseCommand("soltar "+itemId);
			assertEquals("ERROR: String \"soltar "+itemId+"\" does not return a DropCommand", DropCommand.class, c.getClass());
		} catch (WrongCommandFormatException e) {
			fail("ERROR: a correct string throws a WrongCommandFormatException");
		}
	}
	@Test
	public void testParseCommandUseWithoutId() {
		try {
			Parser.parseCommand("use");
			fail("ERROR: parseCommand does not throw the exception with a wrong use command string(\"use\" without id)");
		} 
		catch (WrongCommandFormatException e) {
		}
		catch (Exception e) {
			fail("ERROR: parseCommand does not throw a WrongCommandFormatException");
		}
	}
	
	@Test
	public void testParseCommandUseWithParam() {
		Command c;
		try {
			c = Parser.parseCommand("use "+itemId);
			assertEquals("ERROR: String \"use "+itemId+"\" does not return a UseCommand", UseCommand.class, c.getClass());
			c = Parser.parseCommand("usar "+itemId);
			assertEquals("ERROR: String \"usar "+itemId+"\" does not return a UseCommand", UseCommand.class, c.getClass());
		} catch (WrongCommandFormatException e) {
			fail("ERROR: a correct string throws a WrongCommandFormatException");
		}
	}
	
	@Test
	public void testHelpEnglish() {
		String help = Parser.getHelp();
		help.toUpperCase();
		assertTrue("ERROR: getHelp returns a description which does not contain information about command GO", help.contains("GO"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command HELP", help.contains("HELP"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command QUIT", help.contains("QUIT"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command PICK", help.contains("PICK"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command DROP", help.contains("DROP"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command LOOK", help.contains("LOOK"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command USE", help.contains("USE"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command EXAMINE", help.contains("EXAMINE"));
	}

	@Test
	public void testHelpSpanish() {
		String help = Parser.getHelp();
		help.toUpperCase();
		assertTrue("ERROR: getHelp returns a description which does not contain information about command IR", help.contains("IR"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command AYUDA", help.contains("AYUDA"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command SALIR", help.contains("SALIR"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command COGER", help.contains("COGER"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command SOLTAR", help.contains("SOLTAR"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command MIRA", help.contains("MIRA"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command USAR", help.contains("USAR"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command EXAMINAR", help.contains("EXAMINAR"));
	}
}
