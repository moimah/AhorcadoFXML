package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javax.sound.midi.Soundbank;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import model.PalabrasModel;
import model.PartidaModel;
import model.PuntuacionesModel;
import other_classes.ListaPartidas;
import other_classes.Palabra;
import other_classes.Partida;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PuntuacionesController implements Initializable {
	
	private MainController mainController; 
	
    private PuntuacionesModel model = new PuntuacionesModel();

	private ArrayList<Partida> listaPartidas = new ArrayList<Partida>(); 	
	
	private ObservableList<Partida> observableTextoPartida;
	
	
	
	@FXML
	private VBox root;
	@FXML
	private TableView tablePuntuaciones;
	@FXML
	private TableColumn colPuntuacion;
	@FXML
	private TableColumn colFecha;
		
	
	public PuntuacionesController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PuntuacionesView.fxml"));
		loader.setController(this);
		loader.load();
		
	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		model.listaPuntuacionesProperty().bindBidirectional(tablePuntuaciones.itemsProperty()); //Sin probar
		
		 colPuntuacion.setCellValueFactory(new PropertyValueFactory<Partida, Integer>("puntuacion")); //Dar valor a la columna la tabla
		 colFecha.setCellValueFactory(new PropertyValueFactory<Partida, String>("fecha")); //Dar valor a la columna de la tabla
		 
		 
		 try {
			listaPartidas = ListaPartidas.leerPartidas(); // Cargamos la lista de partidas del fichero						
			Collections.sort(listaPartidas); // Ordenamos por puntuación
			observableTextoPartida = FXCollections.observableArrayList(listaPartidas); //Cargamos la lista en un observableList
			model.setListaPuntuaciones(observableTextoPartida);
			
			
		} catch (Exception e) {
			System.out.println("Lo intento");
		}
		
	}
	
	/*Metodo que recibe un ArrayList de partidas y
	 * actualiza el TableView de partidas ordenadolas
	 * por su puntuacion de mayor a menor
	 */

	public void actualizarPuntuaciones(ArrayList<Partida> lista) {
		Collections.sort(lista); //Ordenamos la lista por puntuacion
		observableTextoPartida = FXCollections.observableArrayList(lista); //Cargamos la lista en un observableList
		model.setListaPuntuaciones(observableTextoPartida);
		
	}
	
	
	public ArrayList<Partida> getListaPartidas() {
		return listaPartidas;
	}



	public void setListaPartidas(ArrayList<Partida> listaPartidas) {
		this.listaPartidas = listaPartidas;		
	}



	public VBox getView() {
		return root;
	}

}
