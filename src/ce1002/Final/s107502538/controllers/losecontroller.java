package ce1002.Final.s107502538.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ce1002.Final.s107502538.Final;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class losecontroller implements Initializable {
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		_showlayer.setText("§¹¦¨¼h¼Æ : Level " + ((maincontroller.level + 2) / 3 - 1));
	}
	
    @FXML
    private Label _showlayer;
    
    @FXML
    void OnAgainPressed(ActionEvent event) throws IOException {
    	maincontroller.level = 0;
		maincontroller.newplayer.sethp(500);
		maincontroller.newplayer.setmaxhp(500);
		maincontroller.newplayer.setatk(30);
        FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/playing.fxml"));
	    Parent play = loadder.load();
	    Scene playscene = new Scene(play);
	    playcontroller Ctrl = loadder.getController();
	    playscene.setOnKeyPressed(Ctrl.onKeyPressed);
	    playscene.setOnKeyReleased(Ctrl.OnKeyReleased);
	    playscene.setOnMouseClicked(Ctrl.OnMouseClicked);
	    Final.mainstage.setScene(playscene);
    }

    @FXML
    void OnExitPressed(ActionEvent event) {
    	Final.mainstage.close();
    }
}
