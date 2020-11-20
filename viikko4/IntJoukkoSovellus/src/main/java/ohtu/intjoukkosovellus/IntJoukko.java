
package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public static final int OLETUSKOKO = 5;
    public static final int OLETUSKASVATUS = 5;
    private int kasvatuskoko;
    private int[] joukko;
    private int mahtavuus;

    public IntJoukko() {
        joukko = new int[OLETUSKOKO];
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int koko) {
        joukko = koko < 0 ? new int[OLETUSKOKO] : new int[koko];
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int koko, int kasvatuskoko) {
        joukko = koko < 0 ? new int[OLETUSKOKO] : new int[koko];
        this.kasvatuskoko = kasvatuskoko < 0 ? OLETUSKASVATUS : kasvatuskoko;
    }

    public boolean lisaa(int luku) {

        if (mahtavuus == 0) {
            joukko[0] = luku;
            mahtavuus++;
            return true;
        }
        if (!kuuluu(luku)) {
            joukko[mahtavuus] = luku;
            mahtavuus++;
            if (mahtavuus == joukko.length) {
                joukko = Arrays.copyOf(joukko, mahtavuus + kasvatuskoko);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < mahtavuus; i++) {
            if (luku == joukko[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int poistettavanIndeksi = -1;
        for (int i = 0; i < mahtavuus; i++) {
            if (luku == joukko[i]) {
                poistettavanIndeksi = i;
                break;
            }
        }
        if (poistettavanIndeksi != -1) {
            for (int j = poistettavanIndeksi; j < mahtavuus - 1; j++) {
                joukko[j] = joukko[j + 1];
            }
            joukko[mahtavuus] = 0;
            mahtavuus--;
            return true;
        }
        return false;
    }

    public int mahtavuus() {
        return mahtavuus;
    }

    public int[] asArray() {
        int[] taulu = new int[mahtavuus];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = joukko[i];
        }
        return taulu;
    }

    @Override
    public String toString() {
        if (mahtavuus == 0) {
            return "{}";
        } else if (mahtavuus == 1) {
            return "{" + joukko[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < mahtavuus - 1; i++) {
                tuotos += joukko[i];
                tuotos += ", ";
            }
            tuotos += joukko[mahtavuus - 1];
            tuotos += "}";
            return tuotos;
        }
    }
}
