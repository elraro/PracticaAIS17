package contacts;

import java.io.Serializable;
import java.util.List;

/**
 * @author Diego Forte Jara
 */
public class Contact implements Serializable, Comparable<Contact> {

	private static final long serialVersionUID = 1L;
	private String name;
	private List<Phone> listPhones;

	public Contact(String name, List<Phone> listPhones) {
		this.name = name;
		this.listPhones = listPhones;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public List<Phone> getList() {
		return this.listPhones;
	}

	@Override
	public int compareTo(Contact o) {
		return this.name.compareTo(o.getName());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contact [name=" + name + ", listPhones=" + listPhones.toString() + "]";
	}
}
