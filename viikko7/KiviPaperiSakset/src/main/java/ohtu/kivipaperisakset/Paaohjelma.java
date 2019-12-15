package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = scanner.nextLine();
            System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
            Peli peli = null;
            if (vastaus.endsWith("a")) {
                peli = Peli.kaksiPelaajaa();
            } else if (vastaus.endsWith("b")) {
                peli = Peli.helppoTekoaly();
            } else if (vastaus.endsWith("c")) {
                peli = Peli.vaikeaTekoaly();
            } else {
                break;
            }
            peli.pelaa();

        }

    }
}
