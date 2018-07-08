package interfaz;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelos.Sala;

public class PrincipalFrame extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField nombreUsuarioTextField;
	private JButton btnPerfil;
	private JButton iniciarConversacionBtn;
	private JButton crearSalaBtn;
	private JTable table;
	private final static String COLUMN_SALA = "Sala";
	private final static String COLUMN_NOMBRE = "Nombre";
	private final static String COLUMN_TOPICO = "Tópico";
	private final static String COLUMN_BOTON = "Botón";
	
	DefaultTableModel tableModel;
	private String[] columnNames = {COLUMN_SALA,COLUMN_NOMBRE,COLUMN_TOPICO,COLUMN_BOTON};
	
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
		
		setTabla();
	}

	private void setTabla() {
		Object[][] data = { {new Sala(1,"Uno","Unoo"),1,"Uno"},
				{new Sala(2,"Dos","Doss"),2,"Dos","Doss"},
				{new Sala(3,"Tres","Tress"),3,"Tres","Tress"}, 
				{new Sala(4,"Cuatro","Cuatroo"),4,"Cuatro","Cuatroo"},
				{new Sala(5,"Cinco","Cincoo"),5,"Cinco","Cincoo"},
				{new Sala(6,"Seis","Seiss"),6,"Seis","Seiss"},
				{new Sala(7,"Siete","Sietee"),7,"Siete","Sietee"}, 
				{new Sala(8,"Ocho","Ochoo"),8,"Ocho","Ochoo"},
				{new Sala(9,"Nueve","Nuevee"),9,"Nueve","Nuevee"}  };
		
		tableModel= new DefaultTableModel(data,columnNames);
		table = new JTable(tableModel);
		table.setBounds(33, 120, 629, 158);
		table.getColumnModel().getColumn(3).setCellRenderer(new SalaTableButtonRenderer());
		table.getColumnModel().getColumn(3).setCellEditor(new SalaTableRenderer(new JCheckBox()));
		table.getColumnModel().getColumn(0).setWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		
		JScrollPane scroll = new JScrollPane(table);
	    scroll.setBounds(33, 120, 629, 158);
		getContentPane().add(scroll);
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
