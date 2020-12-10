package ohtu.kivipaperisakset;

import java.util.HashMap;

public class Pelitehdas {
  private HashMap<String, KiviPaperiSakset> pelit;

  public Pelitehdas() {
    pelit = new HashMap<String, KiviPaperiSakset>();
    pelit.put("a", new KPSPelaajaVsPelaaja());
    pelit.put("b", new KPSTekoaly());
    pelit.put("c", new KPSParempiTekoaly());
  }

  public KiviPaperiSakset hae(String tyyppi) {
    return pelit.getOrDefault(tyyppi, null);
  }
}
