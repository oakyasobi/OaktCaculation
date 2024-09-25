package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TheMain extends Application{

	public static void main(String[] args) {		
		launch(args);

	}

	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("TheMainForm.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
}
