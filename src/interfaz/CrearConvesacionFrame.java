package interfaz;

import java.awt.List;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class CrearConvesacionFrame extends JDialog {

	private static final long serialVersionUID = 1L;


	public static void main(String[] args) {
		try {
			CrearConvesacionFrame dialog = new CrearConvesacionFrame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearConvesacionFrame() {
		setBounds(100, 100, 700, 450);
		getContentPane().setLayout(null);
		
		List list = new List();
		list.setBounds(47, 73, 613, 267);
		getContentPane().add(list);
		
		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(47, 41, 70, 15);
		getContentPane().add(lblUsuarios);
	}
}
