import java.util.*;
/**
 * @author Chad Crum
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

	/*
	* This is to set the ID
	*/
	void setID(String usrID)
	{
		iD = usrID;
	}

	/*
	* This is to get the ID
	*/
	String getID()
	{
		return iD;
	}

	/*
	* This is to set the First Name
	*/
	void setFirstName(String name)
	{
		firstName = name;
	}

	/*
	* This is to get the First Name
	*/
	String getFirstName()
	{
		return firstName;
	}

	/*
	* This is to set the Last Name
	*/
	void setLastName(String name)
	{
		lastName = name;
	}

	/*
	* This is to get the Last Name
	*/
	String getLastName()
	{
		return lastName;
	}

	/*
	* This is to set the program
	*/
	void setProgram(String prog)
	{
		program = prog;
	}

	/*
	* This is to get the program
	*/
	String getProgram()
	{
		return program;
	}

	/*
	* This is to set the Level
	*/
	void setLevel(String lev)
	{
		level = lev;
	}

	/*
	* This is to get the level
	*/
	String getLevel()
	{
		return level;
	}

	/*
	* This is to se the Asurite
	*/
	void setAsurite(String asur)
	{
		asurite = asur;
	}

	/*
	* This is to get the Asurite
	*/
	String getAsurite()
	{
		return asurite;
	}

	/*
	* This is to add a date and time
	*/
	void addDate(String date, int time)
	{
		dates.add(date);
		times.add(time);
	}

	/*
	* This is used to get the number of dates
	*/
	int getNumDates()
	{
		return dates.size();
	}

	/*
	* This is to get the time of a date
	*/
	int getTime(String date)
	{
		int pos = dates.indexOf(date);
		if(pos >=0)
			return times.get(pos);
		else
			return 0;
	}

	/*
	* This is to change the time of a date
	*/
	void changeTime(String date, int time)
	{
		int pos = dates.indexOf(date);
		if(pos >=0)
			times.set(pos, time);
	}

	/*
	* This is for debugging purposes!
	*/
	void printAll()
	{
		System.out.print(iD + " " + firstName + " " + lastName + " " + program + " " + level + " " + asurite);
		int numDates = getNumDates();
		for(int ind = 0; ind < numDates; ind++)
		{
			System.out.print(" " + dates.get(ind) + " " + times.get(ind));
		}
		System.out.print("\n");
	}
}
