package game;


class Block
{
    int wert;
    int x;
    int y;
    
    Boolean verschoben; //Spielmechanik: Verschobenes kann nicht erneut verbunden werden
    
    Block (int wert,int spalte, int zeile)
    {
        this.wert = wert;
        this.x = spalte;
        this.y = zeile;
    }
    
    //Getter Und Setter

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
	
	public Boolean getVerschoben() {
		return verschoben;
	}

	public void setVerschoben(Boolean verschoben) {
		this.verschoben = verschoben;
	}

}
