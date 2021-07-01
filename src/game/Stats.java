package game;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Stats implements Serializable {

	private static final long serialVersionUID = -2406862490360728879L;
	public int punkteGesamt = 0; // anzahl aller Punkte, die ein Spieler gesammelt hat
	public  int runden = 1;		//Man startet in Runde eins
	public int gewonnen = 0; // gewonnene Runden
	public Boolean gewonneneRunde = false; 	//Dieses Feld hat schon gewonnen
	public double winLoseRatio = 0;
	public int hoechstesFeldInsgesamt = 0;
	public int hoechstesAlt = 0;		//Speichert das hoechste Feld des Klons
	public int rekord = 0;
	public int rekordAlt = 0;			//Speichert den Rekord des Klons
	public int zuegeGesamt = -1;
	
	@JsonIgnore
	public Spielfeld s;	//Referenzattribut setzen
	
	
	public Stats() {
		
	}
	
	public Stats(Spielfeld s) {
		this.s = s;	//Setzen einer Objektreferenz
	}
	
	void Statszuruecknehmen(Spielfeld k) {
		this.punkteGesamt -= s.getPunkteDifferenz();
		this.hoechstesFeldInsgesamt =  this.hoechstesAlt;
		this.rekord = this.rekordAlt;
		this.zuegeGesamt -= 1; 
			
		
	} 
	
	void updateSpielfeld(Spielfeld s) {
		this.s = s;
	}
	
	
	void punkteGesamt() { // PRO RUNDE AUFRUFEN

		punkteGesamt += s.getPunkteDifferenz();
	}

	int getRekord() {

		if (rekord < s.getPunkte()) {	//Wenn Punkte groesser als Rekord

			rekord = s.getPunkte();

		}

		return rekord;

	}
	
	int getDurchschnittsPunkte() {
		return  (int)(punkteGesamt/(runden));
	}
	
	
	void saveAlt() { // Vor Update der insgesamt Hoechsten
		
		hoechstesAlt = hoechstesFeldInsgesamt;
		rekordAlt = rekord;

	}
	
	
	void feldHoch() {

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
		
		return s.getZuege();
		
	}
	
	void zuegeGesamt(){
		
		zuegeGesamt += 1;
		
	}
	
	int getZuegeGesamt() {
		
		return zuegeGesamt;
		
	}

	
	void gewonneneRunden() { 

		gewonnen += (s.gewonnen()) ? 1	: 0;	
		
	}

	int getGewonnen() {

		return gewonnen;

	}


	void winLoseRatio() {

		winLoseRatio = (double) (getGewonnen()) / (double) (runden);

	}

	double getwinLoseRatio() {

		return winLoseRatio;

	}

	void update() { // Update pro Zug
		saveAlt();
		
		zuegeGesamt();
		punkteGesamt();
		feldHoch();
		
		if(s.gewonnen() && !gewonneneRunde) {
			gewonneneRunden();
			winLoseRatio();
			gewonneneRunde = true;
		}
		

	}

	void updateEnde() { // Update am Ende der Runde
		runden();
		winLoseRatio();
		gewonneneRunde = false;	//neue Runde
		
	}

}