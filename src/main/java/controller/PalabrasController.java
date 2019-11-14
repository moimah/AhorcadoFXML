package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.layout.VBox;
import model.PalabrasModel;
import model.PartidaModel;
import other_classes.ListaPalabras;
import other_classes.Palabra;

public class PalabrasController implements Initializable {
	
	private MainController mainController; 
	
	private PalabrasModel model = new PalabrasModel();
	
	private ArrayList<Palabra> listaPalabras = new ArrayList<Palabra>();	
	
	private ObservableList<String> observableTextoPalabra;
	
	private ArrayList<String> listaTextoPalabra ;
	
	@FXML
	private VBox root;
	@FXML
	private ListView listViewPalabras;
	@FXML
	private Button btnAnadir;
	@FXML
	private Button btnQuitar;
	
	/*Metodo inyector, permite cargar el contenido del mainController
	 * dentro de los controllers secundarios, ha de ser usado desde 
	 * el MainController
	 */
	public void injectMainController(MainController mainController){
        this.mainController = mainController;
    }
	
	
	public PalabrasController() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PalabrasView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//BIDEOS DE LISTAS
		model.listaPalabrasProperty().bindBidirectional(listViewPalabras.itemsProperty()); //Bindeo del listView
		model.palabraSeleccionadaProperty().bind(listViewPalabras.getSelectionModel().selectedItemProperty()); //Bindeo del seleccionado
								
		try {
			
			listaPalabras = ListaPalabras.leerPalabras(); //Caramos en una lista las palabras
			obtenerStringPalabra(listaPalabras); //Sacamos el String de las palabras y lo cargamos en "listaTextoPalabra"
			
		} catch (Exception e) {
				System.out.println("No se ha cargado la lista de palabras");
		}
		
		
	}

	// Event Listener on Button[#btnAnadir].onAction
	@FXML
	public void clickBtnAnadir(ActionEvent event) {
		
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Ahorcado");
		dialog.setHeaderText("Introduce una palabra");
		dialog.setContentText("Escribela a continuación:");

		
		Optional<String> result = dialog.showAndWait();
				
		if (result.get().length()>1){ //SI se ha introducido texto almacenar la palabra
			guardarNuevaPalabra(result.get());
			mainController.getSubControllerPartida().setListaPalabras(listaPalabras); //Cargamos la lista de palabras actualizadas al controllerPartida			
			mainController.getSubControllerPartida().inicioPartida();//Pruebas
		}

		
	}
	// Event Listener on Button[#btnQuitar].onAction
	@FXML
	public void btnQuitar(ActionEvent event) {
		
		try {			
			eliminarPalabra(model.getPalabraSeleccionada()); //Eliminamos la palabra seleccionada			
//			mainController.getSubControllerPartida().setListaPalabras(listaPalabras); //Cargamos la lista de palabras actualizadas al controllerPartida
//			mainController.getSubControllerPartida().inicioPartida();//Pruebas
		} catch (Exception e) {
			System.out.println("Error al eliminar");
		}
	
	}
	
	/*Extramos los String de los objetos palabra
	 * del ArrayList listaPlabras
	 */
	public void obtenerStringPalabra(ArrayList lista) {
		
		listaTextoPalabra =  new ArrayList<String>(); //Inicializamos la listaTexto palabra
		try {			
		for(int i=0; i<lista.size(); i++) { //Extrae el texto de las palabras de los objeto palabra de la lista
			listaTextoPalabra.add(listaPalabras.get(i).getPalabra());	//Añade a la lista de textos el nuevo objeto
		}
		
		observableTextoPalabra = FXCollections.observableArrayList(listaTextoPalabra);
		model.setListaPalabras(observableTextoPalabra);
		}catch (Exception e) {
			System.out.println("No se ha podido extraer el String");
		}	
	}
	
	/*Elimina una palabra del listView y de la 
	 * listaPalabras, actualiza la vista con los 
	 * resultados
	 */
	
	public void eliminarPalabra(String palabra) {
			
		
		for(int i=0; i<listaPalabras.size(); i++) {		
			if(listaPalabras.get(i).getPalabra().equals(palabra)) {
				listaPalabras.remove(i); //Eliminamos el objeto en la lista  de objetos Palabra
				System.out.println("SE ha eliminado");
			}
		}
				
		obtenerStringPalabra(listaPalabras); //Actualizamos el ListView de palabras
		
		mainController.getSubControllerPartida().setListaPalabras(listaPalabras); //Cargamos la lista de palabras actualizadas al controllerPartida
		mainController.getSubControllerPartida().inicioPartida();//Pruebas
				
		
	}
	
	/*Guarda una nueva palabra a partir de un String
	 * en listaPalabras, actualiza el listView
	 */
	public void guardarNuevaPalabra(String palabra) {
		Palabra p = new Palabra();
		p.setPalabra(palabra);
		listaPalabras.add(p);
		obtenerStringPalabra(listaPalabras); //Actualizamos el ListView de palabras
		
	}
	

	public ArrayList<Palabra> getListaPalabras() {
		return listaPalabras;
	}

	public void setListaPalabras(ArrayList<Palabra> listaPalabras) {
		this.listaPalabras = listaPalabras;
	}

	public VBox getView() {
		return root;
	}


	
	
}
