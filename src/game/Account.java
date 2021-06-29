package game;

public class Account {
	Spielfeld s;
	Stats st;
	Spielfeld klon; // Zum Zurücknehmen des letzten Zuges

	public Account() {
		this.s = new Spielfeld(4); // <- Groesse des Spielfeldes
		this.st = new Stats(s);
		this.klon = new Spielfeld(s);
		;
		System.out.println("Acc wird jetzt schon erstellt!");
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