package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class Peli {

	private static final Scanner scanner = new Scanner(System.in);

	public void pelaa(String toisenPelaajanNimi) {
		Tuomari tuomari = new Tuomari();
		TekoalyHelppo tekoaly = new TekoalyHelppo();

		System.out.print("Ensimmäisen pelaajan siirto: ");
		String ekanSiirto = scanner.nextLine();
		String tokanSiirto = toisenPelaajanSiirto();

		System.out.println(toisenPelaajanNimi + " valitsi: " + tokanSiirto);

		while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
			tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
			System.out.println(tuomari);
			System.out.println();

			System.out.print("Ensimmäisen pelaajan siirto: ");
			ekanSiirto = scanner.nextLine();

			tokanSiirto = toisenPelaajanSiirto();
			System.out.println(toisenPelaajanNimi + " valitsi: " + tokanSiirto);
			ekanPelaajanSiirto(ekanSiirto);

		}

		System.out.println();
		System.out.println("Kiitos!");
		System.out.println(tuomari);
	}

	protected abstract void pelaa();
	protected abstract String toisenPelaajanSiirto();
	protected abstract void ekanPelaajanSiirto(String siirto);

	private static boolean onkoOkSiirto(String siirto) {
		return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
	}

	public static Peli helppoTekoaly() {
		return new KPSTekoaly(new TekoalyHelppo());
	}

	public static Peli vaikeaTekoaly() {
		return new KPSTekoaly(new TekoalyParannettu(20));
	}

	public static Peli kaksiPelaajaa() {
		return new KPSPelaajaVsPelaaja();
	}

}