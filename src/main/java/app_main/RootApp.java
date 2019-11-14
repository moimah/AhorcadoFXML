package app_main;

import java.util.ArrayList;

import controller.PalabrasController;
import controller.PartidaController;
import controller.PuntuacionesController;
import controller.MainController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.PartidaModel;
import other_classes.ListaPalabras;
import other_classes.ListaPartidas;
import other_classes.Palabra;

public class RootApp extends Application {

	private MainController controller;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		controller = new MainController();
						
		Scene scene = new Scene(controller.getRoot(), 800, 600);
		
		
		primaryStage.setTitle("Ahorcado");
		primaryStage.setScene(scene);
		primaryStage.setMinHeight(600);
//		primaryStage.setMaxHeight(600);
		primaryStage.setMinWidth(800);	
//		primaryStage.setMaxWidth(800);
		primaryStage.show();

	}
	
	@Override
	public void init() throws Exception {
				
	}
	
	@Override
	public void stop() throws Exception {
		
		ListaPartidas.guardarPartidas(controller.getSubControllerPartida().getListaPartidas()); //Guarda las nuevas partidas en fichero
		ListaPalabras.guardarPalabra(controller.getSubControllerPalabras().getListaPalabras()); //Guarda las nuevas palabras en fichero
		
		
	}
	
             
   
	public static void main(String[] args) {
		launch(args);
	}

}
