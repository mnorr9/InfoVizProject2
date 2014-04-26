package project2;


import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import database.KickerBuilder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nabreu
 */
public class Kicker2Action implements ItemListener{
    private final Visualization scene;
    private final KickerBuilder kb;
    private final MainForm form;

    Kicker2Action(Visualization scene, KickerBuilder kb, MainForm form) {
        this.scene = scene;
        this.kb = kb;
        this.form = form;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            String name = event.getItem().toString() ;
            double longestFieldGoal = kb.getKicker(name).getLongestFieldGoal();
            System.out.println("Blue Kicker2: " + name + ";  Longest kick: " + longestFieldGoal);
            scene.setPosition2((float) longestFieldGoal);
            form.txtTeam2.setText(kb.getKicker(name).getTeamName());
        }
    }   
}
