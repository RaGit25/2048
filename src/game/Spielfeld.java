package game;

class Spielfeld {
	int punkte;

	Block[][] feld; // Matrix mit allen Bloecken
	int[] index; // Matrix als Liste, vlt.unnötig

	Spielfeld(int g) {
		feld = new Block[g][g];
		index = new int[g * g];
	}

	public int getGroesse() {
		return feld.length;
	}

	public int getBreite() {
		return (int) Math.sqrt(feld.length);
	}

	public int getAnzahl() {
		int a = 0;
		for (int i = 0; i < getBreite(); i++) {
			for (int j = 0; j < getBreite(); j++) {
				if (feld[i][j] != null) {
					a++;
				}
			}
		}
		return a;
	}

	public Boolean existiertFeld(int x, int y) { // überprüft ob ein Block existiert
		try {
			return (feld[x][y] != null) ? true : false;
		} catch (ArrayIndexOutOfBoundsException e) { // wenn der Block außerhalb des Feldes liegt -> existiert nicht
			return false;
		}
	}

	public Boolean gameOver() {
		int zahl = 0;
		if (getAnzahl() >= getGroesse()) { // Prüft ob noch freie Felder da sind
			// Einmal für jeden Block ausprobieren
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

	public void blockErstellen() {
	}

	public void verschieben() {
	}

	public Block zusammmenschieben() {
		return null;
	}
}

/*
 * public int[] leereFelder(){ int l = groesse-anzahl; int[] leereFelder = new
 * int[l]; leereFelder[0] = l;
 * 
 * for(int i = 0; i < index.legth;i++){ if(index[i]==null){ for (int k = 0;k <
 * leereFelder.legth;k++){ if(leereFelder[k]==null){ leereFelder[k] = i; } } } }
 * return leereFelder; } public int[] getIndex(){ for (int i = 0; i <
 * getGroesse(); i++) { for (int j = 0; j < getGroesse(); j++) { if (feld[i][j]
 * != null) { a++; } } }
 * 
 */