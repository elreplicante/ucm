package tp.pr5.testprofesor.items;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.pr5.Player;
import tp.pr5.Room;
import tp.pr5.items.Valuable;

public class ValuableTest {
	Player testPlayer;
	Room testRoom;
	@Before
	public void setUp() throws Exception {
		testPlayer = new Player();
		testRoom = new Room(false, "room desc");		
	}
	
	@Test
	public void testUse() {
		int score = testPlayer.getPoints();
		int inc = 10;
		Valuable vItem = new Valuable("itemId", "Item desc", inc);
		if (vItem.use(testPlayer, testRoom))
			assertEquals("ERROR: use method from a Valuable object is not working properly", score+inc, testPlayer.getPoints());
		else
			fail("ERROR: use method is not working properly because a Valuable object could be used at least once");
	}

	@Test
	public void testUseMoreThanOnce() {
		Player playerTest = new Player();
		Room r = new Room(false, "Room desc");
		int inc = 10;
		Valuable vItem = new Valuable("itemId", "Item desc", inc);
		if (vItem.use(playerTest, r)){
			assertFalse("ERROR: a Valuable Item cannot be used more than once", vItem.canBeUsed());
		}			
		else
			fail("ERROR: use method is not working properly because a Valuable object could be used at least once");
	}
	
	@Test
	public void testMultipleUse() {
		int inc  = 10;
		Valuable vItem = new Valuable("itemId", "Item desc", inc);
		assertTrue("ERROR: A valuable created could be used at least once", vItem.use(testPlayer, testRoom));
		assertFalse("ERROR: A valuable cannot be used more than once", vItem.use(testPlayer, testRoom));
		assertFalse("ERROR: A valuable cannot be used more than once", vItem.canBeUsed());
		assertFalse("ERROR: A valuable cannot be used more than once", vItem.use(testPlayer, testRoom));

	}	
}
