import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * @author Isiah Abad and Chad Crum
 *
 * The MenuActionListener is responsible for responding to button presses appropriately
 * and calling the appropriate classes to progress the program.
 */
class MenuActionListener implements ActionListener {
	/**
	 * This method is responsible for calling the appropriate classes with the necessary information
	 * to progress the program. The buttons must be pressed in this order: 
	 * LoadRoser-> AddAttendnace-> Save-> Plot. If the user attempts to press a button before pressing
	 * the previous, it will not work.
	 */
	public void actionPerformed(ActionEvent event) {
  		String selected = event.getActionCommand();
  		int cont = Main.num;
  		String prev = Main.last;

		DisplayInfo toDis = new DisplayInfo();
  		if(selected == "Load a Roster")
  		{
  			cont = 1;
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

/**
 * @author Isiah Abad and Chad Crum
 *
 * The main class creates the menu and is responsible for creating the main menu of the
 * program and initializing all elements needed for the program. Any menu buttons pushed is passed
 * to MenuActionListener. 
 */
@SuppressWarnings("serial")
public class Main extends JFrame {

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

		JMenu file = new JMenu("File");
		JMenu about = new JMenu("About");

		JMenuItem roster = new JMenuItem("Load a Roster");
		JMenuItem attendance = new JMenuItem("Add Attendance");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem plotData = new JMenuItem("Plot Data");

		roster.addActionListener(new MenuActionListener());
		attendance.addActionListener(new MenuActionListener());
		save.addActionListener(new MenuActionListener());
		plotData.addActionListener(new MenuActionListener());
		
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

		file.add(roster);
		file.add(attendance);
		file.add(save);
		file.add(plotData);
		
		mb.add(file);
		mb.add(about);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,600);
		frame.setJMenuBar(mb);
		frame.setVisible(true);

	 	Listener toListen = new Listener();
	 	data.addObserver(toListen);

		loadRos = new LoadRoster();
		addAtt = new AddAttendance();
		toSave = new Save();
		plot = new Plot();
	}
	/**
	 * This is the main method of the program, it's only job is to call Main() to initialize everything.
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();
	}

}
