import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
/**
 * @author Rebecca Knoche
 *
 * This class is responsible for saving all of the data from LoadRoster and AddAttendence
 */
public class Save
{
	private DisplayInfo di;
	
	/**
	 * This method will get the JTable from DisplayInfo that contains all of the information.
	 * It will then create an instance of FileChooser to get a location to save to.
	 * It will then go through all of the data gathered from LoadRoster and AddAttendence and
	 * save them to the selected csv file.
	 */
	void saveNow()
	{
		int ind,jnd,knd = 0;
		
		DisplayInfo di = new DisplayInfo();
		
		JTable table = di.getTable();
		TableModel model = table.getModel();

		JFrame parentFrame = new JFrame();
		File saveLocation = new File("");
		
		try {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Choose a location to save file");
			
			int selection = fileChooser.showSaveDialog(parentFrame);
			
			if (selection == JFileChooser.APPROVE_OPTION) {
			    saveLocation = fileChooser.getSelectedFile();
			}
			FileWriter csv = new FileWriter(saveLocation);
			
			for(ind = 0; ind < model.getColumnCount(); ind++) {
				if(ind + 1 == model.getColumnCount())
					csv.write(model.getColumnName(ind));
				else
					csv.write(model.getColumnName(ind) + ",");
			}
			csv.write("\n");
			
			for (jnd = 0; jnd < model.getRowCount(); jnd++) {
	            for (knd = 0; knd < model.getColumnCount(); knd++) {
	            	if(knd + 1 == model.getColumnCount()) 
	            		csv.write(model.getValueAt(jnd, knd).toString());
	            	else
	            		csv.write(model.getValueAt(jnd, knd).toString() + ",");
	            }
	            if(jnd + 1 < model.getRowCount())
	            	csv.write("\n");
	        }
	        csv.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}   
	}
}
