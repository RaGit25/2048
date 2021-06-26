package game;

public class Autoplay {
	int anzahl;
	String[] richtungen;
	
	int e = 4; //Gewichtung Eckfeld
	int ne = 2; //Gewichtung neben Eckfeld
	int l = 2; //Gewichtung leer durch
	int p = 8; //Gewichtung passende durch

	public Autoplay() {
		anzahl = 4;
		richtungen = new String[anzahl];
		richtungen[0] = "oben";
		richtungen[1] = "rechts";
		richtungen[2] = "unten";
		richtungen[3] = "links";
	}

	public Boolean zufall() {
		double z = Math.random();
		return (z > 0.5) ? true : false;
	}

	public String zufaelligeRichtung() {
		int i = (int) (Math.random() * 4);
		return richtungen[i];
	}

	public String naechsterZug(Spielfeld feld) {
		int pos = -1; // findet die position des groessten Feldes
		int max = 0;

		int[] scores = new int[anzahl]; // Feld für die Scores der einzelnen Richtungen

		for (int i = 0; i < anzahl; i++) {
			Spielfeld temp = new Spielfeld(feld); // Erstellt temporären Klon
			temp.welcheRichtung(richtungen[i]); // verschiebt diesen
			scores[i] = score(temp); // speichert den aktuellen Score
			System.out.println(richtungen[i] + " > " + scores[i]); // -> Zum Veranschaulichen
			if (scores[i] > max) {
				max = scores[i];
				pos = i;
			} else if ((scores[i] == max) && zufall()) {
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
			bewertung += ecken(f); // große Felder in der Ecke
			bewertung += ((f.breite * f.breite) - f.getAnzahl()) * (f.getHoechstesFeld()/l); // leere Felder
			bewertung += passende(f) * (f.getHoechstesFeld()/p);	//mögliches Zusammenschieben nächste Runde

			return bewertung;
		} else {// bei keiner Veraenderung ist das Verschieben unnoetig
			return 0;
		} 
	}

	public int ecken(Spielfeld f) { // Schaut ob groesstes Feld in der Ecke ist
		int p = 0; // Punkte für große Felder in der Nähe
		if (f.feld[0][0].getWert() == f.getHoechstesFeld()) {

			p += e * f.feld[0][0].getWert();
			p += ne * f.feld[0][1].getWert();
			p += ne * f.feld[1][0].getWert();

		} else if (f.feld[0][(f.breite - 1)].getWert() == f.getHoechstesFeld()) {

			p += e * f.feld[0][(f.breite - 1)].getWert();
			p += ne * f.feld[0][(f.breite - 2)].getWert();
			p += ne * f.feld[0][(f.breite - 2)].getWert();

		} else if (f.feld[(f.breite - 1)][0].getWert() == f.getHoechstesFeld()) {

			p += e * f.feld[(f.breite - 1)][0].getWert();
			p += ne * f.feld[(f.breite - 2)][0].getWert();
			p += ne * f.feld[(f.breite - 2)][0].getWert();

		} else if (f.feld[(f.breite - 1)][(f.breite - 1)].getWert() == f.getHoechstesFeld()) {

			p += e * f.feld[(f.breite - 1)][(f.breite - 1)].getWert();
			p += ne * f.feld[(f.breite - 2)][(f.breite - 2)].getWert();
			p += ne * f.feld[(f.breite - 2)][(f.breite - 2)].getWert();

		}
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
