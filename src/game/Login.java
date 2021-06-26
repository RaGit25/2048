package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Login extends JPanel implements ActionListener{

    static JFrame loginFrame = new JFrame("login");

    static JLabel background = new JLabel(new ImageIcon("Login-bkg.PNG"));

    static JTextField textFeld = new JTextField("namen eingeben");

    static JButton loginButton = new JButton("einloggen");
    static JButton plusButton = new JButton("+");
    static JButton confirmButton = new JButton("bestaetigen");

    static JPanel PanelfuerAccounts = new JPanel();
    static JPanel PanelfuerKontoerstellung = new JPanel();

    static String accountString[] = { "Konto auswaehlen", "aaaaaaaaa", "bbbbbbbb" };
    static JComboBox<Object> accountAuswahlliste = new JComboBox<Object>(Game.accountString);


    public static void loginGui() {

        loginFrame.setSize(600, 200);
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
        gbc.gridwidth = 1;
        background.add(accountAuswahlliste, gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        background.add(plusButton, gbc);

        gbc.gridy = 1; // relative Koordinaten im Layoutmanager
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        background.add(loginButton, gbc); // "einloggen" Knopf mit layoutmanager

        background.add(textFeld);                   // fuer die Kontoerstellung benoetigten Elemente
        background.add(confirmButton);
        textFeld.setVisible(false);                  // unsichtbar gemacht da hier noch nicht gebraucht
        confirmButton.setVisible(false);

        loginFrame.setVisible(true);

        loginButton.addActionListener((ActionEvent e) -> { // auf "einloggen" wird geclickt
            Game.gameGui(); // das Spielfenster wird geoeffnet und das Spiel startet
            loginFrame.dispose(); // das Loginfenster schliesst sich
        });

        plusButton.addActionListener((ActionEvent e) -> { // auf "+" wird geclickt
            loginButton.setVisible(false); // Elemente vom login werden unsichtbar gemacht
            plusButton.setVisible(false);
            accountAuswahlliste.setVisible(false);

            textFeld.setText("namen eingeben");
            textFeld.setVisible(true);          // Elemente von der Kontoerstellung werden sichtbar gemacht
            confirmButton.setVisible(true);
        });

        confirmButton.addActionListener((ActionEvent e) -> { // auf "bestaetigen" wird geclickt
            loginButton.setVisible(true);
            ; // Elemente vom login werden wieder sichtbar gemacht
            plusButton.setVisible(true);
            accountAuswahlliste.setVisible(true);

            textFeld.setVisible(false);         // Elemente von der Kontoerstellung werden unsichtbar gemacht
            confirmButton.setVisible(false);

        });

    }

    public void actionPerformed(ActionEvent e) {

    }
}
