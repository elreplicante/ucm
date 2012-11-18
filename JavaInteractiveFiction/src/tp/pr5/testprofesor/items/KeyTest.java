package tp.pr5.testprofesor.items;

import static org.junit.Assert.*;

import org.junit.Test;

import tp.pr5.Directions;
import tp.pr5.Door;
import tp.pr5.items.Key;
import tp.pr5.Player;
import tp.pr5.Room;

public class KeyTest {
	
	@Test
	public void testUseOnewayDoor() {
		Room sourceRoom= new Room (false, "Source room desc");
		Room targetRoom= new Room (false, "Target room desc");
		Door doorTest= new Door(sourceRoom, Directions.NORTH, targetRoom, false);
		Key keyTest = new Key("keyId", "key description", doorTest);
		
		Player playerTest = new Player();
		assertTrue("ERROR: using a key in the correct room with a closed door should return true", keyTest.use(playerTest, sourceRoom));
		assertTrue("ERROR: using a key on a closed door should open the door", doorTest.isOpen());
		assertTrue("ERROR: using a key in the correct room with a open door should return true", keyTest.use(playerTest, sourceRoom));
		assertFalse("ERROR: using a key on a open door should close the door", doorTest.isOpen());
		
		assertFalse("ERROR: using a key in the target room with a closed oneway door should return false", keyTest.use(playerTest, targetRoom));
	}
	
	@Test
	public void testUsebidirectionalDoor() {
		Room sourceRoom= new Room (false, "Source room desc");
		Room targetRoom= new Room (false, "Target room desc");
		Door doorTest= new Door(sourceRoom, Directions.NORTH, targetRoom, true);
		Key keyTest = new Key("keyId", "key description", doorTest);
		
		Player playerTest = new Player();
		assertTrue("ERROR: using a key in the source room with a closed door should return true", keyTest.use(playerTest, sourceRoom));
		assertTrue("ERROR: using a key on a closed door should open the door", doorTest.isOpen());
		assertTrue("ERROR: using a key in the source room with a open door should return true", keyTest.use(playerTest, sourceRoom));
		assertFalse("ERROR: using a key on a open door should close the door", doorTest.isOpen());
		
		assertTrue("ERROR: using a key in the target room with a closed bidirectional door should return true", keyTest.use(playerTest, targetRoom));
		assertTrue("ERROR: using a key in the target room on a closed bidirectional door should open the door", doorTest.isOpen());
		assertTrue("ERROR: using a key in the target room with a open bidirectional door should return true", keyTest.use(playerTest, targetRoom));
		assertFalse("ERROR: using a key in the target room on a open bidirectional door should close the door", doorTest.isOpen());
	}

}
