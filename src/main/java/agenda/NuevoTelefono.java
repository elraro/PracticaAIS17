package agenda;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author Alberto de Dios Bernáez
 */
public class NuevoTelefono extends JDialog {

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

	public NuevoTelefono() {
		initComponents();
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
		setTitle("Añadir teléfono");
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
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(casaBoton, gridBagConstraints);

		oficinaBoton.setText("Oficina");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(oficinaBoton, gridBagConstraints);

		movilBoton.setText("Móvil");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(movilBoton, gridBagConstraints);

		faxBoton.setText("Fax");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(faxBoton, gridBagConstraints);

		aceptarBoton.setText("Aceptar");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		getContentPane().add(aceptarBoton, gridBagConstraints);

		cancelarBoton.setText("Cancelar");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
		getContentPane().add(cancelarBoton, gridBagConstraints);

		pack();
	}

}
