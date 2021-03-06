package game;

public class Autoplay {
	int anzahl;
	String[] richtungen;

	int e; // Gewichtung Eckfeld
	int a; // Abstufungsschritte der Felder
	double l; // Gewichtung leere Felder
	double p; // Gewichtung passende naechste Runde

	public Autoplay(int e, int a, double l, double p) {
		anzahl = 4;
		richtungen = new String[anzahl];
		richtungen[0] = "oben";
		richtungen[1] = "rechts";
		richtungen[2] = "unten";
		richtungen[3] = "links";

		this.e = e;
		this.a = a;
		this.l = l;
		this.p = p;
	}

	public String zufaelligeRichtung() {
		int i = (int) (Math.random() * 4);
		return richtungen[i];
	}

	public String muster(Spielfeld feld) {
		if (feld.verschiebbar("unten")) {
			return "unten";
		} else if (feld.verschiebbar("links")) {
			return "links";
		} else if (feld.verschiebbar("rechts")) {
			return "rechts";
		}
		return zufaelligeRichtung();
	}

	public String naechsterZug(Spielfeld feld) {
		int pos = -1; // findet die position des groessten Feldes
		int max = 0; // score(feld); //angenommen die Anfangsposition hat den hoechsten Score

		int[] scores = new int[anzahl]; // Feld fuer die Scores der einzelnen Richtungen

		for (int i = 0; i < anzahl; i++) {
			Spielfeld temp = new Spielfeld(feld); // Erstellt temporären Klon
			temp.setHinzufuegen(false); // unterdrueckt neue Bloecke
			temp.welcheRichtung(richtungen[i]); // verschiebt diesen
			scores[i] = score(temp); // speichert den aktuellen Score
			if (scores[i] > max) {
				max = scores[i];
				pos = i;
			} // Nimmt immer die erste Option

		}
		return (pos > -1) ? richtungen[pos] : zufaelligeRichtung();
	}

	public int score(Spielfeld f) { // Bewertet jedes Feld mit einer Zahl
		// nachfolgend wird nur Inkrement verwendet, also "+" vor "="
		int bewertung = 0;
		if (f.getVeraendert()) {
			bewertung += (!ecke(f).equals("keineEcke")) ? ecken(f, ecke(f)) : ecken(f, ecke(f)) / 25; // grosse Felder
																										// in
			bewertung += ((f.getBreite() * f.getBreite()) - f.getAnzahl()) * (f.getHoechstesFeld() * l); // leere Felder
			bewertung += passende(f) * (f.getHoechstesFeld() * p); // moegliches Zusammenschieben nächste Runde

		} // bei keiner Veraenderung ist das Verschieben unnoetig

		return bewertung;
	}

	public String ecke(Spielfeld f) { // Gibt die Ecke mit dem groessten Feld zuruck
		if (f.getFeld()[0][0].getWert() == f.getHoechstesFeld()) {
			return "obenlinks";

		} else if (f.getFeld()[0][(f.getBreite() - 1)].getWert() == f.getHoechstesFeld()) {
			return "obenrechts";

		} else if (f.getFeld()[(f.getBreite() - 1)][0].getWert() == f.getHoechstesFeld()) {
			return "untenlinks";

		} else if (f.getFeld()[(f.getBreite() - 1)][(f.getBreite() - 1)].getWert() == f.getHoechstesFeld()) {
			return "untenrechts";

		} else {
			return "keineEcke";
		}

	}

	public int ecken(Spielfeld f, String ecke) { // Vergibt Punkte fuer eckennahe Felder
		int punkte = 0; // Punkte fuer grosse Felder in der Ecke
		int abs = e * f.getBreite();

		int gruppe = 0;
		int b = 0;
		int c = 0;

		// Viermal fast identische betrachtung jeweils des halben Boardes
		// Gegen den Uhrzeigersinn wird begonnen mit der Gruppe

		// Oben links ausgehend
		switch (ecke) {
		case ("obenlinks"):
			for (int i = 0; i < f.getBreite(); i++) { // Fuer alle. "Gruppen"
				gruppe = 0;
				b = 0; // fester Wert
				c = i; // variabler Wert
				for (int j = 0; j < (i + 1); j++) {// Fuerjedes Feld in "Gruppe"
					gruppe += f.getFeld()[c][b].getWert(); // Feld ist gespiegelt
					b++;
					c--;
				}
				punkte += (abs) * gruppe;
				abs = ((abs - a) > 0) ? abs - a : 0;
			}

			break;
		// Oben rechts ausgehend
		case ("obenrechts"):
			for (int i = (f.getBreite() - 1); i >= 0; i--) { // Fuer Anzahl d. "Gruppen"
				gruppe = 0;
				b = i; // variabler Wert
				c = 0; // fester Wert
				for (int j = 0; j < (f.getBreite() - i); j++) {// Fuer Anzahl Felder in "Gruppe"
					gruppe += f.getFeld()[c][b].getWert(); // Feld ist gespiegelt
					b++;
					c++;
				}
				punkte += (abs) * gruppe;
				abs = ((abs - a) > 0) ? abs - a : 0;
			}

			break;
		// Unten links ausgehend
		case ("untenlinks"):
			for (int i = (f.getBreite() - 1); i >= 0; i--) { // Fuer Anzahl d. "Gruppen"
				gruppe = 0;
				b = ((f.getBreite() - 1) - i); // variabler Wert
				c = (f.getBreite() - 1); // fester Wert
				for (int j = 0; j < (f.getBreite() - i); j++) {// Fuer Anzahl Felder in "Gruppe"
					gruppe += f.getFeld()[c][b].getWert(); // Feld ist gespiegelt
					b--;
					c--;
				}
				punkte += (abs) * gruppe;
				abs = ((abs - a) > 0) ? abs - a : 0;
			}

			break;

		// Unten rechts ausgehend
		case ("untenrechts"):
			for (int i = (f.getBreite() - 1); i >= 0; i--) { // Fuer Anzahl d. "Gruppen"
				gruppe = 0;
				b = (f.getBreite() - 1); // fester Wert
				c = i; // variabler Wert
				for (int j = 0; j < (f.getBreite() - i); j++) {// Fuer Anzahl Felder in "Gruppe"
					gruppe += f.getFeld()[c][b].getWert(); // Feld ist gespiegelt
					b--;
					c++;
				}
				punkte += (abs) * gruppe;
				abs = ((abs - a) > 0) ? abs - a : 0;
			}

			break;

		// Nuancen fuer beliebige Felder
		case ("keineEcke"):
			int mx = 0;
			mx = (ecken(f, "obenlinks") >= mx) ? ecken(f, "obenlinks") : mx;
			mx = (ecken(f, "obenrechts") >= mx) ? ecken(f, "obenrechts") : mx;
			mx = (ecken(f, "untenlinks") >= mx) ? ecken(f, "untenlinks") : mx;
			mx = (ecken(f, "untenrechts") >= mx) ? ecken(f, "untenrechts") : mx;
			punkte = mx;
			break;

		default:
			punkte = 0;
			break;
		}

		return punkte;

	}

	public int passende(Spielfeld f) { // gibt die Anzahl von passenden in der naechsten Runde
		int max = 0;

		for (int i = 0; i < anzahl; i++) {
			Spielfeld temp = new Spielfeld(f); // Erstellt temporaeren Klon
			temp.setHinzufuegen(false); // mit objektiver Ansicht
			temp.welcheRichtung(richtungen[i]); // verschiebt diesen
			max = (temp.getZusammenschuebe() > max) ? temp.getZusammenschuebe() : max;

		}
		return max;
	}

}
