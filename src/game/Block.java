package game;

import java.awt.Color;


class Block {
	int wert;
	int x;
	int y;
	Color farbe;
  Boolean verschoben; //Spielmechanik: Verschobenes kann nicht erneut verbunden werden
  
	Block(int wert, int spalte, int zeile) {
		this.wert = wert;
		this.x = spalte;
		this.y = zeile;
    
     verschoben = false;
	}

	Block() {
	}
	// Getter Und Setter

	public int getWert() {
		return wert;
	}

	public void setWert(int wert) {
		this.wert = wert;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


	public void setFarbe() {

		if (this.getWert() == 2) {

			farbe = new Color(238, 228, 218);

		} else if (this.getWert() == 4) {

			farbe = new Color(237, 224, 200);

		} else if (this.getWert() == 8) {

			farbe = new Color(242, 177, 121);

		} else if (this.getWert() == 16) {

			farbe = new Color(245, 149, 99);

		} else if (this.getWert() == 32) {

			farbe = new Color(246, 124, 95);

		} else if (this.getWert() == 64) {

			farbe = new Color(246, 94, 59);

		} else if (this.getWert() == 128) {

			farbe = new Color(237, 207, 114);

		} else if (this.getWert() == 256) {

			farbe = new Color(237, 204, 97);

		} else if (this.getWert() == 512) {

			farbe = new Color(237, 200, 80);

		} else if (this.getWert() == 1024) {

			farbe = new Color(237, 197, 63);

		} else if (this.getWert() == 2048) {

			farbe = new Color(237, 194, 46);

		} else if (this.getWert() == 4096) {

			farbe = new Color(237, 194, 46);

		} else if (this.getWert() == 8192) {

			farbe = new Color(237, 194, 46);

		} else if (this.getWert() == 16384) {

			farbe = new Color(237, 194, 46);
		}
	}

	public Color getFarbe() {

		this.setFarbe();
		return farbe;

	
	public Boolean getVerschoben() {
		return verschoben;
	}

	public void setVerschoben(Boolean verschoben) {
		this.verschoben = verschoben;

	}

}
