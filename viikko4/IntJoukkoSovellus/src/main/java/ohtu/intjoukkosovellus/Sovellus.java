package ohtu.intjoukkosovellus;

import java.util.Scanner;

public class Sovellus {

    private static IntJoukko A, B, C;

    private static IntJoukko mikaJoukko(Scanner lukija) {
        String komento;
        komento = lukija.nextLine();
        while (true) {
            if (komento.equals("A") || komento.equals("a")) {
                return A;
            } else if (komento.equals("B") || komento.equals("b")) {
                return B;
            } else if (komento.equals("C") || komento.equals("c")) {
                return C;
            } else {
                System.out.println("Virheellinen joukko! " + komento);
                System.out.print("Yritä uudelleen!");
                komento = lukija.nextLine();
            }
        }
    }

    private static void lisaa(Scanner lukija) {
        int lisLuku;
        IntJoukko joukko;
        System.out.print("Mihin joukkoon? ");
        joukko = mikaJoukko(lukija);
        System.out.println("");
        System.out.print("Mikä luku lisätään? ");
        lisLuku = lukija.nextInt();
        joukko.lisaa(lisLuku);
        return;

    }

    private static void yhdiste(Scanner lukija) {
        IntJoukko aJoukko, bJoukko, c;
        System.out.print("1. joukko? ");
        aJoukko = mikaJoukko(lukija);
        System.out.print("2. joukko? ");
        bJoukko = mikaJoukko(lukija);
        c = Operaatiot.yhdiste(aJoukko, bJoukko);
        System.out.println("A yhdiste B = " + c.toString());
        return;
    }

    private static void leikkaus(Scanner lukija) {
        IntJoukko aJoukko, bJoukko, c;
        System.out.print("1. joukko? ");
        aJoukko = mikaJoukko(lukija);
        System.out.print("2. joukko? ");
        bJoukko = mikaJoukko(lukija);
        c = Operaatiot.leikkaus(aJoukko, bJoukko);
        System.out.println("A leikkaus B = " + c.toString());
        return;
    }

    private static void erotus(Scanner lukija) {
        IntJoukko aJoukko, bJoukko, c;
        System.out.print("1. joukko? ");
        aJoukko = mikaJoukko(lukija);
        System.out.print("2. joukko? ");
        bJoukko = mikaJoukko(lukija);
        c = Operaatiot.erotus(aJoukko, bJoukko);
        System.out.println("A erotus B = " + c.toString());
        return;
    }

    private static void poista(Scanner lukija) {
        IntJoukko joukko;
        int lisLuku;
        System.out.print("Mistä joukosta? ");
        joukko = mikaJoukko(lukija);
        System.out.print("Mikä luku poistetaan? ");
        lisLuku = lukija.nextInt();
        joukko.poista(lisLuku);
        return;
    }

    private static void kuuluu(Scanner lukija) {
        IntJoukko joukko;
        int kysLuku;
        System.out.print("Mihin joukkoon? ");
        joukko = mikaJoukko(lukija);
        System.out.print("Mikä luku? ");
        kysLuku = lukija.nextInt();
        boolean kuuluuko = joukko.kuuluu(kysLuku);
        if (kuuluuko) {
            System.out.println(kysLuku + " kuuluu joukkoon ");
        } else {
            System.out.println(kysLuku + " ei kuulu joukkoon ");
        }
        return;
    }

    public static void main(String[] args) {
        A = new IntJoukko();
        B = new IntJoukko();
        C = new IntJoukko();
        String komento;

        System.out.println("Tervetuloa joukkolaboratorioon!");
        System.out.println("Käytössäsi ovat joukot A, B ja C.");
        System.out.println(
                "Komennot ovat lisää(li), poista(p), kuuluu(k), yhdiste(y), erotus(e), leikkaus(le) ja lopetus(quit)(q).");
        System.out.println("Joukon nimi komentona tarkoittaa pyyntöä tulostaa joukko.");

        Scanner lukija = new Scanner(System.in);
        while (true) {
            komento = lukija.nextLine().substring(0, 1).toLowerCase();
            if (komento.equals("lisää") || komento.equals("li")) {
                lisaa(lukija);
            } else if (komento.startsWith("p")) {
                poista(lukija);
            } else if (komento.startsWith("k")) {
                kuuluu(lukija);
            } else if (komento.startsWith("y")) {
                yhdiste(lukija);
            } else if (komento.equals("leikkaus") || komento.equals("le")) {
                leikkaus(lukija);
            } else if (komento.equals("erotus") || komento.equals("e")) {
                erotus(lukija);
            } else if (komento.equals("A")) {
                System.out.println(A);
            } else if (komento.equals("B")) {
                System.out.println(B);
            } else if (komento.equals("C")) {
                System.out.println(C);
            } else if (komento.equals("lopeta") || komento.equals("quit") || komento.equals("q")) {
                System.out.println("Lopetetaan, moikka!");
                break;
            } else {
                System.out.println("Virheellinen komento! " + komento);
                System.out.println(
                        "Komennot ovat lisää(li), poista(p), kuuluu(k), yhdiste(y), erotus(e) ja leikkaus(le).");
            }
            System.out.println("Komennot ovat lisää(li), poista(p), kuuluu(k), yhdiste(y), erotus(e) ja leikkaus(le).");
        }
    }
}
