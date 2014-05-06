import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import database.Kicker;
import database.KickerBuilder;


public class QuckPickAction implements ActionListener {

	
	final MainForm form;
	final KickerBuilder kickerBuilder;

	public QuckPickAction(MainForm form, KickerBuilder kb) {
		this.kickerBuilder = kb;
		this.form = form;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		
		if (actionEvent.getSource() == form.ckBoxLongest) {
			System.out.println("Check Box One Picked:");
			showLongestKick();
		} else if (actionEvent.getSource() == form.ckBoxAverage) {
			System.out.println("Check Box 2 Picked:");
			showAvgKick();
		} else if (actionEvent.getSource() == form.ckBoxShortest) {
			System.out.println("Check Box 3 Picked:");
			showShortKick();
		}
		
		findHighestPointPlayer();
		findLongestKickPlayer();
		
	}
	
	public void showLongestKick() {
		System.out.println("Longest FG: "+kickerBuilder.getLongestFg());
	}
	
	public void showAvgKick() {
		System.out.println("Avg FG: "+kickerBuilder.getAvgFg());
	}
	
	public void showShortKick() {
		System.out.println("Shortest FG: "+kickerBuilder.getShortestFg());
	}
	
	public void findHighestPointPlayer() {
		Kicker highPtKicker = kickerBuilder.getPlayerHighestPoints();
		System.out.println("Highest Player: "+highPtKicker.getPlayerName());
		System.out.println("Highest Points: "+highPtKicker.getPoints());
	}
	
	public void findLongestKickPlayer() {
		Kicker longestKicker = kickerBuilder.getPlayerLongestKick();
		System.out.println("Longest Kick Player: "+longestKicker.getPlayerName());
		System.out.println("Longest Kick : "+longestKicker.getLongestFieldGoal());
	}

}
