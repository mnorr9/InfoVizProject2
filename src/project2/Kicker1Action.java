
import database.KickerBuilder;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nabreu
 */
public class Kicker1Action implements ItemListener{
    private final Visualization scene;
    private final KickerBuilder kb;

    Kicker1Action(Visualization scene, KickerBuilder kb) {
        this.scene = scene;
        this.kb = kb;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            String name = event.getItem().toString() ;
            double longestFieldGoal = kb.getKicker(name).getLongestFieldGoal();
            System.out.println("Kicker: " + name + ";  Longet kick: " + longestFieldGoal);
            scene.setPosition1((float) longestFieldGoal);
        }
    }   
}
