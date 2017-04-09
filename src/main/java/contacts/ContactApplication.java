package contacts;

import interfac.ContactsInterface;

/**
 * @author Alberto de Dios Bernáez
 *
 */
public class ContactApplication {
	public ContactApplication() {
		new ContactsInterface(new ContactsLogic());
	}

	public static void main(String[] args) {
		new ContactApplication();
	}
}
