import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.*;
/*
 * @author Jaime Hern and Chad Crum
 * LoadRoster is called by main and is responsible for displaying all the
 * information on screen from a CSV file the user chooses.
 */
public class LoadRoster
{
    protected LinkedList<ListEntry> entries = new LinkedList<ListEntry>();
    static JScrollPane scrollStud;
    static boolean end = false;

	/*
	 * loadNow is responsible for prompting the user to choose a file,
 	 * loading the file selected and gathering all the data.
 	 * It then parses the data and adds it all to our linkedList data structure
 	 * It will then take all the data gathered and enter it into a 2D array
 	 * so JTable can take that information and display it on screen.
	 */
    void loadNow()
    {
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
		if(end == false)
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
				end = true;
        	}
		}
		if(end == false)
		{
			String column[] = {"ID","First Name","Last Name","Program","Level","ASURITE"};
			int numColumns = entries.size();
        	String[][] data = new String[numColumns][6]; //{{"NULL"}, {"NULL"}};

			for(int ind = 0; ind < numColumns; ind++)
			{
				ListEntry toWrite = entries.get(ind);
				data[ind][0] = toWrite.getID();
				data[ind][1] = toWrite.getFirstName();
				data[ind][2] = toWrite.getLastName();
				data[ind][3] = toWrite.getProgram();
				data[ind][4] = toWrite.getLevel();
				data[ind][5] = toWrite.getAsurite();
			}
			JTable studInfo = new JTable(data, column);
			studInfo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

			for(int i =0; i < 6; i++)
				studInfo.getColumnModel().getColumn(i).setPreferredWidth(120);

			scrollStud = new JScrollPane(studInfo);
			scrollStud.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollStud.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		}
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
