package game;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Account {
	@JsonProperty("spielfeld")
	public Spielfeld s;
	@JsonProperty("stats")
	public Stats st;
	@JsonProperty("klon")
	public Spielfeld klon; // Zum Zurücknehmen des letzten Zuges

	public Account(int a) {
		this.s = new Spielfeld(4); // <- Groesse des Spielfeldes
		this.st = new Stats(s);
		this.klon = new Spielfeld(s);
		;
		System.out.println("Acc wird jetzt schon erstellt!");
	}
	
	public Account() {}

	public void klonen() {
		this.klon = new Spielfeld(s); // Aktuelles Spielfeld wird gespeichert
		main();
	}

	public void zuruecknehmen() {
			this.st.Statszuruecknehmen(klon);	//Statszuruecksetzen
			this.s = new Spielfeld(klon);	//Neusetzen des Spielfeldes
			this.st.updateSpielfeld(s);		//Neusetzen der Referenz
	}
	
	public static void main() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File("./test.json"), new Account());	//Erstellt .json -> save
			
			Account obj = mapper.readValue(new File("./test.json"), Account.class);		//Liest .json ein in java	->load
			System.out.println("RAFAEL " + obj.s.breite);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}