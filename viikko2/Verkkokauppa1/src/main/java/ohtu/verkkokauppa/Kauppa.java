package ohtu.verkkokauppa;

public class Kauppa {

    private VarastoRajapinta varastoRajapinta;
    private PankkiRajapinta pankkiRajapinta;
    private Ostoskori ostoskori;
    private ViitegeneraattoriRajapinta viitegeneraattoriRajapinta;
    private String kaupanTili;

    public Kauppa(VarastoRajapinta varastoRajapinta, PankkiRajapinta pankkiRajapinta, ViitegeneraattoriRajapinta viitegeneraattoriRajapinta) {
        this.varastoRajapinta = varastoRajapinta;
        this.pankkiRajapinta = pankkiRajapinta;
        this.viitegeneraattoriRajapinta = viitegeneraattoriRajapinta;
        kaupanTili = "33333-44455";
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varastoRajapinta.haeTuote(id);
        varastoRajapinta.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varastoRajapinta.saldo(id)>0) {
            Tuote t = varastoRajapinta.haeTuote(id);
            ostoskori.lisaa(t);
            varastoRajapinta.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattoriRajapinta.uusi();
        int summa = ostoskori.hinta();
        
        return pankkiRajapinta.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
