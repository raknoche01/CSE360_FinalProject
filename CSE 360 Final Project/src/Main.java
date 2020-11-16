import javax.swing.*;

/**
 * 
 * @author Isiah Abad
 * 
 * The main class creates the menu 
 *
 */


@SuppressWarnings("serial")
public class Main extends JFrame {

	//create two association variables
	//1 for the controller 
	//another for the panel 
	
	public Main(){
		
		JFrame frame = new JFrame("CSE360 Final Project");
		JMenuBar mb = new JMenuBar();
	
		//creating the menus
		JMenu file = new JMenu("File");
		JMenu about = new JMenu("About");
		
		// menu items
		JMenuItem roster = new JMenuItem("Load a Roster");
		JMenuItem attendance = new JMenuItem("Add Attendance");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem plotData = new JMenuItem("Plot Data");

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
		
		
	}
	public static void main(String[] args) {
		new Main();
	}

}