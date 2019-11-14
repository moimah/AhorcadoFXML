package model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import other_classes.Partida;

public class PuntuacionesModel {
	
	private ListProperty<Partida>  listaPuntuaciones = new SimpleListProperty<Partida>(FXCollections.observableArrayList());

	public final ListProperty<Partida> listaPuntuacionesProperty() {
		return this.listaPuntuaciones;
	}
	

	public final ObservableList<Partida> getListaPuntuaciones() {
		return this.listaPuntuacionesProperty().get();
	}
	

	public final void setListaPuntuaciones(final ObservableList<Partida> listaPuntuaciones) {
		this.listaPuntuacionesProperty().set(listaPuntuaciones);
	}
	

		
	
	

}
