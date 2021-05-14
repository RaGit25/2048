package game;


class Block
{
    int wert;
    int x;
    int y;
    
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
}
