package game;

public class Autoplay {
	int anzahl;
	String[] richtungen;

	int eckenpunkte = 20;
	int leerpunkte = 2;

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
		 return (z>0.5) ? true : false;
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
			Spielfeld temp = new Spielfeld(feld);
			temp.welcheRichtung(richtungen[i]);
			scores[i] = score(temp);
			System.out.println(richtungen[i]+" > "+scores[i]);	//-> Zum Veranschaulichen
			if (scores[i] > max) {
				max = scores[i];
				pos = i;
			}
			else if((scores[i] == max) && zufall()) {
				max = scores[i];
				pos = i;
			}
		}
		return (pos > -1) ? richtungen[pos] : zufaelligeRichtung();
	}

	public int score(Spielfeld f) {	//Bewertet jedes Feld mit einer Zahl
		//nachfolgend wird nur Inkrement verwendet
		int bewertung = 0;
		System.out.println(ecke(f));
		bewertung += (ecke(f)) ? eckenpunkte : 0;
		bewertung += ((f.breite * f.breite) - f.getAnzahl()) * leerpunkte; 			//Anzahl leere = Alles - Anzahl enthaltene
		
		bewertung =(f.veraendert) ? bewertung : 0;	//bei keiner Veraenderung ist das Verschieben unnoetig
		return bewertung;
	}

	public Boolean ecke(Spielfeld f) {	//Schaut ob groesstes Feld in der Ecke ist
		if (f.feld[0][0].getWert() == f.getHoechstesFeld()) {
			return true;
		} else if (f.feld[0][(f.breite-1)].getWert() == f.getHoechstesFeld()) {
			return true;
		} else if (f.feld[(f.breite-1)][0].getWert() == f.getHoechstesFeld()) {
			return true;
		} else if (f.feld[(f.breite-1)][(f.breite-1)].getWert() == f.getHoechstesFeld()) {
			return true;
		} else {
			return false;
		}
	}
	
}
