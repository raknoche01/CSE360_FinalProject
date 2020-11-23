import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 *
 * @author Isiah Abad and Chad Crum
 *
 * The main class creates the menu
 *
 */

class MenuActionListener implements ActionListener {
	public void actionPerformed(ActionEvent event) {
  		String selected = event.getActionCommand();
  		int cont = Main.num;
  		String prev = Main.last;

		DisplayInfo toDis = new DisplayInfo();
  		if(selected == "Load a Roster")
  		{
  			if(cont == 0)
  				cont++;
  			prev = "load";
  			Main.loadRos.loadNow();
  		}

  		else if(cont > 0 && selected == "Add Attendance")
  		{
			if(cont == 1)
  				cont++;
  			prev = "add";
  			Main.addAtt.addNow(Main.loadRos.getEntries());
		}

		else if(cont > 1 && selected == "Save")
		{
			if(cont == 2)
  				cont++;

  			if(prev == "load") {
  				Main.toSave.saveNow();
  				}
  			else {
  				Main.toSave.saveNow();
  			}
		}

		else if(cont > 2 && selected == "Plot Data")
		{
			if(prev == "load")
				Main.plot.plotNow(Main.loadRos.getEntries());
			else
				Main.plot.plotNow(Main.addAtt.getEntries());
		}

		else
		{
			System.out.println("Cannot execute");
		}

		Main.num = cont;
		Main.last = prev;
	}
}

@SuppressWarnings("serial")
public class Main extends JFrame {

	//create two association variables
	//1 for the controller
	//another for the panel
	static int num = 0;
	static String last = "NULL";

	static LoadRoster loadRos;
	static AddAttendance addAtt;
	static Save toSave;
	static Plot plot;
	static JFrame frame;
	static Data data = new Data();


	public Main(){

		frame = new JFrame("CSE360 Final Project");
		JMenuBar mb = new JMenuBar();

		//creating the menus
		JMenu file = new JMenu("File");
		JMenu about = new JMenu("About");

		// menu items
		JMenuItem roster = new JMenuItem("Load a Roster");
		JMenuItem attendance = new JMenuItem("Add Attendance");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem plotData = new JMenuItem("Plot Data");

		//adding actionlisteners
		roster.addActionListener(new MenuActionListener());
		attendance.addActionListener(new MenuActionListener());
		save.addActionListener(new MenuActionListener());
		plotData.addActionListener(new MenuActionListener());
		
		//creating action listener for about menu to open dialog box
		about.addMenuListener(new MenuListener(){
			@Override
			public void menuSelected(MenuEvent event) {
				JOptionPane.showMessageDialog(null, "CSE 360 Final Project (Tuesday Section)\n"
						  + "---------------------------------------"
						  + "\nTeam Members:\n"
						  + "\tChad, Crum, cdcrum\n"
						  + "\tIsiah, Abad, iabad1 \n"
						  + "\tJaime, Hernandez, jihern10\n"
						  + "\tRebecca, Knoche, raknoche\n"
						  + "\tRohan, Nair, ranair3\n"
						  + "---------------------------------------");
			}
			@Override
			public void menuDeselected(MenuEvent e) {}
			@Override
			public void menuCanceled(MenuEvent e) {}
		});

		//adding menu items for main menus
		file.add(roster);
		file.add(attendance);
		file.add(save);
		file.add(plotData);
		
		//adding menu items in menu bar
		mb.add(file);
		mb.add(about);

		//setting frame and menu bar
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,600);
		frame.setJMenuBar(mb);
		//frame.pack(); //used if we want the frame to resize itself depending on panels
		frame.setVisible(true);

	 	Listener toListen = new Listener();
	 	data.addObserver(toListen);

		//creating classes for options
		loadRos = new LoadRoster();
		addAtt = new AddAttendance();
		toSave = new Save();
		plot = new Plot();
	}
	public static void main(String[] args) {
		new Main();
		//reprint();
	}

}
