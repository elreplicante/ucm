package tp.pr5.testprofesor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.pr5.Player;
import tp.pr5.testprofesor.mockobjects.MockItem;

public class PlayerTest {
	
	private MockItem itemTest;
	private String itemId;
	private String itemDescription;
	private Player playerTest;
	
	@Before
	public void setUp() throws Exception {
		itemId ="testId";
		itemDescription = "no description";
		itemTest = new MockItem(itemId, itemDescription);
		playerTest = new Player();
	}

	@Test
	public void testDefaultValues() {
		assertTrue("ERROR: Player starts with 100 health points", 100==playerTest.getHealth());
		assertTrue("ERROR: Player starts with a score of 0 points", 0==playerTest.getPoints());
	}
	
	@Test
	public void testGetItem() {
		assertNull("ERROR: When the inventory does not contain an item, getItem returns null",playerTest.getItem(itemId));
	}
	
	@Test
	public void testAddItem() {
		assertTrue("ERROR: addItem adds a new an valid item but it returns false", playerTest.addItem(itemTest));
		assertFalse("ERROR: addItem tries to add the same item again but it returns true", playerTest.addItem(itemTest));
		assertFalse("ERROR: addItem tries to add the same item again (the id is non case-sensitive) but it returns true", playerTest.addItem(new MockItem(itemId.toUpperCase(),itemDescription)));
	}

	@Test
	public void testRemoveItem() {
		assertFalse("ERROR: removeItem tries to remove an item from an empty inventory but it returns true", playerTest.removeItem(itemId));
		if(playerTest.addItem(itemTest)) {
			assertTrue("ERROR: removeItem tries to remove an item previously added but it returns false", playerTest.removeItem(itemId));
			assertTrue("ERROR: removeItem does not work properly. Test tries to add an item" +
					"previously removed but it returns false", playerTest.addItem(itemTest));
		}
		else 
			fail("ERROR: addItem is not working properly. Check addItem method before executing this test again");
	}

	@Test
	public void testAddPoints() {
		int points = playerTest.getPoints();
		int inc = 5;
		playerTest.addPoints(inc);
		assertEquals("ERROR: addPoints is not adding correctly positive points", points+inc, playerTest.getPoints());
		points = playerTest.getPoints();
		inc = -5;
		playerTest.addPoints(inc);
		assertEquals("ERROR: addPoints is not adding correctly negative points", points+inc, playerTest.getPoints());
	}


	@Test
	public void testAddHealth() {
		int health = playerTest.getHealth();
		int inc = 5;
		playerTest.addHealth(inc);
		assertEquals("ERROR: addHealth is not adding correctly positive health", health+inc, playerTest.getHealth());
		health = playerTest.getHealth();
		inc = -5;
		playerTest.addHealth(inc);
		assertEquals("ERROR: addHealth is not adding correctly negative health", health+inc, playerTest.getHealth());
	}	
	
	@Test
	public void testDead() {
		assertFalse("ERROR: Player health is "+playerTest.getHealth()+" but dead method returns true", playerTest.dead());
		int health = playerTest.getHealth();
		playerTest.addHealth(-health);
		assertTrue("ERROR: Player should be dead after removing exactly her health but dead method returns false", playerTest.dead());
		playerTest = new Player();
		health = playerTest.getHealth();
		health+=10;
		playerTest.addHealth(-health);
		assertTrue("ERROR: Player should be dead after removing more than her health but dead method returns false", playerTest.dead());
		
	}

//	@Test
//	public void testShowItems() {
//		assertTrue("ERROR: Player inventory is empty but showItems does not show the correct message", playerTest.showItems().contains("You are poor"));
//		playerTest.addItem(itemTest);
//		assertTrue("ERROR: Player inventory contains an items but showItems does not show the item description", playerTest.showItems().contains(itemTest.toString()));
//	}

//	@Test
//	public void testLooseLive() {
//		int health = playerTest.getHealth();
//		playerTest.looseLive();
//		assertEquals("ERROR: looseLive must remove 5 health points", health-5, playerTest.getHealth());
//	}

}
