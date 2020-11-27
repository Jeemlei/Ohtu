package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Tapahtumankuuntelija implements ActionListener {

    private HashMap<JButton, Komento> komennot;
    private JButton undo;
    private Komento edellinen;

    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.komennot = new HashMap<JButton, Komento>();
        Sovelluslogiikka sovellus = new Sovelluslogiikka();
        this.komennot.put(plus, new Plus(sovellus, nollaa, undo, tuloskentta, syotekentta));
        this.komennot.put(miinus, new Miinus(sovellus, nollaa, undo, tuloskentta, syotekentta));
        this.komennot.put(nollaa, new Nollaa(sovellus, nollaa, undo, tuloskentta, syotekentta));
        this.undo = undo;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if ( ae.getSource() != undo ) {
            this.edellinen = komennot.get(ae.getSource());
            this.edellinen.suorita();
        } else {
            this.edellinen.peru();
        }
    }

    public class Plus extends Komento {

        public Plus(Sovelluslogiikka sovellus, JButton nollaa, JButton undo,
            JTextField tuloskentta, JTextField syotekentta) {
            super(sovellus, nollaa, undo, tuloskentta, syotekentta);
        }

        @Override
        public void suorita() {
            int syote = 0;
            try {
                syote = Integer.parseInt(syotekentta.getText());
            } catch (Exception e) {
            }
            sovellus.plus(syote);
            this.asetaTekstit(sovellus.tulos());
        }
    }

    public class Miinus extends Komento {

        public Miinus(Sovelluslogiikka sovellus, JButton nollaa, JButton undo,
            JTextField tuloskentta, JTextField syotekentta) {
            super(sovellus, nollaa, undo, tuloskentta, syotekentta);
        }

        @Override
        public void suorita() {
            int syote = 0;
            try {
                syote = Integer.parseInt(syotekentta.getText());
            } catch (Exception e) {
            }
            sovellus.miinus(syote);
            this.asetaTekstit(sovellus.tulos());
        }
    }

    public class Nollaa extends Komento {

        public Nollaa(Sovelluslogiikka sovellus, JButton nollaa, JButton undo,
            JTextField tuloskentta, JTextField syotekentta) {
            super(sovellus, nollaa, undo, tuloskentta, syotekentta);
        }

        @Override
        public void suorita() {
            sovellus.nollaa();
            this.asetaTekstit(0);
            nollaa.setEnabled(false);
        }
    }
}
