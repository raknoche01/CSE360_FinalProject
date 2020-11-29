import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.*;
/**
 * @author Jaime Hern and Chad Crum
 * LoadRoster is called by main and is responsible for displaying all the
 * information on screen from a CSV file the user chooses.
 */
public class LoadRoster
{
    protected LinkedList<ListEntry> entries = new LinkedList<ListEntry>();

	/**
	 * loadNow is responsible for prompting the user to choose a file,
 	 * loading the file selected and gathering all the data.
 	 * It then parses the data and adds it all to our linkedList data structure
 	 * It will then take all the data gathered and enter it into a 2D array
 	 * so JTable can take that information and display it on screen.
	 */
    void loadNow()
    {
		FileChooser fChoose = new FileChooser();
		String csvFile = "NULL";
		csvFile = fChoose.spawnChooser();
		
		while(entries.size() != 0)
			entries.remove();
		
		if(csvFile != "NULL")
		{
			String delim = ",";
			LinkedList<String> temp = new LinkedList<String>();

			try {
				File file = new File(csvFile);
				FileReader fileRead = new FileReader(file);
				BufferedReader buffRead = new BufferedReader(fileRead);

        		String line = "";
        		String[] tempArr;

        		while((line = buffRead.readLine()) != null) {
					StringTokenizer toParse = new StringTokenizer(line, delim);

					while (toParse.hasMoreTokens())
						temp.add(toParse.nextToken());

					ListEntry toEnt = new ListEntry();

					toEnt.setID(temp.getFirst());
					temp.removeFirst();

					toEnt.setFirstName(temp.getFirst());
					temp.removeFirst();

					toEnt.setLastName(temp.getFirst());
					temp.removeFirst();

					toEnt.setProgram(temp.getFirst());
					temp.removeFirst();

					toEnt.setLevel(temp.getFirst());
					temp.removeFirst();

					toEnt.setAsurite(temp.getFirst());
					temp.removeFirst();

					entries.add(toEnt);
			}
			buffRead.close();
			} catch(IOException ioe) {
				ioe.printStackTrace();
        	}
		}

		Main.data.update(entries);
	}

	/**
	 * This method is used by main to get all of the information made here
	 * so it can give it to other parts of the program.
	 */
    LinkedList<ListEntry> getEntries()
    {
        return entries;
    }
}
