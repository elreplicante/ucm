package tp.pr5.testprofesor.items;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.pr5.items.Food;
import tp.pr5.Player;
import tp.pr5.Room;


public class FoodTest {

	Food testItem;
	Player testPlayer;
	Room testRoom;
	int inc;
	
	@Before
	public void setUp() throws Exception {
		inc = 10;
		testItem = new Food("itemID", "item desc", inc);
		testPlayer = new Player();
		testRoom = new Room(false, "room desc");		
	}

	@Test
	public void testUse() {
		int health = testPlayer.getHealth();
		if (testItem.use(testPlayer, testRoom))
			assertEquals("ERROR: use method from a Food object is not working properly", health+inc, testPlayer.getHealth());
		else
			fail("ERROR: use method is not working properly because a Food (created by default) could be used at least once");
	}
	
	@Test
	public void testUseOnce() {
		assertTrue("ERROR: A Food created by default could be used at least once", testItem.use(testPlayer, testRoom));
		assertFalse("ERROR: A Food created by default cannot be used more than once", testItem.canBeUsed());
		assertFalse("ERROR: A Food created by default cannot be used more than once", testItem.use(testPlayer, testRoom));

	}
	
	
	@Test
	public void testMultipleUse() {
		int times = 2;
		testItem = new Food("itemID", "item desc", inc, times);
		assertTrue("ERROR: A Food created for multiple uses (2) could be used at least once", testItem.use(testPlayer, testRoom));
		assertTrue("ERROR: A Food created for multiple uses (2) could be used more than once", testItem.use(testPlayer, testRoom));
		assertFalse("ERROR: A Food created for multiple uses (2) cannot be used more than the times defined during its construction", testItem.canBeUsed());
		assertFalse("ERROR: A Food created for multiple uses (2) could be used more than once", testItem.use(testPlayer, testRoom));

	}	

}
