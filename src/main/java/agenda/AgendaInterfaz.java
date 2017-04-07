package agenda;

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

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import material.MaterialUIConfig;

/**
 * Interfaz de la agenda
 * 
 * @author Alberto de Dios Bernáez
 */
public class AgendaInterfaz extends JFrame {

	private static final long serialVersionUID = 1L;
	// test
	private Object[] panels;

	// Interface
	private JButton botonBorrarContacto;
	private JButton botonExportarCSV;
	private JButton botonImportarCSV;
	private JScrollPane jScrollPane1;
	private JList listaContactos;
	private JButton botonCargarAgenda;
	private JLabel logoURJC;
	private JButton botonModificarContacto;
	private JButton botonNuevoContacto;
	private JButton botonGuardarAgenda;
	private JTextField areaBusqueda;

	/**
	 * Constructor
	 * 
	 * @param agendaLogica
	 *            La lógica de la aplicación
	 * 
	 */
	public AgendaInterfaz(AgendaLogica agendaLogica) {

		// get our images
		Icon pingImage = new ImageIcon(getClass().getClassLoader().getResource("imagenes/contact.png"));
		Icon tracerouteImage = new ImageIcon(getClass().getClassLoader().getResource("imagenes/contact.png"));
		Icon netstatImage = new ImageIcon(getClass().getClassLoader().getResource("imagenes/contact.png"));

		// add the images to jlabels with text
		JLabel pingLabel = new JLabel("Contacto1", pingImage, JLabel.LEFT);
		JLabel tracerouteLabel = new JLabel("Contacto2", tracerouteImage, JLabel.LEFT);
		JLabel netstatLabel = new JLabel("Contacto3", netstatImage, JLabel.LEFT);

		// create the corresponding panels
		JPanel pingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel traceroutePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel netstatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		// add the labels onto the panels
		pingPanel.add(pingLabel);
		traceroutePanel.add(tracerouteLabel);
		netstatPanel.add(netstatLabel);

		// create a panel array
		panels = new Object[] { pingPanel, traceroutePanel, netstatPanel };

		MaterialUIConfig.configureUI();

		initComponents();

		this.getContentPane().setBackground(new java.awt.Color(74, 74, 74));

		listaContactos.setListData(panels);

		this.setVisible(true);
	}

	/**
	 * Inicializar los componentes
	 * 
	 */
	private void initComponents() {
		GridBagConstraints gridBagConstraints;

		botonNuevoContacto = new JButton();
		botonModificarContacto = new JButton();
		jScrollPane1 = new JScrollPane();
		listaContactos = new JList() {
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
		botonBorrarContacto = new JButton();
		areaBusqueda = new JTextField();
		botonImportarCSV = new JButton();
		botonGuardarAgenda = new JButton();
		botonCargarAgenda = new JButton();
		logoURJC = new JLabel();
		botonExportarCSV = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Agenda telefónica");
		setBackground(new Color(0, 0, 0));
		setMaximumSize(new Dimension(426, 238));
		setMinimumSize(new Dimension(426, 238));
		setResizable(false);
		getContentPane().setLayout(new GridBagLayout());

		botonNuevoContacto.setText("Nuevo contacto");
		botonNuevoContacto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newContactActionPerformed(e);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(botonNuevoContacto, gridBagConstraints);

		botonModificarContacto.setText("Modificar contacto");
		botonModificarContacto.setEnabled(false);
		botonModificarContacto.addActionListener(new ActionListener() {
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
		getContentPane().add(botonModificarContacto, gridBagConstraints);

		listaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaContactos.setCellRenderer(new ContactoCellRender());
		listaContactos.setFocusTraversalPolicyProvider(true);
		listaContactos.addMouseListener(new MouseAdapter() {
			@Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList) e.getSource();
                if (list.locationToIndex(e.getPoint()) == -1 && !e.isShiftDown()
                        && !isMenuShortcutKeyDown(e)) {
                    list.clearSelection();
                    listContactsFocusLost(e);
                } else {
                	listContactsMouseClicked(e);
                }
            }

            private boolean isMenuShortcutKeyDown(InputEvent event) {
                return (event.getModifiers() & Toolkit.getDefaultToolkit()
                        .getMenuShortcutKeyMask()) != 0;
            }
		});
		jScrollPane1.setViewportView(listaContactos);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 9;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(jScrollPane1, gridBagConstraints);

		botonBorrarContacto.setText("Borrar contacto");
		botonBorrarContacto.setEnabled(false);
		botonBorrarContacto.addActionListener(new ActionListener() {
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
		getContentPane().add(botonBorrarContacto, gridBagConstraints);

		areaBusqueda.setBackground(new java.awt.Color(74, 74, 74));
		areaBusqueda.setForeground(new java.awt.Color(255, 255, 255));
		areaBusqueda.setText("Buscar...");
		areaBusqueda.setToolTipText("Busca en la agenda");
		areaBusqueda.setBorder(BorderFactory.createTitledBorder(null, "Buscar", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 12), new Color(255, 255, 255)));
		areaBusqueda.setSelectedTextColor(new Color(255, 255, 255));
		areaBusqueda.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				searchFieldFocusLost(evt);
			}
		});
		areaBusqueda.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				searchFieldActionPerformed(e);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(areaBusqueda, gridBagConstraints);

		botonImportarCSV.setText("Importar CSV");
		botonImportarCSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				importButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(botonImportarCSV, gridBagConstraints);

		botonGuardarAgenda.setText("Guardar agenda");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(botonGuardarAgenda, gridBagConstraints);

		botonCargarAgenda.setText("Cargar agenda");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(botonCargarAgenda, gridBagConstraints);

		logoURJC.setIcon(new ImageIcon(getClass().getResource("/imagenes/logo.png")));
		getContentPane().add(logoURJC, new GridBagConstraints());

		botonExportarCSV.setText("Exportar CSV");
		botonExportarCSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				exportButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(botonExportarCSV, gridBagConstraints);

		pack();
	}

	private void searchFieldActionPerformed(MouseEvent e) {
		if (areaBusqueda.getText().equals("Buscar...")) {
			areaBusqueda.setText("");
		}
	}

	private void searchFieldFocusLost(FocusEvent evt) {
		if (areaBusqueda.getText().equals("")) {
			areaBusqueda.setText("Buscar...");
		}
	}

	private void listContactsMouseClicked(MouseEvent evt) {
		botonModificarContacto.setEnabled(true);
		botonBorrarContacto.setEnabled(true);
	}

	private void listContactsFocusLost(MouseEvent evt) {
		botonModificarContacto.setEnabled(false);
		botonBorrarContacto.setEnabled(false);
	}

	private void newContactActionPerformed(ActionEvent evt) {
		NuevoContacto newContact = new NuevoContacto();
		newContact.setVisible(true);
	}

	private void deleteContactActionPerformed(ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void modifyContactActionPerformed(ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void importButtonActionPerformed(ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void exportButtonActionPerformed(ActionEvent evt) {
		// TODO add your handling code here:
	}
}
