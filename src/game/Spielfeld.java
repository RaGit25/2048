package game;

class Spielfeld {
	int breite;
	Block[][] feld; // Matrix mit allen Bloecken

	int punkte;
	// int hoechstesFeld;
	int zuege;
	Boolean veraendert;

	Spielfeld(int g) {
		feld = new Block[g][g];

		breite = g;

		for (int i = 0; i < breite; i++) {
			for (int j = 0; j < breite; j++) {

				feld[i][j] = new Block(0, i, j); // Jedes Feld wird erstellt und hat den Wert 0
				// -> diese sollten logischerweise nicht sichtbar sein

			}
			punkte = 0;
			zuege = 0;
			veraendert = false;
			// setAllFalse();
		}

	}

	public int getAnzahl() {
		int a = 0;
		for (int i = 0; i < breite; i++) {
			for (int j = 0; j < breite; j++) {
				if (feld[i][j].getWert() > 0) {
					a++;
				}
			}
		}
		return a;
	}

	public void setAllFalse() {
		for (int i = 0; i < breite; i++) {
			for (int j = 0; j < breite; j++) {
				feld[i][j].setVerschoben(false);
			}
		}

	}

	public Boolean existiertFeld(int zeile, int spalte) { // �berpr�ft ob ein Block existiert
		try {
			return (feld[zeile][spalte] != null) ? true : false;
		} catch (ArrayIndexOutOfBoundsException e) { // wenn der Block au�erhalb des Feldesliegt -> existiert nicht
			return false;
		}
	}

	public Boolean gameOver() {
		int zahl = 0;
		if (getAnzahl() == (breite * breite)) { // Pr�ft ob noch freie Felder da sind
			// Einmal f�r jeden Block ausprobieren
			for (int i = 0; i < breite; i++) {
				for (int j = 0; j < breite; j++) {
					// Vier Richtungen zum Schieben werden getestet
					if (existiertFeld(i + 1, j)) {
						if (feld[i][j].getWert() == feld[i + 1][j].getWert()) {
							return false;
						}
					}

					if (existiertFeld(i - 1, j)) {
						if (feld[i][j].getWert() == feld[i - 1][j].getWert()) {
							return false;
						}
					}

					if (existiertFeld(i, j + 1)) {
						if (feld[i][j].getWert() == feld[i][j + 1].getWert()) {
							return false;
						}
					}

					if (existiertFeld(i, j - 1)) {
						if (feld[i][j].getWert() == feld[i][j - 1].getWert()) {
							return true;
						}
					}
					zahl++;
					if (zahl == getAnzahl()) {
						return true;
					}
				}
			}
		}
		return false; // bricht Methode ab

	}

	public void welcheRichtung(String Richtung) {
		veraendert = false;
		setAllFalse();
		zuege++; // addiert einen neuen Zug

		String r = Richtung;

		switch (r) {
		// Man geht unterschidlich durchs Feld, beginnend mit der Seite in die geschoben
		// wird
		case "oben": // oben links beginnend

			for (int i = 0; i < breite; i++) {
				for (int j = 0; j < breite; j++) {
					verschieben(i, j, i - 1, j);
				}
			}

			break;

		case "unten": // unten rechts beginnend

			for (int i = (breite - 1); i >= 0; i--) {
				for (int j = (breite - 1); j >= 0; j--) {
					verschieben(i, j, i + 1, j);
				}
			}

			break;

		case "links": // gleich wie oben

			for (int i = 0; i < breite; i++) {
				for (int j = 0; j < breite; j++) {
					verschieben(i, j, i, j - 1);
				}
			}

			break;

		case "rechts": // gleich wie unten

			for (int i = (breite - 1); i >= 0; i--) {
				for (int j = (breite - 1); j >= 0; j--) {
					verschieben(i, j, i, j + 1);
				}
			}
			break;
		}
		if (veraendert) {
			blockErstellen();
		} else {
			zuege--;
		}
	}

	public void verschieben(int zeile, int spalte, int z, int s) {
		// praktische Verbesserung: die Werte f�r z(eile) und s(palte), f�r das
		// hinzuverschiebende Feld werden �bergeben

		if (existiertFeld(z, s) && feld[zeile][spalte].getWert() != 0) { 	// Schaut, ob es das Feld daneben und selbst gibt
																		
			if (feld[zeile][spalte].getWert() == feld[z][s].getWert() 		// Wenn: Feld identisch und
					&& !feld[zeile - 1][spalte].getVerschoben()) { 			// 		noch nicht verschoben

				feld[z][s].setWert(feld[zeile][spalte].getWert() * 2); 		// Neues Feld mit doppeltem Wert
				feld[z][s].setVerschoben(true); 							// Feld als verschoben gekennzeichnet

				feld[zeile][spalte].setWert(0); 							// Altes Feld wird null gesetzt

				punkte += (feld[zeile][spalte].getWert() * 2); 				// Punktesystem

				veraendert = true;
			} else if (feld[z][s].getWert() == 0) { 						// Wenn Feld daneben null ist

				feld[z][s].setWert(feld[zeile][spalte].getWert()); 			// dort hinschieben
				feld[zeile][spalte].setWert(0); 							// und altes Feld nullsetzen

				int ze = z + (z - zeile); // Neue Zeile plus/minus 1
				int sp = s + (s - spalte); // Neue Spalte plus/minus 1

				veraendert = true;				 // eigentlich unn�tig, da Feld jetzt leer

				verschieben(z, s, ze, sp); //  Verschieben neuen Feldes unn�tig, weil richtige Richtung durch Feld

			}
		}

	}

	public void blockErstellen() {

		int zeile = (int) (Math.random() * breite);
		int spalte = (int) (Math.random() * breite);

		double zufallszahl = Math.random(); // bestimmt, wie hoch die Wahrscheinlichkeit ist, ob der Block den Wert 2
											// oder 4 hat.

		if (feld[zeile][spalte].getWert() == 0) {

			if (zufallszahl < 0.6) {

				feld[zeile][spalte] = new Block(2, zeile, spalte);
			} else {

				feld[zeile][spalte] = new Block(4, zeile, spalte);
			}
		} else {
			if (getAnzahl() < (breite * breite)) {
				blockErstellen();
			}
		}
		// ausdrucken();
	}

	public void ausdrucken() {
		for (int k = 0; k < 5; k++) {
			System.out.println();
		}
		System.out.println("-----------------------");
		for (int i = 0; i < breite; i++) {
			for (int j = 0; j < breite; j++) {
				if (feld[i][j].getWert() == 0)
					System.out.print("| |");
				else
					System.out.print("|" + feld[i][j].getWert() + "|");
			}
			System.out.println();
		}
		System.out.println("-----------------------");
	}
}