import java.util.*;
/**
 * @author Chad Crum
 * 
 * ListEntry is a class that holds all elements each entry needs.
 * It simulates a struct in c++
 */
public class ListEntry
{
	private String iD = "NULL";
	private String firstName = "NULL";
	private String lastName = "NULL";
	private String program = "NULL";
	private String level = "NULL";
	private String asurite = "NULL";
	protected LinkedList<String> dates = new LinkedList<String>();
	protected LinkedList<Integer> times = new LinkedList<Integer>();

	/**
	 * This is to set the ID
	 */
	void setID(String usrID)
	{
		iD = usrID;
	}

	/**
	 * This is to get the ID
	 */
	String getID()
	{
		return iD;
	}

	/**
	 * This is to set the First Name
	 */
	void setFirstName(String name)
	{
		firstName = name;
	}

	/**
	 * This is to get the First Name
	 */
	String getFirstName()
	{
		return firstName;
	}

	/**
	 * This is to set the Last Name
	 */
	void setLastName(String name)
	{
		lastName = name;
	}

	/**
	 * This is to get the Last Name
	 */
	String getLastName()
	{
		return lastName;
	}

	/**
	 * This is to set the program
	 */
	void setProgram(String prog)
	{
		program = prog;
	}

	/**
	 * This is to get the program
	 */
	String getProgram()
	{
		return program;
	}

	/**
	 * This is to set the Level
	 */
	void setLevel(String lev)
	{
		level = lev;
	}

	/**
	 * This is to get the level
	 */
	String getLevel()
	{
		return level;
	}

	/**
	 * This is to se the Asurite
	 */
	void setAsurite(String asur)
	{
		asurite = asur;
	}

	/**
	 * This is to get the Asurite
	 */
	String getAsurite()
	{
		return asurite;
	}
	
	/**
	 * This is to convert the month from a number to a month name
	 */
	String convertDate(String month, String day)
	{
		switch(month)
		{
			case "1": month = "Jan";
				break;
			case "2": month = "Feb";
				break;
			case "3": month = "Mar";
				break;
			case "4": month = "Apr";
				break;
			case "5": month = "May";
				break;
			case "6": month = "Jun";
				break;
			case "7": month = "Jul";
				break;
			case "8": month = "Aug";
				break;
			case "9": month = "Sep";
				break;
			case "10": month = "Oct";
				break;
			case "11": month = "Nov";
				break;
			case "12": month = "Dec";
				break;
			default: month = "NULL";
		}
		return month + " " + day;
	}
	
	/**
	 * This is to add a date and time
	 */
	void addDate(String month, String day, int time)
	{
		String toadd = convertDate(month, day);
		dates.add(toadd);
		times.add(time);
	}

	/**
	 * This is used to get the number of dates
	 */
	int getNumDates()
	{
		return dates.size();
	}

	/**
	 * This returns a LinkedList of all the dates
	 */
	LinkedList<String> getDates()
	{
		return dates;
	}

	/**
	 * This is to get the time of a date
	 */
	String getTime(String date)
	{
		int numDates = getNumDates();
		int ind = 0;
		while(!dates.get(ind).equals(date))
		{
			ind++;
		}
		return Integer.toString(times.get(ind));
	}

	/**
	 * This is to change the time of a date
	 */
	void changeTime(String month, String day, int time)
	{
		String date = month + " " + day;
		int pos = dates.indexOf(date);
		if(pos >=0)
			times.set(pos, time);
	}

	/**
	 * This is for debugging purposes!
	 */
	void printAll()
	{
		int numDates = getNumDates();
		for(int ind = 0; ind < numDates; ind++)
		{
			System.out.print(" " + dates.get(ind) + " " + times.get(ind));
		}
		System.out.print("\n");
	}
}
