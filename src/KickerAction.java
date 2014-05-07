
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import database.KickerBuilder;
import java.awt.Color;



/**
 * This class is an implementation of the ItemListenerClass. It is used to 
 * listen for events from the JComboBoxes in MainForm.  
 * 
 * @author Nacer Abreu, Emmanuel Bonilla, Michael Norris
 */
public class KickerAction implements ItemListener{
    private final Visualization scene;
    private final KickerBuilder kb;
    private final MainForm form;
    private float redLongestFieldGoal;
    private float blueLongestFieldGoal;
    private float redFGP;
    private float blueFGP;

    /**
     * Constructor
     * @param scene Instance of Visualization
     * @param kb Instance of KickerBuilder
     * @param form Instance of MainForm
     */
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
            
            if (event.getSource() == form.cmbRedKicker && name == "....") {
                form.txtRedTeam.setText("");
                form.txtRedGamesPlayed.setText("");
                redLongestFieldGoal = 0;
                redFGP=0;
                scene.setRedWidth(redFGP);
                scene.setXCoordinate(0, "red");
                form.btnGrpPlayers.clearSelection();
            }             
            else if (event.getSource() == form.cmbRedKicker) {
                double longestFieldGoal = kb.getKicker(name).getLongestFieldGoal();
                System.out.println("Red Kicker: " + name + ";  Longest kick: " + longestFieldGoal);
                scene.setXCoordinate((float) longestFieldGoal, "red");
                form.txtRedTeam.setText(kb.getKicker(name).getTeamName());
                redFGP = (float) kb.getKicker(name).getFieldGoalPercentage();
                scene.setRedWidth(redFGP);
                String attempts = Double.toString(kb.getKicker(name).getFieldGoalAttempts());
                form.txtRedGamesPlayed.setText(attempts);
                redLongestFieldGoal = (float) longestFieldGoal;
                form.btnGrpPlayers.clearSelection();

            }// Red Kicker
            
            if (event.getSource() == form.cmbBlueKicker && name == "....") {
                form.txtBlueTeam.setText("");
                form.txtBlueGamesPlayed.setText("");
                blueLongestFieldGoal = -1;
                blueFGP=0;
                scene.setBlueWidth(blueFGP);
                scene.setXCoordinate(0, "blue");
            }             
            else if 
            	(event.getSource() == form.cmbBlueKicker) {
                double longestFieldGoal = kb.getKicker(name).getLongestFieldGoal();
                System.out.println("Blue Kicker: " + name + ";  Longest kick: " + longestFieldGoal);
                scene.setXCoordinate((float) longestFieldGoal, "blue");
                form.txtBlueTeam.setText(kb.getKicker(name).getTeamName());
                blueFGP = (float) kb.getKicker(name).getFieldGoalPercentage();
                scene.setBlueWidth(blueFGP);
                String attempts = Double.toString(kb.getKicker(name).getFieldGoalAttempts());
                form.txtBlueGamesPlayed.setText(attempts);
                blueLongestFieldGoal = (float) longestFieldGoal;
                form.btnGrpPlayers.clearSelection();
            }// Blue Kicker

            if ((name == "...." && redFGP==0) &&
            	(name == "...." && blueFGP==0)) {
//                	form.txtLongestFieldGoal.setText("");
//                    form.txtBestFieldGoalPercentage.setText("");
            } else {
            	determineLongestFieldGoal();
            	determineBestFieldGoalPercentage();
        	}
        }//end of ItemEvent.SELECTED
        
                
    }//end of itemStateChanged()
    
    /**
     * This function is determines which kicker holds the longest field goal 
     * and then populates the MainForm text field 
     * "txtLongestFieldGoal" with the last name of this kicker. If the 
     * percentages are the same, for both kickers, the word "SAME" is used in 
     * place of the kicker's last name.
     */
    private void determineLongestFieldGoal() {

        if (this.redLongestFieldGoal > this.blueLongestFieldGoal) {
            String kicker = form.cmbRedKicker.getSelectedItem().toString();
            String[] name = kicker.split(" ");
//            form.txtLongestFieldGoal.setText(name[1]);
        }

        if (this.redLongestFieldGoal < this.blueLongestFieldGoal) {
            String kicker = form.cmbBlueKicker.getSelectedItem().toString();
            String[] name = kicker.split(" ");
//            form.txtLongestFieldGoal.setText(name[1]);
        }

        if (this.redLongestFieldGoal == this.blueLongestFieldGoal) {
//            form.txtLongestFieldGoal.setText("SAME");
        }
    }//end of determineLongestFieldGoal()

    /**
     * This function is determines which kicker has the best field goal 
     * percentage and then populates the MainForm text field 
     * "txtBestFieldGoalPercentage" with the last name of this kicker. If the 
     * percentages are the same, for both kickers, the word "SAME" is used in 
     * place of the kicker's last name.
     */
    private void determineBestFieldGoalPercentage() {

        if (redFGP == blueFGP) {
//            form.txtBestFieldGoalPercentage.setText("SAME");
        }

        if (redFGP > blueFGP) {
            String kicker = form.cmbRedKicker.getSelectedItem().toString();
            String[] name = kicker.split(" ");
//            form.txtBestFieldGoalPercentage.setText(name[1]);            
        }

        if (redFGP < blueFGP) {
            String kicker = form.cmbBlueKicker.getSelectedItem().toString();
            String[] name = kicker.split(" ");
//            form.txtBestFieldGoalPercentage.setText(name[1]);
            
        }

    }//end of determineBestFieldGoalPercentage()
    
}
