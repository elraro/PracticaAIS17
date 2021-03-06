package contacts;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Oscar de la Cuesta Campillo. www.palentino.es
 */
public class ContactsLogic {

	private Database database;
	
	public ContactsLogic() {
		this.database = new Database();
		try {
			this.database.loadFile();
		} catch (ClassNotFoundException | IOException e) {
			// TODO
			// Lanzar aviso de que no se ha podido leer el fichero por lo que sea
		}
	}
	
	public List<Contact> contactList() {
		return new ArrayList<Contact>(this.database.getContactList());
	}

	public boolean addContact(Contact c) {
		return this.database.addContact(c);
	}
	
	public boolean removeContact(Contact c) {
		return this.database.removeContact(c);
	}
	
	public int getIndexContact(Contact c) {
		return this.contactList().indexOf(c);
	}
	
	public TreeSet<Contact> search(String s) {
		return this.database.search(s);
	}
	
	public void save() {
		this.database.saveFile();
	}
	
	public void importCsv(String path) throws FileNotFoundException, IOException {
		this.database.importCsv(path);
	}
	
	public void exportCsv(String path) throws IOException {
		this.database.exportCsv(path);
	}

	/**
	 * @param name
	 * @return
	 */
	public Contact getContact(String nombre) {
		return this.database.getContact(nombre);
	}

}