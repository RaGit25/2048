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

	static Account a;               // Objekt der Klasse Account
	static Autoplay auto;           // Objekt der Klasse Autoplay
	 
	static Boolean statsAktiv;      // Benoetigt fuer den Statistiken Button, siehe ActionListener stats und paintComponent()
	static Boolean clrAktiv;      // Benoetigt fuer den Design Button, siehe ActionListener clrLayout und paintComponent()

	static Color[] clr1;	//Feld mit Farben
	static Color[] clr2;	//Feld mit Farben
	static Color[] clr3;	//Feld mit Farben
	static Color[] clr4;	//Feld mit Farben
	static Color[] clr5;	//Feld mit Farben
	static Color[] clr6;	//Feld mit Farben
	static Color[] clr7;	//Feld mit Farben
	static int clrIndex;	//einheitlicher Index für Color Arrays

	static JButton clrButton1;
	static JButton clrButton2;
	static JButton clrButton3;
	static JButton clrButton4;
	static JButton clrButton5;

	static ImageIcon TEST;

	static Game game = new Game();  // Objekt der Klasse Game, u.a. fuer den KeyListener benoetigt
	
	static JFrame gameFrame;        // Spielfenster

	
	/* Die vier JPanels, bzw. "Teile" des Spielfensters
	 * 
	 * centerPanel = Beinhaltet das Spielfeld und ist dunkelgrau gefaerbt, sozusagen der Hintergrund
	 * 
	 * panel1 = Panel ueber dem Spielfeld
	 * panel2/3 = Panel links/rechts neben dem Spielfeld
	 * panel4 = Panel unter dem Spielfeld
	 * 
	 */
	
	static JPanel centerPanel;
	static JPanel panel1;
	static JPanel panel2;
	static JPanel panel3;
	static JPanel panel4;

	//static LineBorder border;    // Rand der Punkte-, Rekordanzeige
	static LineBorder border1[];     // Rand der roten Buttons
	static LineBorder border2;     // Rand fuer den Zurueckbutton, wenn er grau ist
	static LineBorder border3[];     // Rand fuer schwarze Buttons
	static LineBorder border4[];	   // Rand fuer hellgraue Buttons

	static JLabel punkte;          // Punktzahl oben rechts
	static JLabel rekord;          // Rekord oben rechts
	static JLabel hilfe;           // Wird angezeigt, wenn man auf den Tippbutton drueckt
	static JLabel titel;           // Titel oben links

	static JButton restart;        // Button "Neues Spiel"
	static JButton zurueck;        // Button "Zurueck"
	static JButton tipp;           // Button "?" , aktiviert JLabel "hilfe"
	static JButton stats;          // Button "Statistiken"
	static JButton exit;           // Button "Zum Login"
	static JButton clrLayout;

	// Initialisieren aller Attribute, Objekte. Wird nur einmal aufgerufen
	
	public Game() {

		a = null;
		auto = new Autoplay(8,9,1.5,1.45);
		
		statsAktiv = false;
		clrAktiv = false;
		
		setPreferredSize(new Dimension(850, 1000)); // macht das Spielfeld im Panel "centerPanel" sichtbar

		gameFrame = new JFrame();
		centerPanel = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		//border = new LineBorder(new Color(230, 104, 73), 2, true);
		clrIndex = 0;

		clr1 = new Color[5];
		clr1[0] = new Color(231, 204, 112);
		clr1[1] = new Color(145,0,67);
		clr1[2] = new Color(180,240,215);
		clr1[3] = new Color(255,49,43);
		clr1[4] = new Color(224, 71, 71, 255);

		clr2 = new Color[5];
		clr2[0] = new Color(60, 58, 52);
		clr2[1] = new Color(97,37,87);
		clr2[2] = new Color(172,230,189);
		clr2[3] = new Color(204,92,82);
		clr2[4] = new Color(175, 219, 154);

		clr3 = new Color[5];
		clr3[0] = new Color(230, 104, 73);
		clr3[1] = new Color(172,121,172, 124);
		clr3[2] = new Color(172,206,230);
		clr3[3] = new Color(255,116,103);
		clr3[4] = new Color(141, 181, 134);

		clr4 = new Color[5];
		clr4[0] = new Color(189, 176, 163);
		clr4[1] = new Color(80,67,82);
		clr4[2] = new Color(175,218,212);
		clr4[3] = new Color(128,92,89);
		clr4[4] = new Color(107, 148, 104);

		clr5 = new Color[5];
		clr5[0] = new Color(163, 148, 132);
		clr5[1] = new Color(81, 63, 82, 148);
		clr5[2] = new Color(180,231,240);
		clr5[3] = new Color(128,57,51);
		clr5[4] = new Color(72, 102, 75);

		clr6 = new Color[5];
		clr6[0] = Color.white;
		clr6[1] = new Color(60, 58, 52);
		clr6[2] = Color.white;
		clr6[3] = new Color(255,185,179);
		clr6[4] = Color.white;

		clr7 = new Color[5];
		clr7[0] = new Color(224, 71, 71, 255);
		clr7[1] = new Color(180,240,215);
		clr7[2] = new Color(145, 87, 114);
		clr7[3] = new Color(255,185,179);
		clr7[4] = new Color(231, 204, 112);


		border1 = new LineBorder[5];
		border3 = new LineBorder[5];
		border4 = new LineBorder[5];

		border1[0] = new LineBorder(clr3[0], 2, true);
		border1[1] = new LineBorder(clr3[1], 2, true);
		border1[2] = new LineBorder(clr3[2], 2, true);
		border1[3] = new LineBorder(clr3[3], 2, true);
		border1[4] = new LineBorder(clr3[4], 2, true);

		border2 = new LineBorder(Color.gray, 2, true);

		border3[0] = new LineBorder(clr2[0], 2, true);
		border3[1] = new LineBorder(clr2[1], 2, true);
		border3[2] = new LineBorder(clr2[2], 2, true);
		border3[3] = new LineBorder(clr2[3], 2, true);
		border3[4] = new LineBorder(clr2[4], 2, true);

		border4[0] = new LineBorder(clr4[0], 2, true);
		border4[1] = new LineBorder(clr4[1], 2, true);
		border4[2] = new LineBorder(clr4[2], 2, true);
		border4[3] = new LineBorder(clr4[3], 2, true);
		border4[4] = new LineBorder(clr4[4], 2, true);


		punkte = new JLabel();
		rekord = new JLabel();
		hilfe = new JLabel();
		titel = new JLabel();
		restart = new JButton();
		zurueck = new JButton();
		tipp = new JButton();
		stats = new JButton();
		exit = new JButton();
		clrLayout = new JButton();
		clrButton1 = new JButton();
		clrButton2 = new JButton();
		clrButton3 = new JButton();
		clrButton4 = new JButton();
		clrButton5 = new JButton();
		
		// BorderLayout = 5 Panels, Positionen siehe oben
		
		gameFrame.setLayout(new BorderLayout());   

		gameFrame.add(centerPanel, BorderLayout.CENTER);
		gameFrame.add(panel1, BorderLayout.NORTH);
		gameFrame.add(panel2, BorderLayout.EAST);
		gameFrame.add(panel3, BorderLayout.WEST);
		gameFrame.add(panel4, BorderLayout.SOUTH);

		//Einmaliges Hinzufuegen aller Objekte zu den Panels
		
		panel1.add(punkte);
		panel1.add(rekord);
		panel1.add(titel);
		panel1.add(restart);
		panel1.add(zurueck);
		panel1.add(tipp);
		panel1.add(hilfe);
		panel1.add(stats);
		panel4.add(clrLayout);
		panel4.add(exit);
		centerPanel.add(this); // sichtbarmachen der Felder, added das Objekt Game
		panel1.setBackground(clr6[clrIndex]);
		panel2.setBackground(clr6[clrIndex]);
		panel3.setBackground(clr6[clrIndex]);
		panel4.setBackground(clr6[clrIndex]);
	}

	// Wird in Klasse Login verwendet
	
	public static void setAccount(Account n) {
		Game.a = n;
		a.st.updateSpielfeld(a.s);
		gameFrame.setTitle("2048 - "+a.getName());
	}

	public static void gameGui() { // das Spielfenster

	
		centerPanel.setBackground(clr5[clrIndex]);
		centerPanel.setPreferredSize(new Dimension(100, 100));

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
		//punkte.setBorder(border);
		punkte.setVerticalAlignment(JLabel.TOP);
		punkte.setHorizontalAlignment(JLabel.CENTER);
		punkte.setBackground(clr2[clrIndex]);
		punkte.setOpaque(true);

		// rekord
		
		rekord.setText("<html>Rekord <br>" + a.st.setRekord() + "</html>");
		rekord.setForeground(Color.white);
		rekord.setLayout(null);
		rekord.setBounds(600, 25, 150, 80);
		rekord.setFont(new Font("Arial", Font.BOLD, 30));
		//rekord.setBorder(border);
		rekord.setVerticalAlignment(JLabel.TOP);
		rekord.setHorizontalAlignment(JLabel.CENTER);
		rekord.setBackground(clr2[clrIndex]);
		rekord.setOpaque(true);

		// Titel
		
		titel.setText("2048");
		titel.setBounds(35, 25, 210, 80);
		titel.setFont(new Font("Arial", Font.BOLD, 70));
		titel.setBackground(clr1[clrIndex]);
		titel.setLayout(null);
		//titel.setBorder(border);
		titel.setVerticalAlignment(JLabel.TOP);
		titel.setHorizontalAlignment(JLabel.CENTER);
		titel.setForeground(Color.white);
		titel.setOpaque(true);

		// restart button
		
		restart.setText("Neues Spiel");
		restart.setFont(new Font("Arial", Font.BOLD, 23));
		restart.setBounds(600, 120, 150, 50);
		restart.setBorder(border1[clrIndex]);
		restart.setFocusable(false);
		restart.setBackground(clr3[clrIndex]);
		restart.setForeground(Color.white);
		restart.setOpaque(true);
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
		zurueck.setBorder(border1[clrIndex]);
		zurueck.setFocusable(false);
		zurueck.setBackground(clr3[clrIndex]);
		zurueck.setForeground(Color.white);
		zurueck.setOpaque(true);
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
		tipp.setFocusable(false);
		tipp.setBackground(clr3[clrIndex]);
		tipp.setForeground(Color.white);
		tipp.setOpaque(true);
		tipp.setBorder(border1[clrIndex]);
		hilfe.setBounds(260, 95, 200, 100);
		hilfe.setFont(new Font("Arial", Font.PLAIN, 30));
		hilfe.setForeground(new Color(115, 109, 102));
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
		stats.setFocusable(false);
		stats.setBackground(clr3[clrIndex]);
		stats.setForeground(Color.white);
		stats.setOpaque(true);
		stats.setBorder(border1[clrIndex]);
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
				clrLayout.setVisible(false);
				exit.setVisible(false);
				stats.setFont(new Font("Arial", Font.BOLD, 22));
				stats.setText("Zurueck zum Spiel");
				statsAktiv = true;

			} else {

				restart.setVisible(true);
				zurueck.setVisible(true);
				tipp.setVisible(true);
				clrLayout.setVisible(true);
				exit.setVisible(true);
				stats.setFont(new Font("Arial", Font.BOLD, 25));
				stats.setText("Statistiken");
				statsAktiv = false;

			}

			centerPanel.repaint();

		});
        //Standard Design-Button
		clrButton1.setText("Standard");
		clrButton1.setLayout(null);
		clrButton1.setFont(new Font("Arial", Font.BOLD, 40));
		clrButton1.setBounds(20, 70, 700, 100);
		clrButton1.setBorder(border4[clrIndex]);
		clrButton1.setVisible(false);
		clrButton1.setFocusable(false);
		clrButton1.setBackground(clr4[clrIndex]);
		clrButton1.setForeground(Color.white);
		clrButton1.setOpaque(true);


        //Darkmode Design-Button
		clrButton2.setText("Nachtmodus");
		clrButton2.setLayout(null);
		clrButton2.setFont(new Font("Arial", Font.BOLD, 40));
		clrButton2.setBounds(20, 200, 700, 100);
		clrButton2.setBorder(border4[clrIndex]);
		clrButton2.setVisible(false);
		clrButton2.setFocusable(false);
		clrButton2.setBackground(clr4[clrIndex]);
		clrButton2.setForeground(Color.white);
		clrButton2.setOpaque(true);


		// Design-Button 3
		clrButton3.setText("Arktisblau");
		clrButton3.setLayout(null);
		clrButton3.setFont(new Font("Arial", Font.BOLD, 40));
		clrButton3.setBounds(20, 330, 700, 100);
		clrButton3.setBorder(border4[clrIndex]);
		clrButton3.setVisible(false);
		clrButton3.setFocusable(false);
		clrButton3.setBackground(clr4[clrIndex]);
		clrButton3.setForeground(Color.white);
		clrButton3.setOpaque(true);

// Design-Button 4
		clrButton4.setText("Erdbeere");
		clrButton4.setLayout(null);
		clrButton4.setFont(new Font("Arial", Font.BOLD, 40));
		clrButton4.setBounds(20, 460, 700, 100);
		clrButton4.setBorder(border4[clrIndex]);
		clrButton4.setVisible(false);
		clrButton4.setFocusable(false);
		clrButton4.setBackground(clr4[clrIndex]);
		clrButton4.setForeground(Color.white);
		clrButton4.setOpaque(true);

		// Design-Button 5
		clrButton5.setText("Melone");
		clrButton5.setLayout(null);
		clrButton5.setFont(new Font("Arial", Font.BOLD, 40));
		clrButton5.setBounds(20, 590, 700, 100);
		clrButton5.setBorder(border4[clrIndex]);
		clrButton5.setVisible(false);
		clrButton5.setFocusable(false);
		clrButton5.setBackground(clr4[clrIndex]);
		clrButton5.setForeground(Color.white);
		clrButton5.setOpaque(true);

		// Design button

		clrLayout.setBounds(575, 18, 175, 30);
		clrLayout.setFocusable(false);
		clrLayout.setBackground(clr2[clrIndex]);
		clrLayout.setForeground(Color.white);
		clrLayout.setOpaque(true);
		clrLayout.setBorder(border3[clrIndex]);
		clrLayout.setText("Design");
		clrLayout.setFont(new Font("Arial", Font.BOLD, 15));
		for( ActionListener a : clrLayout.getActionListeners() ) {	//Entfernt alle Actionlistner
			clrLayout.removeActionListener( a );
		}
		clrLayout.addActionListener((ActionEvent e) -> {
			if (!clrAktiv) {

				centerPanel.setLayout(null);
				centerPanel.repaint();
				restart.setVisible(false);
				zurueck.setVisible(false);
				tipp.setVisible(false);
				stats.setVisible(false);
				clrButton1.setVisible(true);
				clrButton2.setVisible(true);
				clrButton3.setVisible(true);
				clrButton4.setVisible(true);
				clrButton5.setVisible(true);
				clrLayout.setFont(new Font("Arial", Font.BOLD, 15));
				clrLayout.setText("Zurueck zum Spiel");
				clrAktiv = true;
				centerPanel.add(clrButton1);
				centerPanel.add(clrButton2);
				centerPanel.add(clrButton3);
				centerPanel.add(clrButton4);
				centerPanel.add(clrButton5);

				for( ActionListener a : clrButton5.getActionListeners() ) {	//Entfernt alle Actionlistner
					clrButton5.removeActionListener( a );
				}

				clrButton5.addActionListener((ActionEvent E) -> { // wenn man auf den button drueckt

					clrIndex = 4;
					centerPanel.repaint();
					panel1.setBackground(clr6[clrIndex]);
					panel2.setBackground(clr6[clrIndex]);
					panel3.setBackground(clr6[clrIndex]);
					panel4.setBackground(clr6[clrIndex]);
					punkte.setBackground(clr2[clrIndex]);
					rekord.setBackground(clr2[clrIndex]);
					titel.setBackground(clr1[clrIndex]);
					restart.setBackground(clr3[clrIndex]);
					zurueck.setBackground(clr3[clrIndex]);
					tipp.setBackground(clr3[clrIndex]);
					hilfe.setBackground(clr3[clrIndex]);
					stats.setBackground(clr3[clrIndex]);
					exit.setBackground(clr2[clrIndex]);
					clrLayout.setBackground(clr2[clrIndex]);
					centerPanel.setBackground(clr5[clrIndex]);
					clrButton1.setBackground(clr4[clrIndex]);
					clrButton2.setBackground(clr4[clrIndex]);
					clrButton3.setBackground(clr4[clrIndex]);
					clrButton4.setBackground(clr4[clrIndex]);
					clrButton5.setBackground(clr4[clrIndex]);

					restart.setBorder(border1[clrIndex]);
					zurueck.setBorder(border1[clrIndex]);
					tipp.setBorder(border1[clrIndex]);
					stats.setBorder(border1[clrIndex]);
					exit.setBorder(border3[clrIndex]);
					clrLayout.setBorder(border3[clrIndex]);
					clrButton1.setBorder(border4[clrIndex]);
					clrButton2.setBorder(border4[clrIndex]);
					clrButton3.setBorder(border4[clrIndex]);
					clrButton4.setBorder(border4[clrIndex]);
					clrButton5.setBorder(border4[clrIndex]);
					labelNeuladen();
				});

				for( ActionListener a : clrButton4.getActionListeners() ) {	//Entfernt alle Actionlistner
					clrButton4.removeActionListener( a );
				}

				clrButton4.addActionListener((ActionEvent E) -> { // wenn man auf den button drueckt

					clrIndex = 3;
					centerPanel.repaint();
					panel1.setBackground(clr6[clrIndex]);
					panel2.setBackground(clr6[clrIndex]);
					panel3.setBackground(clr6[clrIndex]);
					panel4.setBackground(clr6[clrIndex]);
					punkte.setBackground(clr2[clrIndex]);
					rekord.setBackground(clr2[clrIndex]);
					titel.setBackground(clr1[clrIndex]);
					restart.setBackground(clr3[clrIndex]);
					zurueck.setBackground(clr3[clrIndex]);
					tipp.setBackground(clr3[clrIndex]);
					hilfe.setBackground(clr3[clrIndex]);
					stats.setBackground(clr3[clrIndex]);
					exit.setBackground(clr2[clrIndex]);
					clrLayout.setBackground(clr2[clrIndex]);
					centerPanel.setBackground(clr5[clrIndex]);
					clrButton1.setBackground(clr4[clrIndex]);
					clrButton2.setBackground(clr4[clrIndex]);
					clrButton3.setBackground(clr4[clrIndex]);
                    clrButton4.setBackground(clr4[clrIndex]);
					clrButton5.setBackground(clr4[clrIndex]);

					restart.setBorder(border1[clrIndex]);
					zurueck.setBorder(border1[clrIndex]);
					tipp.setBorder(border1[clrIndex]);
					stats.setBorder(border1[clrIndex]);
					exit.setBorder(border3[clrIndex]);
					clrLayout.setBorder(border3[clrIndex]);
					clrButton1.setBorder(border4[clrIndex]);
					clrButton2.setBorder(border4[clrIndex]);
					clrButton3.setBorder(border4[clrIndex]);
					clrButton4.setBorder(border4[clrIndex]);
					clrButton5.setBorder(border4[clrIndex]);
					labelNeuladen();
				});


				for( ActionListener a : clrButton3.getActionListeners() ) {	//Entfernt alle Actionlistner
					clrButton3.removeActionListener( a );
				}

				clrButton3.addActionListener((ActionEvent E) -> { // wenn man auf den button drueckt

					clrIndex = 2;
					centerPanel.repaint();
					panel1.setBackground(clr6[clrIndex]);
					panel2.setBackground(clr6[clrIndex]);
					panel3.setBackground(clr6[clrIndex]);
					panel4.setBackground(clr6[clrIndex]);
					punkte.setBackground(clr2[clrIndex]);
					rekord.setBackground(clr2[clrIndex]);
					titel.setBackground(clr1[clrIndex]);
					restart.setBackground(clr3[clrIndex]);
					zurueck.setBackground(clr3[clrIndex]);
					tipp.setBackground(clr3[clrIndex]);
					hilfe.setBackground(clr3[clrIndex]);
					stats.setBackground(clr3[clrIndex]);
					exit.setBackground(clr2[clrIndex]);
					clrLayout.setBackground(clr2[clrIndex]);
					centerPanel.setBackground(clr5[clrIndex]);
					clrButton1.setBackground(clr4[clrIndex]);
					clrButton2.setBackground(clr4[clrIndex]);
					clrButton3.setBackground(clr4[clrIndex]);
					clrButton4.setBackground(clr4[clrIndex]);
					clrButton5.setBackground(clr4[clrIndex]);

					restart.setBorder(border1[clrIndex]);
					zurueck.setBorder(border1[clrIndex]);
					tipp.setBorder(border1[clrIndex]);
					stats.setBorder(border1[clrIndex]);
					exit.setBorder(border3[clrIndex]);
					clrLayout.setBorder(border3[clrIndex]);
					clrButton1.setBorder(border4[clrIndex]);
					clrButton2.setBorder(border4[clrIndex]);
					clrButton3.setBorder(border4[clrIndex]);
					clrButton4.setBorder(border4[clrIndex]);
					clrButton5.setBorder(border4[clrIndex]);
					labelNeuladen();
				});

			for( ActionListener a : clrButton2.getActionListeners() ) {	//Entfernt alle Actionlistner
					clrButton2.removeActionListener( a );
				}

				clrButton2.addActionListener((ActionEvent E) -> { // wenn man auf den button drueckt

					clrIndex = 1;
					centerPanel.repaint();
					panel1.setBackground(clr6[clrIndex]);
					panel2.setBackground(clr6[clrIndex]);
					panel3.setBackground(clr6[clrIndex]);
					panel4.setBackground(clr6[clrIndex]);
					punkte.setBackground(clr2[clrIndex]);
					rekord.setBackground(clr2[clrIndex]);
					titel.setBackground(clr1[clrIndex]);
					restart.setBackground(clr3[clrIndex]);
					zurueck.setBackground(clr3[clrIndex]);
					tipp.setBackground(clr3[clrIndex]);
					hilfe.setBackground(clr3[clrIndex]);
					stats.setBackground(clr3[clrIndex]);
					exit.setBackground(clr2[clrIndex]);
					clrLayout.setBackground(clr2[clrIndex]);
					centerPanel.setBackground(clr5[clrIndex]);
					clrButton1.setBackground(clr4[clrIndex]);
					clrButton2.setBackground(clr4[clrIndex]);
					clrButton3.setBackground(clr4[clrIndex]);
					clrButton4.setBackground(clr4[clrIndex]);
					clrButton5.setBackground(clr4[clrIndex]);

					restart.setBorder(border1[clrIndex]);
					zurueck.setBorder(border1[clrIndex]);
					tipp.setBorder(border1[clrIndex]);
					stats.setBorder(border1[clrIndex]);
					exit.setBorder(border3[clrIndex]);
					clrLayout.setBorder(border3[clrIndex]);
					clrButton1.setBorder(border4[clrIndex]);
					clrButton2.setBorder(border4[clrIndex]);
					clrButton3.setBorder(border4[clrIndex]);
					clrButton4.setBorder(border4[clrIndex]);
					clrButton5.setBorder(border4[clrIndex]);
					labelNeuladen();
				});


				for( ActionListener a : clrButton1.getActionListeners() ) {	//Entfernt alle Actionlistner
					clrButton1.removeActionListener( a );
				}

				clrButton1.addActionListener((ActionEvent E) -> { // wenn man auf den button drueckt

					clrIndex = 0;
					centerPanel.repaint();
					panel1.setBackground(clr6[clrIndex]);
					panel2.setBackground(clr6[clrIndex]);
					panel3.setBackground(clr6[clrIndex]);
					panel4.setBackground(clr6[clrIndex]);
					punkte.setBackground(clr2[clrIndex]);
					rekord.setBackground(clr2[clrIndex]);
					titel.setBackground(clr1[clrIndex]);
					restart.setBackground(clr3[clrIndex]);
					zurueck.setBackground(clr3[clrIndex]);
					tipp.setBackground(clr3[clrIndex]);
					hilfe.setBackground(clr3[clrIndex]);
					stats.setBackground(clr3[clrIndex]);
					exit.setBackground(clr2[clrIndex]);
					clrLayout.setBackground(clr2[clrIndex]);
					centerPanel.setBackground(clr5[clrIndex]);
					clrButton1.setBackground(clr4[clrIndex]);
					clrButton2.setBackground(clr4[clrIndex]);
					clrButton3.setBackground(clr4[clrIndex]);
					clrButton4.setBackground(clr4[clrIndex]);
					clrButton5.setBackground(clr4[clrIndex]);

					restart.setBorder(border1[clrIndex]);
					zurueck.setBorder(border1[clrIndex]);
					tipp.setBorder(border1[clrIndex]);
					stats.setBorder(border1[clrIndex]);
					exit.setBorder(border3[clrIndex]);
					clrLayout.setBorder(border3[clrIndex]);
					clrButton1.setBorder(border4[clrIndex]);
					clrButton2.setBorder(border4[clrIndex]);
					clrButton3.setBorder(border4[clrIndex]);
					clrButton4.setBorder(border4[clrIndex]);
					clrButton5.setBorder(border4[clrIndex]);
					labelNeuladen();
				});
			} else {

				centerPanel.setLayout(new FlowLayout());
				centerPanel.repaint();
				restart.setVisible(true);
				zurueck.setVisible(true);
				tipp.setVisible(true);
				stats.setVisible(true);
				clrButton1.setVisible(false);
				clrButton2.setVisible(false);
				clrButton3.setVisible(false);
				clrButton4.setVisible(false);
				clrButton5.setVisible(false);
				clrLayout.setFont(new Font("Arial", Font.BOLD, 15));
				clrLayout.setText("Design");
				clrAktiv = false;
				centerPanel.remove(clrButton1);
				centerPanel.remove(clrButton2);
				centerPanel.remove(clrButton3);
				centerPanel.remove(clrButton4);
				centerPanel.remove(clrButton5);

			}

			centerPanel.repaint();

		});


		// exit button
		
		panel4.setLayout(null);
		exit.setFont(new Font("Arial", Font.BOLD, 15));
		exit.setText("Zum Login");
		exit.setBounds(35, 18, 100, 30);
		exit.setBackground(clr2[clrIndex]);
		exit.setForeground(Color.white);
		exit.setOpaque(true);
		exit.setBorder(border3[clrIndex]);
		exit.setFocusable(false);
		for( ActionListener a : exit.getActionListeners() ) {	//Entfernt alle Actionlistner
	        exit.removeActionListener( a );
	    }
		exit.addActionListener((ActionEvent e) -> { // wenn man auf den button drueckt

			clrAktiv = false;
			statsAktiv = false; 
			gameFrame.dispose();
			Login.l.loginGui(); // Static mit Klassennamen aufrufen
			clrAktiv = false;
			clrButton1.setVisible(false);
			clrButton2.setVisible(false);
			clrButton3.setVisible(false);
			clrButton4.setVisible(false);
			clrButton5.setVisible(false);

		});

		gameFrame.setSize(800, 1000);
		gameFrame.setVisible(true);
		gameFrame.setLocationRelativeTo(null);                    // wird in der MItte d. Bildschirms geoeffnet
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fenster schliesst sich und code wird beendet wenn
															      // man auf X das drueckt
		gameFrame.setResizable(false);                            // fenstergroesse nicht veraenderbar
		gameFrame.removeKeyListener(game);                        // Da gameGui() oft aufgerufen wird, wird der Keylistener
		gameFrame.addKeyListener(game);                           // entfernt und hinzugefuegt, damit es nur einen gibt

	}

	// paint wird bei jedem Zug mit repaint() aufgerufen.
	// erstellt im Prinzip alles, was der Spieler sieht, auf zwei Methoden aufgeteilt

	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.gray);
		g.setColor(Color.black);
		
		// Fuer den Fall, wenn der Statistiken Button gedrueckt wird

		if (statsAktiv) {

			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.drawString("Statistiken", 80, 40);
			g.setFont(new Font("Arial", Font.PLAIN, 30));
			g.setColor(Color.white);
			
			// Alle einzelnen Statistiken
			
			g.drawString("Anzahl aller gesammelten Punkte:", 70, 120);
			g.drawString(" " + a.st.getPunkteGesamt(), 550, 120);

			// Farbe wird je nach dem hoechsten Feld ab 2048 geaendert, kleines Detail

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

			// Wenn man jedes Spiel 2048 erreicht sind die 100% gold, kleines Detail

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

		} else if (clrAktiv) {

			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.drawString("Wählen Sie ein Design", 220, 40);
			g.setFont(new Font("Arial", Font.PLAIN, 30));


			// Der Fall, wenn man spielt

		} else {
			
			// Erstellen der Eingabeparameter fuer felder(), da verschiedene Feldgroessen entsprechend angepasst werden muessen

			int breite = 0;            // hoehe, breite eines Feldes
			int schrift = 0;           // Schriftgroesse der Zahl im Feld
			int abstand = 0;           // Abstand der Felder zueinander
			int schriftX = 0;          // Position der Zahl X
			int schriftY = 0;          // Y
			int r = 0;                 // Parameter r, v, e veraendern je nach Feldgroesse und Zahl die Position 
			int v = 0;                 // der Zahl minimal, damit sie in der Mitte sind
			int e = 0;
			int f = 0;                 // Parameter f fuer eine fuenfstellige Zahl
			int schriftAenderung = 0;  // Aenderung der Schriftgroesse je nach Zahl
			int schriftAenderung2 = 0; // Aenderung der Schriftgroesse fuer eine fuenfstellige Zahl

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
				schriftAenderung2 = 10;
				f = -4;

			} else if (a.s.getBreite() == 5) {

				breite = 120;
				schrift = 70;
				abstand = 140;
				schriftX = 41;
				schriftY = 82;
				v = 5;
				e = 7;
				f = 5;
				schriftAenderung2 = 8;

			} else if (a.s.getBreite() == 6) {

				breite = 95;
				schrift = 60;
				abstand = 117;
				schriftX = 31;
				schriftY = 70;
				v = 10;
				e = 17;
				r = 4;
				f = 18;
				schriftAenderung2 = 10;

			} else if (a.s.getBreite() == 7) {

				breite = 86;
				schrift = 45;
				abstand = 99;
				schriftX = 28;
				schriftY = 58;
				v = 20;
				e = 32;
				r = 10;
				f = 22;
				

			} else if (a.s.getBreite() == 8) {

				breite = 72;
				schrift = 35;
				abstand = 87;
				schriftX = 26;
				schriftY = 52;
				v = 27;
				e = 27;
				r = 10;
				f = 26;
				schriftAenderung2 = -5;
				schriftAenderung = 10;

			}

			for (int i = 0; i < a.s.getBreite(); i++) { // geht bei jedem Zug einmal durchs Feld druch und ruft felder()
														// auf
				for (int j = 0; j < a.s.getBreite(); j++) {

					felder(g, a.s.getFeld()[i][j], j * abstand + 85, i * abstand + 15, breite, schrift, schriftX,
							schriftY, v, e, r, f, schriftAenderung, schriftAenderung2);

				}

			}

			if (a.s.gameOver()) { // wenn man verloren hat

				g.setColor(Color.black);
				g.setFont(new Font("Arial", Font.BOLD, 40));
				g.drawString("Game Over!", 300, 340);
				g.setFont(new Font("Arial", Font.BOLD, 30));
				g.drawString("Druecke 'enter' um neu zu starten.", 200, 390);
				labelNeuladen();

				// Startet neue Runden bis Spielende, nur fuers Testen
				
			 	//if (!a.s.gewonnen()) { neuesSpiel(); }

			}

		}
	}

	// kuemmert sich um das Faerben der Felder

	public void felder(Graphics g, Block block, int x, int y, int breite, int schrift, int schriftX, int schriftY,
			int v, int e, int r, int f, int s, int s2) {

		int wert = block.getWert();

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(clr4[clrIndex]);

		g.setFont(new Font("Arial", Font.BOLD, schrift)); // schriftgroesse der zahl im feld

		g2.fillRoundRect(x, y, breite, breite, 10, 10); // graue, leere Felder

		g2.setColor(Color.black);
		if (wert > 0) { // je nach Wert wird das Feld gefaerbt
			g2.setColor(block.getFarbe());

			g2.fillRoundRect(x, y, breite, breite, 10, 10);

			g2.setColor(new Color(117, 110, 101));

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
			
			else if (wert < 100000) {

				g.setColor(Color.white);
				g.setFont(new Font("Arial", Font.BOLD, schrift - 20 - s2));
				g.drawString("" + wert, x + schriftX - 45 + f, y + schriftY);

			}

		}

	}

	// Der KeyListener ruft welcheRichtung() je nach Eingabe auf und klont das Spielfeld
	
	public void keyPressed(KeyEvent e) {

		if ((e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) && !statsAktiv && !clrAktiv) {

			if (a.s.verschiebbar("oben")) {
				a.klonen();
				a.s.welcheRichtung("oben");
				centerPanel.repaint();
			}

		} else if ((e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) && !statsAktiv && !clrAktiv) {

			if (a.s.verschiebbar("unten")) {
				a.klonen();
				a.s.welcheRichtung("unten");
				centerPanel.repaint();
			}

		} else if ((e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT) && !statsAktiv && !clrAktiv) {

			if (a.s.verschiebbar("links")) {
				a.klonen();
				a.s.welcheRichtung("links");
				centerPanel.repaint();
			}

		} else if ((e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) && !statsAktiv && !clrAktiv) {

			if (a.s.verschiebbar("rechts")) {
				a.klonen();
				a.s.welcheRichtung("rechts");
				centerPanel.repaint();
			}

		} else if (e.getKeyChar() == 'z' && !statsAktiv && !clrAktiv) {

			a.klonen();
			a.s.welcheRichtung(auto.zufaelligeRichtung());
			gameFrame.repaint();

		} else if (e.getKeyChar() == 'r' && !statsAktiv && !clrAktiv) {

			a.klonen();
			a.s.welcheRichtung(auto.naechsterZug(a.s));
			gameFrame.repaint();

		} else if (e.getKeyChar() == 'c' && !statsAktiv && !clrAktiv) {

			a.klonen();
			a.s.welcheRichtung(auto.muster(a.s));
			gameFrame.repaint();

		}  else if (e.getKeyCode() == KeyEvent.VK_ENTER && a.s.gameOver()) {

			neuesSpiel();

		}

		if(a.s.getVeraendert()) {		//Nur bei Veraenderung soll sich was aendern
			a.st.update();
			punkte.setText("<html>Punkte <br>" + a.s.getPunkte() + "</html>"); // Updaten der Punktzahl
			rekord.setText("<html>Rekord <br>" + a.st.setRekord() + "</html>"); // Updaten des Rekords
			JSONVerwalter.speichern(a); // Speichern der Json	
		}
		labelNeuladen();
		hilfe.setVisible(false);
		
	}

	public void actionPerformed(ActionEvent e) {	// macht nichts, muss aber da sein weil Interface
	}

	public void keyReleased(KeyEvent e) { // macht nichts, muss aber da sein weil Interface
	}

	public void keyTyped(KeyEvent e) { // macht nichts, muss aber da sein weil Interface
	} 

	// wird beim Starten eines neues Spiels aufgerufen
	
	public static void neuesSpiel() {

		a.st.updateEnde();
		a.s = new Spielfeld(a.s.getBreite());
		a.st.updateSpielfeld(a.s);
		a.s.blockErstellen();
		a.s.blockErstellen();
		a.klonen();
		JSONVerwalter.speichern(a);
		centerPanel.repaint();
		labelNeuladen();
	}

	// Fuer die Punktzahl, rekorde und kleine Details
	
	public static void labelNeuladen() {
		
		a.st.updateSpielfeld(a.s);	//Sicherstellen
		punkte.setText("<html>Punkte <br>" + a.s.getPunkte() + "</html>"); // Updaten der Punktzahl
		rekord.setText("<html>Rekord <br>" + a.st.setRekord() + "</html>"); // Updaten des Rekords

		// Farbe des Titels aendert sich je nach hoechstem Feld ab 2048
		
		if (a.s.hoechstesFeld == 2048) {

			titel.setForeground(clr7[clrIndex]);

		} else if (a.s.hoechstesFeld == 4096) {

			titel.setForeground(new Color(224, 110, 112));

		} else if (a.s.hoechstesFeld == 8192) {

			titel.setForeground(new Color(220, 88, 94));

		} else if (a.s.hoechstesFeld >= 16384) {

			titel.setForeground(new Color(225, 79, 73));

		} else {

			titel.setForeground(Color.white);

		}

		 // zurueck button wird grau, wenn er nichts aendert
		
		if (a.s.equals(a.klon)) {

			zurueck.setBackground(Color.gray);
			zurueck.setBorder(border2);

		} else {

			zurueck.setBackground(clr3[clrIndex]);
			zurueck.setBorder(border1[clrIndex]);

		}
	}
}
