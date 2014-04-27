


import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import database.KickerBuilder;



/**
 * This class is responsible for listening for any events related from the 
 * appropriate combobox widget and calling/triggering the  
 * 
 * @author Nacer Abreu
 */
public class KickerAction implements ItemListener{
    private final Visualization scene;
    private final KickerBuilder kb;
    private final MainForm form;

    KickerAction(Visualization scene, KickerBuilder kb, MainForm form) {
        this.scene = scene;
        this.kb = kb;
        this.form = form;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        
        if (event.getStateChange() == ItemEvent.SELECTED) {
            String name = event.getItem().toString();
            double longestFieldGoal = kb.getKicker(name).getLongestFieldGoal();
            
            if (event.getSource() == form.cmbRedKicker) {
                System.out.println("Red Kicker: " + name + ";  Longest kick: " + longestFieldGoal);
                scene.setXCoordinate((float) longestFieldGoal, "red");
                form.txtRedTeam.setText(kb.getKicker(name).getTeamName());
                // scene.setRedWidth((float) kb.getKicker(name).getNumberOfFumbles() / 100);
                String attempts = Double.toString(kb.getKicker(name).getFieldGoalAttempts());
                form.txtRedGamesPlayed.setText(attempts);
            }// Red Kicker
            
            if (event.getSource() == form.cmbBlueKicker) {
                System.out.println("Blue Kicker: " + name + ";  Longest kick: " + longestFieldGoal);
                scene.setXCoordinate((float) longestFieldGoal, "blue");
                form.txtBlueTeam.setText(kb.getKicker(name).getTeamName());
                //scene.setBlueWidth((float) kb.getKicker(name).getFieldGoalPercentage() / 100);
                String attempts = Double.toString(kb.getKicker(name).getFieldGoalAttempts());
                form.txtBlueGamesPlayed.setText(attempts);
            }// Blue Kicker
            
            
        }//end of ItemEvent.SELECTED
    }//end of itemStateChanged()   
}
