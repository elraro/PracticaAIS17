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
	private JButton acceptButton;
	private JButton newPhoneButton;
	private JButton deletePhoneButton;
	private JButton cancelButton;
	private JList jListPhones;
	private JScrollPane jScrollPanelPhones;
	private JButton modifyPhoneButton;
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JLabel phoneLabel;

	// Telefonos
	private List<Phone> phones;

	// Cambios realizados
	private boolean changes = false;
	private String oldName;
	private List<Phone> oldPhones;
	
	// Icons
	private final Icon HOME_ICON = new ImageIcon(getClass().getClassLoader().getResource("imagenes/casa.png"));
	private final Icon OFFICE_ICON = new ImageIcon(getClass().getClassLoader().getResource("imagenes/oficina.png"));
	private final Icon MOBILE_ICON = new ImageIcon(getClass().getClassLoader().getResource("imagenes/movil.png"));
	private final Icon FAX_ICON = new ImageIcon(getClass().getClassLoader().getResource("imagenes/fax.png"));

	public ModifyContactInterface(ContactsInterface padre, Contact c) {
		super(padre, "Añadir contacto", Dialog.ModalityType.DOCUMENT_MODAL);
		this.phones = new ArrayList<Phone>();
		initComponents();
		setComponents(c);
		// Vamos a copiar los valores actuales por si no se quiere guardar cambios
		oldName = new String(c.getName());
		oldPhones = new ArrayList<Phone>();
		for(Phone f : c.getList()) {
			oldPhones.add(new Phone(new String(f.getPhoneNumber()), f.getType()));
		}
		setVisible(true);
	}

	/**
	 * Inicializar los componentes
	 * 
	 */
	private void initComponents() {
		GridBagConstraints gridBagConstraints;

		nameLabel = new JLabel();
		nameTextField = new JTextField();
		phoneLabel = new JLabel();
		jScrollPanelPhones = new JScrollPane();
		jListPhones = new JList<Phone>();
		newPhoneButton = new JButton();
		modifyPhoneButton = new JButton();
		deletePhoneButton = new JButton();
		acceptButton = new JButton();
		cancelButton = new JButton();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBackground(new Color(255, 255, 255));
		setMaximumSize(new Dimension(500, 450));
		setResizable(false);
		getContentPane().setLayout(new GridBagLayout());

		nameLabel.setForeground(new Color(255, 255, 255));
		nameLabel.setText("Nombre");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(nameLabel, gridBagConstraints);

		nameTextField.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(nameTextField, gridBagConstraints);

		phoneLabel.setForeground(new Color(255, 255, 255));
		phoneLabel.setText("Telefonos");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridheight = 3;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(phoneLabel, gridBagConstraints);

		jScrollPanelPhones.setViewportView(jListPhones);
		jListPhones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jListPhones.setCellRenderer(new CustomCellRender());
		jListPhones.setFocusTraversalPolicyProvider(true);
		refreshList();

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridheight = 3;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(jScrollPanelPhones, gridBagConstraints);

		newPhoneButton.setText("Añadir");
		newPhoneButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				anadirButtonMouseClicked(evt);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(newPhoneButton, gridBagConstraints);

		modifyPhoneButton.setText("Modificar");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(modifyPhoneButton, gridBagConstraints);

		deletePhoneButton.setText("Borrar");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(deletePhoneButton, gridBagConstraints);

		acceptButton.setText("Aceptar");
		acceptButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				acceptButtonMouseClicked(e);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		getContentPane().add(acceptButton, gridBagConstraints);

		cancelButton.setText("Cancelar");
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cancelButtonMouseClicked(e);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 6;
		getContentPane().add(cancelButton, gridBagConstraints);

		pack();
	}
	
	private void setComponents(Contact c) {
		this.nameTextField.setText(c.getName());
		this.phones = c.getList();
		refreshList();
	}

	/**
	 * Método para refrescar la lista de teléfonos
	 */
	private void refreshList() {
		List<JPanel> phonesAux = new ArrayList<JPanel>();

		for (Phone t : this.phones) {
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			switch(t.getType()) {
			case HOME:
				panel.add(new JLabel(t.getPhoneNumber(), HOME_ICON, JLabel.LEFT));
				break;
			case FAX:
				panel.add(new JLabel(t.getPhoneNumber(), FAX_ICON, JLabel.LEFT));
				break;
			case MOBILE:
				panel.add(new JLabel(t.getPhoneNumber(), MOBILE_ICON, JLabel.LEFT));
				break;
			case OFFICE:
				panel.add(new JLabel(t.getPhoneNumber(), OFFICE_ICON, JLabel.LEFT));
				break;
			}
			phonesAux.add(panel);
		}
		this.jListPhones.setListData(phonesAux.toArray());
	}

	private void anadirButtonMouseClicked(MouseEvent e) {
		NewPhoneInterface newPhoneInterface = new NewPhoneInterface(this);
		Phone phone = newPhoneInterface.getPhone();
		if (!phone.getPhoneNumber().equals("")) {
			this.phones.add(phone);
			refreshList();
			this.changes = true;
		}
	}

	private void acceptButtonMouseClicked(MouseEvent e) {
		// TODO guardar
		this.dispose();
	}

	private void cancelButtonMouseClicked(MouseEvent e) {
		if (this.changes) {
			int option = JOptionPane.showConfirmDialog((Component) null, "¿Desea guardar los cambios?","Aviso", JOptionPane.YES_NO_CANCEL_OPTION);
		    switch(option) {
		    case 0:
		    	// Guardamos cambios
		    	this.dispose();
		    	break;
		    case 1:
		    	// Deshacemos cambios
		    	this.nameTextField.setText(this.oldName);
		    	this.phones = this.oldPhones;
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
	
	public Contact getContact() {
		// TODO los distintos tipos de contactos
		return new Contact(this.nameTextField.getText(), this.phones);
	}

}
