package contacts;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.TreeSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

/**
 * @author Saul Alonso, Alejandro Checa, Alberto de Dios
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
			// file.createNewFile();
		} catch (EOFException e) {
			JLabel label = new JLabel(
					"El fichero de la base de datos no es válido o está corrupto. Por favor, borralo o restaura una copia de seguridad.");
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
			for (Phone p : phones) {
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

	public void exportCsv(String path) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (Contact c : this.listContact) {
			sb.append(c.getName()).append(";");
			List<Phone> phones = c.getList();
			for (Phone p : phones) {
				sb.append(String.valueOf(p.getPhoneNumber().getNationalNumber())).append(";");
				sb.append(p.getType()).append(";");
			}
			sb.append("\r\n");
		}
		File fichero = new File(path);
		Writer f;
		try {
			f = new BufferedWriter(new FileWriter(fichero));
			f.write(sb.toString());
			f.close();

		} catch (IOException e) {
			System.out.println("Wrong csv export.");
		}
		System.out.println("Correct export to csv.");
	}

	public void importCsv(String path) throws FileNotFoundException, IOException {
		File fichero = new File(path);
		String line;
		FileReader f;
		BufferedReader bufferedReader;
		StringTokenizer st;
		TreeSet<Contact> listContact = new TreeSet<Contact>();
		try {
			f = new FileReader(fichero);
			bufferedReader = new BufferedReader(f);
			PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
			while ((line = bufferedReader.readLine()) != null) {
				st = new StringTokenizer(line, ";");
				String name = st.nextToken();
				List<Phone> phones = new ArrayList<Phone>();
				while(st.hasMoreElements()) {
					try {
						phones.add(new Phone(phoneUtil.parse(st.nextToken(), "ES"), TypePhone.valueOf(st.nextToken())));
					} catch (IllegalArgumentException | NumberParseException e) {
						throw new IOException();
					}
				}
				Contact c = new Contact(name, phones);
				listContact.add(c);
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		} catch (IOException e) {
			throw new IOException();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException();
		}
		this.listContact = listContact;
		System.out.println("Correct import from csv");
	}

}
