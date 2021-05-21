package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {

	static Spielfeld s = new Spielfeld(4);
	
	
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) {
			s.welcheRichtung("oben");
		} else if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) {
			s.welcheRichtung("unten");
		} else if (e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT) {
			s.welcheRichtung("links");
		} else if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			s.welcheRichtung("rechts");
		}

	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public static void main(String[] args) {
		s.blockErstellen();
		s.blockErstellen();
		//System.out.println((s.gameOver()) ? "GameOver" : "Es geht weiter");
		
		try {
            int i = 0;
            while (i < 10) {
            	//System.out.println((s.gameOver()) ? "GameOver" : "Es geht weiter");
                i--;
               Thread.sleep(2500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
