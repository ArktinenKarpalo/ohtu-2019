package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

	Pankki pankki;

	@Before
	public void setUp() {
		pankki = mock(Pankki.class);
	}

	@Test
	public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
		// luodaan ensin mock-oliot

		Viitegeneraattori viite = mock(Viitegeneraattori.class);
		// määritellään että viitegeneraattori palauttaa viitten 42
		when(viite.uusi()).thenReturn(42);

		Varasto varasto = mock(Varasto.class);
		// määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
		when(varasto.saldo(1)).thenReturn(10);
		when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

		// sitten testattava kauppa
		Kauppa k = new Kauppa(varasto, pankki, viite);

		// tehdään ostokset
		k.aloitaAsiointi();
		k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
		k.tilimaksu("pekka", "12345");

		// sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
		verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());
		// toistaiseksi ei välitetty kutsussa käytetyistä parametreista
	}

	@Test
	public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikein() {
		Viitegeneraattori viite = mock(Viitegeneraattori.class);
		// määritellään että viitegeneraattori palauttaa viitten 42
		when(viite.uusi()).thenReturn(42);

		Varasto varasto = mock(Varasto.class);
		// määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
		when(varasto.saldo(1)).thenReturn(10);
		when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

		// sitten testattava kauppa
		Kauppa k = new Kauppa(varasto, pankki, viite);

		// tehdään ostokset
		k.aloitaAsiointi();
		k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
		k.tilimaksu("pekka", "12345");

		// sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
		verify(pankki).tilisiirto(eq("pekka"), eq(42), anyString(), anyString(),eq(5));
		// toistaiseksi ei välitetty kutsussa käytetyistä parametreista
	}

	@Test
	public void kaksiEriOstosta() {
		Viitegeneraattori viite = mock(Viitegeneraattori.class);
		// määritellään että viitegeneraattori palauttaa viitten 42
		when(viite.uusi()).thenReturn(42);

		Varasto varasto = mock(Varasto.class);
		// määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
		when(varasto.saldo(1)).thenReturn(10);
		when(varasto.saldo(2)).thenReturn(10);
		when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
		when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "luumu", 9));

		// sitten testattava kauppa
		Kauppa k = new Kauppa(varasto, pankki, viite);

		// tehdään ostokset
		k.aloitaAsiointi();
		k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
		k.lisaaKoriin(2);
		k.tilimaksu("pekka", "12345");

		// sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
		verify(pankki).tilisiirto(eq("pekka"), eq(42), anyString(), anyString(),eq(5+9));
		// toistaiseksi ei välitetty kutsussa käytetyistä parametreista
	}

	@Test
	public void kaksiSamaOstosta() {
		Viitegeneraattori viite = mock(Viitegeneraattori.class);
		// määritellään että viitegeneraattori palauttaa viitten 42
		when(viite.uusi()).thenReturn(42);

		Varasto varasto = mock(Varasto.class);
		// määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
		when(varasto.saldo(1)).thenReturn(10);
		when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

		// sitten testattava kauppa
		Kauppa k = new Kauppa(varasto, pankki, viite);

		// tehdään ostokset
		k.aloitaAsiointi();
		k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
		k.lisaaKoriin(1);
		k.tilimaksu("pekka", "12345");

		// sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
		verify(pankki).tilisiirto(eq("pekka"), eq(42), anyString(), anyString(),eq(10));
		// toistaiseksi ei välitetty kutsussa käytetyistä parametreista
	}

	@Test
	public void kaksiEriTuoteToinenLoppu() {
		Viitegeneraattori viite = mock(Viitegeneraattori.class);
		// määritellään että viitegeneraattori palauttaa viitten 42
		when(viite.uusi()).thenReturn(42);

		Varasto varasto = mock(Varasto.class);
		// määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
		when(varasto.saldo(1)).thenReturn(10);
		when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
		when(varasto.saldo(2)).thenReturn(0);
		when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "juusto", 1337));
		// sitten testattava kauppa
		Kauppa k = new Kauppa(varasto, pankki, viite);

		// tehdään ostokset
		k.aloitaAsiointi();
		k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
		k.lisaaKoriin(2);
		k.tilimaksu("pekka", "12345");

		// sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
		verify(pankki).tilisiirto(eq("pekka"), eq(42), anyString(), anyString(),eq(5));
		// toistaiseksi ei välitetty kutsussa käytetyistä parametreista
	}

	@Test
	public void aloitaAsiointiNollaaTiedot() {
		Viitegeneraattori viite = mock(Viitegeneraattori.class);
		// määritellään että viitegeneraattori palauttaa viitten 42
		when(viite.uusi()).thenReturn(42);

		Varasto varasto = mock(Varasto.class);
		// määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
		when(varasto.saldo(1)).thenReturn(10);
		when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
		// sitten testattava kauppa
		Kauppa k = new Kauppa(varasto, pankki, viite);

		// tehdään ostokset
		k.aloitaAsiointi();
		k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
		k.tilimaksu("pekka", "12345");

		k.aloitaAsiointi();
		k.lisaaKoriin(1);
		k.tilimaksu("pekka", "12345");
		verify(pankki, times(2)).tilisiirto(eq("pekka"), eq(42), anyString(), anyString(),eq(5));

	}

	@Test
	public void uusiViitenumero() {
		Viitegeneraattori viite = mock(Viitegeneraattori.class);
		// määritellään että viitegeneraattori palauttaa viitten 42
		when(viite.uusi()).thenReturn(42);

		Varasto varasto = mock(Varasto.class);
		// määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
		when(varasto.saldo(1)).thenReturn(10);
		when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
		// sitten testattava kauppa
		Kauppa k = new Kauppa(varasto, pankki, viite);

		// tehdään ostokset
		k.aloitaAsiointi();
		k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
		k.tilimaksu("pekka", "12345");

		k.aloitaAsiointi();
		k.lisaaKoriin(1);
		k.tilimaksu("pekka", "12345");

		verify(viite, times(2)).uusi();
	}

	@Test
	public void poistoKoristaToimii() {
		Viitegeneraattori viite = mock(Viitegeneraattori.class);
		// määritellään että viitegeneraattori palauttaa viitten 42
		when(viite.uusi()).thenReturn(42);

		Varasto varasto = mock(Varasto.class);
		// määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
		when(varasto.saldo(1)).thenReturn(10);
		when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
		// sitten testattava kauppa
		Kauppa k = new Kauppa(varasto, pankki, viite);

		// tehdään ostokset
		k.aloitaAsiointi();
		k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
		k.poistaKorista(1);
		k.tilimaksu("pekka", "12345");
		verify(pankki, times(1)).tilisiirto(eq("pekka"), eq(42), anyString(), anyString(),eq(0));

	}

}