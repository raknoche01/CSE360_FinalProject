import java.util.*;
/**
 * @author
 *
 */
public class Save
{
	protected LinkedList<ListEntry> entries = new LinkedList<ListEntry>();

	/*
	 *
	 */
	void saveNow(LinkedList<ListEntry> temp)
	{
		entries = temp;

	}
}
