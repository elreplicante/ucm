package tp.pr5.testprofesor.mockobjects;

import java.util.List;

import tp.pr5.items.Item;
import tp.pr5.observers.PlayerObserver;

public class MockPlayerObserver implements PlayerObserver {
	
	private boolean _look = false;
	private boolean _updated = false;
	private boolean _used = false;
	private boolean _empty = false;
	
	private int _notifications = 0;
	
	private int _score = 0;
	private int _health = 0;
	
	public boolean look() {
		return _look;
	}
	
	public boolean updated() {
		return _updated;
	}
	
	public boolean used(){
		return _used;
	}
	
	public boolean empty(){
		return _empty;
	}
	
	public int score(){
		return _score;
	}
	
	public int health(){
		return _health;
	}
	
	public int notifications() {
		return _notifications;
	}

	@Override
	public void playerLookedInventory(List<Item> inventory) {
		_look = true;
		_notifications++;
	}

	@Override
	public void inventoryUpdate(List<Item> inventory) {
		_updated = true;
		_notifications++;
	}

	@Override
	public void playerUpdate(int newHealth, int newScore) {
		_health = newHealth;
		_score= newScore;
		_notifications++;

	}

	@Override
	public void playerDead() {
		_notifications++;
	}

	@Override
	public void itemLooked(String description) {
		_look = true;
		_notifications++;
	}

	@Override
	public void itemUsed(String itemName) {
		_used = true;
		_notifications++;
	}

	@Override
	public void itemEmpty(String itemName) {
		_empty  = true;
		_notifications++;
	}

}
