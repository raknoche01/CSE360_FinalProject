import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.filechooser.*;

/**
 * @author Rohan Nair and Chad Crum
 *
 * This class is responsible for getting a date, parsing a selected attendance csv file
 * and then notifying the observer in main so it will be printed to the screen.
 */
public class AddAttendance
{
	protected LinkedList<ListEntry> entries = new LinkedList<ListEntry>();
	JDialog dialog = new JDialog(Main.frame, "Attendance Info");
	String day;
	String month;

	/**
	 * This method is called from main and is used to set entries to the right data.
	 * It is also used to display DatePicker and store that date to this classes variables.
	 * It will then check to see if the date has already been entered, if it has it will
	 * display a message. If not, it will call parseFile
	 */
	void addNow(LinkedList<ListEntry> temp)
	{
		entries = temp;

		JLabel label = new JLabel("Selected Date:");
		final JTextField text = new JTextField(20);
		JButton but = new JButton("popup");
		JPanel pan = new JPanel();
		pan.add(label);
		pan.add(text);
		pan.add(but);
		final JFrame fra = new JFrame();
		fra.getContentPane().add(pan);
		fra.pack();
		fra.setVisible(true);
		but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				text.setText(new DatePicker(fra).setPickedDate());
				day = DatePicker.day;
				month = Integer.toString(DatePicker.month + 1);
				if(month.equals("13"))
					month = "1";
				fra.setVisible(false);
				
				LinkedList<String> dates = entries.get(0).getDates();
				String dateToCheck = entries.get(0).convertDate(month, day);
				
				if(!dates.contains(dateToCheck))
					parseFile();
				else
				{
					JOptionPane pane = new JOptionPane();
					dialog.add(pane);
					pane.showMessageDialog(dialog, "Duplicate dates not allowed!");
				}
			}
		});
	}

	/**
	* This method is called from addNow and is responsible for creating an instance of
	* FileChooser and getting the selected file. It will then parse the file and all of
	* the attendance data to the existing roster information. After that, it will display
	* all the information of the attendance added. Any users that were not found within
	* the entries are reported to the user here.
	*/
	void parseFile()
	{
		String csvFile = "NULL";
		FileChooser fChoose = new FileChooser();
		csvFile = fChoose.spawnChooser();

		LinkedList<String> tmp = new LinkedList<String>();
		LinkedList<Integer> times = new LinkedList<Integer>();
		LinkedList<String> asurites = new LinkedList<String>();

		if(csvFile != "NULL")
		{
			String delim = ",";

			try
			{
				File file = new File(csvFile);
				FileReader fileRead = new FileReader(file);
				BufferedReader buffRead = new BufferedReader(fileRead);

       		 	String line = "";
       	 		String[] tempArr;

        		while((line = buffRead.readLine()) != null) {
        			StringTokenizer toParse = new StringTokenizer(line, delim);

					while (toParse.hasMoreTokens())
						tmp.add(toParse.nextToken());

					asurites.add(tmp.getFirst());
					tmp.removeFirst();

					times.add(Integer.parseInt(tmp.getFirst()));
					tmp.removeFirst();
        		}
        	buffRead.close();
        	} catch(IOException ioe) {
				ioe.printStackTrace();
        	}
		}

		LinkedList <String> checked = new LinkedList<String>();

		LinkedList <String> asurtiesFound = new LinkedList<String>();
		LinkedList <Integer> timesFound = new LinkedList<Integer>();

        int count = 0;
        for(int ind = 0; ind < entries.size(); ind++)
		{
        	for(int jnd = 0; jnd < asurites.size(); jnd++)
        	{
        		if(asurites.get(jnd).equals(entries.get(ind).getAsurite()))
        		{
        			count += times.get(jnd);
        			if(!asurtiesFound.contains(asurites.get(jnd)))
        			{
        				asurtiesFound.add(asurites.get(jnd));
        				timesFound.add(times.get(jnd));
					}
        		}
        	}
        	entries.get(ind).addDate(month, day, count);
        	count = 0;
        }

		LinkedList <String> asurtiesRejects = new LinkedList<String>();
		LinkedList <Integer> timesRejects = new LinkedList<Integer>();

		for(int ind = 0; ind < asurites.size(); ind++)
		{
			if(!asurtiesFound.contains(asurites.get(ind)))
			{
				if(!asurtiesRejects.contains(asurites.get(ind)))
				{
					asurtiesRejects.add(asurites.get(ind));
					timesRejects.add(times.get(ind));
				}
				else
				{
					int loc = asurtiesRejects.indexOf(asurites.get(ind));
					int tempTime = timesRejects.get(loc);
					timesRejects.set(loc, tempTime + times.get(ind));
				}
			}
		}

		String user;
		if(asurtiesFound.size() == 1)
			user = "user";
		else
			user = "users";
		String output = "";
		output = "<html>Data loaded for " + asurtiesFound.size() + " " + user + " in the Roster.";
		if(asurtiesRejects.size() > 0)
		{
			String atten;
			if(asurtiesRejects.size() == 1)
				atten = "attendee";
			else
				atten = "attendees";
			output = output + "<br>" + asurtiesRejects.size() + " " + atten + " was found:";
		}
		JLabel extra;
		for(int rejs = 0; rejs < asurtiesRejects.size(); rejs++)
		{
			output = output + "<br>Unknown: " + asurtiesRejects.get(rejs) + " attended for: " + timesRejects.get(rejs);
			String timeCon;
			if(timesRejects.get(rejs) == 1)
				timeCon = " minute";
			else
				timeCon = " minutes";
			output = output + timeCon;
		}
		output = output + "</html>";

		JOptionPane pane = new JOptionPane();
		dialog.add(pane);
		pane.showMessageDialog(dialog, output);

		Main.data.update(entries);
		pane.setVisible(true);
	}

	/**
	 * This method's only job is to return the updated entries linked list
	 * @return a linkedList containing ListEntries
	 */
	LinkedList<ListEntry> getEntries()
	{
		return entries;
	}
}
