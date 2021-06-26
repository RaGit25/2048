package game;

public class Account {
	Spielfeld s;
	Stats st;
	Spielfeld klon;		//Zum Zurücknehmen des letzten Zuges
	
	public Account(int a) {
		this.s = new Spielfeld(a);
		this.st = new Stats(s);
		this.klon = new Spielfeld(5);
		klon.blockErstellen();
		System.out.println("Acc wird jetzt schon erstellt!");
	}
	
	public void klonen() {
		this.klon = new Spielfeld(s);	//Aktuelles Spielfeld wird gespeichert
	}
	
	public void zuruecknehmen() {
		this.s = new Spielfeld(klon);
	}
}
