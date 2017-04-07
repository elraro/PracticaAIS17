package agenda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

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

import material.MaterialUIConfig;

/**
 * Interfaz de la agenda
 * @author Alberto de Dios Bernáez
 */
public class AgendaInterfaz extends JFrame {

	private static final long serialVersionUID = 1L;
	// test
	private Object[] panels;
	
	// Interface
	private JButton deleteContact;
	private JButton exportButton;
	private JButton importButton;
	private JScrollPane jScrollPane1;
	private JList listContacts;
	private JButton loadContacts;
	private JLabel logoURJC;
	private JButton modifyContact;
	private JButton newContact;
	private JButton saveContacts;
	private JTextField searchField;

	/**
	 * Constructor
	 * 
	 * @param agendaLogica La lógica de la aplicación
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

		listContacts.setListData(panels);

		this.setVisible(true);
	}

	/**
	 * Inicializar los componentes
	 * 
	 */
	private void initComponents() {
		GridBagConstraints gridBagConstraints;

		newContact = new JButton();
		modifyContact = new JButton();
		jScrollPane1 = new JScrollPane();
		listContacts = new JList();
		deleteContact = new JButton();
		searchField = new JTextField();
		importButton = new JButton();
		saveContacts = new JButton();
		loadContacts = new JButton();
		logoURJC = new JLabel();
		exportButton = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Agenda telefónica");
		setBackground(new Color(0, 0, 0));
		setMaximumSize(new Dimension(426, 238));
		setMinimumSize(new Dimension(426, 238));
		setResizable(false);
		getContentPane().setLayout(new GridBagLayout());

		newContact.setText("Nuevo contacto");
		newContact.addActionListener(new ActionListener() {
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
		getContentPane().add(newContact, gridBagConstraints);

		modifyContact.setText("Modificar contacto");
		modifyContact.setEnabled(false);
		modifyContact.addActionListener(new ActionListener() {
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
		getContentPane().add(modifyContact, gridBagConstraints);

		listContacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listContacts.setCellRenderer(new ContactoCellRender());
		listContacts.setFocusTraversalPolicyProvider(true);
		listContacts.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent evt) {
				listContactsFocusLost(evt);
			}
		});
		listContacts.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                listContactsMouseClicked(e);
            }
        });
		jScrollPane1.setViewportView(listContacts);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 9;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(jScrollPane1, gridBagConstraints);

		deleteContact.setText("Borrar contacto");
		deleteContact.setEnabled(false);
		deleteContact.addActionListener(new ActionListener() {
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
		getContentPane().add(deleteContact, gridBagConstraints);

		searchField.setBackground(new java.awt.Color(74, 74, 74));
		searchField.setForeground(new java.awt.Color(255, 255, 255));
		searchField.setText("Buscar...");
		searchField.setToolTipText("Busca en la agenda");
		searchField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
		searchField.setSelectedTextColor(new java.awt.Color(255, 255, 255));
		searchField.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				searchFieldFocusLost(evt);
			}
		});
		searchField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchFieldActionPerformed(e);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(searchField, gridBagConstraints);

		importButton.setText("Importar CSV");
		importButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				importButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(importButton, gridBagConstraints);

		saveContacts.setText("Guardar agenda");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(saveContacts, gridBagConstraints);

		loadContacts.setText("Cargar agenda");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(loadContacts, gridBagConstraints);

		logoURJC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png")));
		getContentPane().add(logoURJC, new GridBagConstraints());

		exportButton.setText("Exportar CSV");
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				exportButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(exportButton, gridBagConstraints);

		pack();
	}// </editor-fold>

	private void searchFieldActionPerformed(ActionEvent evt) {
		if (searchField.getText().equals("Buscar...")) {
			searchField.setText("");
		}
	}

	private void searchFieldFocusLost(FocusEvent evt) {
		if (searchField.getText().equals("")) {
			searchField.setText("Buscar...");
		}
	}

	private void listContactsMouseClicked(MouseEvent evt) {                                          
        modifyContact.setEnabled(true);
        deleteContact.setEnabled(true);
    }

	private void listContactsFocusLost(FocusEvent evt) {
		// aqui algo raro
		// modifyContact.setEnabled(false);
		// deleteContact.setEnabled(false);
		// listContacts.clearSelection();
	}

	private void newContactActionPerformed(ActionEvent evt) {
		NewContact newContact = new NewContact();
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
