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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import contacts.Contact;
import contacts.ContactApplication;
import contacts.ContactsLogic;
import material.MaterialUIConfig;

/**
 * Interfaz de la agenda
 * 
 * @author Alberto de Dios Bernáez
 */
public class ContactsInterface extends JFrame {

	private static final long serialVersionUID = 1L;

	// Interface
	private JButton removecontactButton;
	private JButton viewContactButton;
	private JButton exportCSVButton;
	private JButton importCSVButton;
	private JScrollPane jScrollPanelContact;
	private JList jListContacts;
	private JLabel logoURJC;
	private JButton modifyContactButton;
	private JButton newContactButton;
	private JTextField searchTextField;

	// Logic of the app
	private ContactsLogic logicContacts;

	// Image
	private final Icon CONTACT_ICON = new ImageIcon(getClass().getClassLoader().getResource("images/contact.png"));
	private final ImageIcon FAVICON = new ImageIcon(getClass().getClassLoader().getResource("images/logo_icon.png"));

	/**
	 * Constructor
	 * 
	 * @param logicContacts
	 *            La lógica de la aplicación
	 * 
	 */
	public ContactsInterface(ContactsLogic logicContacts) {
		this.logicContacts = logicContacts;
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

		viewContactButton = new JButton();
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
		setTitle(ContactApplication.language.getProperty("ContactsTitle"));
		setBackground(new Color(0, 0, 0));
		setMaximumSize(new Dimension(426, 238));
		setMinimumSize(new Dimension(426, 238));
		setResizable(false);
		getContentPane().setLayout(new GridBagLayout());
		
		viewContactButton.setText(ContactApplication.language.getProperty("ViewContact"));
		viewContactButton.setEnabled(false);
		viewContactButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewContactActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(viewContactButton, gridBagConstraints);

		newContactButton.setText(ContactApplication.language.getProperty("NewContact"));
		newContactButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newContactActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(newContactButton, gridBagConstraints);

		modifyContactButton.setText(ContactApplication.language.getProperty("ModifyContact"));
		modifyContactButton.setEnabled(false);
		modifyContactButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modifyContactActionPerformed(e);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
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

		removecontactButton.setText(ContactApplication.language.getProperty("DeleteContact"));
		removecontactButton.setEnabled(false);
		removecontactButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteContactActionPerformed(e);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(removecontactButton, gridBagConstraints);

		searchTextField.setBackground(new java.awt.Color(74, 74, 74));
		searchTextField.setForeground(new java.awt.Color(255, 255, 255));
		searchTextField.setText(ContactApplication.language.getProperty("Search2"));
		searchTextField.setBorder(BorderFactory.createTitledBorder(null,
				ContactApplication.language.getProperty("Search"), TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 12), new Color(255, 255, 255)));
		searchTextField.setSelectedTextColor(new Color(255, 255, 255));
		searchTextField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				searchFieldFocusLost();
			}
		});
		searchTextField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				searchFieldActionPerformed();
			}
		});
		searchTextField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				search();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				search();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				search();
			}

		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(searchTextField, gridBagConstraints);

		importCSVButton.setText(ContactApplication.language.getProperty("ImportCSV"));
		importCSVButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importButtonActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(importCSVButton, gridBagConstraints);

		logoURJC.setIcon(new ImageIcon(getClass().getResource("/images/logo.png")));
		getContentPane().add(logoURJC, new GridBagConstraints());

		exportCSVButton.setText(ContactApplication.language.getProperty("ExportCSV"));
		exportCSVButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportButtonActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(exportCSVButton, gridBagConstraints);

		getContentPane().setBackground(new Color(74, 74, 74));

		this.setIconImage(FAVICON.getImage());

		pack();
	}

	private void search() {
		if (this.searchTextField.getText().equals(ContactApplication.language.getProperty("Search2"))) {
			refreshList();
		} else {
			TreeSet<Contact> search = this.logicContacts.search(this.searchTextField.getText());
			refreshListSearch(search);
		}
	}

	private void searchFieldActionPerformed() {
		if (searchTextField.getText().equals(ContactApplication.language.getProperty("Search2"))) {
			searchTextField.setText("");
		}
	}

	private void searchFieldFocusLost() {
		if (searchTextField.getText().equals("")) {
			searchTextField.setText(ContactApplication.language.getProperty("Search2"));
		}
	}

	private void listContactsMouseClicked() {
		viewContactButton.setEnabled(true);
		modifyContactButton.setEnabled(true);
		removecontactButton.setEnabled(true);
	}

	private void listContactsFocusLost() {
		viewContactButton.setEnabled(false);
		modifyContactButton.setEnabled(false);
		removecontactButton.setEnabled(false);
	}
	
	private void viewContactActionPerformed() {
		JPanel panel = (JPanel) this.jListContacts.getSelectedValue();
		JLabel label = (JLabel) panel.getComponent(0);
		Contact contact = this.logicContacts.getContact(label.getText());
		ViewContactInterface viewContactInterface = new ViewContactInterface(this, contact, logicContacts);
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
		label = new JLabel(ContactApplication.language.getProperty("DeleteContactWarning"));
		label.setForeground(Color.WHITE);
		int option = JOptionPane.showConfirmDialog(null, label, ContactApplication.language.getProperty("Warning"),
				JOptionPane.YES_NO_OPTION);
		switch (option) {
		case 0:
			// Si, borrar el contacto
			this.logicContacts.removeContact(contact);
			search();
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
		search();
		this.jListContacts.setSelectedIndex(this.logicContacts.getIndexContact(modifiedContact));
		this.logicContacts.save();
	}

	private void importButtonActionPerformed() {
		final JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("csv files", "csv");
		fc.setFileFilter(filter);
		int option = fc.showOpenDialog(this);
		if (option == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			System.out.println("Abriendo: " + file.getName() + ".");
			try {
				this.logicContacts.importCsv(file.getAbsolutePath());
				refreshList();
				this.logicContacts.save();
			} catch (IOException e) {
				JLabel label = new JLabel(
						ContactApplication.language.getProperty("CSVError"));
				label.setForeground(Color.WHITE);
				JOptionPane.showMessageDialog(this, label, ContactApplication.language.getProperty("Warning"), JOptionPane.WARNING_MESSAGE);
			}
		} else {
			System.out.println("Cancelado");
		}
	}

	private void exportButtonActionPerformed() {
		final JFileChooser fc = new JFileChooser();
		fc.setDialogType(JFileChooser.SAVE_DIALOG);
		fc.setSelectedFile(new File("contacts.csv"));
		fc.setFileFilter(new FileNameExtensionFilter("csv files", "csv"));
		int option = fc.showOpenDialog(this);
		if (option == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			System.out.println("Abriendo: " + file.getName() + ".");
			try {
				this.logicContacts.exportCsv(file.getAbsolutePath());
				// TODO
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Cancelado");
		}
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

	private void refreshListSearch(TreeSet<Contact> search) {
		List<JPanel> listcontactAux = new ArrayList<JPanel>();
		for (Contact c : search) {
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			panel.add(new JLabel(c.getName(), CONTACT_ICON, JLabel.LEFT));
			listcontactAux.add(panel);
		}
		this.jListContacts.setListData(listcontactAux.toArray());
	}
}
