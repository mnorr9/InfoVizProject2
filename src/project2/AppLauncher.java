package project2;


import javax.swing.WindowConstants;

import database.KickerBuilder;
import java.util.prefs.Preferences;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nasser
 */
public class AppLauncher {
    
    public static void main(String[] args) {
        MainForm form = new MainForm();
        Visualization scene = new Visualization();
        scene.attachToPanel(form.pnlPrimary);

        Preferences prefsRoot = Preferences.userRoot();
        Preferences myPrefs = prefsRoot.node("edu.rowan.team2.staticPreferenceLoader");
        String file = myPrefs.get("dbFile", "");
        
        
        KickerBuilder kb = new KickerBuilder();
        
        System.out.println(file + "\n");
        if (!file.isEmpty()){
            kb.buildKickerDatabase(file);
        }
        
        form.cmbKicker1.setModel(new javax.swing.DefaultComboBoxModel(kb.getNameList().toArray()));
        form.cmbKicker2.setModel(new javax.swing.DefaultComboBoxModel(kb.getNameList().toArray()));

        Kicker1Action kicker1 = new Kicker1Action(scene, kb, form);
        form.cmbKicker1.addItemListener(kicker1);

        Kicker2Action kicker2 = new Kicker2Action(scene, kb, form);
        form.cmbKicker2.addItemListener(kicker2);
        
        OpenAction openAction = new OpenAction(form, kb);
        form.mnItemOpen.addActionListener(openAction);
        
        form.setVisible(true);
        form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    }
    
}
