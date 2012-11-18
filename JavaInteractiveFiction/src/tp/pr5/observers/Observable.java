/**
 * 
 */
package tp.pr5.observers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author repli
 * 
 */
public class Observable<T> {

    protected List<T> observers = new ArrayList<T>();

    public void addObserver(T observer) {

	if (!observers.contains(observer))
	    observers.add(observer);
    }

    public void removeObserver(T observer) {
	if (observers.contains(observer))
	    observers.remove(observer);

    }

}
