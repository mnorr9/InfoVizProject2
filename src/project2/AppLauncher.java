package project2;

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
        @SuppressWarnings("unused")
        Project2 proj = new Project2();
        Visualization scene = new Visualization();
        scene.attachToPanel(proj.pnlPrimary);
        
        //Visualization vis = new Visualization(proj.pnlPrimary);
        KickerBuilder kb = new KickerBuilder();
		kb.buildKickerDatabase();

		proj.cmbKicker1.setModel(new javax.swing.DefaultComboBoxModel(kb.getNameList().toArray()));
    
        proj.setVisible(true);
    }
    
}
