package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

public class Login extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	static Login l = new Login();

	static JFrame loginFrame;

	static ImageIcon bkg1;
	static ImageIcon bkg2;
	static ImageIcon bkg3;
	static ImageIcon bkg4;
	static ImageIcon bkg5;
	static ImageIcon bkg6;
	static ImageIcon bkg7;

	static ImageIcon[] bkgicon;

	static ImageIcon titleicon;
	static JLabel title;

	static JTextField textFeld;

	static Boolean darfListenerAktivSein;
	static Boolean removingActionActive;

	static JButton loginButton;
	static JButton loadGame;
	static JButton plusButton;
	static JButton confirmButton;
	static JButton zurueck;
	static JButton accountEntfernen;
	static JButton confirmDelete;

	static JLabel warnung;
	static JLabel warnung2;
	static JLabel removeLabel;

	static String[] accountString;
	static JComboBox<String> accountAuswahlliste;

	static String[] groesseString;
	static JComboBox<Object> groesseComboBox;

	static JLabel background;

	public Login() {
		
		loginFrame = new JFrame("2048 - Login");

		warnung = new JLabel();
		warnung2 = new JLabel();
		removeLabel = new JLabel();
		
		removingActionActive = false;
		
		loginButton = new JButton("Neues Spiel erstellen");
		loadGame = new JButton("Spielstand laden");
		plusButton = new JButton("Neuer Account");
		confirmButton = new JButton("Erstellen");
		zurueck = new JButton("Zurueck zur Accountauswahl");
		accountEntfernen = new JButton("Account entfernen");
		confirmDelete = new JButton("Bestaetigen");
		
		accountString = accounts();
		accountAuswahlliste = new JComboBox<String>(accountString);
		groesseString = new String[7];
		groesseString[0] = "Feldgroesse auswaehlen";
		groesseString[1] = "3x3";
		groesseString[2] = "4x4";
		groesseString[3] = "5x5";
		groesseString[4] = "6x6";
		groesseString[5] = "7x7";
		groesseString[6] = "8x8";
		groesseComboBox = new JComboBox<Object>(groesseString);

		

		textFeld = new JTextField("Namen eingeben");
		titleicon = new ImageIcon("bilder/2048.png");

		title = new JLabel(new ImageIcon(titleicon.getImage().getScaledInstance(150, 75, Image.SCALE_DEFAULT)));

		bkg1 = new ImageIcon("bilder/bkg1.jpg");
		bkg2 = new ImageIcon("bilder/bkg2.jpg");
		bkg3 = new ImageIcon("bilder/bkg3.jpg");
		bkg4 = new ImageIcon("bilder/bkg4.jpg");
		bkg5 = new ImageIcon("bilder/bkg5.jpg");
		bkg6 = new ImageIcon("bilder/bkg6.jpg");
		bkg7 = new ImageIcon("bilder/bkg7.PNG");

		bkgicon = new ImageIcon[7];
		bkgicon[0] = bkg1;
		bkgicon[1] = bkg2;
		bkgicon[2] = bkg3;
		bkgicon[3] = bkg4;
		bkgicon[4] = bkg5;
		bkgicon[5] = bkg6;
		bkgicon[6] = bkg7;
		background = new JLabel(new ImageIcon(
				bkgicon[(int) (Math.random() * 7)].getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT)));

		GridBagLayout layout = new GridBagLayout(); // Layoutmanager
		GridBagConstraints gbc = new GridBagConstraints(); //
		gbc.fill = GridBagConstraints.CENTER;
		background.setLayout(layout); //

		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		background.add(title, gbc);

		gbc.gridy = 1;
		gbc.gridwidth = 1;
		background.add(accountAuswahlliste, gbc);

		gbc.gridy = 1;
		gbc.gridx = 1;
		background.add(plusButton, gbc);

		gbc.gridy = 3; // relative Koordinaten im Layoutmanager
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		background.add(loginButton, gbc); // "einloggen" Knopf mit layoutmanager

		gbc.gridy = 2;
		gbc.gridx = 0;
		background.add(groesseComboBox, gbc);

		gbc.gridy = 4;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		background.add(loadGame, gbc);
		
		gbc.gridy = 5;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		background.add(accountEntfernen, gbc);
		
		gbc.gridy = 6;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		background.add(confirmDelete, gbc);
		
		gbc.gridy = 7;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		background.add(removeLabel, gbc);

		gbc.gridy = 6;
		gbc.gridx = 0;

		background.add(warnung, gbc);

		gbc.gridy = 3;
		gbc.gridx = 0;

		background.add(warnung2, gbc);

		gbc.gridy = 1;
		gbc.gridx = 0;
		gbc.gridwidth = 1;

		background.add(textFeld, gbc);

		gbc.gridy = 1;
		gbc.gridx = 1;

		background.add(confirmButton, gbc);

		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.gridwidth = 2;

		background.add(zurueck, gbc);

		loginFrame.setSize(600, 300);
		loginFrame.setLocationRelativeTo(null); // wird in der MItte d. Bildschirms geoeffnet
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fenster schliesst sich und code wird beendet wenn
		// man auf X das drueckt
		loginFrame.setResizable(false); // fenstergroesse nicht veraenderbar

		loginFrame.add(background);

	}

	public void loginGui() {
		
		warnung.setText("");
		warnung.setFont(new Font("Arial", Font.PLAIN, 11));
		warnung.setForeground(Color.red);
		warnung2.setText("");
		warnung2.setFont(new Font("Arial", Font.PLAIN, 11));
		warnung2.setForeground(Color.red);
		removeLabel.setText("");
		removeLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		removeLabel.setForeground(Color.black);
		darfListenerAktivSein = true;
		zurueck.setVisible(false);
		confirmDelete.setVisible(false);
		for( ActionListener a : zurueck.getActionListeners() ) {	//Entfernt alle Actionlistner
			zurueck.removeActionListener( a );
	    }
		zurueck.addActionListener((ActionEvent e) -> {

			confirmButton.setVisible(false);
			textFeld.setVisible(false);
			loginButton.setVisible(true);
			plusButton.setVisible(true);
			accountAuswahlliste.setVisible(true);
			title.setVisible(true);
			loadGame.setVisible(true);
			groesseComboBox.setVisible(true);
			warnung2.setVisible(false);
			zurueck.setVisible(false);
			accountEntfernen.setVisible(true);
			loginFrame.setTitle("2048 - Login");

		});
		for( FocusListener f : textFeld.getFocusListeners() ) {	//Entfernt alle Actionlistner
			textFeld.removeFocusListener( f );
	    }
		textFeld.addFocusListener(new FocusAdapter() { // Man soll immer den gesamten Text auswaehlen
			public void focusGained(FocusEvent e) {
				textFeld.selectAll(); // Damit man direkt ueberschreibt
			}
		});

		textFeld.setVisible(false); // unsichtbar gemacht da hier noch nicht gebraucht
		confirmButton.setVisible(false);

		loginFrame.setVisible(true);

		// ----------Neues Spiel------------

		// Soll einen vorhandenen Account, der ausgewaehlt wurde, erkennen
		// und eine neu ausgewaehlte Feldgroesse einstellen und das Spiel starten
		for( ActionListener a : loginButton.getActionListeners() ) {	//Entfernt alle Actionlistner
			loginButton.removeActionListener( a );
	    }
		loginButton.addActionListener((ActionEvent e) -> { // auf "einloggen" wird geclickt
			if (groesseComboBox.getSelectedIndex() != 0 && accountAuswahlliste.getSelectedIndex() != 0) {

				
				Game.setAccount(JSONVerwalter.laden(accountString[accountAuswahlliste.getSelectedIndex()]));
				Game.a.s.breite = groesseComboBox.getSelectedIndex() + 2;
				Game.neuesSpiel();
				Game.statsAktiv = false;
				Game.gameGui();
				loginFrame.dispose(); // das Loginfenster schliesst sich
			}

			checkForWarnings();
		});

		// ---------Spiel laden--------

		// Soll einen ausgewaehlten Account erkennen und den Spielstand laden
		for( ActionListener a : loadGame.getActionListeners() ) {	//Entfernt alle Actionlistner
			loadGame.removeActionListener( a );
	    }		
		loadGame.addActionListener((ActionEvent e) -> { // auf "spiel laden" wird geclickt
			if (accountAuswahlliste.getSelectedIndex() != 0) {

				Game.setAccount(JSONVerwalter.laden(accountString[accountAuswahlliste.getSelectedIndex()]));
				Game.gameGui();
				loginFrame.dispose(); // das Loginfenster schliesst sich
			}

			checkForWarnings();

		});
		for( ActionListener a : plusButton.getActionListeners() ) {	//Entfernt alle Actionlistner
			plusButton.removeActionListener( a );
	    }
		plusButton.addActionListener((ActionEvent e) -> { // auf "+" wird geclickt
			
			loginButton.setVisible(false); // Elemente vom login werden unsichtbar gemacht
			plusButton.setVisible(false);
			accountAuswahlliste.setVisible(false);
			loadGame.setVisible(false);
			title.setVisible(false);
			textFeld.setText("namen eingeben");
			textFeld.setVisible(true); // Elemente von der Kontoerstellung werden sichtbar gemacht
			confirmButton.setVisible(true);
			groesseComboBox.setVisible(false);
			warnung.setText("");
			warnung2.setText("");
			warnung2.setVisible(true);
			zurueck.setVisible(true);
			accountEntfernen.setVisible(false);
			loginFrame.setTitle("2048 - Account erstellen");

		});

		// --------Neuer Account-------

		// Soll einen neuen Account mit dem Namen erstellen, der eingegeben wurde
		// und zur JComboBox "accountAuswahlliste" diesen Account hinzufuegen
		// und ein Spiel mit der ausgewaehlten Feldgroesse starten
		for( ActionListener a : confirmButton.getActionListeners() ) {	//Entfernt alle Actionlistner
			confirmButton.removeActionListener( a );
	    }
		confirmButton.addActionListener((ActionEvent e) -> { // auf "bestaetigen" wird geclickt

			if (nameVerfuegbar(textFeld.getText()) && textFeld.getText().length() <= 60 && zeichenErlaubt()) {

				darfListenerAktivSein = false;

				Account n = new Account(textFeld.getText(), 4); // Standardgroesse 4
				n.s.blockErstellen();
				n.s.blockErstellen();
				n.klonen();
				n.st.update();
				JSONVerwalter.speichern(n);

				accountAuswahlliste.setModel(new DefaultComboBoxModel<String>(accountString));
				accountAuswahlliste.removeAllItems();

				accountString = accounts();

				for (int i = 0; i < accountString.length; i++) {

					accountAuswahlliste.addItem(accountString[i]);

					if (accountString[i].equals(textFeld.getText())) {
						accountAuswahlliste.setSelectedIndex(i);
					}

				}

				darfListenerAktivSein = true;

				groesseComboBox.setSelectedIndex(2);

				confirmButton.setVisible(false);
				textFeld.setVisible(false);
				loginButton.setVisible(true);
				plusButton.setVisible(true);
				accountAuswahlliste.setVisible(true);
				title.setVisible(true);
				loadGame.setVisible(true);
				groesseComboBox.setVisible(true);
				warnung2.setVisible(false);
				zurueck.setVisible(false);
				accountEntfernen.setVisible(true);
				loginFrame.setTitle("2048 - Login");
			}

			checkForWarningsNewAcc();

		});
		for( ActionListener a : accountAuswahlliste.getActionListeners() ) {	//Entfernt alle Actionlistner
			accountAuswahlliste.removeActionListener( a );
	    }
		accountAuswahlliste.addActionListener((ActionEvent e) -> {

			if (darfListenerAktivSein && accountAuswahlliste.getSelectedIndex() != 0) {

				Account a = new Account(JSONVerwalter.laden(accountString[accountAuswahlliste.getSelectedIndex()]));

				groesseComboBox.setSelectedIndex(a.s.getBreite() - 2); // waehlt die Spielgroesse des Accounts aus
				
				if(removingActionActive && darfListenerAktivSein && accountAuswahlliste.getSelectedIndex() != 0) {
				
					confirmDelete.setVisible(true);
					removeLabel.setText("<html>Druecke auf Bestaetigen,<br> um den Account<br> zu entfernen </html>");
					
					
				} 
			}
			
			if(removingActionActive && darfListenerAktivSein && accountAuswahlliste.getSelectedIndex() == 0) {
				
				removeLabel.setText("<html>Waehle den Account aus,<br> der entfernt werden soll</html>");
				confirmDelete.setVisible(false);
			}
			
		});
		
		for( ActionListener a : accountEntfernen.getActionListeners() ) {	//Entfernt alle Actionlistner
			accountEntfernen.removeActionListener( a );
	    }
		accountEntfernen.addActionListener((ActionEvent e) -> {

				if(!removingActionActive) {
					
					accountEntfernen.setText("Abbrechen");
					loginFrame.setTitle("2048 - Account entfernen");
					removeLabel.setText("<html>Waehle den Account aus,<br> der entfernt werden soll</html>");
					
					plusButton.setVisible(false);
					groesseComboBox.setVisible(false);
					loadGame.setVisible(false);
					loginButton.setVisible(false);
					title.setVisible(false);
					accountAuswahlliste.setSelectedIndex(0);
					
					
					removingActionActive = true;
					
				} else {
					
					accountEntfernen.setText("Account entfernen");
					loginFrame.setTitle("2048 - Login");
					removeLabel.setText("");
					
					plusButton.setVisible(true);
					groesseComboBox.setVisible(true);
					loadGame.setVisible(true);
					loginButton.setVisible(true);
					title.setVisible(true);
					confirmDelete.setVisible(false);
					
					removingActionActive = false;
					
				} 

			
		});
		
		for( ActionListener a : confirmDelete.getActionListeners() ) {	//Entfernt alle Actionlistner
			confirmDelete.removeActionListener( a );
	    }
		confirmDelete.addActionListener((ActionEvent e) -> {
			
			if (darfListenerAktivSein) {

			File file = new File("./"+ JSONVerwalter.laden(accountString[accountAuswahlliste.getSelectedIndex()]).name + ".json");
			
			if(file.exists()) {
				
				file.delete();
				
				darfListenerAktivSein = false;
				
				accountAuswahlliste.setModel(new DefaultComboBoxModel<String>(accountString));
				accountAuswahlliste.removeAllItems();

				accountString = accounts();
				
				for (int i = 0; i < accountString.length; i++) {

					accountAuswahlliste.addItem(accountString[i]);
					
				}
				
			}
			
		confirmDelete.setVisible(false);
		removeLabel.setText("<html>Waehle den Account aus,<br> der entfernt werden soll</html>");
		darfListenerAktivSein = true;
		
		if(accountString.length == 1) {
			
			accountEntfernen.setText("Account entfernen");
			loginFrame.setTitle("2048 - Login");
			removeLabel.setText("");
			
			plusButton.setVisible(true);
			groesseComboBox.setVisible(true);
			loadGame.setVisible(true);
			loginButton.setVisible(true);
			title.setVisible(true);
			
			removingActionActive = false;
			
		}
		
			}

		});

	}

	public static void checkForWarnings() {

		if (accountAuswahlliste.getSelectedIndex() == 0 && groesseComboBox.getSelectedIndex() == 0) {

			warnung.setText("Waehle einen Account und eine Feldgroesse aus");

		}

		else if (accountAuswahlliste.getSelectedIndex() == 0) {

			warnung.setText("Waehle einen Account aus");

		}

		else if (groesseComboBox.getSelectedIndex() == 0) {

			warnung.setText("Waehle eine Feldgroesse aus");

		} else {

			warnung.setText("");

		}

	}

	public static void checkForWarningsNewAcc() {

		if (!nameVerfuegbar(textFeld.getText())) {

			textFeld.setText("Namen eingeben");
			warnung2.setText("Dieser Accountname ist bereits vergeben");

		}

		else if (textFeld.getText().length() > 60) {

			textFeld.setText("Namen eingeben");
			warnung2.setText("<html>Der Accountname darf <br> nicht laenger als 60 Zeichen sein</html>");

		} else if (!zeichenErlaubt()) {

			textFeld.setText("Namen eingeben");
			warnung2.setText("<html>Sonderzeichen oder Leerzeichen<br> sind nicht erlaubt</html>");

		} else {

			warnung2.setText("");

		}

	}

	public static Boolean zeichenErlaubt() {

		if (textFeld.getText().indexOf("~") != -1 || textFeld.getText().indexOf("#") != -1
				|| textFeld.getText().indexOf("%") != -1 || textFeld.getText().indexOf("&") != -1
				|| textFeld.getText().indexOf("*") != -1 || textFeld.getText().indexOf(":") != -1
				|| textFeld.getText().indexOf(".") != -1 || textFeld.getText().indexOf("?") != -1
				|| textFeld.getText().indexOf("<") != -1 || textFeld.getText().indexOf(">") != -1
				|| textFeld.getText().indexOf("(") != -1 || textFeld.getText().indexOf(")") != -1
				|| textFeld.getText().indexOf("{") != -1 || textFeld.getText().indexOf("}") != -1
				|| textFeld.getText().indexOf("|") != -1 || textFeld.getText().indexOf("/") != -1
				|| textFeld.getText().indexOf("\\") != -1 || textFeld.getText().indexOf(" ") != -1
				|| textFeld.getText().indexOf("\"") != -1 || textFeld.getText().indexOf("!") != -1
				|| textFeld.getText().indexOf("-") != -1 || textFeld.getText().indexOf("_") != -1
				|| textFeld.getText().indexOf(";") != -1 || textFeld.getText().indexOf("+") != -1
				|| textFeld.getText().indexOf("´") != -1 || textFeld.getText().indexOf("=") != -1
				|| textFeld.getText().indexOf("€") != -1 || textFeld.getText().indexOf("$") != -1
				|| textFeld.getText().indexOf("§") != -1 || textFeld.getText().indexOf("^") != -1
				|| textFeld.getText().indexOf("°") != -1) {

			return false;

		} else {

			return true;

		}

	}

	public void actionPerformed(ActionEvent e) {

	}

	public static boolean nameVerfuegbar(String name) {
		for (int j = 0; j < accountString.length; j++) {
			if (accountString[j].equals(name)) {
				return false;
			}
		}
		return (name.equals("Namen eingeben")) ? false : true;
	}

	private static String[] accounts() {
		File f = new File("./"); // Im gleichen Ordner starten
		File[] fileArray = f.listFiles(); // Erstellen eines Feldes mit allen Dateien

		ArrayList<String> acc = new ArrayList<String>();
		for (int i = 0; i < fileArray.length; i++) {
			if (fileArray[i].getPath().contains(".json")) { // Wenn die Datei .json enthï¿½lt
				String name = fileArray[i].getPath();
				name = name.substring(2, name.length() - 5); // Abschneiden des .json
				acc.add(name);
			}
		}

		String[] accounts = new String[(acc.size()) + 1]; // Erstellen einer String-Liste
		accounts[0] = "Konto auswaehlen"; // Erster Eintrag
		for (int j = 0; j < (acc.size()); j++) {

			accounts[j + 1] = acc.get(j); // Uebertragung der Liste

			accounts[j + 1] = acc.get(j); // Uebertragung der Liste

		}

		return accounts;
	}

	public static void main(String[] args) {

		l.loginGui();

	}
}
