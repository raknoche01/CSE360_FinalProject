import java.awt.Color;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYZDataset; 

/**
 * @author Isiah Abad and Chad Crum
 * 
 * The Plot class plots the List Entries into a Scatter Plot for the user to see.
 */

@SuppressWarnings("serial")
public class Plot extends JFrame
{
	protected LinkedList<ListEntry> entries = new LinkedList<ListEntry>();
	
	/**
	 * @param entries 
	 * 
	 * is the linked list of names, ID's, programs, asurite, 
	 * and levels of each students in the roster, and
	 * is stored within a temp linked list. 
	 */
	void plotNow(LinkedList<ListEntry> temp)
	{
		    XYDataset dataset = makePlot(temp); 
		    
			JFreeChart chart = ChartFactory.createScatterPlot(  
			        "Student Attendance", "Attendance %", "Count", dataset);  
		  
		    XYPlot plot = (XYPlot)chart.getPlot();  
		    plot.setBackgroundPaint(new Color(255,228,196));  
		   
		    ChartPanel panel = new ChartPanel(chart);  
		    setContentPane(panel);  
		    
		    plotEverything();
	}
	
	
	/**
	 * plotEverything will plot and display
	 * all the extracted data points when
	 * Plot Data button is selected. 
	 */
	private void plotEverything() 
	{
		SwingUtilities.invokeLater(() -> {
			  setTitle("CSE 360 Final Project");
		      setSize(800, 400);  
		      setLocationRelativeTo(null);  
		      setVisible(true);  
		    });  
	}
	
	/**
	 * @param temp is the list of student data
	 * 
	 * The makePlot function will create
	 * the scatter plot using a series of 
	 * data points.
	 * 
	 * @return dataset is the XYSeries collection of times
	 */
	private XYDataset makePlot(LinkedList<ListEntry> temp) {
		entries = temp;
		
		XYSeriesCollection dataset = new XYSeriesCollection();  
		
		int dateCount = entries.get(0).getNumDates();
		double percentage = 0;
		String date; 
		
		LinkedList<XYSeries> seriesList = new LinkedList<XYSeries>(); 
		
		int[] percents = new int[21];
		
		for(int ind = 0; ind < dateCount; ind++) {
			
			date = entries.get(0).dates.get(ind); 
	
			XYSeries ser = new XYSeries(date);
			
			seriesList.add(ser);
			
			for(int pos = 0; pos < 21; pos++)
				percents[pos] = 0;
			
			for(int jnd = 0; jnd < entries.size(); jnd++) {
				if(Double.parseDouble(entries.get(jnd).getTime(date)) >= 75) {
					percentage = 100; 
				}
				else {
					percentage = ((Double.parseDouble(entries.get(jnd).getTime(date)))/75.00) * 100.00;
				}
				
				int loc = ((int)percentage) / 5;
				int val = percents[loc];
				percents[loc] = val + 1;
			}

			for(int loc = 0; loc < 21; loc++)
			{
				if(percents[loc] != 0)
					seriesList.get(ind).add(loc * 5, percents[loc]);
			}
			dataset.addSeries(seriesList.get(ind));	
		}
		return dataset;
	}
}
