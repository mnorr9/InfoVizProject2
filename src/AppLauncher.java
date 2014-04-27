


import javax.swing.WindowConstants;

import database.KickerBuilder;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.prefs.Preferences;



/**
 * This is the application entry point. In addition, is in charge of 
 * instantiating different elements of the application. 
 * @author Nacer Abreu
 */
public class AppLauncher {
    

    public static void main(String[] args) {
        MainForm form = new MainForm();
        Visualization scene = new Visualization();
        scene.attachToPanel(form.pnlPrimary);

        /**
         * 
         */
        Preferences prefsRoot = Preferences.userRoot();
        Preferences myPrefs = prefsRoot.node("edu.rowan.team2.staticPreferenceLoader");
        String file = myPrefs.get("dbFile", "");
        
        
        KickerBuilder kb = new KickerBuilder();
        

        if (!file.isEmpty()){
            kb.buildKickerDatabase(file);
        }
        
        form.cmbRedKicker.setModel(new javax.swing.DefaultComboBoxModel(kb.getNameList().toArray()));
        form.cmbBlueKicker.setModel(new javax.swing.DefaultComboBoxModel(kb.getNameList().toArray()));

        KickerAction kicker1 = new KickerAction(scene, kb, form);
        form.cmbRedKicker.addItemListener(kicker1);
        form.cmbBlueKicker.addItemListener(kicker1);
        
        OpenAction openAction = new OpenAction(form, kb);
        form.mnItemOpen.addActionListener(openAction);
        
        form.setVisible(true);
        form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        centerWindow(form);
        
    }
    
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
    
}
