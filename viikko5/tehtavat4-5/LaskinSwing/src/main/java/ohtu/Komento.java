package ohtu;

import javax.swing.JButton;
import javax.swing.JTextField;

public abstract class Komento {

    protected Sovelluslogiikka sovellus;
    protected JButton nollaa;
    protected JButton undo;
    protected JTextField tuloskentta;
    protected JTextField syotekentta;
    protected int edellinenTulos;

    public Komento(Sovelluslogiikka sovellus, JButton nollaa, JButton undo,
            JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.nollaa = nollaa;
        this.undo = undo;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.edellinenTulos = 0;
    }

    public abstract void suorita();

    public void peru() {
        int tulos = edellinenTulos;
        sovellus.nollaa();
        sovellus.plus(tulos);
        this.asetaTekstit(tulos);
        undo.setEnabled(false);
    }

    protected void asetaTekstit(int laskunTulos) {
        try {
            edellinenTulos = Integer.parseInt(tuloskentta.getText());
        } catch (Exception e) {
        }
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
        if (laskunTulos == 0) {
            nollaa.setEnabled(false);
        } else {
            nollaa.setEnabled(true);
        }
        undo.setEnabled(true);
    }
}
