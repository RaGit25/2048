package game;

public class Autoplay {
	int anzahl;
	String[] richtungen;

	int e = 3; // Gewichtung Eckfeld
	int a = 2; // Abstufungsschritte der Felder
	double l = 1; // Gewichtung leere Felder
	double p = 0.5; // Gewichtung passende naechste Runde

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

	public String muster(Spielfeld feld) {
		if(verschiebbar(feld, "unten")) {
			return "unten";
		}else if(verschiebbar(feld, "links")) {
			return "links";
		}
		else if(verschiebbar(feld, "rechts")) {
			return "rechts";
		}
		return zufaelligeRichtung();
	}
	
	public Boolean verschiebbar(Spielfeld f, String richtung) {
		Spielfeld temp = new Spielfeld(f); // Erstellt temporären Klon
		temp.setHinzufuegen(false); // unterdrueckt neue Bloecke
		temp.welcheRichtung(richtung); // verschiebt diesen
		return (temp.veraendert) ? true : false;	//Wenn es veraendert wurde, kann man es verschieben
	}
		
	public String naechsterZug(Spielfeld feld) {
		//System.out.println(ecken(feld, ecke(feld)));
		int pos = -1; // findet die position des groessten Feldes
		int max = 0; // score(feld); //angenommen die Anfangsposition hat den hoechsten Score
		
		int[] scores = new int[anzahl]; // Feld für die Scores der einzelnen Richtungen

		for (int i = 0; i < anzahl; i++) {
			Spielfeld temp = new Spielfeld(feld); // Erstellt temporären Klon
			temp.setHinzufuegen(false); // unterdrueckt neue Bloecke
			temp.welcheRichtung(richtungen[i]); // verschiebt diesen
			scores[i] = score(temp); // speichert den aktuellen Score
			System.out.println(richtungen[i] + " > " + scores[i]);
			if (scores[i] > max) {
				max = scores[i];
				pos = i;
			} else if ((scores[i] == max) && 1 == (int) (Math.random()) * 2) {
				max = scores[i];
				pos = i;
			}
		}
		return (pos > -1) ? richtungen[pos] : zufaelligeRichtung();
	}

	public int score(Spielfeld f) { // Bewertet jedes Feld mit einer Zahl
		// nachfolgend wird nur Inkrement verwendet, also "+" vor "="
		int bewertung = 0;
		if (f.veraendert) {
			bewertung += (!ecke(f).equals("keineEcke")) ? ecken(f, ecke(f)) : 0; // große Felder in
			// bewertung = ecken(f,"keineEcke");
			bewertung += ((f.breite * f.breite) - f.getAnzahl()) * (f.getHoechstesFeld() * l); // leere Felder
			bewertung += passende(f) * (f.getHoechstesFeld() * p); // mögliches Zusammenschieben nächste Runde

		} // bei keiner Veraenderung ist das Verschieben unnoetig

		return bewertung;
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
		int punkte = 0; // Punkte für große Felder in der Ecke
		int abs = e * f.breite;

		int gruppe = 0;
		int b = 0;
		int c = 0;

		// Viermal fast identische betrachtung jeweils des halben Boardes
		// Gegen den Uhrzeigersinn

		// Oben links ausgehend
		switch (ecke) {
		case ("obenlinks"):
			for (int i = 0; i < f.breite; i++) { // Für alle. "Gruppen"
				gruppe = 0;
				b = 0; // fester Wert
				c = i; // variabler Wert
				for (int j = 0; j < (i + 1); j++) {// Fürjedes Feld in "Gruppe"
					gruppe += f.feld[c][b].getWert();	//Feld ist gespiegelt
					//System.out.println(b+"<b-c>"+c+" "+i+"<i-j>"+j);
					b++;
					c--;
				}
				punkte += (abs) * gruppe;
				abs = ((abs - a) > 0) ? abs - a : 0;
			}

			break;
			// Oben rechts ausgehend
		case ("obenrechts"):
			for (int i = (f.breite - 1); i >= 0; i--) { // Für Anzahl d. "Gruppen"
				gruppe = 0;
				b = i; // variabler Wert
				c = 0; // fester Wert
				for (int j = 0; j < (f.breite - i); j++) {// Für Anzahl Felder in "Gruppe"
					gruppe += f.feld[c][b].getWert();	//Feld ist gespiegelt
					//System.out.println(b + "<b-c>" + c + " " + i + "<i-j>" + j);
					b++;
					c++;
				}				
				punkte += (abs) * gruppe;
				abs = ((abs - a) > 0) ? abs - a : 0;
			}

			break;
		// Unten links ausgehend
		case ("untenlinks"):
			for (int i = (f.breite - 1); i >= 0; i--) { // Für Anzahl d. "Gruppen"
				gruppe = 0;
				b = ((f.breite - 1)-i); // variabler Wert
				c = (f.breite - 1); // fester Wert
				for (int j = 0; j < (f.breite - i); j++) {// Für Anzahl Felder in "Gruppe"
					gruppe += f.feld[c][b].getWert();	//Feld ist gespiegelt
					//System.out.println(b+"<b-c>"+c+" "+i+"<i-j>"+j);
					b--;
					c--;
				}
				punkte += (abs) * gruppe;
				abs = ((abs - a) > 0) ? abs - a : 0;
			}

			break;

		

		// Unten rechts ausgehend
		case ("untenrechts"):
			for (int i = (f.breite - 1); i >= 0; i--) { // Für Anzahl d. "Gruppen"
				gruppe = 0; 
				b = (f.breite - 1); // fester Wert
				c = i;				// variabler Wert
				for (int j = 0; j < (f.breite - i); j++) {// Für Anzahl Felder in "Gruppe"
					gruppe += f.feld[c][b].getWert();	//Feld ist gespiegelt
					//System.out.println(b+"<b-c>"+c+" "+i+"<i-j>"+j);
					b--;
					c++;
				}
				punkte += (abs) * gruppe;
				abs = ((abs - a) > 0) ? abs - a : 0;
			}

			break;

		// Nuancen für beliebige Felder
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

		//System.out.println(punkte + " hier in Ecke " + ecke);
		return punkte;

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