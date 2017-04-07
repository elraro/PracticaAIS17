package agenda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author Alberto de Dios Bernáez
 */
public class NuevoContacto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton botonAceptar;
	private JButton botonAnadirTelefono;
	private JButton botonBorrarTelefono;
	private JButton botonCancelar;
	private JList<String> listaTelefonos;
	private JScrollPane jScrollPane1;
	private JButton botonModificarTelefono;
	private JLabel etiquetaNombre;
	private JTextField campoNombre;
	private JLabel etiquetaTelefonos;


	public NuevoContacto() {
		initComponents();
	}

	/**
	 * Inicializar los componentes
	 * 
	 */
	private void initComponents() {
		GridBagConstraints gridBagConstraints;

		etiquetaNombre = new JLabel();
		campoNombre = new JTextField();
		etiquetaTelefonos = new JLabel();
		jScrollPane1 = new JScrollPane();
		listaTelefonos = new JList<>();
		botonAnadirTelefono = new JButton();
		botonModificarTelefono = new JButton();
		botonBorrarTelefono = new JButton();
		botonAceptar = new JButton();
		botonCancelar = new JButton();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Añadir Contacto");
		setBackground(new Color(255, 255, 255));
		setMaximumSize(new Dimension(500, 450));
		setResizable(false);
		getContentPane().setLayout(new GridBagLayout());

		etiquetaNombre.setForeground(new Color(255, 255, 255));
		etiquetaNombre.setText("Nombre");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(etiquetaNombre, gridBagConstraints);

		campoNombre.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(campoNombre, gridBagConstraints);

		etiquetaTelefonos.setForeground(new Color(255, 255, 255));
		etiquetaTelefonos.setText("Teléfonos");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridheight = 3;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(etiquetaTelefonos, gridBagConstraints);

		jScrollPane1.setViewportView(listaTelefonos);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridheight = 3;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(jScrollPane1, gridBagConstraints);

		botonAnadirTelefono.setText("Añadir");
		botonAnadirTelefono.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				anadirButtonMouseClicked(evt);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(botonAnadirTelefono, gridBagConstraints);

		botonModificarTelefono.setText("Modificar");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(botonModificarTelefono, gridBagConstraints);

		botonBorrarTelefono.setText("Borrar");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(botonBorrarTelefono, gridBagConstraints);

		botonAceptar.setText("Aceptar");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		getContentPane().add(botonAceptar, gridBagConstraints);

		botonCancelar.setText("Cancelar");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 6;
		getContentPane().add(botonCancelar, gridBagConstraints);

		pack();
	}

	private void anadirButtonMouseClicked(MouseEvent evt) {
		NuevoTelefono nuevoTelefono = new NuevoTelefono();
		nuevoTelefono.setVisible(true);
	}
}
