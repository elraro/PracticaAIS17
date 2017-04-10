package interfac;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import contacts.Phone;
import contacts.TypePhone;

/**
 *
 * @author Alberto de Dios Bernáez
 */
public class NewPhoneInterface extends JDialog {

	private static final long serialVersionUID = 1L;
	private ButtonGroup groupButtons;
	private JRadioButton homeButton;
	private JRadioButton officeButton;
	private JRadioButton mobileButton;
	private JRadioButton faxButton;
	private JTextField phoneTextField;
	private JLabel phoneLabel;
	private JLabel typeLabel;
	private JButton acceptButton;
	private JButton cancelButton;

	// Tipo de telefono
	private TypePhone typePhone;

	public NewPhoneInterface(JDialog padre) {
		super(padre, "Añadir teléfono", Dialog.ModalityType.DOCUMENT_MODAL);
		initComponents();
		setVisible(true);
	}

	/**
	 * Inicializar los componentes
	 * 
	 */
	private void initComponents() {
		GridBagConstraints gridBagConstraints;

		groupButtons = new ButtonGroup();
		phoneLabel = new JLabel();
		typeLabel = new JLabel();
		phoneTextField = new JTextField();
		homeButton = new JRadioButton();
		officeButton = new JRadioButton();
		mobileButton = new JRadioButton();
		faxButton = new JRadioButton();
		acceptButton = new JButton();
		cancelButton = new JButton();
		groupButtons.add(homeButton);
		groupButtons.add(officeButton);
		groupButtons.add(mobileButton);
		groupButtons.add(faxButton);

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		getContentPane().setLayout(new GridBagLayout());

		phoneLabel.setForeground(new Color(255, 255, 255));
		phoneLabel.setText("Teléfono");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(phoneLabel, gridBagConstraints);

		phoneTextField.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(phoneTextField, gridBagConstraints);

		typeLabel.setForeground(new Color(255, 255, 255));
		typeLabel.setText("Tipo");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridheight = 3;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(typeLabel, gridBagConstraints);

		homeButton.setText("Casa");
		homeButton.setSelected(true);
		this.typePhone = TypePhone.HOME;
		homeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				typePhone = TypePhone.HOME;
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(homeButton, gridBagConstraints);

		officeButton.setText("Oficina");
		officeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				typePhone = TypePhone.OFFICE;
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(officeButton, gridBagConstraints);

		mobileButton.setText("Móvil");
		mobileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				typePhone = TypePhone.MOBILE;
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(mobileButton, gridBagConstraints);

		faxButton.setText("Fax");
		faxButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				typePhone = TypePhone.FAX;
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(faxButton, gridBagConstraints);

		acceptButton.setText("Aceptar");
		acceptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				acceptButtonActionPerformed(e);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		getContentPane().add(acceptButton, gridBagConstraints);

		cancelButton.setText("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelButtonActionPerformed(e);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		getContentPane().add(cancelButton, gridBagConstraints);

		pack();
	}

	private void cancelButtonActionPerformed(ActionEvent e) {
		if (!this.phoneTextField.equals("")) {
			JLabel label = new JLabel("¿Desea guardar los cambios?");
			label.setForeground(Color.WHITE);
			int option = JOptionPane.showConfirmDialog(null, label, "Aviso", JOptionPane.YES_NO_CANCEL_OPTION);
			switch (option) {
			case 0:
				// Guardamos cambios
				this.dispose();
				break;
			case 1:
				// Deshacemos cambios
				this.phoneTextField.setText("");
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

	private void acceptButtonActionPerformed(ActionEvent e) {
		if (this.phoneTextField.getText().length() < 3) {
			JLabel label = new JLabel("El número insertado no es válido.");
			label.setForeground(Color.WHITE);
			JOptionPane.showMessageDialog(null, label);
		} else {
			PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
			try {
				phoneUtil.parse(this.phoneTextField.getText(), "ES");
				this.dispose();
			} catch (NumberParseException ex) {
				JLabel label = new JLabel("El número insertado no es válido.");
				label.setForeground(Color.WHITE);
				JOptionPane.showMessageDialog(null, label);
			}
		}
	}

	public Phone getPhone() {
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		try {
			PhoneNumber phone = phoneUtil.parse(this.phoneTextField.getText(), "ES");
			return new Phone(phone, this.typePhone);
		} catch (NumberParseException e) {
			System.err.println("NumberParseException was thrown: " + e.toString());
		}
		return null; // TODO
	}

}
