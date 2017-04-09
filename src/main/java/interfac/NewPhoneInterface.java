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
	private ButtonGroup grupoBotones;
	private JRadioButton casaBoton;
	private JRadioButton oficinaBoton;
	private JRadioButton movilBoton;
	private JRadioButton faxBoton;
	private JTextField telefonoCampo;
	private JLabel telefonoLabel;
	private JLabel tipoLabel;
	private JButton aceptarBoton;
	private JButton cancelarBoton;

	// Tipo de telefono
	private TypePhone tipoTelefono;

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

		grupoBotones = new ButtonGroup();
		telefonoLabel = new JLabel();
		tipoLabel = new JLabel();
		telefonoCampo = new JTextField();
		casaBoton = new JRadioButton();
		oficinaBoton = new JRadioButton();
		movilBoton = new JRadioButton();
		faxBoton = new JRadioButton();
		aceptarBoton = new JButton();
		cancelarBoton = new JButton();
		grupoBotones.add(casaBoton);
		grupoBotones.add(oficinaBoton);
		grupoBotones.add(movilBoton);
		grupoBotones.add(faxBoton);

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		getContentPane().setLayout(new GridBagLayout());

		telefonoLabel.setForeground(new Color(255, 255, 255));
		telefonoLabel.setText("Teléfono");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(telefonoLabel, gridBagConstraints);

		telefonoCampo.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(telefonoCampo, gridBagConstraints);

		tipoLabel.setForeground(new Color(255, 255, 255));
		tipoLabel.setText("Tipo");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridheight = 3;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(tipoLabel, gridBagConstraints);

		casaBoton.setText("Casa");
		casaBoton.setSelected(true);
		this.tipoTelefono = TypePhone.HOME;
		casaBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tipoTelefono = TypePhone.HOME;
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(casaBoton, gridBagConstraints);

		oficinaBoton.setText("Oficina");
		oficinaBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tipoTelefono = TypePhone.OFFICE;
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(oficinaBoton, gridBagConstraints);

		movilBoton.setText("Móvil");
		movilBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tipoTelefono = TypePhone.MOBILE;
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(movilBoton, gridBagConstraints);

		faxBoton.setText("Fax");
		faxBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tipoTelefono = TypePhone.FAX;
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(faxBoton, gridBagConstraints);

		aceptarBoton.setText("Aceptar");
		aceptarBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aceptarBotonActionPerformed(e);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		getContentPane().add(aceptarBoton, gridBagConstraints);

		cancelarBoton.setText("Cancelar");
		cancelarBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarBotonActionPerformed(e);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		getContentPane().add(cancelarBoton, gridBagConstraints);

		pack();
	}

	private void cancelarBotonActionPerformed(ActionEvent e) {
		if (!this.telefonoCampo.equals("")) {
			// TODO preguntar cambios
		} else {
			this.dispose();
		}
	}

	private void aceptarBotonActionPerformed(ActionEvent e) {
		// TODO aceptar
		this.dispose();
	}

	public Phone getTelefono() {
		return new Phone(this.telefonoCampo.getText(), this.tipoTelefono);
	}

}
