
import javax.swing.WindowConstants;
import database.KickerBuilder;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.prefs.Preferences;



/**
 * This is the application entry point. In addition, it is in charge of 
 * instantiating different parts of the application. 
 * @author Nacer Abreu, David Gwalthney, Emmanuel Bonilla
 */
public class AppLauncher {
    

    public static void main(String[] args) {
        MainForm form = new MainForm();
        Visualization scene = new Visualization();
        
        /** Attaches the GLCanvas to JPanel in MainForm */
        scene.attachToPanel(form.pnlPrimary); 

        Preferences prefsRoot = Preferences.userRoot();
        Preferences myPrefs = prefsRoot.node("edu.rowan.team2.staticPreferenceLoader");
        
        KickerBuilder kb = new KickerBuilder();
        
        String file = myPrefs.get("dbFile", "");
        if (!file.isEmpty()){
            kb.buildKickerDatabase(file);
        }
        
        form.cmbRedKicker.setModel(
              new javax.swing.DefaultComboBoxModel(kb.getNameList().toArray()));
        form.cmbBlueKicker.setModel(
              new javax.swing.DefaultComboBoxModel(kb.getNameList().toArray()));

        KickerAction kickerAction = new KickerAction(scene, kb, form);
        form.cmbRedKicker.insertItemAt("....", 0);
        form.cmbRedKicker.setSelectedIndex(0);
        form.cmbRedKicker.addItemListener(kickerAction);
        form.cmbBlueKicker.insertItemAt("....", 0);
        form.cmbBlueKicker.setSelectedIndex(0);
        form.cmbBlueKicker.addItemListener(kickerAction);
        
        OpenAction openAction = new OpenAction(form, kb);
        form.mnItemOpen.addActionListener(openAction);
        
        // Exit
        form.mnItemExit.addActionListener(new ExitAction());
        
	    // CheckBox Setting
        form.ckBoxLongest.addActionListener(new QuckPickAction(scene, form, kb));
        form.ckBoxAverage.addActionListener(new QuckPickAction(scene, form, kb));
        form.ckBoxShortest.addActionListener(new QuckPickAction(scene, form, kb));
        
        form.setVisible(true);
        form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        centerWindow(form);
        
    }
    
    /**
     * This function ensures that the application main form displays at the 
     * center of the user screen. 
     * @param form  MainForm instance 
     */
    public static void centerWindow(MainForm form) 
    {
        Dimension screenSize =
            Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = form.getSize();
        if (frameSize.width > screenSize.width )
            frameSize.width = screenSize.width;
        if (frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
        form.setLocation (
                   (screenSize.width - frameSize.width ) >> 1,
                   (screenSize.height - frameSize.height) >> 1
                   );
    }
    
}//end of class
