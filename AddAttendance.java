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
	String day;
	String month;


	/*
	 *
	 */
	void addNow(LinkedList<ListEntry> temp)
	{
		entries = temp;

        /*

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

		*/

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
				//System.out.println(month + " " + day);
				p.remove(b);
				f.setVisible(false);
				parseFile();
			}
		});
	}



		/*

		JLabel l;
		String csvFile = "null";
		end = false;

		JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int r = j.showOpenDialog(null);
		l = new JLabel("no file selected");

		if (r == JFileChooser.APPROVE_OPTION)
			csvFile = (j.getSelectedFile().getAbsolutePath());
		else
		{
			l.setText("the user cancelled the operation");
			end = true;
		}
		*/

	/*
	*
	*/
	void parseFile()
	{
		System.out.println("Month should be " + month + " day should be " + day);
		FileChooser fChoose = new FileChooser();
		String csvFile = "NULL";
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

					System.out.print(tmp.getFirst());
					asurites.add(tmp.getFirst());
					tmp.removeFirst();

					System.out.print("  " + tmp.getFirst());
					times.add(Integer.parseInt(tmp.getFirst()));
					tmp.removeFirst();
        		}
        	buffRead.close();
        	} catch(IOException ioe) {
				ioe.printStackTrace();
        	}
		}
		System.out.println(" solo printed " + asurites.get(0));
					/*
					String first = asurites.getFirst();
					//int ind = 0;
					int count = 0;
					for(int index = 0; index < entries.size(); index++) {
						if(entries.get(index).getAsurite() == first) {
							count +=;
						}

					}
					*/
        int count = 0;
        //int loop = asurites.size();
        LinkedList <String> find = new LinkedList<String>();
        LinkedList <Integer> moreFound = new LinkedList<Integer>();
		//System.out.println("passing " + entries.size() + " Asurties size of " + asurties.size());

        boolean found = false;

        for(int i = 0; i < entries.size(); i++)
		{
			//entries.get(i).printAll();
        	for(int j = 0; j < asurites.size(); j++)
        	{
				//System.out.println("checking if " + asurites.get(j) + " equals " + entries.get(i).getAsurite());
        		if(asurites.get(j).equals(entries.get(i).getAsurite()))
        		{
					//System.out.println("they equal!");
        			count += times.get(j);
        			find.add(asurites.get(j));
        			moreFound.add(times.get(j));
        			found = true;
        		}

        	}
        	if(found == true)
        	{
        		entries.get(i).addDate(month, day, count);
        		//entries.get(i).printAll();
        	}
        		count = 0;
        }
        //entries.get(0).printAll();

		LinkedList <String> rejects = new LinkedList <String>();
		//LinkedList <Integer> moreRejects = new LinkedList <Integer>();
		boolean exsists = false;
        for(int x = 0; x < entries.size(); x++)
        {
        	String check = entries.get(x).getAsurite();
        	for(int y = 0; y < asurites.size(); y++)
        	{
        		if(check == asurites.get(y))
        			exsists = true;
        	}
        	if(!found)
        		rejects.add(check);
        	exsists = false;
        }

    	DisplayInfo toDis = new DisplayInfo();
		toDis.displayNow(entries);


		//It won't return to main
		System.out.println("going back to main from add Attendance");
		//need to code alert message for user here
		//Main.repaint();

	}


	/*
	 *
	 */
	LinkedList<ListEntry> getEntries()
	{
		return entries;
	}
}
