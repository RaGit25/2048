package game;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONVerwalter {
	static ObjectMapper mapper;

	public static void speichern(Account a) {			// Erstellt .json -> save
		mapper = new ObjectMapper();

		try {
			mapper.writeValue(new File("./test.json"), a);
		} catch (IOException e) {
			System.out.println("Fehler beim speichern");
			e.printStackTrace();
		} 

		
	}

	public Account laden() {			// Liest .json ein in java ->load
		Account obj = null;
		try {
			obj = mapper.readValue(new File("./test.json"), Account.class);
		} catch (IOException e) {
			System.out.println("Fehler beim laden");
			e.printStackTrace();
		} 
		// System.out.println("breite ausgeben " + obj.s.breite);
		return obj;
	}
}