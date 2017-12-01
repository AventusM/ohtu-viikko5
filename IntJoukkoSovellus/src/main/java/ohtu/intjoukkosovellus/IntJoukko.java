package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukuJono;      // Joukon luvut säilytetään taulukon alkupäässä. 

    /*
    Alkioiden lukumäärä eksplisiittisesti nyt 0
     */
    private int alkioidenLKM = 0;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        lukuJono = new int[KAPASITEETTI];
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti > 0) {
            lukuJono = new int[kapasiteetti];
            this.kasvatuskoko = OLETUSKASVATUS;
        }

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti > 0 && kasvatuskoko > 0) {
            lukuJono = new int[kapasiteetti];
            this.kasvatuskoko = kasvatuskoko;
        }
    }

    public int mahtavuus() {
        return this.alkioidenLKM;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            lukuJono[this.mahtavuus()] = luku; //Indeksöinti alkaa nollasta, tarkistus onnistuu näin päin
            this.alkioidenLKM++;
            if (this.mahtavuus() == this.lukuJono.length) {
                kasvataTayttynytTaulukko();
            }
            return true;
        }
        return false;
    }

    public void kasvataTayttynytTaulukko() {
        int[] placeHolder = this.lukuJono;
        this.lukuJono = new int[this.mahtavuus() + this.kasvatuskoko];
        kopioiTaulukko(placeHolder, lukuJono);
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < this.mahtavuus(); i++) {
            if (luku == lukuJono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int kohta = haePoistettavanLuvunIndeksi(luku);
        if (kohta != -1) {
            offsetTaulukko(kohta);
            return true;
        }
        return false;
    }

    public int haePoistettavanLuvunIndeksi(int luku) {
        for (int i = 0; i < this.mahtavuus(); i++) {
            if (luku == this.lukuJono[i]) {
                this.lukuJono[i] = 0;
                return i;
            }
        }
        return -1;
    }

    public void offsetTaulukko(int aloitusIndeksi) {
        for (int i = aloitusIndeksi; i < this.mahtavuus() - 1; i++) {
            int apu = this.lukuJono[i];
            this.lukuJono[i] = this.lukuJono[i + 1];
            this.lukuJono[i + 1] = apu;
        }
        this.alkioidenLKM--;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    @Override
    public String toString() {
        String apu = "{";
        for (int i = 0; i < this.mahtavuus(); i++) {
            if (i < this.mahtavuus() - 1) {
                apu += this.lukuJono[i] + ", ";
            } else {
                apu += this.lukuJono[i];
            }
        }
        return apu + "}";
    }

    public int[] toIntArray() {
        int[] taulu = new int[this.mahtavuus()];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukuJono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdisteJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdisteJoukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdisteJoukko.lisaa(bTaulu[i]);
        }
        return yhdisteJoukko;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkausJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkausJoukko.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkausJoukko;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotusJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            erotusJoukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotusJoukko.poista(i);
        }

        return erotusJoukko;
    }

}
