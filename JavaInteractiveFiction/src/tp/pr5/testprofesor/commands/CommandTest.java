package tp.pr5.testprofesor.commands;

import static org.junit.Assert.*;

import org.junit.Test;


public class CommandTest  {
	@Test
	public void testEmpty(){
	}

/*	@Test
	public void testIsValid() {
		Command c = new Command();
		assertFalse("ERROR: A command created with default constructor is not a valid command",
				c.isValid());
		
		c = new Command(VerbCommands.GO);
		assertFalse("ERROR: A GO command without direction is not a valid command",
				c.isValid());
		
		c = new Command(VerbCommands.QUIT);
		assertTrue("ERROR: A command created with a verb (QUIT) is a valid command",
				c.isValid());
		
		c = new Command(VerbCommands.EXAMINE);
		assertTrue("ERROR: A command created with a verb (EXAMINE) is a valid command",
				c.isValid());
		
		c = new Command(VerbCommands.LOOK);
		assertTrue("ERROR: A command created with a verb (LOOK) is a valid command",
				c.isValid());
		
		c = new Command(VerbCommands.LOOK, "myId");
		assertTrue("ERROR: A command created with a verb and a string (LOOK myId) is a valid command",
				c.isValid());
		
		c = new Command(VerbCommands.USE);
		assertFalse("ERROR: A command created with only  USE verb is not a valid command",
				c.isValid());
		
		c = new Command(VerbCommands.PICK);
		assertFalse("ERROR: A command created with only  PICK verb is not a valid command",
				c.isValid());

		
		c = new Command(VerbCommands.USE, "");
		assertFalse("ERROR: A command created with USE and an empty string (USE \"\") is not a valid command",
				c.isValid());
		
		c = new Command(VerbCommands.PICK, "");
		assertFalse("ERROR: A command created with PICK and an empty string (PICK \"\") is not a valid command",
				c.isValid());	
		
		
		c = new Command(VerbCommands.USE, "myId");
		assertTrue("ERROR: A command created with a verb and a string (USE myId) is a valid command",
				c.isValid());
		
		c = new Command(VerbCommands.PICK, "myId");
		assertTrue("ERROR: A command created with a verb and a string (PICK myId) is a valid command",
				c.isValid());
		
	}

	@Test
	public void testGetVerb() {
		Command c = new Command(VerbCommands.GO);
		assertEquals("ERROR: A command created with the verb GO does not return the correct verb (GO)",
				VerbCommands.GO,
				c.getVerb());
	}

	@Test
	public void testGetDirection(){
		Command c = new Command(VerbCommands.GO, Directions.EAST);
		assertEquals("ERROR: A command configured with a direction should return the correct direction when executing getDirection method",  Directions.EAST, c.getDirection());
	}*/

}
