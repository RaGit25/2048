package game;

public class Account {
	Spielfeld s;
	Stats st;
	
	public Account(int a) {
		s = new Spielfeld(a);
		st = new Stats(s);
	}
}
