package game;

class Spielfeld {
	int punkte;
	int breite;
	Block[][] feld; // Matrix mit allen Bloecken

	Spielfeld(int g) {
		feld = new Block[g][g];

		breite = g;

		for (int i = 0; i < breite; i++) {
			for (int j = 0; j < breite; j++) {

				feld[i][j] = new Block(0, i, j); // Jedes Feld wird erstellt und hat den Wert 0
				// -> diese sollten logischerweise nicht sichtbar sein

			}
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

	public Boolean existiertFeld(int zeile, int spalte) { // überprüft ob ein Block existiert
		try {
			return (feld[zeile][spalte] != null) ? true : false;
		} catch (ArrayIndexOutOfBoundsException e) { // wenn der Block außerhalb des Feldesliegt -> existiert nicht
			return false;
		}
	}

	public Boolean gameOver() {
		int zahl = 0;
		if (getAnzahl() == (breite * breite)) { // Prüft ob noch freie Felder da sind
			// Einmal für jeden Block ausprobieren
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

		String r = Richtung;

		switch (r) {
		// Man geht unterschidlich durchs Feld, beginnend mit der Seite in die geschoben
		// wird
		case "oben": // oben links beginnend

			for (int i = 0; i < breite; i++) {
				for (int j = 0; j < breite; j++) {
					verschieben(i, j, r);
				}
			}

			break;

		case "unten": // unten rechts beginnend

			for (int i = (breite - 1); i >= 0; i--) {
				for (int j = (breite - 1); j >= 0; j--) {
					verschieben(i, j, r);
				}
			}

			break;

		case "links": // gleich wie oben

			for (int i = 0; i < breite; i++) {
				for (int j = 0; j < breite; j++) {
					verschieben(i, j, r);
				}
			}

			break;

		case "rechts": // gleich wie unten

			for (int i = (breite - 1); i >= 0; i--) {
				for (int j = (breite - 1); j >= 0; j--) {
					verschieben(i, j, r);
				}
			}
			break;
		}
		setAllFalse();
		blockErstellen();
	}

	public void verschieben(int zeile, int spalte, String richtung) {

		String r = richtung;

		switch (r) {

		case "oben":
			if (existiertFeld(zeile - 1, spalte) && feld[zeile][spalte].getWert() != 0) {
				if (feld[zeile][spalte].getWert() == feld[zeile - 1][spalte].getWert() && !feld[zeile - 1][spalte].getVerschoben()) {

					feld[zeile - 1][spalte].setWert(feld[zeile][spalte].getWert() * 2);
					feld[zeile][spalte].setWert(0);
					feld[zeile - 1][spalte].setVerschoben(true);
					punkte += (feld[zeile][spalte].getWert() * 2);

				} else if (feld[zeile - 1][spalte].getWert() == 0) {

					feld[zeile - 1][spalte].setWert(feld[zeile][spalte].getWert());
					feld[zeile][spalte].setWert(0);
					verschieben(zeile - 1, spalte, "oben");

				}
			}

			break;

		case "unten":
			if (existiertFeld(zeile + 1, spalte) && feld[zeile][spalte].getWert() != 0) {
				if (feld[zeile][spalte].getWert() == feld[zeile + 1][spalte].getWert() && !feld[zeile + 1][spalte].getVerschoben()) {

					feld[zeile + 1][spalte].setWert(feld[zeile][spalte].getWert() * 2);
					feld[zeile][spalte].setWert(0);
					feld[zeile + 1][spalte].setVerschoben(true);
					punkte += (feld[zeile][spalte].getWert() * 2);

				} else if (feld[zeile + 1][spalte].getWert() == 0) {

					feld[zeile + 1][spalte].setWert(feld[zeile][spalte].getWert());
					feld[zeile][spalte].setWert(0);
					verschieben(zeile + 1, spalte, "unten");

				}
			}

			break;

		case "links":
			if (existiertFeld(zeile, spalte - 1) && feld[zeile][spalte].getWert() != 0) {
				if (feld[zeile][spalte].getWert() == feld[zeile][spalte - 1].getWert() && !feld[zeile][spalte -1].getVerschoben()) {

					feld[zeile][spalte - 1].setWert(feld[zeile][spalte].getWert() * 2);
					feld[zeile][spalte].setWert(0);
					feld[zeile][spalte - 1].setVerschoben(true);
					punkte += (feld[zeile][spalte].getWert() * 2);

				} else if (feld[zeile][spalte - 1].getWert() == 0) {

					feld[zeile][spalte - 1].setWert(feld[zeile][spalte].getWert());
					feld[zeile][spalte].setWert(0);
					verschieben(zeile, spalte - 1, "links");

				}
			}
			break;

		case "rechts":
			if (existiertFeld(zeile, spalte + 1) && feld[zeile][spalte].getWert() != 0) {
				if (feld[zeile][spalte].getWert() == feld[zeile][spalte + 1].getWert() && !feld[zeile][spalte + 1].getVerschoben()) {

					feld[zeile][spalte + 1].setWert(feld[zeile][spalte].getWert() * 2);
					feld[zeile][spalte].setWert(0);
					feld[zeile][spalte + 1].setVerschoben(true);
					punkte += (feld[zeile][spalte].getWert() * 2);

				} else if (feld[zeile][spalte + 1].getWert() == 0) {

					feld[zeile][spalte + 1].setWert(feld[zeile][spalte].getWert());
					feld[zeile][spalte].setWert(0);
					verschieben(zeile, spalte + 1, "rechts");

				}
			}

			break;

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