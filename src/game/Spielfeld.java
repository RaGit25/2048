package game;

class Spielfeld {
	int breite;
	Block[][] feld; // Matrix mit allen Bloecken

	int punkte;
	int hoechstesFeld;
	int zuege;
	int punkteDifferenz;
	String reverseRichtung; // fuer reverseVerschieben()
	Boolean zurueckGemacht; // fuer reverseVerschieben()
	int zusammenschuebe;
	Boolean veraendert;

	Spielfeld(int g) {
		feld = new Block[g][g];
		breite = g;

		for (int i = 0; i < breite; i++) {
			for (int j = 0; j < breite; j++) {

				feld[i][j] = new Block(); // Jedes Feld wird erstellt und hat den Wert 0
				// -> diese sollten logischerweise nicht sichtbar sein

			}
		}
		punkte = 0;
		hoechstesFeld = 0;
		zuege = 0;

		zusammenschuebe = 0;
		veraendert = false;
		// setAllFalse(); -> Zum crashen auskommentieren
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
		this.zusammenschuebe = a.zusammenschuebe;
		this.veraendert = a.veraendert;
	}

	public int getPunkte() {

		return punkte;
	}

	public int getHoechstesFeld() {

		return hoechstesFeld;
	}

	public Block[][] getFeld() {

		return feld;
	}

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

	public int getZusammenschuebe() {
		return zusammenschuebe;
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

	public Boolean kannVerschobenWerden(String richtung) {

		int count = 0;

		switch (richtung) {

		case "oben":
			for (int i = 0; i < breite; i++) {
				for (int j = 0; j < breite; j++) {

					if (existiertFeld(i - 1, j) && feld[i][j].getWert() != 0
							&& feld[i][j].getWert() == feld[i - 1][j].getWert()
							|| existiertFeld(i - 1, j) && feld[i - 1][j].getWert() == 0) {

						count++;

					}

				}
			}
			break;

		case "unten":
			for (int i = (breite - 1); i >= 0; i--) {
				for (int j = (breite - 1); j >= 0; j--) {

					if (existiertFeld(i + 1, j) && feld[i][j].getWert() != 0
							&& feld[i][j].getWert() == feld[i + 1][j].getWert()
							|| existiertFeld(i + 1, j) && feld[i + 1][j].getWert() == 0) {

						count++;

					}

				}
			}
			break;

		case "links":
			for (int i = 0; i < breite; i++) {
				for (int j = 0; j < breite; j++) {

					if (existiertFeld(i, j - 1) && feld[i][j].getWert() != 0
							&& feld[i][j].getWert() == feld[i][j - 1].getWert()
							|| existiertFeld(i, j - 1) && feld[i][j - 1].getWert() == 0) {

						count++;

					}

				}
			}
			break;

		case "rechts":
			for (int i = (breite - 1); i >= 0; i--) {
				for (int j = (breite - 1); j >= 0; j--) {

					if (existiertFeld(i, j + 1) && feld[i][j].getWert() != 0
							&& feld[i][j].getWert() == feld[i][j + 1].getWert()
							|| existiertFeld(i, j + 1) && feld[i][j + 1].getWert() == 0) {

						count++;

					}

				}
			}
			break;

		}

		if (count > 0) {

			return true;

		} else {

			return false;

		}

	}

	public void welcheRichtung(String Richtung) {
		zusammenschuebe = 0;
		veraendert = false;
		
		int punkteAlt = punkte;

		if (kannVerschobenWerden(Richtung)) {

			setAllFalse();
			punkteDifferenz = 0;
			

		}

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

			if (veraendert) {

				reverseRichtung = "unten";

			}

			break;

		case "unten": // unten rechts beginnend

			for (int i = (breite - 1); i >= 0; i--) {
				for (int j = (breite - 1); j >= 0; j--) {
					verschieben(i, j, i + 1, j);
				}
			}

			if (veraendert) {

				reverseRichtung = "oben";

			}

			break;

		case "links": // gleich wie oben

			for (int i = 0; i < breite; i++) {
				for (int j = 0; j < breite; j++) {
					verschieben(i, j, i, j - 1);
				}
			}

			if (veraendert) {

				reverseRichtung = "rechts";

			}

			break;

		case "rechts": // gleich wie unten

			for (int i = (breite - 1); i >= 0; i--) {
				for (int j = (breite - 1); j >= 0; j--) {
					verschieben(i, j, i, j + 1);
				}
			}

			if (veraendert) {

				reverseRichtung = "links";

			}

			break;
		}

		if (veraendert) {

			for (int i = 0; i < breite; i++) {
				for (int j = 0; j < breite; j++) {
					
					if(feld[i][j].getNeu() == true) {
						
						feld[i][j].setNeu(false);
						
					}
					
				}
			}
			
			blockErstellen();
			zurueckGemacht = false;
			punkteDifferenz = punkte - punkteAlt;

		} else {

			zuege--;

		}

		

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

	public void reverseWelcheRichtung(String reverseRichtung) {

		for (int i = 0; i < breite; i++) {
			for (int j = 0; j < breite; j++) {
				
				if(feld[i][j].getNeu() == true) {
					
					feld[i][j].setNeu(false);
					feld[i][j].setWert(0);
					
				}
				
			}
		}
		
		this.reverseRichtung = reverseRichtung;

		if (!zurueckGemacht) {

			zurueckGemacht = true;

			switch (reverseRichtung) {

			case "oben": // oben links beginnend

				for (int i = 0; i < breite; i++) {
					for (int j = 0; j < breite; j++) {
						reverseVerschieben(i, j, i - 1, j);
					}
				}

				break;

			case "unten": // unten rechts beginnend

				for (int i = (breite - 1); i >= 0; i--) {
					for (int j = (breite - 1); j >= 0; j--) {
						reverseVerschieben(i, j, i + 1, j);
					}
				}

				break;

			case "links": // gleich wie oben

				for (int i = 0; i < breite; i++) {
					for (int j = 0; j < breite; j++) {
						reverseVerschieben(i, j, i, j - 1);
					}
				}

				break;

			case "rechts": // gleich wie unten

				for (int i = (breite - 1); i >= 0; i--) {
					for (int j = (breite - 1); j >= 0; j--) {
						reverseVerschieben(i, j, i, j + 1);
					}
				}

				break;

			}

		}

		punkte -= punkteDifferenz;
		
	}
	
	

	public void reverseVerschieben(int zeile, int spalte, int z, int s) {

		int ze = z + (z - zeile); // Neue Zeile plus/minus 1
		int sp = s + (s - spalte); // Neue Spalte plus/minus 1
		
		if (existiertFeld(z, s) && feld[zeile][spalte].getVerschoben() == false && feld[z][s].getWert() == 0) {

			feld[z][s].setWert(feld[zeile][spalte].getWert());
			feld[zeile][spalte].setWert(0);

			reverseVerschieben(z, s, ze, sp);

		} else if (existiertFeld(z, s) && feld[zeile][spalte].getVerschoben() == true) {

			feld[zeile][spalte].setWert(feld[zeile][spalte].getWert() / 2);
			feld[z][s].setWert(feld[zeile][spalte].getWert());
			
			reverseVerschieben(z, s, ze, sp);

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
				feld[zeile][spalte].setNeu(true);

				if (2 > hoechstesFeld) {
					hoechstesFeld = feld[zeile][spalte].getWert();
				}

				feld[zeile][spalte].setYundX(zeile, spalte); // Speichern des alten Feldes

			} else {

				feld[zeile][spalte].setWert(4);
				feld[zeile][spalte].setNeu(true);

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