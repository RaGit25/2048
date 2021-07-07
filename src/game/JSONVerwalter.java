package game;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONVerwalter {

	public static void speichern(Account a) { // Erstellt/Überschreibt .json -> save

		ObjectMapper mapper = new ObjectMapper();

		//Erstellen eines neuen Ordners, wenn nicht bereits bestehend
		new File("./accounts").mkdirs(); 
		String name = a.getName();
		try {
			mapper.writeValue(new File("./accounts/" + name + ".json"), a);
		} catch (JsonGenerationException e) {
			System.out.println("Generierungs-Fehler");
			e.printStackTrace();
		} catch (JsonMappingException e) {
			System.out.println("Mapping-Fehler");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO-Fehler");
			e.printStackTrace();
		}

	}

	public static Account laden(String name) { // Liest .json ein in java -> load
		Account obj = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			obj = mapper.readValue(new File("./accounts/" + name + ".json"), Account.class);
		} catch (JsonParseException e) {
			System.out.println("Parse-Fehler");
			e.printStackTrace();
		} catch (JsonMappingException e) {
			System.out.println("Mapping-Fehler");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO-Fehler");
			e.printStackTrace();
		}

		return new Account(obj);
	}
}