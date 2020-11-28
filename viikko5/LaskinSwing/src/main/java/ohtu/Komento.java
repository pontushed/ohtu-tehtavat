package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

public abstract class Komento {
    protected JTextField tuloskentta;
    protected JTextField syotekentta;
    protected JButton nollaa;
    protected JButton undo;
    protected Sovelluslogiikka sovellus;
    protected int arvo=0;
    protected int entinenArvo;
    
    public Komento(JTextField tuloskentta, JTextField syotekentta, JButton nollaa, JButton undo, Sovelluslogiikka sovellus) {      
      this.tuloskentta = tuloskentta;
      this.syotekentta = syotekentta;
      this.nollaa = nollaa;
      this.undo = undo;
      this.sovellus = sovellus;      
    }

    public void suorita() {

      try {
        arvo = Integer.parseInt(syotekentta.getText());
      } catch (Exception e) {
      }
      entinenArvo = sovellus.tulos();
      laske();

      int laskunTulos = sovellus.tulos();
         
      syotekentta.setText("");
      tuloskentta.setText("" + laskunTulos);
      if ( laskunTulos==0) {
          nollaa.setEnabled(false);
      } else {
          nollaa.setEnabled(true);
      }
      undo.setEnabled(true);
    };

    public void peru() {
      sovellus.setTulos(entinenArvo);
      tuloskentta.setText("" + entinenArvo);
      undo.setEnabled(false);
    };

    protected abstract void laske();
}


