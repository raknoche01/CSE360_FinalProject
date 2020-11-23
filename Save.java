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
			
			for(int i = 0; i < model.getColumnCount(); i++) {
				csv.write(model.getColumnName(i) + ",");
			}
			
			csv.write("\n");
			
			for (int i = 0; i < model.getRowCount(); i++) {
	            for (int j = 0; j < model.getColumnCount(); j++) {
	                csv.write(model.getValueAt(i, j).toString() + ",");
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
