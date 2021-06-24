package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.View;

public class Game extends JPanel implements KeyListener, ActionListener {

	static Spielfeld s = new Spielfeld(4);

	static Game game = new Game();

	static JFrame gameFrame = new JFrame("2048");

	static JFrame loginFrame = new JFrame("login");

	static JTextField textFeld = new JTextField("namen eingeben");

	static JButton loginButton = new JButton("einloggen");
	static JButton plusButton = new JButton("+");
	static JButton confirmButton = new JButton("bestaetigen");

	static JPanel PanelfuerAccounts = new JPanel();
	static JPanel PanelfuerKontoerstellung = new JPanel();

	static String accountString[] = { "Konto auswählen", "aaaaaaaaa", "bbbbbbbb" };
	static JComboBox<Object> accountAuswahlliste = new JComboBox<Object>(Game.accountString);

	static JPanel centerPanel = new JPanel(); // Mehrere Panels benoetigt (für den Layoutmanager)
	static JPanel panel1 = new JPanel();
	static JPanel panel2 = new JPanel();
	static JPanel panel3 = new JPanel();
	static JPanel panel4 = new JPanel();

	static JLabel punkte = new JLabel(); // Punktzahl ist jetzt in einem JLabel in "panel1"
	static JLabel rekord = new JLabel();

	static JLabel titel = new JLabel();

	static JButton restart = new JButton(); // restart Button oben links im Spiel

	Game() {

		setPreferredSize(new Dimension(850, 1000)); // macht das Spielfeld im Panel "centerPanel" sichtbar

	}

	// Fenster, das zu Beginn geoeffnet wird
	public static void loginGui() {

		loginFrame.setSize(600, 200);
		loginFrame.setLocationRelativeTo(null); // wird in der MItte d. Bildschirms geoeffnet
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fenster schliesst sich und code wird beendet wenn
																	// man auf X das drueckt
		loginFrame.setResizable(false); // fenstergroesse nicht veraenderbar

		GridBagLayout layout = new GridBagLayout(); // Layoutmanager
		GridBagConstraints gbc = new GridBagConstraints(); //
		loginFrame.setLayout(layout); //

		loginFrame.add(PanelfuerKontoerstellung); // fuer die Kontoerstellung benoetigten Elemente
		PanelfuerKontoerstellung.add(textFeld);
		PanelfuerKontoerstellung.add(confirmButton);
		PanelfuerKontoerstellung.setVisible(false); // unsichtbar gemacht da hier noch nicht gebraucht

		gbc.gridy = 1; // relative Koordinaten im Layoutmanager
		gbc.gridx = 0;
		loginFrame.add(loginButton, gbc); // "einloggen" Knopf mit layoutmanager

		PanelfuerAccounts.add(accountAuswahlliste); // der Accountteil hat ein Panel damit der "einloggen" Knopf
													// zentriert ist
		PanelfuerAccounts.add(plusButton);
		gbc.gridy = 0; // relative Koordinaten im Layoutmanager
		gbc.gridx = 0;
		loginFrame.add(PanelfuerAccounts, gbc); // account Panel mit layoutmanager

		loginFrame.setVisible(true);

		loginButton.addActionListener((ActionEvent e) -> { // auf "einloggen" wird geclickt
			gameGui(); // das Spielfenster wird geoeffnet und das Spiel startet
			loginFrame.dispose(); // das Loginfenster schliesst sich
		});

		plusButton.addActionListener((ActionEvent e) -> { // auf "+" wird geclickt
			loginButton.setVisible(false); // Elemente vom login werden unsichtbar gemacht
			plusButton.setVisible(false);
			accountAuswahlliste.setVisible(false);

			PanelfuerKontoerstellung.setVisible(true); // Elemente von der Kontoerstellung werden sichtbar gemacht
			textFeld.setVisible(true);
			confirmButton.setVisible(true);
		});

		confirmButton.addActionListener((ActionEvent e) -> { // auf "bestaetigen" wird geclickt
			loginButton.setVisible(true);
			; // Elemente vom login werden wieder sichtbar gemacht
			plusButton.setVisible(true);
			accountAuswahlliste.setVisible(true);

			PanelfuerKontoerstellung.setVisible(false); // Elemente von der Kontoerstellung werden unsichtbar gemacht
			textFeld.setVisible(false);

		});

	}

	public static void gameGui() { // das Spielfenster

		gameFrame.setLayout(new BorderLayout()); // BorderLayout = 1 panel in der Mitte und 4 außenrum

		centerPanel.setBackground(Color.gray);
		centerPanel.setPreferredSize(new Dimension(100, 100)); // grösse d. panels in d. mitte
		centerPanel.add(new Game()); // sichtbarmachen der Felder

		// punktzahl
		punkte.setText("<html>Punkte <br>" + s.punkte + "</html>");
		Border border = BorderFactory.createLineBorder(Color.gray, 5);
		panel1.add(punkte);
		panel1.setLayout(null);
		punkte.setBounds(440, 15, 150, 80);
		punkte.setFont(new Font("Arial", Font.PLAIN, 30));
		punkte.setBorder(border);
		punkte.setVerticalAlignment(JLabel.TOP);
		punkte.setHorizontalAlignment(JLabel.CENTER);
		punkte.setBackground(Color.lightGray);
		punkte.setOpaque(true);

		// rekord

		rekord.setText("<html>Rekord <br>" + 0 + "</html>"); // rekord fehlt noch, weil keine Speicherung
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
		restart.setBounds(680, 120, 50, 50);
		ImageIcon icon = new ImageIcon("restart.png"); // bild mit den 2 pfeilen, siehe Dateien d. Projekts
		restart.setIcon(icon);
		restart.setBorder(BorderFactory.createEtchedBorder());
		restart.setFocusable(false);
		restart.setBackground(Color.lightGray);
		restart.addActionListener((ActionEvent e) -> { // wenn man auf den button drueckt

			s = new Spielfeld(4);
			s.blockErstellen();
			s.blockErstellen();
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
		g.setFont(new Font("Arial", Font.BOLD, 80)); // schriftgroesse der zahl im feld

		for (int i = 0; i < s.breite; i++) { // geht bei jedem Zug einmal durchs Feld druch und ruft felder() auf
			for (int j = 0; j < s.breite; j++) {

				felder(g, s.feld[i][j], j * 175 + 85, i * 175 + 15);

			}

		}

		if (s.gameOver()) { // wenn man verloren hat

			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.PLAIN, 40));
			g.drawString("Game Over!", 300, 400);
			g.setFont(new Font("Arial", Font.PLAIN, 30));
			g.drawString("Druecke 'enter' um neu zu starten.", 200, 450);

		}

	}

	// kuemmert sich um das Faerben der Felder

	public void felder(Graphics g, Block block, int x, int y) {

		int wert = block.getWert();

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.lightGray);
		g2.fillRoundRect(x, y, 155, 155, 10, 10); // graue, leere Felder
		g2.setColor(Color.black);
		if (wert > 0) { // je nach Wert wird das Feld gefaerbt
			g2.setColor(block.getFarbe());
			g2.fillRoundRect(x, y, 155, 155, 10, 10);
			g2.setColor(Color.darkGray);

			if (wert < 8) {

				g.drawString("" + wert, x + 55, y + 105);

			}

			else if (wert < 16) {

				g.setColor(Color.white);
				g.drawString("" + wert, x + 55, y + 105);

			} else if (wert < 128) {

				g.setColor(Color.white);
				g.drawString("" + wert, x + 32, y + 105);

			} else if (wert < 1024) {

				g.setColor(Color.white);
				g.setFont(new Font("Arial", Font.BOLD, 70));
				g.drawString("" + wert, x + 18, y + 105);

			} else if (wert < 10000) {

				g.setColor(Color.white);
				g.setFont(new Font("Arial", Font.BOLD, 60));
				g.drawString("" + wert, x + 10, y + 105);

			}

		}

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) {

			s.welcheRichtung("oben");
			centerPanel.repaint();

		} else if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) {

			s.welcheRichtung("unten");
			centerPanel.repaint();

		} else if (e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT) {

			s.welcheRichtung("links");
			centerPanel.repaint();

		} else if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) {

			s.welcheRichtung("rechts");
			centerPanel.repaint();

		} else if (e.getKeyCode() == KeyEvent.VK_ENTER && s.gameOver()) {

			s = new Spielfeld(4);
			s.blockErstellen();
			s.blockErstellen();
			centerPanel.repaint();

		}
		punkte.setText("<html>Punkte <br>" + s.punkte + "</html>");
	}

	public void actionPerformed(ActionEvent e) {
	}

	public void keyReleased(KeyEvent e) { // macht nichts, muss aber da sein
	}

	public void keyTyped(KeyEvent e) { // macht nichts, muss aber da sein
	}

	// GUI und zwei bloecke werden erstellt

	public static void main(String[] args) {

		loginGui();
		s.blockErstellen();
		s.blockErstellen();
	}

}
