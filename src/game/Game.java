package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.border.Border;

public class Game extends JPanel implements KeyListener, ActionListener {

	static Account a = new Account(4);

	//static Stats st = new Stats();

	static Boolean statsAktiv = false;

	static Game game = new Game();

	static JFrame gameFrame = new JFrame("2048");

	static String accountString[] = { "Konto auswaehlen", "aaaaaaaaa", "bbbbbbbb" };
	static JComboBox<Object> accountAuswahlliste = new JComboBox<Object>(Game.accountString);

	static JPanel centerPanel = new JPanel(); // Mehrere Panels benoetigt (für den Layoutmanager)
	static JPanel panel1 = new JPanel();
	static JPanel panel2 = new JPanel();
	static JPanel panel3 = new JPanel();
	static JPanel panel4 = new JPanel();

	static JLabel punkte = new JLabel(); // Punktzahl ist jetzt in einem JLabel in "panel1"
	static JLabel rekord = new JLabel();
	static JLabel hilfe = new JLabel();

	static JLabel titel = new JLabel();

	static JButton restart = new JButton(); // restart Button oben links im Spiel
	static JButton zurueck = new JButton();
	static JButton tipp = new JButton();

	static JButton stats = new JButton();

	Game() {

		setPreferredSize(new Dimension(850, 1000)); // macht das Spielfeld im Panel "centerPanel" sichtbar

	}


	public static void gameGui() { // das Spielfenster

		gameFrame.setLayout(new BorderLayout()); // BorderLayout = 1 panel in der Mitte und 4 außenrum

		centerPanel.setBackground(Color.gray);
		centerPanel.setPreferredSize(new Dimension(100, 100)); // grösse d. panels in d. mitte
		centerPanel.add(new Game()); // sichtbarmachen der Felder

		// punktzahl
		punkte.setText("<html>Punkte <br>" + a.s.punkte + "</html>");
		Border border = BorderFactory.createLineBorder(Color.gray, 5);
		panel1.add(punkte);
		panel1.setLayout(null);
		punkte.setBounds(425, 15, 150, 80);
		punkte.setFont(new Font("Arial", Font.PLAIN, 30));
		punkte.setBorder(border);
		punkte.setVerticalAlignment(JLabel.TOP);
		punkte.setHorizontalAlignment(JLabel.CENTER);
		punkte.setBackground(Color.lightGray);
		punkte.setOpaque(true);

		// rekord
		rekord.setText("<html>Rekord <br>" + a.st.getRekord() + "</html>"); // rekord fehlt noch, weil keine Speicherung
		Border border1 = BorderFactory.createLineBorder(Color.gray, 5);
		panel1.add(rekord);
		rekord.setLayout(null);
		rekord.setBounds(600, 15, 150, 80);
		rekord.setFont(new Font("Arial", Font.PLAIN, 30));
		rekord.setBorder(border1);
		rekord.setVerticalAlignment(JLabel.TOP);
		rekord.setHorizontalAlignment(JLabel.CENTER);
		rekord.setBackground(Color.lightGray);
		rekord.setOpaque(true);

		// Titel
		titel.setText("2048");
		titel.setBounds(35, 5, 210, 110);
		titel.setFont(new Font("Arial", Font.BOLD, 80));
		titel.setForeground(Color.darkGray);
		panel1.add(titel);

		// restart button
		panel1.add(restart);
		restart.setBounds(700, 120, 50, 50);
		ImageIcon icon = new ImageIcon("restart.png"); // bild mit den 2 pfeilen, siehe Dateien d. Projekts
		restart.setIcon(icon);
		restart.setBorder(BorderFactory.createEtchedBorder());
		restart.setFocusable(false);
		restart.setBackground(Color.lightGray);
		restart.addActionListener((ActionEvent e) -> { // wenn man auf den button drueckt

			if (!statsAktiv) {

				newGame();
				
			}

		});

		// zurueck button
		panel1.add(zurueck);
		zurueck.setBounds(625, 120, 50, 50);
		ImageIcon icon1 = new ImageIcon("back.png"); // bild mit den 2 pfeilen, siehe Dateien d. Projekts
		zurueck.setIcon(icon1);
		zurueck.setBorder(BorderFactory.createEtchedBorder());
		zurueck.setFocusable(false);
		zurueck.setBackground(Color.lightGray);
		zurueck.addActionListener((ActionEvent e) -> { // wenn man auf den button drueckt

			if (!statsAktiv) {

			}

		});

		// tipp button
		panel1.add(tipp);
		tipp.setBounds(550, 120, 50, 50);
		tipp.setText("?");
		tipp.setFont(new Font("Arial", Font.BOLD, 35));
		tipp.setBorder(BorderFactory.createEtchedBorder());
		tipp.setFocusable(false);
		tipp.setBackground(Color.lightGray);
		panel1.add(hilfe);
		hilfe.setBounds(250, 100, 200, 100);
		hilfe.setFont(new Font("Arial", Font.PLAIN, 30));
		hilfe.setForeground(Color.green);
		hilfe.setVisible(false);
		
		tipp.addActionListener((ActionEvent e) -> { // wenn man auf den button drueckt

			if (!statsAktiv && !a.s.gameOver()) {

				Autoplay au = new Autoplay();
				
				if (au.naechsterZug(a.s).equals("oben")) {
					
					hilfe.setText("Tipp: oben");
					hilfe.setVisible(true);

				} else if (au.naechsterZug(a.s).equals("unten")) {

					hilfe.setText("Tipp: unten");
					hilfe.setVisible(true);
					
				} else if (au.naechsterZug(a.s).equals("links")) {

					hilfe.setText("Tipp: links");
					hilfe.setVisible(true);
					
				} else if (au.naechsterZug(a.s).equals("rechts")) {

					hilfe.setText("Tipp: rechts");
					hilfe.setVisible(true);
					
				}

			}

		});

		// stats button
		panel1.add(stats);
		stats.setBounds(425, 120, 100, 50);
		stats.setBorder(BorderFactory.createEtchedBorder());
		stats.setFocusable(false);
		stats.setBackground(Color.lightGray);
		stats.setText("Statistiken");
		stats.setFont(new Font("Arial", Font.PLAIN, 20));
		stats.addActionListener((ActionEvent e) -> {

			if (statsAktiv == false) {

				statsAktiv = true;

			} else {

				statsAktiv = false;

			}

			centerPanel.repaint();

		});

		// festlegen d. groessen d. anderen panels
		panel1.setPreferredSize(new Dimension(100, 180));
		panel4.setPreferredSize(new Dimension(100, 60));
		panel2.setPreferredSize(new Dimension(30, 30));
		panel3.setPreferredSize(new Dimension(30, 30));

		// borderlayout: position der einzelnen panels (north = oben, center = mitte,
		// usw.)
		gameFrame.add(centerPanel, BorderLayout.CENTER);
		gameFrame.add(panel1, BorderLayout.NORTH);
		gameFrame.add(panel2, BorderLayout.EAST);
		gameFrame.add(panel3, BorderLayout.WEST);
		gameFrame.add(panel4, BorderLayout.SOUTH);

		gameFrame.setSize(800, 1000);
		gameFrame.setVisible(true);
		gameFrame.setLocationRelativeTo(null); // wird in der MItte d. Bildschirms geoeffnet
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fenster schliesst sich und code wird beendet wenn
																	// man auf X das drueckt
		gameFrame.setResizable(false); // fenstergroesse nicht veraenderbar
		gameFrame.addKeyListener(game);

	}

	// paint wird bei jedem Zug mit repaint() aufgerufen.
	// erstellt im Prinzip alles, was der Spieler sieht auf zwei Methoden aufgeteilt

	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.gray);
		g.setColor(Color.black);

		if (statsAktiv == true) {

			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.drawString("Statistiken", 70, 30);
			g.setFont(new Font("Arial", Font.PLAIN, 30));
			g.setColor(Color.darkGray);
			
			g.drawString("Anzahl aller gesammelten Punkte:", 70, 120);
			g.drawString(" " + a.st.getPunkteGesamt(), 550, 120);

			g.drawString("Hoechstes erreichtes Feld", 70, 160);
			g.drawString(" " + a.st.getFeldHoch(), 550, 160);

			g.drawString("Anzahl aller gespielten Runden:", 70, 200);
			g.drawString(" " + a.st.getRundenAlt(), 550, 200);

			g.drawString("Anzahl aller Runden mit 2048:", 70, 240);
			g.drawString(" " + a.st.getGewonnenAlt(), 550, 240);

			g.drawString("Anteil der Runden mit 2048:", 70, 280);
			DecimalFormat df = new DecimalFormat("#.##");
			g.drawString(" " + df.format(a.st.getwinLose() * 100) + "%", 550, 280);

		} else {

			int breite = 0;
			int schrift = 0;
			int x = 0;
			int y = 0;
			int abstand = 0;
			int schriftX = 0;
			int schriftY = 0;
			int r = 0;
			int v = 0;
			int e = 0;

			if (a.s.breite == 4) {

				breite = 155;
				schrift = 80;
				x = 85;
				y = 15;
				abstand = 175;
				schriftX = 55;
				schriftY = 105;

			} else if (a.s.breite == 5) {

				breite = 120;
				schrift = 65;
				x = 85;
				y = 15;
				abstand = 140;
				schriftX = 45;
				schriftY = 80;
				v = 5;
				e = 5;

			} else if (a.s.breite == 6) {

				breite = 95;
				schrift = 60;
				x = 85;
				y = 15;
				abstand = 117;
				schriftX = 35;
				schriftY = 70;
				v = 8;
				e = 13;

			} else if (a.s.breite == 7) {

				breite = 75;
				schrift = 45;
				x = 88;
				y = 18;
				abstand = 100;
				schriftX = 24;
				schriftY = 58;
				v = 20;
				e = 32;
				r = 8;

			} else if (a.s.breite == 8) {

				breite = 70;
				schrift = 35;
				x = 85;
				y = 15;
				abstand = 87;
				schriftX = 26;
				schriftY = 52;
				v = 27;
				e = 38;
				r = 10;

			}

			for (int i = 0; i < a.s.breite; i++) { // geht bei jedem Zug einmal durchs Feld druch und ruft felder() auf
				for (int j = 0; j < a.s.breite; j++) {

					felder(g, a.s.feld[i][j], j * abstand + x, i * abstand + y, breite, schrift, schriftX, schriftY, v, e,
							r);

				}

			}

			if (a.s.gameOver()) { // wenn man verloren hat

				g.setColor(Color.black);
				g.setFont(new Font("Arial", Font.BOLD, 40));
				g.drawString("Game Over!", 300, 340);
				g.setFont(new Font("Arial", Font.BOLD, 30));
				g.drawString("Druecke 'enter' um neu zu starten.", 200, 390);

			}

		}
	}

	// kuemmert sich um das Faerben der Felder

	public void felder(Graphics g, Block block, int x, int y, int breite, int schrift, int schriftX, int schriftY,
			int v, int e, int r) {

		int wert = block.getWert();

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.lightGray);

		g.setFont(new Font("Arial", Font.BOLD, schrift)); // schriftgroesse der zahl im feld

		g2.fillRoundRect(x, y, breite, breite, 10, 10); // graue, leere Felder

		g2.setColor(Color.black);
		if (wert > 0) { // je nach Wert wird das Feld gefaerbt
			g2.setColor(block.getFarbe());

			g2.fillRoundRect(x, y, breite, breite, 10, 10); // graue, leere Felder

			g2.setColor(Color.darkGray);

			if (wert < 8) {

				g.drawString("" + wert, x + schriftX, y + schriftY);

			}

			else if (wert < 16) {

				g.setColor(Color.white);
				g.drawString("" + wert, x + schriftX, y + schriftY);

			} else if (wert < 128) {

				g.setColor(Color.white);
				g.drawString("" + wert, x + schriftX - 22 + r, y + schriftY);

			} else if (wert < 1024) {

				g.setColor(Color.white);
				g.setFont(new Font("Arial", Font.BOLD, schrift - 10));
				g.drawString("" + wert, x + schriftX - 37 + v, y + schriftY);

			} else if (wert < 10000) {

				g.setColor(Color.white);
				g.setFont(new Font("Arial", Font.BOLD, schrift - 20));
				g.drawString("" + wert, x + schriftX - 45 + e, y + schriftY);

			}

		}

	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyChar() == 'w' && !statsAktiv || e.getKeyCode() == KeyEvent.VK_UP && !statsAktiv) {

			a.s.welcheRichtung("oben");
			centerPanel.repaint();
			
		} else if (e.getKeyChar() == 's' && !statsAktiv || e.getKeyCode() == KeyEvent.VK_DOWN && !statsAktiv) {

			a.s.welcheRichtung("unten");
			centerPanel.repaint();

		} else if (e.getKeyChar() == 'a' && !statsAktiv || e.getKeyCode() == KeyEvent.VK_LEFT && !statsAktiv) {

			a.s.welcheRichtung("links");
			centerPanel.repaint();

		} else if (e.getKeyChar() == 'd' && !statsAktiv || e.getKeyCode() == KeyEvent.VK_RIGHT && !statsAktiv) {

			a.s.welcheRichtung("rechts");
			centerPanel.repaint();

		} else if (e.getKeyChar() == 'z' && !statsAktiv) {

			Autoplay au = new Autoplay();
			a.s.welcheRichtung(au.zufaelligeRichtung());
			gameFrame.repaint();

		} else if (e.getKeyChar() == 'r' && !statsAktiv) {

			Autoplay au = new Autoplay();
			System.out.println("Autoplay: " + au.naechsterZug(a.s));
			a.s.welcheRichtung(au.naechsterZug(a.s));
			gameFrame.repaint();

		} else if (e.getKeyCode() == KeyEvent.VK_ENTER && a.s.gameOver()) {

			
			newGame();

		}
		a.st.update();
		
		rekord.setText("<html>Rekord <br>" + a.st.getRekord() + "</html>");
		punkte.setText("<html>Punkte <br>" + a.s.punkte + "</html>");
		hilfe.setVisible(false);
	}

	public void actionPerformed(ActionEvent e) {

	}

	public void keyReleased(KeyEvent e) { // macht nichts, muss aber da sein
	}

	public void keyTyped(KeyEvent e) { // macht nichts, muss aber da sein
	}

	public static void newGame() {
		rekord.setText("<html>Rekord <br>" + a.st.getRekord() + "</html>"); //unnoetig, weil live update vom Rekord
		a.st.updateEnde();
		a.s = new Spielfeld(a.s.breite);
		a.st.updateSpielfeld(a.s);
		a.s.blockErstellen();
		a.s.blockErstellen();
		punkte.setText("<html>Punkte <br>" + a.s.punkte + "</html>");
		centerPanel.repaint();
	}
	
	// GUI und zwei bloecke werden erstellt

	public static void main(String[] args) {

		Login.loginGui();
		a.s.blockErstellen();
		a.s.blockErstellen();
	}

}
