import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
/**
 * @author Chad Crum
 * 
 * This class is responsible for reprinting the information on screen when notified by Data.
 */
public class Listener extends JPanel implements Observer {

	/**
	 * This method is called when Data notifies it. It will remove the old information on the
	 * screen and reprint the new information onto the screen.
	 */
	@Override
	public void update(Observable itm, Object obj) {
		try{
			DisplayInfo.studInfo.setVisible(false);
			DisplayInfo.scrollStud.setVisible(false);
		}catch(Throwable ex)
		{

		}

		DisplayInfo toDis = new DisplayInfo();

		toDis.displayNow(((Data)itm).getEntries());
		Main.frame.add(DisplayInfo.scrollStud);
		Main.frame.revalidate();
		Main.frame.repaint();

		DisplayInfo.studInfo.setVisible(true);
		DisplayInfo.scrollStud.setVisible(true);
  		Main.frame.setVisible(true);
	}
}
