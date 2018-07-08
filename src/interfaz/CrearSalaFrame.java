package interfaz;

import java.awt.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CrearSalaFrame extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnCrear;

	public CrearSalaFrame() {
		setTitle("Sala");
		setBounds(100, 100, 700, 450);
		getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(101, 62, 337, 19);
		getContentPane().add(textField);
		textField.setColumns(10);

		JRadioButton rdbtnPrivada = new JRadioButton("Privada");
		rdbtnPrivada.setBounds(101, 168, 149, 23);
		getContentPane().add(rdbtnPrivada);

		textField_1 = new JTextField();
		textField_1.setBounds(101, 123, 338, 19);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		List list = new List();
		list.setBounds(101, 237, 457, 92);
		getContentPane().add(list);

		btnCrear = new JButton("Crear");
		btnCrear.setBounds(226, 372, 117, 25);
		btnCrear.addActionListener(e->crearSala());
		getContentPane().add(btnCrear);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(355, 372, 117, 25);
		btnCancelar.addActionListener(e -> this.dispose());
		getContentPane().add(btnCancelar);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(105, 43, 70, 15);
		getContentPane().add(lblNombre);

		JLabel lblTpico = new JLabel("Tópico");
		lblTpico.setBounds(101, 103, 70, 15);
		getContentPane().add(lblTpico);

		JLabel lblInvitarUsuarios = new JLabel("Invitar Usuarios");
		lblInvitarUsuarios.setBounds(105, 216, 117, 15);
		getContentPane().add(lblInvitarUsuarios);
	}

	private void crearSala() {
		JOptionPane.showMessageDialog(this, "Se ha creado la Sala");

	}
}
