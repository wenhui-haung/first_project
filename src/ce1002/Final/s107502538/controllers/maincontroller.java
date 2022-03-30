package ce1002.Final.s107502538.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ce1002.Final.s107502538.*;

public class maincontroller implements Initializable{
	public static player newplayer = new player(500, 30);
	public static int level = 0;
	boolean debug2 = false;
	boolean debug3 = false;
	public void initialize(URL arg0, ResourceBundle arg1) {
		MediaPlayer player;
		Media start = new Media(getClass().getResource("../bgm/background.wav").toString());
		player = new MediaPlayer(start);
		player.setCycleCount(MediaPlayer.INDEFINITE);
		player.play();
		
	}
    @FXML
    void OnExitPressed(ActionEvent event) {
    	Final.mainstage.close();
    }

    @FXML
    void OnHeHeHePressed(ActionEvent event) throws IOException {
        FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/HeHeHe.fxml"));
	    Parent HeHeHe = loadder.load();
	    Scene HeHeHescene = new Scene(HeHeHe);
	    Final.mainstage.setScene(HeHeHescene);
    }

    @FXML
    void OnStartPressed(ActionEvent event) throws IOException {
    	if(debug3) {
    		FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/boss.fxml"));
    		Parent boss = loadder.load();
    		Scene bossscene = new Scene(boss);
    		Boss Ctrl = loadder.getController();
    		bossscene.setOnKeyPressed(Ctrl.onKeyPressed);
    		bossscene.setOnKeyReleased(Ctrl.OnKeyReleased);
    		bossscene.setOnMouseClicked(Ctrl.OnMouseClicked);
    		Final.mainstage.setScene(bossscene);
    	}else if (debug2) {
    		FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/playing2.fxml"));
    		Parent play2 = loadder.load();
    		Scene playscene2 = new Scene(play2);
    		playcontroller2 Ctrl = loadder.getController();
    		playscene2.setOnKeyPressed(Ctrl.onKeyPressed);
    		playscene2.setOnKeyReleased(Ctrl.OnKeyReleased);
    		playscene2.setOnMouseClicked(Ctrl.OnMouseClicked);
    		Final.mainstage.setScene(playscene2);
    	}else {
            FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/playing.fxml"));
    	    Parent play = loadder.load();
    	    Scene playscene = new Scene(play);
    	    playcontroller Ctrl = loadder.getController();
    	    playscene.setOnKeyPressed(Ctrl.onKeyPressed);
    	    playscene.setOnKeyReleased(Ctrl.OnKeyReleased);
    	    playscene.setOnMouseClicked(Ctrl.OnMouseClicked);
    	    Final.mainstage.setScene(playscene);
    	}
    }

    @FXML
    void OnTeachPressed(ActionEvent event) throws IOException {
        FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/teach.fxml"));
	    Parent teach = loadder.load();
	    Scene teachscene = new Scene(teach);
	    Final.mainstage.setScene(teachscene);
    }

}
