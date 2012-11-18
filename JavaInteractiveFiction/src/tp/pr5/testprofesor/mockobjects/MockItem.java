package tp.pr5.testprofesor.mockobjects;
import tp.pr5.items.Item;
import tp.pr5.Player;
import tp.pr5.Room;


public class MockItem extends Item {

	public MockItem(String id, String description) {
		super(id, description);
	}

	@Override
	public boolean use(Player who, Room where) {
		return false;
	}

	@Override
	public boolean canBeUsed() {
		return false;
	}

}
