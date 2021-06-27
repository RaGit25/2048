package game;

public class Stats {

	int punkteGesamt = 0; // anzahl aller Punkte, die ein Spieler gesammelt hat
	int runden = 1;		//Man startet in Runde eins
	int gewonnen = 0; // gewonnene Runden
	Boolean gewonneRunde = false; 	//Dieses Feld hat schon gewonnen
	double winLoseRatio = 0;
	int hoechstesFeldInsgesamt = 0;
	int hoechstesAlt = 0;		//Speichert das hoechste Feld des Klons
	int rekord = 0;
	int rekordAlt = 0;			//Speichert den Rekord des Klons
	int zuegeGesamt = -1;
	
	Spielfeld s;	//Referenzattribut setzen
	
	public Stats(Spielfeld s) {
		this.s = s;
	}
	
	void Statszuruecknehmen(Spielfeld k) {
		this.punkteGesamt -= s.punkteDifferenz;
		this.hoechstesFeldInsgesamt =  this.hoechstesAlt;
		this.rekord = this.rekordAlt;
		this.zuegeGesamt -= 1; 
			
		
	} 
	
	void updateSpielfeld(Spielfeld s) {
		this.s = s;
	}
	
	void punkteGesamt() { // PRO RUNDE AUFRUFEN

		punkteGesamt += s.punkteDifferenz;
	}

	int getRekord() {

		if (rekord < s.punkte) {	//Wenn Punkte groesser als Rekord

			rekord = s.punkte;

		}

		return rekord;

	}
	
	int getDurchschnittsPunkte() {
		return  (int)(punkteGesamt/(runden));
	}
	
	
	void saveAlt() { // Vor Update der insgesamt hoechsten
		
		hoechstesAlt = hoechstesFeldInsgesamt;
		rekordAlt = rekord;

	}
	
	
	void feldHoch() { // PRO RUNDE AUFRUFEN

		if (hoechstesFeldInsgesamt < s.getHoechstesFeld()) {	//Wenn groesseres Feld existiert

			hoechstesFeldInsgesamt = s.getHoechstesFeld();

		}

	}

	int getFeldHoch() {

		return hoechstesFeldInsgesamt;
	}

	int getPunkteGesamt() {

		return punkteGesamt;

	}

	void runden() { // NUR AM ENDE DES SPIELS AUFRUFEN

		runden += 1;

	}

	int getRunden() {

		return runden;

	}
	
	int getZuegeMomentan() {
		
		return s.zuege;
		
	}
	
	void zuegeGesamt(){
		
		zuegeGesamt += 1;
		
	}
	
	int getZuegeGesamt() {
		
		return zuegeGesamt;
		
	}

	
	void gewonnen() { 

		if (s.gewonnen()) {

			gewonnen += 1;
			
		}
		
	}

	int getGewonnen() {

		return gewonnen;

	}


	void winLoseRatio() {

		winLoseRatio = (double) (getGewonnen()) / (double) (runden);

	}

	double getwinLose() {

		return winLoseRatio;

	}

	void update() { // Update pro Zug
		saveAlt();
		zuegeGesamt();
		punkteGesamt();
		feldHoch();
		
		if(s.gewonnen() && !gewonneRunde) {
			gewonnen();
			winLoseRatio();
			gewonneRunde = true;
		}
		

	}

	void updateEnde() { // Update am Ende der Runde

		runden();
		winLoseRatio();
		gewonneRunde = false;	//neue Runde
		
	}

}
