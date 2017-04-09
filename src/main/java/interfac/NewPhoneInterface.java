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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

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
			// TODO preguntar cambios
		} else {
			this.dispose();
		}
	}

	private void acceptButtonActionPerformed(ActionEvent e) {
		// TODO aceptar
		this.dispose();
	}

	public Phone getPhone() {
		return new Phone(this.phoneTextField.getText(), this.typePhone);
	}

}
