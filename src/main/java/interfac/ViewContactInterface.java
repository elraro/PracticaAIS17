package interfac;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

import contacts.Contact;
import contacts.ContactApplication;
import contacts.ContactsLogic;
import contacts.Phone;

/**
 *
 * @author Alberto de Dios Bernáez
 */
public class ViewContactInterface extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton closeButton;
	private JList jListPhones;
	private JScrollPane jScrollPanelPhones;
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel phonesLabel;

	// List of phones
	private List<Phone> listPhones;
	// Logic of the contact app
	private ContactsLogic logicContacts;
	// Icons
	private final Icon HOME_ICON = new ImageIcon(getClass().getClassLoader().getResource("images/home.png"));
	private final Icon OFFICE_ICON = new ImageIcon(getClass().getClassLoader().getResource("images/office.png"));
	private final Icon MOBILE_ICON = new ImageIcon(getClass().getClassLoader().getResource("images/mobile.png"));
	private final Icon FAX_ICON = new ImageIcon(getClass().getClassLoader().getResource("images/fax.png"));

	public ViewContactInterface(ContactsInterface father, Contact c, ContactsLogic logic) {
		super(father, ContactApplication.language.getProperty("ViewContact"), Dialog.ModalityType.DOCUMENT_MODAL);
		this.listPhones = new ArrayList<Phone>();
		this.logicContacts = logic;
		initComponents();
		this.nameField.setText(new String(c.getName()));
		this.listPhones = c.getList();
		refreshListPhones();
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
		closeButton = new JButton();

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
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(nameLabel, gridBagConstraints);

		nameField.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.weightx=1;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(nameField, gridBagConstraints);

		phonesLabel.setForeground(new Color(255, 255, 255));
		phonesLabel.setText(ContactApplication.language.getProperty("Phones"));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridheight = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(phonesLabel, gridBagConstraints);

		jScrollPanelPhones.setViewportView(jListPhones);
		jListPhones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jListPhones.setCellRenderer(new CustomCellRender());
		jListPhones.setFocusTraversalPolicyProvider(true);
		refreshListPhones();

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridheight = 3;
		gridBagConstraints.weightx=1;
		gridBagConstraints.weighty=1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(jScrollPanelPhones, gridBagConstraints);

		closeButton.setText(ContactApplication.language.getProperty("Close"));
		closeButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				closeButtonMouseClicked();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		getContentPane().add(closeButton, gridBagConstraints);

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

	private void closeButtonMouseClicked() {
		this.dispose();
	}

}
