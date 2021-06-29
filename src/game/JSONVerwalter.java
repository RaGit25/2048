package game;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONVerwalter {
	
	public static void speichern(Account a) {			// Erstellt .json -> save
		//System.out.println(a.s.getPunkte());
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String name = a.getName();
			mapper.writeValue(new File("./"+name+".json"), a);
		} catch (IOException e) {
			System.out.println("Fehler beim Speichern");
			e.printStackTrace();
		} 

		
	}

	public static Account laden(String name) {			// Liest .json ein in java ->load
		Account obj = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			obj = mapper.readValue(new File("./"+name+".json"), Account.class);
		} catch (IOException e) {
			System.out.println("Fehler beim Laden");
			e.printStackTrace();
		} 
		// System.out.println("breite ausgeben " + obj.s.breite);
		return obj;
	}
}