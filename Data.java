import java.util.Observable;
import java.util.*;

/**
 * @author Chad Crum
 * 
 * This class is responsible for holding the data of the users and notifying the observers
 * when it is updated so it will be reprinted to the screen
 */
public class Data extends Observable
{
	protected LinkedList<ListEntry> entries = new LinkedList<ListEntry>();

	/**
	 * This method is to update the LinkedList data and to notify the observers
	 */
	public void update(LinkedList<ListEntry> temp){
		entries = temp;

		setChanged();
		notifyObservers();
	}
	
	/**
	 * This method is to return the data information
	 * @return a linkedList containing ListEntries
	 */
	LinkedList<ListEntry> getEntries()
	{
		return entries;
	}


}
