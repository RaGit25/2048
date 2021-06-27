package game;

public class Autoplay {
	int anzahl;
	String[] richtungen;


	int e = 3; // Gewichtung Eckfeld
	int a = 3; // Abstufungsschritte der Felder
	int l = 2; // Gewichtung leere Felder
	int p = 2; // Gewichtung passende naechste Runde


	public Autoplay() {
		anzahl = 4;
		richtungen = new String[anzahl];
		richtungen[0] = "oben";
		richtungen[1] = "rechts";
		richtungen[2] = "unten";
		richtungen[3] = "links";
	}

	public String zufaelligeRichtung() {
		int i = (int) (Math.random() * 4);
		return richtungen[i];
	}
	
	public String naechsterZug(Spielfeld feld) {
		int pos = -1; 								// findet die position des groessten Feldes
		int max = 0;  //score(feld);			//angenommen die Anfangsposition hat den hoechsten Score
				
		
		
		int[] scores = new int[anzahl];	//Feld für die Scores der einzelnen Richtungen
		
		

		for (int i = 0; i < anzahl; i++) {
			Spielfeld temp = new Spielfeld(feld); // Erstellt temporären Klon
			//temp.setBloecke(false);	//unterdrueckt neue Bloecke
			temp.welcheRichtung(richtungen[i]); // verschiebt diesen
			scores[i] = score(temp); // speichert den aktuellen Score
			System.out.println(richtungen[i] + " > " + scores[i]);
			if (scores[i] > max) {
				max = scores[i];
				pos = i;
			}
			else if((scores[i] == max) && 1 == (int) (Math.random())*2) {
				max = scores[i];
				pos = i;
			}
		}
		return (pos > -1) ? richtungen[pos] : zufaelligeRichtung();
	}


	public int score(Spielfeld f) { // Bewertet jedes Feld mit einer Zahl
		// nachfolgend wird nur Inkrement verwendet, also Rechenzeichen vor
		// Gleichheitszeichen
		if (f.veraendert) {
			int bewertung = 0;
			bewertung += (!ecke(f).equals("keineEcke")) ? (f.getHoechstesFeld() * e) : 0;
			bewertung += (!ecke(f).equals("keineEcke")) ? ecken(f, ecke(f)) : 0; // große Felder in
																										// der Ecke
			bewertung += ((f.breite * f.breite) - f.getAnzahl()) * (f.getHoechstesFeld() * l); // leere Felder
			bewertung += passende(f) * (f.getHoechstesFeld() * p); // mögliches Zusammenschieben nächste Runde

			return bewertung;
		} else {// bei keiner Veraenderung ist das Verschieben unnoetig
			return 0;
		}
	}

	public String ecke(Spielfeld f) { // Gibt die Ecke mit dem groessten Feld zuruck
		if (f.feld[0][0].getWert() == f.getHoechstesFeld()) {
			return "obenlinks";

		} else if (f.feld[0][(f.breite - 1)].getWert() == f.getHoechstesFeld()) {
			return "obenrechts";

		} else if (f.feld[(f.breite - 1)][0].getWert() == f.getHoechstesFeld()) {
			return "untenlinks";

		} else if (f.feld[(f.breite - 1)][(f.breite - 1)].getWert() == f.getHoechstesFeld()) {
			return "untenrechts";

		} else {
			return "keineEcke";
		}

	}

	public int ecken(Spielfeld f, String ecke) { // Vergibt Punkte für eckennahe Felder
		int p = 0; // Punkte für große Felder in der Ecke
		int abs = e * f.breite;

		// Viermal fast identische betrachtung jeweils des halben Boardes
		// Oben links ausgehend
		switch (ecke) {
		case ("obenlinks"):
			for (int i = 0; i < f.breite; i++) { // für die gesamte breite
				abs = (abs-a > 0) ? abs-a : 1;
				for (int j = 0; j < f.breite; j++) // weil das Feld zwei-dimensional ist
					p += (abs) * f.feld[i][j].getWert();
			}
			break;

		// Oben rechts ausgehend
		case ("obenrechts"):
			for (int i = f.breite; i < 0; i--) { // für die gesamte breite
				abs = (abs-a > 0) ? abs-a : 1;
				for (int j = 0; j < f.breite; j++) // weil das Feld zwei-dimensional ist
					p += (abs) * f.feld[i][j].getWert();
			}
			break;

		// Unten links ausgehend
		case ("untenlinks"):
			for (int i = 0; i < f.breite; i++) { // für die gesamte breite
				abs = (abs-a > 0) ? abs-a : 1;
				for (int j = f.breite; j < 0; j--) // weil das Feld zwei-dimensional ist
					p += (abs) * f.feld[i][j].getWert();
			}
			break;

		// Unten rechts ausgehend
		case ("untenrechts"):
			for (int i = f.breite; i < 0; i--) { // für die gesamte breite
				abs = (abs-a > 0) ? abs-a : 1;
				for (int j = f.breite; j < 0; j--) // weil das Feld zwei-dimensional ist
					p += (abs) * f.feld[i][j].getWert();
			}
			break;

		default:
			p = 0;
			break;
		}

		//System.out.println(p+" hier in Ecke "+ecke);
		return p;

	}

	public int passende(Spielfeld f) {
		int max = 0;

		for (int i = 0; i < anzahl; i++) {
			Spielfeld temp = new Spielfeld(f); // Erstellt temporären Klon
			temp.welcheRichtung(richtungen[i]); // verschiebt diesen
			int z = temp.getZusammenschuebe();
			max = (z > max) ? z : max;
			
		}
		return max;
	}
	
}