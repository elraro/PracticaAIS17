package agenda;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
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
	// Logica
	private AgendaLogica logica;

	public NuevoContacto(AgendaInterfaz padre, AgendaLogica logica) {
		super(padre, "Añadir contacto", Dialog.ModalityType.DOCUMENT_MODAL);
		this.telefonos = new ArrayList<Telefono>();
		this.logica = logica;
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
		listaTelefonos = new JList() {
			@Override
			public int locationToIndex(Point location) {
				int index = super.locationToIndex(location);
				if (index != -1 && !getCellBounds(index, index).contains(location)) {
					return -1;
				} else {
					return index;
				}
			}
		};
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
		listaTelefonos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JList list = (JList) e.getSource();
				if (list.locationToIndex(e.getPoint()) == -1 && !e.isShiftDown() && !isMenuShortcutKeyDown(e)) {
					list.clearSelection();
					listPhonesFocusLost();
				} else {
					listPhonesMouseClicked();
				}
			}

			private boolean isMenuShortcutKeyDown(InputEvent event) {
				return (event.getModifiers() & Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()) != 0;
			}
		});
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
		botonModificarTelefono.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				modificarButtonMouseClicked();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(botonModificarTelefono, gridBagConstraints);

		botonBorrarTelefono.setText("Borrar");
		botonBorrarTelefono.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				borrarButtonMouseClicked();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(botonBorrarTelefono, gridBagConstraints);

		botonAceptar.setText("Aceptar");
		botonAceptar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				aceptarButtonMouseClicked();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		getContentPane().add(botonAceptar, gridBagConstraints);

		botonCancelar.setText("Cancelar");
		botonCancelar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cancelarButtonMouseClicked();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 6;
		getContentPane().add(botonCancelar, gridBagConstraints);

		listPhonesFocusLost();
		
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
			switch (t.getTipo()) {
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
	

	private void listPhonesMouseClicked() {
		botonModificarTelefono.setEnabled(true);
		botonBorrarTelefono.setEnabled(true);
	}

	private void listPhonesFocusLost() {
		botonModificarTelefono.setEnabled(false);
		botonBorrarTelefono.setEnabled(false);
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
	
	private void modificarButtonMouseClicked() {
		
	}
	
	private void borrarButtonMouseClicked() {
		JLabel labelPhone = ((JLabel)((JPanel)this.listaTelefonos.getSelectedValue()).getComponent(0));
		Telefono phone = findPhone(labelPhone.getText(), labelPhone.getIcon());
				// No puedo hacer eso porque cómo se que ese icono es el tipo de telefono que estoy buscando?
		int option = JOptionPane.showConfirmDialog((Component) null, "¿Desea borrar el contacto seleccionado?","Aviso", JOptionPane.YES_NO_OPTION);
	    switch(option) {
	    case 0:
	    	// Si, borrar el contacto
	    	this.agendaLogica.quitarContacto(contacto);
	    	refrescarLista();
	    	this.listaContactos.clearSelection(); // Si lo borramos lo deseleccionamos, para evitar puntero a null
	    	this.listContactsFocusLost();
			this.agendaLogica.guardar();
	    	break;
	    case 1:
	    	// No borrar el contacto
	    	break;
	    }
	}

	private void aceptarButtonMouseClicked() {
		guardar();
	}

	private void cancelarButtonMouseClicked() {
		if (this.cambios || !this.campoNombre.getText().equals("")) {
			int opcion = JOptionPane.showConfirmDialog((Component) null, "¿Desea guardar los cambios?", "Aviso",
					JOptionPane.YES_NO_CANCEL_OPTION);
			switch (opcion) {
			case 0:
				// Guardamos cambios
				guardar();
				break;
			case 1:
				// Deshacemos cambios
				this.campoNombre.setText("");
				this.telefonos.clear();
				break;
			case 2:
				// No hacemos nada, opcion Cancelar
				break;
			}
		} else {
			this.dispose();
		}
	}

	/**
	 * Comprobacion de guardar
	 */
	private void guardar() {
		if (this.logica.getContacto(this.campoNombre.getText()) != null) {
			// Ya existe contacto con ese nombre
			JOptionPane.showMessageDialog(this, "Ya existe un contacto con ese nombre. No es posible guardar contactos con el nombre repetido.", "Aviso", JOptionPane.WARNING_MESSAGE);
		} else {
			this.dispose();
		}
	}

	public Contacto getContacto() {
		return new Contacto(this.campoNombre.getText(), this.telefonos);
	}

}
