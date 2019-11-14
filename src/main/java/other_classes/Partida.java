package other_classes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Partida implements Serializable, Comparable<Partida> {
	
	
	private double puntuacion;
	private String fecha;
	
	
		
	
	public Partida(double puntuacion) {	
		cargarFecha();
		this.puntuacion = puntuacion;
		
	}
	
	public Partida() {		
		this.puntuacion = puntuacion;
	}




	public String getFecha() {
		return fecha;
	}





	public double getPuntuacion() {
		return puntuacion;
	}




	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
		cargarFecha();	
	}
		
	//Setear cada vez que se instancie 
	private void cargarFecha() {		
		fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());	
	}

	
	
	/*Comparator por puntuacion para poder
	 * ordenar objetos de este tipo
	 */
	@Override
	public int compareTo(Partida o) {
		 if (puntuacion < o.puntuacion) {
             return 1;
         }
         if (puntuacion > o.puntuacion) {
             return -1;
         }
         return 0;
	}

	
	
	

}
