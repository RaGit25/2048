package game;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
	@JsonProperty("Name")
	public String name;
	@JsonProperty("Spielfeld")
	public Spielfeld s;
	@JsonProperty("Statistiken")
	public Stats st;
	@JsonProperty("Klon")
	public Spielfeld klon; // Zum Zuruecknehmen des letzten Zuges

	public Account(String n, int groesse) { // Erstellen des Accounts
		this.name = n;
		this.s = new Spielfeld(groesse); // <- Groesse des Spielfeldes
		this.st = new Stats(s);
		this.klon = new Spielfeld(s);
	}

	public Account(Account alt) { // Copy-Constructor
		this.name = alt.name;
		this.s = new Spielfeld(alt.s);
		this.st = new Stats(alt.st);
		this.klon = new Spielfeld(alt.klon);
		st.updateSpielfeld(s);
	}

	public Account() {
	}

	public String getName() {
		return this.name;
	}

	public void klonen() {
		this.klon = new Spielfeld(s); // Aktuelles Spielfeld wird gespeichert
	}

	public void zuruecknehmen() {
		this.st.Statszuruecknehmen(klon); // Statszuruecksetzen
		this.s = new Spielfeld(klon); // Neusetzen des Spielfeldes
		this.st.updateSpielfeld(s); // Neusetzen der Referenz
	}

}