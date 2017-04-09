package interfac;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

import contacts.Contact;
import contacts.Phone;

/**
 *
 * @author Alberto de Dios Bernáez
 */
public class ModifyContactInterface extends JDialog {

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
	private List<Phone> telefonos;

	// Cambios realizados
	private boolean cambios = false;
	private String viejoNombre;
	private List<Phone> viejosTelefonos;

	public ModifyContactInterface(ContactsInterface padre, Contact c) {
		super(padre, "Añadir contacto", Dialog.ModalityType.DOCUMENT_MODAL);
		this.telefonos = new ArrayList<Phone>();
		initComponents();
		setComponents(c);
		// Vamos a copiar los valores actuales por si no se quiere guardar cambios
		viejoNombre = new String(c.getName());
		viejosTelefonos = new ArrayList<Phone>();
		for(Phone f : c.getList()) {
			viejosTelefonos.add(new Phone(new String(f.getPhoneNumber()), f.getType()));
		}
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
		listaTelefonos = new JList<Phone>();
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
	
	private void setComponents(Contact c) {
		this.campoNombre.setText(c.getName());
		this.telefonos = c.getList();
		refrescarLista();
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

		for (Phone t : this.telefonos) {
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			switch(t.getType()) {
			case HOME:
				panel.add(new JLabel(t.getPhoneNumber(), casaImagen, JLabel.LEFT));
				break;
			case FAX:
				panel.add(new JLabel(t.getPhoneNumber(), faxImagen, JLabel.LEFT));
				break;
			case MOBILE:
				panel.add(new JLabel(t.getPhoneNumber(), movilImagen, JLabel.LEFT));
				break;
			case OFFICE:
				panel.add(new JLabel(t.getPhoneNumber(), oficinaImagen, JLabel.LEFT));
				break;
			}
			telefonosAux.add(panel);
		}
		this.listaTelefonos.setListData(telefonosAux.toArray());
	}

	private void anadirButtonMouseClicked(MouseEvent e) {
		NewPhoneInterface nuevoTelefono = new NewPhoneInterface(this);
		Phone telefono = nuevoTelefono.getTelefono();
		System.out.println(telefono.toString()); // Debug
		if (!telefono.getPhoneNumber().equals("")) {
			this.telefonos.add(telefono);
			refrescarLista();
			this.cambios = true;
		}
	}

	private void aceptarButtonMouseClicked(MouseEvent e) {
		// TODO guardar
		this.dispose();
	}

	private void cancelarButtonMouseClicked(MouseEvent e) {
		if (this.cambios) {
			int opcion = JOptionPane.showConfirmDialog((Component) null, "¿Desea guardar los cambios?","Aviso", JOptionPane.YES_NO_CANCEL_OPTION);
		    switch(opcion) {
		    case 0:
		    	// Guardamos cambios
		    	this.dispose();
		    	break;
		    case 1:
		    	// Deshacemos cambios
		    	this.campoNombre.setText(this.viejoNombre);
		    	this.telefonos = this.viejosTelefonos;
		    	this.dispose();
		    	break;
		    case 2:
		    	// No hacemos nada, opcion Cancelar
		    	break;
		    }
		} else {
			this.dispose();
		}
	}
	
	public Contact getContacto() {
		// TODO los distintos tipos de contactos
		return new Contact(this.campoNombre.getText(), this.telefonos);
	}

}
