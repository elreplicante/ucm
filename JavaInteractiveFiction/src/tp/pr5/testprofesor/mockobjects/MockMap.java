package tp.pr5.testprofesor.mockobjects;

import tp.pr5.Map;
import tp.pr5.Room;

public class MockMap extends Map {

	public MockMap(Room initRoom) {
		super(initRoom);
	}
	
	public Room getCurrentRoom() {
		Room r = super.getCurrentRoom();
		if (r==null)
			return new Room(false, "");
		else
			return r;		
	}

}
