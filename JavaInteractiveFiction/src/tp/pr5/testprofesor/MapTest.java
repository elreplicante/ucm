package tp.pr5.testprofesor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.pr5.Directions;
import tp.pr5.Door;
import tp.pr5.Map;
import tp.pr5.Room;

public class MapTest {
	
	private Room targetRoom;
	private Room sourceRoom;
	private Room targetBidirectionalRoom;
	private Map mapTest;
	
	@Before
	public void setUp() throws Exception {
		targetRoom = new Room (false, "Target room desc");
		targetBidirectionalRoom = new Room (false, "Target bidir room desc");
		sourceRoom = new Room (false, "Source room desc");
		mapTest = new Map(sourceRoom);
		mapTest.addDoor(sourceRoom, Directions.NORTH, targetRoom);
		mapTest.addBidirectionalDoor(sourceRoom, Directions.EAST, targetBidirectionalRoom);
	}
	
	@Test
	public void testAddDoor() {
		Door retDoor = mapTest.getDoor(sourceRoom, Directions.NORTH);
		assertTrue("ERROR: the door was not added correctly to the map because " +
				"it is not in the sourceRoom", retDoor.isInRoom(sourceRoom));
		assertTrue("ERROR: the door was not added correctly to the map because " +
				"it is not in the sourceRoom in direction NORTH", retDoor.isInRoom(sourceRoom, Directions.NORTH));
		assertEquals("ERROR: the door was not added correctly to the map because " +
				"the next room is not the targetRoom", targetRoom, retDoor.nextRoom(sourceRoom));
	}
	
	@Test
	public void testAddBidirectionalDoor() {
		Door retDoor = mapTest.getDoor(sourceRoom, Directions.EAST);
		assertTrue("ERROR: the bidirectional door was not added correctly to the map because " +
				"it is not in the sourceRoom", retDoor.isInRoom(sourceRoom));
		assertTrue("ERROR: the bidirectional door was not added correctly to the map because " +
				"it is not in the sourceRoom in direction EAST", retDoor.isInRoom(sourceRoom, Directions.EAST));
		assertEquals("ERROR: the bidirectional door was not added correctly to the map because " +
				"the next room is not the targetBidirectionalRoom", targetBidirectionalRoom, retDoor.nextRoom(sourceRoom));
		
		Door retDoor2 = mapTest.getDoor(targetBidirectionalRoom, Directions.WEST);
		assertTrue("ERROR: addBidirectionalDoor should create only one bidirectional door", retDoor.equals(retDoor2));
		assertTrue("ERROR: the bidirectional door was not added correctly to the map because " +
				"it is not also in the targetBidirectionalRoom", retDoor.isInRoom(targetBidirectionalRoom));
		assertTrue("ERROR: the bidirectional door was not added correctly to the map because " +
				"it is not in the targetBidirectionalRoom in direction WEST", retDoor.isInRoom(targetBidirectionalRoom, Directions.WEST));
		assertEquals("ERROR: the bidirectional door was not added correctly to the map because " +
				"the next room is not the sourceRoom", sourceRoom, retDoor.nextRoom(targetBidirectionalRoom));
		
	}
	
	@Test
	public void testGetDoor() {
		
		assertNull("ERROR: getDoor does not return null when there is no door in a direction", mapTest.getDoor(sourceRoom, Directions.SOUTH));
		/*
		 * getDoor should return the door that connects two rooms although it is not crossable in a direction. This way we can show
		 * different messages if there is no door or if there is a door but it is not crossable
		 */
		assertEquals("ERROR: getDoor should return the same door in both directions although the door is " +
				"not crossable in one of them", mapTest.getDoor(sourceRoom, Directions.NORTH) ,mapTest.getDoor(targetRoom, Directions.SOUTH));
		assertEquals("ERROR: they must be the same door", mapTest.getDoor(sourceRoom, Directions.EAST) ,mapTest.getDoor(targetBidirectionalRoom, Directions.WEST));
		assertNotNull("ERROR: There exists a door in this direction ",mapTest.getDoor(sourceRoom, Directions.NORTH));
		assertNull("ERROR: There does not exists a door in this direction", mapTest.getDoor(sourceRoom, Directions.SOUTH));
		assertNotNull("ERROR: There exists a door in this direction", mapTest.getDoor(sourceRoom, Directions.EAST));
		assertNull("ERROR: There does not exists a door in this direction", mapTest.getDoor(sourceRoom, Directions.WEST));
	}

}
