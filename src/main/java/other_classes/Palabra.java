package other_classes;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Palabra implements Serializable {
	
	private String palabra;
	private String incognita = "";
	private char[] charsPalabra;
	private char[] charsIncognita;
	private int contador = 0; 
	

	public String getPalabra() {
		return palabra;
		
		
	}
	
	//Setea la palrabra y carga la incognita 	
	public void setPalabra(String palabra) {
		this.palabra = palabra;
		cargarIncognita();
	}
	
	
	
	public String getIncognita() {
		return incognita;
	}

	public void setIncognita(String incognita) {
		this.incognita = incognita;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
	
	

	public char[] getCharsPalabra() {
		return charsPalabra;
	}

	public char[] getCharsIncognita() {
		return charsIncognita;
	}

	private void cargarIncognita() {
	
		//Rellenar el array charsPalabra
		 charsPalabra = new char[palabra.length()];
		for(int i=0; i<palabra.length(); i++) {
			charsPalabra[i] = palabra.charAt(i);
		}
		
		
		//Transformar en guiones bajos
		 charsIncognita = new char[palabra.length()];		
		for(int i=0; i<palabra.length();i++) {
		charsIncognita[i] = '_';	
		incognita = incognita+ charsIncognita[i]+" ";
		
		}
		
		
	}
	
	/*Busca si existe un caracter en la palabra
	 * lo cambia si se ha encontrado en la incognita
	 * y suma los intentos
	 */
	
	public boolean encontrarCaracter(char c) {
		boolean resuelto = false;
		
		//Buscar el caracter a través de bucle
		boolean encontrado = false;
		for(int i=0; i<charsPalabra.length;i++) {
			if(c== charsPalabra[i]) { //Si el caracter coincide cambiar guion por caracter
				charsIncognita[i] = c;
				encontrado = true; 
			}
		}
		
		//Transformar la incognita 
		
		if(encontrado) {
			incognita = "";
			for(int i=0; i<charsIncognita.length;i++) {
				incognita = incognita + charsIncognita[i]+" ";				
				}
		}
		
		resuelto = completado();
		
		contador ++;	
						
					
		return resuelto;
		
	}
	
	/*Confirma que el String introducido sea
	 * la palabra almacenada y cuenta el numero de intentos
	 */	
	public boolean resolver(String st) {
		
		if(st.equals(palabra)) {
			contador++;
			return true;
		}else {
			contador++;
			return false;
		}
			
	}
	
	/*Determina si se ha resuelto la incognita
	 * comprobando que no contiene "_"
	 */	
	public boolean completado() {
		
		if(!incognita.contains("_")) {
			return true;
		}else {
			return false;
		}
	}
	
	

}
