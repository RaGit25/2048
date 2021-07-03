package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import java.applet.*;

public class Login extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	static JFrame loginFrame = new JFrame("login");

	static ImageIcon bkg1 = new ImageIcon("bilder/bkg1.jpg");
	static ImageIcon bkg2 = new ImageIcon("bilder/bkg2.jpg");
	static ImageIcon bkg3 = new ImageIcon("bilder/bkg3.jpg");
	static ImageIcon bkg4 = new ImageIcon("bilder/bkg4.jpg");
	static ImageIcon bkg5 = new ImageIcon("bilder/bkg5.jpg");
	static ImageIcon bkg6 = new ImageIcon("bilder/bkg6.jpg");
	static ImageIcon bkg7 = new ImageIcon("bilder/bkg7.PNG");

	static ImageIcon[] bkgicon = { bkg1, bkg2, bkg3, bkg4, bkg5, bkg6, bkg7 };

	static ImageIcon titleicon = new ImageIcon("bilder/2048.png");
	static JLabel title = new JLabel(
			new ImageIcon(titleicon.getImage().getScaledInstance(150, 75, Image.SCALE_DEFAULT)));

	static JTextField textFeld = new JTextField("namen eingeben");

	static JButton loginButton = new JButton("Neues Spiel");
	static JButton loadGame = new JButton("Spiel laden");
	static JButton plusButton = new JButton("Neuer Account");
	static JButton confirmButton = new JButton("bestaetigen");

	static String accountString[] = accounts();
	static JComboBox<String> accountAuswahlliste = new JComboBox<String>(accountString);

	static String groesseString[] = { "Feldgroesse auswaehlen", "3x3", "4x4", "5x5", "6x6", "7x7", "8x8" };
	static JComboBox<Object> groesseComboBox = new JComboBox<Object>(groesseString);

	static JLabel background = new JLabel(new ImageIcon(
			bkgicon[(int) (Math.random() * 7)].getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT)));

	public Login() {

		accountAuswahlliste.setEditable(true);

	}

	public static void loginGui() {

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
		textFeld.addFocusListener(new FocusAdapter() { // Man soll immer den gesmten Text ausw�hlen
			public void focusGained(FocusEvent e) {
				textFeld.selectAll(); // Damit man direkt �berschreibt
			}
		});
		background.add(confirmButton);
		textFeld.setVisible(false); // unsichtbar gemacht da hier noch nicht gebraucht
		confirmButton.setVisible(false);

		loginFrame.setVisible(true);

		// ----------Neues Spiel------------

		// Soll einen vorhandenen Account, der ausgewaehlt wurde, erkennen
		// und eine neu ausgewaehlte Feldgroesse einstellen und das Spiel starten

		loginButton.addActionListener((ActionEvent e) -> { // auf "einloggen" wird geclickt
			if (groesseComboBox.getSelectedIndex() != 0 && accountAuswahlliste.getSelectedIndex() != 0) {

				Game.setAccount(JSONVerwalter.laden(accountString[accountAuswahlliste.getSelectedIndex()]));
				Game.a.s.breite = groesseComboBox.getSelectedIndex() + 2;
				Game.neuesSpiel();
				Game.gameGui();
				loginFrame.dispose(); // das Loginfenster schliesst sich
			}
		});

		// ---------Spiel laden--------

		// Soll einen ausgewaehlten Account erkennen und den Spielstand laden

		loadGame.addActionListener((ActionEvent e) -> { // auf "spiel laden" wird geclickt
			if (accountAuswahlliste.getSelectedIndex() != 0) {

				Game.setAccount(JSONVerwalter.laden(accountString[accountAuswahlliste.getSelectedIndex()]));
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

			if (nameVerfuegbar(textFeld.getText()) && groesseComboBox.getSelectedIndex() != 0) {

				Account n = new Account(textFeld.getText(), groesseComboBox.getSelectedIndex() + 2);
				n.s.blockErstellen();
				n.s.blockErstellen();
				n.klonen();
				n.st.update();
				JSONVerwalter.speichern(n);

				accountAuswahlliste.setModel(new DefaultComboBoxModel<String>(accountString));
				// DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>)
				// accountAuswahlliste.getModel();
				accountAuswahlliste.removeAllItems();

				accountString = accounts();

				for (int i = 0; i < accountString.length; i++) {

					accountAuswahlliste.addItem(accountString[i]);

				}

				confirmButton.setVisible(false);
				textFeld.setVisible(false);
				loginButton.setVisible(true);
				plusButton.setVisible(true);
				accountAuswahlliste.setVisible(true);
				title.setVisible(true);
				loadGame.setVisible(true);
			}
		});

	}

	/*
	 * Account n = new Account(textFeld.getText(),
	 * groesseComboBox.getSelectedIndex() + 2); n.s.blockErstellen();
	 * n.s.blockErstellen(); n.klonen(); n.st.update(); JSONVerwalter.speichern(n);
	 * 
	 * Game.setAccount(n);
	 */

	public void actionPerformed(ActionEvent e) {

	}

	public static boolean nameVerfuegbar(String name) {
		for (int j = 0; j < accountString.length; j++) {
			if (accountString[j].equals(name)) {
				return false;
			}
		}
		return true;
	}

	private static String[] accounts() {
		File f = new File("./"); // Im gleichen Ordner starten
		File[] fileArray = f.listFiles(); // Erstellen eines Feldes mit allen Dateien

		ArrayList<String> acc = new ArrayList<String>();
		for (int i = 0; i < fileArray.length; i++) {
			if (fileArray[i].getPath().contains(".json")) { // Wenn die Datei .json enth�lt
				String name = fileArray[i].getPath();
				name = name.substring(2, name.length() - 5); // Abschneiden des .json
				acc.add(name);
			}
		}

		String[] accounts = new String[(acc.size()) + 1]; // Erstellen einer String-Liste
		accounts[0] = "Konto auswahlen"; // Erster Eintrag
		for (int j = 0; j < (acc.size()); j++) {

			accounts[j + 1] = acc.get(j); // �bertragung der Liste

			accounts[j + 1] = acc.get(j); // �bertragung der Liste

		}

		return accounts;
	}

	public static void main(String[] args) {
		Login l = new Login();
		Login.loginGui();

	}
}