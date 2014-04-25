package project2;


import javax.swing.WindowConstants;

import database.KickerBuilder;


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
        MainForm proj = new MainForm();
        Visualization scene = new Visualization();
        scene.attachToPanel(proj.pnlPrimary);

        KickerBuilder kb = new KickerBuilder();
        kb.buildKickerDatabase();
        proj.cmbKicker1.setModel(new javax.swing.DefaultComboBoxModel(kb.getNameList().toArray()));
        proj.cmbKicker2.setModel(new javax.swing.DefaultComboBoxModel(kb.getNameList().toArray()));

        Kicker1Action kicker1 = new Kicker1Action(scene, kb);
        proj.cmbKicker1.addItemListener(kicker1);

        Kicker2Action kicker2 = new Kicker2Action(scene, kb);
        proj.cmbKicker2.addItemListener(kicker2);
        
        proj.setVisible(true);
        proj.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    }
    
}
