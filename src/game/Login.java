package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class Login extends JPanel implements ActionListener {

	static JFrame loginFrame = new JFrame("login");

	static ImageIcon bkgicon = new ImageIcon("Login-bkg.PNG");
	static JLabel background = new JLabel(
			new ImageIcon(bkgicon.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT)));
	static ImageIcon titleicon = new ImageIcon("2048.png");
	static JLabel title = new JLabel(
			new ImageIcon(titleicon.getImage().getScaledInstance(150, 75, Image.SCALE_DEFAULT)));

	static JTextField textFeld = new JTextField("namen eingeben");

	static JButton loginButton = new JButton("Neues Spiel");
	static JButton loadGame = new JButton("Spiel laden");
	static JButton plusButton = new JButton("+");
	static JButton confirmButton = new JButton("bestaetigen");

	static String accountString[] = { "Konto auswaehlen" };
	static JComboBox<Object> accountAuswahlliste = new JComboBox<Object>(accountString);

	// static int groesse = 0;

	// static Account a = new Account(textFeld.getSelectedText());

	static String groesseString[] = { "Feldgroesse auswaehlen", "3x3", "4x4", "5x5", "6x6", "7x7", "8x8" };
	static JComboBox groesseComboBox = new JComboBox(groesseString);

		public static void loginGui() {

		// Bevor das Fenster erstellt wir sollte gescannt werden, was für .json Dateien
		// vorhanden sind
		scannen();

		loginFrame.setSize(600, 300);
		loginFrame.setLocationRelativeTo(null); // wird in der MItte d. Bildschirms geoeffnet
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fenster schliesst sich und code wird beendet wenn
		// man auf X das drueckt
		loginFrame.setResizable(false); // fenstergroesse nicht veraenderbar

		loginFrame.add(background);

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

		background.add(textFeld); // fuer die Kontoerstellung benoetigten Elemente
		background.add(confirmButton);
		textFeld.setVisible(false); // unsichtbar gemacht da hier noch nicht gebraucht
		confirmButton.setVisible(false);

		loginFrame.setVisible(true);

		// ----------Neues Spiel------------

		// Soll einen vorhandenen Account, der ausgewaehlt wurde, erkennen
		// und eine neu ausgewaehlte Feldgroesse einstellen und das Spiel starten

		loginButton.addActionListener((ActionEvent e) -> { // auf "einloggen" wird geclickt
			if (groesseComboBox.getSelectedIndex() != 0 && accountAuswahlliste.getSelectedIndex() != 0) {
				//Game.setAccount(JSONVerwalter.laden(accountString[accountAuswahlliste.getSelectedIndex()]));
				Game.gameGui();
				loginFrame.dispose(); // das Loginfenster schliesst sich
			}
		});

		// ---------Spiel laden--------

		// Soll einen ausgewaehlten Account erkennen und den Spielstand laden

		loadGame.addActionListener((ActionEvent e) -> { // auf "einloggen" wird geclickt
			if (accountAuswahlliste.getSelectedIndex() != 0) {

				Game.setAccount(JSONVerwalter.laden("test"));
				//System.out.println(accountString[accountAuswahlliste.getSelectedIndex()]);
				
				Game.gameGui();
				loginFrame.dispose(); // das Loginfenster schliesst sich
			}
		});

		plusButton.addActionListener((ActionEvent e) -> { // auf "+" wird geclickt
			loginButton.setVisible(false); // Elemente vom login werden unsichtbar gemacht
			plusButton.setVisible(false);
			accountAuswahlliste.setVisible(false);
			loadGame.setVisible(false);
			title.setVisible(false);

			textFeld.setText("namen eingeben");
			textFeld.setVisible(true); // Elemente von der Kontoerstellung werden sichtbar gemacht
			confirmButton.setVisible(true);
		});

		// --------Neuer Account-------

		// Soll einen neuen Account mit dem Namen erstellen, der eingegeben wurde
		// und zur JComboBox "accountAuswahlliste" diesen Account hinzufuegen
		// und ein Spiel mit der ausgewaehlten Feldgroesse starten

		confirmButton.addActionListener((ActionEvent e) -> { // auf "bestaetigen" wird geclickt

			if (textFeld.getText() != "namen eingeben" && groesseComboBox.getSelectedIndex() != 0) {

				Account n = new Account(textFeld.getText(), groesseComboBox.getSelectedIndex() + 2);
				n.s.blockErstellen();
				n.s.blockErstellen();
				n.klonen();
				n.st.update();

				Game.setAccount(n);

				Game.gameGui();
				loginFrame.dispose();
			}
		});

	}

	public void actionPerformed(ActionEvent e) {

	}

	public static void scannen() {

		File f = new File("./"); // Im gleichen Ordner starten
		File[] fileArray = f.listFiles(); // Erstellen eines Feldes mit allen Dateien

		for (int i = 0; i < fileArray.length; i++) {
			if (fileArray[i].getPath().contains(".json")) { // Wenn die Datei .json enthält
				String name = fileArray[i].getPath();
				name = name.substring(2, name.length() - 5); // Abschneiden des .json
				accountAuswahlliste.addItem(name);
			}
		}

	}

	public static void main(String[] args) {

		Login.loginGui();

	}
}