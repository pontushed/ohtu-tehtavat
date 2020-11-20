package ohtu.intjoukkosovellus;

public class Operaatiot {

  public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
    IntJoukko x = new IntJoukko();
    for (int i : a.asArray()) {
      x.lisaa(i);
    }
    for (int i : b.asArray()) {
      x.lisaa(i);
    }
    return x;
  }

  public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
    IntJoukko y = new IntJoukko();
    for (int i : a.asArray()) {
      for (int j : b.asArray()) {
        if (i == j) {
          y.lisaa(j);
        }
      }
    }
    return y;
  }

  public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
    IntJoukko z = new IntJoukko();
    for (int i : a.asArray()) {
      z.lisaa(i);
    }
    for (int j : b.asArray()) {
      z.poista(j);
    }
    return z;
  }
}
