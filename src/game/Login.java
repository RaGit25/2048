package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Login extends JPanel implements ActionListener{

    static JFrame loginFrame = new JFrame("login");

    static ImageIcon bkgicon = new ImageIcon("Login-bkg.PNG");
    static JLabel background = new JLabel(new ImageIcon(bkgicon.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT)));
    static ImageIcon titleicon = new ImageIcon("2048.png");
    static JLabel title = new JLabel(new ImageIcon(titleicon.getImage().getScaledInstance(150, 75, Image.SCALE_DEFAULT)));

    static JTextField textFeld = new JTextField("namen eingeben");

    static JButton loginButton = new JButton("einloggen");
    static JButton plusButton = new JButton("+");
    static JButton confirmButton = new JButton("bestaetigen");

    static String accountString[] = { "Konto auswaehlen", "aaaaaaaaa", "bbbbbbbb" };
    static JComboBox<Object> accountAuswahlliste = new JComboBox<Object>(accountString);

    static String groesseString[] = { "Feldgroesse auswaehlen", "3x3", "4x4", "5x5", "6x6", "7x7", "8x8" };
    static JComboBox<Object> groesseComboBox = new JComboBox<Object>(groesseString);

    int feldgroesse;

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

        gbc.gridy=0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        background.add(title,gbc);

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


        background.add(textFeld);                   // fuer die Kontoerstellung benoetigten Elemente
        background.add(confirmButton);
        textFeld.setVisible(false);                  // unsichtbar gemacht da hier noch nicht gebraucht
        confirmButton.setVisible(false);

        loginFrame.setVisible(true);

        loginButton.addActionListener((ActionEvent e) -> {                                              // auf "einloggen" wird geclickt
            if (groesseComboBox.getSelectedIndex() != 0 && accountAuswahlliste.getSelectedIndex() != 0)
            {
                Account a = new Account(groesseComboBox.getSelectedIndex()+2);
                Game.gameGui(groesseComboBox.getSelectedIndex()); // das Spielfenster wird geoeffnet und das Spiel startet
                loginFrame.dispose(); // das Loginfenster schliesst sich
            } else {

            }
        });

        plusButton.addActionListener((ActionEvent e) -> {                                               // auf "+" wird geclickt
            loginButton.setVisible(false); // Elemente vom login werden unsichtbar gemacht
            plusButton.setVisible(false);
            accountAuswahlliste.setVisible(false);

            textFeld.setText("namen eingeben");
            textFeld.setVisible(true);          // Elemente von der Kontoerstellung werden sichtbar gemacht
            confirmButton.setVisible(true);
        });

        confirmButton.addActionListener((ActionEvent e) -> {                                        // auf "bestaetigen" wird geclickt
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