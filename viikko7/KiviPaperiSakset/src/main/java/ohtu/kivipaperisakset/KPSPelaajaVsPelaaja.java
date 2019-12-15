package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends Peli {

    private static final Scanner scanner = new Scanner(System.in);

    public void pelaa() {
        super.pelaa("Toinen pelaaja");
    }

    @Override
    protected String toisenPelaajanSiirto() {
        return scanner.nextLine();
    }

    @Override
    protected void ekanPelaajanSiirto(String siirto) {

    }
}