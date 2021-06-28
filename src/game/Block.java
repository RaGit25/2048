package game;

import java.awt.Color;

class Block {
	int wert;

	int xalt;
	int yalt;

	Color farbe;
	Boolean verschoben; // Spielmechanik: Verschobenes kann nicht erneut verbunden werden

	Block() { // Bloecke werden immer leer erstellt
		this.wert = 0;

		this.xalt = 0;
		this.yalt = 0;

		verschoben = false;
	}

	Block(Block alt) { // Copy-Constructor
		this.wert = alt.wert;
		this.farbe = alt.farbe;
		this.verschoben = alt.verschoben;
		this.xalt = alt.xalt;
		this.yalt = alt.yalt;

	}

	@Override
	public boolean equals(Object o) { // eigene Vergleichsmethode
		if (o instanceof Block) {
			Block b = (Block) o;
			return (this.wert == b.wert) && (this.xalt == b.xalt) && (this.yalt == b.yalt);
			//Die anderen Attribute werden beim Vergleich ignoriert
		} else {
			return false;
		}
	}

	// Getter Und Setter

	public int getWert() {
		return wert;
	}

	public void setWert(int wert) {
		this.wert = wert;
	}

	public int getXalt() {
		return xalt;
	}

	public int getYalt() {
		return yalt;
	}

	public void setYundX(int x, int y) { // Speicher, welcher Block der Ursprung ist
		this.xalt = x;
		this.yalt = y;
	}

	public void setFarbe() {

		if (this.getWert() == 2) {

			farbe = new Color(236, 228, 219);

		} else if (this.getWert() == 4) {

			farbe = new Color(236, 225, 204);

		} else if (this.getWert() == 8) {

			farbe = new Color(243, 180, 130);

		} else if (this.getWert() == 16) {

			farbe = new Color(233, 154, 109);

		} else if (this.getWert() == 32) {

			farbe = new Color(232, 130, 103);

		} else if (this.getWert() == 64) {

			farbe = new Color(230, 104, 72);

		} else if (this.getWert() == 128) {

			farbe = new Color(232, 208, 125);

		} else if (this.getWert() == 256) {

			farbe = new Color(232, 204, 114);

		} else if (this.getWert() == 512) {

			farbe = new Color(232, 199, 101);

		} else if (this.getWert() == 1024) {

			farbe = new Color(231, 197, 89);

		} else if (this.getWert() == 2048) {

			farbe = new Color(231, 194, 79);

		} else if (this.getWert() == 4096) {

			farbe = new Color(224, 110, 112);

		} else if (this.getWert() == 8192) {

			farbe = new Color(220, 88, 94);

		} else if (this.getWert() == 16384) {

			farbe = new Color(225, 79, 73);
		}
	}

	public Color getFarbe() {

		this.setFarbe();
		return farbe;
	}

	public Boolean getVerschoben() {
		return verschoben;
	}

	public void setVerschoben(Boolean verschoben) {
		this.verschoben = verschoben;

	}

}
