import java.util.*;
/**
 * @author
 *
 */
public class Plot
{
	protected LinkedList<ListEntry> entries = new LinkedList<ListEntry>();

	/*
	 *
	 */
	void plotNow(LinkedList<ListEntry> temp)
	{
		entries = temp;
	}
}
