package contacts;

import java.io.Serializable;

/**
 * @author Diego Forte Jara
 */
public class Phone implements Serializable, Comparable<Phone> {

	private static final long serialVersionUID = 1L;
	private String phoneNumber;
	private TypePhone type;

	public Phone(String number, TypePhone type) {
		this.phoneNumber = number;
		this.type = type;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String number) {
		this.phoneNumber = number;
	}

	public TypePhone getType() {
		return this.type;
	}

	public void setTipo(TypePhone type) {
		this.type = type;
	}

	public Boolean equals(Phone t) {
		return (this.getPhoneNumber().equals(t.getPhoneNumber()));
	}

	@Override
	public int compareTo(Phone o) {
		if (this.getType().ordinal() > o.getType().ordinal()) {
			return 1;
		} else if (this.getType().ordinal() < o.getType().ordinal()) {
			return -1;
		} else {
			int comp = this.getPhoneNumber().compareTo(o.getPhoneNumber());
			if (comp > 0) {
				return 1;
			} else if (comp < 0) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	@Override
	public String toString() {
		return "Phone [phoneNumber=" + phoneNumber + ", type=" + type + "]";
	}

}
