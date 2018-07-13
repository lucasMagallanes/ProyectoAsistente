package interfaz;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelos.Sala;
import modelos.Usuario;

public class PrincipalFrame extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel nombreUsuarioTextField;
	private JButton btnPerfil;
	private JButton iniciarConversacionBtn;
	private JButton crearSalaBtn;
	private JTable table;
	private final static String COLUMN_SALA = "Sala";
	private final static String COLUMN_NOMBRE = "Nombre";
	private final static String COLUMN_TOPICO = "Tópico";
	private ArrayList<Sala> salas;
	private int idSalas;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	private final static String COLUMN_BOTON = "Botón";
	private Usuario usuario;
	
	DefaultTableModel tableModel;
	private String[] columnNames = {COLUMN_SALA,COLUMN_NOMBRE,COLUMN_TOPICO,COLUMN_BOTON};
	
	public PrincipalFrame(Usuario usuario) {
		this.salas = new ArrayList<Sala>();
		this.salas.add(new Sala(1,null,"La Mejor Materia","Programacion Avanzada"));
		this.salas.add(new Sala(2,null, "La Peor Materia","Analisis de Sistemas"));
		this.idSalas = 3;
		this.usuario = usuario;
		setComponentes();
	}
	
	private void setComponentes() {
		setBounds(100, 100, 700, 450);
		getContentPane().setLayout(null);
		setResizable(false);

		nombreUsuarioTextField = new JLabel();
		nombreUsuarioTextField.setBounds(33, 28, 114, 19);
		getContentPane().add(nombreUsuarioTextField);
		nombreUsuarioTextField.setText(usuario.getAlias());

		btnPerfil = new JButton("Perfil");
		btnPerfil.setBounds(571, 25, 80, 25);
		btnPerfil.addActionListener(e->abrirPerfilFrame());
		getContentPane().add(btnPerfil);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(31, 58, 126, 2);
		getContentPane().add(separator);
		iniciarConversacionBtn = new JButton("Iniciar Conversaci\u00f3n");
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
		
		setTabla();
	}

	private void setTabla() {
//		Object[][] data = { 
//				{new Sala(1,null,"La Mejor Materia","Programacion Avanzada")		,1		,"Uno"	},
//				{new Sala(2,null, "La Peor Materia","Analisis de Sistemas")			,2		,"Dos"	}
//				};
		
		Object[][] matrizSalas = new Object[this.salas.size()][3];
		
		for (int i = 0; i < matrizSalas.length; i++) {
			matrizSalas[i][0] = this.salas.get(i).getId();
			matrizSalas[i][1] = this.salas.get(i).getNombre();
			matrizSalas[i][2] = this.salas.get(i).getTopico();
		}
		
		tableModel= new DefaultTableModel(matrizSalas,columnNames);
		table = new JTable(tableModel);
		table.setBounds(33, 120, 629, 158);
		
		table.getColumnModel().getColumn(3).setCellRenderer(new SalaTableButtonRenderer());
		table.getColumnModel().getColumn(3).setCellEditor(new SalaTableRenderer(new JCheckBox(), this));
		
		table.getColumnModel().getColumn(3).setWidth(70);
		table.getColumnModel().getColumn(3).setMinWidth(70);
		table.getColumnModel().getColumn(3).setMaxWidth(70);
		
		table.getColumnModel().getColumn(0).setWidth(20);
		table.getColumnModel().getColumn(0).setMinWidth(20);
		table.getColumnModel().getColumn(0).setMaxWidth(20);
		
		JScrollPane scroll = new JScrollPane(table);
	    scroll.setBounds(33, 120, 629, 158);
		getContentPane().add(scroll);
	}
	
	public void clik(int salaID) {
//		System.out.println("Se abre sala " + salaID);
		//JOptionPane.showMessageDialog(button, "Click en Sala: "+ salaSeleccionada.toString() );
		JOptionPane.showMessageDialog(null, "Se abre sala " + salaID);
	}

	private void crearSala() {
		CrearSalaFrame crearSalaFrame = new CrearSalaFrame();
		crearSalaFrame.setVisible(true);
	}

	private void abrirPerfilFrame() {
		EditarFrame editar = new EditarFrame(this, usuario);
		editar.setModal(true);
		editar.setVisible(true);
	}
}
