package ohtu;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Nollaa implements Komento {
	private JButton nollaa;
	private JButton undo;
	private JTextField tuloskentta;
	private JTextField syotekentta;
	private Sovelluslogiikka sovellus;
	private int edellinen = 0;

	public Nollaa(JTextField tuloskentta, JTextField syotekentta, Sovelluslogiikka sovellus, JButton nollaa, JButton undo) {
		this.tuloskentta = tuloskentta;
		this.syotekentta = syotekentta;
		this.sovellus = sovellus;
		this.nollaa = nollaa;
		this.undo = undo;
	}

	public void suorita() {
		this.edellinen = sovellus.tulos();
		sovellus.nollaa();

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
		sovellus.plus(edellinen);

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
