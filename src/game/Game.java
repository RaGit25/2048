package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener {

	static Spielfeld s = new Spielfeld(4);

	static Game spiel = new Game();

	static JFrame frame = new JFrame("2048");

	
	//Fenster, das zu Beginn geöffnet wird
	public static void gui() {

		frame.setSize(880, 1000); 
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);                  //wird in der MItte d. Bildschirms geöffnet
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Fenster schließt sich und code wird beendet wenn man auf X das drückt
		frame.setResizable(false);                           //fenstergröße nicht veränderbar
		frame.addKeyListener(spiel);                       
		frame.getContentPane().add(spiel);               //sorgt dafür, dass das gui existiert
	}

	
	// paint wird bei jedem Zug mit repaint() aufgerufen. 
	// erstellt im Prinzip alles, was der Spieler sieht auf zwei Methoden aufgeteilt
	 
	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;             
		g2.setColor(Color.gray);
		
		g2.fillRect(30, 80, 800, 800);   
		g.setFont(new Font("Arial", Font.PLAIN, 50));   
		g.drawString("Punktzahl: " + s.punkte, 20, 50);            
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("Drücke auf 'enter' um neu zu starten", 10, 900);
		g.setFont(new Font("Arial", Font.PLAIN, 25));  //schriftgröße der zahl im feld
		if(s.gameOver()) {
			
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.PLAIN, 30));  
			g.drawString("GAME OVER!", 10, 950);         //bei gameOver() == true erscheint dieser Text
			
		}
		
		for (int i = 0; i < s.breite; i++) {              // geht bei jedem Zug einmal durchs Feld druch und ruft felder() auf
			for (int j = 0; j < s.breite; j++) {

				felder(g, s.feld[i][j], j * 200 + 50, i * 200 + 100);

			}

		}

	}

	//kümmert sich um das Färben der Felder
	
	public void felder(Graphics g, Block block, int x, int y) {
		
		int wert = block.getWert();

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.lightGray);
		g2.fillRoundRect(x, y, 150, 150, 5, 5);     //graue, leere Felder
		g2.setColor(Color.black);
		if (wert > 0) {                                   //je nach Wert wird das Feld gefärbt
			g2.setColor(block.getFarbe());
			g2.fillRoundRect(x, y, 150, 150, 5, 5);
			g2.setColor(Color.black);
			
			g.drawString("" + wert, x + 60, y + 80);
			
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) {

			s.welcheRichtung("oben");
			frame.repaint();

		} else if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) {

			s.welcheRichtung("unten");
			frame.repaint();

		} else if (e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT) {

			s.welcheRichtung("links");
			frame.repaint();

		} else if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) {

			s.welcheRichtung("rechts");
			frame.repaint();

		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) // neues Spiel wird gestartet

		{
			s = new Spielfeld(4);
			s.blockErstellen();
			s.blockErstellen();
			frame.repaint();
		}

	}

	public void keyReleased(KeyEvent e) { //macht nichts, muss aber da sein
	}

	public void keyTyped(KeyEvent e) {    //macht nichts, muss aber da sein
	}

	
	//gui und zwei blöcke werden erstellt
	
	public static void main(String[] args) {

		gui();                  

		 s.blockErstellen(); 
		 s.blockErstellen();
	}

}
