package interfaz;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EditarFrame extends JDialog {

	private static final long serialVersionUID = 6194239582768689856L;
	private JPanel contentPane;
	private JTextFieldAlfabetico nombreTextField;
	private JTextFieldAlfabetico ApellidoTextField;
	private JTextFieldAlfabetico emailtextField;
	private JTextFieldAlfabetico usuarioTextField;
	private JTextField contraseniaTextField;
	private JButton editarBtn;
	private JButton guardarBtn;
	private JButton cancelarBtn;

	public EditarFrame() {
		setComponentes();
		setBotones();
	}

	private void setBotones() {
		
		editarBtn = new JButton("Editar");
		editarBtn.setBounds(173, 350, 117, 25);
		contentPane.add(editarBtn);
		editarBtn.addActionListener(e->setModoEditar());
		
		cancelarBtn = new JButton("Cancelar");
		cancelarBtn.setBounds(381, 350, 117, 25);
		cancelarBtn.addActionListener(e -> this.dispose());
		contentPane.add(cancelarBtn);
	}

	private void setComponentes() {
		setTitle("Perfil");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nombreTextField = new JTextFieldAlfabetico();
		nombreTextField.setBounds(70, 48, 270, 19);
		contentPane.add(nombreTextField);
		// nombreTextField.addKeyListener(e->);
		nombreTextField.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(70, 29, 70, 15);
		contentPane.add(lblNombre);

		JLabel lblNewLabel = new JLabel("Apellido");
		lblNewLabel.setBounds(70, 82, 70, 15);
		contentPane.add(lblNewLabel);

		ApellidoTextField = new JTextFieldAlfabetico();
		ApellidoTextField.setBounds(69, 101, 271, 19);
		contentPane.add(ApellidoTextField);
		ApellidoTextField.setColumns(10);

		JLabel lblUsuario = new JLabel("Email");
		lblUsuario.setBounds(70, 138, 70, 15);
		contentPane.add(lblUsuario);

		emailtextField = new JTextFieldAlfabetico();
		emailtextField.setBounds(70, 159, 270, 19);
		contentPane.add(emailtextField);
		emailtextField.setColumns(10);

		JLabel lblContrasea = new JLabel("Usuario");
		lblContrasea.setBounds(70, 197, 101, 15);
		contentPane.add(lblContrasea);

		usuarioTextField = new JTextFieldAlfabetico();
		usuarioTextField.setBounds(70, 215, 270, 19);
		contentPane.add(usuarioTextField);
		usuarioTextField.setColumns(10);

		JLabel lblEmail = new JLabel("Contrase\u00f1a");
		lblEmail.setBounds(70, 257, 101, 15);
		contentPane.add(lblEmail);

		contraseniaTextField = new JTextField();
		contraseniaTextField.setBounds(70, 278, 270, 19);
		contentPane.add(contraseniaTextField);
		contraseniaTextField.setColumns(10);
	}
	
	private void setModoEditar() {
		contentPane.remove(editarBtn);
		guardarBtn = new JButton("Guardar");
		guardarBtn.setBounds(173, 350, 117, 25);
		guardarBtn.addActionListener(e->actualizarDatos());
		contentPane.add(guardarBtn);
	}

	private void actualizarDatos() {
		if(validarDatos()) {
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(this, "Faltan completar datos.");
		}
	}

	private boolean validarDatos() {
		return true;
	}
}
