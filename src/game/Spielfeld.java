package game;

import com.fasterxml.jackson.annotation.JsonIgnore;

class Spielfeld {
	public int breite;
	public Block[][] feld; // Matrix mit allen Bloecken

	public int punkte;
	public int hoechstesFeld;
	public int zuege;

	public int punkteDifferenz;
	public int zusammenschuebe;
	public Boolean veraendert;
	public Boolean hinzufuegen;

	Spielfeld() {
	}

	Spielfeld(int g) {
		breite = g;
		feld = new Block[breite][breite]; // Initialisierung des Feldes
		for (int i = 0; i < breite; i++) {
			for (int j = 0; j < breite; j++) {

				feld[i][j] = new Block(false); // Jedes Feld wird erstellt und hat den Wert 0
				// -> diese sollten logischerweise nicht sichtbar sein

			}
		}

		punkte = 0;
		hoechstesFeld = 0;
		zuege = 0;

		punkteDifferenz = 0;
		zusammenschuebe = 0;
		veraendert = false;
		hinzufuegen = true;
		// setAllFalse(); //-> Zum crashen auskommentieren
	}

	Spielfeld(Spielfeld a) { // Copy-Constructor
		this.breite = a.breite;
		this.feld = new Block[breite][breite];
		for (int i = 0; i < breite; i++) {
			for (int j = 0; j < breite; j++) {
				this.feld[i][j] = new Block(a.feld[i][j]); // Anderen Copy-Constructor aufrufen
			}
		}

		this.punkte = a.punkte;
		this.hoechstesFeld = a.hoechstesFeld;
		this.zuege = a.zuege;

		this.punkteDifferenz = a.punkteDifferenz;
		this.zusammenschuebe = a.zusammenschuebe;
		this.veraendert = a.veraendert;
		this.hinzufuegen = a.hinzufuegen;
	}

	@Override
	public boolean equals(Object o) { // eigene Vergleichsmethode
		if (o instanceof Spielfeld) {
			Spielfeld s = (Spielfeld) o;
			boolean bl = true; // Testet ob jedes Feld induviduell gleich sind
			try { // Schützt vor nicht existenten Feldern
				for (int i = 0; i < breite; i++) {
					for (int j = 0; j < breite; j++) {
						if (!this.feld[i][j].equals(s.feld[i][j])) {
							bl = false;
							i = breite; // Bricht for aeussere Schleife ab
							j = breite; // Bricht for innere Schleife ab, (unnötig)
						}

					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}

			return (this.breite == s.breite) && (bl) && (this.punkte == s.punkte)
					&& (this.hoechstesFeld == s.hoechstesFeld);
			// Die anderen Attribute werden beim Vergleich ignoriert
		}
		return false;
	}

	public int getBreite() {
		return this.breite;
	}

	public Block[][] getFeld() {
		return this.feld;
	}

	public int getPunkte() {
		return this.punkte;
	}

	public int getHoechstesFeld() {
		return this.hoechstesFeld;
	}

	public int getZuege() {
		return this.zuege;
	}

	public int getPunkteDifferenz() {
		return this.punkteDifferenz;
	}

	@JsonIgnore
	public int getZusammenschuebe() {
		return this.zusammenschuebe;
	}

	public Boolean getVeraendert() {
		return veraendert;
	}

	public Boolean getHinzufuegen() {
		return hinzufuegen;
	}

	@JsonIgnore
	public int getAnzahl() { // Gibt die Anzahl der vollen Felder
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

	public Boolean gewonnen() {
		return (hoechstesFeld >= 2048) ? true : false;
	}

	public void setHinzufuegen(Boolean b) {
		this.hinzufuegen = b;
	}

	public void setAllFalse() {
		for (int i = 0; i < breite; i++) {
			for (int j = 0; j < breite; j++) {
				feld[i][j].setVerschoben(false);
			}
		}

	}

	public Boolean existiertFeld(int zeile, int spalte) { // Ueberprueft ob ein Block existiert
		try {
			return (feld[zeile][spalte] != null) ? true : false;
		} catch (ArrayIndexOutOfBoundsException e) { // wenn der Block ausserhalb des Feldesliegt -> existiert nicht
			return false;
		}
	}

	public Boolean verschiebbar(String richtung) {
		Spielfeld temp = new Spielfeld(this); // Erstellt temporären Klon
		temp.setHinzufuegen(false); // unterdrueckt neue Bloecke
		temp.welcheRichtung(richtung); // verschiebt diesen
		return (temp.veraendert) && (!this.equals(temp)); // Wenn es veraendert wurde, kann man es verschieben
	}

	public Boolean gameOver() {
		int zahl = 0;
		if (getAnzahl() == (breite * breite)) { // Prueft ob noch freie Felder da sind
			// Einmal fuer jeden Block ausprobieren
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
		zusammenschuebe = 0;
		veraendert = false;
		setAllFalse();
		zuege++; // addiert einen neuen Zug

		punkteDifferenz = 0;
		int punkteAlt = punkte;

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
		if (veraendert && hinzufuegen) { // Wenn das Feld sich veraendert hat und es nicht unterdrueckt wird
			blockErstellen();
		} else {
			zuege--;
		}

		punkteDifferenz = punkte - punkteAlt;

	}

	public void verschieben(int zeile, int spalte, int z, int s) {
		// praktische Verbesserung: die Werte fuer z(eile) und s(palte), fuer das
		// hinzuverschiebende Feld werden Uebergeben

		if (existiertFeld(z, s) && feld[zeile][spalte].getWert() != 0) { // Schaut, ob es das Feld daneben und selbst
																			// gibt

			if (feld[zeile][spalte].getWert() == feld[z][s].getWert() // Wenn: Feld identisch und
					&& !feld[z][s].getVerschoben()) { // noch nicht verschoben

				feld[z][s].setWert(feld[zeile][spalte].getWert() * 2); // Neues Feld mit doppeltem Wert
				feld[z][s].setVerschoben(true); // Feld als verschoben gekennzeichnet

				feld[zeile][spalte].setWert(0); // Altes Feld wird null gesetzt

				punkte += (feld[z][s].getWert()); // Punktesystem

				zusammenschuebe++;
				veraendert = true;

				feld[z][s].setYundX(zeile, spalte); // Speichern des alten Feldes

				if (feld[z][s].getWert() > hoechstesFeld) {
					hoechstesFeld = feld[z][s].getWert();
				}

			} else if (feld[z][s].getWert() == 0) { // Wenn Feld daneben null ist

				feld[z][s].setWert(feld[zeile][spalte].getWert()); // dort hinschieben
				feld[zeile][spalte].setWert(0); // und altes Feld nullsetzen

				int ze = z + (z - zeile); // Neue Zeile plus/minus 1
				int sp = s + (s - spalte); // Neue Spalte plus/minus 1

				veraendert = true; // Es wurde was verschoben

				feld[z][s].setYundX(zeile, spalte); // Speichern des alten Feldes

				verschieben(z, s, ze, sp); // Verschieben neuen Feldes

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

				feld[zeile][spalte].setWert(2);

				if (2 > hoechstesFeld) {
					hoechstesFeld = feld[zeile][spalte].getWert();
				}

				feld[zeile][spalte].setYundX(zeile, spalte); // Speichern des alten Feldes

			} else {

				feld[zeile][spalte].setWert(4);

				if (4 > hoechstesFeld) {
					hoechstesFeld = feld[zeile][spalte].getWert();
				}

				feld[zeile][spalte].setYundX(zeile, spalte); // Speichern des alten Feldes
			}
		} else {
			if (getAnzahl() < (breite * breite)) {
				blockErstellen();
			}
		}
		// ausdrucken();
	}

	// Zur Fehlerbehebung
	/*
	 * public void ausdrucken() { for (int k = 0; k < 5; k++) {
	 * System.out.println(); } System.out.println("-----------------------"); for
	 * (int i = 0; i < breite; i++) { for (int j = 0; j < breite; j++) { if
	 * (feld[i][j].getWert() == 0) System.out.print("| |"); else
	 * System.out.print("|" + feld[i][j].getWert() + "|"); } System.out.println(); }
	 * System.out.println("-----------------------"); System.out.println("Zuege:" +
	 * zuege); }
	 */
}