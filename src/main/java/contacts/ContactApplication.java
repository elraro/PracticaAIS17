package contacts;

import interfac.ContactsInterface;

/**
 * @author Alberto de Dios Bernáez
 *
 */
public class ContactApplication {
	
	public static Language language;
	
	public ContactApplication() {
		new ContactsInterface(new ContactsLogic());
	}

	public static void main(String[] args) {
		if (System.getProperty("user.language").equals("es")) {
			language = new Language("spanish");
		} else {
			language = new Language("english");
		}
		new ContactApplication();
	}
}
