package model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PalabrasModel {
	
	private ListProperty<String>  listaPalabras = new SimpleListProperty<String>(FXCollections.observableArrayList());
	private StringProperty palabraSeleccionada = new SimpleStringProperty();
	public final ListProperty<String> listaPalabrasProperty() {
		return this.listaPalabras;
	}
	
	public final ObservableList<String> getListaPalabras() {
		return this.listaPalabrasProperty().get();
	}
	
	public final void setListaPalabras(final ObservableList<String> listaPalabras) {
		this.listaPalabrasProperty().set(listaPalabras);
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
