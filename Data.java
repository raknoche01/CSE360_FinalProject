import java.util.Observable;
import java.util.*;

/**
*
*/
public class Data extends Observable
{
	protected LinkedList<ListEntry> entries = new LinkedList<ListEntry>();

	/**
	*
	*/
	public void update(LinkedList<ListEntry> temp){
		entries = temp;

		setChanged();
		notifyObservers();
	}

	LinkedList<ListEntry> getEntries()
	{
		return entries;
	}


}
