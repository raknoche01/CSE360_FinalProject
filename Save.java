import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
/**
 * @author Rebecca Knoche
 *
 */
public class Save
{
	private DisplayInfo di;
	/*
	 *
	 */
	void saveNow()
	{
		int i,j,k = 0;
		
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
			
			for(i = 0; i < model.getColumnCount(); i++) {
				if(i + 1 == model.getColumnCount())
					csv.write(model.getColumnName(i));
				else
					csv.write(model.getColumnName(i) + ",");
			}
			
			csv.write("\n");
			
			for (j = 0; j < model.getRowCount(); j++) {
	            for (k = 0; k < model.getColumnCount(); k++) {
	            	if(k + 1 == model.getColumnCount()) 
	            		csv.write(model.getValueAt(j, k).toString());
	            	else
	            		csv.write(model.getValueAt(j, k).toString() + ",");
	            }
	            csv.write("\n");
	        }

	        csv.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		

		    
	}
}
