package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class Game implements KeyListener {

	static Spielfeld s = new Spielfeld(4);
	static Scanner Eingabe = new Scanner(System.in);
	
	
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
            while (!s.gameOver()) {
            	int e = Eingabe.nextInt();
            	System.out.println("You entered int "+e);
            	if (e == 8 ) {
        			s.welcheRichtung("oben");
        			s.ausdrucken();
        			e = 0;
        		} else if (e == 2) {
        			s.welcheRichtung("unten");
        			s.ausdrucken();
        			e = 0;
        		} else if (e == 4) {
        			s.welcheRichtung("links");
        			s.ausdrucken();
        			e = 0;
        		} else if (e == 6) {
        			s.welcheRichtung("rechts");
        			s.ausdrucken();
        			e = 0;
        		}
              //Thread.sleep(2500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
