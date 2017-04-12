package contacts;

import java.io.Serializable;

import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

/**
 * @author Diego Forte Jara
 */
public class Phone implements Serializable, Comparable<Phone> {

	private static final long serialVersionUID = 1L;
	private PhoneNumber phoneNumber;
	private TypePhone type;

	public Phone(PhoneNumber number, TypePhone type) {
		this.phoneNumber = number;
		this.type = type;
	}

	public PhoneNumber getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(PhoneNumber number) {
		this.phoneNumber = number;
	}

	public TypePhone getType() {
		return this.type;
	}

	public void setTipo(TypePhone type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phone other = (Phone) obj;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public int compareTo(Phone o) {
		if (this.getType().ordinal() > o.getType().ordinal()) {
			return 1;
		} else if (this.getType().ordinal() < o.getType().ordinal()) {
			return -1;
		} else {
			return this.getPhoneNumber().getNationalNumber() < o.getPhoneNumber().getNationalNumber() ? -1
					: this.getPhoneNumber().getNationalNumber() > o.getPhoneNumber().getNationalNumber() ? 1 : 0;
		}
	}

	@Override
	public String toString() {
		return "Phone [phoneNumber=" + phoneNumber + ", type=" + type + "]";
	}

}
