package tp.pr5.testprofesor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.pr5.Directions;
import tp.pr5.Door;
import tp.pr5.Room;

public class DoorTest {
	
	private Door onewayDoor;
	private Door bidirectionalDoor;
	private Room targetRoom;
	private Room sourceRoom;

	@Before
	public void setUp() throws Exception {
		targetRoom = new Room (false, "Target room desc");
		sourceRoom = new Room (false, "Source room desc");
		onewayDoor = new Door(sourceRoom, Directions.NORTH, targetRoom, false);
		bidirectionalDoor = new Door(sourceRoom, Directions.EAST, targetRoom, true);
	}

	@Test
	public void testIsOpen() {
		assertFalse("ERROR: Doors are created closed by default", onewayDoor.isOpen());
		onewayDoor.open();
		assertTrue("ERROR: After open(), isOpen should return true", onewayDoor.isOpen());
	}

	@Test
	public void testOpen() {
		if (!onewayDoor.isOpen()){
			onewayDoor.open();
			assertTrue("ERROR: After open(), isOpen should return true", onewayDoor.isOpen());
			onewayDoor.open();
			assertTrue("ERROR: After open() on an open door, isOpen should return true", onewayDoor.isOpen());
		}
	}

	@Test
	public void testClose() {
		if (!onewayDoor.isOpen()){
			onewayDoor.close();
			assertFalse("ERROR: After close(), a close door remains close and isOpen should return false", onewayDoor.isOpen());
			onewayDoor.open();
			onewayDoor.close();
			assertFalse("ERROR: After close() on an open door, isOpen should return false", onewayDoor.isOpen());
		}
	}
	
	
	@Test
	public void testIsInRoomOnewayDoor() {
		assertTrue("ERROR: A onewaydoor is always in the source room (isInRoom returns true)", onewayDoor.isInRoom(sourceRoom));
		assertFalse("ERROR: A onewaydoor is never in the target room (isInRoom returns false)", onewayDoor.isInRoom(targetRoom));
	}
	
	@Test
	public void testIsInRoomBidirectionalDoor() {
		assertTrue("ERROR: A bidirectional door is always in the source room (isInRoom returns true)", bidirectionalDoor.isInRoom(sourceRoom));
		assertTrue("ERROR: A bidirectional door is always in the target room (isInRoom returns true)", bidirectionalDoor.isInRoom(targetRoom));
	}

	
	
	@Test
	public void testIsInRoomDirOnewayDoor() {
		assertTrue("ERROR: A onewaydoor is always in the source room in the correct direction (isInRoom with the correct direction returns true)", onewayDoor.isInRoom(sourceRoom, Directions.NORTH));
		assertFalse("ERROR: A onewaydoor is never in the target room (isInRoom returns false)", onewayDoor.isInRoom(targetRoom, Directions.NORTH));
		assertFalse("ERROR: A onewaydoor is not in the source room in a wrong direction (isInRoom with a wrong direction returns false)", onewayDoor.isInRoom(sourceRoom, Directions.EAST));
		assertFalse("ERROR: A onewaydoor is not in the source room in a wrong direction (isInRoom with the opposite direction returns false)", onewayDoor.isInRoom(sourceRoom, Directions.SOUTH));
	}
	
	@Test
	public void testIsInRoomDirBidirectionalDoor() {
		assertTrue("ERROR: A bidirectional door is always in the source room in the correct direction (isInRoom with the correct direction returns true)", bidirectionalDoor.isInRoom(sourceRoom, Directions.EAST));
		assertFalse("ERROR: A bidirectional door is not in the target room in the same direction employed during the creation (isInRoom with the creation direction returns false).", bidirectionalDoor.isInRoom(targetRoom, Directions.EAST));
		assertTrue("ERROR: A bidirectional door is always in the target room in the correct direction (isInRoom with the correct direction returns true)." +
				" The correct direction for the target door is the opposite direction", bidirectionalDoor.isInRoom(targetRoom, Directions.WEST));
		assertFalse("ERROR: A bidirectional is not in the source room in a wrong direction (isInRoom with a wrong direction returns false)", bidirectionalDoor.isInRoom(sourceRoom, Directions.NORTH));
		assertFalse("ERROR: A bidirectional is not in the target room in a wrong direction (isInRoom with a wrong direction returns false)", bidirectionalDoor.isInRoom(targetRoom, Directions.NORTH));
	}

	@Test
	public void testNextRoomOnewayDoor() {
		assertEquals("ERROR: nextRoom(sourceRoom) does not return the correct target room with a oneway door", targetRoom, onewayDoor.nextRoom(sourceRoom));
		assertNull("ERROR: nextRoom(targetRoom) does not return null for a oneway door", onewayDoor.nextRoom(targetRoom));
	}
	
	@Test
	public void testNextRoomBidirectionalDoor() {
		assertEquals("ERROR: nextRoom(sourceRoom) does not return the correct target room with a bidirectional door", targetRoom, bidirectionalDoor.nextRoom(sourceRoom));
		assertEquals("ERROR: nextRoom(targetRoom) does not return the correct target room with a bidirectional door", sourceRoom, bidirectionalDoor.nextRoom(targetRoom));
	}

	@Test
	public void testConnectOnewayDoor() {
		assertFalse("ERROR: connect should return false if the door connects the rooms but it is closed", onewayDoor.connect(sourceRoom, targetRoom));
		onewayDoor.open();
		assertTrue("ERROR: connect should return true if the door connects the rooms and it is open", onewayDoor.connect(sourceRoom, targetRoom));
		assertFalse("ERROR: connect should return false if we ask for the rooms in th opposite direction (target, source)", onewayDoor.connect(targetRoom, sourceRoom));
		Room ghostRoom = new Room (false, "ghost room");
		assertFalse("ERROR: connect should return false if one of the room is not correct", onewayDoor.connect(sourceRoom, ghostRoom));
	}
	
	@Test
	public void testConnectBidirectionalDoor() {
		assertFalse("ERROR: connect should return false if the door connects the rooms but it is closed", bidirectionalDoor.connect(sourceRoom, targetRoom));
		assertFalse("ERROR: connect should return false if the door connects the rooms but it is closed (even in the opposite direction)", bidirectionalDoor.connect(targetRoom, sourceRoom));
		bidirectionalDoor.open();
		assertTrue("ERROR: connect should return true if the door connects the rooms and it is open", bidirectionalDoor.connect(sourceRoom, targetRoom));
		assertTrue("ERROR: connect should return true if the door connects the rooms and it is open (even in the opposite direction (target, source))", bidirectionalDoor.connect(targetRoom, sourceRoom));
		Room ghostRoom = new Room (false, "ghost room");
		assertFalse("ERROR: connect should return false if one of the room is not correct", bidirectionalDoor.connect(sourceRoom, ghostRoom));
	}


}
