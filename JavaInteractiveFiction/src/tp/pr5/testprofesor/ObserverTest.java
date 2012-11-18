package tp.pr5.testprofesor;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tp.pr5.testprofesor.mockobjects.MockGame;
import tp.pr5.testprofesor.mockobjects.MockGameObserver;
import tp.pr5.testprofesor.mockobjects.MockMap;
import tp.pr5.testprofesor.mockobjects.MockMapObserver;
import tp.pr5.testprofesor.mockobjects.MockPlayerObserver;

public class ObserverTest {
	@Test
	public void testAddGameObserverMoreThanOnce() {
		MockGame g = new MockGame(null);
		MockGameObserver obs= new MockGameObserver();
		g.addGameObserver(obs);
		g.addGameObserver(obs);
		g.requestError("");
		assertTrue("ERROR: addObserver for GameObservers does not control if an observer is registered more than once", obs.notifications()==1);
	}
	
	@Test
	public void testAddPlayerObserverMoreThanOnce() {
		MockGame g = new MockGame(null);
		MockPlayerObserver obs= new MockPlayerObserver();
		g.addPlayerObserver(obs);
		g.addPlayerObserver(obs);
		g.getPlayer().requestPlayerUpdate();
		assertTrue("ERROR: addObserver for PlayerObservers does not control if an observer is registered more than once", obs.notifications()==1);
	}
	
	@Test
	public void testAddMapObserverMoreThanOnce() {
		MockGame g = new MockGame(new MockMap(null));
		MockMapObserver obs= new MockMapObserver();
		g.addMapObserver(obs);
		g.addMapObserver(obs);
		g.getCurrentMap().playerExamineRoom();
		assertTrue("ERROR: addObserver for MapObservers does not control if an observer is registered more than once", obs.notifications()==1);
	}
}
