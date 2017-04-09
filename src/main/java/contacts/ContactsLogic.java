package contacts;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	
	public void save() {
		this.database.saveFile();
	}

	/**
	 * @param name
	 * @return
	 */
	public Contact getContact(String nombre) {
		return this.database.getContact(nombre);
	}

}