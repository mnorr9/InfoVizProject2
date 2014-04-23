
import database.KickerBuilder;
import javax.swing.WindowConstants;


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
        MainForm proj = new MainForm();
        Visualization scene = new Visualization();
        scene.attachToPanel(proj.pnlPrimary);

        KickerBuilder kb = new KickerBuilder();
        kb.buildKickerDatabase();
        proj.cmbKicker1.setModel(new javax.swing.DefaultComboBoxModel(kb.getNameList().toArray()));
        proj.cmbKicker2.setModel(new javax.swing.DefaultComboBoxModel(kb.getNameList().toArray()));

    
        proj.setVisible(true);
        proj.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    }
    
}
