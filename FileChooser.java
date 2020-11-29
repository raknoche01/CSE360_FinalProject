import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.*;

/**
* @author Chad Crum
* 
* This class is responsible for displaying a FileChooser for the user to interact with.
*/
public class FileChooser
{
	/**
	* This method creates the FileChooser and displays it to the user.
	* @return a String of the csv file name.
	*/
	String spawnChooser()
	{
		JLabel lab;
		String csvFile = "null";
		JFileChooser jch = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int re = jch.showOpenDialog(null);
		lab = new JLabel("no file selected");

		if (re == JFileChooser.APPROVE_OPTION)
		{
			csvFile = (jch.getSelectedFile().getAbsolutePath());

		}
		else
		{
			csvFile = "NULL";
		}
		return csvFile;
	}
}
