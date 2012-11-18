package tp.pr5.testprofesor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.pr5.items.Item;
import tp.pr5.testprofesor.mockobjects.MockItem;
import tp.pr5.Player;
import tp.pr5.Room;

public class RoomTest {
	
	private Room roomTest;
	private MockItem itemTest;
	private String itemId;
	private String roomDescription;

	@Before
	public void setUp() throws Exception {
		itemId ="testId";
		roomDescription = "room description";
		itemTest = new MockItem(itemId, "no description");
		roomTest = new Room(false, roomDescription, new MockItem[] {itemTest});   
	}
	
	@Test
	public void testRoomCreationWithArrayWithoutDuplicates(){
		Player testPlayer = new Player();
		assertTrue("ERROR: itemTest should exist in the room", roomTest.existsItem(itemId));
		if (roomTest.pickItem(testPlayer, itemId)){
			assertFalse("ERROR: itemTest should not exist in the room after picking it", roomTest.existsItem(itemId));
		}
		else
			fail("ERROR: pickItem is not working properly");

		roomTest = new Room(false, roomDescription, new Item[] {itemTest, itemTest});  

		roomTest = new Room(false, roomDescription, new Item[] {itemTest, new MockItem("anotherItem", "")});  

	}
	
	@Test
	public void testRoomCreationWithArrayWithDuplicates(){
		roomTest = new Room(false, roomDescription, new Item[] {itemTest, itemTest});
		Player testPlayer = new Player();
		assertTrue("ERROR: itemTest should exist in the room", roomTest.existsItem(itemId));
		if (roomTest.pickItem(testPlayer, itemId)){
			assertFalse("ERROR: itemTest should not exist in the room after picking it." +
					" The room creation does not control if the array contains duplicates", roomTest.existsItem(itemId));
		}
		else
			fail("ERROR: pickItem is not working properly");
	}

	@Test
	public void testAddItem() {
		assertFalse("ERROR: The room contains an item with the same id but addItem returns true", roomTest.addItem(itemTest));
		assertFalse("ERROR: The room contains an item with the same id (id is non case-sensitive) but addItem returns true", roomTest.addItem(new MockItem(itemId.toUpperCase(), "")));	
	}

	@Test
	public void testIsExit() {
		assertFalse("ERROR: We have created a non-exit room but isExit returns true",roomTest.isExit());
		// Change the room
		roomTest = new Room(true, "");
		assertTrue("ERROR: We have created a exit room but isExit returns false",roomTest.isExit());
	}

	@Test
	public void testGetDescription() {
		assertTrue("ERROR: the room description has been modified after construction", roomTest.getDescription().contains(roomDescription));
		assertTrue("ERROR: the room description does not contain the information about the item", roomTest.getDescription().contains(itemTest.toString()));
	}

	@Test
	public void testPickItem() {
		Player who = new Player();
		assertFalse("ERROR: pickItem tries to pick an item that does not exist in the room but it returns true", roomTest.pickItem(who, "anotherItem"));
		assertTrue("ERROR: pickItem tries to pick an item that does exist in the room but it returns false", roomTest.pickItem(who, itemId));
		assertFalse("ERROR: pickItem tries to pick an item that was picked from the room but it returns true", roomTest.pickItem(who, itemId));
	}

	@Test
	public void testExistsItem() {
		assertTrue("ERROR: The room contains an item with the same id but existsItem returns true", roomTest.existsItem(itemTest.getId()));
		assertTrue("ERROR: The room contains an item with the same id (id is non case-sensitive) but existsItem returns true", roomTest.existsItem(itemId.toUpperCase()));
		assertFalse("ERROR: The room contains an item with the different id  but existsItem returns false", roomTest.existsItem("AnotherItem"));	
	}
	
	

}
