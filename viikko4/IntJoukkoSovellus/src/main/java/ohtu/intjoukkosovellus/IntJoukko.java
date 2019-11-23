
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko, kapasiteetti;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] alkiot;      // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this.kapasiteetti = KAPASITEETTI;
        this.kasvatuskoko = OLETUSKASVATUS;
        alustaTaulukko();

    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        this.kapasiteetti = kapasiteetti;
        this.kasvatuskoko = OLETUSKASVATUS;
        alustaTaulukko();
    }

    private void alustaTaulukko() {
        alkiot = new int[kapasiteetti];
        for (int i = 0; i < alkiot.length; i++) {
            alkiot[i] = 0;
        }
        alkioidenLkm = 0;
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");
        }
        this.kapasiteetti = kapasiteetti;
        this.kasvatuskoko = kasvatuskoko;
        alustaTaulukko();
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            alkiot[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % alkiot.length == 0) {
                int[] taulukkoOld = alkiot;
                alkiot = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(taulukkoOld, alkiot);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for(int i=0; i<alkioidenLkm; i++)
            if(alkiot[i] == luku)
                return true;
        return false;
    }

    public boolean poista(int luku) {
        for(int i=0; i<alkioidenLkm; i++) {
            if(alkiot[i] == luku) {
                for(int j=i; j<alkioidenLkm-1; j++)
                    alkiot[j] = alkiot[j+1];
                alkioidenLkm--;
                return true;
            }
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < Math.min(vanha.length, uusi.length); i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for(int i=0; i<alkioidenLkm; i++) {
            if(i > 0)
                sb.append(", ");
            sb.append(alkiot[i]);
        }
        sb.append('}');
        return sb.toString();
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        kopioiTaulukko(alkiot, taulu);
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdiste.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdiste.lisaa(bTaulu[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkaus.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkaus;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            erotus.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotus.poista(bTaulu[i]);
        }
 
        return erotus;
    }
        
}
