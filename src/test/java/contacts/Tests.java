package contacts;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.junit.Test;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
public class Tests {

	@Test
	public void CsvTest() throws IOException, NumberParseException {
		Database db = new Database();
		List<Phone> c1Phones = new ArrayList<Phone>();
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		PhoneNumber phone;
		phone = phoneUtil.parse("916180325", "ES");
		c1Phones.add(new Phone(phone, TypePhone.HOME));
		Contact c1 = new Contact("Pepe", c1Phones);
		phone = phoneUtil.parse("689961352", "ES");
		List<Phone> c2Phones = new ArrayList<Phone>();
		c2Phones.add(new Phone(phone, TypePhone.MOBILE));
		Contact c2 = new Contact("Juan", c2Phones);
		db.addContact(c1);
		db.addContact(c2);
		db.exportCsv("prueba.csv");
		
		db = new Database();
		db.importCsv("prueba.csv");
		c1 = null;
		c1 = db.getContact("Pepe");
		assertEquals(c1.getName(), "Pepe");
		assertEquals(c1.getList(), c1Phones);
	}

	@Test
	public void AddContactTest() throws IOException, NumberParseException {
		Database db = new Database();
		List<Phone> c1Phones = new ArrayList<Phone>();
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		PhoneNumber phone;
		phone = phoneUtil.parse("916180325", "ES");
		c1Phones.add(new Phone(phone, TypePhone.HOME));
		Contact c1 = new Contact("Pepe", c1Phones);
		db.addContact(c1);
		Contact c3 = db.getContact("Pepe");
		assertEquals(c3.getName(), "Pepe");
		assertEquals(c3.getList(), c1Phones);
	}
	
	@Test
	public void RemoveContactTest() throws IOException, NumberParseException {
		Database db = new Database();
		List<Phone> c1Phones = new ArrayList<Phone>();
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		PhoneNumber phone;
		phone = phoneUtil.parse("916180325", "ES");
		c1Phones.add(new Phone(phone, TypePhone.HOME));
		Contact c1 = new Contact("Pepe", c1Phones);
		db.addContact(c1);
		db.removeContact(c1);
		Contact c3 = db.getContact("Pepe");
		assertEquals(null,c3 );
	}
	
	@Test
	public void SearchContactTest() throws IOException, NumberParseException {
		Database db = new Database();
		List<Phone> c1Phones = new ArrayList<Phone>();
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		PhoneNumber phone;
		phone = phoneUtil.parse("916180325", "ES");
		c1Phones.add(new Phone(phone, TypePhone.HOME));
		Contact c1 = new Contact("Pepe", c1Phones);
		phone = phoneUtil.parse("689961352", "ES");
		List<Phone> c2Phones = new ArrayList<Phone>();
		c2Phones.add(new Phone(phone, TypePhone.MOBILE));
		Contact c2 = new Contact("Juan", c2Phones);
		db.addContact(c1);
		db.addContact(c2);
		TreeSet<Contact>list1 = db.search("Pepe");
		TreeSet<Contact>list2 = db.search("689961352");
		assertEquals(list1.contains(c1) , true);
		assertEquals(list2.contains(c2), true);
	}
}
