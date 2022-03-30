package ce1002.Final.s107502538.controllers;

import java.io.IOException;

import ce1002.Final.s107502538.Final;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class teachcontroller {

    @FXML
    void OnReturnPressed(ActionEvent event) throws IOException {
    	Final.mainstage.setScene(Final.mainscene);
    }

}
