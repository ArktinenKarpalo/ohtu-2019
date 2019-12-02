package ohtu;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Erotus implements Komento {
	private JButton nollaa;
	private JButton undo;
	private JTextField tuloskentta;
	private JTextField syotekentta;
	private Sovelluslogiikka sovellus;
	private int ed;

	public Erotus(JTextField tuloskentta, JTextField syotekentta, Sovelluslogiikka sovellus, JButton nollaa, JButton undo) {
		this.tuloskentta = tuloskentta;
		this.syotekentta = syotekentta;
		this.sovellus = sovellus;
		this.nollaa = nollaa;
		this.undo = undo;
	}

	public void suorita() {
		int arvo = 0;
		try {
			arvo = Integer.parseInt(syotekentta.getText());
		} catch (Exception e) {
		}
		sovellus.miinus(arvo);
		this.ed = arvo;

		int laskunTulos = sovellus.tulos();

		syotekentta.setText("");
		tuloskentta.setText("" + laskunTulos);
		if ( laskunTulos==0) {
			nollaa.setEnabled(false);
		} else {
			nollaa.setEnabled(true);
		}
		undo.setEnabled(true);
	}

	public void peru() {
		sovellus.plus(ed);

		int laskunTulos = sovellus.tulos();

		syotekentta.setText("");
		tuloskentta.setText("" + laskunTulos);
		if ( laskunTulos==0) {
			nollaa.setEnabled(false);
		} else {
			nollaa.setEnabled(true);
		}
		undo.setEnabled(true);
	}
}
