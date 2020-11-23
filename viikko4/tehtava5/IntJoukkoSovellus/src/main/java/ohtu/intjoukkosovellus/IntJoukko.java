package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5; // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha

    private int kasvatuskoko;   // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] luvut;        // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;   // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this.alustaOlio(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this.alustaOlio(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        this.alustaOlio(kapasiteetti, kasvatuskoko);
    }

    private void alustaOlio(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti ei voi olla negatiivinen");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetti ei voi olla negatiivinen");//heitin vaan jotain :D
        }
        this.luvut = new int[kapasiteetti];
        this.kasvatuskoko = kasvatuskoko;
        this.alkioidenLkm = 0;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            if (this.alkioidenLkm >= this.luvut.length) {
                this.kasvataTaulukkoa();
            }
            luvut[alkioidenLkm] = luku;
            alkioidenLkm++;
            return true;
        }
        return false;
    }

    private void kasvataTaulukkoa() {
        int[] uusi = new int[this.luvut.length + kasvatuskoko];
        for (int i = 0; i < this.luvut.length; i++) {
            uusi[i] = this.luvut[i];
        }
        this.luvut = uusi;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < this.alkioidenLkm; i++) {
            if (luku == this.luvut[i]) {
                return true;
            }
        }
        return false;
    }

    private int luvunIndeksi(int luku) {
        for (int i = 0; i < this.luvut.length; i++) {
            if (this.luvut[i] == luku) {
                return i;
            }
        }
        return -1;
    }

    public boolean poista(int luku) {
        if (!this.kuuluu(luku)) {
            return false;
        }
        for (int i = this.luvunIndeksi(luku); i < this.luvut.length - 1; i++) {
            this.luvut[i] = this.luvut[i + 1];
        }
        this.alkioidenLkm--;
        return true;
    }

    public int mahtavuus() {
        return this.alkioidenLkm;
    }

    @Override
    public String toString() {
        if (this.alkioidenLkm == 0) {
            return "{}";
        } else {
            String tulostus = "{";
            for (int i = 0; i < this.alkioidenLkm - 1; i++) {
                tulostus += this.luvut[i];
                tulostus += ", ";
            }
            tulostus += this.luvut[this.alkioidenLkm - 1];
            tulostus += "}";
            return tulostus;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[this.alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = this.luvut[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdisteAB = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdisteAB.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdisteAB.lisaa(bTaulu[i]);
        }
        return yhdisteAB;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkausAB = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkausAB.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkausAB;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotusAB = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            erotusAB.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotusAB.poista(bTaulu[i]);
        }

        return erotusAB;
    }

}
