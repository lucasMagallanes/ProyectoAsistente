package interfaz;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import dataAccess.DAUsuario;
import modelos.Usuario;

public class EditarFrame extends JDialog {

	private static final long serialVersionUID = 6194239582768689856L;
	private JPanel contentPane;
	private JTextFieldAlfabetico nombreTextField;
	private JTextFieldAlfabetico apellidoTextField;
	private JTextFieldAlfabetico emailtextField;
	private JTextFieldAlfabetico usuarioTextField;
	private JPasswordField contraseniaAntTextField;
	private JButton editarBtn;
	private JButton guardarBtn;
	private JButton cancelarBtn;
	private Usuario usuario;
	private JPasswordField contraseniaNuevatextField;
	private JLabel lblContraseniaAnt;
	private JLabel lblContraseniaNueva;
	private PrincipalFrame pFrame;

	public EditarFrame(PrincipalFrame pFrame, Usuario usuario) {
		this.pFrame = pFrame;
		this.usuario = usuario;
		setComponentes();
		setBotones();
	}

	private void setBotones() {
		
		editarBtn = new JButton("Editar");
		editarBtn.setBounds(124, 389, 117, 25);
		contentPane.add(editarBtn);
		editarBtn.addActionListener(e->setModoEditar());
		
		cancelarBtn = new JButton("Cancelar");
		cancelarBtn.setBounds(382, 389, 117, 25);
		cancelarBtn.addActionListener(e -> this.dispose());
		contentPane.add(cancelarBtn);
		
		guardarBtn = new JButton("Guardar");
		guardarBtn.setEnabled(false);
		guardarBtn.setBounds(253, 389, 117, 25);
		contentPane.add(guardarBtn);
		guardarBtn.addActionListener(e->setModoGuardar());
	}

	private void setComponentes() {
		setTitle("Perfil");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 476);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nombreTextField = new JTextFieldAlfabetico();
		nombreTextField.setBounds(70, 48, 270, 19);
		contentPane.add(nombreTextField);
		// nombreTextField.addKeyListener(e->);
		nombreTextField.setColumns(10);
		nombreTextField.setEnabled(false);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(70, 29, 70, 15);
		contentPane.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(70, 82, 70, 15);
		contentPane.add(lblApellido);

		apellidoTextField = new JTextFieldAlfabetico();
		apellidoTextField.setBounds(69, 101, 271, 19);
		contentPane.add(apellidoTextField);
		apellidoTextField.setColumns(10);
		apellidoTextField.setEnabled(false);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(70, 138, 70, 15);
		contentPane.add(lblEmail);

		emailtextField = new JTextFieldAlfabetico();
		emailtextField.setBounds(70, 159, 270, 19);
		contentPane.add(emailtextField);
		emailtextField.setColumns(10);
		emailtextField.setEnabled(false);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(70, 197, 101, 15);
		contentPane.add(lblUsuario);

		usuarioTextField = new JTextFieldAlfabetico();
		usuarioTextField.setBounds(70, 215, 270, 19);
		contentPane.add(usuarioTextField);
		usuarioTextField.setColumns(10);
		usuarioTextField.setEnabled(false);

		lblContraseniaAnt = new JLabel("Ingrese contraseña anterior");
		lblContraseniaAnt.setBounds(70, 257, 171, 15);
		contentPane.add(lblContraseniaAnt);
		lblContraseniaAnt.setVisible(false);

		contraseniaAntTextField = new JPasswordField();
		contraseniaAntTextField.setBounds(70, 278, 270, 19);
		contentPane.add(contraseniaAntTextField);
		contraseniaAntTextField.setColumns(10);
		contraseniaAntTextField.setVisible(false);
		
		lblContraseniaNueva = new JLabel("Ingrese nueva contraseña");
		lblContraseniaNueva.setBounds(70, 310, 171, 15);
		contentPane.add(lblContraseniaNueva);
		lblContraseniaNueva.setVisible(false);
		
		contraseniaNuevatextField = new JPasswordField();
		contraseniaNuevatextField.setColumns(10);
		contraseniaNuevatextField.setBounds(70, 338, 270, 19);
		contentPane.add(contraseniaNuevatextField);
		contraseniaNuevatextField.setVisible(false);
		
		setearDatosUsuario();
	}
	
	private void setearDatosUsuario() {
		nombreTextField.setText(usuario.getNombre());
		apellidoTextField.setText(usuario.getApellido());
		emailtextField.setText(usuario.getEmail());
		usuarioTextField.setText(usuario.getAlias());
	}
	
	private void setModoEditar() {
		editarBtn.setEnabled(false);
		guardarBtn.setEnabled(true);
		habilitaDeshabilitaControles(true);
	}
	
	private void setModoGuardar() {
		if(validarDatos()) {
			if(contraseniaAntTextField.getText().equals(usuario.getContrasenia())) {
				DAUsuario usuarioDB = new DAUsuario();
				Usuario usuarioActualizado = new Usuario(usuario.getAlias(), nombreTextField.getText(), apellidoTextField.getText(),
						emailtextField.getText(), contraseniaNuevatextField.getText());
				usuarioActualizado.setId(usuario.getId());
				usuarioDB.actualizarUsuario(usuarioActualizado);
				JOptionPane.showMessageDialog(this, "Los datos se actualizaron correctamente.");
				usuario = usuarioActualizado;
				pFrame.setUsuario(usuarioActualizado);
				habilitaDeshabilitaControles(false);
				guardarBtn.setEnabled(false);
				editarBtn.setEnabled(true);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "La contraseña ingresada es incorrecta.");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Faltan completar datos.");
		}
	}
	
	private void habilitaDeshabilitaControles(boolean habilitar) {
		lblContraseniaAnt.setVisible(habilitar);
		contraseniaAntTextField.setVisible(habilitar);
		lblContraseniaNueva.setVisible(habilitar);
		contraseniaNuevatextField.setVisible(habilitar);
		nombreTextField.setEnabled(habilitar);
		apellidoTextField.setEnabled(habilitar);
		emailtextField.setEnabled(habilitar);
	}

	private boolean validarDatos() {
		return (!(nombreTextField.getText().isEmpty() || apellidoTextField.getText().isEmpty() || emailtextField.getText().isEmpty() ||
				contraseniaAntTextField.getText().isEmpty() || contraseniaNuevatextField.getText().isEmpty()));
	}
}
