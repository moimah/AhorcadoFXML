package controller;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import model.PartidaModel;
import model.PuntuacionesModel;
import other_classes.ListaPalabras;
import other_classes.ListaPartidas;
import other_classes.Palabra;
import other_classes.Partida;

public class PartidaController implements Initializable {

	private PartidaModel model = new PartidaModel();
	

	private ArrayList<Partida> listaPartidas = new ArrayList<Partida>();

	private Palabra palabra = new Palabra();

	private ArrayList<Palabra> listaPalabras = new ArrayList<Palabra>();

	private ArrayList<Image> listaImages;

	private int intentos = 0;

	private ArrayList<Character> listaLetrasUsadas = new ArrayList<Character>();

	private int puntuacion = 1000;	
	
	MainController mainController; 

	@FXML
	private VBox root;
	@FXML
	private ImageView imvGaleria;
	@FXML
	private Label lblNumPartida;
	@FXML
	private Label lblPuntuacion;
	@FXML
	private Label lblIncognita;
	@FXML
	private TextField txtEntrada;
	@FXML
	private Button btnLetra;
	@FXML
	private Button btnResolver;
	@FXML
	private Label lblLetras;
	@FXML
	private Button btnReset;
	
	/*Metodo inyector, permite cargar el contenido del mainController
	 * dentro de los controllers secundarios, ha de ser usado desde 
	 * el MainController
	 */
	public void injectMainController(MainController mainController){
        this.mainController = mainController;
    }

	public PartidaController() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PartidaView.fxml"));
		loader.setController(this);
		loader.load();
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Inicializar numero partidas
		try {
		listaPartidas = ListaPartidas.leerPartidas(); //Leemos del fichero la lista de partidas
		}catch (Exception e) {
			System.out.println("No se ha cargado el fichero");
		}
		

		// BINDEOS
		model.imageGaleriaProperty().bindBidirectional(imvGaleria.imageProperty()); //Bindeo de image
		Bindings.bindBidirectional(lblNumPartida.textProperty(), model.numPartidaProperty(),new NumberStringConverter()); //BindeosBidirectional con numberConverter
		Bindings.bindBidirectional(lblPuntuacion.textProperty(), model.puntuacionProperty(),new NumberStringConverter()); //BindeosBidirectional con numberConverter
		model.lblIncognitaProperty().bindBidirectional(lblIncognita.textProperty()); //Bindeo de texto
		model.txtEntradaProperty().bindBidirectional(txtEntrada.textProperty()); //Bindeo de texto
		model.lblLetrasUsadasProperty().bindBidirectional(lblLetras.textProperty()); //Bindeo de texto
	
		
		//Metodos de inicio
		inicioPartida();
		listarImagenes();

	}

	// Event Listener on Button[#btnLetra].onAction
	@FXML
	public void clickBtnLetra(ActionEvent event) {
		
		if (puntuacion > 0) {

			if (model.getTxtEntrada().length() == 1) { // Si se ha introducido un solo caracter
				char c = model.getTxtEntrada().charAt(0); // guardarlo en un char

				// Si el caracter se ha encontrado y no se ha resuleto
				if (palabra.encontrarCaracter(c) == false) {
					model.setLblIncognita(palabra.getIncognita()); // Modificamos la incognita
					model.setImageGaleria(listaImages.get(intentos + 1)); // Pasamos a la siguiente imagen
					insertarLetrasUsadas(c); // Insertamos las letras usadas

					// Restamos la puntuacion
					puntuacion = puntuacion - 125;
					// Mostramos la puntuacion
					model.setPuntuacion(puntuacion);
					// Borramos el texto de txtEntrada
					model.setTxtEntrada("");

					intentos++;

					if (puntuacion == 0) {

						// Añadimos la partida a la lista
						listaPartidas.add(new Partida(puntuacion));
						//Enviamos la lista de partidas al Puntuaciones controller
						enviarListaPartida();

						// A continuación confirm de has perdido
						int confirmado = 0;
						Alert alerta = new Alert(AlertType.CONFIRMATION);
						alerta.setTitle("Ahorcado");
						alerta.setHeaderText("Has perdido!");
						alerta.setContentText("¿Quieres intentarlo de nuevo?");

						Optional<ButtonType> result = alerta.showAndWait(); // Almacena el resultado de un boton

						if (result.get() == ButtonType.OK) {
							confirmado = 1;
						}
						if (confirmado == 1) {
							inicioPartida();
						} else {
							/// CERRAR JUEGOOOOOOOOOOOOO
						}

					}

				} else {
					model.setLblIncognita(palabra.getIncognita()); // Modificamos la incognita con la palabra completada

					// Añadimos la partida a la lista
					listaPartidas.add(new Partida(puntuacion));
					//Enviamos la lista de partidas al Puntuaciones controller
					enviarListaPartida();
					
					// A continuacion confirm de enhorabuena
					int confirmado = 0;
					Alert alerta = new Alert(AlertType.CONFIRMATION);
					alerta.setTitle("Ahorcado");
					alerta.setHeaderText("Enhorabueno has ganado! tu puntuación ha sido: " + puntuacion);
					alerta.setContentText("¿Quieres intentarlo de nuevo?");

					Optional<ButtonType> result = alerta.showAndWait(); // Almacena el resultado de un boton

					if (result.get() == ButtonType.OK) {
						confirmado = 1;
					}
					if (confirmado == 1) {
						inicioPartida();
					} else {
						//Cerrar aplicacion
					}

									}
			}
		}

	}

	// Event Listener on Button[#btnResolver].onAction
	@FXML
	public void clickBtnResolver(ActionEvent event) {

		if (puntuacion > 0) {
			if (!palabra.resolver(model.getTxtEntrada())) { // Si no es la palabra
				// Restamos la puntuacion
				puntuacion = puntuacion - 125;
				// Pasamos a la siguiente imagen
				model.setImageGaleria(listaImages.get(intentos + 1)); 
				// Mostramos la puntuacion
				model.setPuntuacion(puntuacion);
				// Borramos el texto de txtEntrada
				model.setTxtEntrada("");
				intentos++;

				if (puntuacion == 0) {

					// Añadimos la partida a la lista
					listaPartidas.add(new Partida(puntuacion));
					//Enviamos la lista de partidas al Puntuaciones controller
					enviarListaPartida();

					// A continuación confirm de has perdido
					int confirmado = 0;
					Alert alerta = new Alert(AlertType.CONFIRMATION);
					alerta.setTitle("Ahorcado");
					alerta.setHeaderText("Has perdido!");
					alerta.setContentText("¿Quieres intentarlo de nuevo?");

					Optional<ButtonType> result = alerta.showAndWait(); // Almacena el resultado de un boton

					if (result.get() == ButtonType.OK) {
						confirmado = 1;
					}
					if (confirmado == 1) {
						inicioPartida();
					} else {
						//TODO cerrar aplicacion
					}
				}

			}else {
				model.setLblIncognita(palabra.getIncognita()); // Modificamos la incognita con la palabra completada

				// Añadimos la partida a la lista
				listaPartidas.add(new Partida(puntuacion));
				//Enviamos la lista de partidas al Puntuaciones controller
				enviarListaPartida();

				// A continuacion confirm de enhorabuena
				int confirmado = 0;
				Alert alerta = new Alert(AlertType.CONFIRMATION);
				alerta.setTitle("Ahorcado");
				alerta.setHeaderText("Enhorabueno has ganado! tu puntuación ha sido: " + puntuacion);
				alerta.setContentText("¿Quieres intentarlo de nuevo?");

				Optional<ButtonType> result = alerta.showAndWait(); // Almacena el resultado de un boton

				if (result.get() == ButtonType.OK) {
					confirmado = 1;
				}
				if (confirmado == 1) {
					inicioPartida();
				} 
			}

		}
	}

	// Event Listener on Button[#btnReset].onAction
	@FXML
	public void clickBtnReset(ActionEvent event) {
		inicioPartida();
	}

	public void inicioPartida() {

		
		Image image = new Image("/images/1.png");
		model.setImageGaleria(image); //Cargar imagen de inicio
		
		try {
			
			//Botones visibles
			
			btnLetra.setVisible(true);
			btnResolver.setVisible(true);
			txtEntrada.setVisible(true);

			
			if(listaPalabras.size()==0) {
			listaPalabras = ListaPalabras.leerPalabras(); //Cargamos la lista fel fichero
			}
			// Inicializar con una palabra aleatoria
			Random randomGenerator = new Random();
			int i = randomGenerator.nextInt(listaPalabras.size());

			// Cargamos la palabra en memoria
			palabra = listaPalabras.get(i);

			// Mostramos la incognita
			model.setLblIncognita(palabra.getIncognita());

			// borramos el contenido de letras usadas y del label
			listaLetrasUsadas = new ArrayList<Character>();
			model.setLblLetrasUsadas("");

			// borramos el contenido del txt de entrada
			model.setTxtEntrada("");

			// Mostrar la puntuacion
			puntuacion = 1000;
			model.setPuntuacion(puntuacion);

			// Inicializar numero de intentos
			intentos = 0;

			// Inicializar numero de partidas

			if (listaPartidas != null) {
				model.setNumPartida(listaPartidas.size());
			}
			
			
			
		} catch (Exception e) {
			btnLetra.setVisible(false);
			btnResolver.setVisible(false);
			txtEntrada.setVisible(false);
			model.setLblIncognita("");
			System.out.println("No hay palabras en el fichero");
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Ahorcado");
			alert.setHeaderText("No hay palabras en el fichero");
			alert.setContentText("Por favor inserta alguna para jugar!");
			alert.showAndWait();
		}
		

	}

	public void listarImagenes() {

		Image image0 = new Image("/images/1.png");
		Image image1 = new Image("/images/2.png");
		Image image2 = new Image("/images/3.png");
		Image image3 = new Image("/images/4.png");
		Image image4 = new Image("/images/5.png");
		Image image5 = new Image("/images/6.png");
		Image image6 = new Image("/images/7.png");
		Image image7 = new Image("/images/8.png");
		Image image8 = new Image("/images/9.png");

		listaImages = new ArrayList<Image>();
		listaImages.add(image0);
		listaImages.add(image1);
		listaImages.add(image2);
		listaImages.add(image3);
		listaImages.add(image4);
		listaImages.add(image5);
		listaImages.add(image6);
		listaImages.add(image7);
		listaImages.add(image8);

	}

	public void insertarLetrasUsadas(char c) {

		String text = "";

		listaLetrasUsadas.add(c);

		for (int i = 0; i < listaLetrasUsadas.size(); i++) {
			text = text + listaLetrasUsadas.get(i) + " ";

		}
		model.setLblLetrasUsadas(text);

	}
	
	public void enviarListaPartida() {		
		//Enviamos la lista de partidas al Puntuaciones controllerPuntuaciones
		mainController.getSubControllerPuntuaciones().actualizarPuntuaciones(this.listaPartidas);
	}
	
	public void guardarPartida() {
		
		ListaPartidas.guardarPartidas(listaPartidas);

	}
	
	
	public ArrayList<Partida> getListaPartidas() {
		return listaPartidas;
	}

	public void setListaPartidas(ArrayList<Partida> listaPartidas) {
		this.listaPartidas = listaPartidas;
	}

	public ArrayList<Palabra> getListaPalabras() {
		return listaPalabras;
	}
	
	

	public void setListaPalabras(ArrayList<Palabra> listaPalabras) {
		this.listaPalabras = listaPalabras;
	}
	
	
	
		

	public PartidaModel getModel() {
		return model;
	}

	
	public Palabra getPalabra() {
		return palabra;
	}

	public ArrayList<Image> getListaImages() {
		return listaImages;
	}

	public int getIntentos() {
		return intentos;
	}

	public ArrayList<Character> getListaLetrasUsadas() {
		return listaLetrasUsadas;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public MainController getMainController() {
		return mainController;
	}

	public VBox getRoot() {
		return root;
	}

	public ImageView getImvGaleria() {
		return imvGaleria;
	}

	public Label getLblNumPartida() {
		return lblNumPartida;
	}

	public Label getLblPuntuacion() {
		return lblPuntuacion;
	}

	public Label getLblIncognita() {
		return lblIncognita;
	}

	public TextField getTxtEntrada() {
		return txtEntrada;
	}

	public Button getBtnLetra() {
		return btnLetra;
	}

	public Button getBtnResolver() {
		return btnResolver;
	}

	public Label getLblLetras() {
		return lblLetras;
	}

	public Button getBtnReset() {
		return btnReset;
	}

	public VBox getView() {
		return root;

	}
		

}
