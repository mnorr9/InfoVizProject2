import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implementation of the actionlistener associated with the "Exit" menu item 
 * under the "File" menu item.  
 * @author Michael Norris
 */
public class ExitAction implements ActionListener {
	
	@Override
    public void actionPerformed(ActionEvent e) {
       System.exit(0);
    }//end of actionPerformed()


}
