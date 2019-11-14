package other_classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ListaPalabras {
	
	
public static void guardarPalabra(ArrayList<Palabra> palabras) {		
		
		
		File file = new File("listadoPalabras.dat");
		
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			
			if(file.exists()) {
				
				FileOutputStream fout = new FileOutputStream(file);
				ObjectOutputStream ous = new ObjectOutputStream(fout);
				ous.writeObject(palabras);
				ous.close();				

			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
			
			
	}

@SuppressWarnings({ "unchecked", "rawtypes" })
public static ArrayList leerPalabras() {
	
	ArrayList<Palabra> listadoPalabras = new ArrayList<Palabra>();
	
	File file = new File("listadoPalabras.dat");
	
	try {
					
		if(file.exists()) {
			
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fin);
			listadoPalabras =  (ArrayList<Palabra>) ois.readObject();
			ois.close();
		}
		
		
	} catch (Exception e) {
		// TODO: handle exception
	}
		
	return listadoPalabras;
	

}



	
}
