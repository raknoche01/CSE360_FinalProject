import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
/*
*
*/
public class DisplayInfo
{
	protected LinkedList<ListEntry> entries = new LinkedList<ListEntry>();
	JLabel l;
	static JScrollPane scrollStud;
	static JTable studInfo;

	/**
	*
	*/
	void displayNow(LinkedList<ListEntry> temp)
	{
		entries = temp;

		ArrayList<String> column = new ArrayList<String>();
		column.add("ID");
		column.add("First Name");
		column.add("Last Name");
		column.add("Program");
		column.add("Level");
		column.add("ASURITE");

		int titles = 6;
		int numDates = entries.get(0).getNumDates();

		LinkedList<String> datesToEnt = entries.get(0).getDates();

		for(int colm = 0; colm < numDates; colm++)
		{
			column.add(datesToEnt.get(colm));
			titles++;
		}


		int numColumns = entries.size();
		String[][] data = new String[numColumns][6 + numDates];

		for(int ind = 0; ind < numColumns; ind++)
		{
			ListEntry toWrite = entries.get(ind);
			data[ind][0] = toWrite.getID();
			data[ind][1] = toWrite.getFirstName();
			data[ind][2] = toWrite.getLastName();
			data[ind][3] = toWrite.getProgram();
			data[ind][4] = toWrite.getLevel();
			data[ind][5] = toWrite.getAsurite();

			for(int jnd = 0; jnd < numDates; jnd++)
			{
				String date = datesToEnt.get(jnd);
				data[ind][5 + jnd + 1] = toWrite.getTime(date);
			}
		}

		String columntoPass[] = column.toArray(new String[column.size()]);
		studInfo = new JTable(data, columntoPass);

		studInfo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for(int i =0; i < titles ; i++)
			studInfo.getColumnModel().getColumn(i).setPreferredWidth(120);

		scrollStud = new JScrollPane(studInfo);
		scrollStud.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollStud.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	}

}
