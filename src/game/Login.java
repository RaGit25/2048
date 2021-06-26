package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.border.Border;

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
            Game.gameGui(); // das Spielfenster wird geoeffnet und das Spiel startet
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

    public void actionPerformed(ActionEvent e) {

    }
}
