package ce1002.Final.s107502538.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ce1002.Final.s107502538.Final;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class HeHeHecontroller implements Initializable{
	int counter = 0;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MediaPlayer player;
		Media media = new Media(getClass().getResource("../bgm/HeHeHe.mp3").toString());
		player = new MediaPlayer(media);
		player.play();
		Timeline time = new Timeline(new KeyFrame(Duration.millis(1000),(e)-> {
			counter++;
			if(counter == 3) {
				Final.mainstage.close();
			}
		}));
		time.setCycleCount(5);
		time.play();

	}
}
