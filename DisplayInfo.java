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

	void displayNow(LinkedList<ListEntry> temp)
	{
		entries = temp;

		String column[] = {"ID","First Name","Last Name","Program","Level","ASURITE"};
		DefaultTableModel model = new DefaultTableModel();


		int numDates = entries.get(0).getNumDates();
		//if(numDates > 0)
		LinkedList<String> datesToEnt = entries.get(0).getDates();

		for(int colm = 0; colm < numDates; colm++)
			model.addColumn(datesToEnt.get(colm));

		int numColumns = entries.size();

		String[][] data = new String[numColumns][6 + numDates]; //{{"NULL"}, {"NULL"}};

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
				//datesToEnt.get(jnd).printAll();
				String date = datesToEnt.get(jnd);
				data[ind][5 + jnd + 1] = toWrite.getTime(date);
				System.out.println("inside of adding data loop, saying time is " + toWrite.getTime(date));
			}

			//model.insertRow(ind, new Object[]{data[ind][0], data[ind][1], data[ind][2], data[ind][3], data[ind][4], data[ind][5]});
			//model.insertRow(ind, new Object[]{"nov"});
		}

		JTable studInfo = new JTable(data, column);
		//studInfo.addColumn("test");

		studInfo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for(int i =0; i < 6; i++)
			studInfo.getColumnModel().getColumn(i).setPreferredWidth(120);

		scrollStud = new JScrollPane(studInfo);
		scrollStud.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollStud.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	}

}
