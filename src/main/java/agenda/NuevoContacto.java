package agenda;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

/**
 *
 * @author Alberto de Dios Bernáez
 */
public class NuevoContacto extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton botonAceptar;
	private JButton botonAnadirTelefono;
	private JButton botonBorrarTelefono;
	private JButton botonCancelar;
	private JList listaTelefonos;
	private JScrollPane jScrollPane1;
	private JButton botonModificarTelefono;
	private JLabel etiquetaNombre;
	private JTextField campoNombre;
	private JLabel etiquetaTelefonos;

	// Telefonos
	private List<Telefono> telefonos;
	// Cambios realizados
	private boolean cambios = false;

	public NuevoContacto(AgendaInterfaz padre) {
		super(padre, "Añadir contacto", Dialog.ModalityType.DOCUMENT_MODAL);
		this.telefonos = new ArrayList<Telefono>();
		initComponents();
		setVisible(true);
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
		listaTelefonos = new JList<Telefono>();
		botonAnadirTelefono = new JButton();
		botonModificarTelefono = new JButton();
		botonBorrarTelefono = new JButton();
		botonAceptar = new JButton();
		botonCancelar = new JButton();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
		listaTelefonos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaTelefonos.setCellRenderer(new CustomCellRender());
		listaTelefonos.setFocusTraversalPolicyProvider(true);
		refrescarLista();

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
		botonAceptar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				aceptarButtonMouseClicked(e);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		getContentPane().add(botonAceptar, gridBagConstraints);

		botonCancelar.setText("Cancelar");
		botonCancelar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cancelarButtonMouseClicked(e);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 6;
		getContentPane().add(botonCancelar, gridBagConstraints);

		pack();
	}

	/**
	 * Método para refrescar la lista de teléfonos
	 */
	private void refrescarLista() {
		List<JPanel> telefonosAux = new ArrayList<JPanel>();

		Icon casaImagen = new ImageIcon(getClass().getClassLoader().getResource("imagenes/casa.png"));
		Icon oficinaImagen = new ImageIcon(getClass().getClassLoader().getResource("imagenes/oficina.png"));
		Icon movilImagen = new ImageIcon(getClass().getClassLoader().getResource("imagenes/movil.png"));
		Icon faxImagen = new ImageIcon(getClass().getClassLoader().getResource("imagenes/fax.png"));

		for (Telefono t : this.telefonos) {
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			switch(t.getTipo()) {
			case CASA:
				panel.add(new JLabel(t.getNumero(), casaImagen, JLabel.LEFT));
				break;
			case FAX:
				panel.add(new JLabel(t.getNumero(), faxImagen, JLabel.LEFT));
				break;
			case MOVIL:
				panel.add(new JLabel(t.getNumero(), movilImagen, JLabel.LEFT));
				break;
			case OFICINA:
				panel.add(new JLabel(t.getNumero(), oficinaImagen, JLabel.LEFT));
				break;
			}
			telefonosAux.add(panel);
		}
		this.listaTelefonos.setListData(telefonosAux.toArray());
	}

	private void anadirButtonMouseClicked(MouseEvent e) {
		NuevoTelefono nuevoTelefono = new NuevoTelefono(this);
		Telefono telefono = nuevoTelefono.getTelefono();
		System.out.println(telefono.toString()); // Debug
		if (!telefono.getNumero().equals("")) {
			this.telefonos.add(telefono);
			refrescarLista();
		}
	}

	private void aceptarButtonMouseClicked(MouseEvent e) {
		// TODO guardar
		this.dispose();
	}

	private void cancelarButtonMouseClicked(MouseEvent e) {
		if (this.cambios) {
			// TODO se han realizado cambios, preguntar con mensaje
		} else {
			this.dispose();
		}
	}
	
	public Contacto getContacto() {
		// TODO los distintos tipos de contactos
		return new Contacto(this.campoNombre.getText(), this.telefonos);
	}

}
