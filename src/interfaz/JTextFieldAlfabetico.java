package interfaz;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class JTextFieldAlfabetico extends JTextField{

    private static final long serialVersionUID = 1L;
    
    @Override
    public void processKeyEvent(KeyEvent ev) {
        if (!(Character.getNumericValue(ev.getKeyChar())>0 && Character.getNumericValue(ev.getKeyChar())<9)) {
            super.processKeyEvent(ev);
        }
        ev.consume();
        return;
    }

}
