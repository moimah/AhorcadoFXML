package other_classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ListaPartidas {
	
	
public static void guardarPartidas(ArrayList<Partida> partidas) {		
		
		
		File file = new File("listadoPartidas.dat");
		
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			
			if(file.exists()) {
				
				FileOutputStream fout = new FileOutputStream(file);
				ObjectOutputStream ous = new ObjectOutputStream(fout);
				ous.writeObject(partidas);
				ous.close();				

			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
			
			
	}

@SuppressWarnings({ "unchecked", "rawtypes" })
public static ArrayList leerPartidas() {
	
	ArrayList<Partida> listadoLectura = new ArrayList<Partida>();
	
	File file = new File("listadoPartidas.dat");
	
	try {
					
		if(file.exists()) {
			
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fin);
			listadoLectura =  (ArrayList<Partida>) ois.readObject();
			ois.close();
		}
		
		
	} catch (Exception e) {
		// TODO: handle exception
	}
		
	return listadoLectura;
	

}



	
}
