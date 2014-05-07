import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonModel;

import database.Kicker;
import database.KickerBuilder;


public class QuckPickAction implements ActionListener {


	final MainForm form;
	final KickerBuilder kickerBuilder;
	final Visualization scene;

	public QuckPickAction(Visualization scene, MainForm form, KickerBuilder kb) {
		this.kickerBuilder = kb;
		this.form = form;
		this.scene = scene;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

		if (actionEvent.getSource() == form.ckBoxLongest) {
			showLongestKick();
		} else if (actionEvent.getSource() == form.ckBoxAverage) {
			showAvgKick();
		} else if (actionEvent.getSource() == form.ckBoxShortest) {
			showShortKick();
		} else if (actionEvent.getSource() == form.rdBtnBest) {
			showHighestFgPlayer();
		}  else if (actionEvent.getSource() == form.rdBtnLongest) {
			showLongestKickPlayer();
		} else if (actionEvent.getSource() == form.rdBtnPoints) {
			showHighestPointPlayer();
		}

	}

	public void showLongestKick() {
		System.out.println("Longest FG: "+kickerBuilder.getLongestFg());
		scene.enableLongest((float )kickerBuilder.getLongestFg());
	}

	public void showAvgKick() {
		System.out.println("Avg FG: "+kickerBuilder.getAvgFg());
		scene.enableAverage((float) kickerBuilder.getAvgFg());
	}

	public void showShortKick() {
		System.out.println("Shortest FG: "+kickerBuilder.getShortestFg());
		scene.enableShortest((float) kickerBuilder.getShortestFg());
	}

	public void showHighestPointPlayer() {
		Kicker highPtKicker = kickerBuilder.getPlayerHighestPoints();
		setQuickSearchPlayer(highPtKicker);
	}

	public void showLongestKickPlayer() {
		Kicker longestKicker = kickerBuilder.getPlayerLongestKick();
		setQuickSearchPlayer(longestKicker);
	}

	public void showHighestFgPlayer() {
		Kicker highestFgKicker = kickerBuilder.getPlayerHighestFgPct();
		setQuickSearchPlayer(highestFgKicker);
	}
	
	public void setQuickSearchPlayer(Kicker kicker) {
		form.cmbBlueKicker.setSelectedIndex(0);
		// get selected item
		ButtonModel bm = form.btnGrpPlayers.getSelection();
        form.cmbRedKicker.setSelectedItem(kicker.getPlayerName());
        //set select Item
        form.btnGrpPlayers.setSelected(bm, true);
	}

}
