package tp.pr5.testprofesor.mockobjects;

import java.lang.reflect.Field;

import tp.pr5.Game;
import tp.pr5.Map;
import tp.pr5.Player;

public class MockGame extends Game {

	private boolean quitRequested;
	public MockGame(Map map) {
		super(map);
		setQuitRequested(false);
	}
	
	public Player getPlayer() {
		return (Player)getField(Player.class);
	}
	
	public Map getCurrentMap(){
		return (Map)getField(Map.class);	
	}
	public void requestQuit() {
		setQuitRequested(true);
		super.requestQuit();
	}
	
	private Object getField(Class type) {
		Object theField = null;
		// Usamos instrospeccion para encontrar el atributo que es de la clase Map
		Field []fields = Game.class.getDeclaredFields();
		for (Field f:fields)
			if (f.getType().equals(type))
				try {
					f.setAccessible(true);
					theField = f.get(this);
					f.setAccessible(false);
					return theField;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					return null;

				} catch (IllegalAccessException e) {
					e.printStackTrace();
					return null;
				}
		return theField;		
	}

	public void setQuitRequested(boolean quitRequested) {
		this.quitRequested = quitRequested;
	}

	public boolean isQuitRequested() {
		return quitRequested;
	}

}
