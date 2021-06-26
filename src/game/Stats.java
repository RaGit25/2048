package game;

public class Stats extends Game {

	int punkteGesamt = 0; // anzahl aller Punkte, die ein Spieler gesammelt hat
	double runden = 0;
	int rundenAlt = 0; // alle runden
	double gewonnen = 0; // gewonnene Runden
	int gewonnenAlt = 0;
	double winLoseRatio = 0;
	int zuege = 0;
	int hoechstesFeldInsgesamt = 0;
	int hoechstesAlt = 0;
	int rekord = 0;

	void punkte() { // PRO RUNDE AUFRUFEN

		punkteGesamt += s.punkteDifferenz;
	}

	int getRekord() {

		if (rekord < s.punkte) {

			rekord = s.punkte;

		}

		return rekord;

	}

	void feldHochAlt() { // NUR AM ENDE AUFRUFEN

		hoechstesAlt = s.hoechstesFeld;

	}

	void feldHoch() { // PRO RUNDE AUFRUFEN

		if (hoechstesAlt > s.hoechstesFeld) {

			hoechstesFeldInsgesamt = hoechstesAlt;

		} else {

			hoechstesFeldInsgesamt = s.hoechstesFeld;

		}

	}

	int getFeldHoch() {

		return hoechstesFeldInsgesamt;
	}

	int getPunkteGesamt() {

		return punkteGesamt;

	}

	void runden() { // NUR AM ENDE DES SPIELS AUFRUFEN

		runden += 1.0;
		rundenAlt += 1;

	}

	double getRunden() {

		return runden;

	}

	int getRundenAlt() {

		return rundenAlt;

	}

	void gewonnen() { // NUR AM ENDE DES SPIELS AUFRUFEN

		for (int i = 0; i < s.breite; i++) {
			for (int j = 0; j < s.breite; j++) {

				if (s.feld[i][j].getWert() >= 2048) {

					gewonnen += 1.0;
					gewonnenAlt += 1;

				}
			}
		}
	}

	double getGewonnen() {

		return gewonnen;

	}

	int getGewonnenAlt() {

		return gewonnenAlt;

	}

	void winLoseRatio() { // NUR AM ENDE DES SPIELS AUFRUFEN

		winLoseRatio = getGewonnen() / getRunden();

	}

	double getwinLose() {

		return winLoseRatio;

	}

	void update() { // Update pro Zug

		punkte();
		feldHoch();

	}

	void updateEnde() { // Update am Ende der Runde

		runden();
		gewonnen();
		winLoseRatio();
		feldHochAlt();

	}

}
