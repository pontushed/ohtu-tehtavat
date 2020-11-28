package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.util.Map;
import java.util.HashMap;
 
public class Tapahtumankuuntelija implements ActionListener {
    // private JButton plus;
    // private JButton miinus;
    // private JButton nollaa;
    private JButton undo;
    // private JTextField tuloskentta;
    // private JTextField syotekentta;
    private Sovelluslogiikka sovellus;

    private Map<JButton, Komento> komennot;
    private Komento edellinen = null;
 
    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        // this.plus = plus;
        // this.miinus = miinus;
        // this.nollaa = nollaa;
        this.undo = undo;
        // this.tuloskentta = tuloskentta;
        // this.syotekentta = syotekentta;
        this.sovellus = new Sovelluslogiikka();
        this.komennot = new HashMap<>();
        this.komennot.put(plus, new Summa(tuloskentta, syotekentta,  nollaa, undo, sovellus) );
        this.komennot.put(miinus, new Erotus(tuloskentta, syotekentta, nollaa, undo, sovellus) );
        this.komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta,  nollaa, undo, sovellus) );
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if ( event.getSource() != undo ) {
            Komento komento = this.komennot.get((JButton)event.getSource());
            komento.suorita();
            this.edellinen = komento;
        } else {
            this.edellinen.peru();
            this.edellinen = null;
        }                  
    }
    /* 
    @Override
    public void actionPerformed(ActionEvent ae) {
        int arvo = 0;
 
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
 
        if (ae.getSource() == plus) {
            sovellus.plus(arvo);
        } else if (ae.getSource() == miinus) {
            sovellus.miinus(arvo);
        } else if (ae.getSource() == nollaa) {
            sovellus.nollaa();
        } else {
            System.out.println("undo pressed");
        }
        
        int laskunTulos = sovellus.tulos();
         
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
        if ( laskunTulos==0) {
            nollaa.setEnabled(false);
        } else {
            nollaa.setEnabled(true);
        }
        undo.setEnabled(true);
    } */
 
}