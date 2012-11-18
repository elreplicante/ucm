/**
 * @author Jose Carlos Gonz�lez y Sergio Revilla
 *
 */
package tp.pr5.items;

public abstract class PersistentItem extends Item {

	/**
	 * @param id
	 * @param description
	 */
	public PersistentItem(String id, String description) {

		super(id, description);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tp.pr3.items.Item#toString()
	 */
	public String toString() {
		return super.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tp.pr3.items.Item#canBeUsed()
	 */
	public boolean canBeUsed() {
		return true;
	}

}