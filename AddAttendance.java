import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.filechooser.*;

/**
 * @author
 *
 */
public class AddAttendance
{
	protected LinkedList<ListEntry> entries = new LinkedList<ListEntry>();
	JDialog dialog = new JDialog(Main.frame, "Attendance Info");
	String day;
	String month;


	/*
	 *
	 */
	void addNow(LinkedList<ListEntry> temp)
	{
		entries = temp;

		JLabel label = new JLabel("Selected Date:");
		final JTextField text = new JTextField(20);
		JButton b = new JButton("popup");
		JPanel p = new JPanel();
		p.add(label);
		p.add(text);
		p.add(b);
		final JFrame f = new JFrame();
		f.getContentPane().add(p);
		f.pack();
		f.setVisible(true);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				text.setText(new DatePicker(f).setPickedDate());
				day = DatePicker.day;
				month = Integer.toString(DatePicker.month + 1);
				if(month.equals("13"))
					month = "1";
				f.setVisible(false);

				parseFile();
			}
		});
	}

	/*
	*
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
        for(int i = 0; i < entries.size(); i++)
		{
        	for(int j = 0; j < asurites.size(); j++)
        	{
        		if(asurites.get(j).equals(entries.get(i).getAsurite()))
        		{
        			count += times.get(j);
        			if(!asurtiesFound.contains(asurites.get(j)))
        			{
        				asurtiesFound.add(asurites.get(j));
        				timesFound.add(times.get(j));
					}
        		}
        	}
        	entries.get(i).addDate(month, day, count);
        	count = 0;
        }

		LinkedList <String> asurtiesRejects = new LinkedList<String>();
		LinkedList <Integer> timesRejects = new LinkedList<Integer>();

		for(int i = 0; i < asurites.size(); i++)
		{
			if(!asurtiesFound.contains(asurites.get(i)))
			{
				if(!asurtiesRejects.contains(asurites.get(i)))
				{
					asurtiesRejects.add(asurites.get(i));
					timesRejects.add(times.get(i));
				}
				else
				{
					int loc = asurtiesRejects.indexOf(asurites.get(i));
					int tempTime = timesRejects.get(loc);
					timesRejects.set(loc, tempTime + times.get(i));
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
				timeCon = "minute";
			else
				timeCon = "minues";
			output = output + timeCon;
		}
		output = output + "</html>";

		JOptionPane pane = new JOptionPane();
		dialog.add(pane);
		pane.showMessageDialog(dialog, output);

		Main.data.update(entries);
		pane.setVisible(true);
	}


	/*
	 *
	 */
	LinkedList<ListEntry> getEntries()
	{
		return entries;
	}
}
