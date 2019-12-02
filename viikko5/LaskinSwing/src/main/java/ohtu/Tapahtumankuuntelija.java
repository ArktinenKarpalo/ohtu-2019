package ohtu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JTextField;
 
public class Tapahtumankuuntelija implements ActionListener {
    private JButton plus;
    private JButton miinus;
    private JButton nollaa;
    private JButton undo;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private Sovelluslogiikka sovellus;
    private Komento edellinenKomento;

    HashMap<JButton, Komento> komennot = new HashMap<>();
 
    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.plus = plus;
        this.miinus = miinus;
        this.nollaa = nollaa;
        this.undo = undo;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.sovellus = new Sovelluslogiikka();
        komennot.put(plus, new Summa(tuloskentta, syotekentta, sovellus, nollaa, undo));
        komennot.put(miinus, new Erotus(tuloskentta, syotekentta, sovellus, nollaa, undo));
        komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta, sovellus, nollaa, undo));
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == undo) {
            edellinenKomento.peru();
            return;
        }
        try {
            komennot.get((JButton) ae.getSource()).suorita();
            this.edellinenKomento = komennot.get(ae.getSource());
        } catch(NullPointerException e) {
            System.out.println("Komentoa ei vielä implementoitu.");
        }
    }
 
}