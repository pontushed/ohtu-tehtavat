package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Nollaa extends Komento {

  public Nollaa(JTextField tuloskentta, JTextField syotekentta, JButton nollaa, JButton undo, Sovelluslogiikka sovellus) {      
    super(tuloskentta, syotekentta, nollaa, undo, sovellus);
  }

  @Override
  protected void laske() {
    this.sovellus.nollaa();
  }
}