package contacts;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.TreeSet;

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

}
