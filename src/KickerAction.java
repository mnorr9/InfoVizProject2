


import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import database.KickerBuilder;
import java.awt.Color;



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
    private float redLongestFieldGoal;
    private float blueLongestFieldGoal;
    private float redFGP;
    private float blueFGP;

    KickerAction(Visualization scene, KickerBuilder kb, MainForm form) {
        this.scene = scene;
        this.kb = kb;
        this.form = form;
        this.redLongestFieldGoal = 0;
        this.blueLongestFieldGoal = 0;
        this.redFGP = 0f;
        this.blueFGP = 0f;
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
                redFGP = (float) kb.getKicker(name).getFieldGoalPercentage();
                scene.setRedWidth(redFGP);
                String attempts = Double.toString(kb.getKicker(name).getFieldGoalAttempts());
                form.txtRedGamesPlayed.setText(attempts);
                redLongestFieldGoal = (float) longestFieldGoal;
            }// Red Kicker
            
            if (event.getSource() == form.cmbBlueKicker) {
                System.out.println("Blue Kicker: " + name + ";  Longest kick: " + longestFieldGoal);
                scene.setXCoordinate((float) longestFieldGoal, "blue");
                form.txtBlueTeam.setText(kb.getKicker(name).getTeamName());
                blueFGP = (float) kb.getKicker(name).getFieldGoalPercentage();
                scene.setBlueWidth(blueFGP);
                String attempts = Double.toString(kb.getKicker(name).getFieldGoalAttempts());
                form.txtBlueGamesPlayed.setText(attempts);
                blueLongestFieldGoal = (float) longestFieldGoal;
            }// Blue Kicker
        }//end of ItemEvent.SELECTED
        
        determineLongestFieldGoal();
        determineBestFieldGoalPercentage();
                
    }//end of itemStateChanged()
    
    private void determineLongestFieldGoal() {

        if (this.redLongestFieldGoal > this.blueLongestFieldGoal) {
            String kicker = form.cmbRedKicker.getSelectedItem().toString();
            String[] name = kicker.split(" ");
            form.txtLongestFieldGoal.setText(name[1]);
        }

        if (this.redLongestFieldGoal < this.blueLongestFieldGoal) {
            String kicker = form.cmbBlueKicker.getSelectedItem().toString();
            String[] name = kicker.split(" ");
            form.txtLongestFieldGoal.setText(name[1]);
        }

        if (this.redLongestFieldGoal == this.blueLongestFieldGoal) {
            form.txtLongestFieldGoal.setText("SAME");
        }
    }//end of determineLongestFieldGoal()
    
    private void determineBestFieldGoalPercentage() {



        if (redFGP == blueFGP) {
            form.txtBestFieldGoalPercentage.setText("SAME");
        }

        if (redFGP > blueFGP) {
            String kicker = form.cmbRedKicker.getSelectedItem().toString();
            String[] name = kicker.split(" ");
            form.txtBestFieldGoalPercentage.setText(name[1]);            
        }

        if (redFGP < blueFGP) {
            String kicker = form.cmbBlueKicker.getSelectedItem().toString();
            String[] name = kicker.split(" ");
            form.txtBestFieldGoalPercentage.setText(name[1]);
            
        }

    }//end of determineBestFieldGoalPercentage()
    
}
