package game;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Stats {

	public int punkteGesamt; // anzahl aller Punkte, die ein Spieler gesammelt hat
	public int runden; // Man startet in Runde eins
	public int gewonnen; // gewonnene Runden
	public Boolean gewonneneRunde; // Dieses Feld hat schon gewonnen
	public double winLoseRatio;
	public int hoechstesFeldInsgesamt;
	public int hoechstesAlt; // Speichert das hoechste Feld des Klons
	public int rekord;
	public int rekordAlt; // Speichert den Rekord des Klons
	public int zuegeGesamt;

	@JsonIgnore
	public Spielfeld s; // Referenzattribut setzen

	public Stats(Spielfeld s) {
		this.punkteGesamt = 0;
		this.runden = 1;
		this.gewonnen = 0;
		this.gewonneneRunde = false;
		this.winLoseRatio = 0;
		this.hoechstesFeldInsgesamt = 0;
		this.hoechstesAlt = 0;
		this.rekord = 0;
		this.rekordAlt = 0;
		this.zuegeGesamt = 0;
		this.s = s;

	}

	public Stats(Stats alt) { // Copy-Constructor
		this.punkteGesamt = alt.punkteGesamt;
		this.runden = alt.runden;
		this.gewonnen = alt.gewonnen;
		this.gewonneneRunde = alt.gewonneneRunde;
		this.winLoseRatio = alt.winLoseRatio;
		this.hoechstesFeldInsgesamt = alt.hoechstesFeldInsgesamt;
		this.hoechstesAlt = alt.hoechstesAlt;
		this.rekord = alt.rekord;
		this.rekordAlt = alt.rekordAlt;
		this.zuegeGesamt = alt.rekordAlt;
		this.s = null; // temporär null gestetz

	}

	public Stats() {
	}

	// wenn zurueckgemacht wird

	void Statszuruecknehmen(Spielfeld k) {
		this.punkteGesamt -= s.getPunkteDifferenz();
		this.hoechstesFeldInsgesamt = this.hoechstesAlt;
		this.rekord = this.rekordAlt;
		this.zuegeGesamt -= 1;

	}

	void updateSpielfeld(Spielfeld s) {
		this.s = s;
	}

	int getRekord() {

		return rekord;

	}

	int setRekord() {

		if (s.getPunkte() > rekord) { // Wenn Punkte groesser als Rekord

			rekord = s.getPunkte();

		}
		return rekord;
	}

	@JsonIgnore
	int getDurchschnittsPunkte() {
		return (int) (punkteGesamt / (runden));
	}

	void saveAlt() { // Vor Update der insgesamt Hoechsten

		hoechstesAlt = hoechstesFeldInsgesamt;
		rekordAlt = rekord;

	}

	void feldHoch() {

		if (hoechstesFeldInsgesamt < s.getHoechstesFeld()) { // Wenn groesseres Feld existiert

			hoechstesFeldInsgesamt = s.getHoechstesFeld();

		}

	}

	int getFeldHoch() {

		return hoechstesFeldInsgesamt;
	}

	void setPunkteGesamt() { // PRO RUNDE AUFRUFEN

		punkteGesamt += s.getPunkteDifferenz();
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

	void zuegeGesamt() {

		zuegeGesamt += 1;

	}

	int getZuegeGesamt() {

		return zuegeGesamt;

	}

	void gewonneneRunden() {

		gewonnen += (s.gewonnen()) ? 1 : 0;

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

		setRekord();
		zuegeGesamt();
		setPunkteGesamt();
		feldHoch();

		if (s.gewonnen() && !gewonneneRunde) {
			gewonneneRunden();
			winLoseRatio();
			gewonneneRunde = true;
		}

	}

	void updateEnde() { // Update am Ende der Runde
		runden();
		winLoseRatio();
		gewonneneRunde = false; // neue Runde

	}

}