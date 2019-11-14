package model;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;


public class PartidaModel {

	private ListProperty<String>  listaPalabras = new SimpleListProperty<String>(FXCollections.observableArrayList());
	private StringProperty palabraSeleccionada = new SimpleStringProperty();
	
	private IntegerProperty numPartida = new SimpleIntegerProperty();
	private DoubleProperty puntuacion = new SimpleDoubleProperty();	
	private StringProperty lblIncognita = new SimpleStringProperty();
	private StringProperty lblLetrasUsadas = new SimpleStringProperty(); 
	private StringProperty txtEntrada = new SimpleStringProperty();		
	
	private ObjectProperty<javafx.scene.image.Image> imageGaleria = new SimpleObjectProperty<javafx.scene.image.Image>();
	
	
	public final ListProperty<String> listaPalabrasProperty() {
		return this.listaPalabras;
	}
	

	public final ObservableList<String> getListaPalabras() {
		return this.listaPalabrasProperty().get();
	}
	

	public final void setListaPalabras(final ObservableList<String> listaPalabras) {
		this.listaPalabrasProperty().set(listaPalabras);
	}
	

	public final IntegerProperty numPartidaProperty() {
		return this.numPartida;
	}
	

	public final int getNumPartida() {
		return this.numPartidaProperty().get();
	}
	

	public final void setNumPartida(final int numPartida) {
		this.numPartidaProperty().set(numPartida);
	}
	

	public final DoubleProperty puntuacionProperty() {
		return this.puntuacion;
	}
	

	public final double getPuntuacion() {
		return this.puntuacionProperty().get();
	}
	

	public final void setPuntuacion(final double puntuacion) {
		this.puntuacionProperty().set(puntuacion);
	}
	

	public final StringProperty lblIncognitaProperty() {
		return this.lblIncognita;
	}
	

	public final String getLblIncognita() {
		return this.lblIncognitaProperty().get();
	}
	

	public final void setLblIncognita(final String lblIncognita) {
		this.lblIncognitaProperty().set(lblIncognita);
	}
	

	public final StringProperty lblLetrasUsadasProperty() {
		return this.lblLetrasUsadas;
	}
	

	public final String getLblLetrasUsadas() {
		return this.lblLetrasUsadasProperty().get();
	}
	

	public final void setLblLetrasUsadas(final String lblLetrasUsadas) {
		this.lblLetrasUsadasProperty().set(lblLetrasUsadas);
	}
	

	public final StringProperty txtEntradaProperty() {
		return this.txtEntrada;
	}
	

	public final String getTxtEntrada() {
		return this.txtEntradaProperty().get();
	}
	

	public final void setTxtEntrada(final String txtEntrada) {
		this.txtEntradaProperty().set(txtEntrada);
	}
	

	public final ObjectProperty<javafx.scene.image.Image> imageGaleriaProperty() {
		return this.imageGaleria;
	}
	

	public final Image getImageGaleria() {
		return this.imageGaleriaProperty().get();
	}
	

	public final void setImageGaleria(final Image imageGaleria) {
		this.imageGaleriaProperty().set(imageGaleria);
	}


	public final StringProperty palabraSeleccionadaProperty() {
		return this.palabraSeleccionada;
	}
	


	public final String getPalabraSeleccionada() {
		return this.palabraSeleccionadaProperty().get();
	}
	


	public final void setPalabraSeleccionada(final String palabraSeleccionada) {
		this.palabraSeleccionadaProperty().set(palabraSeleccionada);
	}
	
	

	

	
	
}
