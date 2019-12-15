package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends Peli {

    private Tekoaly tekoaly;

    public KPSTekoaly(Tekoaly tekoaly) {
        this.tekoaly = tekoaly;
    }


    public void pelaa() {
        super.pelaa("Tietokone");
    }

    @Override
    protected String toisenPelaajanSiirto() {
        return tekoaly.annaSiirto();
    }

    @Override
    protected void ekanPelaajanSiirto(String siirto) {
        tekoaly.asetaSiirto(siirto);
    }
}