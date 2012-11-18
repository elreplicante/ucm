package tp.pr5.testprofesor.mockobjects;

import tp.pr5.Directions;
import tp.pr5.RoomInfo;
import tp.pr5.observers.MapObserver;

public class MockMapObserver implements MapObserver {
	
	private boolean _enter = false;
	private int _notifications=0;
	
		@Override
	public void roomEntered(Directions direction, RoomInfo targetRoom) {
		_enter = true;
		_notifications++;
	}

	@Override
	public void playerHasExaminedRoom(RoomInfo r) {
		_notifications++;
	}
	
	public boolean enter() {
		return _enter;
	}
	
	public int notifications() {
		return _notifications;
	}

}
