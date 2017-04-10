package interfac;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import contacts.Contact;
import contacts.ContactsLogic;
import material.MaterialUIConfig;

/**
 * Interfaz de la agenda
 * 
 * @author Alberto de Dios Bernáez
 */
public class ContactsInterface extends JFrame {

	private static final long serialVersionUID = 1L;

	// Interfaz
	private JButton removecontactButton;
	private JButton exportCSVButton;
	private JButton importCSVButton;
	private JScrollPane jScrollPanelContact;
	private JList jListContacts;
	private JLabel logoURJC;
	private JButton modifyContactButton;
	private JButton newContactButton;
	private JTextField searchTextField;

	// Lógica de la aplicacion
	private ContactsLogic logicContacts;

	// Image
	private final Icon CONTACT_ICON = new ImageIcon(getClass().getClassLoader().getResource("imagenes/contacto.png"));

	/**
	 * Constructor
	 * 
	 * @param logicContacts
	 *            La lógica de la aplicación
	 * 
	 */
	public ContactsInterface(ContactsLogic logicContacts) {
		this.logicContacts = logicContacts;
		// TODO
		// Que se cargue el fichero de la agenda, o si no existe, crearlo
		// añadir esos contactos a la lista
		// FIN TODO
		MaterialUIConfig.configureUI();
		initComponents();
		refreshList();
		this.setVisible(true);
	}

	/**
	 * Inicializar los componentes
	 * 
	 */
	private void initComponents() {
		GridBagConstraints gridBagConstraints;

		newContactButton = new JButton();
		modifyContactButton = new JButton();
		jScrollPanelContact = new JScrollPane();
		jListContacts = new JList() {
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
		removecontactButton = new JButton();
		searchTextField = new JTextField();
		importCSVButton = new JButton();
		logoURJC = new JLabel();
		exportCSVButton = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Agenda telefónica");
		setBackground(new Color(0, 0, 0));
		setMaximumSize(new Dimension(426, 238));
		setMinimumSize(new Dimension(426, 238));
		setResizable(false);
		getContentPane().setLayout(new GridBagLayout());

		newContactButton.setText("Nuevo contacto");
		newContactButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newContactActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(newContactButton, gridBagConstraints);

		modifyContactButton.setText("Modificar contacto");
		modifyContactButton.setEnabled(false);
		modifyContactButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modifyContactActionPerformed(e);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(modifyContactButton, gridBagConstraints);

		jListContacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jListContacts.setCellRenderer(new CustomCellRender());
		jListContacts.setFocusTraversalPolicyProvider(true);
		jListContacts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JList list = (JList) e.getSource();
				if (list.locationToIndex(e.getPoint()) == -1 && !e.isShiftDown() && !isMenuShortcutKeyDown(e)) {
					list.clearSelection();
					listContactsFocusLost();
				} else {
					listContactsMouseClicked();
				}
			}

			private boolean isMenuShortcutKeyDown(InputEvent event) {
				return (event.getModifiers() & Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()) != 0;
			}
		});
		jScrollPanelContact.setViewportView(jListContacts);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 9;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(jScrollPanelContact, gridBagConstraints);

		removecontactButton.setText("Borrar contacto");
		removecontactButton.setEnabled(false);
		removecontactButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteContactActionPerformed(e);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(removecontactButton, gridBagConstraints);

		searchTextField.setBackground(new java.awt.Color(74, 74, 74));
		searchTextField.setForeground(new java.awt.Color(255, 255, 255));
		searchTextField.setText("Buscar...");
		searchTextField.setToolTipText("Busca en la agenda");
		searchTextField.setBorder(BorderFactory.createTitledBorder(null, "Buscar", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 12), new Color(255, 255, 255)));
		searchTextField.setSelectedTextColor(new Color(255, 255, 255));
		searchTextField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				searchFieldFocusLost(evt);
			}
		});
		searchTextField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				searchFieldActionPerformed(e);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(searchTextField, gridBagConstraints);

		importCSVButton.setText("Importar CSV");
		importCSVButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				importButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(importCSVButton, gridBagConstraints);

		logoURJC.setIcon(new ImageIcon(getClass().getResource("/imagenes/logo.png")));
		getContentPane().add(logoURJC, new GridBagConstraints());

		exportCSVButton.setText("Exportar CSV");
		exportCSVButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				exportButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(exportCSVButton, gridBagConstraints);

		getContentPane().setBackground(new Color(74, 74, 74));

		pack();
	}

	private void searchFieldActionPerformed(MouseEvent e) {
		if (searchTextField.getText().equals("Buscar...")) {
			searchTextField.setText("");
		}
	}

	private void searchFieldFocusLost(FocusEvent evt) {
		if (searchTextField.getText().equals("")) {
			searchTextField.setText("Buscar...");
		}
	}

	private void listContactsMouseClicked() {
		modifyContactButton.setEnabled(true);
		removecontactButton.setEnabled(true);
	}

	private void listContactsFocusLost() {
		modifyContactButton.setEnabled(false);
		removecontactButton.setEnabled(false);
	}

	private void newContactActionPerformed() {
		NewContactInterface newContactInterface = new NewContactInterface(this, this.logicContacts);
		Contact contact = newContactInterface.getContact();
		if (!contact.getName().equals("") || contact.getList().size() != 0) {
			this.logicContacts.addContact(contact);
			refreshList();
			this.logicContacts.save();
		}
	}

	private void deleteContactActionPerformed(ActionEvent evt) {
		JPanel panel = (JPanel) this.jListContacts.getSelectedValue();
		JLabel label = (JLabel) panel.getComponent(0);
		Contact contact = this.logicContacts.getContact(label.getText());
		label = new JLabel("¿Desea borrar el contacto seleccionado?");
		label.setForeground(Color.WHITE);
		int option = JOptionPane.showConfirmDialog(null, label, "Aviso", JOptionPane.YES_NO_OPTION);
		switch (option) {
		case 0:
			// Si, borrar el contacto
			this.logicContacts.removeContact(contact);
			refreshList();
			this.jListContacts.clearSelection();
			this.listContactsFocusLost();
			this.logicContacts.save();
			break;
		case 1:
			// No borrar el contacto
			break;
		}
	}

	private void modifyContactActionPerformed(ActionEvent evt) {
		JPanel panel = (JPanel) this.jListContacts.getSelectedValue();
		JLabel label = (JLabel) panel.getComponent(0);
		Contact contact = this.logicContacts.getContact(label.getText());
		ModifyContactInterface modifyContactInterface = new ModifyContactInterface(this, contact, logicContacts);
		Contact modifiedContact = modifyContactInterface.getContact();
		this.logicContacts.removeContact(contact);
		this.logicContacts.addContact(modifiedContact);
		refreshList();
		this.jListContacts.setSelectedIndex(this.logicContacts.getIndexContact(modifiedContact));
		this.logicContacts.save();
	}

	private void importButtonActionPerformed(ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void exportButtonActionPerformed(ActionEvent evt) {
		// TODO add your handling code here:
	}

	/**
	 * Método para refrescar la lista de contactos
	 */
	private void refreshList() {
		List<JPanel> listcontactAux = new ArrayList<JPanel>();
		for (Contact c : this.logicContacts.contactList()) {
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			panel.add(new JLabel(c.getName(), CONTACT_ICON, JLabel.LEFT));
			listcontactAux.add(panel);
		}
		this.jListContacts.setListData(listcontactAux.toArray());
	}
}
