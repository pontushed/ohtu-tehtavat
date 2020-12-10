package ohtu.kivipaperisakset;

public class KPSTekoaly extends KiviPaperiSakset {

    private Tekoaly tekoaly = new Tekoaly();

    @Override
    protected String toisenSiirto(String ekanSiirto) {
        String siirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + siirto);
        tekoaly.asetaSiirto(ekanSiirto);
        return siirto;
    }
}