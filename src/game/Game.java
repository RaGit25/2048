package game;

public class Game {
	static Spielfeld s;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Test");
		System.out.println("E");
		System.out.println("E2");
		System.out.println("Test2");
		s = new Spielfeld(4);
		System.out.println((s.gameOver()) ? "GameOver":"Es geht weiter");
	}

}
