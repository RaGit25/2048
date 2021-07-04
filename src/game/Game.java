package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Game extends JPanel implements KeyListener, ActionListener {

	private static final long serialVersionUID = 1L;

	static Account a;
	static Autoplay auto;
	
	static Boolean statsAktiv;

	static Game game = new Game();
	
	static JFrame gameFrame;

	static JPanel centerPanel;
	static JPanel panel1;
	static JPanel panel2;
	static JPanel panel3;
	static JPanel panel4;

	static LineBorder border;
	static LineBorder border1;
	static LineBorder border2;

	static JLabel punkte;
	static JLabel rekord;
	static JLabel hilfe;

	static JLabel titel;

	static JButton restart;
	static JButton zurueck;
	static JButton tipp;
	static JButton stats;

	static JButton exit;

	public Game() {

		a = null;
		auto = new Autoplay();
		
		statsAktiv = false;
		
		setPreferredSize(new Dimension(850, 1000)); // macht das Spielfeld im Panel "centerPanel" sichtbar

		gameFrame = new JFrame();
		centerPanel = new JPanel(); // Mehrere Panels benoetigt (fuer den Layoutmanager)
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		border = new LineBorder(new Color(237, 194, 46), 2, true);
		border1 = new LineBorder(new Color(236, 228, 219), 2, true);
		border2 = new LineBorder(Color.gray, 2, true);
		punkte = new JLabel();
		rekord = new JLabel();
		hilfe = new JLabel();
		titel = new JLabel();
		restart = new JButton(); // restart Button oben links im Spiel
		zurueck = new JButton();
		tipp = new JButton();
		stats = new JButton();
		exit = new JButton();
		
		gameFrame.setLayout(new BorderLayout()); // BorderLayout = 1 panel in der Mitte und 4 aussenrum

		gameFrame.add(centerPanel, BorderLayout.CENTER);
		gameFrame.add(panel1, BorderLayout.NORTH);
		gameFrame.add(panel2, BorderLayout.EAST);
		gameFrame.add(panel3, BorderLayout.WEST);
		gameFrame.add(panel4, BorderLayout.SOUTH);

		panel1.add(punkte);
		panel1.add(rekord);
		panel1.add(titel);
		panel1.add(restart);
		panel1.add(zurueck);
		panel1.add(tipp);
		panel1.add(hilfe);
		panel1.add(stats);
		panel4.add(exit);
		centerPanel.add(this); // sichtbarmachen der Felder

	}

	public static void setAccount(Account n) {
		Game.a = n;
		a.st.updateSpielfeld(a.s);
		gameFrame.setTitle("2048 - "+a.getName());
	}

	public static void gameGui() { // das Spielfenster

	
		centerPanel.setBackground(Color.gray);
		centerPanel.setPreferredSize(new Dimension(100, 100)); // groesssse d. panels in d. mitte

		// festlegen d. groessen d. anderen panels
		panel1.setPreferredSize(new Dimension(100, 180));
		panel4.setPreferredSize(new Dimension(100, 60));
		panel2.setPreferredSize(new Dimension(30, 30));
		panel3.setPreferredSize(new Dimension(30, 30));

		// punktzahl
		punkte.setText("<html>Punkte <br>" + a.s.getPunkte() + "</html>");
		punkte.setForeground(Color.white);
		panel1.setLayout(null);
		punkte.setBounds(425, 25, 150, 80);
		punkte.setFont(new Font("Arial", Font.BOLD, 30));
		punkte.setBorder(border);
		punkte.setVerticalAlignment(JLabel.TOP);
		punkte.setHorizontalAlignment(JLabel.CENTER);
		punkte.setBackground(new Color(237, 194, 46));
		punkte.setOpaque(true);

		// rekord
		rekord.setText("<html>Rekord <br>" + a.st.setRekord() + "</html>");
		rekord.setForeground(Color.white);
		rekord.setLayout(null);
		rekord.setBounds(600, 25, 150, 80);
		rekord.setFont(new Font("Arial", Font.BOLD, 30));
		rekord.setBorder(border);
		rekord.setVerticalAlignment(JLabel.TOP);
		rekord.setHorizontalAlignment(JLabel.CENTER);
		rekord.setBackground(new Color(237, 194, 46));
		rekord.setOpaque(true);

		// Titel
		titel.setText("2048");
		titel.setBounds(35, 25, 210, 80);
		titel.setFont(new Font("Arial", Font.BOLD, 70));
		titel.setBackground(new Color(237, 194, 46));
		titel.setLayout(null);
		titel.setBorder(border);
		titel.setVerticalAlignment(JLabel.TOP);
		titel.setHorizontalAlignment(JLabel.CENTER);
		titel.setForeground(Color.white);
		titel.setOpaque(true);

		// restart button
		restart.setText("Neues Spiel");
		restart.setFont(new Font("Arial", Font.BOLD, 23));
		restart.setBounds(600, 120, 150, 50);
		restart.setBorder(border1);
		restart.setFocusable(false);
		restart.setBackground(new Color(236, 228, 219));
		for( ActionListener a : restart.getActionListeners() ) {	//Entfernt alle Actionlistner
	        restart.removeActionListener( a );
	    }
		restart.addActionListener((ActionEvent e) -> { // wenn man auf den button drueckt

				neuesSpiel();
				labelNeuladen();

		});

		// zurueck button
		zurueck.setBounds(425, 120, 100, 50);
		zurueck.setText("Zurueck");
		zurueck.setFont(new Font("Arial", Font.BOLD, 22));
		zurueck.setBorder(border1);
		zurueck.setFocusable(false);
		zurueck.setBackground(new Color(236, 228, 219));
		for( ActionListener a : zurueck.getActionListeners() ) {	//Entfernt alle Actionlistner
	        zurueck.removeActionListener( a );
	    }
		zurueck.addActionListener((ActionEvent e) -> { // wenn man auf den button drueckt

			if (!a.s.gameOver()) {

				a.zuruecknehmen();
				centerPanel.repaint();
				labelNeuladen();

			}

		});

		// tipp button
		tipp.setBounds(528, 120, 50, 50);
		tipp.setText("?");
		tipp.setFont(new Font("Arial", Font.BOLD, 35));
		tipp.setBorder(BorderFactory.createEtchedBorder());
		tipp.setFocusable(false);
		tipp.setBackground(new Color(236, 228, 219));
		tipp.setBorder(border1);
		hilfe.setBounds(260, 100, 200, 100);
		hilfe.setFont(new Font("Arial", Font.PLAIN, 30));
		hilfe.setForeground(Color.green);
		hilfe.setVisible(false);
		for( ActionListener a : tipp.getActionListeners() ) {	//Entfernt alle Actionlistner
	        tipp.removeActionListener( a );
	    }
		tipp.addActionListener((ActionEvent e) -> { // wenn man auf den button drueckt

			if (!a.s.gameOver()) {

				String tipp = auto.naechsterZug(a.s);
				hilfe.setText("Tipp: " + tipp);
				hilfe.setVisible(true);

			}

		});

		// stats button
		stats.setBounds(35, 120, 210, 50);
		stats.setBorder(BorderFactory.createEtchedBorder());
		stats.setFocusable(false);
		stats.setBackground(new Color(236, 228, 219));
		stats.setBorder(border1);
		stats.setText("Statistiken");
		stats.setFont(new Font("Arial", Font.BOLD, 25));
		for( ActionListener a : stats.getActionListeners() ) {	//Entfernt alle Actionlistner
	        stats.removeActionListener( a );
	    }
		stats.addActionListener((ActionEvent e) -> {
			if (!statsAktiv) {

				restart.setVisible(false);
				zurueck.setVisible(false);
				tipp.setVisible(false);
				stats.setFont(new Font("Arial", Font.BOLD, 22));
				stats.setText("Zurueck zum Spiel");
				statsAktiv = true;

			} else {

				restart.setVisible(true);
				zurueck.setVisible(true);
				tipp.setVisible(true);
				stats.setFont(new Font("Arial", Font.BOLD, 25));
				stats.setText("Statistiken");
				statsAktiv = false;

			}

			centerPanel.repaint();

		});

		// exit button
		panel4.setLayout(null);
		exit.setFont(new Font("Arial", Font.BOLD, 15));
		exit.setText("Zum Login");
		exit.setBounds(35, 18, 100, 30);
		exit.setBackground(new Color(236, 228, 219));
		exit.setBorder(border1);
		exit.setFocusable(false);
		for( ActionListener a : exit.getActionListeners() ) {	//Entfernt alle Actionlistner
	        exit.removeActionListener( a );
	    }
		exit.addActionListener((ActionEvent e) -> { // wenn man auf den button drueckt

			statsAktiv = false; 
			gameFrame.dispose();
			Login.l.loginGui(); // Static mit Klassennamen aufrufen

		});

		gameFrame.setSize(800, 1000);
		gameFrame.setVisible(true);
		gameFrame.setLocationRelativeTo(null); // wird in der MItte d. Bildschirms geoeffnet
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fenster schliesst sich und code wird beendet wenn
																	// man auf X das drueckt
		gameFrame.setResizable(false); // fenstergroesse nicht veraenderbar
		gameFrame.removeKeyListener(game);
		gameFrame.addKeyListener(game);

	}

	// paint wird bei jedem Zug mit repaint() aufgerufen.
	// erstellt im Prinzip alles, was der Spieler sieht auf zwei Methoden aufgeteilt

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.gray);
		g.setColor(Color.black);

		if (statsAktiv) {

			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.drawString("Statistiken", 80, 40);
			g.setFont(new Font("Arial", Font.PLAIN, 30));
			g.setColor(Color.white);

			g.drawString("Anzahl aller gesammelten Punkte:", 70, 120);
			g.drawString(" " + a.st.getPunkteGesamt(), 550, 120);

			g.drawString("Hoechstes erreichtes Feld:", 70, 160);

			if (a.st.getFeldHoch() == 2048) {

				g.setColor(new Color(237, 194, 46));

			} else if (a.st.getFeldHoch() == 4096) {

				g.setColor(new Color(224, 110, 112));

			} else if (a.st.getFeldHoch() == 8192) {

				g.setColor(new Color(220, 88, 94));

			} else if (a.st.getFeldHoch() >= 16384) {

				g.setColor(new Color(225, 79, 73));

			}

			g.drawString(" " + a.st.getFeldHoch(), 550, 160);

			g.setColor(Color.white);

			g.drawString("Anzahl aller gespielten Runden:", 70, 200);
			g.drawString(" " + a.st.getRunden(), 550, 200);

			g.drawString("Anzahl aller Runden mit 2048:", 70, 240);
			g.drawString(" " + a.st.getGewonnen(), 550, 240);

			g.drawString("Anteil der Runden mit 2048:", 70, 280);

			if (a.st.getwinLoseRatio() == 1.0) {
				g.setColor(new Color(237, 194, 46));
			}

			DecimalFormat df = new DecimalFormat("#.##");
			g.drawString(" " + df.format(a.st.getwinLoseRatio() * 100) + "%", 550, 280);

			g.setColor(Color.white);

			g.drawString("Durchschnittliche Punktzahl:", 70, 320);
			g.drawString(" " + a.st.getDurchschnittsPunkte(), 550, 320);

			g.drawString("Anzahl der ausgefuehrten Zuege:", 70, 360);
			g.drawString(" " + a.s.getZuege(), 550, 360);

			g.drawString("Anzahl aller ausgefuehrten Zuege:", 70, 400);
			g.drawString(" " + a.st.getZuegeGesamt(), 550, 400);

		} else {

			int breite = 0;
			int schrift = 0;
			int abstand = 0;
			int schriftX = 0;
			int schriftY = 0;
			int r = 0;
			int v = 0;
			int e = 0;
			int schriftAenderung = 0;

			if (a.s.getBreite() == 3) {

				breite = 220;
				schrift = 120;
				abstand = 230;
				schriftX = 75;
				schriftY = 150;
				r = -15;
				v = -20;
				e = -35;

			} else if (a.s.getBreite() == 4) {

				breite = 155;
				schrift = 80;
				abstand = 175;
				schriftX = 55;
				schriftY = 105;

			} else if (a.s.getBreite() == 5) {

				breite = 120;
				schrift = 70;
				abstand = 140;
				schriftX = 41;
				schriftY = 82;
				v = 5;
				e = 7;

			} else if (a.s.getBreite() == 6) {

				breite = 95;
				schrift = 60;
				abstand = 117;
				schriftX = 31;
				schriftY = 70;
				v = 10;
				e = 17;
				r = 4;

			} else if (a.s.getBreite() == 7) {

				breite = 86;
				schrift = 45;
				abstand = 99;
				schriftX = 28;
				schriftY = 58;
				v = 20;
				e = 32;
				r = 10;

			} else if (a.s.getBreite() == 8) {

				breite = 72;
				schrift = 35;
				abstand = 87;
				schriftX = 26;
				schriftY = 52;
				v = 27;
				e = 27;
				r = 10;
				schriftAenderung = 10;

			}

			for (int i = 0; i < a.s.getBreite(); i++) { // geht bei jedem Zug einmal durchs Feld druch und ruft felder()
														// auf
				for (int j = 0; j < a.s.getBreite(); j++) {

					felder(g, a.s.getFeld()[i][j], j * abstand + 85, i * abstand + 15, breite, schrift, schriftX,
							schriftY, v, e, r, schriftAenderung);

				}

			}

			if (a.s.gameOver()) { // wenn man verloren hat

				g.setColor(Color.black);
				g.setFont(new Font("Arial", Font.BOLD, 40));
				g.drawString("Game Over!", 300, 340);
				g.setFont(new Font("Arial", Font.BOLD, 30));
				g.drawString("Druecke 'enter' um neu zu starten.", 200, 390);
				labelNeuladen();

				// Startet neue Runden bis Spielende
			 	//if (!a.s.gewonnen()) { neuesSpiel(); }

			}

		}
	}

	// kuemmert sich um das Faerben der Felder

	public void felder(Graphics g, Block block, int x, int y, int breite, int schrift, int schriftX, int schriftY,
			int v, int e, int r, int s) {

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
				g.setFont(new Font("Arial", Font.BOLD, schrift - 20 + s));
				g.drawString("" + wert, x + schriftX - 45 + e, y + schriftY);

			}

		}

	}

	public void keyPressed(KeyEvent e) {

		if ((e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) && !statsAktiv) {

			if (a.s.verschiebbar("oben")) {
				a.klonen();
				a.s.welcheRichtung("oben");
				centerPanel.repaint();
			}

		} else if ((e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) && !statsAktiv) {

			if (a.s.verschiebbar("unten")) {
				a.klonen();
				a.s.welcheRichtung("unten");
				centerPanel.repaint();
			}

		} else if ((e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT) && !statsAktiv) {

			if (a.s.verschiebbar("links")) {
				a.klonen();
				a.s.welcheRichtung("links");
				centerPanel.repaint();
			}

		} else if ((e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) && !statsAktiv) {

			if (a.s.verschiebbar("rechts")) {
				a.klonen();
				a.s.welcheRichtung("rechts");
				centerPanel.repaint();
			}

		} else if (e.getKeyChar() == 'z' && !statsAktiv) {

			a.klonen();
			a.s.welcheRichtung(auto.zufaelligeRichtung());
			gameFrame.repaint();

		} else if (e.getKeyChar() == 'r' && !statsAktiv) {

			a.klonen();
			a.s.welcheRichtung(auto.naechsterZug(a.s));
			gameFrame.repaint();

		} else if (e.getKeyChar() == 'c' && !statsAktiv) {

			a.klonen();
			a.s.welcheRichtung(auto.muster(a.s));
			gameFrame.repaint();

		} else if (e.getKeyChar() == 'l' && !statsAktiv) { // load

			setAccount(JSONVerwalter.laden("test")); // Neuladen des TestAccounts
			gameFrame.repaint();

		} else if (e.getKeyCode() == KeyEvent.VK_ENTER && a.s.gameOver()) {

			neuesSpiel();

		}

		if(a.s.getVeraendert()) {		//Nur bei Veraenderung soll sich was aendern
			a.st.update();
			punkte.setText("<html>Punkte <br>" + a.s.getPunkte() + "</html>"); // Updaten der Punktzahl
			rekord.setText("<html>Rekord <br>" + a.st.setRekord() + "</html>"); // Updaten des Rekords
			JSONVerwalter.speichern(a); // Speichern der Json
			labelNeuladen();	
		}
		labelNeuladen();
		hilfe.setVisible(false);
		
	}

	public void actionPerformed(ActionEvent e) {	// macht nichts, muss aber da sein
	}

	public void keyReleased(KeyEvent e) { // macht nichts, muss aber da sein
	}

	public void keyTyped(KeyEvent e) { // macht nichts, muss aber da sein
	}

	public static void neuesSpiel() {

		a.st.updateEnde();
		a.s = new Spielfeld(a.s.getBreite());
		a.s.blockErstellen();
		a.s.blockErstellen();
		a.klonen();
		JSONVerwalter.speichern(a);
		centerPanel.repaint();
		labelNeuladen();
	}

	public static void labelNeuladen() {
		
		a.st.updateSpielfeld(a.s);	//Sicherstellen
		punkte.setText("<html>Punkte <br>" + a.s.getPunkte() + "</html>"); // Updaten der Punktzahl
		rekord.setText("<html>Rekord <br>" + a.st.setRekord() + "</html>"); // Updaten des Rekords

		if (a.s.hoechstesFeld == 2048) {

			titel.setForeground(new Color(252, 130, 20));

		} else if (a.s.hoechstesFeld == 4096) {

			titel.setForeground(new Color(224, 110, 112));

		} else if (a.s.hoechstesFeld == 8192) {

			titel.setForeground(new Color(220, 88, 94));

		} else if (a.s.hoechstesFeld >= 16384) {

			titel.setForeground(new Color(225, 79, 73));

		} else {

			titel.setForeground(Color.white);

		}

		if (a.s.equals(a.klon)) {

			zurueck.setBackground(Color.gray);
			zurueck.setBorder(border2);

		} else {

			zurueck.setBackground(new Color(236, 228, 219));
			zurueck.setBorder(border1);

		}

		/*----------nur fuer ein 3x3 Feld----------*/

		if (a.s.getBreite() == 3 && a.s.getHoechstesFeld() == 1024) {

			titel.setForeground(Color.red);
			panel1.setBackground(new Color(237, 204, 97));
			panel2.setBackground(new Color(237, 204, 97));
			panel3.setBackground(new Color(237, 204, 97));
			panel4.setBackground(new Color(237, 204, 97));

		}

	}
}
