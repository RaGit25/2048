package game;

public class Test {
	public static Account a;
	public static Autoplay au;

	static int e;
	static int an;
	static double l;
	static double p;

	public static void main(String[] args) {
		a = new Account("antinull", 4);	//Nur, dass es nicht null ist
		for (int i = 0; i < 100; i++) {
			//a = new Account(String.valueOf(i), 4);
			a.s = new Spielfeld(a.s.getBreite());
			a.st.updateSpielfeld(a.s);
			a.s.blockErstellen();
			a.s.blockErstellen();
			
			
			/*
			e = (int) (Math.random() * 15);
			an = (int) (Math.random() * 10);
			l = (Math.random() * 3);
			p = (Math.random() * 2);
			*/
			///*
			e = 8;
			an = 9;
			l = 1.5;
			p = 1.45;
			//*/	
			
			au = new Autoplay(e, an, l, p);

			for (int j = 0; j < 100; j++) {
				while (!a.s.gameOver()) {
					a.s.welcheRichtung(au.naechsterZug(a.s));
					a.st.update();
				}
				a.st.updateEnde();
				a.s = new Spielfeld(a.s.getBreite());
				a.st.updateSpielfeld(a.s);
				a.s.blockErstellen();
				a.s.blockErstellen();

			}
			if(a.st.getwinLoseRatio() > 0.05) {
				//System.out.println("Ratio* " + a.st.getwinLoseRatio() + " e:" + e + " an: " + an + " l: " + l + " p: " + p);
				//System.out.println("------");
			}
			// JSONVerwalter.speichern(a);
		}
		
			System.out.println("Ratio* " + a.st.getwinLoseRatio() + " e:" + e + " an: " + an + " l: " + l + " p: " + p);

	}

}
