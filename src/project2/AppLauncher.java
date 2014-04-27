package project2;


import javax.swing.WindowConstants;
import database.KickerBuilder;
import java.util.prefs.Preferences;



/**
 * This is the application entry point. In addition, is in charge of 
 * instantiating different different elements of the application. 
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

//        Kicker2Action kicker2 = new Kicker2Action(scene, kb, form);
        form.cmbBlueKicker.addItemListener(kicker1);
        
        OpenAction openAction = new OpenAction(form, kb);
        form.mnItemOpen.addActionListener(openAction);
        
        form.setVisible(true);
        form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    }
    
}
