import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.*;

/**
*
*/
public class FileChooser
{
	/**
	*
	*/
	String spawnChooser()
	{
		JLabel l;
		String csvFile = "null";
		JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int r = j.showOpenDialog(null);
		l = new JLabel("no file selected");

		if (r == JFileChooser.APPROVE_OPTION)
		{
			csvFile = (j.getSelectedFile().getAbsolutePath());

		}
		else
		{
			csvFile = "NULL";
		}
		return csvFile;
	}
}
