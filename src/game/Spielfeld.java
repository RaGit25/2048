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

				feld[i][j] = new Block(0, i, j); // Jedes Feld wird erstellt und hat den Wert 0 (diese sollten
													// logischerweise nicht sichtbar sein)

			}
		}
	}

	public int getGroesse() {
		return getBreite()*getBreite();
	}

	public int getBreite() {
		return feld.length;
	}

	public int getAnzahl() {
		int a = 0;
		for (int i = 0; i < getBreite(); i++) {
			for (int j = 0; j < getBreite(); j++) {
				if (feld[i][j].getWert() > 0) {
					a++;
				}
			}
		}
		return a;
	}

	public Boolean existiertFeld(int x, int y) { // �berpr�ft ob ein Block existiert
		try {
			return (feld[x][y] != null) ? true : false;
		} catch (ArrayIndexOutOfBoundsException e) { // wenn der Block au�erhalb des Feldes liegt -> existiert nicht
			return false;
		}
	}

	public Boolean gameOver() {
		int zahl = 0;
		if (getAnzahl() == getGroesse()) { // Pr�ft ob noch freie Felder da sind
			// Einmal f�r jeden Block ausprobieren
			for (int i = 0; i < getBreite(); i++) {
				for (int j = 0; j < getBreite(); j++) {
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
						if (feld[i][j].getWert() != feld[i][j - 1].getWert()) {
							zahl++;
							if (zahl == getAnzahl()) {
								return true;
							}
						}
					}
				}
			}
		}
		return false; // bricht Methode ab

	}

	public void welcheRichtung(String Richtung) {

		String r = Richtung;

		switch (r) {

		case "oben":

			for (int i = 0; i < getBreite(); i++) {
				for (int j = 0; i < getBreite(); j++) {
					if (existiertFeld(i + 1, j)) {
						verschieben(i, j, "oben");
					}
				}
			}

			break;

		case "unten":

			for (int i = 0; i < getBreite(); i++) {
				for (int j = 0; i < getBreite(); j++) {
					if (existiertFeld(i - 1, j)) {
						verschieben(i, j, "unten");
					}
				}
			}

			break;

		case "links":

			for (int i = 0; i < getBreite(); i++) {
				for (int j = 0; i < getBreite(); j++) {
					if (existiertFeld(i, j - 1)) {
						verschieben(i, j, "links");
					}
				}
			}

			break;

		case "rechts":

			for (int i = 0; i < getBreite(); i++) {
				for (int j = 0; i < getBreite(); j++) {
					if (existiertFeld(i, j + 1)) {
						verschieben(i, j, "rechts");

					}
				}
			}
			break;
		}
		blockErstellen();

	}

	public void verschieben(int zeile, int spalte, String richtung) {

		String r = richtung;

		switch (r) {

		case "oben":

			if (feld[zeile][spalte].getWert() == feld[zeile + 1][spalte].getWert()) {

				feld[zeile + 1][spalte].setWert(feld[zeile][spalte].getWert() * 2);
				feld[zeile][spalte].setWert(0);

				punkte += (feld[zeile][spalte].getWert() * 2);

			} else if (feld[zeile + 1][spalte].getWert() == 0) {

				feld[zeile + 1][spalte].setWert(feld[zeile][spalte].getWert());
				feld[zeile][spalte].setWert(0);
				verschieben(zeile + 1, spalte, "oben");

			}

			break;

		case "unten":

			if (feld[zeile][spalte].getWert() == feld[zeile - 1][spalte].getWert()) {

				feld[zeile - 1][spalte].setWert(feld[zeile][spalte].getWert() * 2);
				feld[zeile][spalte].setWert(0);

				punkte += (feld[zeile][spalte].getWert() * 2);

			} else if (feld[zeile - 1][spalte].getWert() == 0) {

				feld[zeile - 1][spalte].setWert(feld[zeile][spalte].getWert());
				feld[zeile][spalte].setWert(0);
				verschieben(zeile - 1, spalte, "unten");

			}

			break;

		case "links":

			if (feld[zeile][spalte].getWert() == feld[zeile][spalte - 1].getWert()) {

				feld[zeile][spalte - 1].setWert(feld[zeile][spalte].getWert() * 2);
				feld[zeile][spalte].setWert(0);

				punkte += (feld[zeile][spalte].getWert() * 2);

			} else if (feld[zeile][spalte - 1].getWert() == 0) {

				feld[zeile][spalte - 1].setWert(feld[zeile][spalte].getWert());
				feld[zeile][spalte].setWert(0);
				verschieben(zeile, spalte - 1, "links");

			}

			break;

		case "rechts":

			if (feld[zeile][spalte].getWert() == feld[zeile][spalte + 1].getWert()) {

				feld[zeile][spalte + 1].setWert(feld[zeile][spalte].getWert() * 2);
				feld[zeile][spalte].setWert(0);

				punkte += (feld[zeile][spalte].getWert() * 2);

			} else if (feld[zeile][spalte + 1].getWert() == 0) {

				feld[zeile][spalte + 1].setWert(feld[zeile][spalte].getWert());
				feld[zeile][spalte].setWert(0);
				verschieben(zeile, spalte + 1, "rechts");

			}

			break;

		}
	}

	public void blockErstellen() {

		int zeile = (int) (Math.random() * getBreite());
		int spalte = (int) (Math.random() * getBreite());

		double zufallszahl = Math.random(); // bestimmt, wie hoch die Wahrscheinlichkeit ist, ob der Block den Wert 2
											// oder 4 hat.

		if (feld[zeile][spalte].getWert() == 0) {

			if (zufallszahl < 0.6) {

				feld[zeile][spalte] = new Block(2, zeile, spalte);
			} else {

				feld[zeile][spalte] = new Block(4, zeile, spalte);
			}
		}
		ausdrucken();
	}

	public void ausdrucken() {
		for (int k = 0; k < 5; k++) {
			System.out.println();			
		}
		System.out.println("-----------------------");	
		for (int i = 0; i < getBreite(); i++) {
			for (int j = 0; j < getBreite(); j++) {
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