package interfac;

import java.awt.Color;
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
import java.util.Collections;
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
import contacts.ContactApplication;
import contacts.ContactsLogic;
import contacts.Phone;
import contacts.TypePhone;

/**
 *
 * @author Alberto de Dios Bernáez
 */
public class NewContactInterface extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton acceptButton;
	private JButton addPhoneButton;
	private JButton removePhoneButton;
	private JButton cancelButton;
	private JList jListPhones;
	private JScrollPane jScrollPanelPhones;
	private JButton modifyPhoneButton;
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel phonesLabel;

	// List of phones
	private List<Phone> listPhones;
	// Any changes?
	private boolean changes = false;
	// Logic of the contact app
	private ContactsLogic logicContacts;
	// Icons
	private final Icon HOME_ICON = new ImageIcon(getClass().getClassLoader().getResource("images/home.png"));
	private final Icon OFFICE_ICON = new ImageIcon(getClass().getClassLoader().getResource("images/office.png"));
	private final Icon MOBILE_ICON = new ImageIcon(getClass().getClassLoader().getResource("images/mobile.png"));
	private final Icon FAX_ICON = new ImageIcon(getClass().getClassLoader().getResource("images/fax.png"));

	public NewContactInterface(ContactsInterface father, ContactsLogic logic) {
		super(father, ContactApplication.language.getProperty("NewContact"), Dialog.ModalityType.DOCUMENT_MODAL);
		this.listPhones = new ArrayList<Phone>();
		this.logicContacts = logic;
		initComponents();
		setVisible(true);
	}

	/**
	 * Init all the components
	 * 
	 */
	private void initComponents() {
		GridBagConstraints gridBagConstraints;

		nameLabel = new JLabel();
		nameField = new JTextField();
		phonesLabel = new JLabel();
		jScrollPanelPhones = new JScrollPane();
		jListPhones = new JList() {
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
		addPhoneButton = new JButton();
		modifyPhoneButton = new JButton();
		removePhoneButton = new JButton();
		acceptButton = new JButton();
		cancelButton = new JButton();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBackground(new Color(255, 255, 255));
		setMaximumSize(new Dimension(500, 450));
		setResizable(false);
		getContentPane().setLayout(new GridBagLayout());

		nameLabel.setForeground(new Color(255, 255, 255));
		nameLabel.setText(ContactApplication.language.getProperty("Name"));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(nameLabel, gridBagConstraints);

		nameField.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(nameField, gridBagConstraints);

		phonesLabel.setForeground(new Color(255, 255, 255));
		phonesLabel.setText(ContactApplication.language.getProperty("Phones"));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridheight = 3;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(phonesLabel, gridBagConstraints);

		jScrollPanelPhones.setViewportView(jListPhones);
		jListPhones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jListPhones.setCellRenderer(new CustomCellRender());
		jListPhones.setFocusTraversalPolicyProvider(true);
		jListPhones.addMouseListener(new MouseAdapter() {
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
		refreshListPhones();

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridheight = 3;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(jScrollPanelPhones, gridBagConstraints);

		addPhoneButton.setText(ContactApplication.language.getProperty("AddPhone"));
		addPhoneButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				addPhoneButtonMouseClicked(evt);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(addPhoneButton, gridBagConstraints);

		modifyPhoneButton.setText(ContactApplication.language.getProperty("ModifyPhone"));
		modifyPhoneButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				modifyPhoneButtonMouseClicked();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(modifyPhoneButton, gridBagConstraints);

		removePhoneButton.setText(ContactApplication.language.getProperty("DeletePhone"));
		removePhoneButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				removePhoneButtonMouseClicked();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(removePhoneButton, gridBagConstraints);

		acceptButton.setText(ContactApplication.language.getProperty("Accept"));
		acceptButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				acceptButtonMouseClicked();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		getContentPane().add(acceptButton, gridBagConstraints);

		cancelButton.setText(ContactApplication.language.getProperty("Cancel"));
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cancelButtonMouseClicked();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 6;
		getContentPane().add(cancelButton, gridBagConstraints);

		listPhonesFocusLost();

		pack();
	}

	/**
	 * Method to reload the jList with the updated phones
	 */
	private void refreshListPhones() {
		List<JPanel> phonesAux = new ArrayList<JPanel>();
		Collections.sort(this.listPhones);
		for (Phone t : this.listPhones) {
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			switch (t.getType()) {
			case HOME:
				panel.add(new JLabel(String.valueOf(t.getPhoneNumber().getNationalNumber()), HOME_ICON, JLabel.LEFT));
				break;
			case FAX:
				panel.add(new JLabel(String.valueOf(t.getPhoneNumber().getNationalNumber()), FAX_ICON, JLabel.LEFT));
				break;
			case MOBILE:
				panel.add(new JLabel(String.valueOf(t.getPhoneNumber().getNationalNumber()), MOBILE_ICON, JLabel.LEFT));
				break;
			case OFFICE:
				panel.add(new JLabel(String.valueOf(t.getPhoneNumber().getNationalNumber()), OFFICE_ICON, JLabel.LEFT));
				break;
			}
			phonesAux.add(panel);
		}
		this.jListPhones.setListData(phonesAux.toArray());
	}

	private void listPhonesMouseClicked() {
		modifyPhoneButton.setEnabled(true);
		removePhoneButton.setEnabled(true);
	}

	private void listPhonesFocusLost() {
		modifyPhoneButton.setEnabled(false);
		removePhoneButton.setEnabled(false);
	}

	private void addPhoneButtonMouseClicked(MouseEvent e) {
		NewPhoneInterface newPhoneInterface = new NewPhoneInterface(this);
		Phone phone = newPhoneInterface.getPhone();
		if (phone != null) {
			this.listPhones.add(phone);
			refreshListPhones();
		}
	}

	private void modifyPhoneButtonMouseClicked() {
		JLabel labelPhone = ((JLabel) ((JPanel) this.jListPhones.getSelectedValue()).getComponent(0));
		Phone phone = findPhone(labelPhone.getText(), labelPhone.getIcon());
		ModifyPhoneInterface modifyPhoneInterface = new ModifyPhoneInterface(this, phone);
		Phone mPhone = modifyPhoneInterface.getPhone();
		if (!String.valueOf(mPhone.getPhoneNumber().getNationalNumber()).equals(String.valueOf(phone.getPhoneNumber().getNationalNumber())) || mPhone.getType() != phone.getType()) {
			this.listPhones.remove(phone);
			this.listPhones.add(mPhone);
			refreshListPhones();
		}
	}

	private void removePhoneButtonMouseClicked() {
		JLabel labelPhone = ((JLabel) ((JPanel) this.jListPhones.getSelectedValue()).getComponent(0));
		Phone phone = findPhone(labelPhone.getText(), labelPhone.getIcon());
		JLabel label = new JLabel(ContactApplication.language.getProperty("DeletePhoneWarning"));
		label.setForeground(Color.WHITE);
		int option = JOptionPane.showConfirmDialog(null, label, ContactApplication.language.getProperty("Warning"), JOptionPane.YES_NO_OPTION);
		switch (option) {
		case 0:
			// Yes, delete phone number
			this.listPhones.remove(phone);
			refreshListPhones();
			this.jListPhones.clearSelection();
			listPhonesFocusLost();
			break;
		case 1:
			// No
			break;
		}
	}

	private Phone findPhone(String number, Icon icon) {
		TypePhone type;
		if (icon == HOME_ICON) {
			type = TypePhone.HOME;
		} else if (icon == FAX_ICON) {
			type = TypePhone.FAX;
		} else if (icon == MOBILE_ICON) {
			type = TypePhone.MOBILE;
		} else {
			type = TypePhone.OFFICE;
		}
		for (Phone f : this.listPhones) {
			if (String.valueOf(f.getPhoneNumber().getNationalNumber()).equals(number) && f.getType() == type) {
				return f;
			}
		}
		return null; // TODO
	}

	private void acceptButtonMouseClicked() {
		save();
	}

	private void cancelButtonMouseClicked() {
		if (this.changes || !this.nameField.getText().equals("")) {
			JLabel label = new JLabel(ContactApplication.language.getProperty("SaveChanges"));
			label.setForeground(Color.WHITE);
			int option = JOptionPane.showConfirmDialog(null, label, ContactApplication.language.getProperty("Warning"), JOptionPane.YES_NO_CANCEL_OPTION);
			switch (option) {
			case 0:
				save();
				break;
			case 1:
				// Undo
				this.nameField.setText("");
				this.listPhones.clear();
				this.dispose();
				break;
			case 2:
				// Cancel option
				break;
			}
		} else {
			this.dispose();
		}
	}

	/**
	 * Save method
	 */
	private void save() {
		if (this.logicContacts.getContact(this.nameField.getText()) != null) {
			// Ya existe contacto con ese nombre
			JLabel label = new JLabel(
					ContactApplication.language.getProperty("DuplicatedContact"));
			label.setForeground(Color.WHITE);
			JOptionPane.showMessageDialog(this, label, ContactApplication.language.getProperty("Warning"), JOptionPane.WARNING_MESSAGE);
		} else {
			this.dispose();
		}
	}

	public Contact getContact() {
		return new Contact(this.nameField.getText(), this.listPhones);
	}

}
