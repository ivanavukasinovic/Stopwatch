package hellofx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Stopwatch {

	static int milliseconds = 0;
	static int seconds = 0;
	static int minutes = 0;
	static int hours = 0;
	Timeline timeline;

	@FXML
	private Text text;

	@FXML
	private Button start;

	@FXML
	private Button stop;

	@FXML
	private Button reset;

	void change(Text text) {

		if (milliseconds == 1000) {
			seconds++;
			milliseconds = 0;
		}
		if (seconds == 60) {
			minutes++;
			seconds = 0;
		}
		if (minutes == 60) {
			hours++;
			minutes = 0;
		}

		text.setText((((hours / 10) == 0) ? "0" : "") + hours + " : " + (((minutes / 10) == 0) ? "0" : "") + minutes
				+ " : " + (((seconds / 10) == 0) ? "0" : "") + seconds + " : "
				+ (((milliseconds / 10) == 0) ? "00" : (((milliseconds / 100) == 0) ? "0" : "")) + milliseconds++);
	}

	@FXML
	void startPressed(ActionEvent event) {

		timeline = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				change(text);
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(false);
		timeline.play();
	}

	@FXML
	void stopPressed(ActionEvent event) {
		timeline.stop();
	}

	@FXML
	void resetPressed(ActionEvent event) {
		hours = 0;
		minutes = 0;
		seconds = 0;
		milliseconds = 0;

		text.setText("00 : 00 : 00 : 000");
	}

}
