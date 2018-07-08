package interfaz;

import java.awt.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class PrincipalFrame extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField nombreUsuarioTextField;
	private JButton btnPerfil;
	private JButton iniciarConversacionBtn;
	private JButton crearSalaBtn;
	
	public PrincipalFrame() {
		setBounds(100, 100, 700, 450);
		getContentPane().setLayout(null);

		nombreUsuarioTextField = new JTextField();
		nombreUsuarioTextField.setBounds(33, 28, 114, 19);
		getContentPane().add(nombreUsuarioTextField);
		nombreUsuarioTextField.setColumns(10);

		btnPerfil = new JButton("Perfil");
		btnPerfil.setBounds(571, 25, 80, 25);
		btnPerfil.addActionListener(e->abrirPerfilFrame());
		getContentPane().add(btnPerfil);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(31, 58, 126, 2);
		getContentPane().add(separator);
		
		List list = new List();
		list.setBounds(38, 117, 613, 164);
		getContentPane().add(list);
		
		iniciarConversacionBtn = new JButton("Iniciar Conversación");
		iniciarConversacionBtn.setBounds(38, 356, 186, 25);
		getContentPane().add(iniciarConversacionBtn);
		
		crearSalaBtn = new JButton("CREAR SALA");
		crearSalaBtn.addActionListener(e->crearSala());
		crearSalaBtn.setBounds(38, 301, 119, 25);
		getContentPane().add(crearSalaBtn);
		
		JLabel lblSalas = new JLabel("Salas");
		lblSalas.setBounds(38, 93, 70, 15);
		getContentPane().add(lblSalas);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(38, 338, 186, 2);
		getContentPane().add(separator_1);
	}

	private void crearSala() {
		CrearSalaFrame crearSalaFrame = new CrearSalaFrame();
		crearSalaFrame.setVisible(true);
	}

	private void abrirPerfilFrame() {
		EditarFrame editar = new EditarFrame();
		editar.setVisible(true);
	}
}
