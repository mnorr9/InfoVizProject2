



import database.KickerBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;

/**
 * Implementation of the actionlistener associated with the "Open" menu item 
 * under the "File" menu item.  
 * @author Nacer Abreu
 */
public class OpenAction implements ActionListener{
    
    //Create a file chooser
    final JFileChooser fc;
    final MainForm form;
    final KickerBuilder kb;
    Preferences prefsRoot; 
    Preferences myPrefs;
    
    /**
     * Constructor
     * @param form Instance of MainForm
     * @param kb  Instance of KickerBuilder
     */
    public OpenAction(MainForm form, KickerBuilder kb) {
        prefsRoot = Preferences.userRoot();
        myPrefs = prefsRoot.node("edu.rowan.team2.staticPreferenceLoader");
        String dbDir = myPrefs.get("dbDir", "");
        this.fc = new JFileChooser(dbDir);
        this.form = form;
        this.kb = kb;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        int returnVal = fc.showOpenDialog(form);
        String log = "";

        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            kb.buildKickerDatabase(file.getAbsolutePath());
            form.cmbRedKicker.setModel(new javax.swing.DefaultComboBoxModel(kb.getNameList().toArray()));
            form.cmbBlueKicker.setModel(new javax.swing.DefaultComboBoxModel(kb.getNameList().toArray()));
            
            form.cmbRedKicker.insertItemAt("....", 0);
            form.cmbRedKicker.setSelectedIndex(0);
            form.cmbBlueKicker.insertItemAt("....", 0);
            form.cmbBlueKicker.setSelectedIndex(0);
            
            myPrefs.put("dbFile", file.getAbsolutePath());
            myPrefs.put("dbDir", file.getPath());
            log = ("Opening: " + file.getAbsolutePath() + "." + "\n");
        } else {
            log = ("Open command cancelled by user." + "\n");
        }
        Logger.getLogger(OpenAction.class.getName()).log(Level.INFO, log);
    }//end of actionPerformed()
    
}//end of class
