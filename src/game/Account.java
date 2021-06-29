package game;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
	@JsonProperty("Namen")
	public String name;
	@JsonProperty("spielfeld")
	public Spielfeld s;
	@JsonProperty("stats")
	public Stats st;
	@JsonProperty("klon")
	public Spielfeld klon; // Zum Zuruecknehmen des letzten Zuges

	public Account(String n) {
		this.name = n;
		this.s = new Spielfeld(4); // <- Groesse des Spielfeldes
		this.st = new Stats(s);
		this.klon = new Spielfeld(s);
		System.out.println("Acc wird jetzt erstellt!");
	}
	
	public Account() {	}

	public String getName() {
		return this.name;
	}
	
	public void klonen() {
		this.klon = new Spielfeld(s); // Aktuelles Spielfeld wird gespeichert
	}

	public void zuruecknehmen() {
			this.st.Statszuruecknehmen(klon);	//Statszuruecksetzen
			this.s = new Spielfeld(klon);	//Neusetzen des Spielfeldes
			this.st.updateSpielfeld(s);		//Neusetzen der Referenz
	}
	
	
	
	
	
}