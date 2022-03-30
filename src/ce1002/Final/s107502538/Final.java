package ce1002.Final.s107502538;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Final extends Application{
	public static Stage mainstage;
	public static Scene mainscene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		FXMLLoader loadder = new FXMLLoader(getClass().getResource("views/startview.fxml"));
		Final.mainstage = arg0;
		Parent main = loadder.load();
		mainscene = new Scene(main);
		mainstage.setTitle("一個好遊戲");
		mainstage.setScene(mainscene);
		Final.mainstage.setResizable(false);
		mainstage.show();
	}

}
