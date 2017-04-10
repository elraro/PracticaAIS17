package contacts;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @author Saul Alonso
 */
public class Database {

	private TreeSet<Contact> listContact = new TreeSet<>();

	public Database() {
		listContact = new TreeSet<Contact>();
	}

	public TreeSet<Contact> getContactList() {
		return listContact;
	}

	public void saveFile() {
		File file = new File(System.getProperty("user.dir") + File.separator + "URJCcontacts.dat");
		FileOutputStream fileOutput = null;
		ObjectOutputStream objectOutput = null;

		try {
			file.createNewFile();
			fileOutput = new FileOutputStream(file);
			objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(this.listContact);
		} catch (FileNotFoundException e) {
			// TODO
			e.printStackTrace();
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
		System.out.println("Database saved!");
	}

	public void loadFile() throws IOException, ClassNotFoundException {
		File file = new File(System.getProperty("user.dir") + File.separator + "URJCcontacts.dat");
		FileInputStream fileInput;
		ObjectInputStream objectInput;
		BufferedInputStream bufferedInput;

		try {
			fileInput = new FileInputStream(file);
			bufferedInput = new BufferedInputStream(fileInput);
			objectInput = new ObjectInputStream(bufferedInput);
			this.listContact = (TreeSet<Contact>) objectInput.readObject();
			fileInput.close();
			objectInput.close();
		} catch (FileNotFoundException e) {
			file.createNewFile();
		} catch (EOFException e) {
			JLabel label = new JLabel("El fichero de la base de datos no es válido o está corrupto. Por favor, borralo o restaura una copia de seguridad.");
			JOptionPane.showMessageDialog(null, label);
		}
	}

	public boolean addContact(Contact c) {
		return this.listContact.add(c);
	}
	
	public boolean removeContact(Contact c) {
		return this.listContact.remove(c);
	}

	/**
	 * @param name
	 * @return
	 */
	public Contact getContact(String name) {
		Iterator<Contact> iterator = this.listContact.iterator();
		while (iterator.hasNext()) {
			Contact node = iterator.next();
			if (node.getName().equals(name))
				return node;
		}
		return null; // TODO
	}

	public TreeSet<Contact> search(String s) {
		s = s.toLowerCase();
		TreeSet<Contact> search = new TreeSet<Contact>();
		Iterator<Contact> iterator = this.listContact.iterator();
		while (iterator.hasNext()) {
			Contact node = iterator.next();
			List<Phone> phones = node.getList();
			boolean phone = false;
			for(Phone p : phones) {
				if (String.valueOf(p.getPhoneNumber().getNationalNumber()).contains(s)) {
					phone = true;
					break;
				}
			}
			if (node.getName().toLowerCase().contains(s) || phone) {
				search.add(node);
			}
		}
		return search;
	}

}
