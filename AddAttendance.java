import java.util.*;
/**
 * @author
 *
 */
public class AddAttendance
{
	protected LinkedList<ListEntry> entries = new LinkedList<ListEntry>();

	/*
	 *
	 */
	void addNow(LinkedList<ListEntry> temp)
	{
		entries = temp;
	}

	/*
	 *
	 */
	LinkedList<ListEntry> getEntries()
	{
		return entries;
	}
}
