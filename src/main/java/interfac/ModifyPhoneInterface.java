package interfac;

import java.awt.Color;
import java.awt.Component;
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

import contacts.Phone;
import contacts.TypePhone;

/**
 *
 * @author Alberto de Dios Bernáez
 */
public class ModifyPhoneInterface extends JDialog {

	private static final long serialVersionUID = 1L;
	private ButtonGroup groupButton;
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

	// Cambios realizados
	private boolean changes = false;
	private String oldPhone;
	private TypePhone oldTypePhone;

	public ModifyPhoneInterface(JDialog father, Phone phone) {
		super(father, "Modificar teléfono", Dialog.ModalityType.DOCUMENT_MODAL);
		initComponents();
		this.oldPhone = new String(phone.getPhoneNumber());
		this.oldTypePhone = phone.getType();
		this.phoneTextField.setText(phone.getPhoneNumber());
		this.typePhone = phone.getType();
		this.homeButton.setSelected(false);
		switch(phone.getType()) {
		case HOME:
			this.homeButton.setSelected(true);
			break;
		case FAX:
			this.faxButton.setSelected(true);
			break;
		case MOBILE:
			this.mobileButton.setSelected(true);
			break;
		case OFFICE:
			this.officeButton.setSelected(true);
			break;
		}
		setVisible(true);
	}

	/**
	 * Inicializar los componentes
	 * 
	 */
	private void initComponents() {
		GridBagConstraints gridBagConstraints;

		groupButton = new ButtonGroup();
		phoneLabel = new JLabel();
		typeLabel = new JLabel();
		phoneTextField = new JTextField();
		homeButton = new JRadioButton();
		officeButton = new JRadioButton();
		mobileButton = new JRadioButton();
		faxButton = new JRadioButton();
		acceptButton = new JButton();
		cancelButton = new JButton();
		groupButton.add(homeButton);
		groupButton.add(officeButton);
		groupButton.add(mobileButton);
		groupButton.add(faxButton);

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
				changes = true;
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
				changes = true;
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
				changes = true;
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
				changes = true;
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
		if (!this.phoneTextField.equals(this.oldPhone) || changes) {
			int opcion = JOptionPane.showConfirmDialog((Component) null, "¿Desea guardar los cambios?","Aviso", JOptionPane.YES_NO_CANCEL_OPTION);
		    switch(opcion) {
		    case 0:
		    case 1:
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
		this.dispose();
	}

	public Phone getPhone() {
		return new Phone(this.phoneTextField.getText(), this.typePhone);
	}

}
