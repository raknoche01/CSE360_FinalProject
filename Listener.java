import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
/**
*
*/
public class Listener extends JPanel implements Observer {

	/**
	*
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
