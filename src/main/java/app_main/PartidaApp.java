package app_main;

import controller.PartidaController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PartidaApp extends Application {

	private PartidaController controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		controller = new PartidaController();
		
		Scene scene = new Scene(controller.getView(), 800, 600);
		
		primaryStage.setTitle("Ahorcado");
		primaryStage.setScene(scene);
		primaryStage.setMinHeight(600);
		primaryStage.setMinWidth(800);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
