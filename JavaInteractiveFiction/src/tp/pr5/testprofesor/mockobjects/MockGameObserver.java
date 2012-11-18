package tp.pr5.testprofesor.mockobjects;

import tp.pr5.RoomInfo;
import tp.pr5.observers.GameObserver;

public class MockGameObserver implements GameObserver {
	private boolean _error = false;
	private boolean _gameover = false;
	private boolean _requestQuit = false;
	private int _notifications=0;

	public boolean error() {
		return _error;
	}
	
	public boolean gameOver() {
		return _gameover;
	}
	
	public boolean quit() {
		return _requestQuit;
	}

	@Override
	public void gameStart(RoomInfo initialRoom, int playerPoints,
			int playerHealth) {
		_notifications++;

	}

	@Override
	public void gameError(String msg) {
		_error = true;
		_notifications++;
	}

	@Override
	public void gameHelp() {
		_notifications++;
	}

	@Override
	public void gameOver(boolean win) {
		_gameover = true;
		_notifications++;
	}

	@Override
	public void gameQuit() {
		_requestQuit = true;
		_notifications++;
	}
	
	public int notifications() {
		return _notifications;
	}

}
