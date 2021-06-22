package game;

public class Autoplay {
	static String[] richtungen;
	
	public Autoplay() {
		richtungen = new String[4];
		richtungen[0] ="oben";
		richtungen[1] ="rechts";
		richtungen[2] ="unten";
		richtungen[3] ="rechts";
		
	}
	
	public static String zufälligeRichtung() {
		int i = (int) (Math.random() * 4);
		return richtungen[i];
	}
	
	public String naechsterZug(int[][] feld) {
		
		return null;
	} 
}
