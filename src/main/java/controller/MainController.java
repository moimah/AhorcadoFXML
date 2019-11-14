package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Tab;

public class MainController implements Initializable {
	
	private PartidaController subControllerPartida;
	
	private PalabrasController subControllerPalabras;
		
	private PuntuacionesController subControllerPuntuaciones;
	
	@FXML
	private TabPane root;
	@FXML
	private Tab tabPartida;
	@FXML
	private Tab tabPalabras;
	@FXML
	private Tab tabPuntuaciones;
	
	
	
	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RootView.fxml"));
		loader.setController(this);
		loader.load();

	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		try {
			subControllerPartida = new PartidaController();
			subControllerPalabras = new PalabrasController();
			subControllerPuntuaciones = new PuntuacionesController();
			
			// TODO resto de controladores
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Obtenemos los view y los configuramos (Esto lo hacemos porque no lo recoge las propiedades de aligment)
		
		VBox viewPartida = new VBox(subControllerPartida.getView());
		viewPartida.setAlignment(Pos.CENTER);
		VBox viewPalabras = new VBox(subControllerPalabras.getView());
		viewPalabras.setAlignment(Pos.CENTER);
		VBox viewPuntuaciones = new VBox(subControllerPuntuaciones.getView());
		viewPuntuaciones.setAlignment(Pos.CENTER);
		
		//Agregamos los View a los tabs correspondientes
		
		tabPartida.setContent(viewPartida);					
		tabPalabras.setContent(viewPalabras);	
		tabPuntuaciones.setContent(viewPuntuaciones);
		
		
		//Inyeccion de mainController a los subcontrollers
	
		subControllerPartida.injectMainController(this); 
		subControllerPalabras.injectMainController(this); 	
		
		
	}



	public TabPane getRoot() {
		return root;
	}



	public PartidaController getSubControllerPartida() {
		return subControllerPartida;
	}



	public PalabrasController getSubControllerPalabras() {
		return subControllerPalabras;
	}



	public PuntuacionesController getSubControllerPuntuaciones() {
		return subControllerPuntuaciones;
	}

	
	

	

}
