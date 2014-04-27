/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project2;

import database.KickerBuilder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author nasser
 */
public class OpenAction implements ActionListener{
    
    //Create a file chooser
    final JFileChooser fc;
    final MainForm form;
    final KickerBuilder kb;
    Preferences prefsRoot; 
    Preferences myPrefs;
    
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
            
            //This is where a real application would open the file.
            kb.buildKickerDatabase(file.getAbsolutePath());
            form.cmbKicker1.setModel(new javax.swing.DefaultComboBoxModel(kb.getNameList().toArray()));
            form.cmbKicker2.setModel(new javax.swing.DefaultComboBoxModel(kb.getNameList().toArray()));
            myPrefs.put("dbFile", file.getAbsolutePath());
            myPrefs.put("dbDir", file.getPath());
            log = ("Opening: " + file.getAbsolutePath() + "." + "\n");
        } else {
            log = ("Open command cancelled by user." + "\n");
        }
        Logger.getLogger(OpenAction.class.getName()).log(Level.INFO, log);
    }//end of actionPerformed()
    
}//end of class
