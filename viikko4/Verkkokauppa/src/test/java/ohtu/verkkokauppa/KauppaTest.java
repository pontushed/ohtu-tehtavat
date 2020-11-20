package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

  Pankki pankki;
  Viitegeneraattori viite;
  Varasto varasto;
  Kauppa k;

  @Before
  public void setUp() {
    pankki = mock(Pankki.class);
    viite = mock(Viitegeneraattori.class);
    varasto = mock(Varasto.class);
    k = new Kauppa(varasto, pankki, viite);
  }

  @Test
  public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {

    // määritellään että viitegeneraattori palauttaa viitten 42
    when(viite.uusi()).thenReturn(42);

    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
    when(varasto.saldo(1)).thenReturn(10);
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
    k.tilimaksu("pekka", "12345");

    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
  }

  @Test
  public void test2() {
    // määritellään että viitegeneraattori palauttaa viitten 42
    when(viite.uusi()).thenReturn(42);

    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
    when(varasto.saldo(1)).thenReturn(10);
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
    k.tilimaksu("pekka", "12345");
    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));
  }

  @Test
  public void test3() {
    // määritellään että viitegeneraattori palauttaa viitten 42
    when(viite.uusi()).thenReturn(42);

    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
    when(varasto.saldo(1)).thenReturn(10);
    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
    when(varasto.saldo(2)).thenReturn(5);
    when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kahvi", 15));

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
    k.lisaaKoriin(2); // ostetaan tuotetta numero 2 eli kahvia
    k.tilimaksu("pekka", "12345");
    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(20));
  }

  @Test
  public void test4() {
    // määritellään että viitegeneraattori palauttaa viitten 42
    when(viite.uusi()).thenReturn(42);

    when(varasto.saldo(2)).thenReturn(5);
    when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kahvi", 15));

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(2); // ostetaan tuotetta numero 2 eli kahvia
    k.lisaaKoriin(2); // ostetaan tuotetta numero 2 eli kahvia
    k.tilimaksu("pekka", "12345");
    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(30));
  }

  @Test
  public void test5() {
    // määritellään että viitegeneraattori palauttaa viitten 42
    when(viite.uusi()).thenReturn(42);

    when(varasto.saldo(2)).thenReturn(5);
    when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kahvi", 15));
    when(varasto.saldo(3)).thenReturn(0);
    when(varasto.haeTuote(3)).thenReturn(new Tuote(2, "kokis", 5));

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(2); // ostetaan tuotetta numero 2 eli kahvia
    k.lisaaKoriin(3); // ostetaan tuotetta numero 3 eli kokista
    k.tilimaksu("pekka", "12345");
    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(15));
  }

  @Test
  public void varmistaEttaOstoskoriNollaantuu() {

    when(viite.uusi()).thenReturn(42).thenReturn(56);
    when(varasto.saldo(2)).thenReturn(5);
    when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kahvi", 15));
    when(varasto.saldo(3)).thenReturn(5);
    when(varasto.haeTuote(3)).thenReturn(new Tuote(2, "kokis", 5));

    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(2); // ostetaan tuotetta numero 2 eli kahvia
    k.lisaaKoriin(3); // ostetaan tuotetta numero 3 eli kokista
    k.tilimaksu("pekka", "12345");
    verify(pankki).tilisiirto(anyString(), eq(42), anyString(), anyString(), eq(20));
    // aloitetaan uudelleen
    k.aloitaAsiointi();
    k.lisaaKoriin(2); // ostetaan tuotetta numero 2 eli kahvia
    k.tilimaksu("pekka", "12345");
    verify(pankki).tilisiirto(anyString(), eq(56), anyString(), anyString(), eq(15));
  }

  @Test
  public void koristaPoistoToimii() {
    when(viite.uusi()).thenReturn(42);
    when(varasto.saldo(2)).thenReturn(5);
    when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kahvi", 15));

    k.aloitaAsiointi();
    k.lisaaKoriin(2); // ostetaan tuotetta numero 2 eli kahvia
    k.poistaKorista(2); // eiku poistetaan
    k.lisaaKoriin(2); // eiku ostetaan silti
    k.tilimaksu("pekka", "12345");
    verify(pankki).tilisiirto(anyString(), eq(42), anyString(), anyString(), eq(15));
  }
}